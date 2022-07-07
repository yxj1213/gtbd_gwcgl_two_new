package com.ttce.vehiclemanage.api;


import com.jaydenxiao.common.basebean.BaseRespose;
import com.ttce.vehiclemanage.bean.AlarmListBean;
import com.ttce.vehiclemanage.bean.AlipayBean;
import com.ttce.vehiclemanage.bean.AppUpdateBean;
import com.ttce.vehiclemanage.bean.CarLatLngBean;
import com.ttce.vehiclemanage.bean.CarPositionBean;
import com.ttce.vehiclemanage.bean.CarRecordsBean;
import com.ttce.vehiclemanage.bean.ChangeCompanyBean;
import com.ttce.vehiclemanage.bean.ChangeCompanySaveReturnBean;
import com.ttce.vehiclemanage.bean.CloseAccountStateBean;
import com.ttce.vehiclemanage.bean.CompanyItemBean;
import com.ttce.vehiclemanage.bean.CompanyTypeListBean;
import com.ttce.vehiclemanage.bean.DaiBanListBean;
import com.ttce.vehiclemanage.bean.DepartmentByCompanyIdBean;
import com.ttce.vehiclemanage.bean.DictTypeListBean;
import com.ttce.vehiclemanage.bean.DispatchDriverListBean;
import com.ttce.vehiclemanage.bean.EmptyOrderBean;
import com.ttce.vehiclemanage.bean.EquipmentDetailBean;
import com.ttce.vehiclemanage.bean.ExRegistFirstStepBean;
import com.ttce.vehiclemanage.bean.ExSysNoticeSetBean;
import com.ttce.vehiclemanage.bean.ExVerificationBean;
import com.ttce.vehiclemanage.bean.FenceListBean;
import com.ttce.vehiclemanage.bean.HomeOrderBean;
import com.ttce.vehiclemanage.bean.IdCareBean;
import com.ttce.vehiclemanage.bean.InformationAuditBean;
import com.ttce.vehiclemanage.bean.InstructDetailBean;
import com.ttce.vehiclemanage.bean.IsHasWorkBeanchBean;
import com.ttce.vehiclemanage.bean.IsOrderingBean;
import com.ttce.vehiclemanage.bean.LoginBean;
import com.ttce.vehiclemanage.bean.LoginImageVerificationBean;
import com.ttce.vehiclemanage.bean.MarkerDetailsBean;
import com.ttce.vehiclemanage.bean.Message2Bean;
import com.ttce.vehiclemanage.bean.MonitorResponseBean;
import com.ttce.vehiclemanage.bean.NewUserInfoBean;
import com.ttce.vehiclemanage.bean.OrderSuccessBean;
import com.ttce.vehiclemanage.bean.OtherUserId;
import com.ttce.vehiclemanage.bean.PostServerBean;
import com.ttce.vehiclemanage.bean.SosBean;
import com.ttce.vehiclemanage.bean.SystemMessageBean;
import com.ttce.vehiclemanage.bean.TravelItemBean;
import com.ttce.vehiclemanage.bean.TravelListBean;
import com.ttce.vehiclemanage.bean.UserInfoBean;
import com.ttce.vehiclemanage.bean.VerificationBean;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * des:ApiService
 * Created by hk
 */
public interface ApiService {
    //用户登录
    @POST("/api/v1.0/Login/PostLogin")
    Observable<BaseRespose<LoginBean>> login(@Body RequestBody body);

    //退出登录
    @POST("/api/v1.0/Users/PostLoginOut")
    Observable<BaseRespose<String>> logout(@Body RequestBody body);

    //获取登录验证码m
    @POST("/api/v1.0/Login/postLoginVerification")
    Observable<BaseRespose<VerificationBean>> getLoginVerification(@Body RequestBody body);


    //用户修改密码
    @POST("/api/v1.0/Users/Post_Chage_Password")
    Observable<BaseRespose<String>> updatePwd(@Body RequestBody body);

    //获取个人资料
    @POST("/api/v1.0/Users/Post_PersonalInfo")
    Observable<BaseRespose<UserInfoBean>> getUserInfo(@Body RequestBody body);

    //用户注册
    @POST("/api/v1.0/Regist/PostRegistStep3")
    Observable<BaseRespose<String>> userRegister(@Body RequestBody body);

