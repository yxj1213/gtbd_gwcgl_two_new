package com.ttce.vehiclemanage.ui.map.contract;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.ttce.vehiclemanage.bean.EquipmentDetailBean;

import rx.Observable;

/**
 * Created by hk on 2019/7/5.
 */

public interface EquipmentDetailContract {

    interface Model extends BaseModel {
        Observable<EquipmentDetailBean> getDatail(String DeviceId);
    }

    interface View extends BaseView {
        void returnDatail(EquipmentDetailBean equipmentDetailBean);
    }

    abstract static class EquipmentPresenter extends BasePresenter<View, Model> {
        public abstract void getDatail(String DeviceId);
    }

}
