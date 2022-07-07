package com.ttce.vehiclemanage.ui.mine.adapter;

import android.content.Context;

import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ttce.vehiclemanage.R;

import java.util.List;

public class ProblemListAdapter extends CommonRecycleViewAdapter<String> {

    public ProblemListAdapter(Context context, int layoutId, List<String> mDatass) {
        super(context, layoutId, mDatass);
    }

    @Override
    public void convert(ViewHolderHelper holder, String newsSummary) {
        switch (holder.getLayoutId()) {
            case R.layout.adapter_problem_list:
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
    private void setItemValues(final ViewHolderHelper holder, final String newsSummary, final int position) {
        holder.setText(R.id.tv_name, newsSummary);
    }

}