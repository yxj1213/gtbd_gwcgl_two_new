package com.ttce.vehiclemanage.ui.main.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.commonwidget.StatusBarCompat;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.AlipayBean;
import com.ttce.vehiclemanage.bean.DaiBanListBean;
import com.ttce.vehiclemanage.bean.DepartmentByCompanyIdBean;
import com.ttce.vehiclemanage.bean.DictTypeListBean;
import com.ttce.vehiclemanage.bean.HomeOrderBean;
import com.ttce.vehiclemanage.bean.IdCareBean;
import com.ttce.vehiclemanage.bean.IsHasWorkBeanchBean;
import com.ttce.vehiclemanage.bean.Message2Bean;
import com.ttce.vehiclemanage.bean.NewUserInfoBean;
import com.ttce.vehiclemanage.bean.UserInfoBean;
import com.ttce.vehiclemanage.bean.WorkBeanchBean;
import com.ttce.vehiclemanage.ui.main.activity.my.order.MyOrderActivity;
import com.ttce.vehiclemanage.ui.main.activity.workbean.jcxx.ygxx.InformationAuditActivity;
import com.ttce.vehiclemanage.ui.main.adapter.WorkBeanchAdapter;
import com.ttce.vehiclemanage.ui.mine.constract.PersonDetailConstract;
import com.ttce.vehiclemanage.ui.mine.model.PersonDetailModel;
import com.ttce.vehiclemanage.ui.mine.presenter.PersonDetailPresenter;
import com.ttce.vehiclemanage.ui.usermanage.manager.UserManager;
import com.ttce.vehiclemanage.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class WorkBeanchFragment  extends BaseFragment<PersonDetailPresenter, PersonDetailModel> implements PersonDetailConstract.View{

    @Bind(R.id.recycler_view)
    IRecyclerView recycler_view;
    @Bind(R.id.recycler_view_jcxx)
    IRecyclerView recycler_view_jcxx;
    @Bind(R.id.fra_no_data)
    FrameLayout fra_no_data;
    @Bind(R.id.lin_ycgl)
    LinearLayout lin_ycgl;
    @Bind(R.id.lin_jcxx)
    LinearLayout lin_jcxx;
    public boolean nowIsShow;
    WorkBeanchAdapter workBeanchAdapter,workBeanchAdapter2;
    public void setShow(boolean show) {
        nowIsShow = show;
        if(show==true){
            if(!UserManager.getLoginBean().getCompanyId().equals("")&&!UserManager.getLoginBean().getCompanyId().equals("00000000-0000-0000-0000-000000000000"))//代表不在当前在个人下
            {
                if(mPresenter!=null){
                    mPresenter.CarFlowOrderNeedToDoPresenter();
                    lin_jcxx.setVisibility(View.VISIBLE);
                    lin_ycgl.setVisibility(View.VISIBLE);
                    fra_no_data.setVisibility(View.GONE);
                }
            }else{
                lin_jcxx.setVisibility(View.GONE);
                lin_ycgl.setVisibility(View.GONE);
                fra_no_data.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(nowIsShow==true&&!UserManager.getLoginBean().getCompanyId().equals("")&&!UserManager.getLoginBean().getCompanyId().equals("00000000-0000-0000-0000-000000000000"))//代表不在当前在个人下
        {
            mPresenter.CarFlowOrderNeedToDoPresenter();
        }
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_work_beanch;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {

        StatusBarCompat.setStatusBarColor(getActivity(), ContextCompat.getColor(getContext(), R.color.app_main_colors));
        workBeanchAdapter = new WorkBeanchAdapter(getContext(), R.layout.fragment_work_beanch_item, recyclerViewData());
        workBeanchAdapter.openLoadAnimation(new ScaleInAnimation());
        recycler_view.setLayoutManager(new GridLayoutManager(getContext(),3));
        recycler_view.setAdapter(workBeanchAdapter);
        workBeanchAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                        /**
                         * 我的订单 = 1000,
                         *  订单审批 = 2000,
                         *     调度派车 = 3000,
                         *      司机接单 = 4000,
                         *        财务结算 = 5000
                         * */
                        String OrderModule="";
                        switch (position){
                            case 0:
                                OrderModule="2000";
                                break;
                            case 1:
                                OrderModule="3000";
                                break;
                            case 2:
                                OrderModule="4000";
                                break;
                            case 3:
                                OrderModule="5000";
                                break;
                            default:
                                break;
                        }
                        if(UserManager.getLoginBean().getStaffId().equals("")){
                            ToastUtil.showToast("暂无权限");
                        }else{
                            mPresenter.IsHasPrivilegePresenter(OrderModule,position,"用车管理");
                        }

            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });





        workBeanchAdapter2 = new WorkBeanchAdapter(getContext(), R.layout.fragment_work_beanch_item, recyclerViewData_jcxx());
        workBeanchAdapter2.openLoadAnimation(new ScaleInAnimation());
        recycler_view_jcxx.setLayoutManager(new GridLayoutManager(getContext(),3));
        recycler_view_jcxx.setAdapter(workBeanchAdapter2);
        workBeanchAdapter2.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                if(position==0){
                     if(UserManager.getLoginBean().getStaffId().equals("")){
                          ToastUtil.showToast("暂无权限");
                     }else{
                         mPresenter.IsHasPrivilegePresenter("6000",position,"基础信息");
                     }
                }
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }

    List<WorkBeanchBean> mlist=new ArrayList<>();
    public List<WorkBeanchBean> recyclerViewData(){
        mlist.clear();
        mlist=new ArrayList<>();
        mlist.add(new WorkBeanchBean(R.mipmap.icon_work_one,getResources().getString(R.string.me_txt9)));
        mlist.add(new WorkBeanchBean(R.mipmap.icon_work_two,getResources().getString(R.string.me_txt8)));
        mlist.add(new WorkBeanchBean(R.mipmap.icon_work_three,getResources().getString(R.string.me_txt13)));
       // mlist.add(new WorkBeanchBean(R.mipmap.icon_work_four,getResources().getString(R.string.need_car_0_1)));
        return mlist;
    }
    List<WorkBeanchBean> mlist_jcxx=new ArrayList<>();
    public List<WorkBeanchBean> recyclerViewData_jcxx(){
        mlist_jcxx.clear();
        mlist_jcxx=new ArrayList<>();
        mlist_jcxx.add(new WorkBeanchBean(R.mipmap.icon_work_five,getResources().getString(R.string.need_car_1_1)));
//        mlist_jcxx.add(new WorkBeanchBean(R.mipmap.icon_work_two,getResources().getString(R.string.need_car_1_2)));
//        mlist_jcxx.add(new WorkBeanchBean(R.mipmap.icon_work_one,getResources().getString(R.string.need_car_1_3)));
        return mlist_jcxx;
    }
    @Override
    public void getUserInfo(UserInfoBean userInfoBean) {

    }

    @Override
    public void getImgUrl(String imgUrl) {

    }

    @Override
    public void returnIsHasPrivilege(IsHasWorkBeanchBean isHasWorkBeanchBean, int position,String type) {
        if(isHasWorkBeanchBean.isIsHasPrivilege()==true){
            if(type.equals("用车管理")){
                MyOrderActivity.goToPage(getActivity(),position);
            }else if(type.equals("基础信息")){
                InformationAuditActivity.goToPage(getActivity(),position);
            }
        }else{
            ToastUtil.showToast("暂无此权限。");
        }
    }

    @Override
    public void returnStaffMagState(Message2Bean MessageBean) {

    }

    @Override
    public void returnBusinessStaff(NewUserInfoBean MessageBean) {

    }

    @Override
    public void returnBusinessStaffAdd(String str) {

    }

    @Override
    public void returnPostDictTypeList(List<DictTypeListBean> str) {

    }

    @Override
    public void returnPostDepartmentByCompanyId(List<DepartmentByCompanyIdBean> str) {

    }

    @Override
    public void returnCarFlowOrderNeedToDo(List<DaiBanListBean> str) {
        if(str!=null&&str.size()>0){
            mlist.get(0).setTitleid(String.valueOf(str.get(0).getCountItems()));
            mlist.get(1).setTitleid(String.valueOf(str.get(1).getCountItems()));
            mlist.get(2).setTitleid(String.valueOf(str.get(2).getCountItems()));
//          mlist.get(3).setTitleid(String.valueOf(str.get(3).getCountItems()));

            mlist_jcxx.get(0).setTitleid(String.valueOf(str.get(4).getCountItems()));
            workBeanchAdapter.notifyDataSetChanged();
            workBeanchAdapter2.notifyDataSetChanged();
        }
    }

    @Override
    public void returnPostPreconsult(AlipayBean str) {

    }

    @Override
    public void returnPostConsult(IdCareBean str) {

    }

    @Override
    public void returnErrPostConsult(String str) {

    }

    @Override
    public void returnCarFlowOrder_Index_Statistics(HomeOrderBean homeOrderBean) {

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
}
