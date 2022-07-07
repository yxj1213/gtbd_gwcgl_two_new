package com.ttce.vehiclemanage.bean;

import java.io.Serializable;

public class CommonListBean implements Serializable {
    private static final long serialVersionUID = 5172451599725645744L;

    private String Key;
    private String Value;
    private int Sort;
    private boolean selected;

    public CommonListBean() {
    }

    public CommonListBean(String key, String value) {
        Key = key;
        Value = value;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String Key) {
        this.Key = Key;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String Value) {
        this.Value = Value;
    }

    public int getSort() {
        return Sort;
    }

    public void setSort(int sort) {
        Sort = sort;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommonListBean that = (CommonListBean) o;

        return Value != null ? Value.equals(that.Value) : that.Value == null;
    }

    @Override
    public int hashCode() {
        return Value != null ? Value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return Key;
    }
}
