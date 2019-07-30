package com.mincat.mobilemallmanager.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mincat.mobilemallmanager.R;
import com.mincat.sample.manager.web.MinCatWebAct;

public class PublicWeb extends MinCatWebAct {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_public_web);
        initView();
    }

    @Override
    public void initView() {

        webView = getId(R.id.web);



        webView.setWebViewClient(new WebViewClient(){


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {


                return false;
            }
        });


    }

    @Override
    public void onNetRequest() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public String assembleRequestParam() {
        return null;
    }
}
