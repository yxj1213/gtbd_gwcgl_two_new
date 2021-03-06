package com.ttce.vehiclemanage.ui.mine.activity;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.AuthTask;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.contrarywind.view.WheelView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.DisplayUtil;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.AlipayBean;
import com.ttce.vehiclemanage.bean.DaiBanListBean;
import com.ttce.vehiclemanage.bean.DepartmentByCompanyIdBean;
import com.ttce.vehiclemanage.bean.DictTypeListBean;
import com.ttce.vehiclemanage.bean.HomeOrderBean;
import com.ttce.vehiclemanage.bean.IdCareBean;
import com.ttce.vehiclemanage.bean.IsHasWorkBeanchBean;
import com.ttce.vehiclemanage.bean.JsonBean;
import com.ttce.vehiclemanage.bean.LoginBean;
import com.ttce.vehiclemanage.bean.Message2Bean;
import com.ttce.vehiclemanage.bean.MessageBean;
import com.ttce.vehiclemanage.bean.NewUserInfoBean;
import com.ttce.vehiclemanage.bean.UserInfoBean;
import com.ttce.vehiclemanage.ui.main.activity.home.CreateBusinessActivity;
import com.ttce.vehiclemanage.ui.main.fragment.MyFragment;
import com.ttce.vehiclemanage.ui.main.fragment.MyNeedCarFragment;
import com.ttce.vehiclemanage.ui.main.popwindow.MyPersonSelectBottomControlPanel;
import com.ttce.vehiclemanage.ui.mine.constract.PersonDetailConstract;
import com.ttce.vehiclemanage.ui.mine.model.PersonDetailModel;
import com.ttce.vehiclemanage.ui.mine.presenter.PersonDetailPresenter;
import com.ttce.vehiclemanage.ui.usermanage.manager.UserManager;
import com.ttce.vehiclemanage.utils.GetJsonDataUtil;
import com.ttce.vehiclemanage.utils.NoPerson_NoCreatebusinessAlertDialogUtils;
import com.ttce.vehiclemanage.utils.OtherUtil;
import com.ttce.vehiclemanage.utils.TextOrEditTextUtil;
import com.ttce.vehiclemanage.utils.ToastUtil;
import com.ttce.vehiclemanage.widget.linearlayout.ShadowLayout;
import com.ttce.vehiclemanage.wxapi.AuthResult;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
/**
 * Created by hk on 2019/6/19.
 */

