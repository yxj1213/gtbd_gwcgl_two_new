package com.ttce.vehiclemanage.ui.usermanage.presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.LoginBean;
import com.ttce.vehiclemanage.bean.LoginImageVerificationBean;
import com.ttce.vehiclemanage.bean.PostServerBean;
import com.ttce.vehiclemanage.bean.VerificationBean;
import com.ttce.vehiclemanage.ui.usermanage.contract.LoginContract;
import com.ttce.vehiclemanage.ui.usermanage.manager.UserManager;

/**
 * Created by hk on 2019/7/1.
 */

public class   LoginPresenter extends LoginContract.Presenter {
    @Override
    public void loginRequest(String name, String password, String verificationId, String verificationCode) {
        mRxManage.add(mModel.getLoginData(name, password, verificationId, verificationCode).subscribe(new RxSubscriber<LoginBean>(mContext) {
            @Override
            protected void _onNext(LoginBean user) {
                UserManager.saveLoginBean(user);
                mView.returnLoginData(user);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }

    @Override
    public void isNeedImgValid() {
        // TODO: 2019/7/24 检测是否需要图片验证码
    }

    @Override
    public void getImgVaild() {
        mRxManage.add(mModel.getImgVaild().subscribe(new RxSubscriber<VerificationBean>(mContext,true) {
            @Override
            protected void _onNext(VerificationBean verificationBean) {
                mView.returnImgVaild(verificationBean);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }
    @Override
    public void getImgLoginVaild() {
        mRxManage.add(mModel.getImgLoginVaild().subscribe(new RxSubscriber<LoginImageVerificationBean>(mContext,true) {
            @Override
            protected void _onNext(LoginImageVerificationBean verificationBean) {
                 mView.returnImgLoginVaild(verificationBean);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }
    @Override
    public void getCheckImgLoginVaild(String VerificationId,double ImgVerificationpositionX) {
        mRxManage.add(mModel.getCheckImgLoginVaild(VerificationId,ImgVerificationpositionX).subscribe(new RxSubscriber<String>(mContext,true) {
            @Override
            protected void _onNext(String verificationBean) {
                mView.returnCheckImgLoginVaild("成功");
            }

            @Override
            protected void _onError(String message) {
                mView.returnCheckImgLoginVaild("失败");
            }
        }));
    }

    @Override
    public void getPostServerBeanPresenter() {
        mRxManage.add(mModel.getPostServerBean().subscribe(new RxSubscriber<PostServerBean>(mContext,true) {
            @Override
            protected void _onNext(PostServerBean verificationBean) {
                mView.returnPostServerBeanView(verificationBean);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }
}
