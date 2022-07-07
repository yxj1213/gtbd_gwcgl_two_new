package com.ttce.vehiclemanage.ui.main.presenter.needcar;

import com.google.gson.JsonArray;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.EmptyOrderBean;
import com.ttce.vehiclemanage.bean.IsOrderingBean;
import com.ttce.vehiclemanage.bean.Message2Bean;
import com.ttce.vehiclemanage.bean.OrderSuccessBean;
import com.ttce.vehiclemanage.bean.OtherUserId;
import com.ttce.vehiclemanage.ui.main.contract.needcar.MyNeedCarConstract;

import java.util.List;


/**
 * 车辆管理
 * Created by hk on 2019/7/3.
 */

public class MyNeedCarPresenter extends MyNeedCarConstract.Presenter {
    @Override
    public void StaffMagStatePresenter() {
        mRxManage.add(mModel.StaffMagStateModel().subscribe(new RxSubscriber<Message2Bean>(mContext, false) {
            @Override
            protected void _onNext(Message2Bean messageBean) {
                mView.returnStaffMagState(messageBean);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }
    @Override
    public void getEmptyOrderBeans() {
        mRxManage.add(mModel.getEmptyOrderBeanModel().subscribe(new RxSubscriber<EmptyOrderBean>(mContext) {
            @Override
            protected void _onNext(EmptyOrderBean monitorResponseBeanList) {
                mView.returnEmptyOrderBean(monitorResponseBeanList);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }

    @Override
    public void getAddOrderBean(String ycyhuserId, String ycyhStaffId, String ycms, String yclx_lcid, String yclx, String jsr_value, String jsr_id, String jsr_saffid, String ycsy_bt, String ycsy_nr, String ycsj_value, String ycsj_kssj, String ycsj_jssj, String yxcx, String yx_hplx, String yx_cp, String yxzws, String ddld_userid, String ddld_staffid, String scrs, JsonArray markListBeans, JsonArray passengerListBeans) {
        mRxManage.add(mModel.addOrderBeanModel(ycyhuserId,ycyhStaffId,ycms,yclx_lcid,yclx,jsr_value,jsr_id, jsr_saffid,ycsy_bt,ycsy_nr,ycsj_value,ycsj_kssj,ycsj_jssj,yxcx,yx_hplx,yx_cp,yxzws,ddld_userid,ddld_staffid,scrs,markListBeans, passengerListBeans).subscribe(new RxSubscriber<OrderSuccessBean>(mContext) {
            @Override
            protected void _onNext(OrderSuccessBean monitorResponseBeanList) {
                mView.returnAddOrderBean(monitorResponseBeanList);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }

    @Override
    public void getOrderOtherUserIdBean(String type,String xm, String lxdh) {
        mRxManage.add(mModel.OrderOtherUserIdBeanModel(xm,lxdh).subscribe(new RxSubscriber<OtherUserId>(mContext) {
            @Override
            protected void _onNext(OtherUserId monitorResponseBeanList) {
                mView.returnOtherUserIdBean(monitorResponseBeanList);
            }

            @Override
            protected void _onError(String message) {
                mView.returnFailBean(message,type);
            }
        }));
    }
    @Override
    public void getOrderIsOrdering() {
        mRxManage.add(mModel.getOrderIsOrderingModel().subscribe(new RxSubscriber<IsOrderingBean>(mContext) {
            @Override
            protected void _onNext(IsOrderingBean monitorResponseBeanList) {
                mView.returnOrderIsOrdering(monitorResponseBeanList);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
    @Override
    public void setDefaultOrderCarType(String type,String defaultUseCarTypeId) {
        mRxManage.add(mModel.setDefaultOrderCarType(defaultUseCarTypeId).subscribe(new RxSubscriber<String>(mContext) {
            @Override
            protected void _onNext(String monitorResponseBeanList) {
                mView.returnFailBean(monitorResponseBeanList,type);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
    @Override
    public void getOrderDispatchAuditBeans(String carFlow_OrderId, int CheckState, String CheckReason, JsonArray Order_Assigns_Car_List,String type,String AssignsType) {
        mRxManage.add(mModel.getOrderDispatchAuditModel(carFlow_OrderId,CheckState,CheckReason,Order_Assigns_Car_List,AssignsType).subscribe(new RxSubscriber<String>(mContext) {
            @Override
            protected void _onNext(String monitorResponseBeanList) {
                mView.returnFailBean(monitorResponseBeanList,type);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}
