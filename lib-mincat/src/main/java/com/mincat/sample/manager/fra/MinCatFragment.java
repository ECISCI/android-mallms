package com.mincat.sample.manager.fra;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mincat.sample.manager.inter.MinCatInitFra;
import com.mincat.sample.manager.inter.MinCatVolleyAllListener;
import com.mincat.sample.utils.IntentUtils;
import com.mincat.sample.utils.LogUtils;
import com.mincat.sample.utils.NetUtils;
import com.mincat.sample.utils.T;
import com.mincat.sample.utils.VolleySingle;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Gin
 * Fragment基类  基于网络请求
 */
public abstract class MinCatFragment extends Fragment implements MinCatInitFra, MinCatVolleyAllListener {

    public static final String TAG = MinCatFragment.class.getName();

    protected static final int REQUEST_TIMEOUT = 15000;

    protected IntentUtils intentUtils = IntentUtils.getInstance();

    /**
     * Volley请求队列
     */
    protected RequestQueue mQueue;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LogUtils.i(TAG, "BaseFragment onCreateView");

        return super.onCreateView(inflater, container, savedInstanceState);

    }


    /**
     * @param context   上下文对象
     * @param sign      标记
     * @param url       请求的URL地址
     * @param param     参数
     * @param hasDialog 是否有进度条
     * @describe 执行网络请求 POST方法
     */
    protected void executeVolleyPostRequest(final Context context, String url, final String param, String sign, boolean hasDialog) {

        if (NetUtils.checkNet(context)) {

            if (hasDialog) {

            }

            mQueue = Volley.newRequestQueue(context);

            StringRequest request = new StringRequest(
                    Request.Method.POST,
                    url,
                    requestListenerPost(sign, hasDialog),
                    errorListener(sign, hasDialog)) {


                @Override // 添加请求头
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();

                    return headers;
                }

                @Override // 添加Json请求参数
                public byte[] getBody() throws AuthFailureError {
                    return param.getBytes();
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


        }
    }

    /**
     * @param context   上下文对象
     * @param url       请求的url地址
     * @param hasDialog 是否有进度条
     * @描述 Volley 请求 Get请求
     */
    protected void executeVolleyGetRequest(Context context, String url, String sign, boolean hasDialog) {

        // 判断网络是否可用
        if (NetUtils.checkNet(context)) {
            if (hasDialog) {

            }

            mQueue = Volley.newRequestQueue(context);

            StringRequest request = new StringRequest(Request.Method.GET, url, requestListenerGet(hasDialog, sign), errorListener(sign, hasDialog)) {

                public RetryPolicy getRetryPolicy() {
                    return new DefaultRetryPolicy(REQUEST_TIMEOUT,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                }
            };
            VolleySingle.getVolleySingle(context.getApplicationContext()).addToRequestQueue(request);
        } else {

            T.showShort(context, "连接服务器异常,请稍后再试");
        }
    }


    /**
     * @param sign      请求标记
     * @param hasDialog 是否有进度条
     * @return
     * @describe POST 请求成功调用此方法
     */
    public Response.Listener<String> requestListenerPost(final String sign, final boolean hasDialog) {
        return new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                if (hasDialog) {

                }
                LogUtils.i(TAG, "Volley Post 请求成功,返回参数:" + response);
                onHandleResponsePost(response, sign);
            }
        };
    }


    /**
     * @param hasDialog 是否有进度条
     * @return
     * @描述 GET请求成功调用此方法
     */
    protected Response.Listener<String> requestListenerGet(final boolean hasDialog, final String sign) {
        return new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                if (hasDialog) {

                }
                LogUtils.i(TAG, "Volley Get 请求成功,返回参数:" + response);
                onHandleResponseGet(response, sign);


            }
        };
    }


    /**
     * @param hasDialog
     * @return
     * @描述 Volley Get请求失败调用此方法
     */
    protected Response.ErrorListener errorListener(final String sign, final boolean hasDialog) {
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


    /**
     * 查找控件ID
     *
     * @param id   控件id
     * @param view view
     * @param <T>  View对象子类
     * @return 查找到的空间id值
     */
    protected <T extends View> T getId(int id, View view) {
        try {
            return (T) view.findViewById(id);

        } catch (ClassCastException e) {
            throw e;
        }
    }

    @Override // 当页面销毁的时候回调
    public void onDestroyView() {
        System.gc();
        System.gc();
        System.gc();
        LogUtils.i(TAG, "BaseFragment  onDestroyView ");
        super.onDestroyView();
    }
}
