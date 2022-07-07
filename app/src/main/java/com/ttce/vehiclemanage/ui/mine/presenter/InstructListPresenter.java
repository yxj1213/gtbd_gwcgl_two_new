package com.ttce.vehiclemanage.ui.mine.presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.InstructDetailBean;
import com.ttce.vehiclemanage.ui.mine.constract.InstructListConstract;

import java.util.List;

/**
 * Created by hk on 2019/7/12.
 */

public class InstructListPresenter extends InstructListConstract.Presenter {
    @Override
    public void getInstructList(String Type, String DeviceId, String PlateNumber, int Page) {
        mRxManage.add(mModel.getInstrustList(Type, DeviceId, PlateNumber, Page).subscribe(new RxSubscriber<List<InstructDetailBean>>(mContext, false) {
            @Override
            protected void _onNext(List<InstructDetailBean> instructDetailBeans) {
                mView.returnData(instructDetailBeans);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}
