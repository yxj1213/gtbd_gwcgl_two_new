package com.ttce.vehiclemanage.ui.mine.constract;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.ttce.vehiclemanage.bean.AlarmListBean;

import java.util.List;

import rx.Observable;

/**
 * Created by hk on 2019/7/19.
 */

public interface AlarmMessageConstract {

    interface Model extends BaseModel {
        Observable<List<AlarmListBean>> getAlarmList(int type, String DeviceId, int Page);
    }

    interface View extends BaseView {
        void returnData(List<AlarmListBean> dataList);
    }

    abstract static class Persenter extends BasePresenter<View, Model> {
        public abstract void getAlarmList(int type, String DeviceId, int Page);
    }

}
