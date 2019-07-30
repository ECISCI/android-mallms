package com.mincat.sample.manager.base;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.android.volley.RequestQueue;

/**
 * @author Gin
 * @描述 Volley网络请求监听基类
 */

public abstract class MinCatVolleyRequest extends MinCatAppCompat {

    // 请求超时时间
    protected static final int REQUEST_TIMEOUT = 15000;

    // Volley请求队列
    protected RequestQueue mQueue;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        initView();
    }


}
