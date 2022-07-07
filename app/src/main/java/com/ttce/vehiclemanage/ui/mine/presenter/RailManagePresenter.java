package com.ttce.vehiclemanage.ui.mine.presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.FenceListBean;
import com.ttce.vehiclemanage.ui.mine.constract.RailManageContract;

import java.util.List;

/**
 * Created by hk on 2019/7/9.
 */

public class RailManagePresenter extends RailManageContract.RailManagePresenter {
    @Override
    public void getNewFenceList(int Page) {
        mRxManage.add(mModel.getNewFenceList(Page).subscribe(new RxSubscriber<List<FenceListBean>>(mContext) {
            @Override
            protected void _onNext(List<FenceListBean> fenceListBeans) {
                mView.getNewFenceList(fenceListBeans);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }

    @Override
    public void delRail(String id) {
        mRxManage.add(mModel.delRail(id).subscribe(new RxSubscriber<Boolean>(mContext) {
            @Override
            protected void _onNext(Boolean aBoolean) {
                mView.delRail(id,aBoolean);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }

    @Override
    public void addDevice(String id, String devices) {
        mRxManage.add(mModel.addDevice(id,devices).subscribe(new RxSubscriber<Boolean>(mContext) {
            @Override
            protected void _onNext(Boolean aBoolean) {
                mView.addDevice(id,aBoolean);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }


}
