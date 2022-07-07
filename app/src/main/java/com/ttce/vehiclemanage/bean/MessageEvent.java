package com.ttce.vehiclemanage.bean;

public class MessageEvent<T> {
    private int msgWhat;
    private T msgObj;
    private T msgObjOther;
    private T msgObjThird;
    private long totalPage;

    private int totalCount;

    public MessageEvent() {
    }

    public MessageEvent(int msgWhat) {
        this.msgWhat = msgWhat;
    }

    public MessageEvent(int msgWhat, T msgObj) {
        this.msgWhat = msgWhat;
        this.msgObj = msgObj;
    }

    public MessageEvent(int msgWhat, T msgObj, T msgObjOther) {
        this.msgWhat = msgWhat;
        this.msgObj = msgObj;
        this.msgObjOther = msgObjOther;
    }

    public MessageEvent(int msgWhat, T msgObj, T msgObjOther, T msgObjThird) {
        this.msgWhat = msgWhat;
        this.msgObj = msgObj;
        this.msgObjOther = msgObjOther;
        this.msgObjThird = msgObjThird;
    }

    public MessageEvent(int msgWhat, T msgObj, long totalPage) {
        this.msgWhat = msgWhat;
        this.msgObj = msgObj;
        this.totalPage = totalPage;
    }

    public int getMsgWhat() {
        return msgWhat;
    }

    public void setMsgWhat(int msgWhat) {
        this.msgWhat = msgWhat;
    }

    public T getMsgObj() {
        return msgObj;
    }

    public void setMsgObj(T msgObj) {
        this.msgObj = msgObj;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public T getMsgObjOther() {
        return msgObjOther;
    }

    public void setMsgObjOther(T msgObjOther) {
        this.msgObjOther = msgObjOther;
    }

    public T getMsgObjThird() {
        return msgObjThird;
    }

    public void setMsgObjThird(T msgObjThird) {
        this.msgObjThird = msgObjThird;
    }
}
