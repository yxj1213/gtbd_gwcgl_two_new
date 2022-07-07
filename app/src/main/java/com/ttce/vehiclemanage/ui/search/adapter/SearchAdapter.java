package com.ttce.vehiclemanage.ui.search.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.MonitorResponseBean;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context mContext;

    private List<MonitorResponseBean> beanList;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setBeanList(List<MonitorResponseBean> beanList) {
        this.beanList = beanList;
    }

    public SearchAdapter(Context context) {
        mContext = context;
        this.beanList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.adapter_search_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MonitorResponseBean bean = beanList.get(position);
        holder.tv_name.setText(bean.getCarNumber());
        holder.tv_state.setText("(" + bean.getAccFormat() + ")");
        holder.ll_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout ll_layout;
        TextView tv_name;
        TextView tv_state;

        public ViewHolder(View itemView) {
            super(itemView);
            ll_layout = itemView.findViewById(R.id.ll_layout);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_state = itemView.findViewById(R.id.tv_state);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(MonitorResponseBean responseBean);
    }
}
