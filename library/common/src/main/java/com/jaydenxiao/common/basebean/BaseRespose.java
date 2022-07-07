package com.jaydenxiao.common.basebean;

import java.io.Serializable;

/**
 * des:封装服务器返回数据
 */
public class BaseRespose<T> implements Serializable {
public int Code;
    public int PageIndex;
    public int PageSize;
    public int CountPage;
    public int CountItems;
    public String Message;
    public String Description;

    public T Data;

    public boolean success() {
        return 1 == Code;
    }

    @Override
    public String toString() {
        return "BaseResposePage{" +
                "code='" + Code + '\'' +
                ", PageIndex='" + PageIndex + '\'' +
                ", PageSize='" + PageSize + '\'' +
                ", CountPage='" + CountPage + '\'' +
                ", CountItems='" + CountItems + '\'' +
                ", msg='" + Message + '\'' +
                ", description='" + Description+'\'' +
                ", data=" + Data +
                '}';
    }
}
