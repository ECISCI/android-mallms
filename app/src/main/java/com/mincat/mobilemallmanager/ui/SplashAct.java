package com.mincat.mobilemallmanager.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

import com.mincat.mobilemallmanager.MainHomeAct;
import com.mincat.mobilemallmanager.R;
import com.mincat.mobilemallmanager.utils.SpUtils;
import com.mincat.sample.manager.base.MinCatAppCompat;
import com.mincat.sample.utils.WindowTitle;


/**
 * @author Ming
 * @描述 启动页
 */
public class SplashAct extends MinCatAppCompat {

    public static SplashAct instance = null;
    private boolean isLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_splash);
        instance = this;
        initView();
    }

    @Override
    public void initView() {

        WindowTitle.fullScreen(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                isLogin = SpUtils.getIsLogin(SplashAct.this);

                if (!isLogin) { // 如果没有登录,则进入登录页

                    inStep(LoginAct.class);

                } else { // 如果已登录则进入主界面

                    inStep(MainHomeAct.class);
                }

            }
        }, 2000);
    }


    protected void inStep(Class clazz) {

        intentUtils.launchActFromRight(this, clazz);
        SplashAct.this.finish();
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

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
