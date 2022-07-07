package com.ttce.vehiclemanage.ui.main.model.home;

import com.google.gson.JsonObject;
import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.bean.AlipayBean;
import com.ttce.vehiclemanage.bean.ChangeCompanyBean;
import com.ttce.vehiclemanage.bean.ChangeCompanySaveReturnBean;
import com.ttce.vehiclemanage.bean.CompanyTypeListBean;
import com.ttce.vehiclemanage.ui.main.contract.home.HomeLeftContract;
import com.ttce.vehiclemanage.ui.usermanage.manager.UserManager;

import java.util.List;

import rx.Observable;

/**
 * 车辆管理
 * Created by hk on 2019/7/3.
 */

public class HomeLeftModel implements HomeLeftContract.Model {
    @Override
    public Observable<List<CompanyTypeListBean>> companyTypeListModel() {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        return Api.getDefault(HostType.BASE_HOST).CompanyTypeList(params.build())
                .compose(RxHelper.<List<CompanyTypeListBean>>handleResult());
    }
    @Override
    public Observable<String> PostDepartmentAddModel(String CompanyId,String Name) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("CompanyId",CompanyId);
        params.add("Name",Name);
        params.add("CreateUser",UserManager.getLoginBean().getUserId());
        return Api.getDefault(HostType.BASE_HOST).PostDepartmentAdd(params.build())
                .compose(RxHelper.<String>handleResult());
    }
    @Override
    public Observable<ChangeCompanyBean> businessComPanyAddModel(String CompanyName, String ContactPerson, String ContactPhone, String ContactEmail, String CompanyType) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("CompanyName",CompanyName);
        params.add("ContactPerson",ContactPerson);
        params.add("ContactPhone",ContactPhone);
        params.add("ContactEmail",ContactEmail);
        params.add("CompanyType",CompanyType);
        params.add("CreateUser", UserManager.getLoginBean().getUserId());
        return Api.getDefault(HostType.BASE_HOST).BusinessComPanyAdd(params.build())
                .compose(RxHelper.<ChangeCompanyBean>handleResult());
    }
    @Override
    public Observable<ChangeCompanySaveReturnBean> getSaveChangeCompany(String companyId) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("SwitchCompanyId", companyId);
        return Api.getDefault(HostType.BASE_HOST).getSaveSwitchCompanyByUserIds(params.build())
                .compose(RxHelper.handleResult());
    }
}
