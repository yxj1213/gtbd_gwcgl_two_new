package com.ttce.vehiclemanage.ui.main.fragment.needcar;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonutils.DisplayUtil;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.CarPlateTypeListBean;
import com.ttce.vehiclemanage.bean.EmptyOrderBean;
import com.ttce.vehiclemanage.bean.EventBusMessageBean;
import com.ttce.vehiclemanage.ui.main.adapter.needcar.NeedCarTabTypeAdapter;
import com.ttce.vehiclemanage.ui.main.popwindow.OrderSelectBottomControlPanel;
import com.ttce.vehiclemanage.utils.glide.GlideUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MyNeedCarCardFragment extends BaseFragment implements OrderSelectBottomControlPanel.ControlPanelListener {
    private String mbean;
    private LinearLayout mlinearLayout;
    private EmptyOrderBean.CarStyleListBean mcarStyleListBean;

    @Bind(R.id.hplx_txt)
    TextView hplx_txt;
    @Bind(R.id.zw_txt)
    TextView zw_txt;
    @Bind(R.id.tv_zws)
    TextView tv_zws;
    @Bind(R.id.tv_hp)
    TextView tv_hp;
    @Bind(R.id.img_car)
    ImageView img_car;

    public static MyNeedCarCardFragment getInstance(String bean,EmptyOrderBean.CarStyleListBean carStyleListBean,LinearLayout linearLayout) {
        MyNeedCarCardFragment sf = new MyNeedCarCardFragment();
        sf.mbean = bean;
        sf.mcarStyleListBean = carStyleListBean;
        sf.mlinearLayout = linearLayout;
        return sf;
    }

    String yxc="";
    String yxhplx="";
    String zws="";
    @Override
    protected void loadData() {



    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        try {
            if (getUserVisibleHint()) {
                //默认的意向车型、意向号牌类型、意向座位数
                emptyOrderBean=new Gson().fromJson(mbean, EmptyOrderBean.class);
                zws=mcarStyleListBean.getMaxSeatNum()+"";
                yxc=mcarStyleListBean.getCarFlow_CarStyleId();
                yxhplx=emptyOrderBean.getCarPlateType_List().get(0).getCarFlow_CarPlateTypeId();
                EventBus.getDefault().postSticky(new EventBusMessageBean("意向车",yxc,yxhplx,zws));
                //界面可见时
                if(hplx_txt!=null){
                    hplx_txt.setText(emptyOrderBean.getCarPlateType_List().get(0).getName());
                }
                if(zw_txt!=null){
                    zw_txt.setText(mcarStyleListBean.getMinSeatNum()+"-"+mcarStyleListBean.getMaxSeatNum()+"座");
                }
                if(img_car!=null){
                    GlideUtils.displayAdd(getActivity(),img_car,mcarStyleListBean.getImageUrl());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_my_need_car_tabitem;
    }

    @Override
    public void initPresenter() {

    }

    public void popwindow(String type,TextView txt){
        // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
        View popupWindow_view = LinearLayout.inflate(getActivity(), R.layout.needcar_order_car, null);
        popupWindow = new PopupWindow(popupWindow_view,DisplayUtil.dip2px(200), LinearLayout.LayoutParams.WRAP_CONTENT, true);
        // 获取自定义布局文件activity_popupwindow_left.xml的视图
        ListView listview = popupWindow_view.findViewById(R.id.recycler_view);
        if(type.equals("号牌类型")){
            hPlxData(listview);
        }else if(type.equals("座位数")){
            zwsData(listview);
        }
        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        //软键盘不会挡着popupwindow
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.showAsDropDown(txt,-50, 0);
    }
    PopupWindow popupWindow;
    @OnClick({R.id.hplx_txt,R.id.zw_txt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hplx_txt:
//                setMapBottom("号牌类型");
                popwindow("号牌类型",tv_hp);
                break;
            case R.id.zw_txt:
//                setMapBottom("座位数");
                popwindow("座位数",tv_zws);
                break;
        }
    }
    public static int mhPlxselectPosition = 0;//用于记录用户选择的变量
    public CarPlateTypeListBean mhPlxselectBrand ;//用于记录用户选择的对象
    private int mzwsselectPosition =0;//用于记录用户选择的变量
    private CarPlateTypeListBean mzwsselectBrand ;//用于记录用户选择的对象
    private void zwsData(ListView listview) {
        List<CarPlateTypeListBean> mlist=new ArrayList<>();
        for (int i=mcarStyleListBean.getMinSeatNum();i<=mcarStyleListBean.getMaxSeatNum();i++){
            mlist.add(new CarPlateTypeListBean(String.valueOf(i)));
        }
        mzwsselectBrand=mlist.get(0);
        mzwsselectPosition=0;


        NeedCarTabTypeAdapter myAdapter = new NeedCarTabTypeAdapter(getActivity(),mlist,0,"座位数");
        listview.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new NeedCarTabTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CarPlateTypeListBean changeCompanyBean, int selectPosition) {
                mzwsselectBrand=changeCompanyBean;
                mzwsselectPosition=selectPosition;
                popupWindow.dismiss();
                zws=mzwsselectBrand.getName()+"";
                zw_txt.setText(mzwsselectBrand.getName()+"座");
                EventBus.getDefault().postSticky(new EventBusMessageBean("意向车",yxc,yxhplx,zws));
            }

            @Override
            public void onMRClick(CarPlateTypeListBean changeCompanyBean) {
            }
        });
        setListViewHeight(listview,5);
    }
    private void setListViewHeight(ListView listView,int count){
        ListAdapter listAdapter = listView.getAdapter(); //得到ListView 添加的适配器
        if(listAdapter == null){
            return;
        }

        View itemView = listAdapter.getView(0, null, listView); //获取其中的一项
        //进行这一项的测量，为什么加这一步，具体分析可以参考 https://www.jianshu.com/p/dbd6afb2c890这篇文章
        itemView.measure(0,0);
        int itemHeight = itemView.getMeasuredHeight(); //一项的高度
        int itemCount = listAdapter.getCount();//得到总的项数
        LinearLayout.LayoutParams layoutParams = null; //进行布局参数的设置
        if(itemCount < count){
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT ,itemHeight*itemCount);
        }else if(itemCount >= count){
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT ,itemHeight*count);
        }
        listView.setLayoutParams(layoutParams);
    }
    private void hPlxData(ListView listview) {
        List<CarPlateTypeListBean> mlist=new ArrayList<>();
        for (CarPlateTypeListBean carPlateTypeListBean:emptyOrderBean.getCarPlateType_List()) {
            mlist.add(carPlateTypeListBean);
        }
        if(mlist!=null&&mlist.size()>0){
            mhPlxselectBrand=mlist.get(0);
            mhPlxselectPosition=0;
            NeedCarTabTypeAdapter myAdapter = new NeedCarTabTypeAdapter(getActivity(), mlist,mhPlxselectPosition,"号牌类型");
            listview.setAdapter(myAdapter);
            myAdapter.setOnItemClickListener(new NeedCarTabTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(CarPlateTypeListBean changeCompanyBean, int selectPosition) {
                    mhPlxselectBrand=changeCompanyBean;
                    mhPlxselectPosition=selectPosition;
                    popupWindow.dismiss();
                    hplx_txt.setText(mhPlxselectBrand.getName());
                    yxhplx=mhPlxselectBrand.getCarFlow_CarPlateTypeId();
                    EventBus.getDefault().postSticky(new EventBusMessageBean("意向车",yxc,yxhplx,zws));
                }

                @Override
                public void onMRClick(CarPlateTypeListBean changeCompanyBean) {
                }
            });
        }
    }
    OrderSelectBottomControlPanel bottomControlPanel;
    private void setMapBottom(String str) {
        bottomControlPanel.mtype=str;
        if(bottomControlPanel!=null){
            bottomControlPanel.dissmiss();
        }
        bottomControlPanel = OrderSelectBottomControlPanel.newInstance(mbean,mcarStyleListBean.getMinSeatNum(),mcarStyleListBean.getMaxSeatNum(),getActivity(),this);
        bottomControlPanel.show(mlinearLayout);
    }
    EmptyOrderBean emptyOrderBean;
    @Override
    protected void initView() {
        emptyOrderBean=new Gson().fromJson(mbean, EmptyOrderBean.class);
        if(emptyOrderBean!=null){
            GlideUtils.displayAdd(getActivity(),img_car,mcarStyleListBean.getImageUrl());
            if(emptyOrderBean.getCarPlateType_List()!=null&&emptyOrderBean.getCarPlateType_List().size()>0){
                hplx_txt.setText(emptyOrderBean.getCarPlateType_List().get(0).getName());
            }
            zw_txt.setText(mcarStyleListBean.getMinSeatNum()+"-"+mcarStyleListBean.getMaxSeatNum()+"座");
        }
    }

    @Override
    public void onwc(String mtype, String neir1, String neir2) {

    }

    @Override
    public void onTimeSelectWc(String mtype, CarPlateTypeListBean carPlateTypeListBean) {

    }

    @Override
    public void onTabSelect(String mtype, CarPlateTypeListBean carPlateTypeListBean) {
        switch (mtype){
            case "号牌类型":
                hplx_txt.setText(carPlateTypeListBean.getName());
                yxhplx=carPlateTypeListBean.getCarFlow_CarPlateTypeId();
                break;
            case "座位数":
                zws=carPlateTypeListBean.getName()+"";
                zw_txt.setText(carPlateTypeListBean.getName()+"座");
                break;
        }
        EventBus.getDefault().postSticky(new EventBusMessageBean("意向车",yxc,yxhplx,zws));
    }
}
