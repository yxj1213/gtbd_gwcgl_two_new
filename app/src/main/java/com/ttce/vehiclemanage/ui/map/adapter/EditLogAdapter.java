package com.ttce.vehiclemanage.ui.map.adapter;

import android.content.Context;

import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.InstructDetailBean;

import java.util.List;

/**
 * Created by hk on 2019/6/25.
 */

public class EditLogAdapter extends CommonRecycleViewAdapter<InstructDetailBean> {

    public EditLogAdapter(Context context, int layoutId, List<InstructDetailBean> mDatass) {
        super(context, layoutId, mDatass);
    }

    @Override
    public void convert(ViewHolderHelper holder, InstructDetailBean newsSummary) {
        switch (holder.getLayoutId()) {
            case R.layout.adapter_edit_log:
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
    private void setItemValues(final ViewHolderHelper holder, final InstructDetailBean newsSummary, final int position) {
        holder.setText(R.id.tv_name, newsSummary.getPlateNumber());
        holder.setText(R.id.tv_from, newsSummary.getOrginName());
        holder.setText(R.id.tv_content, newsSummary.getName());
        holder.setText(R.id.tv_time, newsSummary.getSendTimeFormat());
        holder.setText(R.id.tv_state, newsSummary.getStatusFormat());
    }

}