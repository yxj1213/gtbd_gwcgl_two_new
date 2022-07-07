package com.ttce.vehiclemanage.ui.locus.presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.CompanyItemBean;
import com.ttce.vehiclemanage.bean.TravelListBean;
import com.ttce.vehiclemanage.ui.locus.contract.DeptContract;
import com.ttce.vehiclemanage.ui.locus.contract.LocusContract;

import java.util.List;

public class DeptPresenter extends DeptContract.Presenter {

    @Override
    public void getDeptData() {
        mRxManage.add(mModel.getDeptData().subscribe(new RxSubscriber<List<CompanyItemBean>>(mContext) {
            @Override
            protected void _onNext(List<CompanyItemBean> companyItemBeanList) {
                mView.buildDeptTree(companyItemBeanList);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }

}
