package com.ttce.vehiclemanage.ui.mine.constract;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;

import rx.Observable;

/**
 * Created by hk on 2019/7/10.
 */

public interface AddSosConstract {

    interface Model extends BaseModel {
        Observable<String> addSos(String Title, String Content, String LinkMan, String LinkPhone, String Password, String CompanyId,String Address);
    }

    interface View extends BaseView {
        void isAdd(String message);
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void addSos(String Title, String Content, String LinkMan, String LinkPhone, String Password, String CompanyId,String Address);
    }

}
