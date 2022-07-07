package com.ttce.vehiclemanage.widget;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayoutManager;

public class MyFlexboxLayoutManager extends FlexboxLayoutManager {

    public MyFlexboxLayoutManager(Context context) {
        super(context);
    }

    public MyFlexboxLayoutManager(Context context, int flexDirection) {
        super(context, flexDirection);
    }

    public MyFlexboxLayoutManager(Context context, int flexDirection, int flexWrap) {
        super(context, flexDirection, flexWrap);
    }


    /**
     * 将LayoutParams转换成新的FlexboxLayoutManager.LayoutParams
     */
    @Override
    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
        if (lp instanceof RecyclerView.LayoutParams) {
            return new FlexboxLayoutManager.LayoutParams((RecyclerView.LayoutParams) lp);
        } else if (lp instanceof ViewGroup.MarginLayoutParams) {
            return new FlexboxLayoutManager.LayoutParams((ViewGroup.MarginLayoutParams) lp);
        } else {
            return new FlexboxLayoutManager.LayoutParams(lp);
        }
    }
}
