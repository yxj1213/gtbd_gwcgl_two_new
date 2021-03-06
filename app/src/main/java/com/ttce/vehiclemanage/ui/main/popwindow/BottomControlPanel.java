package com.ttce.vehiclemanage.ui.main.popwindow;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.google.gson.Gson;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.app.AppConstant;
import com.ttce.vehiclemanage.bean.MonitorResponseBean;
import com.ttce.vehiclemanage.ui.locus.activity.NewLocusActivity;
import com.ttce.vehiclemanage.ui.locus.activity.TrackActivity;
import com.ttce.vehiclemanage.ui.map.activity.AlarmListActivity;
import com.ttce.vehiclemanage.ui.map.activity.EquipmentDetailActivity;
import com.ttce.vehiclemanage.ui.map.activity.FenceListActivity;
import com.ttce.vehiclemanage.ui.map.activity.InstructActivity;
import com.ttce.vehiclemanage.utils.BDMapUtils;
import com.ttce.vehiclemanage.utils.MapNativeUtil;
import com.ttce.vehiclemanage.utils.ToastUtil;
import com.ttce.vehiclemanage.widget.MyLayout;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class BottomControlPanel implements MyLayout.MyLayoutCallBack{

    @Bind(R.id.tv_wd)
    TextView tvWd;
    @Bind(R.id.tv_yl)
    TextView tvYl;
    @Bind(R.id.tv_yl_sy)
    TextView tvylSy;
    private PopupWindow mPopupWindow;

    @Bind(R.id.my_layout)
    MyLayout my_layout;
    @Bind(R.id.iv_state)
    ImageView ivState;
    @Bind(R.id.tv_number)
    TextView tvNumber;
   /* @Bind(R.id.tv_speed)
    TextView tvSpeed;*/
    @Bind(R.id.tv_xh)
    TextView tvXh;
    @Bind(R.id.tv_wx)
    TextView tvWx;
    @Bind(R.id.tv_rlc)
    TextView tvRlc;
   /* @Bind(R.id.tv_state)
    TextView tvState;*/
    @Bind(R.id.tv_acc)
    TextView tvAcc;
    @Bind(R.id.tv_ylc)
    TextView tvYlc;
//    @Bind(R.id.tv_address)
//    TextView tvAddress;
    @Bind(R.id.tv_show_address)
    TextView tvShowAddress;
    @Bind(R.id.tv_route)
    TextView tv_route;

    @Bind(R.id.state_speed)
    TextView state_speed;


    @Bind(R.id.rl_show)
    RelativeLayout rlShow;
     @Bind(R.id.lin_yl_sy)
     LinearLayout lin_yl_sy;
    @Bind(R.id.lin_wd)
    LinearLayout lin_wd;

    private boolean isOpen = true;

    private MonitorResponseBean monitorResponseBean;

    private static BottomControlPanel instance;
    private Activity mContext;

    private ControlPanelListener mControlPanelListener;

    // ???????????????
    private GeoCoder mCoder;

    private String shareUrl;

    public void setmControlPanelListener(ControlPanelListener mControlPanelListener) {
        this.mControlPanelListener = mControlPanelListener;
    }

    private BottomControlPanel(Context context) {
        mContext = (Activity) context;
        View view = LayoutInflater.from(context).inflate(
                R.layout.layout_control_panel, null);
        ButterKnife.bind(this, view);
        mPopupWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        my_layout.setCallBack(this);
        //??????????????????
        mPopupWindow.setOutsideTouchable(false);
    }

    public void initGeoCoder() {
            mCoder = GeoCoder.newInstance();
            mCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
                @Override
                public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

                }

                @Override
                public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                    if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                        tvShowAddress.setText("??????????????????");
                    } else {
                        //????????????
                        String address="";
                        if(reverseGeoCodeResult.getAddress()!=null&&reverseGeoCodeResult.getSematicDescription()!=null){
                             address = reverseGeoCodeResult.getAddress()+","+reverseGeoCodeResult.getSematicDescription();
                        }
                        tvShowAddress.setText(address);
                    }
                }
            });
        mCoder.reverseGeoCode(new ReverseGeoCodeOption().location(BDMapUtils.convert(new LatLng(monitorResponseBean.getLat(), monitorResponseBean.getLng()))));
    }
    public static BottomControlPanel newInstance(Context context, ControlPanelListener controlPanelListener) {
        if (instance == null) {
            instance = new BottomControlPanel(context);
        }
        instance.setmControlPanelListener(controlPanelListener);
        //instance.initGeoCoder();

        return instance;
    }

    public void setData(MonitorResponseBean dataBean) {
        monitorResponseBean = dataBean;
       /* tvShowAddress.setVisibility(View.GONE);
        tvShowAddress.setText("");
        tvAddress.setVisibility(View.VISIBLE);*/
        tvNumber.setText(dataBean.getCarNumber());
        state_speed.setText(dataBean.getCarStatusFormat()+" | "+dataBean.getSpeed() + "km/h");
        tvRlc.setText("????????????" + dataBean.getMileage_DayFormat() + "km");
        tvAcc.setText("ACC???" + dataBean.getAccFormat());
        tvYlc.setText("????????????" + dataBean.getMileage_MonthFormat() + "km");
        tv_route.setText("?????????" + dataBean.getAngleFormat());
        tvXh.setText(dataBean.getGpsTimeFormat());//????????????
        tvWx.setText(dataBean.getServiceTimeFormat());//????????????
        shareUrl = AppConstant.SHARE_URL + "plate=" + monitorResponseBean.getCarNumber() + "&lng=" + monitorResponseBean.getLng() + "&lat=" + monitorResponseBean.getLat();
//        shareUrl ="http://192.168.0.119:65135/share/Position?"+ "plate=" + monitorResponseBean.getCarNumber() + "&lng=" + monitorResponseBean.getLng() + "&lat=" + monitorResponseBean.getLat();
        //TODO  ??????????????????
        if (dataBean.getIsOilSensor() == 0) {
            lin_yl_sy.setVisibility(View.GONE);
        } else {
            lin_yl_sy.setVisibility(View.VISIBLE);
            tvYl.setText("?????????"+dataBean.getOilQuantity());
            tvylSy.setText("?????????"+dataBean.getOilPercent());
        }
        if (dataBean.getIsTemperatureSensor() == 0) {
            lin_wd.setVisibility(View.GONE);
        } else {
            lin_wd.setVisibility(View.VISIBLE);
            tvWd.setText("?????????"+dataBean.getTemperatureFormat());
        }
       // mCoder.reverseGeoCode(new ReverseGeoCodeOption().location(BDMapUtils.convert(new LatLng(dataBean.getLat(), dataBean.getLng()))));
    }

    public void show(View contentView,Context context) {
        instance.initGeoCoder();
        mContext = (Activity) context;
        mControlPanelListener.onShowNextPreCar(contentView.getHeight());
        mPopupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
    }

    public void dissmiss() {
        mControlPanelListener.onDismissNextPreCar();
        mCoder.destroy();
        mPopupWindow.dismiss();
    }

    public boolean isShow() {
        return mPopupWindow.isShowing();
    }

    CoordinateConverter converter;

    @OnClick({R.id.tv_xh,R.id.tv_wx,R.id.iv_state, R.id.iv_close, R.id.tv_address, R.id.tv_zhiling, R.id.tv_gaojing, R.id.tv_weilan, R.id.tv_daohang, R.id.tv_detail, R.id.tv_share, /*R.id.iv_pre, R.id.iv_next,*/ R.id.tv_gj, R.id.tv_zz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_xh:
                ToastUtil.showToast("????????????");
                break;
            case R.id.tv_wx:
                ToastUtil.showToast("????????????");
                break;
            case R.id.iv_close:
                mCoder.destroy();
                mControlPanelListener.onDismissNextPreCar();
                mPopupWindow.dismiss();
                break;
            case R.id.tv_address:
//                    tvAddress.setVisibility(View.GONE);
//                    tvShowAddress.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_state:
                expend();
                break;
            case R.id.tv_zhiling:
                InstructActivity.goToPage(mContext, monitorResponseBean.getId(), monitorResponseBean.getDeviceId());
                break;
            case R.id.tv_gaojing:
                AlarmListActivity.goToPage(mContext, monitorResponseBean.getDeviceId());
                break;
            case R.id.tv_weilan:
                FenceListActivity.goToPage(mContext,new Gson().toJson(monitorResponseBean));
                break;
            case R.id.tv_daohang:
                showDaohang();
                break;
            case R.id.tv_detail:
                EquipmentDetailActivity.goToPage(mContext, monitorResponseBean.getDeviceId());
                break;
            case R.id.tv_share:
                showShare();
                break;
           /* case R.id.iv_pre:
                if (mControlPanelListener != null) {
                    mControlPanelListener.onPre();
                }
                break;
            case R.id.iv_next:
                if (mControlPanelListener != null) {
                    mControlPanelListener.onNext();
                }
                break;*/
            case R.id.tv_gj:
                NewLocusActivity.goToPage(mContext,new Gson().toJson(monitorResponseBean));
                break;
            case R.id.tv_zz:
                if (mControlPanelListener != null) {
                    mControlPanelListener.onZZ();
                }
                    TrackActivity.goToPage(mContext,new Gson().toJson(monitorResponseBean));
                break;
        }
    }

    private void showDaohang() {
        MaterialDialog materialDialog = new MaterialDialog.Builder(mContext).title("????????????")
                .widgetColorRes(R.color.blue_color1)// ????????????????????????
                .customView(R.layout.dialog_map_type, false).build();
        materialDialog.findViewById(R.id.tv_baidu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDialog.dismiss();
                goBaiduNav();
            }
        });
        materialDialog.findViewById(R.id.tv_gaode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDialog.dismiss();
                goGaodeNav();
            }
        });
        materialDialog.show();
    }

    /**
     * ???????????????????????? ????????????
     */
    private void goGaodeNav() {
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            Uri uri = Uri.parse("amapuri://route/plan/?sid=BGVIS1&slat=" +  monitorResponseBean.getStartlocationLat() + "&slon=" +  monitorResponseBean.getStartlocationLng()  + "&sname=" +  monitorResponseBean.getStartlocationDescribe()
                    + "&did=BGVIS2&dlat="+monitorResponseBean.getLat()+"&dlon="+monitorResponseBean.getLng()+"&dname="+tvShowAddress.getText().toString()+"&dev=0&t=0");
            intent.setData(uri);
            mContext.startActivity(intent);
        } catch (Exception e) {
            ToastUtil.showToast("?????????????????????");
        }
    }

    /**
     * ???????????????????????? ????????????
     */
    private void goBaiduNav() {
        try {
            Intent intent = new Intent();
            intent.setData(Uri.parse("baidumap://map/direction?region=&origin="+ "name:"+ monitorResponseBean.getStartlocationDescribe()+"|latlng:"+ monitorResponseBean.getStartlocationLat()+","+monitorResponseBean.getStartlocationLng()+
                    "&destination=name:" +tvShowAddress.getText().toString() +"|latlng:"+ monitorResponseBean.getLat()+","+monitorResponseBean.getLng() + "&mode=driving"));
            mContext.startActivity(intent);
        } catch (Exception e) {
            ToastUtil.showToast("?????????????????????");
        }
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare(); //??????sso??????
        oks.disableSSOWhenAuthorize();
        // title??????????????????QQ???QQ?????????????????????
        oks.setTitle(mContext.getString(R.string.app_name));
        // titleUrl QQ???QQ??????????????????
        oks.setTitleUrl(shareUrl);
        // text???????????????????????????????????????????????????
        oks.setText("????????????");
        // imagePath???????????????????????????Linked-In?????????????????????????????????
        oks.setImageUrl("http://gtbds.com/images/ic_launcher.png");//??????SDcard????????????????????????
        // url?????????????????????Facebook??????????????????
        oks.setUrl(shareUrl);
//        oks.setMusicUrl(shareUrl);
        // comment??????????????????????????????????????????????????????
//        oks.setComment("????????????????????????");
        Bitmap enableLogo = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.icon_copy_url);
        String label = "????????????";
        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                copyUrl();
            }
        };
        oks.setCustomerLogo(enableLogo, label, listener);

        // ????????????GUI
        oks.show(mContext);
    }

    private void copyUrl() {
        //???????????????????????????
        ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
// ?????????????????????ClipData
        ClipData mClipData = ClipData.newRawUri("Label", Uri.parse(shareUrl));
// ???ClipData?????????????????????????????????
        cm.setPrimaryClip(mClipData);
        ToastUtil.showToast("????????????");
    }


    private void showMapSelector(final double desLatitude, double desLongitude) {
        new MaterialDialog.Builder(mContext).theme(Theme.LIGHT).title("????????????").items(R.array.map_type)
                .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which,
                                               CharSequence text) {
                        LatLng baiduLatLng = BDMapUtils.convert(new LatLng(desLatitude, desLongitude));
                        switch (which) {
                            case 0:
                                MapNativeUtil.goToBaiduMap(mContext, 0, baiduLatLng.latitude + "",
                                        baiduLatLng.longitude + "");
                                break;
                            case 1:
                                MapNativeUtil.goTominimap(mContext, "tzjcyth", baiduLatLng.longitude +
                                        "", baiduLatLng.latitude + "");
                                break;
                        }
                        return true; // allow selection
                    }
                }).negativeText("??????").positiveText("??????")
                .show();
    }

    private void expend() {
        if (!isOpen) {
            //????????????????????????,????????????????????????
            isOpen = true;
            ivState.setImageResource(R.mipmap.icon_down);
            rlShow.setVisibility(View.VISIBLE);
        } else {
            //????????????????????????,?????????????????????
            isOpen = false;
            ivState.setImageResource(R.mipmap.icon_up);
            rlShow.setVisibility(View.GONE);
        }
    }

    @Override
    public void scrollByY(int m) {
        if (m < 0) {
            //????????????????????????,????????????????????????
            isOpen = true;
            ivState.setImageResource(R.mipmap.icon_down);
            rlShow.setVisibility(View.VISIBLE);

        } else {
            //????????????????????????,?????????????????????
            isOpen = false;
            ivState.setImageResource(R.mipmap.icon_up);
            rlShow.setVisibility(View.GONE);
        }
    }



    public interface ControlPanelListener {
        void onPre();

        void onNext();

        void onZZ();

        void onShowNextPreCar(int hight);

        void onDismissNextPreCar();
    }
}
