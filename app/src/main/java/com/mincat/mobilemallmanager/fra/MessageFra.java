package com.mincat.mobilemallmanager.fra;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.VolleyError;
import com.mincat.mobilemallmanager.R;
import com.mincat.mobilemallmanager.ui.TestGetVerifyCodeAct;
import com.mincat.sample.manager.fra.MinCatFragment;

/**
 * @author Gin
 * @描述 功能
 */
public class MessageFra extends MinCatFragment implements SwipeRefreshLayout.OnRefreshListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fra_im, null);

        initView(view);

        return view;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void initView(View view) {


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

        }

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
