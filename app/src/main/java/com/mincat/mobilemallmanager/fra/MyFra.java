package com.mincat.mobilemallmanager.fra;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.mincat.mobilemallmanager.R;
import com.mincat.sample.custom.ClassicsHeader;
import com.mincat.sample.manager.fra.MinCatFragment;
import com.mincat.sample.utils.PublicRefreshSetting;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * @author Gin
 * @描述 设置
 */
public class MyFra extends MinCatFragment implements SwipeRefreshLayout.OnRefreshListener {
    RefreshLayout refreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fra_setting, null);


        initView(view);

        return view;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void initView(View view) {

        refreshLayout = getId(R.id.refreshLayout, view);

        PublicRefreshSetting.setRefreshAttribute(getActivity(), refreshLayout, false, 80);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        refreshLayout.finishRefresh();
                    }
                }, 3000);
            }
        });

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public String assembleRequestParam() {
        return null;
    }

    @Override
    public void onHandleResponsePost(String response, String sign) {

    }

    @Override
    public void onHandleResponseGet(String response, String sign) {

    }

    @Override
    public void errorListener(VolleyError error, String sign) {

    }
}
