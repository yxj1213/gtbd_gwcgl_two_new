package com.ttce.vehiclemanage.ui.main.fragment.workbean.ycxx.diaodu;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.CarPlateTypeListBean;
import com.ttce.vehiclemanage.bean.CarPositionBean;
import com.ttce.vehiclemanage.bean.DispatchDriverListBean;
import com.ttce.vehiclemanage.bean.EmptyOrderBean;
import com.ttce.vehiclemanage.bean.WorkBeanchBean;
import com.ttce.vehiclemanage.ui.main.adapter.WorkBeanchAdapter;
import com.ttce.vehiclemanage.ui.main.adapter.needcar.NeedCarTabTypeAdapter;
import com.ttce.vehiclemanage.ui.main.contract.dispatch.DispatchCarContract;
import com.ttce.vehiclemanage.ui.main.model.dispatch.DispatchCarModel;
import com.ttce.vehiclemanage.ui.main.presenter.dispatch.DispatchCarPresenter;
import com.ttce.vehiclemanage.utils.ToastUtil;
import com.ttce.vehiclemanage.widget.linearlayout.ShadowLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 已修改
 * */
public class CheckCarFragment extends BaseFragment<DispatchCarPresenter, DispatchCarModel> implements DispatchCarContract.View,WorkBeanchAdapter.DelListener{

    @Bind(R.id.listview)
    ListView listview;
    @Bind(R.id.tv_sure)
    TextView tv_sure;
    @Bind(R.id.tv_cancle)
    TextView tv_cancle;
    @Bind(R.id.recycler_view)
    RecyclerView recycler_view;
    @Bind(R.id.fra_bottom_sure_cancel)
    ShadowLayout fra_bottom_sure_cancel;
  @Bind(R.id.lin_no_data)
  LinearLayout lin_no_data;

    public static CheckCarFragment newInstance(String type,String cllx,String xctype,String order_id) {
        CheckCarFragment checkCarFragment = new CheckCarFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putString("cllx", cllx);
        bundle.putString("xctype", xctype);
        bundle.putString("order_id", order_id);
        checkCarFragment.setArguments(bundle);
        return checkCarFragment;
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_check_car;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    String CarUseState="",xctype="",order_id="";
    @Override
    protected void initView() {
    }
    @Override
    protected void loadData() {
        CarUseState = getArguments().getString("type");
        xctype = getArguments().getString("xctype");
        order_id = getArguments().getString("order_id");
        if(CarUseState.equals("20")||CarUseState.equals("30")){
            tv_sure.setVisibility(View.GONE);
            fra_bottom_sure_cancel.setVisibility(View.GONE);
        }
        if(xctype.equals("调度选车")){
            tv_cancle.setVisibility(View.GONE);
            tv_sure.setText("确定");
        }else{
            tv_cancle.setVisibility(View.GONE);
            tv_sure.setText("立即接单");
        }
        cllxData();
    }
    @OnClick({R.id.tv_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sure:

                if(myclxselectBrand==null){
                    ToastUtil.showToast("请选择车辆。");
                    return;
                }
                if(xctype.equals("调度选车")){
                    EventBus.getDefault().postSticky(new CarPositionBean(myclxselectBrand.getCarFlow_CarPlateTypeId(),myclxselectBrand.getName(),myclxselectBrand.getId(),myclxselectBrand.getUpdateByUserName()));
                    getActivity().finish();
                }else{//司机接单
                    mPresenter.getgetOrderDriverGrabPresenter(order_id,myclxselectBrand.getId(),myclxselectBrand.getUpdateByUserName(),myclxselectBrand.getCarFlow_CarPlateTypeId());
                }
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    List<WorkBeanchBean> list=new ArrayList<>();
    List<EmptyOrderBean.CarStyleListBean> mlist=new ArrayList<>();
    private void cllxData() {
        String cllx = getArguments().getString("cllx");
        mlist = new Gson().fromJson(cllx, new TypeToken<List<EmptyOrderBean.CarStyleListBean>>() {}.getType());
        cllxselectPosition=0;
        myclxselectBrand=null;
        myclxselectPosition=-1;
         list=new ArrayList<>();
         if(mlist.size()>0){
             list.add(new WorkBeanchBean("","全部"));
             for (EmptyOrderBean.CarStyleListBean carStyleListBean:mlist) {
                 list.add(new WorkBeanchBean(carStyleListBean.getCarFlow_CarStyleId(),carStyleListBean.getName()));
             }
             // 横向设置 , 翻转
             LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
             //设置布局管理器
             recycler_view.setLayoutManager(layoutManager);
             ddzzworkBeanchAdapter = new WorkBeanchAdapter(getContext(), R.layout.fragment_my_need_ryxx2_item, list,"车辆类型",this);
             ddzzworkBeanchAdapter.openLoadAnimation(new ScaleInAnimation());
             recycler_view.setAdapter(ddzzworkBeanchAdapter);

             mPresenter.getCarPositionBeanPresenter(CarUseState,"");
         }
    }
    WorkBeanchAdapter ddzzworkBeanchAdapter;
    public static int cllxselectPosition =0;//用于记录用户选择的车辆类型
    private int myclxselectPosition =-1;//用于记录用户选择的变量
    private CarPlateTypeListBean myclxselectBrand ;//用于记录用户选择的对象

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

    @Override
    public void del(String mtype, int pos) {
         cllxselectPosition=pos;
         ddzzworkBeanchAdapter.notifyDataSetChanged();
         mPresenter.getCarPositionBeanPresenter(CarUseState,list.get(pos).getTitleid());
    }

    @Override
    public void returnOrderCarList(List<CarPositionBean> carPositionBeans) {
        if(carPositionBeans!=null&&carPositionBeans.size()>0){
            listview.setVisibility(View.VISIBLE);
            lin_no_data.setVisibility(View.GONE);
        }else{
            listview.setVisibility(View.GONE);
            lin_no_data.setVisibility(View.VISIBLE);
        }
        List<CarPlateTypeListBean> carpositionmlist=new ArrayList<>();
        for (CarPositionBean carPositionBean:carPositionBeans) {
            carpositionmlist.add(new CarPlateTypeListBean(carPositionBean.getPlateNumber(),carPositionBean.getCarId(),carPositionBean.getCompanyId(),carPositionBean.getDepartmentId(),carPositionBean.isUsable()));
        }
        NeedCarTabTypeAdapter myAdapter = new NeedCarTabTypeAdapter(getContext(), carpositionmlist,myclxselectPosition,"选择车辆");
        listview.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new NeedCarTabTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CarPlateTypeListBean carPlateTypeListBean, int selectPosition) {
                myclxselectBrand=carPlateTypeListBean;
                myclxselectPosition=selectPosition;
            }

            @Override
            public void onMRClick(CarPlateTypeListBean changeCompanyBean) {

            }
        });
    }

    @Override
    public void returnDriverList(List<DispatchDriverListBean> carPositionBeans) {

    }

    @Override
    public void returnOrderDriverGrab(String carPositionBeans) {
        ToastUtil.showToast("接单成功。");
        EventBus.getDefault().postSticky("刷新列表");
        getActivity().finish();
    }
}