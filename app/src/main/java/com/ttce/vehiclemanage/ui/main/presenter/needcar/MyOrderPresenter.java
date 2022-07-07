package com.ttce.vehiclemanage.ui.main.presenter.needcar;

import android.util.Log;

import com.google.gson.JsonArray;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.CarLatLngBean;
import com.ttce.vehiclemanage.bean.CarRecordsBean;
import com.ttce.vehiclemanage.bean.EmptyOrderBean;
import com.ttce.vehiclemanage.bean.MarkerDetailsBean;
import com.ttce.vehiclemanage.bean.TravelListBean;
import com.ttce.vehiclemanage.ui.main.contract.needcar.MyOrderConstract;

import java.util.List;


/**
 * 车辆管理
 * Created by hk on 2019/7/3.
 */

public class MyOrderPresenter extends MyOrderConstract.Presenter {
    @Override
    public void getTravelData(String deviceId, String startTime, String endTime,int type,int state,int alllistsize) {
        mRxManage.add(mModel.getTravelData(deviceId,startTime,endTime).subscribe(new RxSubscriber<TravelListBean>(mContext) {
            @Override
            protected void _onNext(TravelListBean travelListBean) {
                mView.drawTravel(travelListBean,type,state,alllistsize);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }
    @Override
    public void getgetOrderDriverGrabPresenter(String OrderId,String GrabCarCompanyId,String GrabCarDepartmentId,String GrabCarId) {
        mRxManage.add(mModel.getOrderDriverGrabModel(OrderId,GrabCarCompanyId,GrabCarDepartmentId,GrabCarId).subscribe(new RxSubscriber<String>(mContext) {
            @Override
            protected void _onNext(String monitorResponseBeanList) {
                mView.returnOrderDriverGrab(monitorResponseBeanList);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
    @Override
    public void getMyOrderCancelBeans(String carFlow_OrderId,int CheckState, String CheckReason,String type) {
        mRxManage.add(mModel.getmyOrderCancelModel(carFlow_OrderId,CheckState,CheckReason,type).subscribe(new RxSubscriber<String>(mContext) {
            @Override
            protected void _onNext(String monitorResponseBeanList) {
                mView.returnMyOrderCancel(monitorResponseBeanList,type);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }

    @Override
    public void getMyOrderDetailsBeans(String carFlow_OrderId,String OrderListType) {
        mRxManage.add(mModel.getmyOrderDetailsModel(carFlow_OrderId,OrderListType).subscribe(new RxSubscriber<EmptyOrderBean>(mContext) {
            @Override
            protected void _onNext(EmptyOrderBean monitorResponseBeanList) {
                mView.returnMyOrderDetails(monitorResponseBeanList);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }

    @Override
    public void getAllOrderListBeans(int pageIndex,JsonArray escapeOrderState,String type,String StartCreateTime,String EndCreateTime,String PlateNumber,String KeyWord,String CarFlow_OrderId) {
        mRxManage.add(mModel.getAllOrderListBeans(pageIndex,escapeOrderState,type, StartCreateTime, EndCreateTime, PlateNumber, KeyWord, CarFlow_OrderId).subscribe(new RxSubscriber<List<EmptyOrderBean>>(mContext,false) {
            @Override
            protected void _onNext(List<EmptyOrderBean> monitorResponseBeanList) {
                mView.returnAllOrderList(monitorResponseBeanList);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
    @Override
    public void getOrderDispatchAuditBeans(String carFlow_OrderId, int CheckState, String CheckReason, JsonArray Order_Assigns_Car_List,String type) {
        mRxManage.add(mModel.getOrderDispatchAuditModel(carFlow_OrderId,CheckState,CheckReason,Order_Assigns_Car_List).subscribe(new RxSubscriber<String>(mContext) {
            @Override
            protected void _onNext(String monitorResponseBeanList) {
                mView.returnMyOrderCancel(monitorResponseBeanList,type);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }

    @Override
    public void getCarFlowPositionBean(String carId) {
        mRxManage.add(mModel.getCarFlowPositionModel(carId).subscribe(new RxSubscriber<CarLatLngBean>(mContext) {
            @Override
            protected void _onNext(CarLatLngBean latLng) {
                mView.returnCarFlowPosition(latLng);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
    @Override
    public void getOrderStateLogListBean(String orderId,String orderModule) {
        mRxManage.add(mModel.getOrderStateLogListModel(orderId,orderModule).subscribe(new RxSubscriber<List<CarRecordsBean>>(mContext) {
            @Override
            protected void _onNext(List<CarRecordsBean> carRecordsBeans) {
                mView.returnOrderStateLogList(carRecordsBeans);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
    @Override
    public void getUserSureGoCarBeans(String orderId,String type) {
        mRxManage.add(mModel.getUserSureGoCarModel(orderId,type).subscribe(new RxSubscriber<String>(mContext) {
            @Override
            protected void _onNext(String carRecordsBeans) {
                mView.returnMyOrderCancel(carRecordsBeans,type);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
    @Override
    public void getOrderMarkAddPresenter(String type,String OrderId, String NeedMarkTitle, String RealMarkFullAddress, String RealMarkLng, String RealMarkLat,String RemarkTitle, String RemarkContent, String MarkLevel, String MarkType, String CarFlow_Order_MarkId, JsonArray MarkImgUrlList,boolean ismark,String NeedMarkSimpleAddress,String NeedMarkFullAddress,String NeedMarkLng,String NeedMarkLat,String LinkName,String LinkPhone,boolean IsFinishMark) {
        mRxManage.add(mModel.getOrderMarkAddModel( OrderId,  NeedMarkTitle,  RealMarkFullAddress,  RealMarkLng,  RealMarkLat, RemarkTitle,  RemarkContent,  MarkLevel,  MarkType,  CarFlow_Order_MarkId,  MarkImgUrlList, ismark, NeedMarkSimpleAddress, NeedMarkFullAddress, NeedMarkLng, NeedMarkLat, LinkName, LinkPhone, IsFinishMark).subscribe(new RxSubscriber<String>(mContext) {
            @Override
            protected void _onNext(String str) {
                mView.returnOrderMarkAddBean(type);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }

    @Override
    public void getOrderMarkmodelPresenter(String CarFlow_Order_MarkId) {
        mRxManage.add(mModel.getOrderMarkModel(CarFlow_Order_MarkId).subscribe(new RxSubscriber<MarkerDetailsBean>(mContext) {
            @Override
            protected void _onNext(MarkerDetailsBean str) {
                mView.returnOrderMarkModelBean(str);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}
