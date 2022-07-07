package com.ttce.vehiclemanage.ui.main.adapter.workbean.jcxx.ygxx;

import android.content.Context;
import android.widget.TextView;

import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.InformationAuditBean;
import com.ttce.vehiclemanage.utils.OtherUtil;

import java.util.List;

/**
 * 已修改
 * */
public class InformationAuditAdapter extends CommonRecycleViewAdapter<InformationAuditBean> {
    public String type;
    public static Context mcontext;
    public InformationAuditAdapter(Context context, int layoutId, List<InformationAuditBean> mDatass, String type) {
        super(context, layoutId, mDatass);
        this.type=type;
        this.mcontext=context;
    }
    @Override
    public void convert(ViewHolderHelper holder, InformationAuditBean applyOrderResponseBean) {
        setItemValues(holder, applyOrderResponseBean, getPosition(holder));
    }

    private void setItemValues(final ViewHolderHelper holder, final InformationAuditBean item, final int position) {
        holder.setText(R.id.nameTV, item.getName());
        holder.setText(R.id.telTV,  /*OtherUtil.setNumber(*/item.getPhone()/*)*/);
        holder.setText(R.id.idCardTV, OtherUtil.setIdCard(item.getIdCard()));

        TextView  stateTV=holder.getView(R.id.stateTV);
        myOrder(stateTV,type,mContext);

        if(type.equals("1")){
            holder.setText(R.id.timeTV, mContext.getString(R.string.my_order_8)+item.getUpdateTime());
        }else{
            holder.setText(R.id.timeTV, mContext.getString(R.string.my_order_8)+item.getCheckTime());
        }


    }

    public static void myOrder(TextView  stateTV,String type,Context mcontext){

        if(type.equals("1")){
            stateTV.setBackgroundResource(R.drawable.pcz_11);
            stateTV.setTextColor(mcontext.getResources().getColor(R.color.app_main_colors));
            stateTV.setText("待审核");
        }else{
            stateTV.setBackgroundResource(R.drawable.cxz_11);
            stateTV.setTextColor(mcontext.getResources().getColor(R.color.blue));
            stateTV.setText("已审核");
        }
    }
}
