package com.ttce.vehiclemanage.ui.map.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;

import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.aspsine.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.FenceListBean;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hk on 2019/7/9.
 */

public class FenceListAdapter extends MultiItemRecycleViewAdapter<FenceListBean> {

    private FenceListenter fenceListenter;

    public FenceListAdapter(Context context, final List<FenceListBean> datas, FenceListenter fenceListenter) {
        super(context, datas, new MultiItemTypeSupport<FenceListBean>() {

            @Override
            public int getLayoutId(int type) {
                return R.layout.adapter_fence_list;
            }

            @Override
            public int getItemViewType(int position, FenceListBean msg) {
                return 0;
            }
        });
        this.fenceListenter = fenceListenter;
    }

    @Override
    public void convert(ViewHolderHelper holder, FenceListBean newsSummary) {
        switch (holder.getLayoutId()) {
            case R.layout.adapter_fence_list:
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
        if (newsSummary.getAlarmTypes() != null && newsSummary.getAlarmTypes().length > 0) {
            holder.setChecked(R.id.cb_jin, Arrays.asList(newsSummary.getAlarmTypes()).contains(2));
            holder.setChecked(R.id.cb_chu, Arrays.asList(newsSummary.getAlarmTypes()).contains(4));
            holder.setVisible(R.id.iv_state, true);
        } else {
            holder.setVisible(R.id.iv_state, false);
        }

        holder.setOnCheckChangeListener(R.id.cb_jin, new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                fenceListenter.updateAlarmType(newsSummary, isChecked, 2);
            }
        });

        holder.setOnCheckChangeListener(R.id.cb_chu, new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                fenceListenter.updateAlarmType(newsSummary, isChecked, 4);
            }
        });
        holder.setOnClickListener(R.id.tv_car_info, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fenceListenter.onCarListInfo(newsSummary);
            }
        });

    }

    public interface FenceListenter {
        void updateAlarmType(FenceListBean fenceListBean, boolean checked, int type);

        void onCarListInfo(FenceListBean fenceListBean);
    }


}
