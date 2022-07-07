package com.ttce.vehiclemanage.ui.usermanage.manager;

import com.google.gson.reflect.TypeToken;
import com.ttce.vehiclemanage.app.SPDefaultHelper;
import com.ttce.vehiclemanage.bean.LoginBean;
import com.ttce.vehiclemanage.bean.MonitorResponseBean;
import com.ttce.vehiclemanage.utils.SerializeUtil;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hk on 2019/7/2.
 */

public class UserManager {

    public static LoginBean loginBean;

    /**
     * 判断当前账号是否登陆
     *
     * @return
     */
    public static boolean isLogin() {
        if (loginBean == null) {
            loginBean = SerializeUtil.deSerialize(SPDefaultHelper.getString(SPDefaultHelper.USER), LoginBean.class);
        }
        if (loginBean == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 缓存登陆账号相关信息
     *
     * @param bean
     */
    public static void saveLoginBean(LoginBean bean) {
        loginBean = bean;
    }

    /**
     * @param bean
     */
    public static void saveSerialize(LoginBean bean) {
        SPDefaultHelper.saveString(SPDefaultHelper.USER, SerializeUtil.serialize(bean));
    }

    /**
     * 获取登陆账号相关信息
     *
     * @return
     */
    public static LoginBean getLoginBean() {
        if (loginBean == null) {
            loginBean = SerializeUtil.deSerialize(SPDefaultHelper.getString(SPDefaultHelper.USER), LoginBean.class);
        }
        if (loginBean == null) {
            return new LoginBean();
        }
        return loginBean;
    }

    /**
     * 退出登陆，清楚本地账号相关数据
     */

    public static void logout() {
        loginBean = null;
        SPDefaultHelper.remove(SPDefaultHelper.USER);
    }
}
