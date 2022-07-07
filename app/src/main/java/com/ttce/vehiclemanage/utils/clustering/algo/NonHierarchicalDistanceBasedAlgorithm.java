/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */

package com.ttce.vehiclemanage.utils.clustering.algo;

import android.util.Log;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.ttce.vehiclemanage.utils.clustering.Cluster;
import com.ttce.vehiclemanage.utils.clustering.ClusterItem;
import com.ttce.vehiclemanage.utils.projection.Bounds;
import com.ttce.vehiclemanage.utils.projection.Point;
import com.ttce.vehiclemanage.utils.projection.SphericalMercatorProjection;
import com.ttce.vehiclemanage.utils.quadtree.PointQuadTree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.ttce.vehiclemanage.utils.MarkerManager.MAX_DISTANCE_AT_ZOOM;

/**
 * A simple clustering algorithm with O(nlog n) performance. Resulting clusters are not
 * hierarchical.
 * <p/>
 * High level algorithm:<br>
 * 1. Iterate over items in the order they were added (candidate clusters).<br>
 * 2. Create a cluster with the center of the item. <br>
 * 3. Add all items that are within a certain distance to the cluster. <br>
 * 4. Move any items out of an existing cluster if they are closer to another cluster. <br>
 * 5. Remove those items from the list of candidate clusters.
 * <p/>
 * Clusters have the center of the first element (not the centroid of the items within it).
 */
public class NonHierarchicalDistanceBasedAlgorithm<T extends ClusterItem> implements Algorithm<T> {
       BaiduMap mapView;
       public NonHierarchicalDistanceBasedAlgorithm(BaiduMap mmap){
           this.mapView=mmap;
       }
    /**
     * Any modifications should be synchronized on mQuadTree.
     */
    private final Collection<QuadItem<T>> mItems = new ArrayList<QuadItem<T>>();

    /**
     * Any modifications should be synchronized on mQuadTree.
     */
    private final PointQuadTree<QuadItem<T>> mQuadTree = new PointQuadTree<QuadItem<T>>(0, 1, 0, 1);

    private static final SphericalMercatorProjection PROJECTION =
            new SphericalMercatorProjection(1);

    @Override
    public void addItem(T item) {
        final QuadItem<T> quadItem = new QuadItem<T>(item);
        synchronized (mQuadTree) {
            mItems.add(quadItem);
            mQuadTree.add(quadItem);
        }
    }

    @Override
    public void addItems(Collection<T> items) {
        for (T item : items) {
            addItem(item);
        }
    }

    @Override
    public void clearItems() {
        synchronized (mQuadTree) {
            mItems.clear();
            mQuadTree.clear();
        }
    }

    @Override
    public void removeItem(T item) {
        // TODO: delegate QuadItem#hashCode and QuadItem#equals to its item.
        throw new UnsupportedOperationException(
                "NonHierarchicalDistanceBasedAlgorithm.remove not implemented");
    }

