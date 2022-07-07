package com.ttce.vehiclemanage.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MarkerDetailsBean {

    @SerializedName("OrderId")
    private String orderId;
    @SerializedName("NeedMarkTitle")
    private String needMarkTitle;
    @SerializedName("NeedMarkSimpleAddress")
    private String needMarkSimpleAddress;
    @SerializedName("NeedMarkFullAddress")
    private String needMarkFullAddress;
    @SerializedName("NeedMarkLng")
    private double needMarkLng;
    @SerializedName("NeedMarkLat")
    private double needMarkLat;
    @SerializedName("LinkName")
    private String linkName;
    @SerializedName("LinkPhone")
    private String linkPhone;
    @SerializedName("MarkLevel")
    private int markLevel;
    @SerializedName("MarkType")
    private int markType;
    @SerializedName("MarkTypeFormat")
    private Object markTypeFormat;
    @SerializedName("RealMarkSimpleAddress")
    private String realMarkSimpleAddress;
    @SerializedName("RealMarkFullAddress")
    private String realMarkFullAddress;
    @SerializedName("RealMarkLng")
    private double realMarkLng;
    @SerializedName("RealMarkLat")
    private double realMarkLat;
    @SerializedName("RealMarkUserId")
    private String realMarkUserId;
    @SerializedName("RealMarkStaffId")
    private String realMarkStaffId;
    @SerializedName("RealMarkTime")
    private Object realMarkTime;
    @SerializedName("IsMark")
    private boolean isMark;
    @SerializedName("RemarkTitle")
    private String remarkTitle;
    @SerializedName("RemarkContent")
    private String remarkContent;
    @SerializedName("CreateTime")
    private String createTime;
    @SerializedName("CreateTimeFormat")
    private String createTimeFormat;
    @SerializedName("CreateBy")
    private String createBy;
    @SerializedName("CreateByUserName")
    private String createByUserName;
    @SerializedName("UpdateTime")
    private Object updateTime;
    @SerializedName("UpdateTimeFormat")
    private String updateTimeFormat;
    @SerializedName("UpdateBy")
    private String updateBy;
    @SerializedName("UpdateByUserName")
    private String updateByUserName;
    @SerializedName("CarFlow_Order_Mark_Image_List")
    private List<CarFlowOrderMarkImageListBean> carFlow_Order_Mark_Image_List;
    @SerializedName("CarFlow_Order_MarkId")
    private String carFlow_Order_MarkId;
 @SerializedName("RealMarkTimeFormat")
    private String RealMarkTimeFormat;

    public String getRealMarkTimeFormat() {
        return RealMarkTimeFormat;
    }

    public void setRealMarkTimeFormat(String realMarkTimeFormat) {
        RealMarkTimeFormat = realMarkTimeFormat;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getNeedMarkTitle() {
        return needMarkTitle;
    }

    public void setNeedMarkTitle(String needMarkTitle) {
        this.needMarkTitle = needMarkTitle;
    }

    public String getNeedMarkSimpleAddress() {
        return needMarkSimpleAddress;
    }

    public void setNeedMarkSimpleAddress(String needMarkSimpleAddress) {
        this.needMarkSimpleAddress = needMarkSimpleAddress;
    }

    public String getNeedMarkFullAddress() {
        return needMarkFullAddress;
    }

    public void setNeedMarkFullAddress(String needMarkFullAddress) {
        this.needMarkFullAddress = needMarkFullAddress;
    }

    public double getNeedMarkLng() {
        return needMarkLng;
    }

    public void setNeedMarkLng(double needMarkLng) {
        this.needMarkLng = needMarkLng;
    }

    public double getNeedMarkLat() {
        return needMarkLat;
    }

    public void setNeedMarkLat(double needMarkLat) {
        this.needMarkLat = needMarkLat;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public int getMarkLevel() {
        return markLevel;
    }

    public void setMarkLevel(int markLevel) {
        this.markLevel = markLevel;
    }

    public int getMarkType() {
        return markType;
    }

    public void setMarkType(int markType) {
        this.markType = markType;
    }

    public Object getMarkTypeFormat() {
        return markTypeFormat;
    }

    public void setMarkTypeFormat(Object markTypeFormat) {
        this.markTypeFormat = markTypeFormat;
    }

    public String getRealMarkSimpleAddress() {
        return realMarkSimpleAddress;
    }

    public void setRealMarkSimpleAddress(String realMarkSimpleAddress) {
        this.realMarkSimpleAddress = realMarkSimpleAddress;
    }

    public String getRealMarkFullAddress() {
        return realMarkFullAddress;
    }

    public void setRealMarkFullAddress(String realMarkFullAddress) {
        this.realMarkFullAddress = realMarkFullAddress;
    }

    public double getRealMarkLng() {
        return realMarkLng;
    }

    public void setRealMarkLng(double realMarkLng) {
        this.realMarkLng = realMarkLng;
    }

    public double getRealMarkLat() {
        return realMarkLat;
    }

    public void setRealMarkLat(double realMarkLat) {
        this.realMarkLat = realMarkLat;
    }

    public String getRealMarkUserId() {
        return realMarkUserId;
    }

    public void setRealMarkUserId(String realMarkUserId) {
        this.realMarkUserId = realMarkUserId;
    }

    public String getRealMarkStaffId() {
        return realMarkStaffId;
    }

    public void setRealMarkStaffId(String realMarkStaffId) {
        this.realMarkStaffId = realMarkStaffId;
    }

    public Object getRealMarkTime() {
        return realMarkTime;
    }

    public void setRealMarkTime(Object realMarkTime) {
        this.realMarkTime = realMarkTime;
    }

    public boolean isIsMark() {
        return isMark;
    }

    public void setIsMark(boolean isMark) {
        this.isMark = isMark;
    }

    public String getRemarkTitle() {
        return remarkTitle==null?"":remarkTitle;
    }

    public void setRemarkTitle(String remarkTitle) {
        this.remarkTitle = remarkTitle;
    }

    public String getRemarkContent() {
        return remarkContent==null?"":remarkContent;
    }

    public void setRemarkContent(String remarkContent) {
        this.remarkContent = remarkContent;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeFormat() {
        return createTimeFormat==null?"":createTimeFormat;
    }

    public void setCreateTimeFormat(String createTimeFormat) {
        this.createTimeFormat = createTimeFormat;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateByUserName() {
        return createByUserName;
    }

    public void setCreateByUserName(String createByUserName) {
        this.createByUserName = createByUserName;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateTimeFormat() {
        return updateTimeFormat;
    }

    public void setUpdateTimeFormat(String updateTimeFormat) {
        this.updateTimeFormat = updateTimeFormat;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateByUserName() {
        return updateByUserName;
    }

    public void setUpdateByUserName(String updateByUserName) {
        this.updateByUserName = updateByUserName;
    }

    public List<CarFlowOrderMarkImageListBean> getCarFlow_Order_Mark_Image_List() {
        return carFlow_Order_Mark_Image_List;
    }

    public void setCarFlow_Order_Mark_Image_List(List<CarFlowOrderMarkImageListBean> carFlow_Order_Mark_Image_List) {
        this.carFlow_Order_Mark_Image_List = carFlow_Order_Mark_Image_List;
    }

    public String getCarFlow_Order_MarkId() {
        return carFlow_Order_MarkId;
    }

    public void setCarFlow_Order_MarkId(String carFlow_Order_MarkId) {
        this.carFlow_Order_MarkId = carFlow_Order_MarkId;
    }

    public static class CarFlowOrderMarkImageListBean {
        @SerializedName("OrderId")
        private String orderId;
        @SerializedName("OrderMarkId")
        private String orderMarkId;
        @SerializedName("MarkImageUrl")
        private String markImageUrl;
        @SerializedName("MarkImageThumbnailUrl")
        private Object markImageThumbnailUrl;
        @SerializedName("MarkImageSort")
        private int markImageSort;
        @SerializedName("CreateTime")
        private String createTime;
        @SerializedName("CreateTimeFormat")
        private String createTimeFormat;
        @SerializedName("CreateBy")
        private Object createBy;
        @SerializedName("CreateByUserName")
        private Object createByUserName;
        @SerializedName("UpdateTime")
        private Object updateTime;
        @SerializedName("UpdateTimeFormat")
        private Object updateTimeFormat;
        @SerializedName("UpdateBy")
        private Object updateBy;
        @SerializedName("UpdateByUserName")
        private Object updateByUserName;
        @SerializedName("CarFlow_Order_Mark_ImageId")
        private String carFlow_Order_Mark_ImageId;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getOrderMarkId() {
            return orderMarkId;
        }

        public void setOrderMarkId(String orderMarkId) {
            this.orderMarkId = orderMarkId;
        }

        public String getMarkImageUrl() {
            return markImageUrl;
        }

        public void setMarkImageUrl(String markImageUrl) {
            this.markImageUrl = markImageUrl;
        }

        public Object getMarkImageThumbnailUrl() {
            return markImageThumbnailUrl;
        }

        public void setMarkImageThumbnailUrl(Object markImageThumbnailUrl) {
            this.markImageThumbnailUrl = markImageThumbnailUrl;
        }

        public int getMarkImageSort() {
            return markImageSort;
        }

        public void setMarkImageSort(int markImageSort) {
            this.markImageSort = markImageSort;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateTimeFormat() {
            return createTimeFormat;
        }

        public void setCreateTimeFormat(String createTimeFormat) {
            this.createTimeFormat = createTimeFormat;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public Object getCreateByUserName() {
            return createByUserName;
        }

        public void setCreateByUserName(Object createByUserName) {
            this.createByUserName = createByUserName;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getUpdateTimeFormat() {
            return updateTimeFormat;
        }

        public void setUpdateTimeFormat(Object updateTimeFormat) {
            this.updateTimeFormat = updateTimeFormat;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateByUserName() {
            return updateByUserName;
        }

        public void setUpdateByUserName(Object updateByUserName) {
            this.updateByUserName = updateByUserName;
        }

        public String getCarFlow_Order_Mark_ImageId() {
            return carFlow_Order_Mark_ImageId;
        }

        public void setCarFlow_Order_Mark_ImageId(String carFlow_Order_Mark_ImageId) {
            this.carFlow_Order_Mark_ImageId = carFlow_Order_Mark_ImageId;
        }
    }
}
