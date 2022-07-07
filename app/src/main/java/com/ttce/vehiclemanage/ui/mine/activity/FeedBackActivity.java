package com.ttce.vehiclemanage.ui.mine.activity;

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.google.android.flexbox.FlexWrap;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.CommonListBean;
import com.ttce.vehiclemanage.ui.mine.adapter.TypeItemAdapter;
import com.ttce.vehiclemanage.ui.mine.constract.AddFeedBackConstract;
import com.ttce.vehiclemanage.ui.mine.model.AddFeedBackModel;
import com.ttce.vehiclemanage.ui.mine.presenter.AddFeedBackPresenter;
import com.ttce.vehiclemanage.ui.usermanage.manager.UserManager;
import com.ttce.vehiclemanage.widget.MyFlexboxLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 意见反馈
 * Created by hk on 2019/6/24.
 */

public class FeedBackActivity extends BaseActivity<AddFeedBackPresenter, AddFeedBackModel>
        implements AddFeedBackConstract.View, OnItemClickListener<CommonListBean> {

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.irc1)
    IRecyclerView irc1;
    @Bind(R.id.et_content)
    EditText etContent;
    @Bind(R.id.et_person)
    EditText etPerson;
    @Bind(R.id.et_phone)
    EditText etPhone;

    private TypeItemAdapter typeItemAdapter;
    private List<CommonListBean> typeList = new ArrayList<>();

    private String ContentType;
    private String Content;
    private String LinkMan;
    private String LinkPhone;
    private String CompanyId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        titleBarTitle.setText("意见反馈");
        typeItemAdapter = new TypeItemAdapter(this, R.layout.item_wb_by_item, new ArrayList<>());
        typeItemAdapter.openLoadAnimation(new ScaleInAnimation());
        MyFlexboxLayoutManager layoutManager = new MyFlexboxLayoutManager(this);
        layoutManager.setFlexWrap(FlexWrap.WRAP); // 设置是否换行
        irc1.setLayoutManager(layoutManager);
        irc1.setAdapter(typeItemAdapter);
        typeItemAdapter.setOnItemClickListener(this);

        typeList.clear();
        typeList.add(new CommonListBean("追踪问题","01"));
        typeList.add(new CommonListBean("轨迹问题","02"));
        typeList.add(new CommonListBean("指令问题","03"));
        typeList.add(new CommonListBean("围栏问题","04"));
        typeList.add(new CommonListBean("告警问题","05"));
        typeList.add(new CommonListBean("我有疑问","06"));
        typeList.add(new CommonListBean("功能建议","07"));
        typeList.add(new CommonListBean("其他","08"));
        typeItemAdapter.replaceAll(typeList);
    }

    @OnClick({ R.id.title_bar_back, R.id.tv_submit })
    public void onViewClicked(View view) {
        switch (view.getId()) {
        case R.id.title_bar_back:
            finish();
            break;
        case R.id.tv_submit:
            Content = etContent.getText().toString();
            LinkMan = etPerson.getText().toString();
            LinkPhone = etPhone.getText().toString();
            CompanyId = UserManager.getLoginBean().getCompanyId();
            if(TextUtils.isEmpty(ContentType)){
                ToastUitl.showShort("请选择问题类型");
                return;
            }
            if (TextUtils.isEmpty(Content)) {
                ToastUitl.showShort("请输入内容");
                return;
            }
            if (TextUtils.isEmpty(LinkMan)) {
                ToastUitl.showShort("请输入联系人");
                return;
            }
            if (TextUtils.isEmpty(LinkPhone)) {
                ToastUitl.showShort("请输入手机号码");
                return;
            }
            mPresenter.addFeedBack(ContentType, Content, LinkMan, LinkPhone, CompanyId);
            break;
        }
    }

    private void setSelectedItemView(CommonListBean byItemBean) {
        for (CommonListBean item : typeList) {
            item.setSelected(false);
        }
        if (byItemBean == null) {
            typeList.get(0).setSelected(true);
        } else {
            int index = typeList.indexOf(byItemBean);
            if (index != -1) {
                typeList.get(index).setSelected(true);
            }
        }
        typeItemAdapter.replaceAll(typeList);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, CommonListBean commonListBean, int position) {
        ContentType=commonListBean.getKey();
        setSelectedItemView(commonListBean);
    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, CommonListBean commonListBean, int position) {
        return false;
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

    @Override
    public void isAdd(String message) {
        ToastUitl.showShort(TextUtils.isEmpty(message) ? "添加成功" : message);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },1000);
    }
}