    //忘记密码
    @POST("/api/v1.0/Login/PostFindPasswordByPhone")
    Observable<BaseRespose<String>> findPasswordByPhone(@Body RequestBody body);

    //获取组织机构列表 +车辆
    @POST("/api/v1.0/Users/PostCompanys")
    Observable<BaseRespose<List<CompanyItemBean>>> getCompanyList(@Body RequestBody body);

    //当前监控车辆数据,展示在地图上
    @POST("/api/v1.0/Monitor/PostVehicles")
    Observable<BaseRespose<List<MonitorResponseBean>>> getVehiclesList(@Body RequestBody body);

    //发送疲劳驾驶指令
    @POST("/api/v1.0/Instruct/PostPiLaoJiaShi")
    Observable<BaseRespose<String>> sendPljs(@Body RequestBody body);

    //发送断电报警指令
    @POST("/api/v1.0/Instruct/PostDuanDianBaoJin")
    Observable<BaseRespose<String>> sendDdbj(@Body RequestBody body);

    //发送ACC设防指令
    @POST("/api/v1.0/Instruct/PostAccSheFang")
    Observable<BaseRespose<String>> sendAcc(@Body RequestBody body);

    //发送位移报警指令
    @POST("/api/v1.0/Instruct/PostWeiYiBaoJing")
    Observable<BaseRespose<String>> sendWybj(@Body RequestBody body);

    //发送低电提醒指令
    @POST("/api/v1.0/Instruct/PostDiDianTiXing")
    Observable<BaseRespose<String>> sendDdtx(@Body RequestBody body);

    //发送超速报警指令
    @POST("/api/v1.0/Instruct/PostChaoSuBaoJing")
    Observable<BaseRespose<String>> sendCsbj(@Body RequestBody body);

    //获取指令详情
    @POST("/api/v1.0/Instruct/PostDetail")
    Observable<BaseRespose<InstructDetailBean>> getInstructDetail(@Body RequestBody body);

    //已发指令列表 查询某设备所有指令 查询某人所监控所有设备的指令
    @POST("/api/v1.0/Instruct/PostPageList")
    Observable<BaseRespose<List<InstructDetailBean>>> getInstructList(@Body RequestBody body);

    //获取设备详情
    @POST("/api/v1.0/Monitor/PostVehicleDetail")
    Observable<BaseRespose<EquipmentDetailBean>> getEquipmentDetail(@Body RequestBody body);

    //搜索车辆
    @POST("/api/v1.0/Monitor/PostSearchVehicles")
    Observable<BaseRespose<List<MonitorResponseBean>>> searchVehicles(@Body RequestBody body);

    //告警列表
    @POST("/api/v1.0/Alarm/PostPageList")
    Observable<BaseRespose<List<AlarmListBean>>> getAlarmList(@Body RequestBody body);

    //更新告警状态
    @POST("/api/v1.0/Alarm/PostProcessonce")
    Observable<BaseRespose<String>> updateGjState(@Body RequestBody body);

    //个人中心-围栏管理-围栏列表点击设备-围栏
    @POST("/api/v1.0/Fence/PostPageList")
    Observable<BaseRespose<List<FenceListBean>>> getNewFenceList(@Body RequestBody body);

    //个人中心-围栏管理-围栏列表-添加设备
    @POST("/api/v1.0/Fence/PostAddDevice")
    Observable<BaseRespose<Boolean>> addFenceDevice(@Body RequestBody body);

    //添加圆形围栏
    @POST("/api/v1.0/Fence/PostAddOrEditCircle")
    Observable<BaseRespose<Boolean>> addOrEditCircle(@Body RequestBody body);

    //删除圆形围栏
    @POST("/api/v1.0/Fence/PostDelCircle")
    Observable<BaseRespose<Boolean>> delOrEditCircle(@Body RequestBody body);

    //点击设备-围栏-点击告警类型
    @POST("/api/v1.0/Fence/PostSetAlarmType")
    Observable<BaseRespose<Boolean>> setAlarmType(@Body RequestBody body);

    // 通知设置服务--获取告警
    @POST("/api/v1.0/NoticeSet/PostNoticeSetModel")
    Observable<BaseRespose<ExSysNoticeSetBean>> getNoticeDetail(@Body RequestBody body);

    // 通知设置服务--设置告警
    @POST("/api/v1.0/NoticeSet/PostNoticeSetUpdate")
    Observable<BaseRespose<String>> updateNoticeDetail(@Body RequestBody body);

