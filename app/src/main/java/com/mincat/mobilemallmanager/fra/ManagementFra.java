package com.mincat.mobilemallmanager.fra;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;
import com.mincat.mobilemallmanager.R;
import com.mincat.mobilemallmanager.ui.AddGoodsAct;
import com.mincat.mobilemallmanager.ui.GoodsCategoryAct;
import com.mincat.sample.manager.fra.MinCatFragment;

/**
 * @author Gin
 * @描述 管理
 */
public class ManagementFra extends MinCatFragment implements SwipeRefreshLayout.OnRefreshListener {


    private LinearLayout mLinearAddGoods;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fra_management, null);

        initView(view);

        return view;
    }

    @Override
    public void onRefresh() {


    }

    @Override
    public void initView(View view) {


        mLinearAddGoods = getId(R.id.linear_add_new_goods, view);
        mLinearAddGoods.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.linear_add_new_goods:

                intentUtils.launchActFromRight(getActivity(), AddGoodsAct.class);

                break;
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
