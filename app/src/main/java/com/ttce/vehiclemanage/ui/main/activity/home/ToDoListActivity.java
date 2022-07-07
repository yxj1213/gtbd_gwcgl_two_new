package com.ttce.vehiclemanage.ui.main.activity.home;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.jaydenxiao.common.base.BaseActivity;
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
import com.ttce.vehiclemanage.ui.main.adapter.WorkBeanchAdapter;
import com.ttce.vehiclemanage.ui.mine.constract.PersonDetailConstract;
import com.ttce.vehiclemanage.ui.mine.model.PersonDetailModel;
import com.ttce.vehiclemanage.ui.mine.presenter.PersonDetailPresenter;
import com.ttce.vehiclemanage.ui.usermanage.manager.UserManager;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class ToDoListActivity extends BaseActivity<PersonDetailPresenter, PersonDetailModel> implements PersonDetailConstract.View {

    @Bind(R.id.recycler_view)
    IRecyclerView recycler_view;
    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Override
    public int getLayoutId() {
        return R.layout.activity_todolist;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }
    WorkBeanchAdapter workBeanchAdapter;
    List<Integer> OrderEscapemlist=new ArrayList<>();
    @Override
    public void initView() {

        titleBarTitle.setText("待办事项");
        workBeanchAdapter = new WorkBeanchAdapter(this, R.layout.home_todolist_item, recyclerViewData());
        workBeanchAdapter.openLoadAnimation(new ScaleInAnimation());
        recycler_view.setLayoutManager(new GridLayoutManager(this,1));
        recycler_view.setAdapter(workBeanchAdapter);
        workBeanchAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                WorkBeanchBean workBeanchBean= (WorkBeanchBean) o;
                if(!workBeanchBean.getTitle().equals("6000")){
                    if(workBeanchBean.getTitle().equals(getResources().getString(R.string.type2))){//订单审批
                        OrderEscapemlist=new ArrayList<>();
                        OrderEscapemlist.add(Integer.valueOf(mContext.getString(R.string.type2_1)));
                    }else  if(workBeanchBean.getTitle().equals(getResources().getString(R.string.type3))){//调度派车
                        OrderEscapemlist=new ArrayList<>();
                        OrderEscapemlist.add(Integer.valueOf(mContext.getString(R.string.type3_1)));
                    }else  if(workBeanchBean.getTitle().equals(getResources().getString(R.string.type4))){//司机接单
                        OrderEscapemlist=new ArrayList<>();
                        OrderEscapemlist.add(Integer.valueOf(mContext.getString(R.string.type4_1)));
                        OrderEscapemlist.add(Integer.valueOf(mContext.getString(R.string.type4_5)));
                    }else  if(workBeanchBean.getTitle().equals("5000")){//财务结算

                    }
                    OrderToDoListActivity.goToPage(ToDoListActivity.this,workBeanchBean.getTitle(),OrderEscapemlist);
                }else  if(workBeanchBean.getTitle().equals("6000")){//员工基础信息审核
                    ShenHeToDoListActivity.goToPage(ToDoListActivity.this,"1");
                }

            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }
    @OnClick({R.id.title_bar_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!UserManager.getLoginBean().getCompanyId().equals("")&&!UserManager.getLoginBean().getCompanyId().equals("00000000-0000-0000-0000-000000000000"))//代表不在当前在个人下
        {
            startProgressDialog();
            mPresenter.CarFlowOrderNeedToDoPresenter();
        }
    }

    List<WorkBeanchBean> mlist=new ArrayList<>();
    public List<WorkBeanchBean> recyclerViewData(){
        mlist=new ArrayList<>();
//        mlist.add(new WorkBeanchBean(R.mipmap.icon_work_one,getResources().getString(R.string.me_txt9),"用车订单审批提醒"));
//        mlist.add(new WorkBeanchBean(R.mipmap.icon_work_two,getResources().getString(R.string.me_txt8),"调派车辆提醒"));
//        mlist.add(new WorkBeanchBean(R.mipmap.icon_work_three,getResources().getString(R.string.me_txt13),"接单/抢单提醒"));
//         mlist.add(new WorkBeanchBean(R.mipmap.icon_work_four,getResources().getString(R.string.need_car_0_1),"用车费用报销结算提醒"));
//         mlist.add(new WorkBeanchBean(R.mipmap.icon_work_five,getResources().getString(R.string.need_car_1_1),"员工基础信息审核提醒"));
        return mlist;
    }

    @Override
    public void getUserInfo(UserInfoBean userInfoBean) {

    }

    @Override
    public void getImgUrl(String imgUrl) {

    }

    @Override
    public void returnIsHasPrivilege(IsHasWorkBeanchBean isHasWorkBeanchBean, int position, String type) {

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
       stopProgressDialog();
        workBeanchAdapter.removeAll(mlist);
        for (DaiBanListBean daibanlistbean:str) {
            if(daibanlistbean.getCountItems()>0){
                mlist.add(new WorkBeanchBean(String.valueOf(daibanlistbean.getOrderModule()),String.valueOf(daibanlistbean.getNeedToDoTimeFormat()),String.valueOf(daibanlistbean.getCountItems())));
            }
        }
        workBeanchAdapter.notifyDataSetChanged();
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
        stopProgressDialog();
    }
}
