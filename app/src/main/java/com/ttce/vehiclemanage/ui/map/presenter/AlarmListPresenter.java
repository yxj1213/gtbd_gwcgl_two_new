package com.ttce.vehiclemanage.ui.map.presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.AlarmListBean;
import com.ttce.vehiclemanage.ui.map.contract.AlarmListContract;

import java.util.List;

/**
 * Created by hk on 2019/7/5.
 */

public class AlarmListPresenter extends AlarmListContract.AlarmListPresenter {
    @Override
    public void getAlarmList(int type, String DeviceId, int Page) {
        mRxManage.add(mModel.getAlarmList(type, DeviceId, Page).subscribe(new RxSubscriber<List<AlarmListBean>>(mContext) {
            @Override
            protected void _onNext(List<AlarmListBean> alarmListBeans) {
                mView.getAlarmList(alarmListBeans);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }

    @Override
    public void updateState(String AlarmId, String Remark) {
        mRxManage.add(mModel.updateState(AlarmId, Remark).subscribe(new RxSubscriber<String>(mContext) {
            @Override
            protected void _onNext(String id) {
                mView.updateState(id);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}
