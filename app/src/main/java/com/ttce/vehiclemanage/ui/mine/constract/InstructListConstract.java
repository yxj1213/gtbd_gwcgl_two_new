package com.ttce.vehiclemanage.ui.mine.constract;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.ttce.vehiclemanage.bean.InstructDetailBean;

import java.util.List;

import rx.Observable;

/**
 * Created by hk on 2019/7/12.
 */

public interface InstructListConstract {

    interface Model extends BaseModel {
        Observable<List<InstructDetailBean>> getInstrustList(String Type, String DeviceId, String PlateNumber, int Page);
    }

    interface View extends BaseView {
        void returnData(List<InstructDetailBean> dataList);
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void getInstructList(String Type, String DeviceId, String PlateNumber, int Page);
    }

}
