package com.ttce.vehiclemanage.ui.locus.presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.CompanyItemBean;
import com.ttce.vehiclemanage.bean.TravelItemBean;
import com.ttce.vehiclemanage.ui.locus.contract.TrackContract;

import java.util.List;

public class TrackPresenter extends TrackContract.Presenter {

    @Override
    public void getTrackData(String deviceId) {
        mRxManage.add(mModel.getTrackData(deviceId).subscribe(new RxSubscriber<TravelItemBean>(mContext,false) {
            @Override
            protected void _onNext(TravelItemBean travelItemBean) {
                mView.drawTrack(travelItemBean);
            }

            @Override
            protected void _onError(String message) {
//                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
                mView.showErrorTip(message);
            }
        }));
    }

    @Override
    public void getDeptData() {
        mRxManage.add(mModel.getDeptData().subscribe(new RxSubscriber<List<CompanyItemBean>>(mContext) {
            @Override
            protected void _onNext(List<CompanyItemBean> companyItemBeanList) {
                mView.buildDeptTree(companyItemBeanList);
            }

            @Override
            protected void _onError(String message) {
//                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
                mView.showErrorTip(message);
            }
        }));
    }
}