    /**
     * cluster算法核心
     *
     * @param zoom map的级别
     * @return百度地图对应缩放级别
     * int[] zoomLevel = { 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6,5, 4, 3 };
     *
     * 对应级别单位
     * String[] zoomLevelStr = { “10”, “20”, “50”, “100”, “200”, “500”, “1000”,
     * “2000”, “5000”, “10000”, “20000”, “25000”, “50000”, “100000”,
     * “200000”, “500000”, “1000000”, “2000000” }; // 单位/m
     */
    @Override
    public Set<? extends Cluster<T>> getClusters(double zoom) {
        final int discreteZoom = (int) zoom;

        //TODO 表明marker之间聚合的距离，如果zoomSpecificSpanyu越大越容易聚合，反之越不容易聚合，因此我将它修改为final double zoomSpecificSpan = MAX_DISTANCE_AT_ZOOM / Math.pow(2, discreteZoom-2)。这样降低了聚合的条件，会使地图上的marker减少，节省渲染时间。
        final double zoomSpecificSpan = MAX_DISTANCE_AT_ZOOM / Math.pow(2, discreteZoom-2)/256;//定义的可进行聚合的距离

       /* //TODO 3、当地图缩放比例小于10时，级别越小，要聚合。反之不聚合。
        final double zoomSpecificSpan2;//定义的可进行聚合的距离  聚合的距离越远 越容易聚合
        int zooms= (int) mapView.getMapStatus().zoom;
        if(zooms < 10){
            zoomSpecificSpan2=MAX_DISTANCE_AT_ZOOM / Math.pow(2, discreteZoom-2)/256;//定义的可进行聚合的距离
        }else{
            zoomSpecificSpan2=0;
        }*/
        final Set<QuadItem<T>> visitedCandidates = new HashSet<QuadItem<T>>();//遍历QuadItem时保存被遍历过的Item
        final Set<Cluster<T>> results = new HashSet<Cluster<T>>();//保存要返回的cluster簇，每个cluster中包含若干个MyItem对象
        final Map<QuadItem<T>, Double> distanceToCluster = new HashMap<QuadItem<T>, Double>();  //Item --> 此Item与所属的cluster中心点的距离

        final Map<QuadItem<T>, StaticCluster<T>> itemToCluster =
                new HashMap<QuadItem<T>, StaticCluster<T>>();//Item对象 --> 此Item所属的cluster


        synchronized (mQuadTree) {
            for (QuadItem<T> candidate : mItems) {//遍历所有的QuadItem
                if (visitedCandidates.contains(candidate)) {//如果此Item已经被别的cluster框住了，就不再处理它
                    // Candidate is already part of another cluster.
                    continue;
                }

                Bounds searchBounds = createBoundsFromSpan(candidate.getPoint(), zoomSpecificSpan);//这个就是我们说的，根据给定距离生成一个框框
                Collection<QuadItem<T>> clusterItems;
                // search 某边界范围内的clusterItems
                clusterItems = mQuadTree.search(searchBounds); //从quadTree中搜索出框框内的点

                if (clusterItems.size() == 1) {
                    // Only the current marker is in range. Just add the single item to the results.
                    // 如果只有一个点，那么这一个点就是一个cluster，QuadItem也实现了Cluster接口，也可以当作Cluster对象

                    results.add(candidate);
                    visitedCandidates.add(candidate);
                    distanceToCluster.put(candidate, 0d);//并且结束此次循环

                    continue;
                }
               StaticCluster<T> cluster =
                        new StaticCluster<T>(candidate.mClusterItem.getPosition());//如果搜索到多个点,那么就以此item为中心创建一个cluster
                results.add(cluster);

                for (QuadItem<T> clusterItem : clusterItems) {//遍历所有框住的点
                    Double existingDistance = distanceToCluster.get(clusterItem);//获取此item与原来的cluster中心的距离(如果之前已经被其他cluster给框住了)
                    double distance = distanceSquared(clusterItem.getPoint(), candidate.getPoint());//获取此item与现在这个cluster中心的距离
                    if (existingDistance != null) {
                        // Item already belongs to another cluster. Check if it's closer to this
                        // cluster.
                        // 判断那个距离跟小
                        if (existingDistance < distance) {
                            continue;
                        }
                        // Move item to the closer cluster.
                        //如果跟现在的cluster距离更近，则将此item从原来的cluster中移除
                        itemToCluster.get(clusterItem).remove(clusterItem.mClusterItem);
                    }
                    distanceToCluster.put(clusterItem, distance);//保存此item到cluster中心的距离
                    cluster.add(clusterItem.mClusterItem);//将此item添加到cluster中

                    itemToCluster.put(clusterItem, cluster);//建立item -- cluster 的map

                }
                visitedCandidates.addAll(clusterItems);//将所有框住过的点添加到已访问的List中
            }
        }
        return results;
    }

    @Override
    public Collection<T> getItems() {
        final List<T> items = new ArrayList<T>();
        synchronized (mQuadTree) {
            for (QuadItem<T> quadItem : mItems) {
                items.add(quadItem.mClusterItem);
            }
        }
        return items;
    }

    private double distanceSquared(Point a, Point b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }

    private Bounds createBoundsFromSpan(Point p, double span) {
        // TODO: Use a span that takes into account the visual size of the marker, not just its
        // LatLng.
        double halfSpan = span / 2;
        return new Bounds(
                p.x - halfSpan, p.x + halfSpan,
                p.y - halfSpan, p.y + halfSpan);
    }

    private static class QuadItem<T extends ClusterItem> implements PointQuadTree.Item, Cluster<T> {
        private final T mClusterItem;
        private final Point mPoint;
        private final LatLng mPosition;
        private Set<T> singletonSet;

        private QuadItem(T item) {
            mClusterItem = item;
            mPosition = item.getPosition();
            mPoint = PROJECTION.toPoint(mPosition);
            singletonSet = Collections.singleton(mClusterItem);
        }

        @Override
        public Point getPoint() {
            return mPoint;
        }

        @Override
        public LatLng getPosition() {
            return mPosition;
        }

        @Override
        public Set<T> getItems() {
            return singletonSet;
        }

        @Override
        public int getSize() {
            return 1;
        }
    }
}
