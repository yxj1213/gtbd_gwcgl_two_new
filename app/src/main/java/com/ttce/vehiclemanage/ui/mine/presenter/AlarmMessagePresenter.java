package com.ttce.vehiclemanage.ui.mine.presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.AlarmListBean;
import com.ttce.vehiclemanage.ui.mine.constract.AlarmMessageConstract;

import java.util.List;

/**
 * Created by hk on 2019/7/19.
 */

public class AlarmMessagePresenter extends AlarmMessageConstract.Persenter {
    @Override
    public void getAlarmList(int type, String DeviceId, int Page) {
        mRxManage.add(mModel.getAlarmList(type, DeviceId, Page).subscribe(new RxSubscriber<List<AlarmListBean>>(mContext, false) {
            @Override
            protected void _onNext(List<AlarmListBean> alarmListBeans) {
                mView.returnData(alarmListBeans);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}
