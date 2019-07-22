package com.mincat.sample.manager.file;

import android.content.Context;
import android.os.Environment;

import com.mincat.sample.manager.inter.MinCatDownLoadListener;
import com.mincat.sample.utils.NetUtils;
import com.mincat.sample.utils.T;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * @author Gin
 */

public class MinCatDownloadFile {


    // 禁止创建此类对象
    private MinCatDownloadFile() {

    }

    public static final String TAG = MinCatDownloadFile.class.getSimpleName();

    public static int DEFAULT_CACHE_EXPIRY_TIME = 30 * 1000;


    /**
     * @param url      请求下载的url地址
     * @param context  上下文对象
     * @param filePath 文件下载位置
     * @param listener 下载回调监听
     */
    public static void downloadFile(
            final Context context,
            String url,
            String filePath,
            boolean hasDialog,
            final MinCatDownLoadListener listener) {

        RequestParams params = new RequestParams(url);

        //自定义保存路径，Environment.getExternalStorageDirectory()：SD卡的根目录
        params.setSaveFilePath(Environment.getExternalStorageDirectory() + filePath);

        //自动为文件命名
        params.setAutoRename(true);

        // 为请求添加超时时间
        params.setConnectTimeout(DEFAULT_CACHE_EXPIRY_TIME);


        // 检查网络连接
        if (NetUtils.checkNet(context)) {

            if (hasDialog) {
                // 此处添加进度条
            }


            x.http().post(params, new Callback.ProgressCallback<File>() {

                /**
                 * 网络请求成功回调函数
                 * @param result
                 */
                @Override
                public void onSuccess(File result) {

                    // 此处关闭进度条
                    listener.onSuccessListener(result);
                }

                /**
                 * 网络请求错误回调
                 * @param ex
                 * @param isOnCallback
                 */
                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    // 此处关闭进度条
                    listener.onErrorListener(ex, isOnCallback);
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    // 此处关闭进度条
                }

                /**
                 * 请求完成回调函数
                 */
                @Override
                public void onFinished() {
                    // 此处关闭进度条
                }


                /**
                 * 网络请求之前回调
                 */
                @Override
                public void onWaiting() {
                }


                @Override
                public void onStarted() {
                }


                /**
                 * 下载的时候不断回调的方法
                 *
                 * @param total
                 * @param current
                 * @param isDownloading
                 */
                @Override
                public void onLoading(long total, long current, boolean isDownloading) {
                    // 此处关闭进度条
                    listener.onLoadingListener(total, current, isDownloading);
                }
            });

        } else {
            T.showLong(context, "没有检测到网络");

        }

    }

}
