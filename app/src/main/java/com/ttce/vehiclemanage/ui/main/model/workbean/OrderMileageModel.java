package com.ttce.vehiclemanage.ui.main.model.workbean;

import com.google.gson.JsonArray;
import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.ui.main.contract.workbean.OrderMileageContract;
import com.ttce.vehiclemanage.ui.usermanage.manager.UserManager;

import rx.Observable;

public class OrderMileageModel implements OrderMileageContract.Model {
    @Override
    public Observable<String> getCarFlowOrder_stateModel(String type,String OrderId, String Mileage,JsonArray CarFlow_Order_Mileage_Image_List) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("OrderId", OrderId);
        params.add("StaffId", UserManager.getLoginBean().getStaffId());
        params.add("Mileage", Mileage);
        params.add("UserId", UserManager.getLoginBean().getUserId());
        params.add("CarFlow_Order_Mileage_Image_List", CarFlow_Order_Mileage_Image_List);
        switch (type){
            case "开始出发":
                return Api.getDefault(HostType.BASE_HOST).CarFlowOrder_Confirm_Driver(params.build())
                        .compose(RxHelper.<String>handleResult());
            case "结束用车":
                return Api.getDefault(HostType.BASE_HOST).CarFlowOrder_Confirm_Finish(params.build())
                        .compose(RxHelper.<String>handleResult());
            case "前往目的地":
                return Api.getDefault(HostType.BASE_HOST).CarFlowOrder_BeginDestination(params.build())
                        .compose(RxHelper.<String>handleResult());
        }
        return null;
    }
    @Override
    public Observable<String> getOrderMarkAddModel(String OrderId, String NeedMarkTitle, String RealMarkFullAddress, String RealMarkLng, String RealMarkLat,String RemarkTitle, String RemarkContent, String MarkLevel, String MarkType, String CarFlow_Order_MarkId, JsonArray MarkImgUrlList,boolean ismark,String NeedMarkSimpleAddress,String NeedMarkFullAddress,String NeedMarkLng,String NeedMarkLat,String LinkName,String LinkPhone,boolean IsFinishMark) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("OrderId",OrderId);
        params.add("RealMarkSimpleAddress","");//地址简称
        params.add("RealMarkFullAddress",RealMarkFullAddress);//地址全称
        params.add("RealMarkLng",RealMarkLng);
        params.add("RealMarkLat",RealMarkLat);
        params.add("RemarkTitle",RemarkTitle);
        params.add("RemarkContent",RemarkContent);

        params.add("RealMarkUserId", UserManager.getLoginBean().getUserId());
        params.add("RealMarkStaffId", UserManager.getLoginBean().getStaffId());
        params.add("IsMark",ismark);
        params.add("CarFlow_Order_MarkId",CarFlow_Order_MarkId);
        params.add("CarFlow_Order_Mark_Image_List",MarkImgUrlList);

        //下面的参数是新增途经点
        params.add("NeedMarkTitle",NeedMarkTitle);//地址批注
        params.add("NeedMarkSimpleAddress",NeedMarkSimpleAddress);
        params.add("NeedMarkFullAddress",NeedMarkFullAddress);
        params.add("NeedMarkLng",NeedMarkLng);
        params.add("NeedMarkLat",NeedMarkLat);
        params.add("LinkName",LinkName);
        params.add("LinkPhone",LinkPhone);


        params.add("MarkLevel",MarkLevel);//打卡地标识
        params.add("MarkType",MarkType);//打卡类型    正常打卡 10   新增打卡  20   30 新增途经点

        params.add("IsFinishMark",IsFinishMark);//是不是终点打卡 当前订单没有起点和终点的情况下

        return Api.getDefault(HostType.BASE_HOST).OrderMarkAdd(params.build())
                .compose(RxHelper.<String>handleResult());
    }

}