package com.ttce.vehiclemanage.ui.main.model.needcar;

import com.google.gson.JsonArray;
import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.bean.CarLatLngBean;
import com.ttce.vehiclemanage.bean.CarRecordsBean;
import com.ttce.vehiclemanage.bean.EmptyOrderBean;
import com.ttce.vehiclemanage.bean.MarkerDetailsBean;
import com.ttce.vehiclemanage.bean.TravelListBean;
import com.ttce.vehiclemanage.ui.main.contract.needcar.MyOrderConstract;
import com.ttce.vehiclemanage.ui.usermanage.manager.UserManager;

import java.util.List;

import rx.Observable;

/**
 * 车辆管理
 * Created by hk on 2019/7/3.
 */

public class MyOrderModel implements MyOrderConstract.Model {
    @Override
    public Observable<TravelListBean> getTravelData(String deviceId, String startTime, String endTime) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("DeviceId",deviceId);
        params.add("StartTime",startTime);
        params.add("EndTime",endTime);
        return Api.getDefault(HostType.BASE_HOST).getTravelList(params.build())
                .compose(RxHelper.<TravelListBean>handleResult());
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
//    @Override
//    public Observable<List<EmptyOrderBean>> getMyOrderBeanModel(int pageIndex,int orderState) {
//        StringBodyParamBuilder params = StringBodyParamBuilder.create();
//        params.add("PageSize","10");
//        params.add("PageIndex",pageIndex);
//        params.add("OrderState",orderState);
//        params.add("UseMode","10");
//        params.add("DriverType","10");
//        return Api.getDefault(HostType.BASE_HOST).getMyOrders(params.build())
//                .compose(RxHelper.<List<EmptyOrderBean>>handleResult());
//    }

    @Override
    public Observable<String> getmyOrderCancelModel(String carFlow_OrderId,int CheckState, String CheckReason,String type) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        if(type.equals("订单审批")){
            params.add("OrderId",carFlow_OrderId);
            params.add("CheckUserId", UserManager.getLoginBean().getUserId());
            params.add("CheckStaffId", UserManager.getLoginBean().getStaffId());
            params.add("CheckState",CheckState);
            params.add("CheckReason",CheckReason);
            return Api.getDefault(HostType.BASE_HOST).OrderUseCarAudit(params.build())
                    .compose(RxHelper.<String>handleResult());
        }else if(type.equals("取消订单")){
            params.add("CarFlow_OrderId",carFlow_OrderId);
            return Api.getDefault(HostType.BASE_HOST).myOrderCancel(params.build())
                    .compose(RxHelper.<String>handleResult());
        }
        return null;
    }
    @Override
    public Observable<EmptyOrderBean> getmyOrderDetailsModel(String carFlow_OrderId,String OrderListType) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("CarFlow_OrderId",carFlow_OrderId);
        params.add("OrderModule",OrderListType);
        return Api.getDefault(HostType.BASE_HOST).myOrderDetails(params.build())
                .compose(RxHelper.<EmptyOrderBean>handleResult());
    }
    @Override
    public Observable<List<EmptyOrderBean>> getAllOrderListBeans(int pageIndex,JsonArray escapeOrderState,String type,String StartCreateTime,String EndCreateTime,String PlateNumber,String KeyWord,String CarFlow_OrderId) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("OrderModule",type);
        params.add("PageSize","10");
        params.add("PageIndex",pageIndex);
        params.add("EscapeOrderStateList",escapeOrderState);
        params.add("StartCreateTime",StartCreateTime);//创建订单开始时间
        params.add("EndCreateTime",EndCreateTime);// 创建订单结束时间
        params.add("PlateNumber",PlateNumber);//车牌号
        params.add("KeyWord",KeyWord);//  姓名/手机号
        params.add("CarFlow_OrderId",CarFlow_OrderId);//订单号

        return Api.getDefault(HostType.BASE_HOST).AllOrderPageList(params.build())
                .compose(RxHelper.<List<EmptyOrderBean>>handleResult());
    }
    @Override
    public Observable<String> getOrderDispatchAuditModel(String carFlow_OrderId, int CheckState, String CheckReason,JsonArray Order_Assigns_Car_List) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
         params.add("OrderId",carFlow_OrderId);
         params.add("CheckUserId", UserManager.getLoginBean().getUserId());
         params.add("CheckStaffId", UserManager.getLoginBean().getStaffId());
         params.add("CheckState",CheckState);
         params.add("CheckReason",CheckReason);
         params.add("AssignsType","");
         params.add("Order_Assigns_Car_List",Order_Assigns_Car_List);
        return Api.getDefault(HostType.BASE_HOST).OrderDispatchAudit(params.build())
                .compose(RxHelper.<String>handleResult());
    }
    @Override
    public Observable<CarLatLngBean> getCarFlowPositionModel(String carId) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("CarId",carId);
        return Api.getDefault(HostType.BASE_HOST).CarFlowPosition(params.build())
                .compose(RxHelper.<CarLatLngBean>handleResult());
    }
    @Override
    public Observable<List<CarRecordsBean>> getOrderStateLogListModel(String orderId,String orderModule) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("OrderId",orderId);
        params.add("OrderModule",orderModule);
        return Api.getDefault(HostType.BASE_HOST).OrderStateLogList(params.build())
                .compose(RxHelper.<List<CarRecordsBean>>handleResult());
    }

    @Override
    public Observable<String> getUserSureGoCarModel(String orderId,String type) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("OrderId",orderId);
        params.add("StaffId",UserManager.getLoginBean().getStaffId());
        params.add("UserId",UserManager.getLoginBean().getUserId());
        if(type.equals("乘客上车确认")){
            return Api.getDefault(HostType.BASE_HOST).Order_Confirm_UseUser(params.build())
                    .compose(RxHelper.<String>handleResult());
        }else if(type.equals("司机到达上车地确认")){
            return Api.getDefault(HostType.BASE_HOST).OrderReachedBoardingPlace(params.build())
                    .compose(RxHelper.<String>handleResult());
        }else if(type.equals("开始出发")){
            return Api.getDefault(HostType.BASE_HOST).OrderConfirmDriver(params.build())
                    .compose(RxHelper.<String>handleResult());
        }else if(type.equals("结束用车")){
            return Api.getDefault(HostType.BASE_HOST).OrderConfirmFinish(params.build())
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

        params.add("RealMarkUserId",UserManager.getLoginBean().getUserId());
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

    @Override
    public Observable<MarkerDetailsBean> getOrderMarkModel(String CarFlow_Order_MarkId) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("CarFlow_Order_MarkId",CarFlow_Order_MarkId);
        return Api.getDefault(HostType.BASE_HOST).OrderMarkModel(params.build())
                .compose(RxHelper.<MarkerDetailsBean>handleResult());
    }

}
