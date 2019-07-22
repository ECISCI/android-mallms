package com.mincat.sample.manager.inter;

import java.io.File;

/**
 * @author Gin
 * @描述 Xutils 下载文件监听
 */

public interface MinCatDownLoadListener {

    /**
     * 请求成功回调
     *
     * @param result 返回结果
     */
    void onSuccessListener(File result);

    /**
     * 请求失败回调
     *
     * @param ex           错误信息
     * @param isOnCallback
     */
    void onErrorListener(Throwable ex, boolean isOnCallback);

    /**
     * 加载中回调
     *
     * @param total         总量
     * @param current       当前
     * @param isDownloading
     */
    void onLoadingListener(long total, long current, boolean isDownloading);
}