public class NewPersonDetailActivity extends BaseActivity<PersonDetailPresenter, PersonDetailModel> implements PersonDetailConstract.View,MyPersonSelectBottomControlPanel.ControlPanelListener{
    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.iv_right)
    ImageView iv_right;


    @Bind(R.id.edt_xm)
    EditText edt_xm;
    @Bind(R.id.edt_zjhm)
    EditText edt_zjhm;
    @Bind(R.id.edt_lxdh)
    EditText edt_lxdh;
    @Bind(R.id.shadowLayout_sm_bottom)
    ShadowLayout shadowLayout_sm_bottom;
    @Bind(R.id.tv_sub)
    TextView tv_sub;

    @Bind(R.id.lin_bottom)
    LinearLayout lin_bottom;
    @Bind(R.id.tv_bottom)
    ShadowLayout tv_bottom;
    @Bind(R.id.lin_yuangong)
    LinearLayout lin_yuangong;
    @Bind(R.id.txt_rzrq)
    TextView txt_rzrq;
    @Bind(R.id.edt_jjlxr)
    EditText edt_jjlxr;
    @Bind(R.id.edt_jjlxr2)
    EditText edt_jjlxr2;
    @Bind(R.id.edt_jjlxdh)
    EditText edt_jjlxdh;
    @Bind(R.id.edt_jjlxdh2)
    EditText edt_jjlxdh2;
    @Bind(R.id.txt_sfcyjz)
    TextView txt_sfcyjz;
    @Bind(R.id.txt_sfcyjz2)
    TextView txt_sfcyjz2;
    @Bind(R.id.txt_xzz)
    TextView txt_xzz;
    @Bind(R.id.txt_xzz2)
    TextView txt_xzz2;
    @Bind(R.id.tv_sm_sub)
    TextView tv_sm_sub;
    @Bind(R.id.txt_yrz)
    TextView txt_yrz;


     @Bind(R.id.txt_bm)
     TextView txt_bm;
     @Bind(R.id.txt_gw)
     TextView txt_gw;
    @Bind(R.id.txt_bm2)
    TextView txt_bm2;
    @Bind(R.id.txt_gw2)
    TextView txt_gw2;
     @Bind(R.id.txt_1)
     TextView txt_1;
     @Bind(R.id.txt_ygbh2)
     EditText txt_ygbh2;
     @Bind(R.id.txt_ygbh)
     TextView txt_ygbh;
     @Bind(R.id.tv_ygbh)
     TextView tv_ygbh;
     @Bind(R.id.rel_bm)
     RelativeLayout rel_bm;
    @Bind(R.id.rel_bm2)
    RelativeLayout rel_bm2;
     @Bind(R.id.rel_qy)
     RelativeLayout rel_qy;
     @Bind(R.id.rel_gw)
     RelativeLayout rel_gw;
    @Bind(R.id.rel_rzrq)
    RelativeLayout rel_rzrq;
    @Bind(R.id.rel_gw2)
    RelativeLayout rel_gw2;
     @Bind(R.id.imgbm)
     ImageView imgbm;
     @Bind(R.id.imggw)
     ImageView imggw;
    @Bind(R.id.imgbm2)
    ImageView imgbm2;
    @Bind(R.id.imggw2)
    ImageView imggw2;
     @Bind(R.id.tv_gw)
     TextView tv_gw;
     @Bind(R.id.tv_bm)
     TextView tv_bm;
     @Bind(R.id.tv_qy)
     TextView tv_qy;
    @Bind(R.id.txt_qy2)
    TextView txt_qy2;
     @Bind(R.id.txt_qy)
     TextView txt_qy;
     @Bind(R.id.tv_rzrq)
     TextView tv_rzrq;
    @Bind(R.id.txt_rzrq2)
    TextView txt_rzrq2;
    @Bind(R.id.txt_yg_shzt)
    TextView txt_yg_shzt;
     @Bind(R.id.img6)
     ImageView img6;
    @Bind(R.id.img62)
    ImageView img62;
     @Bind(R.id.img3)
     ImageView img3;
     @Bind(R.id.img4)
     ImageView img4;
     @Bind(R.id.lin_all)
     LinearLayout lin_all;
    @Bind(R.id.tv_rz)
    TextView tv_rz;
    @Bind(R.id.lin_yuangong2)
    LinearLayout lin_yuangong2;
    @Bind(R.id.view_bm)
    View view_bm;
    @Bind(R.id.view_gw)
    View view_gw;
    @Bind(R.id.view_rzrq)
    View view_rzrq;
    @Bind(R.id.img_lxdh_show)
    ImageView img_lxdh_show;
    @Bind(R.id.img_name_show)
    ImageView img_name_show;
    @Bind(R.id.img_zjhm_show)
    ImageView img_zjhm_show;
     public static void goToPage(Activity activity, int nr,String staffid,String userid,String CompanyId) {
        Intent intent = new Intent(activity, NewPersonDetailActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("tag",nr);
        bundle.putString("staffid",staffid);
        bundle.putString("userid",userid);
        bundle.putString("CompanyId",CompanyId);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_person_detail;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }
    int tag;
    String staffid="",userid,type,DepartmentId="",PositionId="",zjhm="",smallzjhm="",sjhm="",smallsjhm="",name="",smallname="";
    @Override
    public void initView() {
        initJsonData();
        titleBarTitle.setText("????????????");

        tag=this.getIntent().getExtras().getInt("tag");
        staffid=this.getIntent().getExtras().getString("staffid");
        userid=this.getIntent().getExtras().getString("userid");

        if(tag==-1||tag==-2){//??????????????????????????????????????????????????????
            startProgressDialog();
            type="??????";
            mPresenter.getBusinessStaffPresenter(staffid);
        }else{
            type="??????";
            if(UserManager.getLoginBean().getRealCheck()==1) {//?????????
                txt_yrz.setVisibility(View.VISIBLE);
                name=UserManager.getLoginBean().getUserRealName();
                smallname=OtherUtil.setName(UserManager.getLoginBean().getUserRealName());
                TextOrEditTextUtil.editStyleBoldUtil(edt_xm,smallname);
                zjhm=UserManager.getLoginBean().getIdCard();
                smallzjhm=OtherUtil.setIdCard(UserManager.getLoginBean().getIdCard());
                TextOrEditTextUtil.editStyleBoldUtil(edt_zjhm,smallzjhm);
                sjhm=UserManager.getLoginBean().getUserPhone();
                smallsjhm=OtherUtil.setNumber(UserManager.getLoginBean().getUserPhone());
                TextOrEditTextUtil.editStyleBoldUtil(edt_lxdh,smallsjhm);
                lin_yuangong.setVisibility(View.VISIBLE);
                shadowLayout_sm_bottom.setVisibility(View.GONE);
                tv_rz.setVisibility(View.GONE);
                edt_xm.setEnabled(false);
                edt_xm.setClickable(false);
                edt_zjhm.setEnabled(false);
                edt_zjhm.setClickable(false);
                edt_lxdh.setEnabled(false);
                edt_lxdh.setClickable(false);


                if(!UserManager.getLoginBean().getCompanyId().equals("")&&!UserManager.getLoginBean().getCompanyId().equals("00000000-0000-0000-0000-000000000000"))//??????????????????????????????
                {
                    /**
                     * 0  ?????????
                     * 1 ?????????
                     * */
                    if(tag==0){
                        changeEnable(true,"??????");
                    }else if(tag==1){
                        txt_yg_shzt.setText("?????????");
                        txt_yg_shzt.setTextColor(getResources().getColor(R.color.app_main_colors));
                        mPresenter.getBusinessStaffPresenter(staffid);
                        iv_right.setVisibility(View.GONE);
                    } else{
                        txt_yg_shzt.setText("?????????");
                        txt_yg_shzt.setTextColor(getResources().getColor(R.color.needcar_txt_color1));
                        mPresenter.getBusinessStaffPresenter(staffid);
                        iv_right.setVisibility(View.VISIBLE);
                        iv_right.setImageResource(R.mipmap.icon_myperson_edit);
                    }
                }else{
                    lin_yuangong.setVisibility(View.GONE);
                }

            }else{
                img_zjhm_show.setVisibility(View.INVISIBLE);
                img_name_show.setVisibility(View.INVISIBLE);
                txt_yrz.setVisibility(View.GONE);
                sjhm=UserManager.getLoginBean().getUserPhone();
                smallsjhm=OtherUtil.setNumber(UserManager.getLoginBean().getUserPhone());

                   TextOrEditTextUtil.editStyleBoldUtil(edt_lxdh,smallsjhm);
                    lin_yuangong.setVisibility(View.GONE);
                    shadowLayout_sm_bottom.setVisibility(View.VISIBLE);
                    tv_rz.setVisibility(View.VISIBLE);
                    edt_xm.setEnabled(true);
                    edt_xm.setClickable(true);
                    edt_zjhm.setEnabled(true);
                    edt_zjhm.setClickable(true);
                    edt_lxdh.setEnabled(false);
                    edt_lxdh.setClickable(false);
//                    shadowLayout_sm_bottom.setAlpha(0.4f);//????????????????????????????????????40%????????????


                TextOrEditTextUtil.editStyleBoldUtil(edt_xm);
                shadowLayout_sm_bottom.bgColor=(getResources().getColor(R.color.color_93));
                tv_sm_sub.setClickable(false);
                tv_sm_sub.setEnabled(false);
                // ??????????????????????????????????????????????????????
                edt_zjhm.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void afterTextChanged(Editable arg0) {
                        edt_zjhm.getPaint().setFakeBoldText(true);
                    }

                    @Override
                    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

                    }

                    @Override
                    public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                        if (cs.length() <= 0) {
                            return;
                        }
                        if (cs.length() == 18) {

//                            shadowLayout_sm_bottom.setAlpha(1f);//????????????????????????????????????40%????????????
                            shadowLayout_sm_bottom.bgColor=getResources().getColor(R.color.app_main_colors);
                            tv_sm_sub.setClickable(true);
                            tv_sm_sub.setEnabled(true);
                        } else {
//                            shadowLayout_sm_bottom.setAlpha(0.4f);//????????????????????????????????????40%????????????
                            shadowLayout_sm_bottom.bgColor=(getResources().getColor(R.color.color_93));
                            tv_sm_sub.setClickable(false);
                            tv_sm_sub.setEnabled(false);
                        }
                    }
                });
            }
        }
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

    @Override
    public void getUserInfo(UserInfoBean userInfoBean) {
//        tvZh.setText(userInfoBean.getUserName());
//        tvQymc.setText(userInfoBean.getCompanyName());
//        tvName.setText(userInfoBean.getUserRealName());
//        tvSex.setText(userInfoBean.getSex());
//        tvBirthday.setText(userInfoBean.getBirthDay());
//        tvTelphone.setText(userInfoBean.getUserPhone());
//        tvEmail.setText(userInfoBean.getUserEmail());
    }

    @Override
    public void getImgUrl(String imgUrl) {

    }

    @Override
    public void returnIsHasPrivilege(IsHasWorkBeanchBean isHasWorkBeanchBean, int position, String type) {

    }

    @Override
    public void returnStaffMagState(Message2Bean MessageBean) {

    }

    NewUserInfoBean userInfoBean;
    @Override
    public void returnBusinessStaff(NewUserInfoBean muserInfoBean) {
        PositionId=muserInfoBean.getPositionId();
        DepartmentId=muserInfoBean.getDeaprtmentId();
        this.userInfoBean=muserInfoBean;
        if(tag==-1){
            changeEnable(true,"?????????");
        }else{
            changeEnable(false,"?????????");
        }
    }

    @Override
    public void returnBusinessStaffAdd(String str) {
        stopProgressDialog();
        ToastUtil.showToast("????????????");
        txt_yg_shzt.setText("?????????");
        txt_yg_shzt.setTextColor(getResources().getColor(R.color.app_main_colors));
        if(tag==-1){//??????????????????????????????????????????????????????
            EventBus.getDefault().postSticky("????????????");
        }else{
            LoginBean loginBean= UserManager.getLoginBean();
            loginBean.setStaffId(str);
            UserManager.saveSerialize(new LoginBean(loginBean.getRealCheck(),loginBean.getToken(),loginBean.getUserPhone(),loginBean.getCompanyId(),loginBean.getCompanyName(),loginBean.getDepartMentId(),loginBean.getUserId(),str,loginBean.getUserRealName(),loginBean.getIdCard()));
        }
        finish();
    }

    @Override
    public void returnPostDictTypeList(List<DictTypeListBean> str) {
        if(str!=null&&str.size()>0){
            setMapBottom("????????????",new Gson().toJson(str));
        }else{
            ToastUtil.showToast("????????????");
        }
    }

    @Override
    public void returnPostDepartmentByCompanyId(List<DepartmentByCompanyIdBean> str) {
        if(str!=null&&str.size()>0){
            setMapBottom("????????????",new Gson().toJson(str));
        }else{
            ToastUtil.showToast("????????????");
        }
    }

    @Override
    public void returnCarFlowOrderNeedToDo(List<DaiBanListBean> str) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            MyNeedCarFragment.isRef=true;
        }
        return super.onKeyDown(keyCode, event);
    }

    boolean isShowIdCard=false;
    boolean isShowName=false;
    boolean isShowNumber=false;
    @OnClick({R.id.img_name_show,R.id.tv_yg_qx,R.id.img_lxdh_show,R.id.img_zjhm_show,R.id.tv_sm_sub,R.id.tv_sub,R.id.rel_bm2,R.id.rel_gw2,R.id.title_bar_back,R.id.iv_right,R.id.tv_yg_sub,R.id.txt_rzrq2,R.id.txt_sfcyjz,R.id.txt_xzz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_yg_qx:
                changeEnable(false,"?????????");
                break;
            case R.id.img_name_show:
                isShowName=!isShowName;
                if(isShowName==true){
                    img_name_show.setImageResource(R.mipmap.icon_look);
                    TextOrEditTextUtil.editStyleBoldUtil(edt_xm,name);
                }else{
                    img_name_show.setImageResource(R.mipmap.icon_no_look);
                    TextOrEditTextUtil.editStyleBoldUtil(edt_xm,smallname);
                }
                break;
            case R.id.img_lxdh_show:
                isShowNumber=!isShowNumber;
                if(isShowNumber==true){
                    img_lxdh_show.setImageResource(R.mipmap.icon_look);
                    TextOrEditTextUtil.editStyleBoldUtil(edt_lxdh,sjhm);
                }else{
                    img_lxdh_show.setImageResource(R.mipmap.icon_no_look);
                    TextOrEditTextUtil.editStyleBoldUtil(edt_lxdh,smallsjhm);
                }
                break;
            case R.id.img_zjhm_show:
                if(edt_zjhm.getText().toString().trim().length()==18){
                    isShowIdCard=!isShowIdCard;
                    if(isShowIdCard==true){
                        img_zjhm_show.setImageResource(R.mipmap.icon_look);
                        TextOrEditTextUtil.editStyleBoldUtil(edt_zjhm,zjhm);
                    }else{
                        img_zjhm_show.setImageResource(R.mipmap.icon_no_look);
                        TextOrEditTextUtil.editStyleBoldUtil(edt_zjhm,smallzjhm);
                    }
                }else{
                    ToastUtil.showToast("?????????????????????????????????");
                }
                break;
            case R.id.rel_bm2:
                mPresenter.PostDepartmentByCompanyIdPresenter();
                break;
            case R.id.rel_gw2:
                mPresenter.PostDictTypeListPresenter();
                break;
            case R.id.title_bar_back:
                MyNeedCarFragment.isRef=true;
                MyFragment.IsRef=true;
                finish();
                break;
            case R.id.iv_right:
                lin_bottom.setVisibility(View.VISIBLE);
                changeEnable(true,"??????");
                break;
            case R.id.tv_sub:
                if(tag==-1){//??????????????????????????????????????????????????????
                    if(txt_bm2.getText().toString().trim().equals("")){
                        ToastUtil.showToast("??????????????????");
                        return;
                    }
                    if(txt_gw2.getText().toString().trim().equals("")){
                        ToastUtil.showToast("??????????????????");
                        return;
                    }
//                    if(txt_rzrq2.getText().toString().trim().equals("")){
//                        ToastUtil.showToast("????????????????????????");
//                        return;
//                    }

                }
                mPresenter.BusinessStaffAddPresenter(type, staffid, PositionId,DepartmentId,userid,"","0",txt_rzrq2.getText().toString().trim(),edt_jjlxr.getText().toString().trim(),edt_jjlxdh.getText().toString().trim(),txt_sfcyjz.getText().toString().trim(),txt_xzz.getText().toString().trim(),txt_ygbh2.getText().toString().trim());

                break;
            case R.id.tv_sm_sub:
                startProgressDialog();
                JsonObject jsonObject=new JsonObject();
                jsonObject.addProperty("user_name",edt_xm.getText().toString().trim());
                jsonObject.addProperty("cert_type","IDENTITY_CARD");
                jsonObject.addProperty("cert_no",edt_zjhm.getText().toString().trim());
                jsonObject.addProperty("mobile","");
                mPresenter.PostPreconsultPresenter(jsonObject);
                break;
            case R.id.tv_yg_sub:
                startProgressDialog();
                    mPresenter.BusinessStaffAddPresenter(type, staffid, PositionId,DepartmentId,userid,"","0",txt_rzrq.getText().toString().trim(),edt_jjlxr.getText().toString().trim(),edt_jjlxdh.getText().toString().trim(),txt_sfcyjz.getText().toString().trim(),txt_xzz.getText().toString().trim(),"");
                break;
            case R.id.txt_rzrq2://????????????
                setTimeSelect("????????????",txt_rzrq2);
                break;
            case R.id.txt_sfcyjz://???????????????
                setMapBottom("???????????????","");
                break;
            case R.id.txt_xzz://?????????
                //???????????????
                OptionsPickerView pvOptions = new OptionsPickerBuilder(NewPersonDetailActivity.this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                        //?????????????????????????????????????????????
                        String tx = options1Items.get(options1).getPickerViewText()
                                + options2Items.get(options1).get(option2)
                                + options3Items.get(options1).get(option2).get(options3);
                        txt_xzz.setText(tx);
                    }
                }).build();
                pvOptions.setPicker(options1Items, options2Items, options3Items);
                pvOptions.show();
                break;
        }
    }

    private List<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private void initJsonData() {//????????????

        /**
         * ?????????assets ????????????Json??????????????????????????????????????????????????????
         * ???????????????????????????
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//??????assets????????????json????????????

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//???Gson ????????????

        /**
         * ??????????????????
         *
         * ???????????????????????????JavaBean????????????????????????????????? IPickerViewData ?????????
         * PickerView?????????getPickerViewText????????????????????????????????????
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//????????????
            ArrayList<String> cityList = new ArrayList<>();//????????????????????????????????????
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//??????????????????????????????????????????

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//??????????????????????????????
                String cityName = jsonBean.get(i).getCityList().get(c).getName();
                cityList.add(cityName);//????????????
                ArrayList<String> city_AreaList = new ArrayList<>();//??????????????????????????????

                //??????????????????????????????????????????????????????????????????null ?????????????????????????????????????????????
                /*if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    city_AreaList.add("");
                } else {
                    city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }*/
                city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                province_AreaList.add(city_AreaList);//??????????????????????????????
            }

            /**
             * ??????????????????
             */
            options2Items.add(cityList);

            /**
             * ??????????????????
             */
            options3Items.add(province_AreaList);
        }
    }


    public ArrayList<JsonBean> parseData(String result) {//Gson ??????
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

    MyPersonSelectBottomControlPanel bottomControlPanel;
    public void setMapBottom(String type,String str) {
        MyPersonSelectBottomControlPanel.mtype=type;
        if(bottomControlPanel!=null){
            bottomControlPanel.dissmiss();
        }
        bottomControlPanel = MyPersonSelectBottomControlPanel.newInstance(NewPersonDetailActivity.this,this,str);
        bottomControlPanel.show(lin_all);
    }


    TimePickerView pvTime = null;
    private void setTimeSelect(String title, TextView textview) {
        //???????????????
        pvTime = new TimePickerBuilder(NewPersonDetailActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
                textview.setText(format2.format(date));
            }
        })
                .setLayoutRes(R.layout.dialog_time_select, new CustomListener() {
                    @Override
                    public void customLayout(View v) {

                        LinearLayout lin = (LinearLayout) v.findViewById(R.id.dialog_date_lin);
                        TextView tvTitle = (TextView) v.findViewById(R.id.tv_title);
                        TextView tvSure = (TextView) v.findViewById(R.id.tv_sure);
                        tvTitle.setText(title);
                        tvSure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                pvTime.returnData();
                                pvTime.dismiss();
                            }
                        });
                        //??????????????????????????????
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) lin.getLayoutParams();
                        int width = NewPersonDetailActivity.this.getResources().getDisplayMetrics().widthPixels;
                        int height = NewPersonDetailActivity.this.getResources().getDisplayMetrics().heightPixels;
                        params.setMargins(20, 0, 20, 0);
                        //???????????????
                        params.width = width;
                        //???????????????
                        params.height = height / 3;
                        //??????????????????????????????????????????
                        lin.setLayoutParams(params);
                    }
                })
                .setOutSideCancelable(false)//???????????????????????????????????????????????????????????????
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")//?????????????????????????????????
                .setDividerColor(Color.GRAY)
                .setDividerType(WheelView.DividerType.WRAP)
                .setTextXOffset(0, 0, 0, 0, 0, 0)//??????X???????????????[ -90 , 90??]
                .isDialog(true)//??????????????????????????????
                .setLineSpacingMultiplier(2.0f)//????????????????????????????????????
                .setItemVisibleCount(3)
                .setContentTextSize(18)
                .build();
        pvTime.show();
    }

    public void changeEnable(boolean isEnable,String type){

        if(type.equals("?????????")||type.equals("?????????")){//???????????????
            stopProgressDialog();
            shadowLayout_sm_bottom.setVisibility(View.GONE);
            tv_rz.setVisibility(View.GONE);
            edt_xm.setEnabled(false);
            edt_xm.setClickable(false);
            edt_zjhm.setEnabled(false);
            edt_zjhm.setClickable(false);
            edt_lxdh.setEnabled(false);
            edt_lxdh.setClickable(false);
            if(type.equals("?????????")){
                lin_yuangong.setVisibility(View.GONE);
                lin_yuangong2.setVisibility(View.VISIBLE);
                tv_bottom.setVisibility(View.VISIBLE);
                tv_sub.setText("??????");
                rel_bm2.setEnabled(true);
                rel_gw2.setClickable(true);
                txt_rzrq2.setClickable(true);
            }else{
                edt_jjlxr2.setTextColor(getResources().getColor(R.color.work_item_txt_color));
                edt_jjlxdh2.setTextColor(getResources().getColor(R.color.work_item_txt_color));
                txt_sfcyjz2.setTextColor(getResources().getColor(R.color.work_item_txt_color));//1?????????????????????????????? 0??????????????????????????????
                txt_xzz2.setTextColor(getResources().getColor(R.color.work_item_txt_color));
                txt_qy2.setTextColor(getResources().getColor(R.color.work_item_txt_color));
                tv_bottom.setVisibility(View.GONE);
                if(tag!=-1&&tag!=-2){//?????????????????????
                    lin_yuangong.setVisibility(View.VISIBLE);
                    lin_bottom.setVisibility(View.GONE);
                    lin_yuangong2.setVisibility(View.GONE);
                    rel_bm.setEnabled(false);
                    rel_gw.setClickable(false);
                    txt_rzrq.setClickable(false);
                    imgbm.setVisibility(View.GONE);
                    imggw.setVisibility(View.GONE);
                    img6.setVisibility(View.GONE);

                    img3.setVisibility(View.GONE);
                    img4.setVisibility(View.GONE);
                    txt_sfcyjz.setEnabled(false);
                    txt_sfcyjz.setClickable(false);
                    txt_xzz.setEnabled(false);
                    txt_xzz.setClickable(false);
                    edt_jjlxdh.setEnabled(false);
                    edt_jjlxdh.setClickable(false);
                    edt_jjlxr.setEnabled(false);
                    edt_jjlxr.setClickable(false);
                }else{
                    lin_yuangong.setVisibility(View.GONE);
                    lin_yuangong2.setVisibility(View.VISIBLE);
                    rel_bm2.setEnabled(false);
                    rel_gw2.setClickable(false);
                    txt_rzrq2.setClickable(false);
                    txt_ygbh2.setClickable(false);
                    txt_ygbh2.setEnabled(false);
                    imgbm2.setVisibility(View.GONE);
                    imggw2.setVisibility(View.GONE);
                    img62.setVisibility(View.GONE);
                }
            }
        }else{//??????????????????
            if(UserManager.getLoginBean().getRealCheck()==1){//?????????
                if(type.equals("??????")){
                    txt_sfcyjz.setText("???");//1?????????????????????????????? 0??????????????????????????????
                    TextOrEditTextUtil.textStyleBoldUtil(txt_qy,UserManager.getLoginBean().getCompanyName());
                    rel_bm.setVisibility(View.GONE);
                    rel_gw.setVisibility(View.GONE);
//                    rel_rzrq.setVisibility(View.GONE);
                    view_bm.setVisibility(View.GONE);
                    view_gw.setVisibility(View.GONE);
                    view_rzrq.setVisibility(View.GONE);
                    zjhm=UserManager.getLoginBean().getIdCard();
                    smallzjhm=OtherUtil.setIdCard(UserManager.getLoginBean().getIdCard());
                    TextOrEditTextUtil.editStyleBoldUtil(edt_zjhm,smallzjhm);
                    isShowIdCard=false;
                    img_zjhm_show.setImageResource(R.mipmap.icon_no_look);
                    img_zjhm_show.setVisibility(View.VISIBLE);

                    name=UserManager.getLoginBean().getUserRealName();
                    smallname=OtherUtil.setName(UserManager.getLoginBean().getUserRealName());
                    TextOrEditTextUtil.editStyleBoldUtil(edt_xm,smallname);
                    isShowName=false;
                    img_name_show.setImageResource(R.mipmap.icon_no_look);
                    img_name_show.setVisibility(View.VISIBLE);
                }
            }

            edt_jjlxr.setEnabled(isEnable);
            edt_jjlxdh.setEnabled(isEnable);
            txt_sfcyjz.setEnabled(isEnable);
            txt_sfcyjz.setClickable(isEnable);
            txt_xzz.setEnabled(isEnable);
            txt_xzz.setClickable(isEnable);
            if(isEnable==false){
                img3.setVisibility(View.GONE);
                img4.setVisibility(View.GONE);
            }else{
                img3.setVisibility(View.VISIBLE);
                img4.setVisibility(View.VISIBLE);
                rel_bm.setEnabled(false);
                rel_gw.setClickable(false);
                txt_rzrq.setClickable(false);
                imgbm.setVisibility(View.GONE);
                imggw.setVisibility(View.GONE);
                img6.setVisibility(View.GONE);
            }
        }
        if (type.equals("??????")) {
            txt_bm.setTextColor(getResources().getColor(R.color.hint_txt_color));
            txt_gw.setTextColor(getResources().getColor(R.color.hint_txt_color));
            txt_1.setTextColor(getResources().getColor(R.color.hint_txt_color));
//            tv_bm.setTextColor(getResources().getColor(R.color.hint_txt_color));
//            tv_gw.setTextColor(getResources().getColor(R.color.hint_txt_color));
//            tv_qy.setTextColor(getResources().getColor(R.color.hint_txt_color));
            txt_qy.setTextColor(getResources().getColor(R.color.hint_txt_color));
//            tv_rzrq.setTextColor(getResources().getColor(R.color.hint_txt_color));
            txt_rzrq.setTextColor(getResources().getColor(R.color.hint_txt_color));
//            tv_ygbh.setTextColor(getResources().getColor(R.color.hint_txt_color));
            txt_ygbh.setTextColor(getResources().getColor(R.color.hint_txt_color));
        }else{
            txt_bm.setTextColor(getResources().getColor(R.color.work_item_txt_color));
            txt_gw.setTextColor(getResources().getColor(R.color.work_item_txt_color));
            txt_1.setTextColor(getResources().getColor(R.color.work_item_txt_color));
//            tv_bm.setTextColor(getResources().getColor(R.color.work_item_txt_color));
//            tv_gw.setTextColor(getResources().getColor(R.color.work_item_txt_color));
//            tv_qy.setTextColor(getResources().getColor(R.color.work_item_txt_color));
            txt_qy.setTextColor(getResources().getColor(R.color.work_item_txt_color));
//            tv_rzrq.setTextColor(getResources().getColor(R.color.work_item_txt_color));
            txt_rzrq.setTextColor(getResources().getColor(R.color.work_item_txt_color));
//            tv_ygbh.setTextColor(getResources().getColor(R.color.work_item_txt_color));
            txt_ygbh.setTextColor(getResources().getColor(R.color.work_item_txt_color));
        }
        if(userInfoBean!=null) {
            //????????????
            edt_jjlxr.setText(userInfoBean.getEmergencycontactName());
            edt_jjlxdh.setText(userInfoBean.getEmergencycontactPhone());

            if(userInfoBean.getIsDriver() == 1){//1?????????????????????????????? 0??????????????????????????????
                TextOrEditTextUtil.textStyleBoldUtil(txt_sfcyjz,"???");
            }else{
                TextOrEditTextUtil.textStyleBoldUtil(txt_sfcyjz,"???");
            }
            txt_xzz.setText(userInfoBean.getXianZhu());
            TextOrEditTextUtil.textStyleBoldUtil(txt_bm,userInfoBean.getDeaprtmentName());
            TextOrEditTextUtil.textStyleBoldUtil(txt_gw,userInfoBean.getPositionName());
            TextOrEditTextUtil.textStyleBoldUtil(txt_qy,userInfoBean.getCompanyName());
            txt_rzrq.setText(userInfoBean.getOfficeDateFormat());
            if(!userInfoBean.getDeaprtmentName().equals("")){
                rel_bm.setVisibility(View.VISIBLE);
                view_bm.setVisibility(View.VISIBLE);
            }else{
                rel_bm.setVisibility(View.GONE);
                view_bm.setVisibility(View.GONE);
            }

            if(!userInfoBean.getPositionName().equals("")){
                rel_gw.setVisibility(View.VISIBLE);
                view_gw.setVisibility(View.VISIBLE);
            }else{
                rel_gw.setVisibility(View.GONE);
                view_gw.setVisibility(View.GONE);
            }


            if(userInfoBean.getCode().equals("")){//1?????????????????????????????? 0??????????????????????????????
                TextOrEditTextUtil.textStyleBoldUtil(txt_ygbh,"--");
            }else{
                TextOrEditTextUtil.textStyleBoldUtil(txt_ygbh,userInfoBean.getCode());
            }
//            if(!userInfoBean.getOfficeDateFormat().equals("")){
//                rel_rzrq.setVisibility(View.VISIBLE);
//                view_rzrq.setVisibility(View.VISIBLE);
//            }else{
//                rel_rzrq.setVisibility(View.GONE);
//                view_rzrq.setVisibility(View.GONE);
//            }

            //??????????????????
            edt_jjlxr2.setText(userInfoBean.getEmergencycontactName());
            edt_jjlxdh2.setText(userInfoBean.getEmergencycontactPhone());
            txt_xzz2.setText(userInfoBean.getXianZhu());

            if(userInfoBean.getIsDriver() == 1){//1?????????????????????????????? 0??????????????????????????????
                TextOrEditTextUtil.textStyleBoldUtil(txt_sfcyjz2,"???");
            }else{
                TextOrEditTextUtil.textStyleBoldUtil(txt_sfcyjz2,"???");
            }
            TextOrEditTextUtil.textStyleBoldUtil(txt_qy2,userInfoBean.getCompanyName());
            if(!userInfoBean.getDeaprtmentName().equals("")){
                TextOrEditTextUtil.textStyleBoldUtil(txt_bm2,userInfoBean.getDeaprtmentName());
            }
            if(!userInfoBean.getPositionName().equals("")){
                TextOrEditTextUtil.textStyleBoldUtil(txt_gw2,userInfoBean.getPositionName());
            }
            txt_rzrq2.setText(userInfoBean.getOfficeDateFormat());
            if(type.equals("?????????")){
                if(!userInfoBean.getCode().equals("")){
                    TextOrEditTextUtil.editStyleBoldUtil(txt_ygbh2,userInfoBean.getCode());
                }else{
                    TextOrEditTextUtil.editStyleBoldUtil(txt_ygbh2);
                }
            }else if(type.equals("?????????")){
                if(userInfoBean.getCode().equals("")){
                    TextOrEditTextUtil.editStyleBoldUtil(txt_ygbh2,"--");
                }else{
                    TextOrEditTextUtil.editStyleBoldUtil(txt_ygbh2,userInfoBean.getCode());
                }
            }
            //????????????
            name=userInfoBean.getUserRealName();
            smallname=OtherUtil.setName(userInfoBean.getUserRealName());

            zjhm=userInfoBean.getIdCard();
            smallzjhm=OtherUtil.setIdCard(userInfoBean.getIdCard());


            sjhm=userInfoBean.getUserPhone();
            smallsjhm=OtherUtil.setNumber(userInfoBean.getUserPhone());
            if(tag==-1||tag==-2){
                TextOrEditTextUtil.editStyleBoldUtil(edt_xm,name);
                TextOrEditTextUtil.editStyleBoldUtil(edt_zjhm,smallzjhm);
                TextOrEditTextUtil.editStyleBoldUtil(edt_lxdh,sjhm);
                img_name_show.setVisibility(View.GONE);
                img_zjhm_show.setVisibility(View.GONE);
                img_lxdh_show.setVisibility(View.GONE);
                edt_xm.setPadding(0,0, DisplayUtil.dip2px(6),0);
                edt_zjhm.setPadding(0,0, DisplayUtil.dip2px(6),0);
                edt_lxdh.setPadding(0,0, DisplayUtil.dip2px(6),0);
            }else{
                TextOrEditTextUtil.editStyleBoldUtil(edt_xm,smallname);
                isShowName=false;
                img_name_show.setVisibility(View.VISIBLE);
                img_name_show.setImageResource(R.mipmap.icon_no_look);

                TextOrEditTextUtil.editStyleBoldUtil(edt_zjhm,smallzjhm);
                isShowIdCard=false;
                img_zjhm_show.setVisibility(View.VISIBLE);
                img_zjhm_show.setImageResource(R.mipmap.icon_no_look);

                img_lxdh_show.setVisibility(View.VISIBLE);
                TextOrEditTextUtil.editStyleBoldUtil(edt_lxdh,smallsjhm);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onwc(String neir1, String mtype) {
        switch (mtype){
            case "???????????????":
                txt_sfcyjz.setText(neir1);
                break;
        }
    }

    @Override
    public void onwc(MessageBean messageBean, String mtype) {
        if(mtype.equals("????????????")){
            TextOrEditTextUtil.textStyleBoldUtil(txt_gw2,messageBean.getText()+"");
            PositionId=messageBean.getValue();
        }else  if(mtype.equals("????????????")){
           TextOrEditTextUtil.textStyleBoldUtil(txt_bm2,messageBean.getText()+"");
            DepartmentId=messageBean.getValue();
        }
    }
    @Override
    public void returnPostPreconsult(AlipayBean str) {
        stopProgressDialog();
        if(str.getAlipay_User_Certdoc_Certverify_Preconsult_Response().getCode().equals("10000")){
            verify_id=str.getAlipay_User_Certdoc_Certverify_Preconsult_Response().getVerify_id();
            getAuthCode(str.getAuth_url());
        }else{
            ToastUtil.showToast(str.getAlipay_User_Certdoc_Certverify_Preconsult_Response().getSub_msg());
        }

    }

    @Override
    public void returnPostConsult(IdCareBean str) {
           shadowLayout_sm_bottom.setVisibility(View.GONE);
            tv_rz.setVisibility(View.GONE);
            txt_yrz.setVisibility(View.VISIBLE);
            LoginBean loginBean= UserManager.getLoginBean();
            loginBean.setRealCheck(1);
            loginBean.setUserRealName(edt_xm.getText().toString().trim());
             loginBean.setIdCard(str.getIdCard());
            UserManager.saveSerialize(new LoginBean(1,loginBean.getToken(),loginBean.getUserPhone(),loginBean.getCompanyId(),loginBean.getCompanyName(),loginBean.getDepartMentId(),loginBean.getUserId(),loginBean.getStaffId(),edt_xm.getText().toString().trim(),str.getIdCard()));
            if(!UserManager.getLoginBean().getCompanyId().equals("")&&!UserManager.getLoginBean().getCompanyId().equals("00000000-0000-0000-0000-000000000000"))//??????????????????????????????
            {
                lin_yuangong.setVisibility(View.VISIBLE);
                changeEnable(true,"??????");
            }else{
                lin_yuangong.setVisibility(View.GONE);
                zjhm=str.getIdCard();
                isShowIdCard=false;
                smallzjhm=OtherUtil.setIdCard(str.getIdCard());
                TextOrEditTextUtil.editStyleBoldUtil(edt_zjhm,smallzjhm);
                img_zjhm_show.setImageResource(R.mipmap.icon_no_look);
                img_zjhm_show.setVisibility(View.VISIBLE);


                name=UserManager.getLoginBean().getUserRealName();
                smallname=OtherUtil.setName(UserManager.getLoginBean().getUserRealName());
                TextOrEditTextUtil.editStyleBoldUtil(edt_xm,smallname);
                isShowName=false;
                img_name_show.setImageResource(R.mipmap.icon_no_look);
                img_name_show.setVisibility(View.VISIBLE);
            }
            edt_xm.setEnabled(false);
            edt_xm.setClickable(false);
            edt_zjhm.setEnabled(false);
            edt_zjhm.setClickable(false);
            edt_lxdh.setEnabled(false);
            edt_lxdh.setClickable(false);
    }

    @Override
    public void returnErrPostConsult(String str) {
        ToastUtil.showToast(str);
    }

    @Override
    public void returnCarFlowOrder_Index_Statistics(HomeOrderBean homeOrderBean) {

    }


    String verify_id="";
    public void getAuthCode(String authInfo){
        // authInfo ????????????????????? ?????????????????? ??????????????????????????? SDK Demo ???????????????
        // authInfo ???????????????????????????????????????????????????????????????????????????
        // ?????????????????????????????????????????????
        Runnable authRunnable = new Runnable() {
            @Override
            public void run() {
                // ??????AuthTask ??????
                AuthTask authTask = new AuthTask(NewPersonDetailActivity.this);
                // ??????????????????
                // AuthTask#authV2(String info, boolean isShowLoading)???
                // ?????????????????????
                Map<String, String> result = authTask.authV2(authInfo, true);
                // ?????????????????? Message ?????????????????? App ????????????????????????
                // ??????????????????????????????????????????????????? SDK Demo ???????????????
                Message msg = new Message();
                msg.what = SDK_AUTH_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread authThread = new Thread(authRunnable);
        authThread.start();
    }
    private static final int SDK_AUTH_FLAG = 2;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // ??????resultStatus ??????9000??????result_code
                    // ??????200?????????????????????????????????????????????????????????????????????????????????
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // ??????alipay_open_id???????????????????????????extern_token ???value
                        // ??????????????????????????????????????????
                        if(verify_id!=null&&!verify_id.equals("")){

                            JsonObject jsonObject=new JsonObject();
                            jsonObject.addProperty("UserRealName",edt_xm.getText().toString().trim());
                            jsonObject.addProperty("IdCard",edt_zjhm.getText().toString().trim());

                            mPresenter.PostConsultPresenter(verify_id,authResult.getAuthCode(),jsonObject);
                        }else{
                            ToastUtil.showToast("??????verify_id?????????");
                        }
                    } else {
                        // ?????????????????????????????????
//                        showAlert(NewPersonDetailActivity.this, getString(R.string.auth_failed) + authResult);
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };
}
