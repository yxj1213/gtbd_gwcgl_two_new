package com.ttce.vehiclemanage.bean;

import java.io.Serializable;

/**
 * Created by hk on 2019/7/10.
 */

public class SosBean implements Serializable {

    private String Id;//	string
    private String Content;// string
    private String CreateUser;//  string
    private String CreateUserName;// string
    private String CreateTime;//  string($date-time)
    private String CreateTimeFormat;//  string
    private String HandleUser;//  string
    private String HandleUserName;//  string
    private String Address;
    private String Title;
    private String LinkMan;
    private String LinkPhone;
    private String HandleTime;// string($date-time)
    private String HandleTimeFormat;//  string
    private String CompanyId;//  string
    private String CompanyName;// string
    private int Status;// integer($int32)

    public String getId() {
        return Id == null ? "" : Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getContent() {
        return Content == null ? "" : Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getCreateUser() {
        return CreateUser == null ? "" : CreateUser;
    }

    public void setCreateUser(String createUser) {
        CreateUser = createUser;
    }

    public String getCreateUserName() {
        return CreateUserName == null ? "" : CreateUserName;
    }

    public void setCreateUserName(String createUserName) {
        CreateUserName = createUserName;
    }

    public String getCreateTime() {
        return CreateTime == null ? "" : CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getCreateTimeFormat() {
        return CreateTimeFormat == null ? "" : CreateTimeFormat;
    }

    public void setCreateTimeFormat(String createTimeFormat) {
        CreateTimeFormat = createTimeFormat;
    }

    public String getHandleUser() {
        return HandleUser == null ? "" : HandleUser;
    }

    public void setHandleUser(String handleUser) {
        HandleUser = handleUser;
    }

    public String getHandleUserName() {
        return HandleUserName == null ? "" : HandleUserName;
    }

    public void setHandleUserName(String handleUserName) {
        HandleUserName = handleUserName;
    }

    public String getHandleTime() {
        return HandleTime == null ? "" : HandleTime;
    }

    public void setHandleTime(String handleTime) {
        HandleTime = handleTime;
    }

    public String getHandleTimeFormat() {
        return HandleTimeFormat == null ? "" : HandleTimeFormat;
    }

    public void setHandleTimeFormat(String handleTimeFormat) {
        HandleTimeFormat = handleTimeFormat;
    }

    public String getCompanyId() {
        return CompanyId == null ? "" : CompanyId;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
    }

    public String getCompanyName() {
        return CompanyName == null ? "" : CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getAddress() {
        return Address == null ? "" : Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTitle() {
        return Title == null ? "" : Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getLinkMan() {
        return LinkMan == null ? "" : LinkMan;
    }

    public void setLinkMan(String linkMan) {
        LinkMan = linkMan;
    }

    public String getLinkPhone() {
        return LinkPhone == null ? "" : LinkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        LinkPhone = linkPhone;
    }
}