    // 发送短信接口返回不带验证码编号 （接口会返回当前手机号是否注册过）
    @POST("/api/v1.0/Regist/PostRegistStep1")
    Observable<BaseRespose<ExVerificationBean>> sendSms(@Body RequestBody body);
   //用于发送短信验证码
    @POST("/api/v1.0/Sms/PostSendSmsVerificationCode")
    Observable<BaseRespose<ExVerificationBean>> PostSendSmsVerificationCode(@Body RequestBody body);
    // 短信验证码校验
    @POST("/api/v1.0/Regist/PostRegistStep2")
    Observable<BaseRespose<ExRegistFirstStepBean>> checkSms(@Body RequestBody body);

    //行程服务--车辆轨迹
    @POST("/api/v1.0/Travel/PostList")
    Observable<BaseRespose<TravelListBean>> getTravelList(@Body RequestBody body);

    //行程服务--车辆追踪
    @POST("/api/v1.0/Travel/PostTrack")
    Observable<BaseRespose<TravelItemBean>> getTrackInfo(@Body RequestBody body);

    //获取sos列表
    @POST("/api/v1.0/SOS/PostPageList")
    Observable<BaseRespose<List<SosBean>>> getSosList(@Body RequestBody body);

    //上报sos
    @POST("/api/v1.0/SOS/PostSOSAdd")
    Observable<BaseRespose<String>> addSos(@Body RequestBody body);

    //意见反馈
    @POST("/api/v1.0/Feedback/PostFeedbackAdd")
    Observable<BaseRespose<String>> addFeedBack(@Body RequestBody body);

    //获取系统消息列表
    @POST("/api/v1.0/NoticeInfo/PostPageList")
    Observable<BaseRespose<List<SystemMessageBean>>> getSystemMessageList(@Body RequestBody body);
    //获取个人资料
    //上传头像,并保存头像路径
    @POST("/api/v1.0/ZYUseCar/PostIsStaff")
    Observable<BaseRespose<Boolean>> getIsStaff(@Body RequestBody body);
    //上传头像,并保存头像路径
    @POST("/api/v1.0/Users/PostUploadHead")
    Observable<BaseRespose<String>> getUploadHead(@Body RequestBody body);

    @POST("/api/v1.0/AppVersion/PostAppVersionModel")
    Observable<BaseRespose<AppUpdateBean>> getAppVersionModel(@Body RequestBody body);

    //获取用户可切换的企业
    @POST("/api/v1.0/Users/PostSwitchCompanyByUserId")
    Observable<BaseRespose<List<ChangeCompanyBean>>> getSwitchCompanyByUserId(@Body RequestBody body);

    //保存切换企业
    @POST("/api/v1.0/Users/PostSaveSwitchCompany_20220321")
    Observable<BaseRespose<ChangeCompanySaveReturnBean>> getSaveSwitchCompanyByUserIds(@Body RequestBody body);
    //TODO 登陆地方的图片验证码
    @POST("/api/v1.0/Login/postLoginImageVerification")
    Observable<BaseRespose<LoginImageVerificationBean>> postLoginImageVerification(@Body RequestBody body);
    //验证登陆地方的图片验证码
    @POST("/api/v1.0/Login/postLoginImageVerificationIsCorrect")
    Observable<BaseRespose<String>> postLoginImageVerificationIsCorrect(@Body RequestBody body);
    //申请注销账号
    @POST("/api/v1.0/CloseAccount/Post_CloseAccount")
    Observable<BaseRespose<Boolean>> postLogout(@Body RequestBody body);
    //获取账号状态（是否注销）
    @POST("/api/v1.0/CloseAccount/Post_CloseAccountState")
    Observable<BaseRespose<CloseAccountStateBean>> PostCloseAccountState(@Body RequestBody body);




