package com.mincat.mobilemallmanager.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.AppCompatImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mincat.sample.manager.file.MinCatDownloadFile;
import com.mincat.sample.manager.inter.MinCatDownLoadListener;
import com.mincat.sample.utils.LogUtils;
import com.mincat.sample.utils.T;

import java.io.File;

public class HttpGetVCodeUtil {

    private static final String TAG = HttpGetVCodeUtil.class.getSimpleName();

    private HttpGetVCodeUtil() { // 禁止创建此类对象
    }


    private static boolean checkDownloadPremission(Activity activity) {

        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

            //验证是否许可权限
            for (String str : permissions) {
                if (activity.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    activity.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return false;
                } else {
                    //这里就是权限打开之后自己要操作的逻辑
                    return true;
                }
            }
        }
        return false;
    }


    public static void getVCode(final Activity activity, String url, String filePath, boolean hasDialog, final AppCompatImageView mImageVCode) {

        if (checkDownloadPremission(activity)) {



            MinCatDownloadFile.downloadFile(activity, url, filePath, hasDialog, new MinCatDownLoadListener() {


                @Override
                public void onSuccessListener(File result) {

                    LogUtils.i(TAG, "图片保存地址:" + result);

                    Glide.with(activity).load(Uri.fromFile(result)).into(mImageVCode);
                }

                @Override
                public void onErrorListener(Throwable ex, boolean isOnCallback) {
                    LogUtils.i(TAG, "Error:" + ex);
                }

                @Override
                public void onLoadingListener(long total, long current, boolean isDownloading) {

                }
            });

        } else {

            T.showShort(activity, "权限不足");
        }
    }
}
