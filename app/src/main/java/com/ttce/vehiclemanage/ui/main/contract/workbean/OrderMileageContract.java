package com.ttce.vehiclemanage.ui.main.contract.workbean;

import com.google.gson.JsonArray;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.ttce.vehiclemanage.bean.InformationAuditBean;

import java.util.List;

import rx.Observable;

/**
 * Created by hk on 2019/7/3.
 */

public interface OrderMileageContract {

    interface Model extends BaseModel {
        Observable<String> getCarFlowOrder_stateModel(String type,String OrderId, String Mileage,JsonArray CarFlow_Order_Mileage_Image_List);
        Observable<String> getOrderMarkAddModel(String OrderId, String NeedMarkTitle, String RealMarkFullAddress, String RealMarkLng, String RealMarkLat,String RemarkTitle, String RemarkContent, String MarkLevel, String MarkType, String CarFlow_Order_MarkId, JsonArray MarkImgUrlList,boolean ismark,String NeedMarkSimpleAddress,String NeedMarkFullAddress,String NeedMarkLng,String NeedMarkLat,String LinkName,String LinkPhone,boolean IsFinishMark);
    }

    interface View extends BaseView {
        void returnCarFlowOrder_stateView(String str);
        void returnOrderMarkAddBean(String type);
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void getCarFlowOrder_statePresenter(String type,String OrderId, String Mileage,JsonArray CarFlow_Order_Mileage_Image_List);
        public abstract void getOrderMarkAddPresenter(String type,String OrderId, String NeedMarkTitle, String RealMarkFullAddress, String RealMarkLng, String RealMarkLat,String RemarkTitle, String RemarkContent, String MarkLevel, String MarkType, String CarFlow_Order_MarkId, JsonArray MarkImgUrlList,boolean ismark,String NeedMarkSimpleAddress,String NeedMarkFullAddress,String NeedMarkLng,String NeedMarkLat,String LinkName,String LinkPhone,boolean IsFinishMark);
    }
}
