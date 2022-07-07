package com.ttce.vehiclemanage.ui.mine.constract;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;

import rx.Observable;
import rx.Observer;

/**
 * Created by hk on 2019/7/10.
 */

public interface AddFeedBackConstract {

    interface Model extends BaseModel {
        Observable<String> addFeedBack(String ContentType, String Content, String LinkMan, String LinkPhone, String CompanyId);
    }

    interface View extends BaseView {
        void isAdd(String message);
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void addFeedBack(String ContentType, String Content, String LinkMan, String LinkPhone, String CompanyId);
    }

}
