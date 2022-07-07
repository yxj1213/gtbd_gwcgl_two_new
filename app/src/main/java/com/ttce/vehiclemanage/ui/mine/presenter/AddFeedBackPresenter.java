package com.ttce.vehiclemanage.ui.mine.presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.ui.mine.constract.AddFeedBackConstract;

/**
 * Created by hk on 2019/7/10.
 */

public class AddFeedBackPresenter extends AddFeedBackConstract.Presenter {
    @Override
    public void addFeedBack(String ContentType, String Content, String LinkMan, String LinkPhone, String CompanyId) {
        mRxManage.add(mModel.addFeedBack(ContentType, Content, LinkMan, LinkPhone, CompanyId).subscribe(new RxSubscriber<String>(mContext) {
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
