package com.ttce.vehiclemanage.ui.usermanage.presenter;


import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.AppUpdateBean;
import com.ttce.vehiclemanage.bean.ChangeCompanyBean;
import com.ttce.vehiclemanage.bean.ChangeCompanySaveReturnBean;
import com.ttce.vehiclemanage.bean.CloseAccountStateBean;
import com.ttce.vehiclemanage.bean.UserInfoBean;
import com.ttce.vehiclemanage.ui.usermanage.contract.MainContract;

import java.util.List;

public class MainPresenter extends MainContract.Presenter {
    @Override
    public void getUserInfo() {
        mRxManage.add(mModel.getUserInfo().subscribe(new RxSubscriber<UserInfoBean>(mContext) {
            @Override
            protected void _onNext(UserInfoBean userInfoBean) {
                mView.getUserInfo(userInfoBean);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }

    @Override
    public void getChangeCompany() {
        mRxManage.add(mModel.getChangeCompany().subscribe(new RxSubscriber<List<ChangeCompanyBean>>(mContext) {
            @Override
            protected void _onNext(List<ChangeCompanyBean> userInfoBean) {
                mView.getChangeCompanys(userInfoBean);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }

    @Override
    public void getSaveChangeCompany(String companyId) {
        mRxManage.add(mModel.getSaveChangeCompany(companyId).subscribe(new RxSubscriber<ChangeCompanySaveReturnBean>(mContext) {
            @Override
            protected void _onNext(ChangeCompanySaveReturnBean code) {
                mView.getSaveChangeCompanys(code);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }

//    @Override
//    public void getRealCheckInfo() {
//        mRxManage.add(mModel.getRealCheckInfo().subscribe(new RxSubscriber<RealCheckBean>(mContext) {
//            @Override
//            protected void _onNext(RealCheckBean checkBean) {
//                mView.realCheckInfoData(checkBean);
//            }
//
//            @Override
//            protected void _onError(String message) {
//            }
//        }));
//    }

    @Override
    public void getAppUpdate() {
        mRxManage.add(mModel.getAppUpdate().subscribe(new RxSubscriber<AppUpdateBean>(mContext) {
            @Override
            protected void _onNext(AppUpdateBean checkBean) {
                mView.returnAppUpdateData(checkBean);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }

    @Override
    public void getAppLogout(String applyReason) {
        mRxManage.add(mModel.getAppLogout(applyReason).subscribe(new RxSubscriber<Boolean>(mContext) {
            @Override
            protected void _onNext(Boolean isLogout) {
                mView.returnAppLogout(isLogout);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
    public void getCloseAccountState() {
        mRxManage.add(mModel.getCloseAccountState().subscribe(new RxSubscriber<CloseAccountStateBean>(mContext,false) {
            @Override
            protected void _onNext(CloseAccountStateBean closeAccountStateBean) {
                mView.returnCloseAccountState(closeAccountStateBean);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
    @Override
    public void logout() {
        mRxManage.add(mModel.logout().subscribe(new RxSubscriber<String>(mContext) {
            @Override
            protected void _onNext(String rs) {
                mView.logout();
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }
}