    //获取空订单
    @POST("/api/v1.0/CarFlow_Order/Post_CarFlow_Order_Empty")
    Observable<BaseRespose<EmptyOrderBean>> getEmptyOrder(@Body RequestBody body);
    //创建订单
    @POST("/api/v1.0/CarFlow_Order/Post_CarFlow_Order_Add")
    Observable<BaseRespose<OrderSuccessBean>> addPostOrder(@Body RequestBody body);
    //根据手机号和员工姓名 获取员工编号和用户编号
    @POST("/api/v1.0/Users/Post_Staff_UserId_By_Name_And_Phone")
    Observable<BaseRespose<OtherUserId>> getOtherUserId(@Body RequestBody body);
    //取消订单
    @POST("/api/v1.0/CarFlow_Order/Post_CarFlow_Order_Cancel")
    Observable<BaseRespose<String>> myOrderCancel(@Body RequestBody body);
    //获取单个订单详情
    @POST("/api/v1.0/CarFlow_Order/Post_CarFlow_Order_Model")
    Observable<BaseRespose<EmptyOrderBean>> myOrderDetails(@Body RequestBody body);
    //获取列表
    @POST("/api/v1.0/CarFlow_Order/Post_CarFlow_Order_PageList")
    Observable<BaseRespose<List<EmptyOrderBean>>> AllOrderPageList(@Body RequestBody body);
    //订单审批（通过、驳回）
    @POST("/api/v1.0/CarFlow_Order/Post_CarFlow_Order_UseCar_Audit")
    Observable<BaseRespose<String>> OrderUseCarAudit(@Body RequestBody body);
    //获取是否有进行中的订单
    @POST("/api/v1.0/CarFlow_Order/Post_CarFlow_IsOrdering")
    Observable<BaseRespose<IsOrderingBean>> OrderIsOrdering(@Body RequestBody body);
    //用户设置默认用车类型
    @POST("/api/v1.0/CarFlow_UserUseCarType/Post_CarFlow_UserUseCarType_SetDefault")
    Observable<BaseRespose<String>> SetDefaultOrderCarType(@Body RequestBody body);
    //调度审核（调派车辆、驳回）
    @POST("/api/v1.0/CarFlow_Order/Post_CarFlow_Order_Dispatch_Audit")
    Observable<BaseRespose<String>> OrderDispatchAudit(@Body RequestBody body);
    //获取车辆位置
    @POST("/api/v1.0/CarFlow_Order/Post_CarFlow_Position")
    Observable<BaseRespose<CarLatLngBean>> CarFlowPosition(@Body RequestBody body);
    //获取调度车辆
    @POST("/api/v1.0/CarFlow_Order/Post_CarFlow_Order_Car_List")
    Observable<BaseRespose<List<CarPositionBean>>> OrderCarList(@Body RequestBody body);
    //获取列表
    @POST("/api/v1.0/Business_Staff/Post_Business_Staff_List")
    Observable<BaseRespose<List<DispatchDriverListBean>>> OrderDriverList(@Body RequestBody body);
    //获取订单详情中的审批记录和用车记录
    @POST("/api/v1.0/CarFlow_Order_State_Log/Post_CarFlow_Order_State_Log_List")
    Observable<BaseRespose<List<CarRecordsBean>>> OrderStateLogList(@Body RequestBody body);
    //司机接单或司机抢单
    @POST("/api/v1.0/CarFlow_Order/Post_CarFlow_Order_Driver_Grab")
    Observable<BaseRespose<String>> OrderDriverGrab(@Body RequestBody body);
    //用户上车确认
    @POST("/api/v1.0/CarFlow_Order/Post_CarFlow_Order_Confirm_UseUser")
    Observable<BaseRespose<String>> Order_Confirm_UseUser(@Body RequestBody body);
    //车已到达上车地
    @POST("/api/v1.0/CarFlow_Order/Post_CarFlow_Order_Reached_Boarding_Place")
    Observable<BaseRespose<String>> OrderReachedBoardingPlace(@Body RequestBody body);
    //司机开始用车确认
    @POST("/api/v1.0/CarFlow_Order/Post_CarFlow_Order_Confirm_Driver")
    Observable<BaseRespose<String>> OrderConfirmDriver(@Body RequestBody body);
    //结束用车
    @POST("/api/v1.0/CarFlow_Order/Post_CarFlow_Order_Confirm_Finish")
    Observable<BaseRespose<String>> OrderConfirmFinish(@Body RequestBody body);
    //新增打卡
    @POST("/api/v1.0/CarFlow_Order_Mark/Post_CarFlow_Order_Mark_Add")
    Observable<BaseRespose<String>> OrderMarkAdd(@Body RequestBody body);
    //获取单个模型
    @POST("/api/v1.0/CarFlow_Order_Mark/Post_CarFlow_Order_Mark_Model")
    Observable<BaseRespose<MarkerDetailsBean>> OrderMarkModel(@Body RequestBody body);
    //获取企业类型
    @POST("/api/v1.0/Business_Company/Post_CompanyType_List")
    Observable<BaseRespose<List<CompanyTypeListBean>>> CompanyTypeList(@Body RequestBody body);
    //全新创建企业
    @POST("/api/v1.0/Business_Company/Post_Business_ComPany_Add")
    Observable<BaseRespose<ChangeCompanyBean>> BusinessComPanyAdd(@Body RequestBody body);
    //判断登录用户是否有模块操作权限
    @POST("/api/v1.0/CarFlow_Order/Post_CarFlow_Order_Module")
    Observable<BaseRespose<IsHasWorkBeanchBean>> IsHasPrivilege(@Body RequestBody body);
    //管理员审核列表
    @POST("/api/v1.0/Business_Staff/Post_Business_Staff_CheckList")
    Observable<BaseRespose<List<InformationAuditBean>>> BusinessStaffCheckList(@Body RequestBody body);
    //获取当前用户在当前企业下员工信息状态
    @POST("/api/v1.0/Business_Staff/Post_StaffMagState")
    Observable<BaseRespose<Message2Bean>> StaffMagState(@Body RequestBody body);

