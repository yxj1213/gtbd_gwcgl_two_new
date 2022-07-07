package com.ttce.vehiclemanage.ui.main.activity.workbean.ycxx.diaodu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.CarPlateTypeListBean;
import com.ttce.vehiclemanage.bean.CarPositionBean;
import com.ttce.vehiclemanage.bean.DispatchDriverListBean;
import com.ttce.vehiclemanage.ui.main.adapter.needcar.NeedCarTabTypeAdapter;
import com.ttce.vehiclemanage.ui.main.contract.dispatch.DispatchCarContract;
import com.ttce.vehiclemanage.ui.main.model.dispatch.DispatchCarModel;
import com.ttce.vehiclemanage.ui.main.presenter.dispatch.DispatchCarPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class CheckDriverActivity extends BaseActivity<DispatchCarPresenter, DispatchCarModel> implements DispatchCarContract.View{
    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.listview)
    ListView listview;
    @Bind(R.id.tv_sure)
    TextView tv_sure;
    @Bind(R.id.tv_cancle)
    TextView tv_cancle;
    @Bind(R.id.lin_no_data)
    LinearLayout lin_no_data;
    @Override
    public int getLayoutId() {
        return R.layout.recyleview;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        titleBarTitle.setText("选择驾驶人");
        tv_cancle.setVisibility(View.GONE);
        tv_sure.setText("确定");
        mPresenter.getDriverBeanPresenter();
    }

    @OnClick({R.id.title_bar_back,R.id.tv_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                finish();
                break;
            case R.id.tv_sure:
                EventBus.getDefault().postSticky(new DispatchDriverListBean(driverselectBrand.getName(),driverselectBrand.getCarFlow_CarPlateTypeId(),driverselectBrand.getId(),driverselectBrand.getUpdateByUserName(),driverselectBrand.getUpdateBy()));
                finish();
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    public static void goToPage(Activity activity) {
        Intent intent = new Intent(activity, CheckDriverActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void returnOrderCarList(List<CarPositionBean> carPositionBeans) {

    }

    private int driverselectPosition =0;//用于记录用户选择的变量
    private CarPlateTypeListBean driverselectBrand ;//用于记录用户选择的对象
    @Override
    public void returnDriverList(List<DispatchDriverListBean> dispatchDriverListBeans) {
        if(dispatchDriverListBeans==null||dispatchDriverListBeans.size()==0){
            tv_sure.setAlpha(0.4f);//当前有订单时，按钮修改为40%透明度。
            tv_sure.setClickable(false);
        }else{
            tv_sure.setAlpha(1f);//当前有订单时，按钮修改为40%透明度。
            tv_sure.setClickable(true);

        }
        List<CarPlateTypeListBean> dispatchDrivermlist=new ArrayList<>();
        for (DispatchDriverListBean dispatchDriverListBean:dispatchDriverListBeans) {
            dispatchDrivermlist.add(new CarPlateTypeListBean(dispatchDriverListBean.getName()+" "+dispatchDriverListBean.getPhone(),dispatchDriverListBean.getCompanyId(),dispatchDriverListBean.getDepartmentId(),dispatchDriverListBean.getUserId(),dispatchDriverListBean.getStaffId()));
        }
        if(dispatchDrivermlist.size()>0){
            listview.setVisibility(View.VISIBLE);
            lin_no_data.setVisibility(View.GONE);
            driverselectBrand=dispatchDrivermlist.get(0);
            driverselectPosition=0;
        }else{
            lin_no_data.setVisibility(View.VISIBLE);
            listview.setVisibility(View.GONE);
        }
        NeedCarTabTypeAdapter myAdapter = new NeedCarTabTypeAdapter(this, dispatchDrivermlist,driverselectPosition,"选择驾驶人");
        listview.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new NeedCarTabTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CarPlateTypeListBean carPlateTypeListBean, int selectPosition) {
                driverselectBrand=carPlateTypeListBean;
                driverselectPosition=selectPosition;
            }

            @Override
            public void onMRClick(CarPlateTypeListBean changeCompanyBean) {

            }
        });
    }

    @Override
    public void returnOrderDriverGrab(String carPositionBeans) {

    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {
        ToastUitl.showToastWithImg(msg, R.drawable.ic_wrong);
    }
}
