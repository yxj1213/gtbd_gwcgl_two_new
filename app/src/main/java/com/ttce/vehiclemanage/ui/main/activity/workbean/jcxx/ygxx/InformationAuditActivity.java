package com.ttce.vehiclemanage.ui.main.activity.workbean.jcxx.ygxx;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.base.BaseFragmentAdapter;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.OrderEscapeListBean;
import com.ttce.vehiclemanage.ui.main.activity.my.order.OrderSelectActivity;
import com.ttce.vehiclemanage.ui.main.fragment.workbean.jcxx.ygxx.InformationAuditFragment;
import com.ttce.vehiclemanage.widget.TabLayout.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 已修改 信息审核
 */
public class InformationAuditActivity extends BaseActivity {

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.tablayout)
    TabLayout tablayouts;
    @Bind(R.id.pager)
    ViewPager pager;
    @Bind(R.id.iv_right)
    ImageView iv_right;
    @Override
    public int getLayoutId() {
        return R.layout.activity_my_order;
    }

    @Override
    public void initPresenter() {

    }
    List<Fragment> fragmentList;
    List<String> mlist;
    List<OrderEscapeListBean> mescapeOrderStatelist;
    @Override
    public void initView() {


        int str=this.getIntent().getIntExtra("type",0);
        switch (str){
            case 0:
                fragmentList = new ArrayList<>();
                mescapeOrderStatelist=new ArrayList<>();
                mescapeOrderStatelist.add(new OrderEscapeListBean("1",-2));
                mescapeOrderStatelist.add(new OrderEscapeListBean("2",Integer.valueOf(mContext.getString(R.string.type1_1))));


                mlist = new ArrayList<>();
                mlist.add("待审核");
                mlist.add("已审核");
                titleBarTitle.setText(getResources().getString(R.string.need_car_1_1));
                break;
//            case 0:
//                fragmentList = new ArrayList<>();
//                mescapeOrderStatelist=new ArrayList<>();
//                mescapeOrderStatelist.add(new OrderEscapeListBean(mContext.getString(R.string.type2),-2));
//                mescapeOrderStatelist.add(new OrderEscapeListBean(mContext.getString(R.string.type2),Integer.valueOf(mContext.getString(R.string.type2_1))));
//                mescapeOrderStatelist.add(new OrderEscapeListBean(mContext.getString(R.string.type2),Integer.valueOf(mContext.getString(R.string.type2_2))));
//                mescapeOrderStatelist.add(new OrderEscapeListBean(mContext.getString(R.string.type2),Integer.valueOf(mContext.getString(R.string.type2_3))));
//
//                mlist = new ArrayList<>();
//                mlist.add("全部");
//                mlist.add("待审批");
//                mlist.add("已通过");
//                mlist.add("已驳回");
//                titleBarTitle.setText(getResources().getString(R.string.me_txt9));
//                break;
//            case 1:
//                fragmentList = new ArrayList<>();
//                mescapeOrderStatelist=new ArrayList<>();
//                mescapeOrderStatelist.add(new OrderEscapeListBean(mContext.getString(R.string.type3),-2));
//                mescapeOrderStatelist.add(new OrderEscapeListBean(mContext.getString(R.string.type3),Integer.valueOf(mContext.getString(R.string.type3_1))));
//                mescapeOrderStatelist.add(new OrderEscapeListBean(mContext.getString(R.string.type3),Integer.valueOf(mContext.getString(R.string.type3_2))));
//                mescapeOrderStatelist.add(new OrderEscapeListBean(mContext.getString(R.string.type3),Integer.valueOf(mContext.getString(R.string.type3_3))));
//
//                mlist = new ArrayList<>();
//                mlist.add("全部");
//                mlist.add("待派车");
//                mlist.add("已派车");
//                mlist.add("已驳回");
//                titleBarTitle.setText(getResources().getString(R.string.me_txt8));
//                break;
//            case 2:
//                fragmentList = new ArrayList<>();
//                mescapeOrderStatelist=new ArrayList<>();
//                mescapeOrderStatelist.add(new OrderEscapeListBean(mContext.getString(R.string.type4),-2));
//                mescapeOrderStatelist.add(new OrderEscapeListBean(mContext.getString(R.string.type4),Integer.valueOf(mContext.getString(R.string.type4_1))));
//                mescapeOrderStatelist.add(new OrderEscapeListBean(mContext.getString(R.string.type4),Integer.valueOf(mContext.getString(R.string.type4_2))));
//                mescapeOrderStatelist.add(new OrderEscapeListBean(mContext.getString(R.string.type4),Integer.valueOf(mContext.getString(R.string.type4_3))));
//                mescapeOrderStatelist.add(new OrderEscapeListBean(mContext.getString(R.string.type4),Integer.valueOf(mContext.getString(R.string.type4_4))));
//
//                mlist = new ArrayList<>();
//                mlist.add("全部");
//                mlist.add("待接单");
//                mlist.add("出行中");
//                mlist.add("已完成");
//                mlist.add("已取消");
//                titleBarTitle.setText(getResources().getString(R.string.me_txt13));
//                break;
        }
        for (int i = 0; i < mescapeOrderStatelist.size(); i++) {
            if(i==0){
                List<Integer> mlist=new ArrayList<>();
                fragmentList.add(InformationAuditFragment.newInstance(mescapeOrderStatelist.get(i).getType(),mlist));
            }else if(mescapeOrderStatelist.get(i).getType().equals(mContext.getString(R.string.type4))&&mescapeOrderStatelist.get(i).getmEscapeList()==Integer.valueOf(mContext.getString(R.string.type4_1))){
                List<Integer> mlist=new ArrayList<>();
                mlist.add(mescapeOrderStatelist.get(i).getmEscapeList());
                mlist.add(Integer.valueOf(mContext.getString(R.string.type4_5)));
                fragmentList.add(InformationAuditFragment.newInstance(mescapeOrderStatelist.get(i).getType(),mlist));
            }else{
                List<Integer> mlist=new ArrayList<>();
                mlist.add(mescapeOrderStatelist.get(i).getmEscapeList());
                fragmentList.add(InformationAuditFragment.newInstance(mescapeOrderStatelist.get(i).getType(),mlist));
            }
        }

        for (int i = 0; i < mlist.size(); i++) {
            tablayouts.addTab(tablayouts.newTab().setText(mlist.get(i)));
        }
        BaseFragmentAdapter baseFragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragmentList, mlist);
        pager.setAdapter(baseFragmentAdapter);
        pager.setOffscreenPageLimit(1);
        tablayouts.setupWithViewPager(pager);
        iv_right.setVisibility(View.VISIBLE);
        iv_right.setImageResource(R.mipmap.icon_order_select);
    }

    @OnClick({R.id.title_bar_back,R.id.iv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                finish();
                break;
            case R.id.iv_right:
                OrderSelectActivity.goToPage(this,"信息审核");
                break;
        }
    }

    public static void goToPage(Activity activity,int type) {
        Intent intent = new Intent(activity, InformationAuditActivity.class);
        intent.putExtra("type",type);
        activity.startActivity(intent);
    }
}