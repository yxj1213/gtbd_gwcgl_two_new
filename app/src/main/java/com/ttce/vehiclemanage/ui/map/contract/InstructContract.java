package com.ttce.vehiclemanage.ui.map.contract;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;

import rx.Observable;


/**
 * 指令
 * Created by hk on 2019/7/4.
 */

public interface InstructContract {

    interface Model extends BaseModel {

        Observable<String> sendPljs(int Time, int HuanCun, String OptionPassword, String InstructId, String DeviceId);

        Observable<String> sendDdbj(int IsOpen, String OptionPassword, String InstructId, String DeviceId);

        Observable<String> sendAcc(int IsOpen, String OptionPassword, String InstructId, String DeviceId);

        Observable<String> sendWybj(int IsOpen, int Distance, String OptionPassword, String InstructId, String DeviceId);

        Observable<String> sendDdtx(int IsOpen, int Voltage, String OptionPassword, String InstructId, String DeviceId);

        Observable<String> sendCsbj(int IsOpen, int ContinueValue, int MaxSpeedValue, String OptionPassword, String InstructId, String DeviceId);

    }

    interface View extends BaseView {

        void returnPljs(String isSend);

        void returnDdbj(String isSend);

        void returnAcc(String isSend);

        void returnWybj(String isSend);

        void returnDdtx(String isSend);

        void returnCsbj(String isSend);

    }

    abstract static class Presenter extends BasePresenter<View, Model> {

        public abstract void sendPljsRequest(int Time, int HuanCun, String OptionPassword, String InstructId, String DeviceId);

        public abstract void sendDdbjRequest(int IsOpen, String OptionPassword, String InstructId, String DeviceId);

        public abstract void sendAccRequest(int IsOpen, String OptionPassword, String InstructId, String DeviceId);

        public abstract void sendWybjRequest(int IsOpen, int Distance, String OptionPassword, String InstructId, String DeviceId);

        public abstract void sendDdtxRequest(int IsOpen, int Voltage, String OptionPassword, String InstructId, String DeviceId);

        public abstract void sendCsbjRequest(int IsOpen, int ContinueValue, int MaxSpeedValue, String OptionPassword, String InstructId, String DeviceId);

    }
}
