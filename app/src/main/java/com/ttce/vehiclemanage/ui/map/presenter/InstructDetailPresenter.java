package com.ttce.vehiclemanage.ui.map.presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.InstructDetailBean;
import com.ttce.vehiclemanage.ui.map.contract.InstructDetailContract;

/**
 * Created by hk on 2019/7/5.
 */

public class InstructDetailPresenter extends InstructDetailContract.Presenter {
    @Override
    public void getDatail(String InstructId) {

        mRxManage.add(mModel.getDetail(InstructId).subscribe(new RxSubscriber<InstructDetailBean>(mContext) {
            @Override
            protected void _onNext(InstructDetailBean instructDetailBean) {
                mView.returnDetail(instructDetailBean);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));

    }
}
