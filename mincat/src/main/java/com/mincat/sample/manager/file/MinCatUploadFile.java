package com.mincat.sample.manager.file;

import android.content.Context;

import com.mincat.sample.manager.inter.MinCatUpLoadListener;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * @author Gin
 * @描述 Xutils 上传文件
 */

public class MinCatUploadFile {

    // 禁止创建此类对象
    private MinCatUploadFile() {

    }


    /**
     * @param filePath      文件路径
     * @param fileParamName 服务器给的文件参数名
     * @param listener      上传回调监听
     * @描述 Xutils上传文件
     */
    public static void upLoadFile(
            Context context,
            String filePath,
            String fileParamName,
            final MinCatUpLoadListener listener) {

        RequestParams params = new RequestParams(filePath);
        params.setMultipart(true);
        params.addBodyParameter(fileParamName, new File(filePath));

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                listener.onSuccessListener(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                listener.onErrorListener(ex, isOnCallback);
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }

        });
    }


}
