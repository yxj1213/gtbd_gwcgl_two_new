package com.ttce.vehiclemanage.ui.main.activity.needcar;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.baidu.mapapi.model.LatLng;
import com.google.gson.Gson;
import com.jaydenxiao.common.base.BaseActivity;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.EmptyOrderBean;
import com.ttce.vehiclemanage.bean.TuJingDiBean;
import com.ttce.vehiclemanage.ui.main.adapter.needcar.TuJingDiAdapter;
import com.ttce.vehiclemanage.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 已修改
 * */
public class AddRouteActivity extends BaseActivity implements TuJingDiAdapter.PushclickListener{

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.tv_sure)
    TextView tv_sure;
    @Bind(R.id.tv_cancle)
    TextView tv_cancle;

    @Bind(R.id.needcar2_txt2)
    TextView needcar2_txt2;
    @Bind(R.id.tv_cylx)
    TextView tv_cylx;
    @Bind(R.id.recycler_view)
    RecyclerView recycler_view;
    @Bind(R.id.img_swap)
    ImageView img_swap;
    @Bind(R.id.needcar2_txt)
    TextView needcar2_txt;

    TuJingDiAdapter tuJingDiAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_route;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        tv_cancle.setVisibility(View.GONE);
        tv_cylx.setVisibility(View.GONE);
        tv_sure.setText(getResources().getString(R.string.ditu10));
        titleBarTitle.setText(getResources().getString(R.string.address_book_txt6));


        tjdData();
    }

    List<TuJingDiBean> tjdmlist;
    private void tjdData() {

        tjdmlist=new ArrayList<>();
        tjdmlist.add(new TuJingDiBean("",null));
        tjdmlist.add(new TuJingDiBean("",null));
        tjdmlist.add(new TuJingDiBean("",null));
        tjdmlist.add(new TuJingDiBean("",null));
        tjdmlist.add(new TuJingDiBean("",null));
        tjdmlist.add(new TuJingDiBean("",null));
        tjdmlist.add(new TuJingDiBean("",null));
        tjdmlist.add(new TuJingDiBean("",null));
        tjdmlist.add(new TuJingDiBean("",null));
        tjdmlist.add(new TuJingDiBean("",null));
        tjdmlist.add(new TuJingDiBean("",null));
        tjdmlist.add(new TuJingDiBean("",null));
        tjdmlist.add(new TuJingDiBean("",null));
        tjdmlist.add(new TuJingDiBean("",null));
        tjdmlist.add(new TuJingDiBean("",null));
        tjdmlist.add(new TuJingDiBean("",null));
        tjdmlist.add(new TuJingDiBean("",null));
        tjdmlist.add(new TuJingDiBean("",null));
        tjdmlist.add(new TuJingDiBean("",null));
        tjdmlist.add(new TuJingDiBean("",null));
        tjdmlist.add(new TuJingDiBean("",null));
        tjdmlist.add(new TuJingDiBean("",null));
        tjdmlist.add(new TuJingDiBean("",null));
        tjdmlist.add(new TuJingDiBean("",null));
        tjdmlist.add(new TuJingDiBean("",null));
        tjdmlist.add(new TuJingDiBean("",null));
        tjdmlist.add(new TuJingDiBean("",null));
//        tuJingDiAdapter = new TuJingDiAdapter(this, R.layout.fragment_my_need_tjd_item,tjdmlist,this,true);
//        tuJingDiAdapter.openLoadAnimation(new ScaleInAnimation());
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
//            @Override
//            public boolean canScrollVertically() {
//                // 直接禁止垂直滑动
//                return false;
//            }
//        };
//        recycler_view.setNestedScrollingEnabled(false);//禁止滑动
//        recycler_view.setLayoutManager(new LinearLayoutManager(this));
//        recycler_view.setAdapter(tuJingDiAdapter);
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new MyItemTouchHelper(this,tjdmlist,tuJingDiAdapter));
//        itemTouchHelper.attachToRecyclerView(recycler_view);
    }

    @OnClick({R.id.title_bar_back,R.id.tv_sure,R.id.needcar2_txt2,R.id.needcar2_txt,R.id.img_swap})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                finish();
                break;
            case R.id.tv_sure:
                finish();
                break;
            case R.id.needcar2_txt2:
                break;
            case R.id.needcar2_txt:
                img_swap.setVisibility(View.GONE);
                ToastUtil.showToast(tjdmlist.size()+"--点");
                if(tjdmlist.size()<17){
                    tjdmlist.add(tjdmlist.size()-1,new TuJingDiBean("",null));
                    tuJingDiAdapter.notifyDataSetChanged();
                    if(tjdmlist.size()>=2){
                        needcar2_txt.setText("添加途经点("+(tjdmlist.size()-2)+"/15)");
                    }
                }else{
                    ToastUtil.showToast("最多只能添加15个途径地。");
                }
                break;
            case R.id.img_swap:
                String title0=tjdmlist.get(0).getTitle();
                LatLng latlng0=tjdmlist.get(0).getLatLng();
                String title1=tjdmlist.get(1).getTitle();
                LatLng latlng1=tjdmlist.get(1).getLatLng();
                tjdmlist.set(0,new TuJingDiBean(title1,latlng1));
                tjdmlist.set(1,new TuJingDiBean(title0,latlng0));
                tuJingDiAdapter.notifyDataSetChanged();
                break;
        }
    }

    boolean isContains=false;
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMessageEvent(TuJingDiBean tuJingDiBean) {
        for (int i=0;i<tjdmlist.size();i++){
            if(i==select){
                isContains=true;
                tjdmlist.set(i,new TuJingDiBean(tuJingDiBean.getTitle(),tuJingDiBean.getTitle2(),tuJingDiBean.getLatLng(),tuJingDiBean.getDzpz(),tuJingDiBean.getXm(),tuJingDiBean.getLxdh()));
            }
        }

        if(isContains==false){
            tjdmlist.add(tuJingDiBean);
        }
        tuJingDiAdapter.notifyDataSetChanged();

        if(tjdmlist.size()>2){
            img_swap.setVisibility(View.GONE);
        }
    }

    @Override
    public void del(int pos) {
        if(tjdmlist.size()>2){
            tjdmlist.remove(pos);
            tuJingDiAdapter.notifyDataSetChanged();
            needcar2_txt.setText("添加途经点("+(tjdmlist.size()-2)+"/15)");
        }
    }

    @Override
    public void item(EmptyOrderBean.OrderMarkListBean item, int size, int pos) {
        select=pos;
        String nr="";
        if(pos==0){
            nr="起点";
        }else if(pos==tjdmlist.size()-1){
            nr="终点";
        }else{
            nr="途径地";
        }
        DiTuCheckAddressActivity.goToPage(this,nr,new Gson().toJson(item),"添加路线");
    }

    public int select;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public static void goToPage(Activity activity) {
        Intent intent = new Intent(activity, AddRouteActivity.class);
        activity.startActivity(intent);
    }
}