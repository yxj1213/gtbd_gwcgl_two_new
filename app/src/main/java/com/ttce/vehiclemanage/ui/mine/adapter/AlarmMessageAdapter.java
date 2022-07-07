package com.ttce.vehiclemanage.ui.mine.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import com.aspsine.irecyclerview.baseadapter.BaseReclyerViewAdapter;
import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.AlarmListBean;
import com.ttce.vehiclemanage.bean.SystemMessageBean;

import java.util.List;

/**
 * Created by hk on 2019/6/25.
 */

public class AlarmMessageAdapter extends CommonRecycleViewAdapter<AlarmListBean> {

    public AlarmMessageAdapter(Context context, int layoutId, List<AlarmListBean> mDatass) {
        super(context, layoutId, mDatass);
    }

    @Override
    public void convert(ViewHolderHelper holder, AlarmListBean newsSummary) {
        switch (holder.getLayoutId()) {
            case R.layout.adapter_alarm_message:
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
    private void setItemValues(final ViewHolderHelper holder, final AlarmListBean newsSummary, final int position) {
        holder.setText(R.id.tv_name, newsSummary.getPlateNumber());
        holder.setText(R.id.tv_tips, newsSummary.getName() + "(" + newsSummary.getDeviceId() + ")");
        holder.setText(R.id.tv_time, newsSummary.getAlarmTimeStr());
    }

}