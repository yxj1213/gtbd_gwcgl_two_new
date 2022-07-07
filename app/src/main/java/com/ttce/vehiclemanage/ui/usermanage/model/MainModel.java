package com.ttce.vehiclemanage.ui.usermanage.model;

import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.bean.AppUpdateBean;
import com.ttce.vehiclemanage.bean.ChangeCompanyBean;
import com.ttce.vehiclemanage.bean.ChangeCompanySaveReturnBean;
import com.ttce.vehiclemanage.bean.CloseAccountStateBean;
import com.ttce.vehiclemanage.bean.UserInfoBean;
import com.ttce.vehiclemanage.ui.usermanage.contract.MainContract;


import java.util.List;
import rx.Observable;

public class MainModel implements MainContract.Model {
    @Override
    public Observable<UserInfoBean> getUserInfo() {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        return Api.getDefault(HostType.BASE_HOST).getUserInfo(params.build())
                .compose(RxHelper.<UserInfoBean>handleResult());
    }

    @Override
    public Observable<List<ChangeCompanyBean>> getChangeCompany() {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        return Api.getDefault(HostType.BASE_HOST).getSwitchCompanyByUserId(params.build())
                .compose(RxHelper.handleResult());
    }

    @Override
    public Observable<ChangeCompanySaveReturnBean> getSaveChangeCompany(String companyId) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("SwitchCompanyId", companyId);
        return Api.getDefault(HostType.BASE_HOST).getSaveSwitchCompanyByUserIds(params.build())
                .compose(RxHelper.handleResult());
    }


    @Override
    public Observable<AppUpdateBean> getAppUpdate() {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        return Api.getDefault(HostType.BASE_HOST).getAppVersionModel(params.build())
                .compose(RxHelper.<AppUpdateBean>handleResult());
    }

    @Override
    public Observable<Boolean> getAppLogout(String applyReason) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("ApplyReason",applyReason);
        return Api.getDefault(HostType.BASE_HOST).postLogout(params.build())
                .compose(RxHelper.<Boolean>handleResult());
    }
    @Override
    public Observable<CloseAccountStateBean> getCloseAccountState() {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        return Api.getDefault(HostType.BASE_HOST).PostCloseAccountState(params.build())
                .compose(RxHelper.<CloseAccountStateBean>handleResult());
    }
    @Override

    public Observable<String> logout() {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        return Api.getDefault(HostType.BASE_HOST).logout(params.build())
                .compose(RxHelper.<String>handleResult());

    }
}
