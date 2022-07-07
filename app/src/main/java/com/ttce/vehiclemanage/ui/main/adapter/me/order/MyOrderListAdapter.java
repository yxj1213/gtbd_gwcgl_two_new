package com.ttce.vehiclemanage.ui.main.adapter.me.order;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.EmptyOrderBean;
import com.ttce.vehiclemanage.utils.BDMapUtils;
import com.ttce.vehiclemanage.utils.SizeLabel;

import java.util.List;

/**
 * 已修改
 * */
public class MyOrderListAdapter extends CommonRecycleViewAdapter<EmptyOrderBean> {
    private DriverJieDanListenter railListenter;
    public String type;
    public static Context mcontext;
    public MyOrderListAdapter(Context context, int layoutId, List<EmptyOrderBean> mDatass, String type) {
        super(context, layoutId, mDatass);
        this.type=type;
        this.mcontext=context;
    }
    public MyOrderListAdapter(Context context, int layoutId, List<EmptyOrderBean> mDatass,String type, DriverJieDanListenter railListenter) {
        super(context, layoutId, mDatass);
        this.type=type;
        this.mcontext=context;
        this.railListenter = railListenter;
    }
    public interface DriverJieDanListenter {

        void jiedan(EmptyOrderBean tuJingDiBean);
    }
    @Override
    public void convert(ViewHolderHelper holder, EmptyOrderBean applyOrderResponseBean) {
        setItemValues(holder, applyOrderResponseBean, getPosition(holder));
    }

