package com.mincat.sample.manager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mincat.sample.custom.CustomProgressDialog;
import com.mincat.sample.manager.base.MinCatAppCompat;
import com.mincat.sample.manager.inter.MinCatVolleyAllListener;
import com.mincat.sample.utils.NetUtils;
import com.mincat.sample.utils.VolleySingle;
import com.mincat.sample.utils.statusBar.StatusBarCompat;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Gin
 * @描述 包含了网络请求, GET 和POST两种方法
 */
public abstract class MinCatBaseRequest extends MinCatAppCompat implements MinCatVolleyAllListener {

    private static final String TAG = MinCatBaseRequest.class.getSimpleName();

    // 请求超时时间
    protected static final int REQUEST_TIMEOUT = 15000;

    // Volley请求队列
    protected RequestQueue mQueue;

    protected CustomProgressDialog customProgressDialog = CustomProgressDialog.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.getInstance().setStatusBarWhiteBg(this);
    }

    /**
     * 执行网络请求 POST方法
     *
     * @param context   上下文对象
     * @param sign      标记
     * @param url       请求的URL地址
     * @param param     参数
     * @param hasDialog 是否有进度条
     */
    protected void executeVolleyPostRequest(final Context context, String url, final String param, String sign, boolean hasDialog) {

        if (NetUtils.checkNet(context)) {
            if (hasDialog) {


            }
            mQueue = Volley.newRequestQueue(context);

            StringRequest request = new StringRequest(Request.Method.POST, url, requestListenerPost(sign, hasDialog), errorListener(sign, hasDialog)) {

                @Override // 此处在请求体中添加请求参数
                public byte[] getBody() throws AuthFailureError {
                    return param.getBytes();
                }

                @Override // 添加请求头 可以添加token等等信息,根据项目情况自定义
                public Map<String, String> getHeaders() throws AuthFailureError {

                    return initHeaders();
                }


                public RetryPolicy getRetryPolicy() {
                    return new DefaultRetryPolicy(REQUEST_TIMEOUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                }

            };
            // 此处对Volley请求队列做单利模式处理
            VolleySingle.getVolleySingle(context.getApplicationContext()).addToRequestQueue(request);


        } else {

            showNetConnectionErrorToast(context);
        }
    }


    /**
     * 执行网络请求 GET方法
     *
     * @param context   上下文对象
     * @param sign      标记
     * @param url       请求的URL地址
     * @param hasDialog 是否有进度条
     */
    protected void executeVolleyGetRequest(final Context context, String url, String sign, boolean hasDialog) {

        if (NetUtils.checkNet(context)) {
            if (hasDialog) {
                // 此处加载请求进度条

            }
            mQueue = Volley.newRequestQueue(context);

            StringRequest request = new StringRequest(Request.Method.GET, url, requestListenerGet(hasDialog, sign), errorListener(sign, hasDialog)) {

                @Override // 添加请求头
                public Map<String, String> getHeaders() throws AuthFailureError {


                    return initHeaders();
                }

                public RetryPolicy getRetryPolicy() {
                    return new
                            DefaultRetryPolicy(REQUEST_TIMEOUT,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                }

            };
            // 此处对Volley请求队列做单利模式处理
            VolleySingle.getVolleySingle(context.getApplicationContext()).addToRequestQueue(request);


        } else {

            showNetConnectionErrorToast(context);
        }
    }

    /**
     * GET请求成功回调
     *
     * @param hasDialog 是否关闭进度条
     * @param sign      请求标记
     * @return
     */
    protected Response.Listener<String> requestListenerGet(final boolean hasDialog, final String sign) {

        return new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                if (hasDialog) {
                    // 在此处关闭进度条
                }

                onHandleResponseGet(response, sign);
            }
        };
    }


    /**
     * @param sign      请求标记
     * @param hasDialog 是否有进度条
     * @return
     * @描述 POST 请求成功调用此方法
     */
    public Response.Listener<String> requestListenerPost(final String sign, final boolean hasDialog) {
        return new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                if (hasDialog) {
                    // 在此处关闭进度条

                }
                onHandleResponsePost(response, sign);
            }
        };
    }


    /**
     * @param sign      请求标记
     * @param hasDialog 是否有进度条
     * @return
     * @描述 Volley 请求失败调用此方法
     */
    public Response.ErrorListener errorListener(final String sign, final boolean hasDialog) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (hasDialog) {
                    // 如果包含请求进度条 在此处关闭进度条

                }
                errorListener(error, sign);
            }


        };
    }

    /**
     * 设置请求头
     *
     * @return 一个Header的map集合
     */
    private Map<String, String> initHeaders() {

        Map<String, String> headers = new HashMap<>();

        headers.put("Content-Type", "application/json");
        headers.put("Token", "999999999999999999999999999");
        return headers;
    }

}
