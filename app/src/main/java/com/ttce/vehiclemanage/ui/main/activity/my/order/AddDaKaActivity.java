package com.ttce.vehiclemanage.ui.main.activity.my.order;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jaydenxiao.common.base.BaseActivity;
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
import com.ttce.vehiclemanage.bean.CarLatLngBean;
import com.ttce.vehiclemanage.bean.CarRecordsBean;
import com.ttce.vehiclemanage.bean.EmptyOrderBean;
import com.ttce.vehiclemanage.bean.MarkerDetailsBean;
import com.ttce.vehiclemanage.bean.TravelListBean;
import com.ttce.vehiclemanage.bean.WorkBeanchBean;
import com.ttce.vehiclemanage.ui.main.adapter.WorkBeanchAdapter;
import com.ttce.vehiclemanage.ui.main.contract.needcar.MyOrderConstract;
import com.ttce.vehiclemanage.ui.main.model.needcar.MyOrderModel;
import com.ttce.vehiclemanage.ui.main.presenter.needcar.MyOrderPresenter;
import com.ttce.vehiclemanage.utils.BDMapUtils;
import com.ttce.vehiclemanage.utils.ImageUtil;
import com.ttce.vehiclemanage.utils.ToastUtil;
import com.ttce.vehiclemanage.utils.dialog_photo.CommonPopupWindow;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 已修改
 * */
public class AddDaKaActivity extends BaseActivity<MyOrderPresenter, MyOrderModel> implements TakePhoto.TakeResultListener, InvokeListener,MyOrderConstract.View,WorkBeanchAdapter.DelListener ,OnGetGeoCoderResultListener {

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.tv_address)
    TextView tv_address;
    @Bind(R.id.tv_dala_class)
    TextView tv_dala_class;
    @Bind(R.id.edt_tjdmc)
    EditText edt_tjdmc;
    @Bind(R.id.edt_bt)
    EditText edt_bt;
    @Bind(R.id.edt_nr)
    EditText edt_nr;
    @Bind(R.id.mmapview)
    TextureMapView mmapview;
    @Bind(R.id.recycler_view)
    IRecyclerView recycler_view;

    BaiduMap mBaiduMap;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_daka;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }
    EmptyOrderBean.OrderMarkListBean item;
    String orderId,MarkLevel,MarkType,markbean,CarFlow_Order_MarkId="",carId="";
    // 逆地理解析
    private GeoCoder mCoder;

    @Override
    public void initView() {
       /* initImagePicker();*/
        tuPianData();
        initGeoCoder();

        mBaiduMap=mmapview.getMap();


        titleBarTitle.setText(getResources().getString(R.string.me_txt10));
        orderId=this.getIntent().getStringExtra("orderId");
        MarkLevel=this.getIntent().getStringExtra("MarkLevel");
        MarkType=this.getIntent().getStringExtra("MarkType");
        markbean=this.getIntent().getStringExtra("markbean");
        carId=this.getIntent().getStringExtra("carId");
        if (MarkType.equals("10")) {//途经打卡
            tv_dala_class.setText("途经打卡");
            if(!markbean.equals("")){
                item=new Gson().fromJson(markbean, EmptyOrderBean.OrderMarkListBean.class);
                edt_tjdmc.setText(item.getNeedMarkTitle());
                CarFlow_Order_MarkId=item.getCarFlow_Order_MarkId();
            }
        } else if (MarkType.equals("20")) {  //新增打卡
            tv_dala_class.setText("新增打卡");
        }

        mPresenter.getCarFlowPositionBean(carId);
        beginRefreshTimer();
    }
    private Timer sendMsgTimer;
    @Override
    protected void onPause() {
        super.onPause();
        if(sendMsgTimer!=null){
            sendMsgTimer.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(sendMsgTimer!=null){
            sendMsgTimer.cancel();
        }
    }

    /**
     * 开始刷新定时器
     */
    private void beginRefreshTimer() {
        sendMsgTimer = new Timer();
        sendMsgTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mPresenter.getCarFlowPositionBean(carId);
            }
        }, 0, 20000);
    }

    int PIC_MAX=3;
    int REQUEST_IMAGE=1;
    List<WorkBeanchBean> scrymlist;
    WorkBeanchAdapter scryworkBeanchAdapter;
