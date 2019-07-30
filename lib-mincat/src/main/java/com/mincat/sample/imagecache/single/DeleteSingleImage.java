package com.mincat.sample.imagecache.single;

import android.content.Context;

import com.mincat.sample.imagecache.DiskLruCache;
import com.mincat.sample.imagecache.utils.CreateDiskLruCache;
import com.mincat.sample.imagecache.utils.ImageUrlMd5;
import com.mincat.sample.utils.LogUtils;

import java.io.IOException;

/**
 * @author Gin
 * @描述 删除单张图片缓存
 */

public class DeleteSingleImage {

    public static final String TAG = DeleteSingleImage.class.getSimpleName();

    private static DiskLruCache diskLruCache;

    private static String key;


    public static void delete(Context context, String imageUrl) {

        try {
            diskLruCache = CreateDiskLruCache.create(context);
            key = ImageUrlMd5.hashKeyForDisk(imageUrl);
            diskLruCache.remove(key);

            LogUtils.i(TAG, "remove single cache success");
        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.i(TAG, "remove single cache failed" + e);
        }

    }
}
