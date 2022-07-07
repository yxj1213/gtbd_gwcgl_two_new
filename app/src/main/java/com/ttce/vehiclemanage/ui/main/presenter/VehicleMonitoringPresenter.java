package com.ttce.vehiclemanage.ui.main.presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.ttce.vehiclemanage.bean.CompanyItemBean;
import com.ttce.vehiclemanage.bean.MonitorResponseBean;
import com.ttce.vehiclemanage.ui.main.contract.VehicalMonitoringContract;

import java.util.List;

/**
 * 车辆管理
 * Created by hk on 2019/7/3.
 */

public class VehicleMonitoringPresenter extends VehicalMonitoringContract.Presenter {

    @Override
    public void getVehiclesListRequest() {
        mRxManage.add(mModel.getVehiclesList().subscribe(new RxSubscriber<List<MonitorResponseBean>>(mContext) {
            @Override
            protected void _onNext(List<MonitorResponseBean> monitorResponseBeanList) {
                mView.returnVehiclesList(monitorResponseBeanList);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }

    @Override
    public void getCompanyListRequest() {
        mRxManage.add(mModel.getCompanyLists().subscribe(new RxSubscriber<List<CompanyItemBean>>(mContext, false) {
            @Override
            protected void _onNext(List<CompanyItemBean> companyItemBeans) {
                mView.returnCompanyListRequest(companyItemBeans);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }

    @Override
    public void getIsStaffs() {
        mRxManage.add(mModel.getIsStaff().subscribe(new RxSubscriber<Boolean>(mContext, false) {
            @Override
            protected void _onNext(Boolean companyItemBeans) {
                mView.returnStaff(companyItemBeans);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}
