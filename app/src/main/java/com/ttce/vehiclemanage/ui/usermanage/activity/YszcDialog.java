package com.ttce.vehiclemanage.ui.usermanage.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jaydenxiao.common.base.BaseDialogFragment;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.app.AppConstant;
import com.ttce.vehiclemanage.app.SPDefaultHelper;
import com.ttce.vehiclemanage.bean.MessageEvent;
import com.ttce.vehiclemanage.ui.mine.activity.ProtocolActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YszcDialog extends BaseDialogFragment {

    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.fragments)
    LinearLayout fragments;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_yszc, container);
        ButterKnife.bind(this, view);

        String str = getResources().getString(R.string.splash_yzxy);

        SpannableString spannableString = new SpannableString(str);
        String span = "可阅读《隐私政策》和《使用协议》";
        spannableString.setSpan(clickableSpan, str.indexOf(span) + 3, str.indexOf(span) + 9, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(clickableSpan1, str.indexOf(span) + 10, str.indexOf(span) + span.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvContent.setMovementMethod(LinkMovementMethod.getInstance());
        tvContent.setText(spannableString);
        return view;
    }

    ClickableSpan clickableSpan = new ClickableSpan() {
        @Override
        public void onClick(View widget) {
//            Intent intent = new Intent();
//            intent.setAction("android.intent.action.VIEW");
//            Uri content_url = Uri.parse("https://www.gtbds.com/upload/privacy.html");
//            intent.setData(content_url);
//            startActivity(intent);
            ProtocolActivity.goToPage(getActivity(),1);
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(Color.parseColor("#2A78FE"));
            ds.setUnderlineText(false);
            ds.clearShadowLayer();
        }
    };
    ClickableSpan clickableSpan1 = new ClickableSpan() {
        @Override
        public void onClick(View widget) {
//            Intent intent = new Intent();
//            intent.setAction("android.intent.action.VIEW");
//            Uri content_url = Uri.parse("https://www.gtbds.com/upload/agreement.html");
//            intent.setData(content_url);
//            startActivity(intent);
            ProtocolActivity.goToPage(getActivity(),0);
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(Color.parseColor("#2A78FE"));
            ds.setUnderlineText(false);
            ds.clearShadowLayer();
        }
    };

    public static YszcDialog newInstance() {
        Bundle args = new Bundle();
        YszcDialog fragment = new YszcDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick({R.id.tv_exit, R.id.txt_agree})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_exit:
                dismissAllowingStateLoss();
                getActivity().finish();
                break;
            case R.id.txt_agree:
                EventBus.getDefault().post(new MessageEvent(AppConstant.MESSAGE_YSXY, "同意"));
                SPDefaultHelper.saveBoolean(SPDefaultHelper.ISFIRST,false);
                dismissAllowingStateLoss();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
