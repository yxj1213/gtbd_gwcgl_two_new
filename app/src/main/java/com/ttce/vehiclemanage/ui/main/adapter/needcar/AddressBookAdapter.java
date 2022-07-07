package com.ttce.vehiclemanage.ui.main.adapter.needcar;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.TuJingDiBean;
import com.ttce.vehiclemanage.ui.main.activity.needcar.AddressBookActivity;
import com.ttce.vehiclemanage.ui.main.activity.needcar.CommonRouteActivity;
import com.ttce.vehiclemanage.utils.SizeLabel;
import com.ttce.vehiclemanage.widget.MaxRecyclerView;

import java.util.List;


/**
 * 围栏管理
 * Created by hk on 2019/6/20.
 */

public class AddressBookAdapter extends CommonRecycleViewAdapter<TuJingDiBean> {

    private AddressBookListenter railListenter;
    private String type;

    public AddressBookAdapter(Context context,int layoutId, final List<TuJingDiBean> datas, AddressBookListenter railListenter,String type) {
        super(context, layoutId, datas);
        this.railListenter = railListenter;
        this.type = type;
    }

    @Override
    public void convert(ViewHolderHelper holder, TuJingDiBean newsSummary) {
        setItemValues(holder, newsSummary, getPosition(holder));

    }

    /**
     * 普通样式
     *
     * @param holder
     * @param tuJingDiBean
     * @param position
     */
    private void setItemValues(final ViewHolderHelper holder, final TuJingDiBean tuJingDiBean, final int position) {
        switch (holder.getLayoutId()) {
            case R.layout.address_book_item:
                holder.setText(R.id.txt_address1, tuJingDiBean.getTitle());
                holder.setText(R.id.txt_address2, tuJingDiBean.getTitle2());
                TextView txtmr=holder.getView(R.id.tv_mr);
                if(holder.getAdapterPosition()-2==0){
                    txtmr.setVisibility(View.VISIBLE);
                }else{
                    txtmr.setVisibility(View.GONE);
                }
                LinearLayout lin_bottom=holder.getView(R.id.lin_bottom);
                if(AddressBookActivity.isEdit==true){
                    lin_bottom.setVisibility(View.VISIBLE);
                }else{
                    lin_bottom.setVisibility(View.GONE);
                }
                holder.setText(R.id.rb, "设为默认"+ AddressBookActivity.mtag);
                break;
            case R.layout.common_route_item:
                TextView txtmr2=holder.getView(R.id.tv_mr);
                TextView txt_start=holder.getView(R.id.txt_start);
                TextView txt_end=holder.getView(R.id.txt_end);
                LinearLayout lin_tjd=holder.getView(R.id.lin_tjd);
                TextView txt_tjd_num=holder.getView(R.id.txt_tjd_num);
                TextView txt_zk=holder.getView(R.id.txt_zk);
                MaxRecyclerView recycler_view_tjd=holder.getView(R.id.recycler_view_tjd);
                if(holder.getAdapterPosition()-2==0){
                    txtmr2.setVisibility(View.VISIBLE);
                }else{
                    txtmr2.setVisibility(View.GONE);
                }
                holder.setText(R.id.rb, "设为默认路线");


                String html= "<html><strong><body style='font-size:14px;color:#272727' font-family='PingFang-SC-Medium'>"+mContext.getString(R.string.needcar2_tjd_txt1)+"</strong><br /><font size='12px' color= '#888888' family='PingFangSC-Regular'><size>"+"郭先生"+"</size></font></body></html>";
                String html2= "<html><strong><body style='font-size:14px;color:#272727' font-family='PingFang-SC-Medium'>"+mContext.getString(R.string.needcar2_tjd_txt1)+"</strong><br /><font size='12px' color= '#888888' family='PingFangSC-Regular'><size>"+"郭先生"+"</size></font></body></html>";
                txt_start.setText(Html.fromHtml(html,null,new SizeLabel(12,mContext)));
                txt_end.setText(Html.fromHtml(html2,null,new SizeLabel(12,mContext)));
                tjdData(lin_tjd,txt_tjd_num,recycler_view_tjd);

                txt_zk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(txt_zk.getText().toString().equals("收起")){
                            recycler_view_tjd.setVisibility(View.GONE);
                            txt_zk.setText("展开");
                        }else{
                            recycler_view_tjd.setVisibility(View.VISIBLE);
                            txt_zk.setText("收起");
                        }
                    }
                });
                LinearLayout lin_bottom2=holder.getView(R.id.lin_bottom);
                if(CommonRouteActivity.isEdit==true){
                    lin_bottom2.setVisibility(View.VISIBLE);
                }else{
                    lin_bottom2.setVisibility(View.GONE);
                }
                break;
        }

        holder.setOnClickListener(R.id.img_edit, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                railListenter.editAddressBook(tuJingDiBean);
            }
        });
        holder.setOnClickListener(R.id.img_del, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                railListenter.delAddressBook(tuJingDiBean);
            }
        });


    }
    List<TuJingDiBean> tjdAdapterList;
    private void tjdData(LinearLayout lin_tjd,TextView txt_tjd_num,MaxRecyclerView recycler_view_tjd) {
//        List<TuJingDiBean>  tjdmlist=new ArrayList<>();
//        tjdmlist.add(new TuJingDiBean("兰亭御湖城1","山西省太原市晋源区义井街道",new LatLng(39.912943,116.435314),"","","",false));
//        tjdmlist.add(new TuJingDiBean("兰亭御湖城2","山西省太原市晋源区义井街道",new LatLng(38.059467,114.519696),"","","",false));
//        tjdmlist.add(new TuJingDiBean("兰亭御湖城3","山西省太原市晋源区义井街道",new LatLng(34.378482,108.931517),"","","",false));
//        tjdmlist.add(new TuJingDiBean("兰亭御湖城4","山西省太原市晋源区义井街道",new LatLng(37.899277,112.51899),"","","",false));
//
//        tjdAdapterList=new ArrayList<>();
//        for (int i = 1; i < tjdmlist.size()-1; i++) {
//            tjdAdapterList.add(tjdmlist.get(i));
//        }
//        if(tjdAdapterList.size()==0){
//            lin_tjd.setVisibility(View.GONE);
//        }else{
//            lin_tjd.setVisibility(View.VISIBLE);
//            txt_tjd_num.setText(tjdAdapterList.size()+"个途经点");
//        }
//
//        TuJingDiAdapter tuJingDiAdapter = new TuJingDiAdapter(mContext, R.layout.myorderdetails_tjd_item,tjdAdapterList,"不打卡");
//        tuJingDiAdapter.openLoadAnimation(new ScaleInAnimation());
//        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext) {
//            @Override
//            public boolean canScrollVertically() {
//                // 直接禁止垂直滑动
//                return false;
//            }
//        };
//        recycler_view_tjd.setNestedScrollingEnabled(false);//禁止滑动
//        recycler_view_tjd.setLayoutManager(layoutManager);
//        recycler_view_tjd.setAdapter(tuJingDiAdapter);
    }
    public interface AddressBookListenter {

        void editAddressBook(TuJingDiBean tuJingDiBean);
        void delAddressBook(TuJingDiBean tuJingDiBean);
    }

}