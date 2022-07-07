package com.ttce.vehiclemanage.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DomainCheck {
    /**
     * 检查端口是否合法
     *
     * @param domain
     * @return 返回"LEGAL"成功
     * 其余就返回不合法消息
     */
    public static  boolean isNetPort(int port) {
        boolean flag = false;
        if (port >= 0 && port <= 65535) {
            flag = true;
        }
        return flag;
    }
    /**
     * 检查域名是否合法
     *
     * @param domain
     * @return 返回"LEGAL"成功
     * 其余就返回不合法消息
     */

    public static boolean domainCheckLegal(String domain) {
        String regex = "^((https|http|ftp|rtsp|mms)?://)"
                + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@
                + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
                + "|" // 允许IP和DOMAIN（域名）
                + "([0-9a-z_!~*'()-]+\\.)*" // 域名- www.
//                 + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名
                + "[a-z]{2,6})" // first level domain- .com or .museum
                + "(:[0-9]{1,4})?" // 端口- :80
                + "((/?)|" // a slash isn't required if there is no file name
                + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
        return match( regex ,domain );
    }
    /**
     * @param regex
     * 正则表达式字符串
     * @param str
     * 要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

}
