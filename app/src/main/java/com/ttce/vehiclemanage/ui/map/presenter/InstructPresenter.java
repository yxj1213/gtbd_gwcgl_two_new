package com.ttce.vehiclemanage.ui.map.presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.ui.map.contract.InstructContract;

/**
 * 指令
 * Created by hk on 2019/7/4.
 */

public class InstructPresenter extends InstructContract.Presenter {
    @Override
    public void sendPljsRequest(int Time, int HuanCun, String OptionPassword, String InstructId, String DeviceId) {
        mRxManage.add(mModel.sendPljs(Time, HuanCun, OptionPassword, InstructId, DeviceId).subscribe(new RxSubscriber<String>(mContext) {
            @Override
            protected void _onNext(String aBoolean) {
                mView.returnPljs(aBoolean);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }

    @Override
    public void sendDdbjRequest(int IsOpen, String OptionPassword, String InstructId, String DeviceId) {
        mRxManage.add(mModel.sendDdbj(IsOpen, OptionPassword, InstructId, DeviceId).subscribe(new RxSubscriber<String>(mContext) {
            @Override
            protected void _onNext(String aBoolean) {
                mView.returnDdbj(aBoolean);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }

    @Override
    public void sendAccRequest(int IsOpen, String OptionPassword, String InstructId, String DeviceId) {
        mRxManage.add(mModel.sendAcc(IsOpen, OptionPassword, InstructId, DeviceId).subscribe(new RxSubscriber<String>(mContext) {
            @Override
            protected void _onNext(String aBoolean) {
                mView.returnAcc(aBoolean);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }

    @Override
    public void sendWybjRequest(int IsOpen, int Distance, String OptionPassword, String InstructId, String DeviceId) {
        mRxManage.add(mModel.sendWybj(IsOpen, Distance, OptionPassword, InstructId, DeviceId).subscribe(new RxSubscriber<String>(mContext) {
            @Override
            protected void _onNext(String aBoolean) {
                mView.returnWybj(aBoolean);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }

    @Override
    public void sendDdtxRequest(int IsOpen, int Voltage, String OptionPassword, String InstructId, String DeviceId) {
        mRxManage.add(mModel.sendDdtx(IsOpen, Voltage, OptionPassword, InstructId, DeviceId).subscribe(new RxSubscriber<String>(mContext) {
            @Override
            protected void _onNext(String aBoolean) {
                mView.returnDdtx(aBoolean);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }

    @Override
    public void sendCsbjRequest(int IsOpen, int ContinueValue, int MaxSpeedValue, String OptionPassword, String InstructId, String DeviceId) {
        mRxManage.add(mModel.sendCsbj(IsOpen, ContinueValue, MaxSpeedValue, OptionPassword, InstructId, DeviceId).subscribe(new RxSubscriber<String>(mContext) {
            @Override
            protected void _onNext(String aBoolean) {
                mView.returnCsbj(aBoolean);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }
}
