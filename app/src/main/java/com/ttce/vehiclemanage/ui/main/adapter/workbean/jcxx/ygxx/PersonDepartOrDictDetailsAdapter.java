package com.ttce.vehiclemanage.ui.main.adapter.workbean.jcxx.ygxx;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.Message2Bean;
import com.ttce.vehiclemanage.bean.MessageBean;

import java.util.ArrayList;
import java.util.List;

public class PersonDepartOrDictDetailsAdapter extends BaseAdapter {
    Context context;
    String  mtype;
    List<MessageBean> brandsList=new ArrayList<MessageBean>();
    LayoutInflater mInflater;
    private int selectPosition = -1;//用于记录用户选择的变量

    public PersonDepartOrDictDetailsAdapter(Context context, List<MessageBean> mList, String type) {
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

        viewHolder.tv_title.setText(brandsList.get(position).getText());

        if(position == selectPosition){
            viewHolder.select.setChecked(true);
            viewHolder.select.setBackgroundResource(R.mipmap.icon_rb_sel);
        }else{
            viewHolder.select.setChecked(false);
            viewHolder.select.setBackgroundResource(R.drawable.soild_gray_yuan);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPosition = position;
                mSelectListener.onItemClick(brandsList.get(selectPosition));
                PersonDepartOrDictDetailsAdapter.this.notifyDataSetChanged();
            }
        });
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
        void onItemClick(MessageBean changeCompanyBean);
    }
}

