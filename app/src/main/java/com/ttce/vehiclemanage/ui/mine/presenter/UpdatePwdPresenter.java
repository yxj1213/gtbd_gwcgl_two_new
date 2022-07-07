package com.ttce.vehiclemanage.ui.mine.presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.ui.mine.constract.UpdatePwdConstract;

/**
 * Created by hk on 2019/7/12.
 */

public class UpdatePwdPresenter extends UpdatePwdConstract.Presenter {
    @Override
    public void updatePwd(String OldPassword, String NewPassword, String ConfirmNewPassword) {
        mRxManage.add(mModel.updatePwd(OldPassword, NewPassword, ConfirmNewPassword).subscribe(new RxSubscriber<String>(mContext) {
            @Override
            protected void _onNext(String s) {
                mView.isUpdate(s);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }
}
