package com.mincat.sample.manager.post;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.mincat.sample.manager.base.MinCatXutilsRequest;
import com.mincat.sample.manager.inter.MinCatXutilsPostListener;
import com.mincat.sample.utils.LogUtils;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * @author Gin
 * @描述 Xutils Post网络访问,当页面仅需要Post网络访问请求时继承此类即可
 */

public abstract class MinCatXutilsPost extends MinCatXutilsRequest implements MinCatXutilsPostListener {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    protected Callback.Cancelable executeXutilsPostRequest(Context context, RequestParams params, String sign, boolean hasDialog) {

        return sendXutilsPostHttpRequest(context, HttpMethod.POST, params, sign, hasDialog);
    }


    protected Callback.Cancelable sendXutilsPostHttpRequest(Context context,
                                                            HttpMethod method,
                                                            RequestParams params,
                                                            final String sign,
                                                            final boolean hasDialog
    ) {

        if (params == null) {

            throw new NullPointerException("post 必须携带请求参数");
        }
        if (hasDialog) {

            // 在此加载进度条
        }


        return x.http().request(method, params, new Callback.CommonCallback<String>() {


            /**
             * 主动取消请求回调函数
             * @param msg
             */
            @Override
            public void onCancelled(CancelledException msg) {
                LogUtils.d(TAG, " Xutils网络访问取消 onCancelled()方法");
            }

            /**
             * 请求异常回调方法
             * @param arg0 抛出的异常信息
             * @param arg1
             */
            @Override
            public void onError(Throwable arg0, boolean arg1) {
                LogUtils.d(TAG, " Xutils网络访问异常 onError()方法");
                onErrorListener(arg0, arg1, hasDialog, sign);
            }

            /**
             * 请求成功回调函数
             * @param result 返回结果
             */
            @Override
            public void onSuccess(String result) {

                LogUtils.d(TAG, " Xutils网络访问成功");

                onSuccessListener(result, hasDialog, sign);
            }

            /**
             * 请求完成回调函数
             */
            @Override
            public void onFinished() {

                LogUtils.i(TAG, "onFinished()");
                onFinishedListener(hasDialog, sign);
            }
        });
    }
}
