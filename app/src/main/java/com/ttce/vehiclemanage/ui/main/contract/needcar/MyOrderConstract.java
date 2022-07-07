package com.ttce.vehiclemanage.ui.main.contract.needcar;

import com.google.gson.JsonArray;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.ttce.vehiclemanage.bean.CarLatLngBean;
import com.ttce.vehiclemanage.bean.CarRecordsBean;
import com.ttce.vehiclemanage.bean.EmptyOrderBean;
import com.ttce.vehiclemanage.bean.MarkerDetailsBean;
import com.ttce.vehiclemanage.bean.TravelListBean;

import java.util.List;

import rx.Observable;

public interface MyOrderConstract {
    interface Model extends BaseModel {
        Observable<String> getOrderDriverGrabModel(String OrderId,String GrabCarCompanyId,String GrabCarDepartmentId,String GrabCarId);
        Observable<String> getmyOrderCancelModel(String carFlow_OrderId,int CheckState, String CheckReason,String type);
        Observable<EmptyOrderBean> getmyOrderDetailsModel(String carFlow_OrderId,String OrderListType);
        Observable<List<EmptyOrderBean>> getAllOrderListBeans(int pageIndex,JsonArray escapeOrderState,String type,String StartCreateTime,String EndCreateTime,String PlateNumber,String KeyWord,String CarFlow_OrderId);
        Observable<String> getOrderDispatchAuditModel(String carFlow_OrderId, int CheckState, String CheckReason, JsonArray Order_Assigns_Car_List);
        Observable<CarLatLngBean> getCarFlowPositionModel(String carId);
        Observable<List<CarRecordsBean>> getOrderStateLogListModel(String orderId,String orderModule);
        Observable<String> getUserSureGoCarModel(String orderId,String type);
        Observable<String> getOrderMarkAddModel(String OrderId, String NeedMarkTitle, String RealMarkFullAddress, String RealMarkLng, String RealMarkLat,String RemarkTitle, String RemarkContent, String MarkLevel, String MarkType, String CarFlow_Order_MarkId, JsonArray MarkImgUrlList,boolean ismark,String NeedMarkSimpleAddress,String NeedMarkFullAddress,String NeedMarkLng,String NeedMarkLat,String LinkName,String LinkPhone,boolean IsFinishMark);
        Observable<MarkerDetailsBean> getOrderMarkModel(String CarFlow_Order_MarkId);
        Observable<TravelListBean> getTravelData(String deviceId, String startTime, String endTime);
    }

    interface View extends BaseView {
        void returnOrderDriverGrab(String carPositionBeans);
        void returnMyOrderCancel(String carFlow_OrderId,String type);
        void returnMyOrderDetails(EmptyOrderBean carFlow_OrderId);
        void returnAllOrderList(List<EmptyOrderBean> emptyOrderBean);
        void returnCarFlowPosition(CarLatLngBean latLng);
        void returnOrderStateLogList(List<CarRecordsBean> carRecordsBeans);
        void returnOrderMarkAddBean(String type);
        void returnOrderMarkModelBean(MarkerDetailsBean str);
        void drawTravel(TravelListBean travelListBeanList,int type,int state,int alllistsize);
    }

    abstract static class Presenter extends BasePresenter<View, Model>{
        public abstract void getgetOrderDriverGrabPresenter(String OrderId,String GrabCarCompanyId,String GrabCarDepartmentId,String GrabCarId);
        public abstract void getMyOrderCancelBeans(String carFlow_OrderId,int CheckState, String CheckReason,String type);
        public abstract void getMyOrderDetailsBeans(String carFlow_OrderId,String OrderListType);
        public abstract void getAllOrderListBeans(int pageIndex,JsonArray escapeOrderState,String type,String StartCreateTime,String EndCreateTime,String PlateNumber,String KeyWord,String CarFlow_OrderId);
        public abstract void getOrderDispatchAuditBeans(String carFlow_OrderId, int CheckState, String CheckReason,JsonArray Order_Assigns_Car_List,String type);
        public abstract void getCarFlowPositionBean(String carId);
        public abstract void getOrderStateLogListBean(String orderId,String orderModule);
        public abstract void getUserSureGoCarBeans(String orderId,String type);
        public abstract void getOrderMarkAddPresenter(String type,String OrderId, String NeedMarkTitle, String RealMarkFullAddress, String RealMarkLng, String RealMarkLat,String RemarkTitle, String RemarkContent, String MarkLevel, String MarkType, String CarFlow_Order_MarkId, JsonArray MarkImgUrlList,boolean ismark,String NeedMarkSimpleAddress,String NeedMarkFullAddress,String NeedMarkLng,String NeedMarkLat,String LinkName,String LinkPhone,boolean IsFinishMark);
        public abstract void getOrderMarkmodelPresenter(String CarFlow_Order_MarkId);
        public abstract void getTravelData(String deviceId, String startTime, String endTime,int type,int state,int alllistsize);
    }
}
