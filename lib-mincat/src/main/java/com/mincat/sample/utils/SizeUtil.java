package com.mincat.sample.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * 尺寸转换工具类
 *
 * @author Gin
 */

public class SizeUtil {


    // 禁止创建此类对象
    private SizeUtil() {
    }

    //dpi转px
    public static int Dp2Px(Context context, int dpi) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpi, context.getResources().getDisplayMetrics());
    }

    //px转dp
    public static int Px2Dp(Context context, int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, context.getResources().getDisplayMetrics());
    }

    //sp转px
    public static int Sp2Px(Context context, int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    //px转sp
    public static int Px2Sp(Context context, int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, context.getResources().getDisplayMetrics());
    }
}
