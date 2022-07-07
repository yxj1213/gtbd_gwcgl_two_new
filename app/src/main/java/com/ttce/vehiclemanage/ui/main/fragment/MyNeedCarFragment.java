package com.ttce.vehiclemanage.ui.main.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.flyco.tablayout.SlidingTabLayout;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.commonwidget.StatusBarCompat;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.app.AppConstant;
import com.ttce.vehiclemanage.bean.CarPlateTypeListBean;
import com.ttce.vehiclemanage.bean.EmptyOrderBean;
import com.ttce.vehiclemanage.bean.EventBusMessageBean;
import com.ttce.vehiclemanage.bean.IsOrderingBean;
import com.ttce.vehiclemanage.bean.Message2Bean;
import com.ttce.vehiclemanage.bean.MessageEvent;
import com.ttce.vehiclemanage.bean.OrderSuccessBean;
import com.ttce.vehiclemanage.bean.OtherUserId;
import com.ttce.vehiclemanage.bean.WorkBeanchBean;
import com.ttce.vehiclemanage.ui.main.activity.MainActivity;
import com.ttce.vehiclemanage.ui.main.activity.home.CreateBusinessActivity;
import com.ttce.vehiclemanage.ui.main.activity.my.order.MyOrderDetailsActivity;
import com.ttce.vehiclemanage.ui.main.activity.needcar.CommonRouteActivity;
import com.ttce.vehiclemanage.ui.main.activity.needcar.DiTuCheckAddressActivity;
import com.ttce.vehiclemanage.ui.main.activity.needcar.OrderSuccessActivity;
import com.ttce.vehiclemanage.ui.main.adapter.needcar.NeedCarTabDataAdapter;
import com.ttce.vehiclemanage.ui.main.adapter.needcar.TuJingDiAdapter;
import com.ttce.vehiclemanage.ui.main.adapter.WorkBeanchAdapter;
import com.ttce.vehiclemanage.ui.main.contract.needcar.MyNeedCarConstract;
import com.ttce.vehiclemanage.ui.main.fragment.needcar.MyNeedCarCardFragment;
import com.ttce.vehiclemanage.ui.main.model.needcar.MyNeedCarModel;
import com.ttce.vehiclemanage.ui.main.popwindow.OrderSelectBottomControlPanel;
import com.ttce.vehiclemanage.ui.main.presenter.needcar.MyNeedCarPresenter;
import com.ttce.vehiclemanage.ui.mine.activity.NewPersonDetailActivity;
import com.ttce.vehiclemanage.ui.usermanage.manager.UserManager;
import com.ttce.vehiclemanage.utils.AlertDialogUtils;
import com.ttce.vehiclemanage.utils.KeybordUtil;
import com.ttce.vehiclemanage.utils.NoPerson_NoCreatebusinessAlertDialogUtils;
import com.ttce.vehiclemanage.utils.TextOrEditTextUtil;
import com.ttce.vehiclemanage.utils.ToastUtil;
import com.ttce.vehiclemanage.widget.MaxRecyclerView;
import com.ttce.vehiclemanage.widget.VehicleDialog;
import com.ttce.vehiclemanage.widget.linearlayout.ShadowLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MyNeedCarFragment  extends BaseFragment<MyNeedCarPresenter, MyNeedCarModel> implements VehicleDialog.SelectCarClickListener,MyNeedCarConstract.View, OrderSelectBottomControlPanel.ControlPanelListener,TuJingDiAdapter.PushclickListener, WorkBeanchAdapter.DelListener,AlertDialogUtils.DialogDataListener,NoPerson_NoCreatebusinessAlertDialogUtils.DialogDataListener{

    @Bind(R.id.recycler_view)
    MaxRecyclerView recycler_view;
    @Bind(R.id.tab_layout)
    SlidingTabLayout tab_layout;
    @Bind(R.id.vp)
    ViewPager vp;
    @Bind(R.id.img_gift)
    ImageView img_gift;
    @Bind(R.id.recycler_view_ddzz)
    MaxRecyclerView recycler_view_ddzz;
    @Bind(R.id.recycler_view_scry)
    IRecyclerView recycler_view_scry;
    @Bind(R.id.tv_ycr)
    TextView tv_ycr;
    @Bind(R.id.tv_jsr)
    TextView tv_jsr;
    @Bind(R.id.lin_tryc)
    LinearLayout lin_tryc;
    @Bind(R.id.edt_scry_num)
    EditText edt_scry_num;
//    @Bind(R.id.tv_ddzz_num)
//    TextView tv_ddzz_num;
     @Bind(R.id.lin_gdxx)
     LinearLayout lin_gdxx;
     @Bind(R.id.lin_all)
     LinearLayout lin_all;
     @Bind(R.id.tv_gdxx)
    TextView tv_gdxx;
     @Bind(R.id.needcar2_txt)
    TextView needcar2_txt;
 @Bind(R.id.needcar2_txt2)
    TextView needcar2_txt2;
     @Bind(R.id.img_swap)
    ImageView img_swap;
    @Bind(R.id.bmapView)
    MapView mMapView;
    @Bind(R.id.txt_address)
    TextView txt_address;
    @Bind(R.id.tv_yclx)
    TextView tv_yclx;
    @Bind(R.id.tv_ycsj)
    TextView tv_ycsj;
    @Bind(R.id.tv_sub)
    TextView tv_sub;
    @Bind(R.id.txt_jjgz)
    TextView txt_jjgz;
    @Bind(R.id.tv_gdxx_sq)
    TextView tv_gdxx_sq;
    @Bind(R.id.rel_cph)
    RelativeLayout rel_cph;
    @Bind(R.id.view_cph)
    View view_cph;
    @Bind(R.id.lin_ycsy)
    LinearLayout lin_ycsy;
    @Bind(R.id.edt_bt)
    EditText edt_bt;
    @Bind(R.id.edt_nr)
    EditText edt_nr;
    @Bind(R.id.txt_lxdh)
    TextView txt_lxdh;
/*    @Bind(R.id.edt_xm)
    EditText edt_xm;*/
    @Bind(R.id.tv_yxcph)
    TextView tv_yxcph;
    @Bind(R.id.tv_bottom)
    ShadowLayout tv_bottom;
    @Bind(R.id.id_select)
    CheckBox id_select;

    @Bind(R.id.fra_no_data)
    FrameLayout fra_no_data;
    @Bind(R.id.lin_hava_data)
    LinearLayout lin_hava_data;

    public boolean nowIsShow;
    WorkBeanchAdapter ddzzworkBeanchAdapter;
    WorkBeanchAdapter scryworkBeanchAdapter;
    TuJingDiAdapter tuJingDiAdapter;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private NeedCarTabDataAdapter mAdapter;
    public static boolean isRef=false;
    public void setShow(boolean show) {
        nowIsShow = show;

        if(show==true){
            if(!UserManager.getLoginBean().getCompanyId().equals("")&&!UserManager.getLoginBean().getCompanyId().equals("00000000-0000-0000-0000-000000000000"))//代表不在当前在个人下
            {
                if(lin_hava_data!=null&&fra_no_data!=null){
                    lin_hava_data.setVisibility(View.VISIBLE);
                    fra_no_data.setVisibility(View.GONE);
                }
                if(mPresenter!=null){
                    mPresenter.getEmptyOrderBeans();
                }
            }else{
                NoPerson_NoCreatebusinessAlertDialogUtils.showAlertDialog(getContext(),"个人",this);
                tv_bottom.setAlpha(0.4f);//当前有订单时，按钮修改为40%透明度。
                tv_bottom.setClickable(false);
            }
        }
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_my_need_car;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        StatusBarCompat.setStatusBarColor(getActivity(), ContextCompat.getColor(getContext(), R.color.app_main_colors));
        EventBus.getDefault().register(this);


        txt_jjgz.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        img_gift.getBackground().setAlpha(15);
        requestPermission();
        ddzzData();//带队组长
        scryData();//随车人员

       TextOrEditTextUtil.editStyleBoldUtil(edt_scry_num);
    }

    public void CheckOnclick(boolean isCheck,int txt1noselectbg,int txt1bg,int txt2noselectbg,int txt2bg){
        needcar2_txt2.setEnabled(isCheck);
        needcar2_txt.setEnabled(isCheck);

        needcar2_txt2.setClickable(isCheck);
        needcar2_txt.setClickable(isCheck);

        if(isCheck==false){
            needcar2_txt.setBackgroundDrawable(getResources().getDrawable(txt1noselectbg));
            needcar2_txt2.setBackgroundDrawable(getResources().getDrawable(txt2noselectbg));
            needcar2_txt2.setTextColor(getResources().getColor(R.color.white));
            tuJingDiAdapter.disableAllItemChoser();
        }else{
            needcar2_txt.setBackgroundDrawable(getResources().getDrawable(txt1bg));
            needcar2_txt2.setBackgroundDrawable(getResources().getDrawable(txt2bg));
            needcar2_txt2.setTextColor(getResources().getColor(R.color.app_main_colors));
            tuJingDiAdapter.enableItemChoser();
        }
    }
    OrderSelectBottomControlPanel bottomControlPanel;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick({R.id.txt_lxdh,R.id.tv_yxcph,R.id.rel_ycr,R.id.rel_jsr,R.id.tv_gdxx,R.id.tv_gdxx_sq,R.id.rel_ycsj,R.id.needcar2_txt,R.id.img_swap,R.id.rel_yclx,R.id.tv_bottom,R.id.needcar2_txt2,R.id.tv_cylx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_lxdh:
                setMapBottom("他人用车");
                break;
            case R.id.tv_yxcph:
                new VehicleDialog().show(getActivity().getSupportFragmentManager(),this);
                break;

            case R.id.rel_jsr:
                OrderSelectBottomControlPanel.mycrvaule=tv_ycr.getText().toString().trim();
                setMapBottom("驾驶人");
                break;

            case R.id.tv_cylx:
                CommonRouteActivity.goToPage(getActivity());
                break;

            case R.id.needcar2_txt2:
                if(isXuanZe==true){
                    if(tjdmlist.size()>3){
                        List<EmptyOrderBean.OrderMarkListBean> centertjdlist=new ArrayList<>();
                        for (int i = 1; i < tjdmlist.size() - 1; i++) {
                            EmptyOrderBean.OrderMarkListBean orderMarkListBean=tjdmlist.get(i);
                            double dis = DistanceUtil.getDistance(new LatLng(tjdmlist.get(0).getNeedMarkLat(),tjdmlist.get(0).getNeedMarkLng()), new LatLng(orderMarkListBean.getNeedMarkLat(),orderMarkListBean.getNeedMarkLng()));
                            orderMarkListBean.setLineDistance(dis);
                            centertjdlist.add(orderMarkListBean);
                        }
                        centertjdlist.sort((x, y) -> Double.compare(x.getLineDistance(), y.getLineDistance()));

                        for (int i = 1; i < tjdmlist.size() - 1; i++) {
                            tjdmlist.set(i,centertjdlist.get(i-1));
                        }
                        tuJingDiAdapter.notifyDataSetChanged();
                    }else{
                        ToastUtil.showToast("无法进行智能排序.");
                    }
                }else{
                    ToastUtil.showToast("无法进行智能排序.");
                }
                break;
            case R.id.rel_yclx:
                if(memptyOrderBean!=null&&memptyOrderBean.getCompanyUseCarType_List()!=null&&memptyOrderBean.getCompanyUseCarType_List().size()>0){
                    setMapBottom("用车类型");
                }else{
                    ToastUtil.showToast("该企业暂无用车流程，请联系管理员。");
                }
                break;
            case R.id.rel_ycsj:
                if(bottomControlPanel!=null){
                    bottomControlPanel.dissmiss();
                }
                setMapBottom("用车时间");
                break;
            case R.id.rel_ycr:
                setMapBottom("用车人");
                break;
            case R.id.tv_gdxx_sq:
                tv_gdxx_sq.setVisibility(View.GONE);
                lin_gdxx.setVisibility(View.GONE);
                tv_gdxx.setVisibility(View.VISIBLE);
                lin_ycsy.setVisibility(View.GONE);
                break;
            case R.id.tv_gdxx:
                lin_gdxx.setVisibility(View.VISIBLE);
                tv_gdxx.setVisibility(View.GONE);
                lin_ycsy.setVisibility(View.VISIBLE);
                tv_gdxx_sq.setVisibility(View.VISIBLE);

                break;
            case R.id.needcar2_txt:
                img_swap.setVisibility(View.GONE);
                if(tjdmlist.size()<17){
                    tjdmlist.add(tjdmlist.size()-1,new EmptyOrderBean.OrderMarkListBean());
                    tuJingDiAdapter.notifyDataSetChanged();
                    if(tjdmlist.size()>=2){
                        needcar2_txt.setText("添加途经点("+(tjdmlist.size()-2)+"/15)");
                    }
                }else{
                    ToastUtil.showToast("最多只能添加15个途径地。");
                }
                break;
            case R.id.img_swap:
                EmptyOrderBean.OrderMarkListBean markStartBean=tjdmlist.get(0);
                EmptyOrderBean.OrderMarkListBean markEndBean=tjdmlist.get(1);
                tjdmlist.set(0,markEndBean);
                tjdmlist.set(1,markStartBean);
                tuJingDiAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_bottom:
                if(isXuanZe==true){
                    for (int i=0;i<tjdmlist.size();i++) {
                        if(tjdmlist.get(i).getNeedMarkSimpleAddress().equals("")){
                            ToastUtil.showToast("请选择起点或终点。");
                            return;
                        }
                    }
                }else{
                    if(id_select.isChecked()==false){
                        for (int i=0;i<tjdmlist.size();i++) {
                            if(tjdmlist.get(i).getNeedMarkTitle().equals("")){
                                ToastUtil.showToast("请输入起点或终点。");
                                return;
                            }
                        }
                    }
                }

                if(jsr_value.equals("")){
                    ToastUtil.showToast("请选择驾驶人。");
                    return;
                }
                if(!tv_ycr.getText().toString().contains("本人用车")){
                    if(txt_lxdh.getText().toString().trim().equals("")){
                        ToastUtil.showToast("请输入用车人姓名和手机号。");
                        return;
                    }
                }
                save();
                break;
        }
    }


    public void setMapBottom(String type) {
        bottomControlPanel.mtype=type;
        if (bottomControlPanel == null) {
            bottomControlPanel = OrderSelectBottomControlPanel.newInstance(new Gson().toJson(memptyOrderBean),getActivity(),this);
        }
        bottomControlPanel.show(lin_all);
    }

    List<EmptyOrderBean.OrderMarkListBean> tjdmlist;
    private void tjdData(boolean isXuanZe) {
        id_select.setButtonDrawable(R.drawable.soild_gray_yuan);
        id_select.setChecked(false);
        id_select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(isXuanZe==false){//只有流程是输入的情况下才可以选择暂不填写用车线路
                    if(b==true){
                        isEnable=false;
                        id_select.setButtonDrawable(R.drawable.soild_blue_yuanhuan);
                        id_select.setTextColor(getResources().getColor(R.color.work_item_txt_color));
                        CheckOnclick(false,R.drawable.noselect_gray_15_bg,0,R.drawable.noselect_gray_15_bg,0);
                    }else{
                        isEnable=true;
                        id_select.setButtonDrawable(R.drawable.soild_gray_yuan);
                        id_select.setTextColor(getResources().getColor(R.color.needcar_txt_color1));
                        CheckOnclick(true,0,R.drawable.blue_15_bg,0,R.drawable.border_blue_bg_15);
                    }
                }else{
                    ToastUtil.showToast("当前流程不能使用该功能。");
                }
            }
        });

        tjdmlist=new ArrayList<>();
        tjdmlist.add(new EmptyOrderBean.OrderMarkListBean());
        tjdmlist.add(new EmptyOrderBean.OrderMarkListBean());
        needcar2_txt.setText("添加途经点("+(tjdmlist.size()-2)+"/15)");


        tuJingDiAdapter = new TuJingDiAdapter(getContext(), R.layout.fragment_my_need_tjd_item,tjdmlist,this,isXuanZe, mItemHelper );
