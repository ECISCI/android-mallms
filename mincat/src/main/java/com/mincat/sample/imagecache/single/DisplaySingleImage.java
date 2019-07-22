package com.mincat.sample.imagecache.single;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.mincat.sample.imagecache.DiskLruCache;
import com.mincat.sample.imagecache.utils.CreateDiskLruCache;
import com.mincat.sample.imagecache.utils.ImageUrlMd5;
import com.mincat.sample.utils.LogUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Gin
 * @描述 展示单张缓存的图片
 */

public class DisplaySingleImage {

    public static final String TAG = DisplaySingleImage.class.getSimpleName();

    // 禁止创建此类对象
    private DisplaySingleImage() {
    }

    private static DiskLruCache diskLruCache;
    // 输入流
    private static InputStream in;
    // BitMap图片展示对象
    private static Bitmap bitmap;

    private static DiskLruCache.Snapshot snapShot;

    public static void display(Context context, String imageUrl, ImageView imageView) {

        try {

            diskLruCache = CreateDiskLruCache.create(context);
            String key = ImageUrlMd5.hashKeyForDisk(imageUrl);
            snapShot = diskLruCache.get(key);
            if (snapShot != null) {
                in = snapShot.getInputStream(0);
                bitmap = BitmapFactory.decodeStream(in);
                imageView.setImageBitmap(bitmap);

                LogUtils.i(TAG, "display single image  success:" + snapShot);
            } else {
                LogUtils.i(TAG, "display single image  failed:" + snapShot);
            }
        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.i(TAG, "display  single image failed:" + e);
        }

    }
}
