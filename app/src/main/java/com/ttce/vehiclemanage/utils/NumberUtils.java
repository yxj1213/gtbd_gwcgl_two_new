package com.ttce.vehiclemanage.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class NumberUtils {

    /**
     * 对double数据进行取精度.
     *
     * @param value double数据.
     * @param scale 精度位数(保留的小数位数).
     * @return 精度计算后的数据.
     */
    public static double round(double value, int scale) {
        BigDecimal mData = new BigDecimal(value).setScale(scale, BigDecimal.ROUND_HALF_UP);
        double d = mData.doubleValue();
        mData = null;
        return d;
    }

    /**
     * 获取map中第一个key值
     *
     * @param mapList 数据源
     * @return
     */
    public static String getKeyOrNull(List<Map<String, String>> mapList, int m) {
        String obj = "";
        Map<String, String> map = mapList.get(m);
        for (Map.Entry<String, String> str : map.entrySet()) {
            if ((str.getKey()).equals("Key") || (str.getKey()).equals("Text")) {
                obj = str.getValue();
            }
        }
        return obj;
    }


    /**
     * 获取map中第一个数据值
     *
     * @param mapList 数据源
     * @return
     */
    public static String getValueOrNull(List<Map<String, String>> mapList, int m) {
        String obj = "";
        Map<String, String> map = mapList.get(m);
        for (Map.Entry<String, String> str : map.entrySet()) {
            if ((str.getKey()).equals("Value")) {
                obj = str.getValue();
            }
        }
        return obj;
    }
}
