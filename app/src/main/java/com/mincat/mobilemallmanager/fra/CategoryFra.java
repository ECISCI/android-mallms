package com.mincat.mobilemallmanager.fra;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.mincat.mobilemallmanager.R;
import com.mincat.sample.utils.LogUtils;

/**
 * @author Gin
 * @描述 功能
 */
public class CategoryFra extends MinCatFragment implements SwipeRefreshLayout.OnRefreshListener {


    private TextView mTvFlag;

    private String flag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fra_category, null);

        LogUtils.i(TAG, "initView");

        initView(view);

        return view;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void initView(View view) {

        mTvFlag = getId(R.id.tv_flag, view);

        flag = getArguments().getString(TAG);

        mTvFlag.setText(flag);
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
