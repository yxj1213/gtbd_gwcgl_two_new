package com.ttce.vehiclemanage.ui.search.constract;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.ttce.vehiclemanage.bean.MonitorResponseBean;

import java.util.List;

import rx.Observable;

public interface SearchConstract {

    interface Model extends BaseModel {
        Observable<List<MonitorResponseBean>> searchVehicles(String SearchKey);

        Observable<String[]> getSearchHistory();

    }

    interface View extends BaseView {
        void searchVehicles(List<MonitorResponseBean> monitorResponseBeanList);

        void onHistory(String[] historyKeys);
    }

    abstract static class Presenter extends BasePresenter<SearchConstract.View, SearchConstract.Model> {
        public abstract void searchVehicles(String SearchKey);

        public abstract void getSearchHistory();
    }

}
