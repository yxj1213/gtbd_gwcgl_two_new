package com.ttce.vehiclemanage.ui.mine.model;

import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.ui.mine.constract.UpdatePwdConstract;

import rx.Observable;

/**
 * Created by hk on 2019/7/12.
 */

public class UpdatePwdModel implements UpdatePwdConstract.Model {
    @Override
    public Observable<String> updatePwd(String OldPassword, String NewPassword, String ConfirmNewPassword) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("OldPassword", OldPassword);
        params.add("NewPassword", NewPassword);
        params.add("ConfirmNewPassword", ConfirmNewPassword);
        return Api.getDefault(HostType.BASE_HOST).updatePwd(params.build())
                .compose(RxHelper.<String>handleResult());
    }
}
