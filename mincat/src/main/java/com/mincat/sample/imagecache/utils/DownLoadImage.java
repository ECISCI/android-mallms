package com.mincat.sample.imagecache.utils;

import com.mincat.sample.utils.LogUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Gin
 * @描述 下载图片并写入
 */
public class DownLoadImage {
    public static final String TAG = DownLoadImage.class.getSimpleName();

    // 禁止创建本类对象
    private DownLoadImage() {
    }

    private static HttpURLConnection urlConnection = null;
    private static BufferedOutputStream out = null;
    private static BufferedInputStream in = null;

    public static boolean downLoadImage(String urlString, OutputStream outputStream) {

        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            out = new BufferedOutputStream(outputStream, 8 * 1024);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (final IOException e) {
            LogUtils.i(TAG, "image cache failed:" + e);
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                LogUtils.i(TAG, "image cache failed:" + e);
                e.printStackTrace();
            }
        }
        return false;
    }
}
