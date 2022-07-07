package com.ttce.vehiclemanage.ui.main.model.dispatch;

import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.bean.CarPositionBean;
import com.ttce.vehiclemanage.bean.DispatchDriverListBean;
import com.ttce.vehiclemanage.ui.main.contract.dispatch.DispatchCarContract;
import com.ttce.vehiclemanage.ui.usermanage.manager.UserManager;

import java.util.List;

import rx.Observable;

/**
 * 车辆管理
 * Created by hk on 2019/7/3.
 */

public class DispatchCarModel implements DispatchCarContract.Model {
    @Override
    public Observable<List<CarPositionBean>> getOrderCarListModel(String CarUseState,String CarTypeId) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("CarUseState",CarUseState);
        params.add("CarTypeId",CarTypeId);
        return Api.getDefault(HostType.BASE_HOST).OrderCarList(params.build())
                .compose(RxHelper.<List<CarPositionBean>>handleResult());
    }
    @Override
    public Observable<List<DispatchDriverListBean>> getOrderDriverListModel() {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("IsDriver",1);
        return Api.getDefault(HostType.BASE_HOST).OrderDriverList(params.build())
                .compose(RxHelper.<List<DispatchDriverListBean>>handleResult());
    }
    @Override
    public Observable<String> getOrderDriverGrabModel(String OrderId,String GrabCarCompanyId,String GrabCarDepartmentId,String GrabCarId) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("OrderId",OrderId);
        params.add("GrabUserCompanyId",UserManager.getLoginBean().getCompanyId());
        params.add("GrabStaffId",UserManager.getLoginBean().getStaffId());
        params.add("GrabUserId",UserManager.getLoginBean().getUserId());
        params.add("GrabCarCompanyId",GrabCarCompanyId);
        params.add("GrabCarDepartmentId",GrabCarDepartmentId);
        params.add("GrabCarId",GrabCarId);
        return Api.getDefault(HostType.BASE_HOST).OrderDriverGrab(params.build())
                .compose(RxHelper.<String>handleResult());
    }
}
