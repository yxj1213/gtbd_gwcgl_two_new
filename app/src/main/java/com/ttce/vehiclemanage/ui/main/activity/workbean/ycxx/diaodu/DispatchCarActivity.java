package com.ttce.vehiclemanage.ui.main.activity.workbean.ycxx.diaodu;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jaydenxiao.common.base.BaseActivity;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.CarPlateTypeListBean;
import com.ttce.vehiclemanage.bean.CarPositionBean;
import com.ttce.vehiclemanage.bean.DispatchDriverListBean;
import com.ttce.vehiclemanage.bean.EmptyOrderBean;
import com.ttce.vehiclemanage.bean.IsOrderingBean;
import com.ttce.vehiclemanage.bean.Message2Bean;
import com.ttce.vehiclemanage.bean.OrderSuccessBean;
import com.ttce.vehiclemanage.bean.OtherUserId;
import com.ttce.vehiclemanage.ui.main.contract.needcar.MyNeedCarConstract;
import com.ttce.vehiclemanage.ui.main.model.needcar.MyNeedCarModel;
import com.ttce.vehiclemanage.ui.main.popwindow.OrderSelectBottomControlPanel;
import com.ttce.vehiclemanage.ui.main.presenter.needcar.MyNeedCarPresenter;
import com.ttce.vehiclemanage.utils.AlertDialogUtils;
import com.ttce.vehiclemanage.utils.TextOrEditTextUtil;
import com.ttce.vehiclemanage.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.OnClick;

public class DispatchCarActivity extends BaseActivity<MyNeedCarPresenter, MyNeedCarModel> implements MyNeedCarConstract.View, OrderSelectBottomControlPanel.ControlPanelListener,AlertDialogUtils.DialogDataListener{
    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.tv_yclx)
    TextView tv_yclx;
    @Bind(R.id.tv_pcfw)
    TextView tv_pcfw;
    @Bind(R.id.tv_xzjsr)
    TextView tv_xzjsr;
    @Bind(R.id.tv_xzcl)
    TextView tv_xzcl;
    @Bind(R.id.lin_sel)
    LinearLayout lin_sel;
    @Bind(R.id.tv_sure)
    TextView tv_sure;
    @Bind(R.id.tv_cancle)
    TextView tv_cancle;
    @Bind(R.id.tv_pclx)
    TextView tv_pclx;
    @Bind(R.id.lin_cl_jsr)
    LinearLayout lin_cl_jsr;
    @Override
    public int getLayoutId() {
        return R.layout.activity_dispatch_car;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }
    EmptyOrderBean orderdetailsBean;
    @Override
    public void initView() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        titleBarTitle.setText("调派车辆");
        tv_cancle.setVisibility(View.GONE);
        tv_sure.setVisibility(View.VISIBLE);
        tv_sure.setText("立即派车");
        String order_details=this.getIntent().getStringExtra("order_details");
        if(!order_details.equals("")) {
            orderdetailsBean = new Gson().fromJson(order_details, EmptyOrderBean.class);
            if(orderdetailsBean.getDispatch_Model()!=null){
                pclx=String.valueOf(orderdetailsBean.getDispatch_Model().getAssignsType());
                if(pclx.equals("0")){
                    pclx="20";
                }

                if(orderdetailsBean.getDispatch_Model().getAssignsType()==10){//抢单
                    tv_pclx.setText("抢单");
                    lin_cl_jsr.setVisibility(View.GONE);
                }else if(orderdetailsBean.getDispatch_Model().getAssignsType()==20){//接单
                    tv_pclx.setText("指派");
                    lin_cl_jsr.setVisibility(View.VISIBLE);

                    if(orderdetailsBean.getDispatch_Grab_List()!=null&&orderdetailsBean.getDispatch_Grab_List().size()>0){
                        TextOrEditTextUtil.textStyleBoldUtil(tv_xzcl,orderdetailsBean.getDispatch_Grab_List().get(0).getGrabCarPlateNumber());
                        TextOrEditTextUtil.textStyleBoldUtil(tv_xzjsr,orderdetailsBean.getDispatch_Grab_List().get(0).getGrabStaffName());
                        carPositionBean=new CarPositionBean(orderdetailsBean.getDispatch_Grab_List().get(0).getGrabCarId(),orderdetailsBean.getDispatch_Grab_List().get(0).getGrabCarPlateNumber(),orderdetailsBean.getDispatch_Grab_List().get(0).getGrabCarCompanyId(),orderdetailsBean.getDispatch_Grab_List().get(0).getGrabCarDepartmentId());
                        dispatchDriverListBean=new DispatchDriverListBean(orderdetailsBean.getDispatch_Grab_List().get(0).getGrabStaffName(),orderdetailsBean.getDispatch_Grab_List().get(0).getGrabUserCompanyId(),orderdetailsBean.getDispatch_Grab_List().get(0).getGrabUserDepartmentId(),orderdetailsBean.getDispatch_Grab_List().get(0).getGrabUserId(),orderdetailsBean.getDispatch_Grab_List().get(0).getGrabStaffId());
                    }
                }

            }else{
                pclx="20";
            }
        }
