package com.ttce.vehiclemanage.ui.locus.presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.TravelListBean;
import com.ttce.vehiclemanage.ui.locus.contract.LocusContract;

import java.util.List;

public class LocusPresenter extends LocusContract.Presenter {

    @Override
    public void getTravelData(String deviceId, String startTime, String endTime) {
        mRxManage.add(mModel.getTravelData(deviceId,startTime,endTime).subscribe(new RxSubscriber<TravelListBean>(mContext) {
            @Override
            protected void _onNext(TravelListBean travelListBean) {
                mView.drawTravel(travelListBean);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }
}
