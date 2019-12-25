package com.mincat.mobilemallmanager.app;

import com.mincat.sample.app.MinCatApp;
import com.mincat.sample.utils.GetAppSha1;
import com.mincat.sample.utils.LogUtils;

/**
 * @author Gin
 * @描述 application
 */
public class MyApp extends MinCatApp {

    private static final String TAG = MyApp.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();


        LogUtils.i(TAG,"sha1:"+ GetAppSha1.getSha1(this));

    }
}
