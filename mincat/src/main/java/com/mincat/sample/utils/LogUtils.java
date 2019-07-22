package com.mincat.sample.utils;

import android.util.Log;

/**
 *
 * @author Gin
 *         Log日志管理类
 */
public class LogUtils {

    // 是否需要打印bug，可以在application的onCreate函数里面初始化
    public static boolean isDebug = true;

    public static void i(String tag, String msg) {

        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void e(String tag, String msg) {

        if (isDebug) {
            Log.e(tag, msg);
        }
    }

    public static void d(String tag, String msg) {

        if (isDebug) {
            Log.d(tag, msg);
        }
    }
}
