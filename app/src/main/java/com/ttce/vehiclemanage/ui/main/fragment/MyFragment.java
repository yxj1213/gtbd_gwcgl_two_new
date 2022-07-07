package com.ttce.vehiclemanage.ui.main.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.commonwidget.StatusBarCompat;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.app.AppConstant;
import com.ttce.vehiclemanage.bean.AlipayBean;
import com.ttce.vehiclemanage.bean.DaiBanListBean;
import com.ttce.vehiclemanage.bean.DepartmentByCompanyIdBean;
import com.ttce.vehiclemanage.bean.DictTypeListBean;
import com.ttce.vehiclemanage.bean.HomeOrderBean;
import com.ttce.vehiclemanage.bean.IdCareBean;
import com.ttce.vehiclemanage.bean.IsHasWorkBeanchBean;
import com.ttce.vehiclemanage.bean.Message2Bean;
import com.ttce.vehiclemanage.bean.MessageEvent;
import com.ttce.vehiclemanage.bean.NewUserInfoBean;
import com.ttce.vehiclemanage.bean.UserInfoBean;
import com.ttce.vehiclemanage.ui.main.activity.MainActivity;
import com.ttce.vehiclemanage.ui.main.activity.my.myuse.MyUseActivity;
import com.ttce.vehiclemanage.ui.main.activity.my.order.AddDaKaActivity;
import com.ttce.vehiclemanage.ui.main.activity.my.order.MyOrderActivity;
import com.ttce.vehiclemanage.ui.main.activity.my.order.OrderMileageActivity;
import com.ttce.vehiclemanage.ui.main.popwindow.OrderSelectBottomControlPanel;
import com.ttce.vehiclemanage.ui.mine.activity.NewPersonDetailActivity;
import com.ttce.vehiclemanage.ui.mine.activity.SetActivity;
import com.ttce.vehiclemanage.ui.mine.constract.PersonDetailConstract;
import com.ttce.vehiclemanage.ui.mine.model.PersonDetailModel;
import com.ttce.vehiclemanage.ui.mine.presenter.PersonDetailPresenter;
import com.ttce.vehiclemanage.ui.usermanage.manager.UserManager;
import com.ttce.vehiclemanage.utils.OtherUtil;
import com.ttce.vehiclemanage.utils.ToastUtil;
import com.ttce.vehiclemanage.utils.dialog_photo.CommonPopupWindow;
import com.ttce.vehiclemanage.utils.dialog_photo.MyPhotoBottomControlPanel;
import com.ttce.vehiclemanage.utils.glide.GlideUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
/**
 * 我的
 * Created by hk on 2019/6/18.
 */