//    ArrayList<String> mlist;
    private void tuPianData() {
        scrymlist=new ArrayList<>();
        scrymlist.add(new WorkBeanchBean("添加"));
        scryworkBeanchAdapter = new WorkBeanchAdapter(this, R.layout.pic_item, scrymlist,"新增打卡",this);
        scryworkBeanchAdapter.openLoadAnimation(new ScaleInAnimation());
        recycler_view.setLayoutManager(new GridLayoutManager(this, 4));
        recycler_view.setAdapter(scryworkBeanchAdapter);
        scryworkBeanchAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                if(((WorkBeanchBean) o).getTitle().equals("添加")){
                    if(scrymlist.size()-1<PIC_MAX){
                        requestPermission();
                    }else{
                        ToastUtil.showToast("最多只能选3张。");
                    }
                }else{
                    ArrayList<String> mlist=new ArrayList<>();
                    for (int i=1;i<scrymlist.size();i++) {
                        mlist.add(scrymlist.get(i).getTitle());
                    }
                    BigImagePagerActivity.startImagePagerActivity(AddDaKaActivity.this,mlist,position-1);
                }
                /*if(((WorkBeanchBean) o).getTitle().equals("添加")){
                    paizhao();
                }else{
                    ArrayList<ImageItem> mlist=new ArrayList<>();
                    for (int i=1;i<scrymlist.size();i++) {
                        ImageItem imageItem= new ImageItem();
                        imageItem.path=scrymlist.get(i).getTitle();
                        mlist.add(imageItem);
                    }
                    //打开预览
                    Intent intentPreview = new Intent(AddDaKaActivity.this, ImagePreviewDelActivity.class);
                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS,mlist);
                    intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position-1);
                    intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                    startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                }*/
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }

  /*  public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(PIC_MAX);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }
    public void paizhao(){
        if(scrymlist.size()<PIC_MAX+1){
            //打开选择,本次允许选择的数量
            ImagePicker.getInstance().setSelectLimit(PIC_MAX);
            Intent intent1 = new Intent(this, ImageGridActivity.class);
            *//* 如果需要进入选择的时候显示已经选中的图片，
             * 详情请查看ImagePickerActivity
             * *//*

            ArrayList<ImageItem> mlist=new ArrayList<>();
            for (int i=1;i<scrymlist.size();i++) {
                ImageItem imageItem= new ImageItem();
                imageItem.path=scrymlist.get(i).getTitle();
                imageItem.addTime=scrymlist.get(i).getTime();
                mlist.add(imageItem);
            }
            intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,mlist);

            startActivityForResult(intent1, REQUEST_CODE_SELECT);
        }else{
            ToastUtil.showToast("最多只能选择"+PIC_MAX+"张图片哦！");
        }
    }
    private ImagePicker imagePicker;
    List<ImageItem> allimg=new ArrayList<>();
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                ArrayList<ImageItem>  images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    List<WorkBeanchBean> itemList = new ArrayList<>();
                    for (ImageItem tImage : images) {
                        for (WorkBeanchBean workBeanchBean : scrymlist) {
                            if (workBeanchBean.getTitle().equals(tImage.path)) {
                                itemList.add(workBeanchBean);
                            }
                        }
                        scrymlist.add(new WorkBeanchBean(tImage.path,tImage.addTime));
                        allimg.clear();
                        allimg.add(tImage);
                    }
                    if (itemList != null && itemList.size() > 0) {
                        scrymlist.removeAll(itemList);
                    }
                    scryworkBeanchAdapter.notifyDataSetChanged();
                }
            }
        }
    }
    private static final int REQUEST_CODE_CAMERA = 1;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE_CAMERA:
                if(grantResults.length >= 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    paizhao();
                } else {
                    Toast.makeText(this, "权限打开失败", Toast.LENGTH_SHORT).show();
                }
                break;
            default:break;
        }
    }*/
    /**
     * 创建地图中心点marker
     */
    private void createCenterMarker(LatLng latLng) {
        MarkerOptions markerOptions = new MarkerOptions()
                .position(BDMapUtils.convert(latLng))
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.water_drop))
                .zIndex(9)
                .draggable(true);
        mBaiduMap.addOverlay(markerOptions);
    }
    @OnClick({R.id.title_bar_back,R.id.tv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                finish();
                break;
            case R.id.tv_add:

                if(edt_tjdmc.getText().toString().trim().equals("")){
                    ToastUtil.showToast("请输入途经点名称。");
                    return;
                }
                startProgressDialog();
                if(scrymlist.size()==1){
                    JsonArray MarkImgUrlList= new JsonArray();
                    mPresenter.getOrderMarkAddPresenter("新增打卡",orderId,edt_tjdmc.getText().toString().trim(),tv_address.getText().toString().trim(),String.valueOf(carlng),String.valueOf(catlat),edt_bt.getText().toString().trim(),edt_nr.getText().toString().trim(),MarkLevel,MarkType,CarFlow_Order_MarkId,MarkImgUrlList,true,"","",null,null,"","",false);
                }else{
                    JsonArray MarkImgUrlList= new JsonArray();
                    for (int i=1;i<scrymlist.size();i++) {
                        JsonObject objectInList = new JsonObject();
                        objectInList.addProperty("FileBase64", ImageUtil.createBase64(scrymlist.get(i).getTitle()));
                        objectInList.addProperty("FileBaseFormat","data:image/"+scrymlist.get(i).getTitle().substring(scrymlist.get(i).getTitle().lastIndexOf(".")+1,scrymlist.get(i).getTitle().length()));
                        objectInList.addProperty("OrderId",orderId);
                        objectInList.addProperty("OrderMarkId",CarFlow_Order_MarkId);
                        objectInList.addProperty("MarkImageSort",(i+1));
                        MarkImgUrlList.add(objectInList);
                    }
                    mPresenter.getOrderMarkAddPresenter("新增打卡",orderId,edt_tjdmc.getText().toString().trim(),tv_address.getText().toString().trim(),String.valueOf(carlng),String.valueOf(catlat),edt_bt.getText().toString().trim(),edt_nr.getText().toString().trim(),MarkLevel,MarkType,CarFlow_Order_MarkId,MarkImgUrlList,true,"","",null,null,"","",false);

                   /* for (int i=1;i<scrymlist.size();i++) {
                        luBanCompres(scrymlist.get(i).getTitle(),i-1);

                    }*/
                }
                break;
        }
    }
   /* List<String> tjtp=new ArrayList<>();
    public void luBanCompres(String path,int pos){
        Luban.with(this)
                .load(path)
                .ignoreBy(100)
                .putGear(1)
                .setTargetDir(AddDaKaActivity.this.mContext.getExternalCacheDir().toString())
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
                        startProgressDialog();
                    }

                    @Override
                    public void onSuccess(File file) {
                        // TODO 压缩成功后调用，返回压缩后的图片文件
                        if(file!=null&&file.length()/1024>200){
                            luBanCompres(file.getAbsolutePath(),pos);
                        }else{
                            tjtp.add(file.getAbsolutePath());
                            if(tjtp.size()==scrymlist.size()-1){
                                JsonArray MarkImgUrlList= new JsonArray();
                                for (int i=0;i<tjtp.size();i++) {
                                    JsonObject objectInList = new JsonObject();
                                    objectInList.addProperty("FileBase64", ImageUtil.createBase64(tjtp.get(i)));
                                    objectInList.addProperty("FileBaseFormat","data:image/"+tjtp.get(i).substring(tjtp.get(i).lastIndexOf(".")+1,tjtp.get(i).length()));
                                    objectInList.addProperty("OrderId",orderId);
                                    objectInList.addProperty("OrderMarkId",CarFlow_Order_MarkId);
                                    objectInList.addProperty("MarkImageSort",(i+1));
                                    MarkImgUrlList.add(objectInList);
                                }
                                mPresenter.getOrderMarkAddPresenter("新增打卡",orderId,edt_tjdmc.getText().toString().trim(),tv_address.getText().toString().trim(),String.valueOf(carlng),String.valueOf(catlat),edt_bt.getText().toString().trim(),edt_nr.getText().toString().trim(),MarkLevel,MarkType,CarFlow_Order_MarkId,MarkImgUrlList,true,"","",null,null,"","",false);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO 当压缩过程出现问题时调用
                    }
                }).launch();
    }*/
    double carlng,catlat;
    public static void goToPage(Activity activity,String orderId,String MarkLevel,String MarkType,String markbean,String carId) {
        Intent intent = new Intent(activity, AddDaKaActivity.class);
        intent.putExtra("orderId",orderId);
        intent.putExtra("MarkLevel",MarkLevel);
        intent.putExtra("MarkType",MarkType);
        intent.putExtra("markbean",markbean);
        intent.putExtra("carId",carId);
        activity.startActivity(intent);
    }

    @Override
    public void del(String mtype, int pos) {
        scrymlist.remove(pos);
        scryworkBeanchAdapter.notifyDataSetChanged();
    }

    @Override
    public void returnOrderDriverGrab(String carPositionBeans) {

    }

    @Override
    public void returnMyOrderCancel(String carFlow_OrderId, String type) {

    }

    @Override
    public void returnMyOrderDetails(EmptyOrderBean carFlow_OrderId) {

    }

    @Override
    public void returnAllOrderList(List<EmptyOrderBean> emptyOrderBean) {

    }

    @Override
    public void returnCarFlowPosition(CarLatLngBean latLng) {
        carlng=latLng.getLng();
        catlat=latLng.getLat();
        LatLng latLng1=new LatLng(latLng.getLat(),latLng.getLng());
        createCenterMarker(latLng1);

        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(latLng1, 13);
        mBaiduMap.setMapStatus(mapStatusUpdate);
        mCoder.reverseGeoCode(new ReverseGeoCodeOption()
                .location(BDMapUtils.convert(latLng1)));
    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
            tv_address.setText("解析地址错误");
        } else {
            //详细地址
            String address="";
            if(reverseGeoCodeResult.getAddress()!=null&&reverseGeoCodeResult.getSematicDescription()!=null){
                address = reverseGeoCodeResult.getAddress()+","+reverseGeoCodeResult.getSematicDescription();
            }
            tv_address.setText(address);
        }
        mmapview.getChildAt(2).setPadding(0, 0, 0, tv_address.getMeasuredHeight()/2);//这是控制缩放控件的位置
    }


    public void initGeoCoder() {
        mCoder = GeoCoder.newInstance();
        mCoder.setOnGetGeoCodeResultListener(this);
    }

    @Override
    public void returnOrderStateLogList(List<CarRecordsBean> carRecordsBeans) {

    }

    @Override
    public void returnOrderMarkAddBean(String str) {
        stopProgressDialog();
        EventBus.getDefault().postSticky(new EmptyOrderBean.OrderMarkListBean(MarkType));
        finish();
    }

    @Override
    public void returnOrderMarkModelBean(MarkerDetailsBean str) {

    }

    @Override
    public void drawTravel(TravelListBean travelListBeanList, int type, int state, int alllistsize) {

    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {
       ToastUtil.showToast(msg);
    }

    @Override
    public void takeSuccess(TResult result) {
        scrymlist.add(new WorkBeanchBean(result.getImage().getCompressPath()));
        scryworkBeanchAdapter.notifyDataSetChanged();
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
                    showPopupWindow();
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
    private static final int REQUEST_CODE_CAMERA = 1;
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED&&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED ){
                //授予权限
                showPopupWindow();
            }else{
                //未获得权限
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},REQUEST_CODE_CAMERA);
            }
        }

    }
    public PopupWindow popupWindow;
    private void showPopupWindow() {
        if (popupWindow != null && popupWindow.isShowing()) {
            return;
        }

        View popView = View.inflate(this,R.layout.popup_take_photo_daka,null);
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popup_take_photo_daka)
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
}