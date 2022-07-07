package com.ttce.vehiclemanage.ui.usermanage.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.DisplayUtil;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.app.AppConstant;
import com.ttce.vehiclemanage.bean.ExRegistFirstStepBean;
import com.ttce.vehiclemanage.bean.ExVerificationBean;
import com.ttce.vehiclemanage.bean.MessageEvent;
import com.ttce.vehiclemanage.ui.mine.activity.ProtocolActivity;
import com.ttce.vehiclemanage.ui.usermanage.contract.SendSmsConstract;
import com.ttce.vehiclemanage.ui.usermanage.model.SendSmsModel;
import com.ttce.vehiclemanage.ui.usermanage.presenter.SendSmsPresenter;
import com.ttce.vehiclemanage.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by hk on 2019/6/19.
 */

public class ForgetPwdActivity extends BaseActivity<SendSmsPresenter, SendSmsModel> implements SendSmsConstract.View {

    public static final int TYPE_REGISTER = 1;
    public static final int TYPE_FORGET = 2;

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.tv_message)
    TextView tvMessage;
    @Bind(R.id.et_yzm)
    EditText etYzm;
    @Bind(R.id.tv_send)
    TextView tvSend;
    @Bind(R.id.tv_next)
    TextView tvNext;
    @Bind(R.id.ll_one)
    LinearLayout llOne;
    @Bind(R.id.tv_pwd)
    TextView tvPwd;
    @Bind(R.id.et_pwd)
    EditText etPwd;
    @Bind(R.id.tv_sure_pwd)
    TextView tvSurePwd;
    @Bind(R.id.et_sure_pwd)
    EditText etSurePwd;
    @Bind(R.id.ll_two)
    LinearLayout llTwo;
    @Bind(R.id.iv_eye)
    ImageView ivPwdEye;
    @Bind(R.id.tv_nickname)
    TextView tvNickname;
    @Bind(R.id.et_nickname)
    TextView edNickname;
    @Bind(R.id.rel_nickname)
    RelativeLayout relNickname;
    @Bind(R.id.rel_password)
    RelativeLayout relPassword;
    @Bind(R.id.tv_yszc)
    TextView tvyszc;

    @Bind(R.id.lin_ysxy)
    LinearLayout linysxy;
    @Bind(R.id.rb_ysxy)
    CheckBox rbysxy;
    private String VerificationId;
    private String VerificationCode;
    private String Phone;
    private String StepToken;
    private String Password;
    private String EdNickname;
    private String ConfirmPassword;
    private Timer sendMsgTimer;
    private int msgReSendInterval = 60;

    private int type;

    public static void goToPage(Activity activity, int type) {
        Intent intent = new Intent(activity, ForgetPwdActivity.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        tvSend.setTextColor(ContextCompat.getColor(this, R.color.app_main_colors));
        tvSend.setClickable(true);
        tvSend.setText("发送信息");
        type = getIntent().getIntExtra("type", TYPE_REGISTER);
        if (type == TYPE_REGISTER) {
            titleBarTitle.setText("账号注册");
            linysxy.setVisibility(View.VISIBLE);
            String str = "请您阅读并同意《隐私政策》和《使用协议》";
            SpannableString spannableString = new SpannableString(str);
            spannableString.setSpan(clickableSpan,7,13, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(clickableSpan1,15 , str.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            tvyszc.setMovementMethod(LinkMovementMethod.getInstance());
            tvyszc.setText(spannableString);
        } else {
            titleBarTitle.setText("忘记密码");
        }
    }
    ClickableSpan clickableSpan = new ClickableSpan() {
        @Override
        public void onClick(View widget) {
//            Intent intent = new Intent();
//            intent.setAction("android.intent.action.VIEW");
//            Uri content_url = Uri.parse("https://www.gtbds.com/upload/privacy.html");
//            intent.setData(content_url);
//            startActivity(intent);
            ProtocolActivity.goToPage(ForgetPwdActivity.this,1);

        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(Color.parseColor("#2A78FE"));
            ds.setUnderlineText(false);
            ds.clearShadowLayer();
        }
    };
    ClickableSpan clickableSpan1 = new ClickableSpan() {
        @Override
        public void onClick(View widget) {
//            Intent intent = new Intent();
//            intent.setAction("android.intent.action.VIEW");
//            Uri content_url = Uri.parse("https://www.gtbds.com/upload/agreement.html");
//            intent.setData(content_url);
//            startActivity(intent);
            ProtocolActivity.goToPage(ForgetPwdActivity.this,0);
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(Color.parseColor("#2A78FE"));
            ds.setUnderlineText(false);
            ds.clearShadowLayer();
        }
    };
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getMsgWhat()) {
            case AppConstant.MESSAGE_UPDATE_MSG_TIME:
                    if (msgReSendInterval <= 0) {
                        resetTime();
                    } else {
                        tvSend.setText("剩余" + msgReSendInterval + "秒重发");
                        tvSend.setTextColor(ContextCompat.getColor(this, R.color.wheel_item_text_color_no_select));
                        tvSend.setClickable(false);
                    }
                break;
        }
    }
    private void resetTime() {
        msgReSendInterval = 60;
        tvSend.setText("发送信息");
        tvSend.setTextColor(ContextCompat.getColor(this, R.color.main_text_color));
        tvSend.setClickable(true);
        if(sendMsgTimer!=null){
            sendMsgTimer.cancel();
        }
    }

    String userInputCode;
    @OnClick({R.id.title_bar_back, R.id.iv_eye, R.id.tv_send, R.id.tv_next, R.id.tv_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                finish();
                break;
            case R.id.iv_eye:
                if (ivPwdEye.getTag() == null || (Integer) ivPwdEye.getTag() == 0) {
                    etSurePwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    ivPwdEye.setImageResource(R.mipmap.eye_open);
                    ivPwdEye.setTag(1);
                } else {
                    etSurePwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    ivPwdEye.setImageResource(R.mipmap.eye_close);
                    ivPwdEye.setTag(0);
                }
                break;
            case R.id.tv_send:
                Phone = etPhone.getText().toString();
                if (TextUtils.isEmpty(Phone)) {
                    ToastUitl.showShort("请输入手机号");
                    return;
                }


                if (type == TYPE_REGISTER) {//账号注册

                        mPresenter.sendPhone(Phone);

                } else {//忘记密码
                    mPresenter.sendOtherPhone(Phone);
                }
                break;
            case R.id.tv_next:

                userInputCode = etYzm.getText().toString();
                if (TextUtils.isEmpty(userInputCode)) {
                    ToastUitl.showShort("请填写验证码");
                    return;
                }

//                if (!userInputCode.equals(VerificationCode)) {
//                    ToastUitl.showShort("验证码错误");
//                    return;
//                }

//                showRuleDialog();
                if (type == TYPE_REGISTER) {
                    if(!rbysxy.isChecked()){
                        ToastUitl.showLong("请您阅读并同意隐私政策。");
                    }else{
                        mPresenter.checkSms(VerificationId, userInputCode, Phone);
                    }
                }else{
                    mPresenter.checkSms(VerificationId, userInputCode, Phone);
                }
                break;
            case R.id.tv_complete:
                Password = etPwd.getText().toString();
                ConfirmPassword = etSurePwd.getText().toString();
                EdNickname = edNickname.getText().toString();
                if (TextUtils.isEmpty(Password)) {
                    ToastUitl.showShort("请填写密码");
                    return;
                }
                if (TextUtils.isEmpty(ConfirmPassword)) {
                    ToastUitl.showShort("请填写确认密码");
                    return;
                }
                if (type == TYPE_REGISTER) {
                    if (TextUtils.isEmpty(EdNickname)) {
                        ToastUitl.showShort("请填写昵称");
                        return;
                    }
                }

                if (!Password.equals(ConfirmPassword)) {
                    ToastUitl.showShort("两次输入密码不一致");
                    return;
                }
                mPresenter.userRegister(Phone, StepToken, Password, ConfirmPassword, type,EdNickname);
                break;
        }

    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {
        if (msg.equals("1")) {
            resetTime();
        }
    }

    @Override
    public void returnResult(ExVerificationBean exVerificationBean) {
        VerificationId = exVerificationBean.getVerificationId();
        VerificationCode = exVerificationBean.getVerificationCode();

        sendMsgTimer = new Timer();
        sendMsgTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                EventBus.getDefault().postSticky(new MessageEvent(AppConstant.MESSAGE_UPDATE_MSG_TIME));
                msgReSendInterval--;
            }
        }, 0, 1000);
    }

    @Override
    public void returnCheck(ExRegistFirstStepBean exRegistFirstStepBean) {
        llOne.setVisibility(View.GONE);
        llTwo.setVisibility(View.VISIBLE);
        if(type == TYPE_REGISTER){
            relNickname.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DisplayUtil.dip2px( 50));
            params.setMargins(DisplayUtil.dip2px(25),DisplayUtil.dip2px(0),DisplayUtil.dip2px(25),DisplayUtil.dip2px(0));
            relPassword.setLayoutParams(params);
        }
        StepToken = exRegistFirstStepBean.getStepToken();
    }

    @Override
    public void returnOtherResult(ExVerificationBean exVerificationBean) {
        VerificationId = exVerificationBean.getVerificationId();
        VerificationCode = exVerificationBean.getVerificationCode();

        sendMsgTimer = new Timer();
        sendMsgTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                EventBus.getDefault().postSticky(new MessageEvent(AppConstant.MESSAGE_UPDATE_MSG_TIME));
                msgReSendInterval--;
            }
        }, 0, 1000);
    }

    @Override
    public void registerMessage(String message) {
        if (type == TYPE_REGISTER) {
            ToastUitl.showShort("注册成功");
        } else {
            ToastUitl.showShort("修改成功");
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(sendMsgTimer!=null){
            sendMsgTimer.cancel();
            sendMsgTimer=null;
        }
        //移除所有的粘性事件
        EventBus.getDefault().removeAllStickyEvents();
        //解除注册
        EventBus.getDefault().unregister(this);
    }
}
