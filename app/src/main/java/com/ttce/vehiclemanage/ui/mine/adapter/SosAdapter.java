package com.ttce.vehiclemanage.ui.mine.adapter;

import android.content.Context;

import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.aspsine.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.SosBean;

import java.util.List;

/**
 * Created by hk on 2019/6/21.
 */

public class SosAdapter extends MultiItemRecycleViewAdapter<SosBean> {

    public SosAdapter(Context context, final List<SosBean> datas) {
        super(context, datas, new MultiItemTypeSupport<SosBean>() {
            @Override
            public int getLayoutId(int type) {
                return R.layout.adapter_sos;
            }

            @Override
            public int getItemViewType(int position, SosBean msg) {
                return 0;
            }
        });
    }

    @Override
    public void convert(ViewHolderHelper holder, SosBean newsSummary) {
        switch (holder.getLayoutId()) {
            case R.layout.adapter_sos:
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
    private void setItemValues(final ViewHolderHelper holder, final SosBean newsSummary, final int position) {
        holder.setText(R.id.tv_time, newsSummary.getCreateTimeFormat());
        holder.setText(R.id.tv_comname, newsSummary.getTitle());
        holder.setText(R.id.tv_name, newsSummary.getLinkMan() + newsSummary.getLinkPhone());
        holder.setText(R.id.tv_content, newsSummary.getContent());
        holder.setText(R.id.tv_adress, newsSummary.getAddress());
    }

}