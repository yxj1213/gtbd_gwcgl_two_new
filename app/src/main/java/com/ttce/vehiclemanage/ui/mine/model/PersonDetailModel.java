package com.ttce.vehiclemanage.ui.mine.model;

import android.util.Base64;

import com.google.gson.JsonObject;
import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.bean.AlipayBean;
import com.ttce.vehiclemanage.bean.DaiBanListBean;
import com.ttce.vehiclemanage.bean.DepartmentByCompanyIdBean;
import com.ttce.vehiclemanage.bean.DictTypeListBean;
import com.ttce.vehiclemanage.bean.HomeOrderBean;
import com.ttce.vehiclemanage.bean.IdCareBean;
import com.ttce.vehiclemanage.bean.IsHasWorkBeanchBean;
import com.ttce.vehiclemanage.bean.Message2Bean;
import com.ttce.vehiclemanage.bean.NewUserInfoBean;
import com.ttce.vehiclemanage.bean.UserInfoBean;
import com.ttce.vehiclemanage.ui.mine.constract.PersonDetailConstract;
import com.ttce.vehiclemanage.ui.usermanage.manager.UserManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import rx.Observable;

/**
 * Created by hk on 2019/7/12.
 */

public class PersonDetailModel implements PersonDetailConstract.Model {
    @Override
    public Observable<HomeOrderBean> CarFlowOrder_Index_StatisticsModel(String DateTimeType) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("DateTimeType",DateTimeType);
        return Api.getDefault(HostType.BASE_HOST).CarFlowOrder_Index_Statistics(params.build())
                .compose(RxHelper.<HomeOrderBean>handleResult());
    }

    @Override
    public Observable<UserInfoBean> getUserInfo() {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        return Api.getDefault(HostType.BASE_HOST).getUserInfo(params.build())
                .compose(RxHelper.<UserInfoBean>handleResult());
    }

    @Override
    public Observable<NewUserInfoBean> getBusinessStaffModel(String staffid) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("Business_StaffId",staffid);
        return Api.getDefault(HostType.BASE_HOST).BusinessStaffModel(params.build())
                .compose(RxHelper.<NewUserInfoBean>handleResult());
    }

    @Override
    public Observable<String> BusinessStaffAddModel(String type,String staffid,String PositionId,String DepartmentId,String UserId,String xb,String nl,String rzrq,String jjlxr,String jjlxdh,String sfcyjz,String xzz,String ygbhCode) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("ID",staffid);
        params.add("UserId",UserId);
        params.add("DepartmentId",DepartmentId);
        params.add("Sex", xb);
        params.add("Age", nl);
        params.add("EmergencycontactName", jjlxr);
        params.add("EmergencycontactPhone", jjlxdh);
        params.add("OfficeDate", rzrq);
        params.add("XianZhu", xzz);
        if(sfcyjz.equals("有")){
            params.add("IsDriver", 1);
        }else{
            params.add("IsDriver", 0);
        }
        params.add("PositionId", PositionId);
        if(type.equals("审核")){
            params.add("Code",ygbhCode);

            return Api.getDefault(HostType.BASE_HOST).PostBusinessStaffCheck(params.build())
                    .compose(RxHelper.<String>handleResult());
        }else{
            return Api.getDefault(HostType.BASE_HOST).BusinessStaffAdd(params.build())
                    .compose(RxHelper.<String>handleResult());
        }
    }

    @Override
    public Observable<List<DictTypeListBean>> PostDictTypeListModel() {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        return Api.getDefault(HostType.BASE_HOST).PostDictTypeList(params.build())
                .compose(RxHelper.<List<DictTypeListBean>>handleResult());
    }

    @Override
    public Observable<List<DepartmentByCompanyIdBean>> PostDepartmentByCompanyIdModel() {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        return Api.getDefault(HostType.BASE_HOST).PostDepartmentByCompanyId(params.build())
                .compose(RxHelper.<List<DepartmentByCompanyIdBean>>handleResult());
    }

    @Override
    public Observable<List<DaiBanListBean>> CarFlowOrderNeedToDoModel() {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        return Api.getDefault(HostType.BASE_HOST).Post_CarFlow_Order_Need_ToDo(params.build())
                .compose(RxHelper.<List<DaiBanListBean>>handleResult());
    }

    @Override
    public Observable<String> uploadImg(String filepath, String fileName ,String type) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("fileBase64", createBase64(filepath));
        params.add("FileName", fileName);
        params.add("HeadType", type);//头像标识：1：自定义 2：默认
        return Api.getDefault(HostType.BASE_HOST).getUploadHead(params.build())
                .compose(RxHelper.<String>handleResult());
    }

    @Override
    public Observable<IsHasWorkBeanchBean> IsHasPrivilegeModel(String OrderModule) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("OrderModule", OrderModule);
        params.add("StaffId", UserManager.getLoginBean().getStaffId());
        params.add("UserId", UserManager.getLoginBean().getUserId());
        return Api.getDefault(HostType.BASE_HOST).IsHasPrivilege(params.build())
                .compose(RxHelper.<IsHasWorkBeanchBean>handleResult());
    }
    @Override
    public Observable<Message2Bean> StaffMagStateModel() {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("CompanyId", UserManager.getLoginBean().getCompanyId());
        params.add("UserId", UserManager.getLoginBean().getUserId());
        return Api.getDefault(HostType.BASE_HOST).StaffMagState(params.build())
                .compose(RxHelper.<Message2Bean>handleResult());
    }
    private String createBase64(String filePath) {
        String base64 = null;
        InputStream in = null;
        try {
            in = new FileInputStream(new File(filePath));
            byte[] bytes = new byte[in.available()];
            int length = in.read(bytes);
            base64 = Base64.encodeToString(bytes, 0, length, Base64.DEFAULT);
            base64 = base64.replace("data:image/jpeg;base64,", "");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return base64;
    }

    @Override
    public Observable<AlipayBean> PostPreconsultModel(JsonObject jsonObject) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("PreconsultModel",jsonObject);

        return Api.getDefault(HostType.BASE_HOST).PostPreconsult(params.build())
                .compose(RxHelper.<AlipayBean>handleResult());
    }
    @Override
    public Observable<IdCareBean> PostConsultModel(String verify_id, String auth_code, JsonObject jsonObject) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("verify_id",verify_id);
        params.add("auth_code",auth_code);
        params.add("Pa_Consult",jsonObject);
        return Api.getDefault(HostType.BASE_HOST).Post_Consult(params.build())
                .compose(RxHelper.<IdCareBean>handleResult());
    }
}
