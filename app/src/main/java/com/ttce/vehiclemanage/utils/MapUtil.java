package com.ttce.vehiclemanage.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.text.TextUtils;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.baidu.mapapi.utils.DistanceUtil;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.app.AppApplication;
import com.ttce.vehiclemanage.bean.RoutePoint;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapUtil {

    // 将行政区域显示在可视范围
    public static void showAllArea(Context context, TextureMapView mapView, BaiduMap baiduMap,
                                   List<List<LatLng>> latLngList) {
        try {
            if (context == null || mapView == null || baiduMap == null || latLngList == null ||
                    latLngList.size() == 0) {
                return;
            }

            List<LatLng> latLngs = new ArrayList<>();
            for (List<LatLng> tempLatlngs : latLngList) {
                if (tempLatlngs == null || tempLatlngs.size() == 0) {
                    continue;
                }
                latLngs.addAll(tempLatlngs);
            }

            if (latLngs.size() == 0) {
                return;
            }

            LatLngBounds.Builder builder = new LatLngBounds.Builder();

            for (LatLng latlng : latLngs) {
                builder = builder.include(latlng);
            }

            LatLngBounds latlngBounds = builder.build();
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLngBounds(latlngBounds, mapView
                    .getWidth(), mapView.getHeight());
            baiduMap.animateMapStatus(u, 1200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public static GeoPoint getGeoPointBystr(String str) {
        GeoPoint gpGeoPoint = null;
        if (str != null) {
            Geocoder gc = new Geocoder(AppApplication.getAppContext(), Locale.getDefault());
            List<Address> addressList = null;
            try {
                addressList = gc.getFromLocationName(str, 1);
                if (!addressList.isEmpty()) {
                    Address address_temp = addressList.get(0);
                    //计算经纬度
                    double Latitude = address_temp.getLatitude() * 1E6;
                    double Longitude = address_temp.getLongitude() * 1E6;
                    System.out.println("经度:" + Latitude);
                    System.out.println("纬度:" + Longitude);
                    //生产GeoPoint
                    gpGeoPoint = new GeoPoint((int) Latitude, (int) Longitude);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return gpGeoPoint;

    }

    public static void showAllArea(Context context, TextureMapView mapView, BaiduMap baiduMap,
                                   List<List<LatLng>> latLngList, int offset) {
        try {
            if (context == null || mapView == null || baiduMap == null || latLngList == null ||
                    latLngList.size() == 0) {
                return;
            }

            List<LatLng> latLngs = new ArrayList<>();
            for (List<LatLng> tempLatlngs : latLngList) {
                if (tempLatlngs == null || tempLatlngs.size() == 0) {
                    continue;
                }
                latLngs.addAll(tempLatlngs);
            }

            if (latLngs.size() == 0) {
                return;
            }

            LatLngBounds.Builder builder = new LatLngBounds.Builder();

            for (LatLng latlng : latLngs) {
                builder = builder.include(latlng);
            }

            LatLngBounds latlngBounds = builder.build();
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLngBounds(latlngBounds, mapView
                    .getWidth() - offset, mapView.getHeight() - offset);
            baiduMap.animateMapStatus(u, 1200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 给指定区域外面加上阴影
    public static void districtOverlay(Context context, BaiduMap baiduMap,
                                       List<List<LatLng>> latLngList) {
        try {
            if (context == null || baiduMap == null | latLngList == null
                    || latLngList.size() == 0) {
                return;
            }

            // 区域点集合
            List<LatLng> boundLatLngs = new ArrayList<>();

            // 找右侧极值
            int longMaxIndx = 0;
            LatLng longMax = latLngList.get(0).get(0);
            for (List<LatLng> latLngs : latLngList) {
                if (latLngs == null || latLngs.size() == 0) {
                    continue;
                }

                for (LatLng latLng : latLngs) {
                    boundLatLngs.add(latLng);

                    if (latLng.longitude > longMax.longitude) {
                        longMax = latLng;
                        longMaxIndx = boundLatLngs.size() - 1;
                    }
                }
            }

            // 调整集合顺序，从右侧极值开始
            List<LatLng> latLngsTemp = new ArrayList<>();
            latLngsTemp
                    .addAll(boundLatLngs.subList(longMaxIndx, boundLatLngs.size()));
            latLngsTemp.addAll(boundLatLngs.subList(0, longMaxIndx));

            // 加上外侧点
            latLngsTemp.add(new LatLng(latLngsTemp.get(0).latitude - 0.00000001,
                    latLngsTemp.get(0).longitude));
            latLngsTemp
                    .add(new LatLng(latLngsTemp.get(0).latitude - 0.00000001, 150));
            latLngsTemp.add(new LatLng(10, 150));
            latLngsTemp.add(new LatLng(10, 60));
            latLngsTemp.add(new LatLng(60, 60));
            latLngsTemp.add(new LatLng(60, 150));
            latLngsTemp.add(new LatLng(latLngsTemp.get(0).latitude, 150));

            OverlayOptions ooPolygon = new PolygonOptions().points(latLngsTemp)
                    .stroke(new Stroke(1,
                            context.getResources().getColor(R.color.map_overlay)))
                    .fillColor(
                            context.getResources().getColor(R.color.map_overlay));
            baiduMap.addOverlay(ooPolygon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 读取低排区坐标点文件
    public static List<LatLng> readPoints(Context context) {
        try {
            InputStream is = context.getAssets().open("tz_points.txt");
            int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String text = new String(buffer);
            if (TextUtils.isEmpty(text)) {
                return null;
            }

            String[] pointArray = text.split(";");
            if (pointArray.length == 0) {
                return null;
            }

            List<LatLng> latLngs = new ArrayList<>();

            for (String onePointStr : pointArray) {
                if (TextUtils.isEmpty(onePointStr)) {
                    continue;
                }

                String[] onePoint = onePointStr.split(",");
                if (onePoint.length != 2) {
                    continue;
                }

                latLngs.add(new LatLng(Double.parseDouble(onePoint[1]), Double.parseDouble
                        (onePoint[0])));
            }

            return latLngs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据两点算取图标转的角度
     */
    public static double getAngle(LatLng fromPoint, LatLng toPoint) {
        double slope = getSlope(fromPoint, toPoint);
        if (slope == Double.MAX_VALUE) {
            if (toPoint.latitude > fromPoint.latitude) {
                return 0;
            } else {
                return 180;
            }
        }
        float deltAngle = 0;
        if ((toPoint.latitude - fromPoint.latitude) * slope < 0) {
            deltAngle = 180;
        }
        double radio = Math.atan(slope);
        return 180 * (radio / Math.PI) + deltAngle - 90;
    }

    /**
     * 算斜率
     */
    public static double getSlope(LatLng fromPoint, LatLng toPoint) {
        if (toPoint.longitude == fromPoint.longitude) {
            return Double.MAX_VALUE;
        }
        return (toPoint.latitude - fromPoint.latitude) / (toPoint.longitude - fromPoint.longitude);
    }

    /**
     * X方向移动的距离
     */
    public static double xMoveDistance(LatLng startPoint, LatLng endPoint, double DISTANCE, double length) {
        return Math.abs((endPoint.latitude - startPoint.latitude) / length * DISTANCE);
    }

    /**
     * Y方向移动的距离
     */
    public static double yMoveDistance(LatLng startPoint, LatLng endPoint, double DISTANCE, double length) {
        return Math.abs((endPoint.longitude - startPoint.longitude) / length * DISTANCE);
    }

    /***
     * 逆地址解析（坐标-地址）
     * @return
     */
    public static void geoCoderResult(LatLng point, OnGetGeoCoderResultListener listener) {
        GeoCoder mCoder = GeoCoder.newInstance();
        mCoder.setOnGetGeoCodeResultListener(listener);
        mCoder.reverseGeoCode(new ReverseGeoCodeOption()
                .location(point)
                // POI召回半径，允许设置区间为0-1000米，超过1000米按1000米召回。默认值为1000
                .radius(1000));
        mCoder.destroy();
    }

    /***
     * 计算两点之间距离 单位：米
     * @param start
     * @param end
     * @return
     */
    public static Double getDistance(LatLng start, LatLng end) {
        /*double lat1 = (Math.PI/180)*start.latitude;
        double lat2 = (Math.PI/180)*end.latitude;

        double lon1 = (Math.PI/180)*start.longitude;
        double lon2 = (Math.PI/180)*end.longitude;

        //地球半径
        double R = 6378.137;

        //两点间距离 km，如果想要米的话，结果*1000
        double d =  Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon2-lon1))*R;
        if(d<1)
            return (Double)(d*1000);
        else
            return Double.valueOf(String.format("%.2f",d*1000));*/

        return DistanceUtil.getDistance(start, end);
    }

    /***
     * 计算两点之间距离 单位：公里
     * @param start
     * @param end
     * @return
     */
    public static Double getDistanceGl(LatLng start, LatLng end) {
        double m = DistanceUtil.getDistance(start, end);
        m = m / 1000;
        BigDecimal bg = new BigDecimal(m);
        //保留两位小数
        Double lenth = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return lenth;
    }

    /**
     * 返回一个点是否在一个多边形区域内
     *
     * @param mPoints 多边形坐标点列表
     * @param point   待判断点
     * @return true 多边形包含这个点,false 多边形未包含这个点。
     */
    public static boolean isPolygonContainsPoint(List<LatLng> mPoints, LatLng point) {
        int nCross = 0;
        for (int i = 0; i < mPoints.size(); i++) {
            LatLng p1 = mPoints.get(i);
            LatLng p2 = mPoints.get((i + 1) % mPoints.size());
            // 取多边形任意一个边,做点point的水平延长线,求解与当前边的交点个数
            // p1p2是水平线段,要么没有交点,要么有无限个交点
            if (p1.longitude == p2.longitude)
                continue;
            // point 在p1p2 底部 --> 无交点
            if (point.longitude < Math.min(p1.longitude, p2.longitude))
                continue;
            // point 在p1p2 顶部 --> 无交点
            if (point.longitude >= Math.max(p1.longitude, p2.longitude))
                continue;
            // 求解 point点水平线与当前p1p2边的交点的 X 坐标
            double x = (point.longitude - p1.longitude) * (p2.latitude - p1.latitude) / (p2.longitude - p1.latitude) + p1.latitude;
            if (x > point.latitude) // 当x=point.x时,说明point在p1p2线段上
                nCross++; // 只统计单边交点
        }
        // 单边交点为偶数，点在多边形之外 ---
        return (nCross % 2 == 1);
    }

    /***
     * 坐标地址转换
     * @param sourceLatLng
     * @return
     */
    public static LatLng gps_to_BaiduGps(LatLng sourceLatLng) {
        CoordinateConverter converter = new CoordinateConverter();
        converter.from(CoordinateConverter.CoordType.GPS);
        converter.coord(sourceLatLng);
        return converter.convert();
    }

    /***
     * 轨迹平滑算法优化
     * @param inPoint
     * @return
     */
    public List<LatLng> optimizePoints(List<RoutePoint> inPoint) {
        int size = inPoint.size();
        List<LatLng> outPoint = new ArrayList<>();

        int i;
        if (size < 5) {
            for (RoutePoint routePoint : inPoint) {
                outPoint.add(new LatLng(routePoint.getLat(), routePoint.getLng()));
            }
            return outPoint;
        } else {
            // Latitude
            inPoint.get(0)
                    .setLat((3.0 * inPoint.get(0).getLat() + 2.0
                            * inPoint.get(1).getLat() + inPoint.get(2).getLat() - inPoint
                            .get(4).getLat()) / 5.0);
            inPoint.get(1)
                    .setLat((4.0 * inPoint.get(0).getLat() + 3.0
                            * inPoint.get(1).getLat() + 2
                            * inPoint.get(2).getLat() + inPoint.get(3).getLat()) / 10.0);

            inPoint.get(size - 2).setLat(
                    (4.0 * inPoint.get(size - 1).getLat() + 3.0
                            * inPoint.get(size - 2).getLat() + 2
                            * inPoint.get(size - 3).getLat() + inPoint.get(
                            size - 4).getLat()) / 10.0);
            inPoint.get(size - 1).setLat(
                    (3.0 * inPoint.get(size - 1).getLat() + 2.0
                            * inPoint.get(size - 2).getLat()
                            + inPoint.get(size - 3).getLat() - inPoint.get(
                            size - 5).getLat()) / 5.0);

            // Longitude
            inPoint.get(0)
                    .setLng((3.0 * inPoint.get(0).getLng() + 2.0
                            * inPoint.get(1).getLng() + inPoint.get(2).getLng() - inPoint
                            .get(4).getLng()) / 5.0);
            inPoint.get(1)
                    .setLng((4.0 * inPoint.get(0).getLng() + 3.0
                            * inPoint.get(1).getLng() + 2
                            * inPoint.get(2).getLng() + inPoint.get(3).getLng()) / 10.0);

            inPoint.get(size - 2).setLng(
                    (4.0 * inPoint.get(size - 1).getLng() + 3.0
                            * inPoint.get(size - 2).getLng() + 2
                            * inPoint.get(size - 3).getLng() + inPoint.get(
                            size - 4).getLng()) / 10.0);
            inPoint.get(size - 1).setLng(
                    (3.0 * inPoint.get(size - 1).getLng() + 2.0
                            * inPoint.get(size - 2).getLng()
                            + inPoint.get(size - 3).getLng() - inPoint.get(
                            size - 5).getLng()) / 5.0);
        }

        for (RoutePoint routePoint : inPoint) {
            outPoint.add(new LatLng(routePoint.getLat(), routePoint.getLng()));
        }

        return outPoint;
    }

    /***
     * 获取多个点的中心点（400KM以外）
     * @param geoCoordinateList
     * @return
     */
    public static LatLng getCenterPointFromListOfPointsOut400(List<LatLng> geoCoordinateList) {
        int total = geoCoordinateList.size();
        double X = 0, Y = 0, Z = 0;
        for (LatLng p : geoCoordinateList) {
            double lat, lon, x, y, z;
            lat = p.latitude * Math.PI / 180;
            lon = p.longitude * Math.PI / 180;
            x = Math.cos(lat) * Math.cos(lon);
            y = Math.cos(lat) * Math.sin(lon);
            z = Math.sin(lat);
            X += x;
            Y += y;
            Z += z;
        }
        X = X / total;
        Y = Y / total;
        Z = Z / total;
        double Lon = Math.atan2(Y, X);
        double Hyp = Math.sqrt(X * X + Y * Y);
        double Lat = Math.atan2(Z, Hyp);
        return new LatLng(Lat * 180 / Math.PI, Lon * 180 / Math.PI);
    }

}
