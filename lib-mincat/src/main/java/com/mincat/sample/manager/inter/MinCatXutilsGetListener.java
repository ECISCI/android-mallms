package com.mincat.sample.manager.inter;

/**
 * @author Gin
 * @描述 Xutils Get请求监听
 */

public interface MinCatXutilsGetListener {

    /**
     * 请求成功回调
     *
     * @param result 返回结果
     * @param sign   请求标记
     */
    void onSuccessListener(String result, String sign);

    /**
     * 请求失败回调
     *
     * @param ex           错误信息
     * @param isOnCallback
     */
    void onErrorListener(Throwable ex, boolean isOnCallback);
}
