package com.mincat.sample.utils;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

/**
 * @author Gin
 * @描述 获取屏幕信息
 */

public class GetScreenParam {

    private static WindowManager mWindowManager;

    private static int width;

    private static int height;

    // 禁止创建此类对象
    private GetScreenParam() {
    }

    /**
     * 获取屏幕宽度
     *
     * @param activity
     * @return
     */
    public static int getScreenWidth(Activity activity) {


        mWindowManager = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);

        width = mWindowManager.getDefaultDisplay().getWidth();

        return width;
    }

    /**
     * 获取屏幕高度
     *
     * @param activity
     * @return
     */
    public static int getScreenHeight(Activity activity) {


        mWindowManager = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);

        height = mWindowManager.getDefaultDisplay().getHeight();

        return height;

    }
}
