package com.ttce.vehiclemanage.ui.mine.constract;

import com.google.gson.JsonObject;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
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

import java.util.List;

import rx.Observable;

/**
 * Created by hk on 2019/7/12.
 */

public interface PersonDetailConstract {

    interface Model extends BaseModel {
        Observable<UserInfoBean> getUserInfo();
        Observable<String> uploadImg(String filepath,String fileName,String type);
        Observable<IsHasWorkBeanchBean> IsHasPrivilegeModel(String OrderModule);
        Observable<Message2Bean> StaffMagStateModel();
        Observable<NewUserInfoBean> getBusinessStaffModel(String staffid);
        Observable<String> BusinessStaffAddModel(String type,String staffid,String PositionId,String DepartmentId,String UserId,String xb,String nl,String rzrq,String jjlxr,String jjlxdh,String sfcyjz,String xzz,String ygbhCode);
        Observable<List<DictTypeListBean>> PostDictTypeListModel();
        Observable<List<DepartmentByCompanyIdBean>> PostDepartmentByCompanyIdModel();
        Observable<List<DaiBanListBean>> CarFlowOrderNeedToDoModel();

        Observable<AlipayBean> PostPreconsultModel(JsonObject jsonObject);
        Observable<IdCareBean> PostConsultModel(String verify_id, String auth_code,JsonObject jsonObject);
        Observable<HomeOrderBean> CarFlowOrder_Index_StatisticsModel(String DateTimeType);
    }

    interface View extends BaseView {
        void getUserInfo(UserInfoBean userInfoBean);
        void getImgUrl(String imgUrl);
        void returnIsHasPrivilege(IsHasWorkBeanchBean isHasWorkBeanchBean,int position,String type);
        void returnStaffMagState(Message2Bean MessageBean);
        void returnBusinessStaff(NewUserInfoBean MessageBean);
        void returnBusinessStaffAdd(String str);
        void returnPostDictTypeList(List<DictTypeListBean> str);
        void returnPostDepartmentByCompanyId(List<DepartmentByCompanyIdBean> str);
        void returnCarFlowOrderNeedToDo(List<DaiBanListBean> str);


        void returnPostPreconsult(AlipayBean str);
        void returnPostConsult(IdCareBean str);
        void returnErrPostConsult(String str);
        void returnCarFlowOrder_Index_Statistics(HomeOrderBean homeOrderBean);
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void getUserInfo();
        public abstract void uploadImg(String filepath,String fileName,String type);
        public abstract void IsHasPrivilegePresenter(String OrderModule,int position,String type);
        public abstract void StaffMagStatePresenter();
        public abstract void getBusinessStaffPresenter(String staffid);
        public abstract void BusinessStaffAddPresenter(String type,String staffid,String PositionId,String DepartmentId,String UserId,String xb,String nl,String rzrq,String jjlxr,String jjlxdh,String sfcyjz,String xzz,String ygbhCode);
        public abstract void PostDictTypeListPresenter();
        public abstract void PostDepartmentByCompanyIdPresenter();
        public abstract void CarFlowOrderNeedToDoPresenter();

        public abstract void PostPreconsultPresenter(JsonObject jsonObject);
        public abstract void PostConsultPresenter(String verify_id, String auth_code,JsonObject jsonObject);
        public abstract void CarFlowOrder_Index_StatisticsPresenter(String DateTimeType);
    }
}
