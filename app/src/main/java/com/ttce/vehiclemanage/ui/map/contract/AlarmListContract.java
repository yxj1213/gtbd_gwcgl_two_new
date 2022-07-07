package com.ttce.vehiclemanage.ui.map.contract;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.ttce.vehiclemanage.bean.AlarmListBean;

import java.util.List;

import rx.Observable;

/**
 * Created by hk on 2019/7/5.
 */

public interface AlarmListContract {

    interface Model extends BaseModel {
        Observable<List<AlarmListBean>> getAlarmList(int type, String DeviceId, int Page);

        Observable<String> updateState(String AlarmId, String Remark);
    }

    interface View extends BaseView {
        void getAlarmList(List<AlarmListBean> dataList);

        void updateState(String id);
    }

    abstract static class AlarmListPresenter extends BasePresenter<View, Model> {
        public abstract void getAlarmList(int type, String DeviceId, int Page);

        public abstract void updateState(String AlarmId, String Remark);
    }
}
