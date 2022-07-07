package com.ttce.vehiclemanage.ui.mine.adapter;

import android.content.Context;

import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.SystemMessageBean;

import java.util.List;

/**
 * Created by hk on 2019/6/24.
 */

public class SystemMessageAdapter extends CommonRecycleViewAdapter<SystemMessageBean> {

    public SystemMessageAdapter(Context context, int layoutId, List<SystemMessageBean> mDatass) {
        super(context, layoutId, mDatass);
    }

    @Override
    public void convert(ViewHolderHelper holder, SystemMessageBean newsSummary) {
        switch (holder.getLayoutId()) {
            case R.layout.adapter_system_message:
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
    private void setItemValues(final ViewHolderHelper holder, final SystemMessageBean newsSummary, final int position) {
        holder.setText(R.id.tv_notice, newsSummary.getContent());
    }

}