//        tv_yclx.setText(orderdetailsBean.getUseCarTypeName());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除所有的粘性事件
        EventBus.getDefault().removeAllStickyEvents();
        //解除注册
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.title_bar_back,R.id.rel_cl,R.id.rel_jsr,R.id.rel_yclx,R.id.tv_sure,R.id.rel_pclx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                finish();
                break;
            case R.id.rel_cl:
                CheckCarActivity.goToPage(this,"调度选车","");
                break;
            case R.id.rel_jsr:
                CheckDriverActivity.goToPage(this);
                break;
            case R.id.rel_yclx:
                setMapBottom("用车类型");
                break;
            case R.id.rel_pclx:
               setMapBottom("派车类型");
                break;
            case R.id.tv_sure:
                if(pclx.equals("20")){//指派
                    if(carPositionBean==null){
                        ToastUtil.showToast("请选择车辆。");
                        return;
                    }

                    if(dispatchDriverListBean==null){
                        ToastUtil.showToast("请选择驾驶人。");
                        return;
                    }
                }
                AlertDialogUtils.showAlertDialog(this,"确认是否立即派车？",2,this);
                break;
        }
    }
    CarPositionBean carPositionBean;
    DispatchDriverListBean dispatchDriverListBean;
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(CarPositionBean carPositionBean) {
        this.carPositionBean=carPositionBean;
        TextOrEditTextUtil.textStyleBoldUtil(tv_xzcl,carPositionBean.getPlateNumber());
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(DispatchDriverListBean dispatchDriverListBean) {
        this.dispatchDriverListBean=dispatchDriverListBean;
        TextOrEditTextUtil.textStyleBoldUtil(tv_xzjsr,dispatchDriverListBean.getName());
    }

    OrderSelectBottomControlPanel bottomControlPanel;
    public void setMapBottom(String type) {
        bottomControlPanel.mtype=type;
        if (bottomControlPanel == null) {
            bottomControlPanel = OrderSelectBottomControlPanel.newInstance("",this,this);
        }
        bottomControlPanel.show(lin_sel);
    }
    public static void goToPage(Activity activity,String order_details) {
        Intent intent = new Intent(activity, DispatchCarActivity.class);
        intent.putExtra("order_details",order_details);
        activity.startActivity(intent);
    }

    @Override
    public void returnEmptyOrderBean(EmptyOrderBean emptyOrderBean) {
    }

    @Override
    public void returnAddOrderBean(OrderSuccessBean emptyOrderBean) {

    }

    @Override
    public void returnOtherUserIdBean(OtherUserId otherUserId) {

    }

    @Override
    public void returnFailBean(String message, String type) {
        stopProgressDialog();
        EventBus.getDefault().postSticky("刷新列表");
        if(type.equals("调派车辆")){
            ToastUtil.showToast("成功调派车辆。");
            finish();
        }
    }

    @Override
    public void returnOrderIsOrdering(IsOrderingBean message) {

    }

    @Override
    public void returnStaffMagState(Message2Bean MessageBean) {

    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {
        stopProgressDialog();
    }

    @Override
    public void onwc(String mtype, String neir1, String neir2) {

    }

    String yclx="",yclx_lcid="",pclx="";
    @Override
    public void onTimeSelectWc(String mtype, CarPlateTypeListBean carPlateTypeListBean) {
        switch (mtype){
            case "用车类型"://用车类型如果IsWillCarPlate这个字段显示的是true，则显示意向车牌号的输入。
                tv_yclx.setText(carPlateTypeListBean.getName());
                //选择的用车类型和流程ID
                yclx=carPlateTypeListBean.getId();
                yclx_lcid=carPlateTypeListBean.getCarFlow_CarPlateTypeId();
                break;
            case "派车类型":
                tv_pclx.setText(carPlateTypeListBean.getName());
                pclx=carPlateTypeListBean.getCarFlow_CarPlateTypeId();
                if(carPlateTypeListBean.getCarFlow_CarPlateTypeId().equals("10")){
                    lin_cl_jsr.setVisibility(View.GONE);
                }else{
                    lin_cl_jsr.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public void onTabSelect(String mtype, CarPlateTypeListBean carPlateTypeListBean) {

    }

    @Override
    public void ondialogwc(String mtype, String neir1, String neir2) {
        if(mtype.equals("确认是否立即派车？")){
            startProgressDialog();
            JsonArray mMarklist= new JsonArray();
            if(pclx.equals("20")){
                JsonObject objectInList = new JsonObject();
                objectInList.addProperty("GrabUserCompanyId",dispatchDriverListBean.getCompanyId());
                objectInList.addProperty("GrabUserDepartmentId",dispatchDriverListBean.getDepartmentId());
                objectInList.addProperty("GrabStaffId",dispatchDriverListBean.getStaffId());
                objectInList.addProperty("GrabUserId",dispatchDriverListBean.getUserId());
                objectInList.addProperty("GrabCarCompanyId",carPositionBean.getCompanyId());
                objectInList.addProperty("GrabCarDepartmentId",carPositionBean.getDepartmentId());
                objectInList.addProperty("GrabCarId",carPositionBean.getCarId());
                Log.d("TAG",new Gson().toJson(carPositionBean)+"-----"+new Gson().toJson(dispatchDriverListBean));
                mMarklist.add(objectInList);
            }
            mPresenter.getOrderDispatchAuditBeans(orderdetailsBean.getCarFlow_OrderId(),Integer.valueOf(getResources().getString(R.string.type3_2)),"",mMarklist,"调派车辆",pclx);
        }
    }
}
