package com.ttce.vehiclemanage.ui.main.contract.needcar;

import com.google.gson.JsonArray;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.ttce.vehiclemanage.bean.EmptyOrderBean;
import com.ttce.vehiclemanage.bean.IsOrderingBean;
import com.ttce.vehiclemanage.bean.Message2Bean;
import com.ttce.vehiclemanage.bean.OrderSuccessBean;
import com.ttce.vehiclemanage.bean.OtherUserId;


import rx.Observable;

public interface MyNeedCarConstract {
    interface Model extends BaseModel {
        Observable<EmptyOrderBean> getEmptyOrderBeanModel();
        Observable<OrderSuccessBean> addOrderBeanModel(String ycyhuserId,String ycyhStaffId,String ycms,String yclx_lcid,String yclx,String jsr_value,String jsr_id,String jsr_saffid,String ycsy_bt,String ycsy_nr,String ycsj_value,String ycsj_kssj,String ycsj_jssj,String yxcx,String yx_hplx,String yx_cp,String yxzws,String ddld_userid,String ddld_staffid,String scrs,JsonArray markListBeans, JsonArray passengerListBeans);
        Observable<OtherUserId> OrderOtherUserIdBeanModel(String xm,String lxdh);
        Observable<IsOrderingBean> getOrderIsOrderingModel();
        Observable<String> setDefaultOrderCarType(String defaultUseCarTypeId);
        Observable<String> getOrderDispatchAuditModel(String carFlow_OrderId, int CheckState, String CheckReason, JsonArray Order_Assigns_Car_List,String AssignsType);
        Observable<Message2Bean> StaffMagStateModel();
    }

    interface View extends BaseView {
        void returnEmptyOrderBean(EmptyOrderBean emptyOrderBean);
        void returnAddOrderBean(OrderSuccessBean emptyOrderBean);
        void returnOtherUserIdBean(OtherUserId otherUserId);
        void returnFailBean(String message,String type);
        void returnOrderIsOrdering(IsOrderingBean message);
        void returnStaffMagState(Message2Bean MessageBean);
    }

    abstract static class Presenter extends BasePresenter<View, Model>{
        public abstract void getEmptyOrderBeans();
        public abstract void getAddOrderBean(String ycyhuserId, String ycyhStaffId, String ycms, String yclx_lcid, String yclx, String jsr_value, String jsr_id, String jsr_saffid, String ycsy_bt, String ycsy_nr, String ycsj_value, String ycsj_kssj, String ycsj_jssj, String yxcx, String yx_hplx, String yx_cp, String yxzws, String ddld_userid, String ddld_staffid, String scrs, JsonArray markListBeans, JsonArray passengerListBeans);
        public abstract void getOrderOtherUserIdBean(String type,String xm,String lxdh);
        public abstract void getOrderIsOrdering();
        public abstract void setDefaultOrderCarType(String type,String defaultUseCarTypeId);
        public abstract void getOrderDispatchAuditBeans(String carFlow_OrderId, int CheckState, String CheckReason,JsonArray Order_Assigns_Car_List,String type,String AssignsType);
        public abstract void StaffMagStatePresenter();
    }
}
