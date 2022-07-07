package com.ttce.vehiclemanage.bean;

import com.google.gson.annotations.SerializedName;

public class CarPlateTypeListBean {

    public CarPlateTypeListBean(String name) {
        this.name = name;
    }

    public CarPlateTypeListBean(String name, String id, String carFlow_CarPlateTypeId, boolean isSelected, boolean isMapPoint, boolean isDefault) {
        this.name = name;
        this.carFlow_CarPlateTypeId = carFlow_CarPlateTypeId;
        this.isSelected = isSelected;
        this.isMapPoint = isMapPoint;
        this.id = id;
        this.isDefault = isDefault;
    }

    public CarPlateTypeListBean(String name, String carFlow_CarPlateTypeId) {
        this.name = name;
        this.carFlow_CarPlateTypeId = carFlow_CarPlateTypeId;
    }
    public CarPlateTypeListBean(String name, String carFlow_CarPlateTypeId,String id,String updateByUserName,boolean isSelected) {
        this.name = name;
        this.carFlow_CarPlateTypeId = carFlow_CarPlateTypeId;
        this.id = id;
        this.updateByUserName = updateByUserName;
        this.isSelected = isSelected;
    }
    public CarPlateTypeListBean(String name, String carFlow_CarPlateTypeId,String id,String updateByUserName,String updateBy) {
        this.name = name;
        this.carFlow_CarPlateTypeId = carFlow_CarPlateTypeId;
        this.id = id;
        this.updateByUserName = updateByUserName;
        this.updateBy = updateBy;
    }
        @SerializedName("Name")
        private String name;
        @SerializedName("Sort")
        private int sort;
        @SerializedName("CreateTime")
        private String createTime;
        @SerializedName("CreateTimeFormat")
        private String createTimeFormat;
        @SerializedName("CreateBy")
        private String createBy;
        @SerializedName("CreateByUserName")
        private String createByUserName;
        @SerializedName("UpdateTime")
        private String updateTime;
        @SerializedName("UpdateTimeFormat")
        private String updateTimeFormat;
        @SerializedName("UpdateBy")
        private String updateBy;
        @SerializedName("UpdateByUserName")
        private String updateByUserName;
        @SerializedName("CarFlow_CarPlateTypeId")
        private String carFlow_CarPlateTypeId;
        @SerializedName("IsSelected")
        private boolean isSelected;
        private boolean isMapPoint;//用来控制起点终点途经点是选择的还是输入的
        private boolean isDefault;
        private String id;

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isMapPoint() {
        return isMapPoint;
    }

    public void setMapPoint(boolean mapPoint) {
        isMapPoint = mapPoint;
    }

    public String getName() {
            return name==null?"":name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
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

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
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

    public String getCarFlow_CarPlateTypeId() {
        return carFlow_CarPlateTypeId;
    }

    public void setCarFlow_CarPlateTypeId(String carFlow_CarPlateTypeId) {
        this.carFlow_CarPlateTypeId = carFlow_CarPlateTypeId;
    }

    public boolean isIsSelected() {
            return isSelected;
        }

        public void setIsSelected(boolean isSelected) {
            this.isSelected = isSelected;
        }
}
