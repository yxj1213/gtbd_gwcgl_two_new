package com.ttce.vehiclemanage.ui.map.presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.CompanysItemBean;
import com.ttce.vehiclemanage.bean.FenceListBean;
import com.ttce.vehiclemanage.ui.map.contract.FenceListContract;

import java.util.List;

/**
 * Created by hk on 2019/7/9.
 */

public class FenceListPresenter extends FenceListContract.FenceListPresenter {

    @Override
    public void getFenceList(String DeviceId, int Page) {
        mRxManage.add(mModel.getFenceList(DeviceId, Page).subscribe(new RxSubscriber<List<FenceListBean>>(mContext) {
            @Override
            protected void _onNext(List<FenceListBean> companysItemBeans) {
                mView.getFenceList(companysItemBeans);
            }

            @Override
            protected void _onError(String message) {
//                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
                mView.showErrorTip(message);
            }
        }));
    }

    @Override
    public void updateFenceAlarmType(FenceListBean fenceListBean, String DeviceId, String alarmTypes) {
        mRxManage.add(mModel.updateFenceAlarmType(fenceListBean.getId(),DeviceId, alarmTypes).subscribe(new RxSubscriber<Boolean>(mContext) {
            @Override
            protected void _onNext(Boolean result) {
                mView.onFenceAlarmTypeUpdate(fenceListBean,alarmTypes,result);
            }

            @Override
            protected void _onError(String message) {
//                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
                mView.showErrorTip(message);
            }
        }));
    }

}
