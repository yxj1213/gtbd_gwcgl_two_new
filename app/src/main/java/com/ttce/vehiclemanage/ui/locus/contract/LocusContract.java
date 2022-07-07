package com.ttce.vehiclemanage.ui.locus.contract;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.ttce.vehiclemanage.bean.TravelListBean;

import java.util.List;

import rx.Observable;

public interface LocusContract {

    interface Model extends BaseModel {
        Observable<TravelListBean> getTravelData(String deviceId, String startTime, String endTime);
    }

    interface View extends BaseView {
        void drawTravel(TravelListBean travelListBeanList);
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void getTravelData(String deviceId, String startTime, String endTime);
    }
}
