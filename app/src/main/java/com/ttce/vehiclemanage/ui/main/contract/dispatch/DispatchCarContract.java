package com.ttce.vehiclemanage.ui.main.contract.dispatch;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.ttce.vehiclemanage.bean.CarPositionBean;
import com.ttce.vehiclemanage.bean.DispatchDriverListBean;

import java.util.List;

import rx.Observable;

public interface DispatchCarContract {
    interface Model extends BaseModel {
        Observable<List<CarPositionBean>> getOrderCarListModel(String CarUseState,String CarTypeId);
        Observable<List<DispatchDriverListBean>> getOrderDriverListModel();
        Observable<String> getOrderDriverGrabModel(String OrderId,String GrabCarCompanyId,String GrabCarDepartmentId,String GrabCarId);
    }

    interface View extends BaseView {
        void returnOrderCarList(List<CarPositionBean> carPositionBeans);
        void returnDriverList(List<DispatchDriverListBean> carPositionBeans);
        void returnOrderDriverGrab(String carPositionBeans);
    }

    abstract static class Presenter extends BasePresenter<DispatchCarContract.View, DispatchCarContract.Model> {
        public abstract void getCarPositionBeanPresenter(String CarUseState,String CarTypeId);
        public abstract void getDriverBeanPresenter();
        public abstract void getgetOrderDriverGrabPresenter(String OrderId,String GrabCarCompanyId,String GrabCarDepartmentId,String GrabCarId);
    }
}
