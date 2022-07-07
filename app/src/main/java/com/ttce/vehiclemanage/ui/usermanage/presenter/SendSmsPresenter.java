package com.ttce.vehiclemanage.ui.usermanage.presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.ExRegistFirstStepBean;
import com.ttce.vehiclemanage.bean.ExVerificationBean;
import com.ttce.vehiclemanage.ui.usermanage.contract.SendSmsConstract;

/**
 * Created by hk on 2019/7/17.
 */

public class SendSmsPresenter extends SendSmsConstract.Presenter {
    @Override
    public void sendPhone(String phone) {
        mRxManage.add(mModel.sendPhone(phone).subscribe(new RxSubscriber<ExVerificationBean>(mContext) {
            @Override
            protected void _onNext(ExVerificationBean exVerificationBean) {
                mView.returnResult(exVerificationBean);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip("1");
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }

    @Override
    public void checkSms(String VerificationId, String VerificationCode, String Phone) {
        mRxManage.add(mModel.checkSms(VerificationId, VerificationCode, Phone).subscribe(new RxSubscriber<ExRegistFirstStepBean>(mContext) {
            @Override
            protected void _onNext(ExRegistFirstStepBean exRegistFirstStepBean) {
                mView.returnCheck(exRegistFirstStepBean);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }
    @Override
    public void sendOtherPhone(String Phone) {
        mRxManage.add(mModel.sendOtherPhone(Phone).subscribe(new RxSubscriber<ExVerificationBean>(mContext) {
            @Override
            protected void _onNext(ExVerificationBean exRegistFirstStepBean) {
                mView.returnOtherResult(exRegistFirstStepBean);
            }

            @Override
            protected void _onError(String message) {
                if(message.contains("该手机号已注册")){
                    mView.showErrorTip("0");
                }
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }
    @Override
    public void userRegister(String Phone, String StepToken, String Password, String ConfirmPassword, int type,String EdNickname) {
        mRxManage.add(mModel.userRegister(Phone, StepToken, Password, ConfirmPassword, type,EdNickname).subscribe(new RxSubscriber<String>(mContext) {
            @Override
            protected void _onNext(String s) {
                mView.registerMessage(s);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }
}
