package com.mincat.sample.utils.statusBar;

import android.app.Activity;

import com.mincat.sample.R;

public class StatusBarCompat {

    private static StatusBarCompat statusBarCompat;

    public static StatusBarCompat getInstance() {
        if (statusBarCompat == null) {
            synchronized (StatusBarCompat.class) {
                statusBarCompat = new StatusBarCompat();
            }
        }
        return statusBarCompat;
    }


    public void setStatusBarWhiteBg(Activity activity) {
        if (ImmersionBar.isSupportStatusBarDarkFont()) { //判断当前设备支不支持状态栏字体变色
            //处理状态栏字体为黑色
            ImmersionBar.with(activity).
                    statusBarColor(R.color.white).fitsSystemWindows(true).statusBarDarkFont(true).
                    init();
        } else {
            //处理状态栏有透明度
            ImmersionBar.with(activity).
                    statusBarColor(R.color.white).fitsSystemWindows(true).
                    init();
        }
    }

    public void setTransparentStatusBar(Activity activity) {
        ImmersionBar.with(activity).
                fitsSystemWindows(false).statusBarDarkFont(false).
                transparentStatusBar().init();
    }

    public void setStatusBarColor(Activity activity, int color) {
        ImmersionBar.with(activity).
                statusBarColor(color).fitsSystemWindows(true).statusBarDarkFont(false).
                init();
    }
}
