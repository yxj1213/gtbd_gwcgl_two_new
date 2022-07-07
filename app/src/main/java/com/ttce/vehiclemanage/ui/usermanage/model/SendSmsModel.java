package com.ttce.vehiclemanage.ui.usermanage.model;

import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.bean.ExRegistFirstStepBean;
import com.ttce.vehiclemanage.bean.ExVerificationBean;
import com.ttce.vehiclemanage.ui.usermanage.contract.SendSmsConstract;

import rx.Observable;

/**
 * Created by hk on 2019/7/17.
 */

public class SendSmsModel implements SendSmsConstract.Model {
    @Override
    public Observable<ExVerificationBean> sendPhone(String phone) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("Phone", phone);
        return Api.getDefault(HostType.BASE_HOST).sendSms(params.build())
                .compose(RxHelper.<ExVerificationBean>handleResult());
    }

    @Override
    public Observable<ExRegistFirstStepBean> checkSms(String VerificationId, String VerificationCode, String Phone) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("VerificationId", VerificationId);
        params.add("VerificationCode", VerificationCode);
        params.add("Phone", Phone);
        return Api.getDefault(HostType.BASE_HOST).checkSms(params.build())
                .compose(RxHelper.<ExRegistFirstStepBean>handleResult());
    }
    @Override
    public Observable<ExVerificationBean> sendOtherPhone( String Phone) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("Phone", Phone);
        return Api.getDefault(HostType.BASE_HOST).PostSendSmsVerificationCode(params.build())
                .compose(RxHelper.<ExVerificationBean>handleResult());
    }
    @Override
    public Observable<String> userRegister(String Phone, String StepToken, String Password, String ConfirmPassword, int type,String EdNickname) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("Phone", Phone);
        params.add("StepToken", StepToken);
        params.add("Password", Password);
        params.add("ConfirmPassword", ConfirmPassword);
        if (type == 1) {
            params.add("UserNickName", EdNickname);
            return Api.getDefault(HostType.BASE_HOST).userRegister(params.build())
                    .compose(RxHelper.<String>handleResult());
        } else {
            return Api.getDefault(HostType.BASE_HOST).findPasswordByPhone(params.build())
                    .compose(RxHelper.<String>handleResult());
        }

    }
}
