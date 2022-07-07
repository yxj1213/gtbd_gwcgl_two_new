package com.ttce.vehiclemanage.ui.usermanage.model;

import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.bean.LoginBean;
import com.ttce.vehiclemanage.bean.LoginImageVerificationBean;
import com.ttce.vehiclemanage.bean.PostServerBean;
import com.ttce.vehiclemanage.bean.VerificationBean;
import com.ttce.vehiclemanage.ui.usermanage.contract.LoginContract;

import rx.Observable;

/**
 * Created by hk on 2019/7/1.
 */

public class LoginModel implements LoginContract.Model {
    @Override
    public Observable<LoginBean> getLoginData(String name, String password, String verificationId, String verificationCode) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("UserName", name)
                .add("PassWord", password)
                .add("VerificationId", verificationId)
                .add("VerificationCode", verificationCode);
        return Api.getDefault(HostType.BASE_HOST).login(params.build())
                .compose(RxHelper.<LoginBean>handleResult());
    }

    @Override
    public Observable<VerificationBean> getImgVaild() {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        return Api.getDefault(HostType.BASE_HOST).getLoginVerification(params.build()).compose(RxHelper.<VerificationBean>handleResult());
    }

    @Override
    public Observable<LoginImageVerificationBean> getImgLoginVaild() {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        return Api.getDefault(HostType.BASE_HOST).postLoginImageVerification(params.build()).compose(RxHelper.<LoginImageVerificationBean>handleResult());
    }
    @Override
    public Observable<String> getCheckImgLoginVaild(String VerificationId,double ImgVerificationpositionX) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("VerificationId", VerificationId)
                .add("ImgVerificationpositionX", ImgVerificationpositionX);
        return Api.getDefault(HostType.BASE_HOST).postLoginImageVerificationIsCorrect(params.build()).compose(RxHelper.<String>handleResult());
    }

    @Override
    public Observable<PostServerBean> getPostServerBean() {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        return Api.getDefault(HostType.BASE_HOST).PostServerBean(params.build()).compose(RxHelper.<PostServerBean>handleResult());
    }
}
