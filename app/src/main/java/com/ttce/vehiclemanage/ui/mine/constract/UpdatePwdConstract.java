package com.ttce.vehiclemanage.ui.mine.constract;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;

import rx.Observable;

/**
 * Created by hk on 2019/7/12.
 */

public interface UpdatePwdConstract {

    interface Model extends BaseModel {
        Observable<String> updatePwd(String OldPassword, String NewPassword, String ConfirmNewPassword);
    }

    interface View extends BaseView {
        void isUpdate(String message);
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void updatePwd(String OldPassword, String NewPassword, String ConfirmNewPassword);
    }

}