    //获取单个模型
    @POST("/api/v1.0/Business_Staff/Post_Business_Staff_Model")
    Observable<BaseRespose<NewUserInfoBean>> BusinessStaffModel(@Body RequestBody body);
    //添加
    @POST("/api/v1.0/Business_Staff/Post_Business_Staff_AddOrEdit")
    Observable<BaseRespose<String>> BusinessStaffAdd(@Body RequestBody body);
    //创建部门
    @POST("/api/v1.0/Business_Department/Post_Department_Add")
    Observable<BaseRespose<String>> PostDepartmentAdd(@Body RequestBody body);
    //管理员审核
    @POST("/api/v1.0/Business_Staff/Post_Business_Staff_Check")
    Observable<BaseRespose<String>> PostBusinessStaffCheck(@Body RequestBody body);
    //根据企业获取岗位信息
    @POST("/api/v1.0/Business_Staff/Post_PositionByCompanyId")
    Observable<BaseRespose<List<DictTypeListBean>>> PostDictTypeList(@Body RequestBody body);
    //根据企业ID获取所有部门
    @POST("/api/v1.0/Business_Department/Post_DepartmentByCompanyId")
    Observable<BaseRespose<List<DepartmentByCompanyIdBean>>> PostDepartmentByCompanyId(@Body RequestBody body);
    //用车工作台代办事项
    @POST("/api/v1.0/CarFlow_Order/Post_CarFlow_Order_Need_ToDo")
    Observable<BaseRespose<List<DaiBanListBean>>> Post_CarFlow_Order_Need_ToDo(@Body RequestBody body);
    //实名认证第一步
    @POST("/api/v1.0/Autonymome/Post_Preconsult")
    Observable<BaseRespose<AlipayBean>> PostPreconsult(@Body RequestBody body);
    //实名认证第二步
    @POST("/api/v1.0/Autonymome/Post_Consult")
    Observable<BaseRespose<IdCareBean>> Post_Consult(@Body RequestBody body);
    //首页今日订单统计
    @POST("/api/v1.0/CarFlow_Order/Post_CarFlow_Order_Index_Statistics")
    Observable<BaseRespose<HomeOrderBean>> CarFlowOrder_Index_Statistics(@Body RequestBody body);
  //司机开始用车确认
    @POST("/api/v1.0/CarFlow_Order/Post_CarFlow_Order_Confirm_Driver")
    Observable<BaseRespose<String>> CarFlowOrder_Confirm_Driver(@Body RequestBody body);
//结束用车
    @POST("/api/v1.0/CarFlow_Order/Post_CarFlow_Order_Confirm_Finish")
    Observable<BaseRespose<String>> CarFlowOrder_Confirm_Finish(@Body RequestBody body);
//准备前往目的地
    @POST("/api/v1.0/CarFlow_Order/Post_CarFlow_Order_BeginDestination")
    Observable<BaseRespose<String>> CarFlowOrder_BeginDestination(@Body RequestBody body);
    //获取服务器时间
    @POST("/api/v1.0/Login/PostServerTime")
    Observable<BaseRespose<PostServerBean>> PostServerBean(@Body RequestBody body);
}
