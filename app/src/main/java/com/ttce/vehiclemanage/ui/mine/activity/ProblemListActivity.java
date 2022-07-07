package com.ttce.vehiclemanage.ui.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.jaydenxiao.common.base.BaseActivity;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.ui.mine.adapter.ProblemListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 问题列表
 * Created by hk on 2019/6/24.
 */

public class ProblemListActivity extends BaseActivity implements OnItemClickListener<String> {




    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.recyclerview)
    IRecyclerView recyclerview;

    private List<String> datas = new ArrayList<>();
    private ProblemListAdapter problemListAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_problem_list;
    }

    @Override
    public void initPresenter() {

    }


    @Override
    public void initView() {
        titleBarTitle.setText("常见问题");
        datas.add("1.终端不在线常见原因");
        datas.add("2.系统平台显示数据异常常见原因");
        datas.add("3.其它");
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        problemListAdapter = new ProblemListAdapter(this, R.layout.adapter_problem_list, new ArrayList<>());
        recyclerview.setAdapter(problemListAdapter);
        problemListAdapter.setOnItemClickListener(this);
        problemListAdapter.replaceAll(datas);
    }

    @OnClick(R.id.title_bar_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, String s, int position) {
        Bundle bundle = new Bundle();
        bundle.putString("id", position + "");
        startActivity(ProblemDetailActivity.class, bundle);
    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, String s, int position) {
        return false;
    }
}
