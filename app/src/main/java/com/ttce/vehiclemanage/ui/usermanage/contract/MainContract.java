package com.ttce.vehiclemanage.ui.usermanage.contract;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.ttce.vehiclemanage.bean.AppUpdateBean;
import com.ttce.vehiclemanage.bean.ChangeCompanyBean;
import com.ttce.vehiclemanage.bean.ChangeCompanySaveReturnBean;
import com.ttce.vehiclemanage.bean.CloseAccountStateBean;
import com.ttce.vehiclemanage.bean.UserInfoBean;

import java.util.List;

import rx.Observable;

public interface MainContract {
    interface Model extends BaseModel {
        Observable<UserInfoBean> getUserInfo();
        Observable<List<ChangeCompanyBean>> getChangeCompany();
        Observable<ChangeCompanySaveReturnBean> getSaveChangeCompany(String companyId);
//        Observable<RealCheckBean> getRealCheckInfo();
        Observable<AppUpdateBean> getAppUpdate();
        Observable<Boolean> getAppLogout(String applyReason);
        Observable<CloseAccountStateBean> getCloseAccountState();
        Observable<String> logout();
    }

    interface View extends BaseView {
        void getUserInfo(UserInfoBean userInfoBean);
        void getChangeCompanys(List<ChangeCompanyBean> list);
        void getSaveChangeCompanys(ChangeCompanySaveReturnBean str);
//        void realCheckInfoData(RealCheckBean checkBean);
        void returnAppUpdateData(AppUpdateBean appUpdateBean);
        void returnAppLogout(Boolean isLogout);
        void returnCloseAccountState(CloseAccountStateBean closeAccountStateBean);
        void logout();
    }

    abstract static class Presenter extends BasePresenter<MainContract.View, MainContract.Model> {
        public abstract void getUserInfo();
//        public abstract void getRealCheckInfo();
        public abstract void getChangeCompany();
        public abstract void getSaveChangeCompany(String companyId);
        public abstract void getAppUpdate();
        public abstract void getAppLogout(String applyReason);
        public abstract void getCloseAccountState();
        public abstract void logout();
    }
}
