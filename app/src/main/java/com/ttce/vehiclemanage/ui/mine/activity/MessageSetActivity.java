package com.ttce.vehiclemanage.ui.mine.activity;

import android.widget.CompoundButton;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.ExSysNoticeSetBean;
import com.ttce.vehiclemanage.ui.mine.constract.MessageSetConstract;
import com.ttce.vehiclemanage.ui.mine.model.MessageSetModel;
import com.ttce.vehiclemanage.ui.mine.presenter.MessageSetPresenter;
import com.ttce.vehiclemanage.utils.AppPreferenceSetting;
import com.ttce.vehiclemanage.widget.NewSwitchButton;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 消息设置
 * Created by hk on 2019/7/10.
 */

public class MessageSetActivity extends BaseActivity<MessageSetPresenter, MessageSetModel> implements MessageSetConstract.View, CompoundButton.OnCheckedChangeListener {

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.sb_gjjs)
    NewSwitchButton sbGjjs;
    @Bind(R.id.sb_zdbj)
    NewSwitchButton sbZdbj;
    @Bind(R.id.sb_lxgj)
    NewSwitchButton sbLxgj;
    @Bind(R.id.sb_cwxmqbj)
    NewSwitchButton sbCwxmqbj;
    @Bind(R.id.sb_nbdcddbj)
    NewSwitchButton sbNbdcddbj;
    @Bind(R.id.sb_ddbj)
    NewSwitchButton sbDdbj;
    @Bind(R.id.sb_jwxmqbj)
    NewSwitchButton sbJwxmqbj;
    @Bind(R.id.sb_ddgj)
    NewSwitchButton sbDdgj;
    @Bind(R.id.sb_plbj)
    NewSwitchButton sbPlbj;
    @Bind(R.id.sb_accsf)
    NewSwitchButton sbAccsf;
    @Bind(R.id.sb_sosbj)
    NewSwitchButton sbSosbj;
    @Bind(R.id.sb_csbj)
    NewSwitchButton sbCsbj;
    @Bind(R.id.sb_srwlgj)
    NewSwitchButton sbSrwlgj;
    @Bind(R.id.sb_scwlgj)
    NewSwitchButton sbScwlgj;

    @Override
    public int getLayoutId() {
        return R.layout.activity_message_set;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        titleBarTitle.setText("消息设置");
        sbGjjs.setOnCheckedChangeListener(this);
        sbZdbj.setOnCheckedChangeListener(this);
        sbLxgj.setOnCheckedChangeListener(this);
        sbCwxmqbj.setOnCheckedChangeListener(this);
        sbNbdcddbj.setOnCheckedChangeListener(this);
        sbDdbj.setOnCheckedChangeListener(this);
        sbJwxmqbj.setOnCheckedChangeListener(this);
        sbDdgj.setOnCheckedChangeListener(this);
        sbPlbj.setOnCheckedChangeListener(this);
        sbAccsf.setOnCheckedChangeListener(this);
        sbSosbj.setOnCheckedChangeListener(this);
        sbCsbj.setOnCheckedChangeListener(this);
        sbSrwlgj.setOnCheckedChangeListener(this);
        sbScwlgj.setOnCheckedChangeListener(this);
        mPresenter.getMessageSet();
        initSettingBtnStatus();
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
    public void getMessageSet(ExSysNoticeSetBean exSysNoticeSetBean) {
        if (exSysNoticeSetBean.getGaoJingJieShou() == 0) {
            sbGjjs.setChecked(false);
        } else {
            sbGjjs.setChecked(true);
        }
        if (exSysNoticeSetBean.getZhenDong() == 0) {
            sbZdbj.setChecked(false);
        } else {
            sbZdbj.setChecked(true);
        }
        if (exSysNoticeSetBean.getLiXian() == 0) {
            sbLxgj.setChecked(false);
        } else {
            sbLxgj.setChecked(true);
        }
        if (exSysNoticeSetBean.getChuWeiXingMangQu() == 0) {
            sbCwxmqbj.setChecked(false);
        } else {
            sbCwxmqbj.setChecked(true);
        }
        if (exSysNoticeSetBean.getDianChiDiDian() == 0) {
            sbNbdcddbj.setChecked(false);
        } else {
            sbNbdcddbj.setChecked(true);
        }
        if (exSysNoticeSetBean.getDuanDian() == 0) {
            sbDdbj.setChecked(false);
        } else {
            sbDdbj.setChecked(true);
        }
        if (exSysNoticeSetBean.getJinWeiXingMangQu() == 0) {
            sbJwxmqbj.setChecked(false);
        } else {
            sbJwxmqbj.setChecked(true);
        }
        if (exSysNoticeSetBean.getDiDian() == 0) {
            sbDdgj.setChecked(false);
        } else {
            sbDdgj.setChecked(true);
        }
        if (exSysNoticeSetBean.getPiLao() == 0) {
            sbPlbj.setChecked(false);
        } else {
            sbPlbj.setChecked(true);
        }
        if (exSysNoticeSetBean.getACC() == 0) {
            sbAccsf.setChecked(false);
        } else {
            sbAccsf.setChecked(true);
        }
        if (exSysNoticeSetBean.getChaoSu() == 0) {
            sbCsbj.setChecked(false);
        } else {
            sbCsbj.setChecked(true);
        }
        if (exSysNoticeSetBean.getShiRuWeiLan() == 0) {
            sbSrwlgj.setChecked(false);
        } else {
            sbSrwlgj.setChecked(true);
        }
        if (exSysNoticeSetBean.getShiChuWeiLan() == 0) {
            sbScwlgj.setChecked(false);
        } else {
            sbScwlgj.setChecked(true);
        }
        if (exSysNoticeSetBean.getWeiYi() == 0) {
            sbSosbj.setChecked(false);
        } else {
            sbSosbj.setChecked(true);
        }
    }

    @Override
    public void updateMessage(String message) {
        mPresenter.getMessageSet();
    }

    @OnClick(R.id.title_bar_back)
    public void onViewClicked() {
        finish();
    }

    private void initSettingBtnStatus() {
        sbGjjs.setChecked(AppPreferenceSetting.getAlarmMsgReceive());
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.sb_gjjs:
                AppPreferenceSetting.setAlarmMsgReceive(isChecked);
                mPresenter.updateMessage("GaoJingJieShou", isChecked ? 1 : 0);
                break;
            case R.id.sb_zdbj:
                mPresenter.updateMessage("ZhenDong", isChecked ? 1 : 0);
                break;
            case R.id.sb_lxgj:
                mPresenter.updateMessage("LiXian", isChecked ? 1 : 0);
                break;
            case R.id.sb_cwxmqbj:
                mPresenter.updateMessage("ChuWeiXingMangQu", isChecked ? 1 : 0);
                break;
            case R.id.sb_nbdcddbj:
                mPresenter.updateMessage("DianChiDiDian", isChecked ? 1 : 0);
                break;
            case R.id.sb_ddbj:
                mPresenter.updateMessage("DuanDian", isChecked ? 1 : 0);
                break;
            case R.id.sb_jwxmqbj:
                mPresenter.updateMessage("JinWeiXingMangQu", isChecked ? 1 : 0);
                break;
            case R.id.sb_ddgj:
                mPresenter.updateMessage("DiDian", isChecked ? 1 : 0);
                break;
            case R.id.sb_plbj:
                mPresenter.updateMessage("PiLao", isChecked ? 1 : 0);
                break;
            case R.id.sb_accsf:
                mPresenter.updateMessage("ACC", isChecked ? 1 : 0);
                break;
            case R.id.sb_sosbj:
                mPresenter.updateMessage("WeiYi", isChecked ? 1 : 0);
                break;
            case R.id.sb_csbj:
                mPresenter.updateMessage("ChaoSu", isChecked ? 1 : 0);
                break;
            case R.id.sb_srwlgj:
                mPresenter.updateMessage("ShiRuWeiLan", isChecked ? 1 : 0);
                break;
            case R.id.sb_scwlgj:
                mPresenter.updateMessage("ShiChuWeiLan", isChecked ? 1 : 0);
                break;
        }
    }
}
