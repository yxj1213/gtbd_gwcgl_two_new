package com.ttce.vehiclemanage.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.LoginImageVerificationBean;
import com.ttce.vehiclemanage.ui.usermanage.activity.LoginActivity;
import com.ttce.vehiclemanage.utils.ImageUtil;


/**
 * Date:2020/5/19
 * author:wuyan
 */
public class BlockPuzzleDialog extends Dialog {
    private String baseImageBase64;//背景图片
    private String slideImageBase64;//滑动图片
    private String token;
    private Context mContext;
    private TextView tvDelete;
    private ImageView tvRefresh;
    private DragImageView dragView;
    private Handler handler = new Handler();
    private String key;


    public BlockPuzzleDialog(@NonNull Context context) {
        super(context, R.style.dialog);
        this.mContext = context;
        setContentView(R.layout.dialog_block_puzzle);
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        WindowManager windowManager = ((Activity) mContext).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        ViewGroup.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth() * 9 / 10;
        getWindow().setAttributes((WindowManager.LayoutParams) lp);
        setCanceledOnTouchOutside(false);//点击外部Dialog不消失
        LoginActivity.mcontext.mPresenter.getImgLoginVaild();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tvRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.mcontext.mPresenter.getImgLoginVaild();
            }
        });
    }

    private void initView() {
        tvDelete = findViewById(R.id.tv_delete);
        tvRefresh = findViewById(R.id.tv_refresh);
        dragView = findViewById(R.id.dragView);

        Bitmap bitmap = ImageUtil.getBitmap(getContext(), R.mipmap.bg_default);
        dragView.setUp(bitmap, bitmap,"0");
        dragView.setSBUnMove(false);
    }

    private void initEvent() {
        dragView.setDragListenner(new DragImageView.DragListenner() {
            @Override
            public void onDrag(double position) {
                LoginActivity.mcontext.mPresenter.getCheckImgLoginVaild(loginImageVerificationBean.getVerificationId(),position);
            }
        });
    }
    LoginImageVerificationBean loginImageVerificationBean;
    //获取图片验证码
    public void getimgVerification(LoginImageVerificationBean loginImageVerificationBean) {
        this.loginImageVerificationBean=loginImageVerificationBean;
        String[] min=loginImageVerificationBean.getVerificationminImgBase64url().split(",");
        dragView.setUp(ImageUtil.base64ToBitmap(loginImageVerificationBean.getVerificationmaxImgBase64url()),
                ImageUtil.base64ToBitmap(min[1]),min[0]);
        dragView.setSBUnMove(true);
        initEvent();
    }
    //验证图片验证码是否正确
    public void checkimgVerification(String tag) {
        if(tag.equals("成功")){
            dragView.ok();
            LoginActivity.mcontext.mPresenter.getImgLoginVaild();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dismiss();
                }
            }, 1500);
        }else{
            dragView.fail();
            //刷新验证码
            LoginActivity.mcontext.mPresenter.getImgLoginVaild();
        }
    }
}
