package com.ttce.vehiclemanage.ui.locus.contract;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.ttce.vehiclemanage.bean.CompanyItemBean;
import com.ttce.vehiclemanage.bean.TravelItemBean;

import java.util.List;

import rx.Observable;

public interface TrackContract {

    interface Model extends BaseModel {
        Observable<TravelItemBean> getTrackData(String deviceId);
        Observable<List<CompanyItemBean>> getDeptData();
    }

    interface View extends BaseView {
        void drawTrack(TravelItemBean currentPositionData);
        void buildDeptTree(List<CompanyItemBean> deptListData);
    }

    abstract static class Presenter extends BasePresenter<TrackContract.View, TrackContract.Model> {
        public abstract void getTrackData(String deviceId);
        public abstract void getDeptData();
    }

}
