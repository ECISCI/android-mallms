package com.mincat.sample.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * @author Gin
 * @描述 文件管理类
 */

public class FileUtil {


    public static final String TAG = FileUtil.class.getSimpleName();

    /**
     * 检验sdCard状态
     *
     * @return boolean
     */
    public static boolean checkSDCard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }


    public static String getSDPath(Context context) {
        String sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory().toString();// 获取跟目录
        } else {
            // SD卡不存在返回 内存路径
            sdDir = context.getCacheDir().getAbsolutePath();
        }
        return sdDir;

    }

    /**
     * 保存文件文件到目录
     *
     * @param context
     * @return 文件保存的目录
     */
    public static String setMkdir(Context context) {
        String filePath;
        if (checkSDCard()) {
            filePath = Environment.getExternalStorageDirectory() + File.separator + "DownloadImage";
        } else {
            filePath = context.getCacheDir().getAbsolutePath() + File.separator + "DownloadImage";
        }
        File file = new File(filePath);
        if (!file.exists()) {
            boolean b = file.mkdirs();

            LogUtils.e(TAG, "文件不存在,创建文件" + b);
        } else {
            LogUtils.e(TAG, "文件存在");
        }
        return filePath;
    }
}
