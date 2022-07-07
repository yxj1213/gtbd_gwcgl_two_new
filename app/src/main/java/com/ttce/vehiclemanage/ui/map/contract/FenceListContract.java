package com.ttce.vehiclemanage.ui.map.contract;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.ttce.vehiclemanage.bean.CompanysItemBean;
import com.ttce.vehiclemanage.bean.FenceListBean;

import java.util.List;

import rx.Observable;

/**
 * 围栏列表
 * Created by hk on 2019/7/9.
 */

public interface FenceListContract {

    interface Model extends BaseModel {
        Observable<List<FenceListBean>> getFenceList(String DeviceId, int Page);
        Observable<Boolean> updateFenceAlarmType(String id,String DeviceId, String alarmTypes);

    }

    interface View extends BaseView {
        void getFenceList(List<FenceListBean> dataList);
        void onFenceAlarmTypeUpdate(FenceListBean fenceListBean,String alarmTypes,boolean result);
    }

    abstract static class FenceListPresenter extends BasePresenter<View, Model> {
        public abstract void getFenceList(String DeviceId, int Page);
        public abstract void updateFenceAlarmType(FenceListBean fenceListBean,String DeviceId, String alarmTypes);
    }

}
