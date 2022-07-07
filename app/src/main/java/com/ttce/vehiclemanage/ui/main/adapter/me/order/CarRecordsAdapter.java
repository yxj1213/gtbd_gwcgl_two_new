package com.ttce.vehiclemanage.ui.main.adapter.me.order;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.CarRecordsBean;

import java.util.List;

/**
 * 已修改
 * */
public class CarRecordsAdapter extends CommonRecycleViewAdapter<CarRecordsBean> {



    public CarRecordsAdapter(Context context, int layoutId, List<CarRecordsBean> mDatass) {
        super(context, layoutId, mDatass);

    }

    @Override
    public void convert(ViewHolderHelper holder, CarRecordsBean applyOrderResponseBean) {
        switch (holder.getLayoutId()) {
            case R.layout.order_details_car_records_item:
                setItemValues(holder, applyOrderResponseBean, getPosition(holder));
                break;
        }
    }

    private void setItemValues(final ViewHolderHelper holder, final CarRecordsBean item, final int position) {
        holder.setText(R.id.tv_zt, item.getEscapeOrderStateFormat());
        holder.setText(R.id.tv_time, item.getCreateTimeFormat());
        ImageView imageView=holder.getView(R.id.iv_state);

        switch (item.getIcon()){
            case 10:
                imageView.setImageResource(R.mipmap.icon_gray);
                break;
            case 20:
                imageView.setImageResource(R.mipmap.icon_blue_sel);
                break;
            case 30:
                imageView.setImageResource(R.mipmap.icon_red_close);
                break;
        }

        holder.setText(R.id.txtzw, item.getLabelTitle());
        holder.setText(R.id.txtry, item.getRealName()+"  "+item.getPhone());
        TextView  bhly=holder.getView(R.id.bhly);
        RelativeLayout rel_ly=holder.getView(R.id.rel_ly);

        if(String.valueOf(item.getCheckState()).equals(mContext.getResources().getString(R.string.type3_3))){
            bhly.setVisibility(View.VISIBLE);
            bhly.setText("驳回原因："+item.getCheckReason());
            if(item.getIcon()==10){
                rel_ly.setVisibility(View.GONE);
            }else{
                bhly.setVisibility(View.GONE);
            }
        }else{
            bhly.setVisibility(View.GONE);
        }

        View view=holder.getView(R.id.view);
        if(item.getOrderState()==120){
            view.setVisibility(View.GONE);
        }else{
            view.setVisibility(View.VISIBLE);
        }
    }
}
