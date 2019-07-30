package com.mincat.sample.manager.get;

import android.content.Context;

import com.mincat.sample.manager.base.MinCatXutilsRequest;
import com.mincat.sample.manager.inter.MinCatXutilsGetListener;
import com.mincat.sample.utils.NetUtils;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * @author Gin
 */

public abstract class MinCatXutilsGet extends MinCatXutilsRequest implements MinCatXutilsGetListener {


    /**
     * @param url       请求地址
     * @param hasDialog 是否包含进度条
     * @param sign      请求标记
     * @return
     */
    protected Callback.Cancelable executeXutilsGetRequest(Context context,
                                                          String url,
                                                          String sign,
                                                          boolean hasDialog

    ) {

        return sendXutilsGetHttpRequest(context, HttpMethod.GET, url, params, sign, hasDialog);
    }


    private Callback.Cancelable sendXutilsGetHttpRequest(
            Context context,
            HttpMethod method,
            String url,
            RequestParams params,
            final String sign,
            boolean hasDialog


    ) {

        if (params == null) {
            params = new RequestParams(url);
        }

        if (NetUtils.checkNet(context)) {

            if (hasDialog) {
                // 此处加载进度条
            }

            // 为请求添加缓存时间
            params.setCacheMaxAge(CACHE_TIME);
            // 为请求添加超时时间
            params.setConnectTimeout(DEFAULT_CACHE_EXPIRY_TIME);


            return x.http().request(method, params, new Callback.CommonCallback<String>() {


                /**
                 * 请求成功回调函数
                 *
                 * @param result
                 */
                @Override
                public void onSuccess(String result) {
                    // 此处进行进度条关闭操作
                    onSuccessListener(result, sign);


                }

                /**
                 * 请求失败回调函数
                 *
                 * @param ex
                 * @param isOnCallback
                 */
                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    // 此处进行进度条关闭操作
                    onErrorListener(ex, isOnCallback);
                }

                /**
                 * 主动取消回调函数
                 *
                 * @param cex
                 */
                @Override
                public void onCancelled(CancelledException cex) {
                    // 此处进行进度条关闭操作

                }

                /**
                 * 请求完成回调函数
                 */
                @Override
                public void onFinished() {
                    // 此处进行进度条关闭操作
                }
            });

        } else {
            showNetConnectionErrorToast(context);
            return null;
        }
    }


}
