package com.ttce.vehiclemanage.ui.mine.presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.ExSysNoticeSetBean;
import com.ttce.vehiclemanage.ui.mine.constract.MessageSetConstract;

/**
 * Created by hk on 2019/7/11.
 */

public class MessageSetPresenter extends MessageSetConstract.Presenter {
    @Override
    public void getMessageSet() {
        mRxManage.add(mModel.getMessageSet().subscribe(new RxSubscriber<ExSysNoticeSetBean>(mContext) {
            @Override
            protected void _onNext(ExSysNoticeSetBean exSysNoticeSetBean) {
                mView.getMessageSet(exSysNoticeSetBean);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }

    @Override
    public void updateMessage(String NoticeKey, int NoticeValue) {
        mRxManage.add(mModel.updateMeassage(NoticeKey, NoticeValue).subscribe(new RxSubscriber<String>(mContext) {
            @Override
            protected void _onNext(String s) {
                mView.updateMessage(s);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }
}