    private void setItemValues(final ViewHolderHelper holder, final EmptyOrderBean item, final int position) {
        holder.setText(R.id.nameTV, item.getUseStaffName());
        holder.setText(R.id.telTV, mContext.getString(R.string.strings)+item.getUseStaffPhone());

        TextView  startAdsTV=holder.getView(R.id.startAdsTV);
        TextView  endAdsTV=holder.getView(R.id.endAdsTV);
        TextView  stateTV=holder.getView(R.id.stateTV);

        myOrder(item,stateTV,type,mContext);
        stateTV.setText(item.getEscapeOrderStateFormat());

        String str1start="",str1end="";
        String str2start="",str2end="";
        if(item.getOrder_Mark_List()!=null&&item.getOrder_Mark_List().size()>0){
            holder.setVisible(R.id.tv_zwyclx,false);
            holder.setVisible(R.id.lin_start,true);

            if(item.isHasMark()==false){//订单没有起点终点的情况
                if(item.getOrderState()==180){

                    holder.setVisible(R.id.lin_end,true);
                    initGeoCoder(new LatLng(item.getOrder_Mark_List().get(0).getRealMarkLat(), item.getOrder_Mark_List().get(0).getRealMarkLng()),startAdsTV);
                    initGeoCoder(new LatLng(item.getOrder_Mark_List().get(item.getOrder_Mark_List().size()-1).getRealMarkLat(), item.getOrder_Mark_List().get(item.getOrder_Mark_List().size()-1).getRealMarkLng()),endAdsTV);

                }else{

                    holder.setVisible(R.id.lin_end,false);
                    initGeoCoder(new LatLng(item.getOrder_Mark_List().get(0).getRealMarkLat(), item.getOrder_Mark_List().get(0).getRealMarkLng()),startAdsTV);

                }
            }else{
                holder.setVisible(R.id.lin_end,true);
                str1start=!item.getOrder_Mark_List().get(0).getNeedMarkSimpleAddress().equals("")?item.getOrder_Mark_List().get(0).getNeedMarkSimpleAddress():"";
                str1end=!item.getOrder_Mark_List().get(0).getNeedMarkTitle().equals("")?item.getOrder_Mark_List().get(0).getNeedMarkTitle():"";
                str2start=!item.getOrder_Mark_List().get(item.getOrder_Mark_List().size()-1).getNeedMarkSimpleAddress().equals("")?item.getOrder_Mark_List().get(item.getOrder_Mark_List().size()-1).getNeedMarkSimpleAddress():"";
                str2end=!item.getOrder_Mark_List().get(item.getOrder_Mark_List().size()-1).getNeedMarkTitle().equals("")?item.getOrder_Mark_List().get(item.getOrder_Mark_List().size()-1).getNeedMarkTitle():"";

                String html = null;
                if(!str1start.equals("")&&!str1end.equals("")){
                     html= "<html><strong><body style='font-size:14px;color:#272727' family='PingFang-SC-Medium'>"+str1start+"</strong><br /><font size='12px' color= '#888888' family='PingFangSC-Regular'><size>"+str1end+"</size></font></body></html>";
                }else if(str1start.equals("")&&!str1end.equals("")){
                    html= "<html><strong><body style='font-size:14px;color:#272727' family='PingFang-SC-Medium'>"+str1end+"</strong>"+"</body></html>";
                }else if(!str1start.equals("")&&str1end.equals("")){
                    html= "<html><strong><body style='font-size:14px;color:#272727' family='PingFang-SC-Medium'>"+str1start+"</strong>"+"</body></html>";
                }
                startAdsTV.setText(Html.fromHtml(html,null,new SizeLabel(12,mContext)));



                String html2 = null;
                if(!str2start.equals("")&&!str2end.equals("")){
                    html2 = "<html><strong><body style='font-size:14px;color:#272727' family='PingFang-SC-Medium'>"+str2start+"</strong><br /><font size='12px' color= '#888888' family='PingFangSC-Regular'><size>"+str2end+"</size></font></body></html>";
                }else if(str2start.equals("")&&!str2end.equals("")){
                    html2= "<html><strong><body style='font-size:14px;color:#272727' family='PingFang-SC-Medium'>"+str2end+"</strong>"+"</body></html>";
                }else if(!str2start.equals("")&&str2end.equals("")){
                    html2= "<html><strong><body style='font-size:14px;color:#272727' family='PingFang-SC-Medium'>"+str2start+"</strong>"+"</body></html>";
                }
                endAdsTV.setText(Html.fromHtml(html2,null,new SizeLabel(12,mContext)));
            }
        }else{
            holder.setVisible(R.id.tv_zwyclx,true);
            holder.setVisible(R.id.lin_start,false);
            holder.setVisible(R.id.lin_end,false);
        }


        /**
         *  我的订单 = 1000
         *   审核订单 = 2000
         *   调度订单 = 3000
         *   司机订单 = 4000
         *
         *   如果是订单审批和调度派车、司机接单，需要将创建时间修改为用车时间
         * */
            if(type.equals(mContext.getString(R.string.type1))){
                holder.setText(R.id.timeTV, mContext.getString(R.string.my_order_6)+item.getCreateTimeFormat());
            }else{
                if(item.getWillUseEndDateTimeFormat().equals("")||item.getWillUseEndDateTimeFormat().equals("--")){
                    holder.setText(R.id.timeTV, mContext.getString(R.string.my_order_7)+item.getWillUseStartDateTimeFormat());
                }else{
                    holder.setText(R.id.timeTV, mContext.getString(R.string.my_order_7)+item.getWillUseStartDateTimeFormat()+"-"+item.getWillUseEndDateTimeFormat());
                }
            }

        if(holder.getLayoutId()==R.layout.adapter_driver_order_item){//司机接单
            LinearLayout lin_jd =holder.getView(R.id.lin_jd);
            FrameLayout fra_right =holder.getView(R.id.fra_right);
            TextView tv_jdzt =holder.getView(R.id.tv_jdzt);
            lin_jd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    railListenter.jiedan(item);
                }
            });
            tv_jdzt.setTextColor(mContext.getResources().getColor(R.color.white));
            if(item.getEscapeOrderState()==Integer.valueOf(mContext.getString(R.string.type4_5))){
                lin_jd.setVisibility(View.VISIBLE);
                fra_right.setBackgroundResource(R.mipmap.icon_yellow_qiang);
                tv_jdzt.setText("抢");
            }else if(item.getEscapeOrderState()==Integer.valueOf(mContext.getString(R.string.type4_1))){
                lin_jd.setVisibility(View.VISIBLE);
                fra_right.setBackgroundResource(R.mipmap.icon_blue_pai);
                tv_jdzt.setText("派");
            }else{
                    if(item.getDispatch_Model().getAssignsType()==10){//抢单
                        fra_right.setBackgroundResource(R.mipmap.icon_yellow_qiang);
                        tv_jdzt.setText("抢");
                    }else if(item.getDispatch_Model().getAssignsType()==20){//接单
                        fra_right.setBackgroundResource(R.mipmap.icon_blue_pai);
                        tv_jdzt.setText("派");
                    }
                lin_jd.setVisibility(View.GONE);
            }
        }else{//其他订单

        }
    }
    public void initGeoCoder(LatLng latLng,TextView txt) {
        GeoCoder mCoder = GeoCoder.newInstance();
        mCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    txt.setText("解析地址错误");
                } else {
                    //详细地址
                    String html = "<html><strong><body style='font-size:14px;color:#272727' family='PingFang-SC-Medium'>" + reverseGeoCodeResult.getAddressDetail().province+"-"+ reverseGeoCodeResult.getAddressDetail().city+"-"+reverseGeoCodeResult.getAddressDetail().district+ "</strong><br /><font size='12px' color= '#888888' family='PingFangSC-Regular'><size>" + reverseGeoCodeResult.getAddress() + "</size></font></body></html>";
                    txt.setText(Html.fromHtml(html, null, new SizeLabel(12, mContext)));
                }
            }
        });
        mCoder.reverseGeoCode(new ReverseGeoCodeOption().location(BDMapUtils.convert(latLng)));
    }
    public static void myOrder(EmptyOrderBean item,TextView  stateTV,String type,Context mcontext){
        /**
         *  我的订单 = 1000  派车中 1000   未通过  1100  已取消  1200  出行中  1400  已完成  1500
         *   审核订单 = 2000  待审批 10    已通过 12   已驳回 90
         *   调度订单 = 3000   待派车 20    已派车 22   已驳回 90
         *   司机订单 = 4000
         * */

        if(type.equals(mcontext.getString(R.string.type1))){

            if(String.valueOf(item.getEscapeOrderState()).equals(mcontext.getResources().getString(R.string.type1_1))){
                stateTV.setBackgroundResource(R.drawable.pcz_11);
                stateTV.setTextColor(mcontext.getResources().getColor(R.color.app_main_colors));
            }else if(String.valueOf(item.getEscapeOrderState()).equals(mcontext.getResources().getString(R.string.type1_2))){
                stateTV.setBackgroundResource(R.drawable.cxz_11);
                stateTV.setTextColor(mcontext.getResources().getColor(R.color.blue));
            }else if(String.valueOf(item.getEscapeOrderState()).equals(mcontext.getResources().getString(R.string.type1_3))){
                stateTV.setBackgroundResource(R.drawable.ywc_11);
                stateTV.setTextColor(mcontext.getResources().getColor(R.color.needcar_txt_color1));
            }else if(String.valueOf(item.getEscapeOrderState()).equals(mcontext.getResources().getString(R.string.type1_4))){
                stateTV.setBackgroundResource(R.drawable.wtg_11);
                stateTV.setTextColor(mcontext.getResources().getColor(R.color.f5_color));
            }else if(String.valueOf(item.getEscapeOrderState()).equals(mcontext.getResources().getString(R.string.type1_5))){
                stateTV.setBackgroundResource(R.drawable.yqx_11);
                stateTV.setTextColor(mcontext.getResources().getColor(R.color.color_b8));
            }

        }else if(type.equals(mcontext.getString(R.string.type2))){

            if(String.valueOf(item.getEscapeOrderState()).equals(mcontext.getResources().getString(R.string.type2_1))){
                stateTV.setBackgroundResource(R.drawable.pcz_11);
                stateTV.setTextColor(mcontext.getResources().getColor(R.color.app_main_colors));
            }else if(String.valueOf(item.getEscapeOrderState()).equals(mcontext.getResources().getString(R.string.type2_2))){
                stateTV.setBackgroundResource(R.drawable.cxz_11);
                stateTV.setTextColor(mcontext.getResources().getColor(R.color.blue));
            }else if(String.valueOf(item.getEscapeOrderState()).equals(mcontext.getResources().getString(R.string.type2_3))){
                stateTV.setBackgroundResource(R.drawable.wtg_11);
                stateTV.setTextColor(mcontext.getResources().getColor(R.color.f5_color));
            }

        }else if(type.equals(mcontext.getString(R.string.type3))){

            if(String.valueOf(item.getEscapeOrderState()).equals(mcontext.getResources().getString(R.string.type3_1))){
                stateTV.setBackgroundResource(R.drawable.pcz_11);
                stateTV.setTextColor(mcontext.getResources().getColor(R.color.app_main_colors));
            }else if(String.valueOf(item.getEscapeOrderState()).equals(mcontext.getResources().getString(R.string.type3_2))){
                stateTV.setBackgroundResource(R.drawable.cxz_11);
                stateTV.setTextColor(mcontext.getResources().getColor(R.color.blue));
            }else if(String.valueOf(item.getEscapeOrderState()).equals(mcontext.getResources().getString(R.string.type3_3))){
                stateTV.setBackgroundResource(R.drawable.wtg_11);
                stateTV.setTextColor(mcontext.getResources().getColor(R.color.f5_color));
            }

        }else if(type.equals(mcontext.getString(R.string.type4))){
            //待接单 待抢单
            if(String.valueOf(item.getEscapeOrderState()).equals(mcontext.getResources().getString(R.string.type4_1))||String.valueOf(item.getEscapeOrderState()).equals(mcontext.getResources().getString(R.string.type4_5))){
                stateTV.setBackgroundResource(R.drawable.pcz_11);
                stateTV.setTextColor(mcontext.getResources().getColor(R.color.app_main_colors));
            }else if(String.valueOf(item.getEscapeOrderState()).equals(mcontext.getResources().getString(R.string.type4_2))){//出行中
                stateTV.setBackgroundResource(R.drawable.cxz_11);
                stateTV.setTextColor(mcontext.getResources().getColor(R.color.blue));
            }else if(String.valueOf(item.getEscapeOrderState()).equals(mcontext.getResources().getString(R.string.type4_3))){//已完成
                stateTV.setBackgroundResource(R.drawable.ywc_11);
                stateTV.setTextColor(mcontext.getResources().getColor(R.color.needcar_txt_color1));
            }else if(String.valueOf(item.getEscapeOrderState()).equals(mcontext.getResources().getString(R.string.type4_4))){//已取消
                stateTV.setBackgroundResource(R.drawable.yqx_11);
                stateTV.setTextColor(mcontext.getResources().getColor(R.color.color_b8));
            }
        }
    }
}
