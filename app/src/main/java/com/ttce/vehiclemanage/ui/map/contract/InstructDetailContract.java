package com.ttce.vehiclemanage.ui.map.contract;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.ttce.vehiclemanage.bean.InstructDetailBean;

import rx.Observable;


/**
 * Created by Administrator on 2019/7/5.
 */

public interface InstructDetailContract {

    interface Model extends BaseModel {
        Observable<InstructDetailBean> getDetail(String InstructId);
    }

    interface View extends BaseView {
        void returnDetail(InstructDetailBean instructDetailBean);
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void getDatail(String InstructId);
    }

}
