package com.ttce.vehiclemanage.ui.main.activity.home;

import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.baseapp.AppManager;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.commonwidget.StatusBarCompat;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.app.AppConstant;
import com.ttce.vehiclemanage.bean.AlipayBean;
import com.ttce.vehiclemanage.bean.ChangeCompanyBean;
import com.ttce.vehiclemanage.bean.ChangeCompanySaveReturnBean;
import com.ttce.vehiclemanage.bean.CompanyTypeListBean;
import com.ttce.vehiclemanage.bean.LoginBean;
import com.ttce.vehiclemanage.bean.MessageEvent;
import com.ttce.vehiclemanage.ui.main.contract.home.HomeLeftContract;
import com.ttce.vehiclemanage.ui.main.fragment.MyNeedCarFragment;
import com.ttce.vehiclemanage.ui.main.model.home.HomeLeftModel;
import com.ttce.vehiclemanage.ui.main.presenter.home.HomeLeftPresenter;
import com.ttce.vehiclemanage.ui.usermanage.manager.UserManager;
import com.ttce.vehiclemanage.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ttce.vehiclemanage.ui.main.fragment.MyNeedCarFragment.isRef;

/**
 * 已修改
 */
public class CreateBusiness2Activity extends BaseActivity<HomeLeftPresenter, HomeLeftModel> implements HomeLeftContract.View {
    @Bind(R.id.edt_qymc)
    EditText edt_qymc;
    @Bind(R.id.edt_xm)
    EditText edt_xm;
    @Bind(R.id.edt_lxdh)
    EditText edt_lxdh;
    @Bind(R.id.edt_dzyx)
    EditText edt_dzyx;
    @Bind(R.id.title_bar_layout)
    RelativeLayout title_bar_layout;
    @Bind(R.id.img_back)
    ImageView img_back;
    @Bind(R.id.title_bar_title)
    TextView title_bar_title;
    @Bind(R.id.txt_big)
    TextView txt_big;
    @Bind(R.id.lin_create_business)
    LinearLayout lin_create_business;
    @Bind(R.id.lin_fzmc)
    LinearLayout lin_fzmc;
    @Bind(R.id.edt_fzmc)
    EditText edt_fzmc;
    public static void goToPage(Activity activity, String Business_CompanyTypeId,String Business_CompanyTypeName, String type) {
        Intent intent = new Intent(activity, CreateBusiness2Activity.class);
        intent.putExtra("Business_CompanyTypeId", Business_CompanyTypeId);
        intent.putExtra("Business_CompanyTypeName", Business_CompanyTypeName);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    @OnClick({R.id.title_bar_back, R.id.tv_sub})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:

                finish();
                break;
            case R.id.tv_sub:
                if(type.equals("分组")){
                    if (edt_fzmc.getText().toString().trim().equals("")) {
                        ToastUtil.showToast("请输入分组名称。");
                        return;
                    }
                    mPresenter.PostDepartmentAddPresenter(business_CompanyTypeId,edt_fzmc.getText().toString().trim());
                }else{
                    if (edt_qymc.getText().toString().trim().equals("")) {
                        ToastUtil.showToast("请输入企业名称。");
                        return;
                    }
                    mPresenter.getbusinessComPanyAddModelPresenter(edt_qymc.getText().toString().trim(), edt_xm.getText().toString().trim(), edt_lxdh.getText().toString().trim(), edt_dzyx.getText().toString().trim(), business_CompanyTypeId);
                }
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.mainleft_create_business2;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    String business_CompanyTypeId,Business_CompanyTypeName, type;

    @Override
    public void initView() {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.app_main_colors));
        title_bar_layout.setBackground(getResources().getDrawable(R.drawable.titlebar));
        img_back.setImageResource(R.mipmap.icon_back_white);

        title_bar_title.setTextColor(getResources().getColor(R.color.white));


        business_CompanyTypeId = this.getIntent().getStringExtra("Business_CompanyTypeId");
        Business_CompanyTypeName = this.getIntent().getStringExtra("Business_CompanyTypeName");
        type = this.getIntent().getStringExtra("type");
        if(type.equals("分组")){
            txt_big.setText("全新创建分组");
            lin_create_business.setVisibility(View.GONE);
            lin_fzmc.setVisibility(View.VISIBLE);
            title_bar_title.setText("创建分组");
            edt_qymc.setText(Business_CompanyTypeName);
            edt_qymc.setEnabled(false);
            edt_qymc.setClickable(false);
        }else{
            title_bar_title.setText("创建企业");
            txt_big.setText(getResources().getString(R.string.main_left9));
            lin_create_business.setVisibility(View.VISIBLE);
            lin_fzmc.setVisibility(View.GONE);
        }
    }

    @Override
    public void returnCompanyTypeList(List<CompanyTypeListBean> companyTypeListBeans) {

    }

    @Override
    public void returnbusinessComPanyAdd(String str) {
            ToastUtil.showToast("分组创建成功。");
            finish();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            MyNeedCarFragment.isRef=true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void returnbusinessComPanyAdd(ChangeCompanyBean str) {
        if(UserManager.getLoginBean().getCompanyId().equals("")||UserManager.getLoginBean().getCompanyId().equals("00000000-0000-0000-0000-000000000000"))//代表当前在个人选中的情况下创建企业
        {
            if(!str.getCompanyId().equals("")){
                mPresenter.getSaveChangeCompany(str.getCompanyId());
            }
        }else{
            ToastUtil.showToast("企业创建成功。");
            isRef=true;
            AppManager.getAppManager().preActivity().finish();
            finish();
        }

    }
    @Override
    public void getSaveChangeCompanys(ChangeCompanySaveReturnBean str) {
        LoginBean loginBean= UserManager.getLoginBean();
        loginBean.setCompanyId(str.getCompanyId());
        loginBean.setCompanyName(str.getCompanyName());
        UserManager.saveSerialize(new LoginBean(loginBean.getRealCheck(),loginBean.getToken(),loginBean.getUserPhone(),str.getCompanyId(),str.getCompanyName(),loginBean.getDepartMentId(),str.getUserId(),str.getStaffId(),loginBean.getUserRealName(),loginBean.getIdCard()));
        EventBus.getDefault().post(new MessageEvent(AppConstant.CHANGE_COMPANYID,"刷新"));

        ToastUtil.showToast("企业创建成功。");
        isRef=true;
        AppManager.getAppManager().preActivity().finish();
        finish();
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