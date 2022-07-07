package com.ttce.vehiclemanage.bean;

import android.text.TextUtils;

public class WorkBeanchBean{
    private int img;
    private String title;
    private String title2;
    private String titleid;
    private long time;
    public WorkBeanchBean(String title) {
        this.title = title;
    }
    public WorkBeanchBean(String title,long time) {
        this.title = title;
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public WorkBeanchBean(int img, String title) {
        this.img = img;
        this.title = title;
    }
    public WorkBeanchBean(String titleid, String title) {
        this.titleid = titleid;
        this.title = title;
    }

    public WorkBeanchBean(String title, String title2, String titleid) {
        this.title = title;
        this.title2 = title2;
        this.titleid = titleid;
    }

    public String getTitleid() {
        return titleid==null?"":titleid;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public void setTitleid(String titleid) {
        this.titleid = titleid;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;//地址相等
        }

        if (obj == null) {
            return false;//非空性：对于任意非空引用x，x.equals(null)应该返回false。
        }

        if (obj instanceof MonitorResponseBean) {
            WorkBeanchBean other = (WorkBeanchBean) obj;
            //需要比较的字段相等，则这两个对象相等
            if (equalsStr(this.title, other.title)) {
                return true;
            }
        }

        return false;
    }

    private boolean equalsStr(String str1, String str2) {
        if (TextUtils.isEmpty(str1) && TextUtils.isEmpty(str2)) {
            return true;
        }
        if (!TextUtils.isEmpty(str1) && str1.equals(str2)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (title == null ? 0 : title.hashCode());
        return result;
    }
}
