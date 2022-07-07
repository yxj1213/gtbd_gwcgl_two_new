package com.ttce.vehiclemanage.ui.main.adapter.home;

import android.content.Context;
import android.widget.ImageView;

import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.CompanyTypeListBean;

import java.util.List;

public class CompanyTypeListAdapter extends CommonRecycleViewAdapter<CompanyTypeListBean> {

        public static Context mcontext;

        public CompanyTypeListAdapter(Context context, int layoutId, List<CompanyTypeListBean> mDatass) {
            super(context, layoutId, mDatass);
            this.mcontext=context;
        }

        @Override
        public void convert(ViewHolderHelper holder, CompanyTypeListBean applyOrderResponseBean) {
            setItemValues(holder, applyOrderResponseBean, getPosition(holder));
        }

        private void setItemValues(final ViewHolderHelper holder, final CompanyTypeListBean item, final int position) {
            holder.setText(R.id.txt_name, item.getName());
            if(item.getName().contains("政府")){
                holder.setText(R.id.txt_nr, "适用于政府和各类党政机关");
                ImageView imageView=holder.getView(R.id.img_left2);
                imageView.setImageResource(R.mipmap.business_government);
            }else  if(item.getName().contains("企业")){
                ImageView imageView=holder.getView(R.id.img_left2);
                imageView.setImageResource(R.mipmap.icon_select_business);
                holder.setText(R.id.txt_nr, "适用于公司、合伙企业等盈利组织");
            }else  if(item.getName().contains("其他")){
                ImageView imageView=holder.getView(R.id.img_left2);
                imageView.setImageResource(R.mipmap.icon_business_other);
                holder.setText(R.id.txt_nr, "适用于其他组织团体");
            }else  if(item.getName().contains("租赁")){
                ImageView imageView=holder.getView(R.id.img_left2);
                imageView.setImageResource(R.mipmap.icon_business_lease);
                holder.setText(R.id.txt_nr, "适用于包含汽车租赁业务团体");
            }
        }
}
