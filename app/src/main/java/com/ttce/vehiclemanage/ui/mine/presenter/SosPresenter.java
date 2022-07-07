package com.ttce.vehiclemanage.ui.mine.presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.SosBean;
import com.ttce.vehiclemanage.ui.mine.constract.SosListConstract;

import java.util.List;

/**
 * Created by hk on 2019/7/10.
 */

public class SosPresenter extends SosListConstract.Presenter {
    @Override
    public void getSosList(int Page) {
        mRxManage.add(mModel.getSosList(Page).subscribe(new RxSubscriber<List<SosBean>>(mContext) {
            @Override
            protected void _onNext(List<SosBean> sosBeans) {
                mView.returnList(sosBeans);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}
