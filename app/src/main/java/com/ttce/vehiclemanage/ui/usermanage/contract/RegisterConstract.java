package com.ttce.vehiclemanage.ui.usermanage.contract;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;

import rx.Observable;

/**
 * Created by hk on 2019/7/12.
 */

public interface RegisterConstract {

    interface Model extends BaseModel {
        Observable<String> realCheckAdd(String RealName, String IdCard, String LinkPhone, String UserEmail);
    }

    interface View extends BaseView {
        void realCheckMessage(String message);
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void realCheckAdd(String RealName, String IdCard, String LinkPhone, String UserEmail);
    }

}
