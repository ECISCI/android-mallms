package com.mincat.sample.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Gin
 * @描述 SharedPreferences工具类
 */
public class SpUtils {
    public static final String TAG = SpUtils.class.getName();

    // 禁止创建本类对象
    private SpUtils() {

    }


    // 清空本地所有通过SharePreference缓存的数据
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constants.CONFIG,
                Context.MODE_PRIVATE);
        sp.edit().clear().commit();
        SharedPreferences spa = context.getSharedPreferences(Constants.USERINFO,
                Context.MODE_PRIVATE);
        spa.edit().clear().commit();
    }
}
