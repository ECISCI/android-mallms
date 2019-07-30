package com.mincat.sample.imagecache.single;

import android.content.Context;

import com.mincat.sample.imagecache.DiskLruCache;
import com.mincat.sample.imagecache.utils.CreateDiskLruCache;
import com.mincat.sample.imagecache.utils.DownLoadImage;
import com.mincat.sample.imagecache.utils.ImageUrlMd5;
import com.mincat.sample.utils.LogUtils;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Gin
 * @描述 缓存单张图片
 * @说明 此处做单利模式处理
 * @说明 通常缓存的地址在   /sdcard/Android/data/<application package>/cache
 * @注意 每当APP版本号发生变，缓存路径下的缓存文件就会全部被清除
 */

public class CacheSingleImage {

    public static final String TAG = CacheSingleImage.class.getSimpleName();


    // 禁止创建本类对象
    private CacheSingleImage() {
    }

    private static CacheSingleImage cacheSingleImage = new CacheSingleImage();

    public static CacheSingleImage getInstance() {

        return cacheSingleImage;
    }

    private DiskLruCache diskLruCache;

    private DiskLruCache.Editor editor;

    private String key;

    private OutputStream outputStream;

    /**
     * @描述 缓存单张图片
     */
    public void cache(final Context context, final String imageUrl) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    diskLruCache = CreateDiskLruCache.create(context);
                    key = ImageUrlMd5.hashKeyForDisk(imageUrl);
                    editor = diskLruCache.edit(key);
                    if (editor != null) {
                        outputStream = editor.newOutputStream(0);

                        if (DownLoadImage.downLoadImage(imageUrl, outputStream)) {
                            LogUtils.i(TAG, "图片缓存成功:" + "editor.commit()");
                            editor.commit();
                        } else {
                            try {
                                editor.abort();
                            } catch (Exception e) {
                                LogUtils.i(TAG, "图片缓存失败:" + " editor.abort()");
                            }

                            LogUtils.i(TAG, "图片缓存失败:" + " editor.abort()");
                        }
                    }
                    diskLruCache.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                    LogUtils.i(TAG, "图片缓存失败" + e);
                }
            }
        }).start();

    }


}
