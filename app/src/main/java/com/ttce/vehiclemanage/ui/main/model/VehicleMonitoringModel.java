package com.ttce.vehiclemanage.ui.main.model;

import android.util.Log;

import com.alibaba.fastjson.JSONPObject;
import com.google.gson.JsonObject;
import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.bean.CompanyItemBean;
import com.ttce.vehiclemanage.bean.MonitorResponseBean;
import com.ttce.vehiclemanage.ui.main.contract.VehicalMonitoringContract;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import rx.Observable;

/**
 * 车辆管理
 * Created by hk on 2019/7/3.
 */

public class VehicleMonitoringModel implements VehicalMonitoringContract.Model {

    @Override
        public Observable<List<MonitorResponseBean>> getVehiclesList() {
            StringBodyParamBuilder params = StringBodyParamBuilder.create();
            return Api.getDefault(HostType.BASE_HOST).getVehiclesList(params.build())
                    .compose(RxHelper.<List<MonitorResponseBean>>handleResult());
        }

    @Override
    public Observable<List<CompanyItemBean>> getCompanyLists() {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        return  Api.getDefault(HostType.BASE_HOST).getCompanyList(params.build())
                .compose(RxHelper.handleResult());
    }

    @Override
    public Observable<Boolean> getIsStaff() {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        return  Api.getDefault(HostType.BASE_HOST).getIsStaff(params.build())
                .compose(RxHelper.handleResult());
    }
}
