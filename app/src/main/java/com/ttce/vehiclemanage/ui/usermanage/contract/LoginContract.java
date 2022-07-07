package com.ttce.vehiclemanage.ui.usermanage.contract;


import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.ttce.vehiclemanage.bean.LoginBean;
import com.ttce.vehiclemanage.bean.LoginImageVerificationBean;
import com.ttce.vehiclemanage.bean.PostServerBean;
import com.ttce.vehiclemanage.bean.VerificationBean;

import rx.Observable;

/**
 * Created by hk on 2019/7/1.
 */

public interface LoginContract {

    interface Model extends BaseModel {
        Observable<LoginBean> getLoginData(String name, String password, String verificationId, String verificationCode);

        Observable<VerificationBean> getImgVaild();

        Observable<LoginImageVerificationBean> getImgLoginVaild();

        Observable<String> getCheckImgLoginVaild(String VerificationId,double ImgVerificationpositionX);
        Observable<PostServerBean> getPostServerBean();
    }

    interface View extends BaseView {
        void returnLoginData(LoginBean newsDetail);

        void returnImgVaild(VerificationBean verificationBean);

        void returnImgLoginVaild(LoginImageVerificationBean loginImageVerificationBean);

        void returnCheckImgLoginVaild(String tag);

        void returnPostServerBeanView(PostServerBean postServerBean);
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void loginRequest(String name, String password, String verificationId, String verificationCode);

        public abstract void isNeedImgValid();

        public abstract void getImgVaild();

        public abstract void getImgLoginVaild();

        public abstract void getCheckImgLoginVaild(String VerificationId,double ImgVerificationpositionX);

        public abstract void getPostServerBeanPresenter();
    }
}
