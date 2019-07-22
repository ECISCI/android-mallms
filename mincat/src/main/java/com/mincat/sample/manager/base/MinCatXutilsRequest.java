package com.mincat.sample.manager.base;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.mincat.sample.manager.inter.MinCatInitUi;

import org.xutils.http.RequestParams;

/**
 * @author Gin
 * @描述 Xutils 网络请求监听基类
 */

public abstract class MinCatXutilsRequest extends MinCatAppCompat implements MinCatInitUi {

    public static int DEFAULT_CACHE_EXPIRY_TIME = 3000;
    public static int CACHE_TIME = 1000 * 0;
    protected RequestParams params;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

}
