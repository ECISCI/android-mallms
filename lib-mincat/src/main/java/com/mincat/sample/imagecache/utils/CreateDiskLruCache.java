package com.mincat.sample.imagecache.utils;

import android.content.Context;

import com.mincat.sample.imagecache.DiskLruCache;
import com.mincat.sample.utils.Constants;
import com.mincat.sample.utils.GetVersion;
import com.mincat.sample.utils.LogUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author Gin
 * @描述 创建 DiskLruCache对象
 */

public class CreateDiskLruCache {

    private static final String TAG = CreateDiskLruCache.class.getSimpleName();

    // 禁止创建本类对象
    private CreateDiskLruCache() {
    }

    // 缓存图片的大小 一般10M就可以,如果需要更大直接更改此处即可
    private static long cache_length = 10 * 1024 * 1024;

    // 创建本地文件夹命名
    private static String file_dir_name = Constants.FILE_DIR_NAME;

    private static DiskLruCache mDiskLruCache = null;

    private static File cacheDir;


    /**
     * @param context 上下文对象
     * @return
     */
    public static DiskLruCache create(Context context) {

        try {
            cacheDir = GetDiskDirPath.cacheDir(context, file_dir_name);

            if (!cacheDir.exists()) {

                cacheDir.mkdirs();
            }
            mDiskLruCache = DiskLruCache.open(cacheDir, GetVersion.getVersionCode(context), 1, cache_length);

            return mDiskLruCache;

        } catch (IOException e) {

            LogUtils.i(TAG, "创建 DiskLruCache失败:" + e);
            e.printStackTrace();
            return null;
        }

    }
}
