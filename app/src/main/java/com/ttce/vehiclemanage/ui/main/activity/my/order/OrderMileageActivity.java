package com.ttce.vehiclemanage.ui.main.activity.my.order;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.aspsine.irecyclerview.IRecyclerView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.imagePager.BigImagePagerActivity;
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
import com.ttce.vehiclemanage.bean.EmptyOrderBean;
import com.ttce.vehiclemanage.ui.main.adapter.WorkBeanchAdapter;
import com.ttce.vehiclemanage.ui.main.contract.workbean.OrderMileageContract;
import com.ttce.vehiclemanage.ui.main.model.workbean.OrderMileageModel;
import com.ttce.vehiclemanage.ui.main.presenter.workbean.OrderMileagePresenter;
import com.ttce.vehiclemanage.utils.AlertDialogUtils;
import com.ttce.vehiclemanage.utils.ImageUtil;
import com.ttce.vehiclemanage.utils.ToastUtil;
import com.ttce.vehiclemanage.utils.glide.GlideUtils;

import org.greenrobot.eventbus.EventBus;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.OnClick;

/**
 * 已修改
 */
public class OrderMileageActivity extends BaseActivity<OrderMileagePresenter, OrderMileageModel> implements TakePhoto.TakeResultListener, InvokeListener ,OrderMileageContract.View,WorkBeanchAdapter.DelListener,AlertDialogUtils.DialogDataListener{
    @Bind(R.id.recycler_view)
    IRecyclerView recycler_view;
    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.img)
    ImageView img;
    @Bind(R.id.rel_creama)
    RelativeLayout rel_creama;
    @Bind(R.id.rel_pic)
    RelativeLayout rel_pic;
    @Bind(R.id.img_camera)
    ImageView img_camera;
   @Bind(R.id.edt_licheng)
   EditText edt_licheng;

    /**
     * 入口
     *
     * @param activity
     */
    public static void goToPage(Activity activity, boolean isHasMark, int orderstate, String Order_Mark_List, String OrderId, double lat,double lng) {
        Intent intent = new Intent(activity, OrderMileageActivity.class);
        Bundle bundle=new Bundle();
        bundle.putBoolean("isHasMark",isHasMark);
        bundle.putInt("orderstate",orderstate);
        bundle.putString("Order_Mark_List",Order_Mark_List);
        bundle.putString("OrderId",OrderId);
        bundle.putDouble("lat",lat);
        bundle.putDouble("lng",lng);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_mileage;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    boolean isHasMark;
    int orderstate;
    List<EmptyOrderBean.OrderMarkListBean> currentordermark;
    String Order_Mark_List,OrderId;
    double lat,lng;
    @Override
    public void initView() {
        isHasMark=this.getIntent().getExtras().getBoolean("isHasMark");
        orderstate=this.getIntent().getExtras().getInt("orderstate",0);
        Order_Mark_List=this.getIntent().getExtras().getString("Order_Mark_List");
        OrderId=this.getIntent().getExtras().getString("OrderId");
        lat=this.getIntent().getExtras().getDouble("lat");
        lng=this.getIntent().getExtras().getDouble("lng");
        currentordermark =new Gson().fromJson(Order_Mark_List, new TypeToken<List<EmptyOrderBean.OrderMarkListBean>>() {}.getType());


        titleBarTitle.setText(/*getResources().getString(R.string.me_txt0)*/"");
    }

    ArrayList<String> mlist;
    private static final int REQUEST_CODE_CAMERA = 1;
    String imgpath="";

/*    private ImagePicker imagePicker;*/
    @OnClick({R.id.rel_creama,R.id.img_del,R.id.rel_pic,R.id.title_bar_back,R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                finish();
                break;
            case R.id.rel_creama:
           /*     imagePicker=ImagePicker.getInstance();
                if (!(checkPermission(Manifest.permission.CAMERA))) {
                    ActivityCompat.requestPermissions(OrderMileageActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSION_CAMERA);
                } else {
                    imagePicker.takePicture(OrderMileageActivity.this, ImagePicker.REQUEST_CODE_TAKE);
                }*/

                requestPermission();
                break;
            case R.id.img_del:
                imgpath="";
                mlist.remove(mlist);
                rel_creama.setVisibility(View.VISIBLE);
                rel_pic.setVisibility(View.GONE);
                break;
            case R.id.rel_pic:
                BigImagePagerActivity.startImagePagerActivity(this,mlist,0);
               /* Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS,mlist);
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, 0);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);*/
                break;
            case R.id.tv_submit:
                if(edt_licheng.getText().toString().trim().equals("")){
                    ToastUtil.showToast("请输入当前里程。");
                    return;
                }
                if(imgpath.equals("")){
                    ToastUtil.showToast("请对里程表拍照并上传。");
                    return;
                }
                switch (orderstate){
                    case 150://前往目的地
//                        luBanCompres(imgpath,"前往目的地");

                        getSaveImage("前往目的地");
                        break;
                    case 160://开始出发
                        AlertDialogUtils.showAlertDialog(this, getResources().getString(R.string.dialog_ts5_8), 2, this);
                        break;
                    case 170://结束用车
                        AlertDialogUtils.showAlertDialog(this, getResources().getString(R.string.dialog_ts5_5), 2, this);
                        break;
                }
                break;
        }
    }
 public void getSaveImage(String type){
        startProgressDialog();
     JsonArray MarkImgUrlList= new JsonArray();
     JsonObject objectInList = new JsonObject();
     objectInList.addProperty("OrderId",OrderId);
     objectInList.addProperty("MileageImageType","10");
     objectInList.addProperty("MarkImageSort",0);
     objectInList.addProperty("FileBase64",ImageUtil.createBase64(imgpath));
     objectInList.addProperty("FileBaseFormat","data:image/"+imgpath.substring(imgpath.lastIndexOf(".")+1,imgpath.length()));
     MarkImgUrlList.add(objectInList);
     mPresenter.getCarFlowOrder_statePresenter(type,OrderId,edt_licheng.getText().toString().trim(),MarkImgUrlList);
 }
  /*  @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                imagePicker.takePicture(this, ImagePicker.REQUEST_CODE_TAKE);
            } else {
                Toast.makeText(this, "权限被禁止，无法打开相机", Toast.LENGTH_SHORT);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == ImagePicker.REQUEST_CODE_TAKE) {//直接拍照返回
            //发送广播通知图片增加了
            ImagePicker.galleryAddPic(this, imagePicker.getTakeImageFile());
            *//**
             * 2017-03-21 对机型做旋转处理
             *//*
                imgpath= imagePicker.getTakeImageFile().getAbsolutePath();
                GlideUtils.displayAdd(OrderMileageActivity.this,img_camera,imgpath);
                mlist=new ArrayList<>();
                ImageItem imageItem=new ImageItem();
                imageItem.path=imgpath;
                mlist.add(imageItem);
                rel_creama.setVisibility(View.GONE);
                rel_pic.setVisibility(View.VISIBLE);

            }
    }
*/
/*
    public boolean checkPermission(@NonNull String permission) {
        return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    List<String> tjtp=new ArrayList<>();
    public void luBanCompres(String path,String type){
        Luban.with(this)
                .load(path)
                .ignoreBy(100)
                .putGear(1)
                .setTargetDir(OrderMileageActivity.this.mContext.getExternalCacheDir().toString())
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                    }

                    @Override
                    public void onSuccess(File file) {
                        // TODO 压缩成功后调用，返回压缩后的图片文件
                        if(file!=null&&file.length()/1024>200){
                            luBanCompres(file.getAbsolutePath(),type);
                        }else{
                            tjtp.add(file.getAbsolutePath());
//                            if(tjtp.size()==scrymlist.size()-1){
                                JsonArray MarkImgUrlList= new JsonArray();
                                JsonObject objectInList = new JsonObject();
                                objectInList.addProperty("OrderId",OrderId);
                                objectInList.addProperty("MileageImageType","10");
                                objectInList.addProperty("MarkImageSort",0);
                                objectInList.addProperty("FileBase64",ImageUtil.createBase64(tjtp.get(0)));
                                objectInList.addProperty("FileBaseFormat","data:image/"+tjtp.get(0).substring(tjtp.get(0).lastIndexOf(".")+1,tjtp.get(0).length()));
                                MarkImgUrlList.add(objectInList);
                                mPresenter.getCarFlowOrder_statePresenter(type,OrderId,edt_licheng.getText().toString().trim(),MarkImgUrlList);
//                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO 当压缩过程出现问题时调用
                    }
                }).launch();
    }*/
    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {
        ToastUitl.showToastWithImg(msg, R.drawable.ic_wrong);
    }

    @Override
    public void del(String mtype, int pos) {
//        scrymlist.remove(pos);
//        scryworkBeanchAdapter.notifyDataSetChanged();
    }

    @Override
    public void ondialogwc(String mtype, String neir1, String neir2) {
        if(mtype.equals(getResources().getString(R.string.dialog_ts5_8))){
            if(isHasMark==true){
                JsonArray MarkImgUrlList= new JsonArray();
                mPresenter.getOrderMarkAddPresenter("司机开始出发",OrderId,currentordermark.get(0).getNeedMarkTitle(),currentordermark.get(0).getNeedMarkSimpleAddress(),String.valueOf(lng),String.valueOf(lat),"","","0","10",currentordermark.get(0).getCarFlow_Order_MarkId(),MarkImgUrlList,true,"","",null,null,"","",false);
            }else{
                JsonArray MarkImgUrlList= new JsonArray();
                mPresenter.getOrderMarkAddPresenter("司机开始出发",OrderId,"","",String.valueOf(lng),String.valueOf(lat),"","","0","20","",MarkImgUrlList,true,"","",null,null,"","",false);
            }

        }else if(mtype.equals(this.getString(R.string.dialog_ts5_5))){
            if(isHasMark==false){
                JsonArray MarkImgUrlList= new JsonArray();
                mPresenter.getOrderMarkAddPresenter("结束用车",OrderId,"","",String.valueOf(lng),String.valueOf(lat),"","","0","20","",MarkImgUrlList,true,"","",null,null,"","",true);
            }else{
                JsonArray MarkImgUrlList= new JsonArray();
                mPresenter.getOrderMarkAddPresenter("结束用车",OrderId,currentordermark.get(currentordermark.size() - 1).getNeedMarkTitle(),currentordermark.get(currentordermark.size() - 1).getNeedMarkSimpleAddress(),String.valueOf(lng),String.valueOf(lat),"","",currentordermark.size() - 1+"","10",currentordermark.get(currentordermark.size() - 1).getCarFlow_Order_MarkId(),MarkImgUrlList,true,"","",null,null,"","",false);
            }
        }
    }

    @Override
    public void returnCarFlowOrder_stateView(String str) {
        EventBus.getDefault().postSticky("刷新列表");
        stopProgressDialog();
        finish();
    }

    @Override
    public void returnOrderMarkAddBean(String str) {

        JsonArray MarkImgUrlList= new JsonArray();
        JsonObject objectInList = new JsonObject();
        objectInList.addProperty("OrderId",OrderId);
        objectInList.addProperty("MileageImageType","10");
        objectInList.addProperty("MarkImageSort",0);
        objectInList.addProperty("FileBase64",ImageUtil.createBase64(imgpath));
        objectInList.addProperty("FileBaseFormat","data:image/"+imgpath.substring(imgpath.lastIndexOf(".")+1,imgpath.length()));
        MarkImgUrlList.add(objectInList);
        if(str.equals("结束用车")){
            getSaveImage("结束用车");
//            luBanCompres(imgpath,);
        }else if(str.equals("司机开始出发")){
            getSaveImage("开始出发");
//            luBanCompres(imgpath,"开始出发");
        }
    }


    //TODO
    @Override
    public void takeSuccess(TResult result) {
        imgpath= result.getImage().getCompressPath();
        GlideUtils.displayAdd(OrderMileageActivity.this,img_camera,imgpath);
        mlist=new ArrayList<>();
        mlist.add(imgpath);
        rel_creama.setVisibility(View.GONE);
        rel_pic.setVisibility(View.VISIBLE);
    }

    @Override
    public void takeFail(TResult result, String msg) {

    }

    @Override
    public void takeCancel() {

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
//        PermissionManager.TPermissionType type=PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
//        PermissionManager.handlePermissionsResult(getActivity(),type,invokeParam,this);
        switch (requestCode){
            case REQUEST_CODE_CAMERA:
                if(grantResults.length >= 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startCamera();
                } else {
                    Toast.makeText(this, "权限获取失败", Toast.LENGTH_SHORT).show();
                }
                break;
            default:break;
        }
    }

    private InvokeParam invokeParam;
    private TakePhoto takePhoto;
    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam=invokeParam;
        }
        return type;
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

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED&&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED ){
                //授予权限
                startCamera();
            }else{
                //未获得权限
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},REQUEST_CODE_CAMERA);
            }
        }

    }
    public void startCamera(){
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Uri imageUri = Uri.fromFile(file);
        takePhoto.onPickFromCapture(imageUri);
    }

}