package com.ttce.vehiclemanage.ui.main.adapter.needcar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.CarPlateTypeListBean;
import com.ttce.vehiclemanage.bean.EmptyOrderBean;

import java.util.ArrayList;
import java.util.List;

public class NeedCarTabTypeAdapter extends BaseAdapter {
    Context context;
    String  mtype;
    List<CarPlateTypeListBean> brandsList=new ArrayList<CarPlateTypeListBean>();
    LayoutInflater mInflater;
    private int selectPosition = -1;//用于记录用户选择的变量

    public NeedCarTabTypeAdapter(Context context, List<CarPlateTypeListBean> mList, int selectPosition, String type) {
        this.context = context;
        this.brandsList = mList;
        this.selectPosition = selectPosition;
        this.mtype = type;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if(brandsList==null){
            return 0;
        }
        return brandsList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.fragment_needcartype_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.select = (RadioButton) convertView.findViewById(R.id.id_select);
            viewHolder.tv_mr = (TextView) convertView.findViewById(R.id.tv_mr);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_title.setText(brandsList.get(position).getName());
        if(mtype.equals("选择车辆")&&brandsList.get(position).isSelected()==false){
            viewHolder.select.setVisibility(View.GONE);
            viewHolder.tv_title.setTextColor(context.getResources().getColor(R.color.hint_txt_color));
        }else{
            viewHolder.select.setVisibility(View.VISIBLE);
            viewHolder.tv_title.setTextColor(context.getResources().getColor(R.color.work_item_txt_color));
        }

        if(mtype.equals("用车类型")){
            if(position == selectPosition){
                viewHolder.select.setChecked(true);
                viewHolder.select.setBackgroundResource(R.mipmap.icon_rb_sel);
                viewHolder.tv_mr.setVisibility(View.VISIBLE);
                if(brandsList.get(position).isDefault()==true){
                    viewHolder.tv_mr.setBackgroundResource(R.drawable.blue_bg_4);
                    viewHolder.tv_mr.setText(context.getString(R.string.needcar3_yclx_txt4));
                }
            }else{
                viewHolder.select.setChecked(false);
                viewHolder.select.setBackgroundResource(R.drawable.soild_gray_yuan);
                viewHolder.tv_mr.setVisibility(View.GONE);
            }
        }else{

                if(position == selectPosition){
                    viewHolder.select.setChecked(true);
                    viewHolder.select.setBackgroundResource(R.mipmap.icon_rb_sel);
                }else{
                    viewHolder.select.setChecked(false);
                    viewHolder.select.setBackgroundResource(R.drawable.soild_gray_yuan);
                }

        }

        ViewHolder finalViewHolder = viewHolder;
        viewHolder.tv_mr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!finalViewHolder.tv_mr.getText().equals(context.getString(R.string.needcar3_yclx_txt4))){
                    finalViewHolder.tv_mr.setBackgroundResource(R.drawable.blue_bg_4);
                    finalViewHolder.tv_mr.setText(context.getString(R.string.needcar3_yclx_txt4));
                    mSelectListener.onMRClick(brandsList.get(selectPosition));
                }
            }
        });

        if(mtype.equals("选择车辆")){
            if(brandsList.get(position).isSelected()==true){
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectPosition = position;
                        mSelectListener.onItemClick(brandsList.get(selectPosition),selectPosition);
                        NeedCarTabTypeAdapter.this.notifyDataSetChanged();
                    }
                });
            }
        }else{
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectPosition = position;
                    mSelectListener.onItemClick(brandsList.get(selectPosition),selectPosition);
                    NeedCarTabTypeAdapter.this.notifyDataSetChanged();
                }
            });
        }

        return convertView;
    }
    private OnItemClickListener mSelectListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mSelectListener = listener;
    }
    public class ViewHolder {
        TextView tv_title,tv_mr;
        RadioButton select;
    }
    public interface OnItemClickListener{
        void onItemClick(CarPlateTypeListBean changeCompanyBean,int selectPosition);
        void onMRClick(CarPlateTypeListBean changeCompanyBean);
    }
}

