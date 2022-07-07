package com.ttce.vehiclemanage.ui.map.model;

import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.ui.map.contract.InstructContract;

import rx.Observable;

/**
 * Created by hk on 2019/7/4.
 */

public class InstructModel implements InstructContract.Model {

    @Override
    public Observable<String> sendPljs(int Time, int HuanCun, String OptionPassword, String InstructId, String DeviceId) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("Time", Time)
                .add("HuanCun", HuanCun)
                .add("OptionPassword", OptionPassword)
                .add("InstructId", InstructId)
                .add("DeviceId", DeviceId);
        return Api.getDefault(HostType.BASE_HOST).sendPljs(params.build())
                .compose(RxHelper.<String>handleResultSet());
    }

    @Override
    public Observable<String> sendDdbj(int IsOpen, String OptionPassword, String InstructId, String DeviceId) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("IsOpen", IsOpen)
                .add("OptionPassword", OptionPassword)
                .add("InstructId", InstructId)
                .add("DeviceId", DeviceId);
        return Api.getDefault(HostType.BASE_HOST).sendDdbj(params.build())
                .compose(RxHelper.<String>handleResultSet());
    }

    @Override
    public Observable<String> sendAcc(int IsOpen, String OptionPassword, String InstructId, String DeviceId) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("IsOpen", IsOpen)
                .add("OptionPassword", OptionPassword)
                .add("InstructId", InstructId)
                .add("DeviceId", DeviceId);
        return Api.getDefault(HostType.BASE_HOST).sendAcc(params.build())
                .compose(RxHelper.<String>handleResultSet());
    }

    @Override
    public Observable<String> sendWybj(int IsOpen, int Distance, String OptionPassword, String InstructId, String DeviceId) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("IsOpen", IsOpen)
                .add("Distance", Distance)
                .add("OptionPassword", OptionPassword)
                .add("InstructId", InstructId)
                .add("DeviceId", DeviceId);
        return Api.getDefault(HostType.BASE_HOST).sendWybj(params.build())
                .compose(RxHelper.<String>handleResultSet());
    }

    @Override
    public Observable<String> sendDdtx(int IsOpen, int Voltage, String OptionPassword, String InstructId, String DeviceId) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("IsOpen", IsOpen)
                .add("Voltage", Voltage)
                .add("OptionPassword", OptionPassword)
                .add("InstructId", InstructId)
                .add("DeviceId", DeviceId);
        return Api.getDefault(HostType.BASE_HOST).sendDdtx(params.build())
                .compose(RxHelper.<String>handleResultSet());
    }

    @Override
    public Observable<String> sendCsbj(int IsOpen, int ContinueValue, int MaxSpeedValue, String OptionPassword, String InstructId, String DeviceId) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("IsOpen", IsOpen)
                .add("ContinueValue", ContinueValue)
                .add("MaxSpeedValue", MaxSpeedValue)
                .add("OptionPassword", OptionPassword)
                .add("InstructId", InstructId)
                .add("DeviceId", DeviceId);
        return Api.getDefault(HostType.BASE_HOST).sendCsbj(params.build())
                .compose(RxHelper.<String>handleResultSet());
    }
}
