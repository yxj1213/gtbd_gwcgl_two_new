package com.ttce.vehiclemanage.widget;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ttce.vehiclemanage.R;

import java.util.List;

/**
 * Created by Administrator on 2019/9/23.
 */

public class ListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ListAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_name, item);
    }
}