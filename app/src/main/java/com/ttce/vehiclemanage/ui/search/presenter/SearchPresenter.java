package com.ttce.vehiclemanage.ui.search.presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.MonitorResponseBean;
import com.ttce.vehiclemanage.ui.search.constract.SearchConstract;

import java.util.List;

public class SearchPresenter extends SearchConstract.Presenter {
    @Override
    public void searchVehicles(String SearchKey) {
        mRxManage.add(mModel.searchVehicles(SearchKey).subscribe(new RxSubscriber<List<MonitorResponseBean>>(mContext) {
            @Override
            protected void _onNext(List<MonitorResponseBean> monitorResponseBean) {
                mView.searchVehicles(monitorResponseBean);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }

    @Override
    public void getSearchHistory() {
        mRxManage.add(mModel.getSearchHistory().subscribe(new RxSubscriber<String[]>(mContext, false) {
            @Override
            protected void _onNext(String[] monitorResponseBean) {
                mView.onHistory(monitorResponseBean);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }
}
