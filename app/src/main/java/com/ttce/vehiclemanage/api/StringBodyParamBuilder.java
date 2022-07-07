package com.ttce.vehiclemanage.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ttce.vehiclemanage.app.AppApplication;
import com.ttce.vehiclemanage.ui.usermanage.manager.UserManager;
import com.ttce.vehiclemanage.utils.DeviceUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 *
 */

public class StringBodyParamBuilder {

    private JsonObject jsonObject;

    private StringBodyParamBuilder() {
        jsonObject = new JsonObject();
        jsonObject.addProperty("DevName", "");
        jsonObject.addProperty("SoftVer", "");
        jsonObject.addProperty("TerminalIdentify", "21");
        jsonObject.addProperty("IMEI", DeviceUtils.getDeviceId(AppApplication.getAppContext()));
        if (UserManager.isLogin()) {
            //TODO 2021.11.9
//            jsonObject.addProperty("NowUserId", UserManager.getLoginBean().getUserId());
            jsonObject.addProperty("CompanyId", UserManager.getLoginBean().getCompanyId());
        }
    }

    public StringBodyParamBuilder add(String key, int value) {
        jsonObject.addProperty(key, value + "");
        return this;
    }


    public StringBodyParamBuilder add(String key, String value) {
        jsonObject.addProperty(key, value);
        return this;
    }

    public StringBodyParamBuilder add(String key, boolean value) {
        jsonObject.addProperty(key, value);
        return this;
    }

    public StringBodyParamBuilder add(String key, double value) {
        jsonObject.addProperty(key, value);
        return this;
    }

    public StringBodyParamBuilder add(String key, JsonArray value) {
        jsonObject.add(key, value);
        return this;
    }
    public StringBodyParamBuilder add(String key, JsonObject value) {
        jsonObject.add(key, value);
        return this;
    }

    public static StringBodyParamBuilder create() {
        return new StringBodyParamBuilder();
    }

    public RequestBody build() {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
    }
}
