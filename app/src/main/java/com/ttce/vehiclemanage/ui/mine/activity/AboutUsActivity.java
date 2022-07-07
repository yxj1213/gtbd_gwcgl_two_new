package com.ttce.vehiclemanage.ui.mine.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.azhon.appupdate.config.UpdateConfiguration;
import com.azhon.appupdate.listener.OnButtonClickListener;
import com.azhon.appupdate.listener.OnDownloadListener;
import com.azhon.appupdate.manager.DownloadManager;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.BuildConfig;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.app.AppConstant;
import com.ttce.vehiclemanage.bean.AppUpdateBean;
import com.ttce.vehiclemanage.bean.ChangeCompanyBean;
import com.ttce.vehiclemanage.bean.ChangeCompanySaveReturnBean;
import com.ttce.vehiclemanage.bean.CloseAccountStateBean;
import com.ttce.vehiclemanage.bean.UserInfoBean;
import com.ttce.vehiclemanage.ui.usermanage.contract.MainContract;
import com.ttce.vehiclemanage.ui.usermanage.model.MainModel;
import com.ttce.vehiclemanage.ui.usermanage.presenter.MainPresenter;
import com.ttce.vehiclemanage.utils.AppUtils;
import com.ttce.vehiclemanage.utils.ToastUtil;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by hk on 2019/6/19.
 */

public class AboutUsActivity extends BaseActivity<MainPresenter, MainModel> implements MainContract.View, OnDownloadListener, OnButtonClickListener {

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;

    @Bind(R.id.rl_share)
    RelativeLayout rl_share;
    @Bind(R.id.rl_line)
    View rl_line;
    @Bind(R.id.tv_version)
    TextView tvVersion;
    @Override
    public int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        tvVersion.setText("V"+ AppUtils.getVersionName(this));
        titleBarTitle.setText("关于我们");
        if (!BuildConfig.CHANNEL.equals(AppConstant.HUAWEIYUN_CHANNEL)) {
            rl_share.setVisibility(View.GONE);
            rl_line.setVisibility(View.GONE);
        } else {
            rl_share.setVisibility(View.VISIBLE);
            rl_line.setVisibility(View.VISIBLE);
        }
    }


    @OnClick({R.id.title_bar_back, R.id.rl_fws, R.id.rl_yjfk, R.id.rl_cjwt, R.id.rl_share, R.id.rl_syxy, R.id.rl_yszc,R.id.rl_bbh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                finish();
                break;
            //服务商信息
            case R.id.rl_fws:
                startActivity(PlatformServiceActivity.class);
                break;
            //意见反馈
            case R.id.rl_yjfk:
                startActivity(FeedBackActivity.class);
                break;
            //问题列表
            case R.id.rl_cjwt:
                startActivity(ProblemListActivity.class);
                break;
            //APP分享
            case R.id.rl_share:
                showShare();
                break;
            //使用协议
            case R.id.rl_syxy:
                ProtocolActivity.goToPage(this,0);
                break;
            //隐私政策
            case R.id.rl_yszc:
                ProtocolActivity.goToPage(this,1);
                break;
            //当前版本号
            case R.id.rl_bbh:
                mPresenter.getAppUpdate();
                break;
        }
    }

    private void showShare() {

        OnekeyShare oks = new OnekeyShare(); //关闭sso授权 oks.disableSSOWhenAuthorize();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(mContext.getString(R.string.app_name));
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl(AppConstant.DOWNLAOD_URL);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("APP分享");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImageUrl("http://gtbds.com/images/ic_launcher.png");//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl(AppConstant.DOWNLAOD_URL);
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
        ClipData mClipData = ClipData.newRawUri("Label", Uri.parse(AppConstant.DOWNLAOD_URL));
// 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
        ToastUtil.showToast("复制成功");
    }

    @Override
    public void onButtonClick(int id) {

    }

    @Override
    public void start() {

    }

    @Override
    public void downloading(int max, int progress) {

    }

    @Override
    public void done(File apk) {

    }

    @Override
    public void cancel() {

    }

    @Override
    public void error(Exception e) {

    }
    @Override
    public void getUserInfo(UserInfoBean userInfoBean) {

    }

    @Override
    public void getChangeCompanys(List<ChangeCompanyBean> list) {

    }

    @Override
    public void getSaveChangeCompanys(ChangeCompanySaveReturnBean str) {

    }

    @Override
    public void returnAppUpdateData(AppUpdateBean appUpdateBean) {
        startUpdate3(appUpdateBean);
    }
    public DownloadManager manager;
    public void startUpdate3(AppUpdateBean appUpdateBean) {
        /*
         * 整个库允许配置的内容
         * 非必选
         */
        UpdateConfiguration configuration = new UpdateConfiguration()
                //输出错误日志
                .setEnableLog(true)
                //设置自定义的下载
                //.setHttpManager()
                //下载完成自动跳动安装页面
                .setJumpInstallPage(true)
                //设置对话框背景图片 (图片规范参照demo中的示例图)
                //.setDialogImage(R.drawable.ic_dialog)
                //设置按钮的颜色
                //.setDialogButtonColor(Color.parseColor("#E743DA"))
                //设置对话框强制更新时进度条和文字的颜色
                //.setDialogProgressBarColor(Color.parseColor("#E743DA"))
                //设置按钮的文字颜色
                .setDialogButtonTextColor(Color.WHITE)
                //设置是否显示通知栏进度
                .setShowNotification(true)
                //设置是否提示后台下载toast
                .setShowBgdToast(false)
                //设置强制更新
                .setForcedUpgrade(appUpdateBean.isIsForceUpdating()==1?true:false)
                //设置对话框按钮的点击监听
                .setButtonClickListener(this)
                //设置下载过程的监听
                .setOnDownloadListener(this);

        manager = DownloadManager.getInstance(this);
        manager.setApkName(AppUtils.getAppName(this))
                .setApkUrl(appUpdateBean.getAppVersionUrl())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setShowNewerToast(true)
                .setConfiguration(configuration)
                .setApkVersionCode(appUpdateBean.getAppVersionCode())
                .setApkVersionName(appUpdateBean.getAppVersionName())
//                .setApkSize("20.4")
                .setApkDescription(appUpdateBean.getAppVersionDescription())
//                .setApkMD5("DC501F04BBAA458C9DC33008EFED5E7F")
                .download();
    }
    @Override
    public void returnAppLogout(Boolean isLogout) {

    }

    @Override
    public void returnCloseAccountState(CloseAccountStateBean closeAccountStateBean) {

    }

    @Override
    public void logout() {

    }

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
}
