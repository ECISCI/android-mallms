package com.mincat.sample.manager.inter;

/**
 * @author Gin
 * @描述 Xutils Post请求监听
 */

public interface MinCatXutilsPostListener {

    /**
     * 请求成功回调
     *
     * @param result    返回记过
     * @param hasDialog 是否有进度条
     * @param sign      请求标记
     */
    void onSuccessListener(String result, boolean hasDialog, String sign);

    /**
     * 请求失败回调
     *
     * @param arg0      错误信息
     * @param arg1
     * @param hasDialog 是否有进度条
     * @param sign      请求标记
     */
    void onErrorListener(Throwable arg0, boolean arg1, boolean hasDialog, String sign);

    /**
     * 请求结束回调
     *
     * @param hasDialog 是否有进度条
     * @param sign      请求标记
     */
    void onFinishedListener(boolean hasDialog, String sign);
}
