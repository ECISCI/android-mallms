package com.mincat.sample.imagecache.more;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.widget.ImageView;

import com.mincat.sample.imagecache.DiskLruCache;
import com.mincat.sample.imagecache.utils.DownLoadImage;
import com.mincat.sample.imagecache.utils.ImageUrlMd5;
import com.mincat.sample.utils.GetVersion;
import com.mincat.sample.utils.LogUtils;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Gin
 * @描述 缓存列表图片
 * @说明 此类可以缓存很多图片，可以做照片墙
 */

public class CacheMoreImage {

    public static final String TAG = CacheMoreImage.class.getSimpleName();

    // 禁止创建此类对象
    private CacheMoreImage() {
    }

    private static CacheMoreImage cacheMoreImage = new CacheMoreImage();

    public static CacheMoreImage getInstance() {

        return cacheMoreImage;
    }

    // 记录所有正在下载或等待下载的任务
    private Set<AsyncDownloadImage> taskCollection;

    // 图片缓存技术的核心类，用于缓存所有下载好的图片，在程序内存达到设定值时会将最少最近使用的图片移除掉
    private LruCache<String, Bitmap> mMemoryCache;

    // 图片硬盘缓存核心类
    private DiskLruCache mDiskLruCache;

    // 需要传入Recycle 保证图片加载Tag不错位
    private RecyclerView recyclerView;

    // 获取应用程序最大可用内存
    private int maxMemory = (int) Runtime.getRuntime().maxMemory();

    // 设置图片缓存大小为程序最大可用内存的1/8
    private int cacheSize = maxMemory / 8;

    // 获取图片缓存路径
    private File cacheDir;

    // 缓存路径名称
    private String file_route_name = "thumb";

    // 图片最大缓存为10m
    private long max_size = 10 * 1024 * 1024;


    /**
     * 核心方法
     *
     * @param imageView
     * @param imageUrl
     */
    public void cacheImage(ImageView imageView, String imageUrl) {

        loadBitmaps(imageView, imageUrl);
    }


    /**
     * 核心方法 初始化 相关参数
     *
     * @param context 上下文对象
     */
    public void initCacheParam(Context context, RecyclerView recyclerView) {

        this.recyclerView = recyclerView;

        taskCollection = new HashSet<AsyncDownloadImage>();

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };
        try {

            cacheDir = getDiskCacheDir(context, file_route_name);
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            // 创建DiskLruCache实例，初始化缓存数据
            mDiskLruCache = DiskLruCache
                    .open(cacheDir, GetVersion.getVersionCode(context), 1, max_size);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * @param key    LruCache的键，这里传入图片的URL地址
     * @param bitmap LruCache的键，这里传入从网络上下载的Bitmap对象
     * @描述 将一张图片存储到LruCache中
     */
    private void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemoryCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    /**
     * @param key LruCache的键，这里传入图片的URL地址
     * @return 对应传入键的Bitmap对象，或者null
     * @描述 从LruCache中获取一张图片，如果不存在就返回null
     */
    private Bitmap getBitmapFromMemoryCache(String key) {
        return mMemoryCache.get(key);
    }

    /**
     * 加载Bitmap对象。此方法会在LruCache中检查所有屏幕中可见的ImageView的Bitmap对象
     * 如果发现任何一个ImageView的Bitmap对象不在缓存中，就会开启异步线程去下载图片
     */
    private void loadBitmaps(ImageView imageView, String imageUrl) {
        try {
            Bitmap bitmap = getBitmapFromMemoryCache(imageUrl);
            if (bitmap == null) {
                AsyncDownloadImage task = new AsyncDownloadImage();
                taskCollection.add(task);
                task.execute(imageUrl);
            } else {
                if (imageView != null && bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        } catch (Exception e) {
            LogUtils.i(TAG, "loadBitmaps is failed:" + e);
            e.printStackTrace();
        }
    }

    /**
     * @描述 取消所有正在下载或等待下载的任务
     */
    public void cancelAllTasks() {
        if (taskCollection != null) {
            for (AsyncDownloadImage task : taskCollection) {
                task.cancel(false);
            }
        }
    }

    //

    /**
     * @param context    上下文对象
     * @param uniqueName 根据传入的uniqueName获取缓存的完整路径地址
     * @return 路径地址
     * @描述 获取缓存路径
     */
    private File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }


    /**
     * @描述 将缓存记录同步到journal文件中
     * @说明 此方法最好在onPause()方法中调用
     */
    public void flushCache() {
        if (mDiskLruCache != null) {
            try {
                mDiskLruCache.flush();
                LogUtils.i(TAG, "flush success");
            } catch (IOException e) {
                e.printStackTrace();
                LogUtils.i(TAG, "flush error:" + e);
            }
        }
    }

    /**
     * @描述 异步下载图片的任务。
     */
    class AsyncDownloadImage extends AsyncTask<String, Void, Bitmap> {

        // 图片的URL地址
        private String imageUrl;

        @Override
        protected Bitmap doInBackground(String... params) {
            imageUrl = params[0];
            FileDescriptor fileDescriptor = null;
            FileInputStream fileInputStream = null;
            DiskLruCache.Snapshot snapShot = null;
            try {
                // 生成图片URL对应的key
                final String key = ImageUrlMd5.hashKeyForDisk(imageUrl);
                // 查找key对应的缓存
                snapShot = mDiskLruCache.get(key);
                if (snapShot == null) {
                    // 如果没有找到对应的缓存，则准备从网络上请求数据，并写入缓存
                    DiskLruCache.Editor editor = mDiskLruCache.edit(key);
                    if (editor != null) {
                        OutputStream outputStream = editor.newOutputStream(0);
                        if (DownLoadImage.downLoadImage(imageUrl, outputStream)) {
                            editor.commit();
                        } else {
                            editor.abort();
                        }
                    }
                    // 缓存被写入后，再次查找key对应的缓存
                    snapShot = mDiskLruCache.get(key);
                }
                if (snapShot != null) {
                    fileInputStream = (FileInputStream) snapShot.getInputStream(0);
                    fileDescriptor = fileInputStream.getFD();
                }
                // 将缓存数据解析成Bitmap对象
                Bitmap bitmap = null;
                if (fileDescriptor != null) {
                    bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                }
                if (bitmap != null) {
                    // 将Bitmap对象添加到内存缓存当中
                    addBitmapToMemoryCache(params[0], bitmap);
                }
                return bitmap;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileDescriptor == null && fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            // 根据Tag找到相应的ImageView控件，将下载好的图片显示出来。
            ImageView imageView = (ImageView) recyclerView.findViewWithTag(imageUrl);
            if (imageView != null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
            taskCollection.remove(this);
        }
    }


}
