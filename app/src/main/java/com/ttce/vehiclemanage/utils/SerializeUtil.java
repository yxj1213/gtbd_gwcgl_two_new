package com.ttce.vehiclemanage.utils;

import com.google.gson.Gson;

public class SerializeUtil {

    public static String serialize(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static <T> T deSerialize(String str, Class<T> c) {
        Gson gson = new Gson();
        return gson.fromJson(str, c);
    }

}
