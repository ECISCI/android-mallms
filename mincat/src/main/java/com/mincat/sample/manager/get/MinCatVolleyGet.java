package com.mincat.sample.manager.get;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mincat.sample.manager.base.MinCatVolleyRequest;
import com.mincat.sample.manager.inter.MinCatVolleyGetListener;
import com.mincat.sample.utils.LogUtils;
import com.mincat.sample.utils.NetUtils;
import com.mincat.sample.utils.VolleySingle;

/**
 * @author Gin
 * @描述 Volley 执行Get网络请求 当Activity仅需要Get请求时继承此类即可
 */

public abstract class MinCatVolleyGet extends MinCatVolleyRequest implements MinCatVolleyGetListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }


    /**
     * @param context   上下文对象
     * @param url       请求的url地址
     * @param hasDialog 是否有进度条
     * @描述 Volley 请求 Get请求
     */
    protected void executeVolleyGetRequest(Context context, String url, String sign, boolean hasDialog
    ) {

        // 判断网络是否可用
        if (NetUtils.checkNet(context)) {
            if (hasDialog) {


                // 此处加载进度条
            }

            mQueue = Volley.newRequestQueue(context);

            StringRequest request = new StringRequest(Request.Method.GET, url, requestListener(hasDialog, sign), errorListener(hasDialog, sign)) {

                public RetryPolicy getRetryPolicy() {
                    return new DefaultRetryPolicy(REQUEST_TIMEOUT,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                }
            };
            VolleySingle.getVolleySingle(context.getApplicationContext()).addToRequestQueue(request);
        } else {

            showNetConnectionErrorToast(context);

        }
    }


    /**
     * @param hasDialog 是否有进度条
     * @return
     * @描述 Volley Get请求成功调用此方法
     */
    protected Response.Listener<String> requestListener(final boolean hasDialog, final String sign) {
        return new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                if (hasDialog) {
                    // 在此处关闭进度条
                }
                LogUtils.i(TAG, "Volley Get 请求成功,返回参数:" + response);
                onHandleResponsePost(response, sign);


            }
        };
    }


    /**
     * @param hasDialog
     * @return
     * @描述 Volley Get请求失败调用此方法
     */
    protected Response.ErrorListener errorListener(final boolean hasDialog, final String sign) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (hasDialog) {
                    // 在此处关闭进度条
                }
                LogUtils.i(TAG, "Volley Get 请求失败,错误信息:" + error);
                errorListener(error, sign);
            }
        };
    }


}
