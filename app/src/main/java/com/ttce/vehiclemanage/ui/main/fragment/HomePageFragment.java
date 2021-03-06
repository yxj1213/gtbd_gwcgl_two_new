package com.ttce.vehiclemanage.ui.main.fragment;

import android.graphics.Outline;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonutils.DisplayUtil;
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
import com.ttce.vehiclemanage.ui.main.activity.home.ToDoListActivity;
import com.ttce.vehiclemanage.ui.mine.constract.PersonDetailConstract;
import com.ttce.vehiclemanage.ui.mine.model.PersonDetailModel;
import com.ttce.vehiclemanage.ui.mine.presenter.PersonDetailPresenter;
import com.ttce.vehiclemanage.ui.usermanage.manager.UserManager;
import com.ttce.vehiclemanage.utils.ToastUtil;
import com.ttce.vehiclemanage.widget.GlideImageLoaders;
import com.ttce.vehiclemanage.widget.linearlayout.ShadowLayout;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class HomePageFragment  extends BaseFragment<PersonDetailPresenter, PersonDetailModel> implements PersonDetailConstract.View {

    public boolean nowIsShow;
    @Bind(R.id.tv_time)
    TextView tv_time;
     @Bind(R.id.tv_number)
    TextView tv_number;
   @Bind(R.id.tv_thisday)
    TextView tv_thisday;
   @Bind(R.id.tv_num_all)
    TextView tv_num_all;
    @Bind(R.id.tv_num_pcz)
    TextView tv_num_pcz;
    @Bind(R.id.tv_num_cxz)
    TextView tv_num_cxz;
    @Bind(R.id.tv_num_finish)
    TextView tv_num_finish;
   @Bind(R.id.banner)
   Banner banner;
   @Bind(R.id.shadowLayout_bottom)
   ShadowLayout shadowLayout_bottom;
    public void setShow(boolean show) {
        nowIsShow = show;
        if(show==true){
            if(mPresenter!=null){
                if(!UserManager.getLoginBean().getCompanyId().equals("")&&!UserManager.getLoginBean().getCompanyId().equals("00000000-0000-0000-0000-000000000000"))//??????????????????????????????
                {
                    tv_thisday.setText("????????????");
                    selecttag=0;
                    mPresenter.CarFlowOrderNeedToDoPresenter();
                    mPresenter.CarFlowOrder_Index_StatisticsPresenter("10");
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(nowIsShow==true&&!UserManager.getLoginBean().getCompanyId().equals("")&&!UserManager.getLoginBean().getCompanyId().equals("00000000-0000-0000-0000-000000000000"))//??????????????????????????????
        {
            mPresenter.CarFlowOrderNeedToDoPresenter();
            mPresenter.CarFlowOrder_Index_StatisticsPresenter("10");
        }
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutResource() {
          return R.layout.fragment_home_page;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initView() {

        List<Integer> images=new ArrayList<>();
        images.add(R.mipmap.banner1_new);
        images.add(R.mipmap.banner2);
        images.add(R.mipmap.banner3);
        banner.setImages(images).setImageLoader(new GlideImageLoaders()).start();
        banner.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0,0,view.getWidth(),view.getHeight(),15);
            }
        });
        banner.setDelayTime(5000);
        banner.setClipToOutline(true);


        StatusBarCompat.setStatusBarColor(getActivity(), ContextCompat.getColor(getContext(), R.color.app_main_colors));
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        tv_time.setText(year+"-"+month+"-"+day);


    }
    PopupWindow popupWindow;
    int selecttag=0;
    @OnClick({R.id.rel_daiban,R.id.lin_thisday})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rel_daiban:
                if(!UserManager.getLoginBean().getCompanyId().equals("")&&!UserManager.getLoginBean().getCompanyId().equals("00000000-0000-0000-0000-000000000000"))//??????????????????????????????
                {
                    if(Integer.valueOf(tv_number.getText().toString().trim())>0){
                        startActivity(ToDoListActivity.class);
                    }else{
                        ToastUtil.showToast("?????????????????????");
                    }
                }else{
                    ToastUtil.showToast("?????????????????????");
                }
                break;
            case R.id.lin_thisday:
                if(!UserManager.getLoginBean().getCompanyId().equals("")&&!UserManager.getLoginBean().getCompanyId().equals("00000000-0000-0000-0000-000000000000"))//??????????????????????????????
                {
                    shadowLayout_bottom.bgColor = getResources().getColor(R.color.color_00);
                    shadowLayout_bottom.setAlpha(0.4f);
                    // ??????PopupWindow??????,200,LayoutParams.MATCH_PARENT????????????????????????
                    View popupWindow_view = LinearLayout.inflate(getActivity(), R.layout.popwindow_home_select, null);
                    popupWindow = new PopupWindow(popupWindow_view,DisplayUtil.dip2px(110), LinearLayout.LayoutParams.WRAP_CONTENT, true);
                    // ???????????????????????????activity_popupwindow_left.xml?????????
                    TextView tv = popupWindow_view.findViewById(R.id.tv);
                    TextView txt = popupWindow_view.findViewById(R.id.tv_1);
                    TextView tv_3 = popupWindow_view.findViewById(R.id.tv_3);
                    TextView tv_7 = popupWindow_view.findViewById(R.id.tv_7);
                    TextView tv_30 = popupWindow_view.findViewById(R.id.tv_30);

                    if (selecttag == 0) {
                        tv.setTextColor(getResources().getColor(R.color.color_10));
                    } else if (selecttag == 1) {
                        txt.setTextColor(getResources().getColor(R.color.color_10));
                    } else if (selecttag == 2) {
                        tv_3.setTextColor(getResources().getColor(R.color.color_10));
                    } else if (selecttag == 3) {
                        tv_7.setTextColor(getResources().getColor(R.color.color_10));
                    } else if (selecttag == 4) {
                        tv_30.setTextColor(getResources().getColor(R.color.color_10));
                    }
                    //10?????????20?????????30???????????????31???????????????40???????????????50?????????60?????????70?????????80?????????99?????????
                    txtOnclick(tv, 0, "10", popupWindow);
                    txtOnclick(txt, 1, "20", popupWindow);
                    txtOnclick(tv_3, 2, "31", popupWindow);
                    txtOnclick(tv_7, 3, "50", popupWindow);
                    txtOnclick(tv_30, 4, "70", popupWindow);
                    //??????????????????
                    popupWindow.setOutsideTouchable(true);
                    popupWindow.setFocusable(true);
                    //?????????????????????popupwindow
                    popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                    popupWindow.showAsDropDown(tv_thisday, DisplayUtil.dip2px(-20), 0);

                    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            shadowLayout_bottom.bgColor = getResources().getColor(R.color.white);
                            shadowLayout_bottom.setAlpha(1f);
                        }
                    });
                }else{
                    ToastUtil.showToast("???????????????????????????,??????????????????????????????.");
                }
                break;
        }
    }

    public void txtOnclick(TextView txt,int tag,String type,PopupWindow popupWindow){
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecttag=tag;
                tv_thisday.setText(txt.getText().toString().trim()+"??????");
                mPresenter.CarFlowOrder_Index_StatisticsPresenter(type);
                popupWindow.dismiss();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if(popupWindow!=null){
            popupWindow.dismiss();
        }
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
        int num=0;
        for (DaiBanListBean daibanlist:str) {
            if(daibanlist.getCountItems()>0){
                num=num+daibanlist.getCountItems();
            }
        }
        tv_number.setText(num+"");
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
        tv_num_all.setText(homeOrderBean.getTotalCount()+"");
        tv_num_pcz.setText(homeOrderBean.getAssignCount()+"");
        tv_num_cxz.setText(homeOrderBean.getUseCarCount()+"");
        tv_num_finish.setText(homeOrderBean.getCompletedCount()+"");
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
