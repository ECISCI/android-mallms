package com.mincat.sample.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Environment;
import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Gin
 *         文件数据存储 读取帮助类
 */
public class IOUtils {
    /**
     * Log 标识
     */
    public static String TAG = IOUtils.class.getName();
    private static final int BUFFER_SIZE = 32 * 1024; // 32 KB
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    private IOUtils() {

    }

    public static void copyStream(InputStream is, OutputStream os)
            throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        while (true) {
            int count = is.read(bytes, 0, BUFFER_SIZE);
            if (count == -1) {
                break;
            }
            os.write(bytes, 0, count);
        }
    }

    public static void closeSilently(Closeable closeable) {
        try {
            closeable.close();
        } catch (Exception e) {
        }
    }

    /**
     * 将流转化为数组
     *
     * @param is 传入的流
     * @return 返回转化完的字节数组
     */
    public static byte[] readByte(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = is.read(b)) != -1) {
                baos.write(b, 0, len);
            }
            is.close();
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将流转化为字符串
     *
     * @param is 传入的流
     * @return 返回转化后得到的字符串
     */
    public static String readString(InputStream is) {
        byte[] b = readByte(is);
        return new String(b);
    }

    /**
     * 将字节流转换成文本文件
     *
     * @param bytes        字节数组
     * @param filePathName 文件路径名
     * @return 返回写入成功或失败
     */
    public static boolean writeByteToFile(byte[] bytes, String filePathName) {
        File file = new File(filePathName);
        FileOutputStream fout;
        boolean flag = false;
        try {
            fout = new FileOutputStream(file, true);
            fout.write(bytes);
            fout.close();
            flag = true;
        } catch (Exception e) {
        }
        return flag;
    }

    /**
     * 将数据转换成文本文件
     *
     * @param data         写入文本对象的数据
     * @param filePathName 文字的路径和名称 比如：folder +"/"+"fileName"
     * @return 是否保存成功
     */
    public static boolean writeTextFile(String data, String filePathName) {
        return writeTextFile(data, filePathName, false);
    }

    /**
     * 将数据转换成文本文件
     *
     * @param data         写入文本对象的数据
     * @param filePathName 文字的路径和名称 比如：folder +"/"+"fileName"
     * @return 是否保存成功
     */
    public static boolean writeTextFile(String data, String filePathName,
                                        boolean append) {
        if (TextUtils.isEmpty(data) || TextUtils.isEmpty(filePathName)) {
            return false;
        }
        File file = new File(filePathName);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream fout;
        boolean flag = false;
        try {
            fout = new FileOutputStream(file, append);
            byte[] bytes = data.getBytes();
            fout.write(bytes);
            fout.close();
            flag = true;
        } catch (Exception e) {
        }
        return flag;
    }

    /**
     * 将数据转换成文本文件
     *
     * @param data     写入文本对象的数据
     * @param filePath path
     *                 文本对象保存的路径
     * @param fileName 文本对象保存的名称
     * @return 是否转换成功 true 成功 :false 失败
     */
    public static boolean writeTextFile(String data, String filePath,
                                        String fileName) {
        String storgePath = filePath + File.separator + fileName;
        return writeTextFile(data, storgePath);

    }

    /**
     * 通过文件路径获取文件
     *
     * @param path     文件的路径
     * @param fileName 文件的名称
     * @return 得到文件
     */
    public static File getFile(String path, String fileName) {
        String filePathName = path + File.separator + fileName;
        File file = new File(filePathName);
        if (file != null && file.isFile()) {
            return file;
        }
        return null;
    }

    /**
     * 通过文件详细路径将文本内容读取出来
     *
     * @param filePath 文件路径
     * @return 得到字节数组
     */
    public static byte[] readTextFile(String filePath) {
        File file = getFile(filePath);
        if (file == null || !file.isFile()) {
            return null;
        }
        return readTextFile(file);
    }

    /**
     * 通过文件将文件内容读取出来
     *
     * @param file 文件
     * @return 字节数组
     */
    public static byte[] readTextFile(File file) {
        StringBuffer StringBuffer = new StringBuffer();
        String result = "";
        try {
            FileInputStream fin = new FileInputStream(file);

            // 对此输入流调用的方法可以不受阻塞地从此输入流读取
            int length = fin.available();
            byte[] buffer = new byte[length];
            while (fin.read(buffer) != -1) {
                StringBuffer.append(buffer);
            }
            result = StringBuffer.toString();
            fin.close();
        } catch (Exception e) {

        }
        return result.getBytes();
    }

    /**
     * 获取文件文本对象
     *
     * @param filePath path
     *                 文件路径名 例： folder+"\"+fileName
     * @return 得到文件对象
     */
    public static File getFile(String filePath) {
        File file = new File(filePath);
        return file;
    }

    /**
     * 按比例压缩Bitmap 的图片
     *
     * @param bitmap 需要压缩的bitmap
     * @param width  压缩后的宽
     * @param height 压缩后的高
     * @return 得到的Bitmap图像
     */
    public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) width / w);
        float scaleHeight = ((float) height / h);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
        return newbmp;
    }

    /**
     * Create a File for saving an image or video
     */
    public static File getOutputMediaFile(int type, String fileName) {

        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                fileName);

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".bmp");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }
}
