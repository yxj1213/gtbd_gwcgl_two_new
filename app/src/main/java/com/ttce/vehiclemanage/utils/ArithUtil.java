package com.ttce.vehiclemanage.utils;

import java.math.BigDecimal;

public class ArithUtil {
    // 默认除法运算精度
    private static final int DEF_DIV_SCALE = 10;
    // 这个类不能实例化
    private ArithUtil() {
    }
    /**
     * 提供精确的加法运算。
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1  被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }
    public static double mulMore(double x,double ...args) {
        BigDecimal b1 = new BigDecimal(Double.toString(x));
        double total=1;
        for(int i=0;i<args.length;i++){
            BigDecimal b2 = new BigDecimal(Double.toString(total));
            BigDecimal b3 = new BigDecimal(Double.toString(args[i]));
            total=b2.multiply(b3).doubleValue();
        }
        BigDecimal b4= new BigDecimal(Double.toString(total));
        return b1.multiply(b4).doubleValue();
    }
    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v1));
        BigDecimal one = new BigDecimal(v2);
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    public static double div(double v1, double v2, int scale,int style) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v1));
        BigDecimal one = new BigDecimal(v2);
        return b.divide(one, scale, style).doubleValue();
    }
    /**
     * 保留小数位，由scale参数指 定精度，以后的数字四舍五入。
     * @param v1 未四舍五入前
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return  按scale精确保留后的小数
     */
    public static String accurateDecimal(String v1,int scale){
        BigDecimal bd=new BigDecimal(v1);
        return bd.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }
}
