package com.ttce.vehiclemanage.ui.main.model.needcar;

import com.google.gson.JsonArray;
import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.bean.EmptyOrderBean;
import com.ttce.vehiclemanage.bean.IsOrderingBean;
import com.ttce.vehiclemanage.bean.Message2Bean;
import com.ttce.vehiclemanage.bean.OrderSuccessBean;
import com.ttce.vehiclemanage.bean.OtherUserId;
import com.ttce.vehiclemanage.ui.main.contract.needcar.MyNeedCarConstract;
import com.ttce.vehiclemanage.ui.usermanage.manager.UserManager;


import rx.Observable;

/**
 * 车辆管理
 * Created by hk on 2019/7/3.
 */

public class MyNeedCarModel implements MyNeedCarConstract.Model {
    @Override
    public Observable<Message2Bean> StaffMagStateModel() {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("CompanyId", UserManager.getLoginBean().getCompanyId());
        params.add("UserId", UserManager.getLoginBean().getUserId());
        return Api.getDefault(HostType.BASE_HOST).StaffMagState(params.build())
                .compose(RxHelper.<Message2Bean>handleResult());
    }
    @Override
    public Observable<EmptyOrderBean> getEmptyOrderBeanModel() {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        return Api.getDefault(HostType.BASE_HOST).getEmptyOrder(params.build())
                .compose(RxHelper.<EmptyOrderBean>handleResult());
    }

    @Override
    public Observable<OrderSuccessBean> addOrderBeanModel(String ycyhuserId, String ycyhStaffId, String ycms, String yclx_lcid, String yclx, String jsr_value, String jsr_id, String jsr_saffid, String ycsy_bt, String ycsy_nr, String ycsj_value, String ycsj_kssj, String ycsj_jssj,
                                                          String yxcx, String yx_hplx, String yx_cp, String yxzws, String ddld_userid, String ddld_staffid, String scrs, JsonArray markListBeans, JsonArray passengerListBeans) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("ApplyStaffId", UserManager.getLoginBean().getStaffId())
                .add("ApplyUserId", UserManager.getLoginBean().getUserId())
                .add("UseUserId", ycyhuserId)
                .add("UseStaffId", ycyhStaffId)
                .add("UseMode", ycms)
                .add("LineDistance", "0")
                .add("GpsDistance", "0")
                .add("OrderState", "0")
                .add("ProcessId", yclx_lcid)
                .add("UseCarTypeId", yclx)
                .add("DriverType", jsr_value)
                .add("DriverUserId", jsr_id)
                .add("DriverStaffId", jsr_saffid)
                .add("ReasonTitle", ycsy_bt)
                .add("ReasonContent", ycsy_nr)
                .add("WillUseDateTimeType", ycsj_value)
                .add("WillUseStartDateTime", ycsj_kssj)
                .add("WillUseEndDateTime", ycsj_jssj)
                .add("CarStyleId", yxcx)
                .add("CarPlateTypeId", yx_hplx)
                .add("WillUseCarPlate", yx_cp)
                .add("WillUseCarSeatNum", yxzws)
                .add("LeaderUserId", ddld_userid)
                .add("LeaderStaffId", ddld_staffid)
                .add("PassengerNum", scrs)
                .add("OrderState", "0")
                .add("Remark", "")
                .add("CarFlow_Order_Mark_List",markListBeans)
                .add("CarFlow_Order_Passenger_List", passengerListBeans);
        return Api.getDefault(HostType.BASE_HOST).addPostOrder(params.build())
                .compose(RxHelper.<OrderSuccessBean>handleResult());
    }

    @Override
    public Observable<OtherUserId> OrderOtherUserIdBeanModel(String xm, String lxdh) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("Name",xm);
        params.add("Phone",lxdh);
        return Api.getDefault(HostType.BASE_HOST).getOtherUserId(params.build())
                .compose(RxHelper.<OtherUserId>handleResult());
    }
    @Override
    public Observable<IsOrderingBean> getOrderIsOrderingModel() {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        return Api.getDefault(HostType.BASE_HOST).OrderIsOrdering(params.build())
                .compose(RxHelper.<IsOrderingBean>handleResult());
    }

    @Override
    public Observable<String> setDefaultOrderCarType(String defaultUseCarTypeId) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("DefaultUseCarTypeId",defaultUseCarTypeId);
        return Api.getDefault(HostType.BASE_HOST).SetDefaultOrderCarType(params.build())
                .compose(RxHelper.<String>handleResult());
    }
    @Override
    public Observable<String> getOrderDispatchAuditModel(String carFlow_OrderId, int CheckState, String CheckReason,JsonArray Order_Assigns_Car_List,String AssignsType) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("OrderId",carFlow_OrderId);
        params.add("CheckUserId", UserManager.getLoginBean().getUserId());
        params.add("CheckStaffId", UserManager.getLoginBean().getStaffId());
        params.add("CheckState",CheckState);
        params.add("CheckReason",CheckReason);
        params.add("AssignsType",AssignsType);
        params.add("Order_Assigns_Car_List",Order_Assigns_Car_List);
        return Api.getDefault(HostType.BASE_HOST).OrderDispatchAudit(params.build())
                .compose(RxHelper.<String>handleResult());
    }
}
