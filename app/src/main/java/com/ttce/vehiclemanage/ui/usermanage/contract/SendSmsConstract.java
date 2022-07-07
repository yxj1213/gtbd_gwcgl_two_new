package com.ttce.vehiclemanage.ui.usermanage.contract;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.ttce.vehiclemanage.bean.ExRegistFirstStepBean;
import com.ttce.vehiclemanage.bean.ExVerificationBean;

import rx.Observable;


/**
 * Created by hk on 2019/7/17.
 */

public interface SendSmsConstract {

    interface Model extends BaseModel {
        Observable<ExVerificationBean> sendPhone(String phone);

        Observable<ExRegistFirstStepBean> checkSms(String VerificationId, String VerificationCode, String Phone);

        Observable<ExVerificationBean> sendOtherPhone(String Phone);

        Observable<String> userRegister(String Phone, String StepToken, String Password, String ConfirmPassword, int type,String EdNickname);
    }

    interface View extends BaseView {
        void returnResult(ExVerificationBean exVerificationBean);

        void returnCheck(ExRegistFirstStepBean exRegistFirstStepBean);

        void returnOtherResult(ExVerificationBean exRegistFirstStepBean);

        void registerMessage(String message);
    }

    abstract static class Presenter extends BasePresenter<View, Model> {

        public abstract void sendPhone(String phone);

        public abstract void checkSms(String VerificationId, String VerificationCode, String Phone);

        public abstract void sendOtherPhone(String Phone);

        public abstract void userRegister(String Phone, String StepToken, String Password, String ConfirmPassword, int type,String EdNickname);
    }

}
