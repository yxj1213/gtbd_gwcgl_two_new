package com.ttce.vehiclemanage.ui.mine.adapter;

import android.content.Context;

import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.CommonListBean;

import java.util.List;

/**
 * Created by hk on 2019/6/25.
 */

public class TypeItemAdapter extends CommonRecycleViewAdapter<CommonListBean> {

    public TypeItemAdapter(Context context, int layoutId, List<CommonListBean> mDatass) {
        super(context, layoutId, mDatass);
    }

    @Override
    public void convert(ViewHolderHelper holder, CommonListBean newsSummary) {
        switch (holder.getLayoutId()) {
            case R.layout.item_wb_by_item:
                setItemValues(holder, newsSummary, getPosition(holder));
                break;
        }
    }

    /**
     * 普通样式
     *
     * @param holder
     * @param item
     * @param position
     */
    private void setItemValues(final ViewHolderHelper holder, final CommonListBean item, final int position) {
        if(item.isSelected()){
            holder.setBackgroundRes(R.id.rootLL,R.drawable.bluebg_blueborder30_round_tv_bg);
            holder.setFlowText(R.id.itemTV, item.getKey());
            holder.setTextColorRes(R.id.itemTV, R.color.white);
        }else{
            holder.setBackgroundRes(R.id.rootLL,R.drawable.whitebg_grayborder30_round_tv_bg);
            holder.setFlowText(R.id.itemTV, item.getKey());
            holder.setTextColorRes(R.id.itemTV, R.color.text_grey_color);
        }

    }

}