package com.ttce.vehiclemanage.ui.main.adapter.needcar;

import android.content.Context;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.ItemTouchHelper;

import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.EmptyOrderBean;
import com.ttce.vehiclemanage.utils.DeviceUtils;
import com.ttce.vehiclemanage.utils.SizeLabel;

import java.util.List;


public class TuJingDiAdapter extends CommonRecycleViewAdapter<EmptyOrderBean.OrderMarkListBean> {

    private PushclickListener pushclickListener;
    List<EmptyOrderBean.OrderMarkListBean> mlist;
    String mtype = "";
    boolean misXuanZe,misShow;

//    public boolean isMisXuanZe() {
//        return misXuanZe;
//    }
//
//    public void setMisXuanZe(boolean misXuanZe) {
//        this.misXuanZe = misXuanZe;
//    }

    public TuJingDiAdapter(Context context, int layoutId, List<EmptyOrderBean.OrderMarkListBean> list, PushclickListener pushclickListener1, boolean isXuanZe, ItemTouchHelper itemHelper) {
        super(context, layoutId, list);
        pushclickListener = pushclickListener1;
        mlist = list;
        misXuanZe = isXuanZe;
    }
    public TuJingDiAdapter(Context context, int layoutId, List<EmptyOrderBean.OrderMarkListBean> list, PushclickListener pushclickListener1, boolean isXuanZe, boolean isshow) {
        super(context, layoutId, list);
        pushclickListener = pushclickListener1;
        mlist = list;
        misXuanZe = isXuanZe;
        misShow = isshow;
    }
    public TuJingDiAdapter(Context context, int layoutId, List<EmptyOrderBean.OrderMarkListBean> list, String type) {
        super(context, layoutId, list);
        mlist = list;
        mtype = type;
    }

    @Override
    public void convert(ViewHolderHelper holder, EmptyOrderBean.OrderMarkListBean checkListBean) {
        setItemValues(holder, checkListBean, getPosition(holder));
    }
    private boolean isAllItemEnable = true;
    public void disableAllItemChoser() {
        isAllItemEnable = false;
        notifyDataSetChanged();
    }
    public void enableItemChoser() {
        isAllItemEnable = true;
        notifyDataSetChanged();
    }
    @Override
    public boolean isEnabled(int position) {
        return isAllItemEnable;
    }

