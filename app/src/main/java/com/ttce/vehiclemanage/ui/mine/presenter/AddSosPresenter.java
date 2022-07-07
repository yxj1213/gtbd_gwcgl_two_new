package com.ttce.vehiclemanage.ui.mine.presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.ui.mine.constract.AddSosConstract;

/**
 * Created by hk on 2019/7/10.
 */

public class AddSosPresenter extends AddSosConstract.Presenter {
    @Override
    public void addSos(String Title, String Content, String LinkMan, String LinkPhone, String Password, String CompanyId, String Address) {
        mRxManage.add(mModel.addSos(Title, Content, LinkMan, LinkPhone, Password, CompanyId, Address).subscribe(new RxSubscriber<String>(mContext) {
            @Override
            protected void _onNext(String s) {
                mView.isAdd(s);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }
}
