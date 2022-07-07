package com.ttce.vehiclemanage.ui.main.presenter.dispatch;

import android.util.Log;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.ttce.vehiclemanage.bean.CarPositionBean;
import com.ttce.vehiclemanage.bean.DispatchDriverListBean;
import com.ttce.vehiclemanage.ui.main.contract.dispatch.DispatchCarContract;

import java.util.List;

public class DispatchCarPresenter extends DispatchCarContract.Presenter {
    @Override
    public void getCarPositionBeanPresenter(String CarUseState,String CarTypeId) {
        mRxManage.add(mModel.getOrderCarListModel(CarUseState,CarTypeId).subscribe(new RxSubscriber<List<CarPositionBean>>(mContext) {
            @Override
            protected void _onNext(List<CarPositionBean> monitorResponseBeanList) {
                mView.returnOrderCarList(monitorResponseBeanList);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }

    @Override
    public void getDriverBeanPresenter() {
        mRxManage.add(mModel.getOrderDriverListModel().subscribe(new RxSubscriber<List<DispatchDriverListBean>>(mContext) {
            @Override
            protected void _onNext(List<DispatchDriverListBean> monitorResponseBeanList) {
                mView.returnDriverList(monitorResponseBeanList);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
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
}
