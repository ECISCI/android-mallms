package com.mincat.sample.imagecache.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * @author Gin
 * @描述 获取缓存路径
 */

public class GetDiskDirPath {

    // 禁止创建本类对象
    private GetDiskDirPath() {
    }

    private static String cachePath;

    /**
     * @param context    上下文对象
     * @param uniqueName 文件名
     * @描述 判断SD卡是否存在，并根据SD卡存在与否更换缓存路径
     */
    public static File cacheDir(Context context, String uniqueName) {

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {

            /**
             * @描述 SD卡存在不可被移除的时候 缓存到 /sdcard/Android/data/<application package>/cache
             */
            cachePath = context.getExternalCacheDir().getPath();

        } else {
            /**
             *  @描述 SD卡不存在的时候缓存到 /data/data/<application package>/cache 这个路径
             */

            cachePath = context.getCacheDir().getPath();
        }

        return new File(cachePath + File.separator + uniqueName);
    }
}
