package com.ttce.vehiclemanage.ui.locus.popwindow;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.lai.library.ButtonStyle;
import com.ttce.vehiclemanage.BuildConfig;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.app.AppConstant;
import com.ttce.vehiclemanage.bean.MonitorResponseBean;
import com.ttce.vehiclemanage.ui.map.activity.EquipmentDetailActivity;
import com.ttce.vehiclemanage.utils.BDMapUtils;
import com.ttce.vehiclemanage.utils.CarStateFactory;
import com.ttce.vehiclemanage.utils.NumberUtils;
import com.ttce.vehiclemanage.utils.ToastUtil;
import com.ttce.vehiclemanage.widget.MyLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class BottomControlPanel
        implements MyLayout.MyLayoutCallBack, OnGetGeoCoderResultListener {

    @Bind(R.id.iv_state)
    ImageView ivState;
    @Bind(R.id.iv_close)
    ImageView ivClose;
    @Bind(R.id.rl_top)
    RelativeLayout rlTop;
    @Bind(R.id.iv_pre)
    ImageView ivPre;
    @Bind(R.id.tv_number)
    TextView tvNumber;
    @Bind(R.id.tv_status)
    ButtonStyle tvStatus;
    @Bind(R.id.iv_next)
    ImageView ivNext;
    @Bind(R.id.tv_state)
    TextView tvState;
    @Bind(R.id.tv_speed)
    TextView tvSpeed;
    @Bind(R.id.tv_rlc)
    TextView tvRlc;
    @Bind(R.id.ll_center)
    LinearLayout llCenter;
    @Bind(R.id.tv_loc_time)
    TextView tvLocTime;
    @Bind(R.id.tv_tongxun_time)
    TextView tvTongxunTime;
    @Bind(R.id.tv_ads)
    TextView tvAds;
    @Bind(R.id.tv_detail)
    TextView tvDetail;
    @Bind(R.id.tv_share)
    TextView tvShare;
    @Bind(R.id.my_layout)
    MyLayout my_layout;
    @Bind(R.id.rl_show)
    LinearLayout rlShow;
    @Bind(R.id.ll_share)
    LinearLayout ll_share;

    private PopupWindow mPopupWindow;
    private boolean isOpen = true;

    private MonitorResponseBean monitorResponseBean;

    private static BottomControlPanel instance;
    private Activity mContext;

    private ControlPanelListener mControlPanelListener;

    // 逆地理解析
    private GeoCoder mCoder;

    private String shareUrl;


    public void setmControlPanelListener(ControlPanelListener mControlPanelListener) {
        this.mControlPanelListener = mControlPanelListener;
    }

    private BottomControlPanel(Context context) {
        mContext = (Activity) context;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_track_control_panel, null);
        ButterKnife.bind(this, view);
        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        my_layout.setCallBack(this);
        // 点击外部消失
        mPopupWindow.setOutsideTouchable(false);
    }

    public void initGeoCoder() {
        mCoder = GeoCoder.newInstance();
        mCoder.setOnGetGeoCodeResultListener(this);
    }

    public static BottomControlPanel newInstance(Context context, ControlPanelListener controlPanelListener) {
        if (instance == null) {
            instance = new BottomControlPanel(context);
        }
        instance.setmControlPanelListener(controlPanelListener);
        instance.initGeoCoder();
        return instance;
    }

    public void setData(MonitorResponseBean dataBean) {
        ivPre.setVisibility(View.GONE);
        ivNext.setVisibility(View.GONE);
        monitorResponseBean = dataBean;
        tvNumber.setText(dataBean.getCarNumber());
        tvStatus.setText(dataBean.getCarStatusFormat());
        tvStatus.setNormalColor(CarStateFactory.getCarTextColorByStatus(
                dataBean.getCarDisplayColorFormat()));
        tvStatus.setStrokeWidth(0);
        tvSpeed.setText("速度：" + dataBean.getSpeed() + "km/h");
        tvRlc.setText("日里程：" + dataBean.getMileage_DayFormat() + "km");
        tvState.setText("ACC：" + dataBean.getAccFormat());
        tvLocTime.setText("定位时间：" + dataBean.getGpsTimeFormat());
        tvTongxunTime.setText("通讯时间：" + dataBean.getServiceTimeFormat());
        mCoder.reverseGeoCode(new ReverseGeoCodeOption()
                .location(BDMapUtils.convert(new LatLng(monitorResponseBean.getLat(), monitorResponseBean.getLng()))));
        shareUrl = AppConstant.SHARE_URL + "plate=" + monitorResponseBean.getCarNumber() + "&lng=" + monitorResponseBean.getLng() + "&lat=" + monitorResponseBean.getLat();
        if (!BuildConfig.CHANNEL.equals(AppConstant.HUAWEIYUN_CHANNEL)) {
            ll_share.setVisibility(View.GONE);
        } else {
            ll_share.setVisibility(View.VISIBLE);
        }
    }

    public void show(View contentView) {
        if(contentView!=null){
            contentView.post(new Runnable() {
                @Override
                public void run() {
                    mPopupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
                }
            });
        }
    }

    public void dissmiss() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
        mCoder.destroy();
    }

    CoordinateConverter converter;

    @OnClick({R.id.iv_state, R.id.iv_close, R.id.tv_detail, R.id.tv_share, R.id.iv_pre, R.id.iv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                mPopupWindow.dismiss();
                break;
            case R.id.iv_state:
                expend();
                break;
            case R.id.tv_detail:
                EquipmentDetailActivity.goToPage(mContext, monitorResponseBean.getDeviceId());
                break;
            case R.id.tv_share:
                showShare();
                break;
            case R.id.iv_pre:
                if (mControlPanelListener != null) {
                    mControlPanelListener.onPre();
                }
                break;
            case R.id.iv_next:
                if (mControlPanelListener != null) {
                    mControlPanelListener.onNext();
                }
                break;
        }
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare(); //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(mContext.getString(R.string.app_name));
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl(shareUrl);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("共享位置");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImageUrl("http://gtbds.com/images/ic_launcher.png");//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl(shareUrl);
//        oks.setMusicUrl(shareUrl);
        // comment是我对这条分享的评论，仅在人人网使用
//        oks.setComment("我是测试评论文本");

        Bitmap enableLogo = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.icon_copy_url);
        String label = "复制链接";
        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                copyUrl();
            }
        };
        oks.setCustomerLogo(enableLogo, label, listener);

        // 启动分享GUI
        oks.show(mContext);
    }

    private void copyUrl() {
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
// 创建普通字符型ClipData
        ClipData mClipData = ClipData.newRawUri("Label", Uri.parse(shareUrl));
// 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
        ToastUtil.showToast("复制成功");
    }

    private void expend() {
        if (!isOpen) {
            // 点击前是关闭状态,点击后就扩展拉开
            isOpen = true;
            ivState.setImageResource(R.mipmap.icon_down);
            rlShow.setVisibility(View.VISIBLE);
        } else {
            // 点击前是开启状态,点击后就缩回去
            isOpen = false;
            ivState.setImageResource(R.mipmap.icon_up);
            rlShow.setVisibility(View.GONE);
        }
    }

    @Override
    public void scrollByY(int m) {
        if (m < 0) {
            // 点击前是关闭状态,点击后就扩展拉开
            isOpen = true;
            ivState.setImageResource(R.mipmap.icon_down);
            rlShow.setVisibility(View.VISIBLE);

        } else {
            // 点击前是开启状态,点击后就缩回去
            isOpen = false;
            ivState.setImageResource(R.mipmap.icon_up);
            rlShow.setVisibility(View.GONE);
        }
    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

        if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
            tvAds.setText("地址：暂无");
        } else {
            // 详细地址
            String address="";
            if(reverseGeoCodeResult.getAddress()!=null&&reverseGeoCodeResult.getSematicDescription()!=null){
                address = reverseGeoCodeResult.getAddress()+","+reverseGeoCodeResult.getSematicDescription();
            }
            tvAds.setText("地址：" + address);
        }
    }

    public interface ControlPanelListener {
        void onPre();

        void onNext();
    }
}
