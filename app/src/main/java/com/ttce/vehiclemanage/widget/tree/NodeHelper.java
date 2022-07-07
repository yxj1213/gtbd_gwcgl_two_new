package com.ttce.vehiclemanage.widget.tree;


import android.util.Log;

import com.google.gson.Gson;
import com.ttce.vehiclemanage.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HQOCSHheqing on 2016/8/2.
 *
 * @description 节点帮助类
 */
public class NodeHelper {

    /**
     * 传入所有的要展示的节点数据
     *
     * @param DeptList 返回值是所有的根节点
     * @return
     */
    public static List<Dept> sortDepts(List<Dept> DeptList) {

        List<Dept> rootDepts = new ArrayList<>();
        int size = DeptList.size();
        Dept m;
        Dept n;
        //两个for循环整理出所有数据之间的斧子关系，最后会构造出一个森林（就是可能有多棵树）
        for (int i = 0; i < size; i++) {
            m = DeptList.get(i);
            for (int j = i + 1; j < size; j++) {
                n = DeptList.get(j);
                if (m.parent(n)) {
                    m.get_childrenList().add(n);
                    n.set_parent(m);
                } else if (m.child(n)) {
                    n.get_childrenList().add(m);
                    m.set_parent(n);
                }
            }
        }
        //找出所有的树根，同事设置相应的图标（后面你会发现其实这里的主要
        // 作用是设置叶节点和非叶节点的图标）
        for (int i = 0; i < size; i++) {
            m = DeptList.get(i);
            if (m.isRoot()) {

                rootDepts.add(m);
            }
            setDeptIcon(m);

           /* if(m.get_childrenList().size()<=0&&m.getCompanyItemBean().getType() != 0){
                Log.d("1111111111122222",m.getName()+"此处数据应该删除----"+m.get_childrenList().size()+"---");
            }*/
        }
        DeptList.clear();//此时所有的关系已经整理完成了
        // ，森林构造完成，可以清空之前的数据，释放内存，提高性能
        // ，如果之前的数据还有用的话就不清空
        DeptList = rootDepts;//返回所有的根节点
        rootDepts = null;
        return DeptList;
    }

    public static List<Dept> sortDeptss(List<Dept> DeptList, String ss) {

        List<Dept> rootDepts = new ArrayList<>();
        int size = DeptList.size();
        Dept m;
        Dept n;
        //两个for循环整理出所有数据之间的斧子关系，最后会构造出一个森林（就是可能有多棵树）
        for (int i = 0; i < size; i++) {
            m = DeptList.get(i);
            for (int j = i + 1; j < size; j++) {
                n = DeptList.get(j);
                if (m.parent(n)) {
                    m.get_childrenList().add(n);
                    n.set_parent(m);
                } else if (m.child(n)) {
                    n.get_childrenList().add(m);
                    m.set_parent(n);
                }
            }
        }
        //找出所有的树根，同事设置相应的图标（后面你会发现其实这里的主要
        // 作用是设置叶节点和非叶节点的图标）
        for (int i = 0; i < size; i++) {
            m = DeptList.get(i);
            if (m.isLeaf() && m.getName().contains(ss)) {
                rootDepts.add(m);
            }
//            setDeptIcon(m);
        }
        DeptList.clear();//此时所有的关系已经整理完成了
        // ，森林构造完成，可以清空之前的数据，释放内存，提高性能
        // ，如果之前的数据还有用的话就不清空
        DeptList = rootDepts;//返回所有的根节点
        rootDepts = null;
        return DeptList;
    }

    /**
     * 设置图标
     *
     * @param dept
     */
    private static void setDeptIcon(Dept dept) {
        if (!dept.isLeaf()) {
            if (dept.isExpand()) {
                dept.set_icon(R.mipmap.arraw_smal_down);
            } else {
                dept.set_icon(R.mipmap.arraw_smal_right);
            }
        } else {
            if (dept.getCompanyItemBean().getType() == 0) {
                dept.set_icon(-1);
            } else {
                dept.set_icon(R.mipmap.arraw_smal_right);
            }
        }
    }
}
