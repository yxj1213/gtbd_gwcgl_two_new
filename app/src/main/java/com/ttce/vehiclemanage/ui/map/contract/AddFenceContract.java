package com.ttce.vehiclemanage.ui.map.contract;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.ttce.vehiclemanage.bean.FenceListBean;

import java.util.List;

import rx.Observable;

/**
 * 围栏列表
 * Created by hk on 2019/7/9.
 */

public interface AddFenceContract {

    interface Model extends BaseModel {
        Observable<Boolean> addFence(String name, int radius, double lat, double lng, String ads, String alarmTypes, String deviceIds);
        Observable<Boolean> updateFence(String id,String name, int radius, double lat, double lng, String ads, String alarmTypes, String deviceIds);
    }

    interface View extends BaseView {
        void onFenceAdded(boolean result);
        void onFenceUpdate(boolean result);
    }

    abstract static class AddFencePresenter extends BasePresenter<View, Model> {
        public abstract void addFence(String name,int radius,double lat,double lng,String ads,String alarmTypes,String deviceIds);
        public abstract void updateFence(String id,String name,int radius,double lat,double lng,String ads,String alarmTypes,String deviceIds);
    }

}
