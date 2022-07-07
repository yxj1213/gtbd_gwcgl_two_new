package com.ttce.vehiclemanage.ui.main.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.bumptech.glide.Glide;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.WorkBeanchBean;
import com.ttce.vehiclemanage.utils.DeviceUtils;
import com.ttce.vehiclemanage.utils.glide.GlideUtils;

import java.util.List;

import static com.ttce.vehiclemanage.ui.main.fragment.workbean.ycxx.diaodu.CheckCarFragment.cllxselectPosition;

public class WorkBeanchAdapter extends CommonRecycleViewAdapter<WorkBeanchBean> {

    List<WorkBeanchBean> mlist;
    private DelListener pushclickListener;
    private String mtype;

    //用于工作台
    public WorkBeanchAdapter(Context context, int layoutId,final List<WorkBeanchBean> datas) {
        super(context, layoutId, datas);
        this.mlist=datas;
    }

    //打卡、带队组长、随车人员需要有返回信息的
    public WorkBeanchAdapter(Context context, int layoutId,final List<WorkBeanchBean> datas,String type,DelListener pushclickListener1) {
        super(context, layoutId, datas);
        this.mlist=datas;
        this.mtype=type;
        pushclickListener = pushclickListener1;
    }

    //各种订单详情不需要返回信息的
    public WorkBeanchAdapter(Context context, int layoutId,final List<WorkBeanchBean> datas,String type) {
        super(context, layoutId, datas);
        this.mlist=datas;
        this.mtype=type;
    }

    @Override
    public void convert(ViewHolderHelper holder, WorkBeanchBean workBeanchBean) {
        setItemValues(holder, workBeanchBean);
    }

    /**
     * 普通样式
     *
     * @param holder
     * @param workBeanchBean
     */
    private void setItemValues(final ViewHolderHelper holder, final WorkBeanchBean workBeanchBean) {
        switch(holder.getLayoutId()){
            case R.layout.home_todolist_item://工作台
                if(!workBeanchBean.getTitleid().equals("0")){
                    holder.setVisible(R.id.txt_num,true);
                    holder.setText(R.id.txt_num,workBeanchBean.getTitleid());
                }else{
                    holder.setVisible(R.id.txt_num,false);
                }

                if(workBeanchBean.getTitle().equals("2000")){
                    holder.setBackgroundRes(R.id.icon,R.mipmap.icon_work_one);
                    holder.setText(R.id.tv_title,mContext.getResources().getString(R.string.me_txt9));
                    holder.setText(R.id.tv_title2,"用车订单审批提醒");
                }else  if(workBeanchBean.getTitle().equals("3000")){
                    holder.setBackgroundRes(R.id.icon,R.mipmap.icon_work_two);
                    holder.setText(R.id.tv_title,mContext.getResources().getString(R.string.me_txt8));
                    holder.setText(R.id.tv_title2,"调派车辆提醒");
                }else  if(workBeanchBean.getTitle().equals("4000")){
                    holder.setBackgroundRes(R.id.icon,R.mipmap.icon_work_three);
                    holder.setText(R.id.tv_title,mContext.getResources().getString(R.string.me_txt13));
                    holder.setText(R.id.tv_title2,"接单/抢单提醒");
                }else  if(workBeanchBean.getTitle().equals("5000")){
                    holder.setBackgroundRes(R.id.icon,R.mipmap.icon_work_four);
                    holder.setText(R.id.tv_title,mContext.getResources().getString(R.string.need_car_0_1));
                    holder.setText(R.id.tv_title2,"用车费用报销结算提醒");
                }else  if(workBeanchBean.getTitle().equals("6000")){
                    holder.setBackgroundRes(R.id.icon,R.mipmap.icon_work_four);
                    holder.setText(R.id.tv_title,mContext.getResources().getString(R.string.need_car_1_1));
                    holder.setText(R.id.tv_title2,"员工基础信息审核提醒");
                }

                holder.setText(R.id.tv_time, workBeanchBean.getTitle2());
                break;
            case R.layout.fragment_work_beanch_item://工作台
                if(!workBeanchBean.getTitleid().equals("0")&&!workBeanchBean.getTitleid().equals("")){
                    holder.setVisible(R.id.view_circle,true);
                }else{
                    holder.setVisible(R.id.view_circle,false);
                }
                holder.setImageResource(R.id.icon,workBeanchBean.getImg());
                holder.setText(R.id.tv_name, workBeanchBean.getTitle());
                break;
            case R.layout.pic_item://图片
                ImageView imageView=holder.getView(R.id.img);
                if(workBeanchBean.getTitle().equals("添加")){
                    holder.setVisible(R.id.rel_add,true);
                    holder.setVisible(R.id.rel_pic,false);
                }else{
                    holder.setVisible(R.id.rel_add,false);
                    holder.setVisible(R.id.rel_pic,true);
                    GlideUtils.displayAdd(mContext,imageView,workBeanchBean.getTitle());
                }
                if(mtype.equals("新增打卡")){
                    holder.setVisible(R.id.img_del,true);
                }else{
                    holder.setVisible(R.id.img_del,false);
                }
                holder.getView(R.id.img_del).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pushclickListener.del(mtype,holder.getLayoutPosition()-2);
                    }
                });
                break;
            case R.layout.fragment_my_need_ryxx_item://我要用车-添加人员
                if(!mtype.contains("详情")){
                    if(workBeanchBean.getTitle().equals("添加")){
                        holder.setVisible(R.id.tv_add,true);
                        holder.setVisible(R.id.tv_name,false);
                    }else{
                        holder.setVisible(R.id.tv_add,false);
                        holder.setVisible(R.id.tv_name,true);
                        holder.setText(R.id.tv_name, workBeanchBean.getTitle());
                    }

                    holder.getView(R.id.tv_name).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            pushclickListener.del(mtype,holder.getLayoutPosition()-2);
                        }
                    });
                }else{
                    TextView txtname=holder.getView(R.id.tv_name);
                    txtname.setPadding(DeviceUtils.dip2px(mContext,10),DeviceUtils.dip2px(mContext,3),DeviceUtils.dip2px(mContext,10),DeviceUtils.dip2px(mContext,4));
                    txtname.setCompoundDrawables(null, null, null, null);
                    txtname.setText(mlist.get(holder.getLayoutPosition()-2).getTitle());
                }
                break;
            case R.layout.fragment_my_need_ryxx2_item:
                TextView textView=holder.getView(R.id.tv_name);
                if((holder.getLayoutPosition())== cllxselectPosition){
                    textView.setBackgroundResource(R.drawable.blue_15_bg);
                    textView.setTextColor(mContext.getResources().getColor(R.color.white));
                }else{
                    textView.setBackgroundResource(R.drawable.gray_15_bg);
                    textView.setTextColor(mContext.getResources().getColor(R.color.needcar_txt_color4));
                }
                textView.setText(workBeanchBean.getTitle());
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pushclickListener.del(mtype,holder.getLayoutPosition());

                    }
                });
                break;
        }
    }
    public interface DelListener {
        void del(String mtype,int pos);
    }
}