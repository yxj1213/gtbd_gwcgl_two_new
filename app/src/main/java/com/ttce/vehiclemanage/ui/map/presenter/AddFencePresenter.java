package com.ttce.vehiclemanage.ui.map.presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.FenceListBean;
import com.ttce.vehiclemanage.ui.map.contract.AddFenceContract;
import com.ttce.vehiclemanage.ui.map.contract.FenceListContract;

import java.util.List;

/**
 * Created by hk on 2019/7/9.
 */

public class AddFencePresenter extends AddFenceContract.AddFencePresenter {

    @Override
    public void addFence(String name,int radius,double lat,double lng,String ads,String alarmTypes,String deviceIds) {
        mRxManage.add(mModel.addFence(name,radius,lat,lng,ads,alarmTypes,deviceIds).subscribe(new RxSubscriber<Boolean>(mContext) {
            @Override
            protected void _onNext(Boolean result) {
                mView.onFenceAdded(result);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }

    @Override
    public void updateFence(String id,String name,int radius,double lat,double lng,String ads,String alarmTypes,String deviceIds) {
        mRxManage.add(mModel.updateFence(id,name,radius,lat,lng,ads,alarmTypes,deviceIds).subscribe(new RxSubscriber<Boolean>(mContext) {
            @Override
            protected void _onNext(Boolean result) {
                mView.onFenceUpdate(result);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }
}