//        tuJingDiAdapter.openLoadAnimation(new ScaleInAnimation());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayout.VERTICAL,false) {
            @Override
            public boolean canScrollVertically() {
                // 直接禁止垂直滑动
                return false;
            }
        };
        recycler_view.setNestedScrollingEnabled(false);//禁止滑动
        recycler_view.setLayoutManager(layoutManager);
        recycler_view.setAdapter(tuJingDiAdapter);

        mItemHelper.attachToRecyclerView(recycler_view);
    }

    public boolean isEnable=true;
    ItemTouchHelper mItemHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                final int swipeFlags = 0;
                return makeMovementFlags(dragFlags, swipeFlags);
            } else {
                final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                final int swipeFlags = 0;
                return makeMovementFlags(dragFlags, swipeFlags);
            }
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            //得到当拖拽的viewHolder的Position
            int fromPosition = viewHolder.getAdapterPosition()-2;
            //拿到当前拖拽到的item的viewHolder
            int toPosition;
            if(target.getAdapterPosition()==1){
                toPosition = target.getAdapterPosition();
            }else{
                toPosition = target.getAdapterPosition()-2;
            }

            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(tjdmlist, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(tjdmlist, i, i - 1);
                }
            }
            tuJingDiAdapter.notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//                Toast.makeText(MainActivity.this, "拖拽完成 方向" + direction, Toast.LENGTH_SHORT).show();
            Log.e("hsjkkk", "拖拽完成 方向" + direction);

        }

        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            super.onSelectedChanged(viewHolder, actionState);
            Log.e("hsjkkk", "onSelectedChanged()");
            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE)
                viewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.bg_color));
        }

        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            Log.e("hsjkkk", "clearView()");
            viewHolder.itemView.setBackgroundColor(0);

        }

        //重写拖拽不可用
        @Override
        public boolean isLongPressDragEnabled() {
            Log.e("hsjkkk", "isLongPressDragEnabled()");
            return isEnable;
        }
    });





    List<WorkBeanchBean> ddzzmlist;
    private void ddzzData() {
        recycler_view_ddzz.setVisibility(View.VISIBLE);
        ddzzmlist=new ArrayList<>();
        ddzzmlist.add(new WorkBeanchBean("添加"));
//        tv_ddzz_num.setText((ddzzmlist.size()-1)+"人");
        ddzzworkBeanchAdapter = new WorkBeanchAdapter(getContext(), R.layout.fragment_my_need_ryxx_item, ddzzmlist,"带队组长",this);
        ddzzworkBeanchAdapter.openLoadAnimation(new ScaleInAnimation());

        recycler_view_ddzz.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recycler_view_ddzz.setAdapter(ddzzworkBeanchAdapter);
        ddzzworkBeanchAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                WorkBeanchBean workBeanchBean = (WorkBeanchBean) o;
                if(ddzzmlist.size()==2){
                    ToastUtil.showToast("最多只能有一个带队组长。");
                }else{
                    setMapBottom("带队组长");
                }
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }

    List<WorkBeanchBean> scrymlist;
    private void scryData() {
        recycler_view_scry.setVisibility(View.VISIBLE);
        scrymlist=new ArrayList<>();
        scrymlist.add(new WorkBeanchBean("添加"));
        scryworkBeanchAdapter = new WorkBeanchAdapter(getContext(), R.layout.fragment_my_need_ryxx_item, scrymlist,"随车人员",this);
        scryworkBeanchAdapter.openLoadAnimation(new ScaleInAnimation());
        recycler_view_scry.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recycler_view_scry.setAdapter(scryworkBeanchAdapter);
       // recycler_view_scry.addItemDecoration(new SpaceItemDecoration(2));
        scryworkBeanchAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                setMapBottom("随车人员");
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }

    public void save(){
        AlertDialogUtils.showAlertDialog(getContext(),getContext().getString(R.string.dialog_ts5),2,this);
    }


    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {
        if (nowIsShow) {

                ToastUitl.showToastWithImg(msg, R.drawable.ic_wrong);

        } else {

        }
    }
    boolean isContains=false;
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMessageEvent(EmptyOrderBean.OrderMarkListBean tuJingDiBean) {
        if(tuJingDiBean!=null){
            if(tuJingDiBean.getMarkTypeFormat()!=null&&!tuJingDiBean.getMarkTypeFormat().equals("")){
                if(tuJingDiBean.getMarkTypeFormat().equals("创建订单选择地址")){
                    for (int i=0;i<tjdmlist.size();i++){
                        if(i==select){
                            isContains=true;
                            tjdmlist.set(i,tuJingDiBean);
                            break;
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
            }
        }
    }
    public String yxc="",yxhplx="",yxzws="";
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMessageEvent(EventBusMessageBean eventBusMessageBean) {
       switch (eventBusMessageBean.getTag()){
           case "意向车"://选择的意向车型、意向号牌类型、意向座位数
               yxc=eventBusMessageBean.title;
               yxhplx=eventBusMessageBean.title2;
               yxzws=eventBusMessageBean.title3;
               break;
       }
    }
    public String ycsj_value="",ycsj_kssj="",ycsj_jssj="";
    public String yz="";
    @Override
    public void onwc(String type,String neir1, String neir2) {
            switch (type){
                case "他人用车":
                    yz="他人用车";
                    mPresenter.getOrderOtherUserIdBean("检测是否存在该员工",neir1,neir2);
                    break;
                case "带队组长":
                    yz="带队组长";
                    mPresenter.getOrderOtherUserIdBean("检测是否存在该员工",neir1,neir2);
                    break;
                case "随车人员":
                   /* if(neir1.length()<=2){
                        scrymlist.add(0,new WorkBeanchBean(neir1+getResources().getString(R.string.stringsnull)+neir2));
                    }else if(neir1.length()>2&&neir1.length()<=4){*/
                       scrymlist.add(0,new WorkBeanchBean(neir1+getResources().getString(R.string.stringsnull2)+neir2));
                   /* }else{
                        scrymlist.add(0,new WorkBeanchBean(neir1+" "+neir2));
                    }*/
                    scryworkBeanchAdapter.notifyDataSetChanged();
                    break;
                case "设置默认用车类型":
                    mPresenter.setDefaultOrderCarType("用车类型默认",neir1);
                    break;
                default:
                    tv_ycsj.setText(type);
                    //选择的用车时间
                    if(type.contains("现在用车")){
                        ycsj_value="10";
                        ycsj_kssj="";
                        ycsj_jssj=neir2;
                    }else{
                        ycsj_value="20";
                        ycsj_kssj=neir1;
                        ycsj_jssj=neir2;
                    }
                    break;
        }
    }

    public boolean isXuanZe=false;//如果是true，则表示途经地是选择，反之是输入。
    public String ycr="";
    public String yclx="",yclx_lcid="";
    public String jsr_userid="",jsr_saffid="",jsr_value="";
    @Override
    public void onTimeSelectWc(String mtype, CarPlateTypeListBean carPlateTypeListBean) {
        switch (mtype){
            case "用车类型"://用车类型如果IsWillCarPlate这个字段显示的是true，则显示意向车牌号的输入。
                tv_yclx.setText(carPlateTypeListBean.getName());
                if(carPlateTypeListBean.isIsSelected()==true){
                    rel_cph.setVisibility(View.VISIBLE);
                    view_cph.setVisibility(View.VISIBLE);
                }else{
                    rel_cph.setVisibility(View.GONE);
                    view_cph.setVisibility(View.GONE);
                }
                //选择的用车类型和流程ID
                yclx=carPlateTypeListBean.getId();
                yclx_lcid=carPlateTypeListBean.getCarFlow_CarPlateTypeId();


                isXuanZe=carPlateTypeListBean.isMapPoint();
                tjdData(isXuanZe);
                break;
            case "用车人":
                tv_ycr.setText(carPlateTypeListBean.getName());

                //选择的用车人(如果是本人用车，则ycyh-用车用户是本人的staffid，如果是他人用车，则ycyh-用车用户是他人的staffid)
                ycr=carPlateTypeListBean.getCarFlow_CarPlateTypeId();
                if(carPlateTypeListBean.getName().contains("本人用车")){
                    lin_tryc.setVisibility(View.GONE);
                    ycyhUserId=UserManager.getLoginBean().getUserId();
                    ycyhStaffId=UserManager.getLoginBean().getStaffId();
                }else{
                    ycyhUserId="";
                    ycyhStaffId="";
                    lin_tryc.setVisibility(View.VISIBLE);
                }
                tv_jsr.setText("");
                jsr_value="";
                jsr_userid="";
                jsr_saffid="";
                break;
            case "驾驶人":
                TextOrEditTextUtil.textStyleBoldUtil(tv_jsr,carPlateTypeListBean.getName());
                jsr_value=carPlateTypeListBean.getCarFlow_CarPlateTypeId();
                if(carPlateTypeListBean.getName().contains("本人")){
                    jsr_userid=UserManager.getLoginBean().getUserId();
                    jsr_saffid=UserManager.getLoginBean().getStaffId();
                }else{//如果是本人驾驶DriverUserId、DriverStaffId是当前用户id,系统指派时是传空
                    jsr_userid="";
                    jsr_saffid="";
                }
                break;
        }
    }

    @Override
    public void onTabSelect(String mtype, CarPlateTypeListBean carPlateTypeListBean) {

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

        if(isXuanZe==true){//如果是选择，则跳转到地图界面跳转，反之，则输入。
            DiTuCheckAddressActivity.goToPage(getActivity(),nr,new Gson().toJson(item),"创建订单选择地址");
        }else{
            tjdmlist.set(pos,item);
        }
    }

    public int select=0;

    private static final int REQUEST_CODE_CAMERA = 1;
    private LocationClient mLocationClient;
    boolean isLocationClientStop = false;
    BaiduMap mBaiduMap;

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >23) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED&&
                    ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ){
                //授予权限
                initLocation();
            }else{
                //未获得权限
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},REQUEST_CODE_CAMERA);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE_CAMERA:
                if(grantResults.length >= 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initLocation();
                } else {
                    Toast.makeText(getActivity(), "定位失败", Toast.LENGTH_SHORT).show();
                }
                break;
            default:break;
        }
    }

    private void initLocation() {
        mBaiduMap = mMapView.getMap();
        //定位初始化
        try {
            /**
             * 定位SDK是否同意隐私政策接口
             * false不同意：不支持定位，SDK抛出异常
             * true同意：支持定位功能
             */
            LocationClient.setAgreePrivacy(true);
            mLocationClient = new LocationClient(getActivity());
            //通过LocationClientOption设置LocationClient相关参数
            LocationClientOption option = new
                    LocationClientOption();
            option.setOpenGps(true); // 打开gps
            option.setCoorType("bd09ll"); // 设置坐标类型
            option.setScanSpan(1000);
            option.setIsNeedLocationDescribe(true);
            option.setIsNeedAddress(true); // 可选，设置是否需要地址信息，默认不需要
//设置locationClientOption
            mLocationClient.setLocOption(option);
//注册LocationListener监听器
            MyLocationListener myLocationListener = new MyLocationListener();
            mLocationClient.registerLocationListener(myLocationListener);
//开启地图定位图层
            mLocationClient.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void del(String type, int pos) {
         if(type.equals("带队组长")){
             ddzzmlist.remove(pos);
             ddzzworkBeanchAdapter.notifyDataSetChanged();
//             tv_ddzz_num.setText(ddzzmlist.size()-1+"人");
         }else{
             scrymlist.remove(pos);
             scryworkBeanchAdapter.notifyDataSetChanged();
         }
    }

    @Override
    public void ondialogwc(String mtype, String neir1, String neir2) {
        if(mtype.equals(getContext().getString(R.string.dialog_ts5))){

            JsonArray mlist= new JsonArray();
            if(scrymlist.size()>1){
                for (WorkBeanchBean workBeanchBean:scrymlist) {
                    if(!workBeanchBean.getTitle().equals("添加")){
                        String[] str=workBeanchBean.getTitle().split(getResources().getString(R.string.stringsnull2));
                        JsonObject objectInList = new JsonObject();
                        objectInList.addProperty("PassengerName",str[0]);
                        objectInList.addProperty("PassengerPhone",str[1]);
                        mlist.add(objectInList);
                    }
                }
            }

            JsonArray mMarklist= new JsonArray();
            if(isXuanZe==true){
                for (int i=0;i<tjdmlist.size();i++) {
                    JsonObject objectInList = new JsonObject();
                    objectInList.addProperty("NeedMarkTitle",tjdmlist.get(i).getNeedMarkTitle());
                    objectInList.addProperty("NeedMarkSimpleAddress",tjdmlist.get(i).getNeedMarkSimpleAddress());
                    objectInList.addProperty("NeedMarkFullAddress",tjdmlist.get(i).getNeedMarkFullAddress());
                    objectInList.addProperty("NeedMarkLng",tjdmlist.get(i).getNeedMarkLng());
                    objectInList.addProperty("NeedMarkLat",tjdmlist.get(i).getNeedMarkLat());
                    objectInList.addProperty("MarkLevel",i+1);
                    objectInList.addProperty("MarkType","10");
                    objectInList.addProperty("IsFinishMark",false);
                    objectInList.addProperty("LinkName",tjdmlist.get(i).getLinkName());
                    objectInList.addProperty("LinkPhone",tjdmlist.get(i).getLinkPhone());
                    mMarklist.add(objectInList);
                }
            }else{
                if(id_select.isChecked()==false){
                    for (int i=0;i<tjdmlist.size();i++) {
                        JsonObject objectInList = new JsonObject();
                        objectInList.addProperty("NeedMarkTitle",tjdmlist.get(i).getNeedMarkTitle());
                        objectInList.addProperty("MarkLevel",i+1);
                        mMarklist.add(objectInList);
                    }
                }
            }
            mPresenter.getAddOrderBean(ycyhUserId,ycyhStaffId,ycr,yclx_lcid,yclx,jsr_value,jsr_userid,jsr_saffid,edt_bt.getText().toString().trim(),edt_nr.getText().toString().trim(),ycsj_value,ycsj_kssj,ycsj_jssj,yxc,yxhplx,tv_yxcph.getText().toString().trim(),yxzws,ddld_userid,ddld_staffid,edt_scry_num.getText().toString().trim(),mMarklist,mlist);

        }
    }

    @Override
    public void returnEmptyOrderBean(EmptyOrderBean emptyOrderBean) {
        this.memptyOrderBean=emptyOrderBean;
        jsr_value="";
        mPresenter.StaffMagStatePresenter();


        //界面显示数据流程
        /** 设置头部的tab */
        tabData();
        /** 初始化的值*/
        moRenZhi();
    }
    @Override
    public void returnAddOrderBean(OrderSuccessBean orderSuccessBean) {
        if(orderSuccessBean!=null&&!orderSuccessBean.getCarFlow_OrderId().equals("")){
            OrderSuccessActivity.goToPage(getActivity(),orderSuccessBean.getCarFlow_OrderId());
        }else{
            ToastUtil.showToast("订单提交失败。");
        }

    }

    public String ycyhUserId="",ycyhStaffId="";
    public String ddld_userid="",ddld_staffid="";
    @Override
    public void returnOtherUserIdBean(OtherUserId otherUserId) {
            if(yz.equals("带队组长")){//验证带队组长和他人用车时的用户的姓名和电话正确否
                ddzzmlist.add(0,new WorkBeanchBean(otherUserId.getName()+" "+otherUserId.getPhone()));
//                tv_ddzz_num.setText((ddzzmlist.size()-1)+"人");
                ddzzworkBeanchAdapter.notifyDataSetChanged();
                ddld_userid=otherUserId.getUserId();
                ddld_staffid=otherUserId.getStaffId();
            }else{
                isJieGuo=true;
                TextOrEditTextUtil.textStyleBoldUtil(txt_lxdh,otherUserId.getName()+" "+otherUserId.getPhone());
                ycyhUserId=otherUserId.getUserId();
                ycyhStaffId=otherUserId.getStaffId();
                txt_lxdh.setTextColor(getResources().getColor(R.color.work_item_txt_color));
            }
    }

    @Override
    public void returnFailBean(String message,String type) {
        if(type.equals("用车类型默认")){
            ToastUtil.showToast("设置成功。");
        }else{
//            AlertDialogUtils.showAlertDialog(getContext(),message,1,this);
            ToastUtil.showToast(message);
            txt_lxdh.setTextColor(getResources().getColor(R.color.red));
        }
    }
    IsOrderingBean isOrderingBean;
    @Override
    public void returnOrderIsOrdering(IsOrderingBean isOrderingBean) {

        if(isOrderingBean.isOrdering()==true){
            this.isOrderingBean=isOrderingBean;
            NoPerson_NoCreatebusinessAlertDialogUtils.showAlertDialog(getContext(),"有进行中的订单",this);
            tv_bottom.setAlpha(0.4f);//当前有订单时，按钮修改为40%透明度。
            tv_bottom.setClickable(false);
        }else{
            tv_bottom.setAlpha(1f);//当前有订单时，按钮修改为40%透明度。
            tv_bottom.setClickable(true);
        }
    }
    Message2Bean mMessageBean;
    @Override
    public void returnStaffMagState(Message2Bean MessageBean) {
        mMessageBean= MessageBean;
        if(memptyOrderBean==null||memptyOrderBean.getCompanyUseCarType_List()==null||memptyOrderBean.getCompanyUseCarType_List().size()==0){//该企业下未创建流程
            tv_bottom.setAlpha(0.4f);//当前有订单时，按钮修改为40%透明度。
            tv_bottom.setClickable(false);
            /**
             * 0  待完善
             * 1 审核中
             * */
            if(MessageBean.getValue()==0||MessageBean.getValue()==1){//该员工在该企业下未完善员工信息
                NoPerson_NoCreatebusinessAlertDialogUtils.showAlertDialog(getContext(),"未完善员工信息未创建流程",this);
            }else{
                NoPerson_NoCreatebusinessAlertDialogUtils.showAlertDialog(getContext(),"已完善员工信息未创建流程",this);
            }
        }else{
            /**
             * 0  待完善
             * 1 审核中
             * */
            if(MessageBean.getValue()==0||MessageBean.getValue()==1){//该员工在该企业下未完善员工信息
                NoPerson_NoCreatebusinessAlertDialogUtils.showAlertDialog(getContext(),"有流程未完善员工信息",this);
                tv_bottom.setAlpha(0.4f);//当前有订单时，按钮修改为40%透明度。
                tv_bottom.setClickable(false);
            }else{
//                NoPerson_NoCreatebusinessAlertDialogUtils.showAlertDialog(getContext(),"有流程已完善员工信息",this);
//                tv_bottom.setAlpha(1f);//当前有订单时，按钮修改为40%透明度。
//                tv_bottom.setClickable(true);
                mPresenter.getOrderIsOrdering();
            }
        }

    }

    public EmptyOrderBean memptyOrderBean=null;
    /** 设置头部的tab */
    public void tabData() {
        mFragments = new ArrayList<>();
        if(memptyOrderBean!=null){
            if(memptyOrderBean.getCarStyle_List()!=null&&memptyOrderBean.getCarStyle_List().size()>0){
                List<EmptyOrderBean.CarStyleListBean> emptyOrderBeans=memptyOrderBean.getCarStyle_List();

                if(emptyOrderBeans!=null&&emptyOrderBeans.size()>0){
                    for (EmptyOrderBean.CarStyleListBean title : emptyOrderBeans) {
                        mFragments.add(MyNeedCarCardFragment.getInstance(new Gson().toJson(memptyOrderBean),title,lin_all));
                    }
                    mAdapter = new NeedCarTabDataAdapter(getFragmentManager(),mFragments,emptyOrderBeans);
                    vp.setAdapter(mAdapter);
                    tab_layout.setViewPager(vp);
                    tab_layout.setCurrentTab(0);
                    tab_layout.notifyDataSetChanged();


                    //默认的意向车型、意向号牌类型、意向座位数
                    yxzws=emptyOrderBeans.get(0).getMaxSeatNum()+"";
                    yxc=emptyOrderBeans.get(0).getCarFlow_CarStyleId();
                }
            }

            if(memptyOrderBean.getCarPlateType_List()!=null&&memptyOrderBean.getCarPlateType_List().size()>0){
                yxhplx=memptyOrderBean.getCarPlateType_List().get(0).getCarFlow_CarPlateTypeId();
            }
        }
    }

    /** 初始化的值*/
    public void moRenZhi(){

        if(memptyOrderBean.getCompanyUseCarType_List()!=null){
            /**
             * 首次进入页面没有默认的用车类型和流程id,显示第一条数据。
             * */
                tv_yclx.setText(memptyOrderBean.getCompanyUseCarType_List().get(0).getUseCarTypeName());
                isXuanZe=memptyOrderBean.getCompanyUseCarType_List().get(0).isIsUseMapPoint();

                //默认的用车类型和流程ID
                yclx=memptyOrderBean.getCompanyUseCarType_List().get(0).getUseCarTypeId();
                yclx_lcid=memptyOrderBean.getCompanyUseCarType_List().get(0).getProcessId();
                for (EmptyOrderBean.CompanyUseCarTypeListBean em:memptyOrderBean.getCompanyUseCarType_List()) {
                    if(em.isIsDefault()==true){//判断用户是否有默认值
                        tv_yclx.setText(em.getUseCarTypeName());
                        isXuanZe=em.isIsUseMapPoint();

                        //默认的用车类型和流程ID
                        yclx=em.getUseCarTypeId();
                        yclx_lcid=em.getProcessId();
                    }
                }
                tjdData(isXuanZe);
        }

        for (EmptyOrderBean.WillUseDateTimeTypeListBean em:memptyOrderBean.getWillUseDateTimeType_List()) {
            if(em.isSelected()==true){
                tv_ycsj.setText(em.getText());
                //默认的用车时间的值
                ycsj_value="10";
            }
        }

        for (EmptyOrderBean.UseModeListBean em:memptyOrderBean.getUseMode_List()) {
            if(em.isIsSelected()==true){
                tv_ycr.setText(em.getText());
                //默认的用车人
                ycr=em.getValue();
                ycyhUserId=UserManager.getLoginBean().getUserId();
                ycyhStaffId=UserManager.getLoginBean().getStaffId();
            }
        }
        tv_jsr.setText("");


//        // 当输入关键字变化时，动态更新建议列表
//        edt_lxdh.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void afterTextChanged(Editable arg0) {
//
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
//                if (cs.length() <= 0) {
//                    return;
//                }
//                if(cs.length()==11&&isJieGuo==false){
//                    yz="他人用车";
//                    mPresenter.getOrderOtherUserIdBean("检测是否存在该员工",edt_xm.getText().toString().trim(),edt_lxdh.getText().toString().trim());
//                    edt_lxdh.setSelection(cs.length());
//                }else{
//                    edt_xm.setTextColor(getResources().getColor(R.color.work_item_txt_color));
//                    edt_lxdh.setTextColor(getResources().getColor(R.color.work_item_txt_color));
//                }
//            }
//        });
//        edt_xm.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void afterTextChanged(Editable arg0) {
//
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
//                edt_xm.setTextColor(getResources().getColor(R.color.work_item_txt_color));
//                edt_lxdh.setTextColor(getResources().getColor(R.color.work_item_txt_color));
//            }
//        });

    }
    boolean isJieGuo=false;

    @Override
    public void selectCar(String number) {
        TextOrEditTextUtil.textStyleBoldUtil(tv_yxcph,number);
    }

    @Override
    public void ondialogwc(String mtype) {
        if(mtype.equals("个人")){
            CreateBusinessActivity.goToPage(getActivity());
        }else if(mtype.equals("有进行中的订单")){
            if(isOrderingBean!=null&&isOrderingBean.getCarFlow_OrderId()!=null&&!isOrderingBean.getCarFlow_OrderId().equals("")){
                MyOrderDetailsActivity.goToPage(getActivity(),isOrderingBean.getCarFlow_OrderId(),getResources().getString(R.string.type1));
            }
        }else{
            if(mtype.equals("消失")){
                if(!UserManager.getLoginBean().getCompanyId().equals("")&&!UserManager.getLoginBean().getCompanyId().equals("00000000-0000-0000-0000-000000000000"))//代表不在当前在个人下
                {
                    ((MainActivity) getActivity()).SwitchTo(0);
                    lin_hava_data.setVisibility(View.VISIBLE);
                    fra_no_data.setVisibility(View.GONE);
                }else{
                    lin_hava_data.setVisibility(View.GONE);
                    fra_no_data.setVisibility(View.VISIBLE);
                }

            }else{
                NewPersonDetailActivity.goToPage(getActivity(),mMessageBean.getValue(),UserManager.getLoginBean().getStaffId(),UserManager.getLoginBean().getUserId(),UserManager.getLoginBean().getCompanyId());
            }
        }
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (location == null || isLocationClientStop)
                return;

            MyLocationData locData = new MyLocationData.Builder() .accuracy(location.getRadius()) .direction(100)
                    .latitude(location.getLatitude()) .longitude(location.getLongitude()) .build();
            mBaiduMap.setMyLocationData(locData);
            txt_address.setText(location.getCity());
        }

    }

    @Override
    public void onResume() {
        mMapView.onResume();
        KeybordUtil.closeKeybord(getActivity());
//        mPresenter.getOrderIsOrdering();
        super.onResume();
        if(nowIsShow==true&&isRef){
            if(!UserManager.getLoginBean().getCompanyId().equals("")&&!UserManager.getLoginBean().getCompanyId().equals("00000000-0000-0000-0000-000000000000"))//代表不在当前在个人下
            {
                isRef=false;
                mPresenter.getEmptyOrderBeans();
            }else{
                NoPerson_NoCreatebusinessAlertDialogUtils.showAlertDialog(getContext(),"个人",this);
                tv_bottom.setAlpha(0.4f);//当前有订单时，按钮修改为40%透明度。
                tv_bottom.setClickable(false);
            }

        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent msg) {
        switch (msg.getMsgWhat()) {
            case AppConstant.CHANGE_COMPANYID:
//                mPresenter.getOrderIsOrdering();
//                mPresenter.getEmptyOrderBeans();
        }
    }
    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        if(mLocationClient!=null){
            mLocationClient.stop();
        }
        if(mBaiduMap!=null){
            mBaiduMap.setMyLocationEnabled(false);
        }
        if(mMapView!=null){
            mMapView.onDestroy();
            mMapView = null;
        }
        super.onDestroyView();
    }
}
