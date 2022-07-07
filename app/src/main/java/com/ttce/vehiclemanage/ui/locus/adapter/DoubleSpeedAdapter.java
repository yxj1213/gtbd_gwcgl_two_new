package com.ttce.vehiclemanage.ui.locus.adapter;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.aspsine.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.ui.locus.activity.NewLocusActivity;

import java.util.List;

/**
 * Created by hk on 2019/6/21.
 */

public class DoubleSpeedAdapter extends MultiItemRecycleViewAdapter<String> {
    int mPosition = 0;

    public DoubleSpeedAdapter(Context context, final List<String> datas) {
        super(context, datas, new MultiItemTypeSupport<String>() {
            @Override
            public int getLayoutId(int type) {
                return R.layout.activity_locaus_double_speed;
            }

            @Override
            public int getItemViewType(int position, String msg) {
                return 0;
            }
        });
    }

    @Override
    public void convert(ViewHolderHelper holder, String newsSummary) {
        switch (holder.getLayoutId()) {
            case R.layout.activity_locaus_double_speed:
                setItemValues(holder, newsSummary, getPosition(holder));
                break;
        }
    }

    /**
     * 普通样式
     *
     * @param holder
     * @param newsSummary
     */
    private void setItemValues(final ViewHolderHelper holder, final String newsSummary, int position) {
        position=holder.getLayoutPosition();
        TextView txt=holder.getView(R.id.txt);
        txt.setText(newsSummary);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.setTextColorRes(R.id.txt,R.color.black);
                txt.setTextSize(16);
                mPosition = holder.getLayoutPosition();
                notifyDataSetChanged();
                switch (txt.getText().toString()){
                    case "x1":
                        NewLocusActivity.DISTANCE1=0.00005;
                        break;
                    case "x2":
                        NewLocusActivity.DISTANCE1=0.00009;
                        break;
                    case "x4":
                        NewLocusActivity.DISTANCE1=0.0005;
                        break;
                    case "x6":
                        NewLocusActivity.DISTANCE1=0.0009;
                        break;
                    case "x8":
                        NewLocusActivity.DISTANCE1=0.005;
                        break;
                }
            }
        });

        if (mPosition != position) {
            holder.setTextColorRes(R.id.txt, R.color.role_right_gray);
            txt.setTextSize(14);
        }else{
            holder.setTextColorRes(R.id.txt, R.color.black);
            txt.setTextSize(16);
        }
    }
}