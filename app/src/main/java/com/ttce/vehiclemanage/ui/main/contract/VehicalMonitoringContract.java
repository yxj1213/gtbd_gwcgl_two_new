package com.ttce.vehiclemanage.ui.main.contract;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.ttce.vehiclemanage.bean.CompanyItemBean;
import com.ttce.vehiclemanage.bean.MonitorResponseBean;

import java.util.List;

import rx.Observable;

/**
 * Created by hk on 2019/7/3.
 */

public interface VehicalMonitoringContract {

    interface Model extends BaseModel {



        Observable<List<MonitorResponseBean>> getVehiclesList();

        Observable<List<CompanyItemBean>> getCompanyLists();

        Observable<Boolean> getIsStaff();
    }

    interface View extends BaseView {


        void returnVehiclesList(List<MonitorResponseBean> monitorResponseBeanList);

        void returnCompanyListRequest(List<CompanyItemBean> dataList);

        void returnStaff(Boolean str);
    }

    abstract static class Presenter extends BasePresenter<View, Model> {


        public abstract void getVehiclesListRequest();
        public abstract void getCompanyListRequest();
        public abstract void getIsStaffs();
    }
}
