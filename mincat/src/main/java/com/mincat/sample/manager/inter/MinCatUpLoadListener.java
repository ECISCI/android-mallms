package com.mincat.sample.manager.inter;

/**
 * @author Gin
 */

public interface MinCatUpLoadListener {

    /**
     * 上传成功回调
     *
     * @param result 返回结果
     */
    void onSuccessListener(String result);

    /**
     * 上传失败回调
     *
     * @param ex           错误信息
     * @param isOnCallback
     */
    void onErrorListener(Throwable ex, boolean isOnCallback);
}