    private void setItemValues(final ViewHolderHelper holder, final EmptyOrderBean.OrderMarkListBean item, final int position) {
        switch (holder.getLayoutId()) {
            case R.layout.fragment_my_need_tjd_item:
                AutoCompleteTextView editText = holder.getView(R.id.edt_1);
                TextView tv_1 = holder.getView(R.id.tv_1);
                RelativeLayout rel = holder.getView(R.id.rel);
                ImageView img_del = holder.getView(R.id.img_del);

                // 控制是否置灰
                if (!isAllItemEnable) {
                    editText.setEnabled(false);
                    editText.setEnabled(false);
                    editText.setTextColor(mContext.getResources().getColor(R.color.text_grey_color));
                    editText.setHintTextColor(mContext.getResources().getColor(R.color.text_grey_color));
                    img_del.setVisibility(View.INVISIBLE);
                } else {
                    editText.setEnabled(true);
                    editText.setEnabled(true);
                    editText.setTextColor(mContext.getResources().getColor(R.color.work_item_txt_color));
                    editText.setHintTextColor(mContext.getResources().getColor(R.color.hint_txt_color));
                    if (mlist.size() > 2) {
                        img_del.setVisibility(View.VISIBLE);
                    } else {
                        img_del.setVisibility(View.INVISIBLE);
                    }
                }



                if (position == 0) {
                    tv_1.setText("起");
                    tv_1.setBackgroundResource(R.drawable.blue_yuan);

                    if(misXuanZe==true){
                        setedt(item.getNeedMarkSimpleAddress(), editText, "请输入起点...", rel, item.getNeedMarkTitle());
                    }else{
                        setedt(item.getNeedMarkTitle(), editText, "请输入起点...", rel, null);
                    }
                } else if (position == mlist.size() - 1) {
                    tv_1.setText("终");
                    tv_1.setBackgroundResource(R.drawable.orange_yuan);

                    if(misXuanZe==true){
                        setedt(item.getNeedMarkSimpleAddress(), editText, "请输入终点...", rel, item.getNeedMarkTitle());
                    }else{
                        setedt(item.getNeedMarkTitle(), editText, "请输入终点...", rel,null);
                    }
                } else {
                    tv_1.setText("经");
                    tv_1.setBackgroundResource(R.drawable.gray_yuan);

                    if(misXuanZe==true){
                        setedt(item.getNeedMarkSimpleAddress(), editText, "请输入途径点...", rel, item.getNeedMarkTitle());
                    }else{
                        setedt(item.getNeedMarkTitle(), editText, "请输入途径点...", rel, null);
                    }
                }

                holder.setOnClickListener(R.id.img_del, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pushclickListener.del(position);
                    }
                });
                editText.setLongClickable(false);
                if (misXuanZe == true) {
                    editText.setFocusableInTouchMode(false);
                    editText.setFocusable(false);
                    editText.setClickable(true);
                    editText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            pushclickListener.item(item, mlist.size(), holder.getAdapterPosition() - 2);
                        }
                    });
                } else {
                    holder.setIsRecyclable(false);
                    editText.setFocusableInTouchMode(true);
                    editText.setFocusable(true);
                    editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if(hasFocus){
                                editText.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                                        item.setNeedMarkTitle(editText.getText().toString().trim());
                                        pushclickListener.item(item,mlist.size(),holder.getAdapterPosition()-2);
                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {
                                        //do something
                                        editText.getPaint().setFakeBoldText(true);
                                    }
                                });
                            }
                        }
                    });
                }

                        break;
                        case R.layout.myorderdetails_tjd_item:
                            TextView txt1 = holder.getView(R.id.txt1);
                            TextView txt2 = holder.getView(R.id.txt2);
                            TextView txt3 = holder.getView(R.id.txt3);
                            TextView txt4 = holder.getView(R.id.txt4);
                            TextView tv_dk = holder.getView(R.id.tv_dk);

                            if (misXuanZe==true) {//该笔订单是地图选址的
                                if(item.getMarkType()==20){//新增打卡
                                    holder.setVisible(R.id.txt1,false);
                                }else{
                                    if(!item.getNeedMarkSimpleAddress().equals("")){
                                        holder.setVisible(R.id.txt1,true);
                                        holder.setText(R.id.txt1,item.getNeedMarkSimpleAddress());
                                    }else{
                                        holder.setVisible(R.id.txt1,false);
                                    }
                                }

                                if(item.getMarkType()==20) {//新增打卡
                                    if(!item.getRealMarkFullAddress().equals("")){
                                        holder.setVisible(R.id.txt2,true);
                                        holder.setText(R.id.txt2,item.getRealMarkFullAddress());
                                    }else{
                                        holder.setVisible(R.id.txt2,false);
                                    }
                                }else{
                                    if(!item.getNeedMarkFullAddress().equals("")){
                                        holder.setVisible(R.id.txt2,true);
                                        holder.setText(R.id.txt2,item.getNeedMarkFullAddress());
                                    }else{
                                        holder.setVisible(R.id.txt2,false);
                                    }
                                }

                                if(item.getMarkType()==20) {//新增打卡
                                    if(!item.getNeedMarkTitle().equals("")){
                                        holder.setVisible(R.id.txt3,true);
                                        holder.setText(R.id.txt3,item.getNeedMarkTitle());
                                    }else{
                                        holder.setVisible(R.id.txt3,false);
                                    }
                                }else{
                                    if(!item.getNeedMarkTitle().equals("")){
                                        holder.setVisible(R.id.txt3,true);
                                        holder.setText(R.id.txt3,item.getNeedMarkTitle());
                                    }else{
                                        holder.setVisible(R.id.txt3,false);
                                    }
                                }

                                if (!item.getLinkPhone().equals("") || !item.getLinkName().equals("")) {
                                    holder.setVisible(R.id.txt4,true);
                                    txt4.setText(item.getLinkName() + item.getLinkPhone());
                                } else {
                                    holder.setVisible(R.id.txt4,false);
                                }
                            } else if (misXuanZe==false) {//该笔订单是输入的

                                if(item.getMarkType()==30) {//新增途经点
                                    if(!item.getNeedMarkSimpleAddress().equals("")){
                                        holder.setVisible(R.id.txt1,true);
                                        holder.setText(R.id.txt1,item.getNeedMarkSimpleAddress());
                                    }else{
                                        holder.setVisible(R.id.txt1,false);
                                    }

                                    if(!item.getNeedMarkFullAddress().equals("")){
                                        holder.setVisible(R.id.txt2,true);
                                        holder.setText(R.id.txt2,item.getNeedMarkFullAddress());
                                    }else{
                                        holder.setVisible(R.id.txt2,false);
                                    }

                                    if(!item.getNeedMarkTitle().equals("")){
                                        holder.setVisible(R.id.txt3,true);
                                        holder.setText(R.id.txt3,item.getNeedMarkTitle());
                                    }else{
                                        holder.setVisible(R.id.txt3,false);
                                    }

                                    if (!item.getLinkPhone().equals("") || !item.getLinkName().equals("")) {
                                        holder.setVisible(R.id.txt4,true);
                                        txt4.setText(item.getLinkName() + item.getLinkPhone());
                                    } else {
                                        holder.setVisible(R.id.txt4,false);
                                    }
                                }else if(item.getMarkType()==20) {//新增打卡
//                                    holder.setText(R.id.txt1,"");
                                    holder.setVisible(R.id.txt1,false);

                                    if(!item.getRealMarkFullAddress().equals("")){
                                        holder.setVisible(R.id.txt2,true);
                                        holder.setText(R.id.txt2,item.getRealMarkFullAddress());
                                    }else{
                                        holder.setVisible(R.id.txt2,false);
                                    }

                                    if(!item.getNeedMarkTitle().equals("")){
                                        holder.setVisible(R.id.txt3,true);
                                        holder.setText(R.id.txt3,item.getNeedMarkTitle());
                                    }else{
                                        holder.setVisible(R.id.txt3,false);
                                    }
                                    holder.setVisible(R.id.txt4,false);
                                }else{
                                    if(!item.getNeedMarkSimpleAddress().equals("")){
                                        holder.setVisible(R.id.txt1,true);
                                        holder.setText(R.id.txt1,item.getNeedMarkSimpleAddress());
                                    }else{
                                        holder.setVisible(R.id.txt1,false);
                                    }

                                    if(!item.getNeedMarkFullAddress().equals("")){
                                        holder.setVisible(R.id.txt2,true);
                                        holder.setText(R.id.txt2,item.getNeedMarkFullAddress());
                                    }else{
                                        holder.setVisible(R.id.txt2,false);
                                    }

                                    if(!item.getNeedMarkTitle().equals("")){
                                        holder.setVisible(R.id.txt3,true);
                                        holder.setText(R.id.txt3,item.getNeedMarkTitle());
                                    }else{
                                        holder.setVisible(R.id.txt3,false);
                                    }
                                    if (!item.getLinkPhone().equals("") || !item.getLinkName().equals("")) {
                                        holder.setVisible(R.id.txt4,true);
                                        txt4.setText(item.getLinkName() + item.getLinkPhone());
                                    } else {
                                        holder.setVisible(R.id.txt4,false);
                                    }
                                }
                            }


                            if (mtype.equals("不打卡")) {//订单审批、调度派车、司机接单、地址簿不需要显示打卡功能
                                tv_dk.setVisibility(View.GONE);
                            }

                            if (item.isMark() == true) {//我的订单需要打卡功能
                                tv_dk.setText("已打卡");
                                tv_dk.setVisibility(View.VISIBLE);
                                tv_dk.setTextColor(mContext.getResources().getColor(R.color.app_main_colors));
                                tv_dk.setBackgroundResource(R.drawable.blue_14_bg);
                            } else {
                                if(misShow==true){
                                    if(misXuanZe==true){
                                        if(item.isMarkCircleRadius()){
                                            tv_dk.setVisibility(View.VISIBLE);
                                            tv_dk.setText("打卡");
                                            tv_dk.setTextColor(mContext.getResources().getColor(R.color.app_main_colors));
                                            tv_dk.setBackgroundResource(R.drawable.border_blue_bg_14);
                                        }else{
                                            tv_dk.setVisibility(View.GONE);
                                        }
                                    }else{
                                        tv_dk.setVisibility(View.VISIBLE);
                                        tv_dk.setText("打卡");
                                        tv_dk.setTextColor(mContext.getResources().getColor(R.color.app_main_colors));
                                        tv_dk.setBackgroundResource(R.drawable.border_blue_bg_14);
                                    }
                                }
                            }

                            holder.setOnClickListener(R.id.tv_dk, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pushclickListener.item(item, mlist.size(), holder.getAdapterPosition() - 2);
                                }
                            });
                            break;
                    }

                }
                public void setedt (String title, TextView editText, String hinttitle, RelativeLayout rel, String dzpz){
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                if (title == null || title.equals("")) {
                    editText.setHint(hinttitle);
                    editText.setText(null);
                    editText.setBackground(mContext.getResources().getDrawable(R.drawable.gray_6_bg));
                    lp.setMargins(DeviceUtils.dip2px(mContext, 0), DeviceUtils.dip2px(mContext, 7), DeviceUtils.dip2px(mContext, 0), DeviceUtils.dip2px(mContext, 7));
                } else {
                        String dzpzs = "";
                        if (dzpz != null) {
                            dzpzs = dzpz;
                        }
                        String html = "<html><strong><body style='font-size:16px;color:#272727' font-family='PingFang-SC-Medium'>" + title + "<br /></strong><font size='12px' color= '#888888'  family='PingFangSC-Regular'><size>" + dzpzs + "</size></font></body></html>";
                        editText.setText(Html.fromHtml(html, null, new SizeLabel(12, mContext)));

                    editText.setBackground(null);
                    lp.setMargins(DeviceUtils.dip2px(mContext, 0), DeviceUtils.dip2px(mContext, -10), DeviceUtils.dip2px(mContext, 0), DeviceUtils.dip2px(mContext, -10));
                }
                rel.setLayoutParams(lp);
            }
            public interface PushclickListener {
                void del(int pos);

                void item(EmptyOrderBean.OrderMarkListBean item, int size, int pos);
            }
        }