public class MyFragment extends BaseFragment<PersonDetailPresenter, PersonDetailModel> implements PersonDetailConstract.View, TakePhoto.TakeResultListener, InvokeListener, MyPhotoBottomControlPanel.ControlPanelListener {

    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_qymc)
    TextView tvQymc;
    @Bind(R.id.tv_right_persondetails)
    TextView tv_right_persondetails;
    @Bind(R.id.iv_head)
    ImageView ivHead;

 @Bind(R.id.my_all_bg)
 ScrollView my_all_bg;


    public boolean nowIsShow;
    public static boolean IsRef=false;

    public void setShow(boolean show) {
        nowIsShow = show;
        if(nowIsShow==true){
            if(mPresenter!=null){
                mPresenter.getUserInfo();
                if(!UserManager.getLoginBean().getCompanyId().equals("")&&!UserManager.getLoginBean().getCompanyId().equals("00000000-0000-0000-0000-000000000000"))//代表不在当前在个人下
                {
                    mPresenter.StaffMagStatePresenter();
                }
            }
        }
    }

    @Override
    protected void loadData() {


    }

    @Override
    protected int getLayoutResource() {
        return R.layout.new_fragment_person;
    }


    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        StatusBarCompat.setStatusBarColor(getActivity(), ContextCompat.getColor(getContext(), R.color.app_main_colors));
        EventBus.getDefault().register(this);
        tv_right_persondetails.setText("身份信息");
    }

    @Override
    public void onResume() {
        super.onResume();
        if(MyFragment.IsRef==true){
            IsRef=false;
            mPresenter.getUserInfo();
        }
        if(nowIsShow==true&&!UserManager.getLoginBean().getCompanyId().equals("")&&!UserManager.getLoginBean().getCompanyId().equals("00000000-0000-0000-0000-000000000000"))//代表不在当前在个人下
        {
            mPresenter.StaffMagStatePresenter();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent msg) {
        switch (msg.getMsgWhat()) {
            case AppConstant.CHANGE_COMPANYID:
                mPresenter.getUserInfo();
                if(!UserManager.getLoginBean().getCompanyId().equals("")&&!UserManager.getLoginBean().getCompanyId().equals("00000000-0000-0000-0000-000000000000"))//代表不在当前在个人下
                {
                    mPresenter.StaffMagStatePresenter();
                }
                break;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.lin_right_person,R.id.iv_head,R.id.img_left,R.id.tv_wddd, R.id.tv_wdqb, R.id.tv_clgl, R.id.tv_zjgl, R.id.tv_tjyq, R.id.tv_wdyy, R.id.tv_sz,R.id.img_xx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_xx:
                break;
            case R.id.img_left:
                ((MainActivity) getActivity()).openDrawerLayout();
                break;
            //我的订单
            case R.id.tv_wddd:
                MyOrderActivity.goToPage(getActivity(),-1);
                break;
            //我的钱包
            case R.id.tv_wdqb:

                break;
            //车辆管理
            case R.id.tv_clgl:
                break;
            //证件管理
            case R.id.tv_zjgl:
                break;
            //推荐邀请
            case R.id.tv_tjyq:

                break;
            //我的应用
            case R.id.tv_wdyy:
                  startActivity(MyUseActivity.class);

                break;
            //设置
            case R.id.tv_sz:
                startActivity(SetActivity.class);
                break;
            case R.id.lin_right_person:
                if(!UserManager.getLoginBean().getCompanyId().equals("")&&!UserManager.getLoginBean().getCompanyId().equals("00000000-0000-0000-0000-000000000000"))//代表不在当前在个人下
                {
                    if(mMessageBean!=null){
                        NewPersonDetailActivity.goToPage(getActivity(),mMessageBean.getValue(),UserManager.getLoginBean().getStaffId(),UserManager.getLoginBean().getUserId(),UserManager.getLoginBean().getCompanyId());
                    }else{
                        ToastUtil.showToast("没有获取到数据，请重新刷新界面。");
                    }
                }else{
                    NewPersonDetailActivity.goToPage(getActivity(),0,UserManager.getLoginBean().getStaffId(),UserManager.getLoginBean().getUserId(),UserManager.getLoginBean().getCompanyId());
                }
                break;
            case R.id.iv_head:
                requestPermission();
                break;
        }
    }

    @Override
    public void getUserInfo(UserInfoBean userInfoBean) {
        if(userInfoBean.getUserRealName().equals("")){
            tvName.setText(userInfoBean.getUserNikeName());
        }else{
            tvName.setText(OtherUtil.setName(userInfoBean.getUserRealName()));
        }
        if(userInfoBean.getCompanyName().equals("")){
            tvQymc.setVisibility(View.GONE);
        }else{
            tvQymc.setVisibility(View.VISIBLE);
            tvQymc.setText(userInfoBean.getCompanyName());
        }
        GlideUtils.displayHead(getActivity(),ivHead,userInfoBean.getHeadPic(),R.mipmap.icon_head,R.mipmap.icon_head);
    }
    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {
        if (nowIsShow) {
            ToastUitl.showToastWithImg(msg, R.drawable.ic_wrong);
        } else {

        }
    }
    @Override
    public void getImgUrl(String imgUrl) {
        if(!imgUrl.equals("")){
            GlideUtils.displayHead(getActivity(),ivHead,imgUrl,R.mipmap.icon_head,R.mipmap.icon_head);
        }else{
            ToastUtil.showToast("上传失败。");
        }
    }

    @Override
    public void returnIsHasPrivilege(IsHasWorkBeanchBean isHasWorkBeanchBean, int position, String type) {

    }

    Message2Bean mMessageBean;
    @Override
    public void returnStaffMagState(Message2Bean MessageBean) {
        mMessageBean=MessageBean;
    }

    @Override
    public void returnBusinessStaff(NewUserInfoBean MessageBean) {

    }

    @Override
    public void returnBusinessStaffAdd(String str) {

    }

    @Override
    public void returnPostDictTypeList(List<DictTypeListBean> str) {

    }

    @Override
    public void returnPostDepartmentByCompanyId(List<DepartmentByCompanyIdBean> str) {

    }

    @Override
    public void returnCarFlowOrderNeedToDo(List<DaiBanListBean> str) {

    }

    @Override
    public void returnPostPreconsult(AlipayBean str) {

    }

    @Override
    public void returnPostConsult(IdCareBean str) {

    }

    @Override
    public void returnErrPostConsult(String str) {

    }

    @Override
    public void returnCarFlowOrder_Index_Statistics(HomeOrderBean homeOrderBean) {

    }

    /**
     * takePhoto
     * */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void takeSuccess(TResult result) {

        mPresenter.uploadImg(result.getImage().getCompressPath(),result.getImage().getCompressPath().substring(result.getImage().getCompressPath().lastIndexOf("/") + 1,result.getImage().getCompressPath().length()),"1");
    }

    @Override
    public void takeFail(TResult result, String msg) {
    }

    @Override
    public void takeCancel() {
    }

    private static final int REQUEST_CODE_CAMERA = 1;
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
//        PermissionManager.TPermissionType type=PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
//        PermissionManager.handlePermissionsResult(getActivity(),type,invokeParam,this);
        switch (requestCode){
            case REQUEST_CODE_CAMERA:
                if(grantResults.length >= 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    showPopupWindow();
                    setMapBottom();
                } else {
                    Toast.makeText(getActivity(), "权限获取失败", Toast.LENGTH_SHORT).show();
                }
                break;
            default:break;
        }
    }
    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam=invokeParam;
        }
        return type;
    }
    private InvokeParam invokeParam;
    private TakePhoto takePhoto;
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >23) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED&&
                    ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED ){
                //授予权限
//                showPopupWindow();
                setMapBottom();
            }else{
                //未获得权限
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},REQUEST_CODE_CAMERA);
            }
        }

    }
    MyPhotoBottomControlPanel myPhotoBottomControlPanel;
    public void setMapBottom() {
        if (myPhotoBottomControlPanel == null) {
            myPhotoBottomControlPanel = MyPhotoBottomControlPanel.newInstance(getActivity(),this);
        }
        myPhotoBottomControlPanel.show(my_all_bg);
    }

    public PopupWindow popupWindow;
    private void showPopupWindow() {
        if (popupWindow != null && popupWindow.isShowing()) {
            return;
        }

        View popView = View.inflate(getActivity(),R.layout.popup_take_photo,null);

        popupWindow = new CommonPopupWindow.Builder(getActivity())
                .setView(R.layout.popup_take_photo)
                .setAnimationStyle(R.style.AnimUp)
                .setBackGroundLevel(0.5f)
                .setWidthAndHeight(980,400)
                .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
                    @Override
                    public void getChildView(View view, int layoutResId) {
                        TextView photograph = view.findViewById(R.id.tv_avatar_photograph);
                        TextView selPhoto = view.findViewById(R.id.tv_avatar_photo);
                        TextView cancel = view.findViewById(R.id.tv_avatar_cancel);

                        photograph.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //TODO拍照

                                File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                                if (!file.getParentFile().exists()) {
                                    file.getParentFile().mkdirs();
                                }
                                Uri imageUri = Uri.fromFile(file);
                                takePhoto.onPickFromCapture(imageUri);


                                if (popupWindow != null) {
                                    popupWindow.dismiss();
                                }
                            }
                        });

                        selPhoto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //TODO相册

                                takePhoto.onPickFromGallery();
                                if (popupWindow != null) {
                                    popupWindow.dismiss();
                                }
                            }
                        });

                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //TODO  上传默认头像
                                 mPresenter.uploadImg("","","2");

                                if (popupWindow != null) {
                                    popupWindow.dismiss();
                                }
                            }
                        });

                    }
                })
                .setOutsideTouchable(true)
                .create();
        popupWindow.showAtLocation(popView, Gravity.BOTTOM,0,50);
    }
    /**
     *  获取TakePhoto实例
     * @return
     */
    public TakePhoto getTakePhoto(){
        if (takePhoto==null){
            takePhoto= (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this,this));
        }
        //配置图片压缩
        CompressConfig config=new CompressConfig.Builder()
                .setMaxSize(200) //500K
                .setMaxPixel(1000) //1000px
                .create();
        takePhoto.onEnableCompress(config,true);//true是否显示提示压缩dialog
        return takePhoto;
    }

    @Override
    public void onPhotoSelectWc(String mtype) {
        switch (mtype){
            case "1":
                //TODO拍照

                File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                Uri imageUri = Uri.fromFile(file);
                takePhoto.onPickFromCapture(imageUri);
                break;
            case "2":
                //TODO相册

                takePhoto.onPickFromGallery();
                break;
            case "3":
                //TODO  上传默认头像
                mPresenter.uploadImg("","","2");

                break;
        }
    }
}
