package com.ttce.vehiclemanage.ui.mine.adapter;

import android.content.Context;
import android.view.View;

import com.aspsine.irecyclerview.baseadapter.BaseReclyerViewAdapter;
import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.aspsine.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.CompanysItemBean;
import com.ttce.vehiclemanage.bean.FenceListBean;

import java.util.List;

/**
 * 围栏管理
 * Created by hk on 2019/6/20.
 */

public class RailManageAdapter extends MultiItemRecycleViewAdapter<FenceListBean> {

    private RailListenter railListenter;

    public RailManageAdapter(Context context, final List<FenceListBean> datas, RailListenter railListenter) {
        super(context, datas, new MultiItemTypeSupport<FenceListBean>() {

            @Override
            public int getLayoutId(int type) {
                return R.layout.adapter_rail_manage;
            }

            @Override
            public int getItemViewType(int position, FenceListBean msg) {
                return 0;
            }
        });
        this.railListenter = railListenter;
    }

    @Override
    public void convert(ViewHolderHelper holder, FenceListBean newsSummary) {
        switch (holder.getLayoutId()) {
            case R.layout.adapter_rail_manage:
                setItemValues(holder, newsSummary, getPosition(holder));
                break;
        }
    }

    /**
     * 普通样式
     *
     * @param holder
     * @param newsSummary
     * @param position
     */
    private void setItemValues(final ViewHolderHelper holder, final FenceListBean newsSummary, final int position) {
        holder.setText(R.id.tv_name, newsSummary.getName());
        holder.setText(R.id.tv_long, "方圆" + newsSummary.getRadius() + "米");
        holder.setText(R.id.tv_address, newsSummary.getAddress());
        holder.setOnClickListener(R.id.tv_update, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                railListenter.updateRail(newsSummary);
            }
        });
        holder.setOnClickListener(R.id.tv_add, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                railListenter.addRail(newsSummary);
            }
        });
        holder.setOnClickListener(R.id.tv_del, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                railListenter.delRail(newsSummary);
            }
        });
        holder.setOnClickListener(R.id.tv_car_info, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                railListenter.showCars(newsSummary);
            }
        });
    }

    public interface RailListenter {
        void updateRail(FenceListBean fenceListBean);

        void addRail(FenceListBean fenceListBean);

        void delRail(FenceListBean fenceListBean);

        void showCars(FenceListBean fenceListBean);
    }

}