package com.mincat.mobilemallmanager.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.mincat.sample.utils.Constants;
import com.mincat.sample.utils.LogUtils;

/**
 * @author Gin
 * @描述 SharedPreferences工具类
 */
public class SpUtils {
    public static final String TAG = SpUtils.class.getName();

    // 禁止创建本类对象
    private SpUtils() {

    }

    /**
     * 添加登录token
     *
     * @param user_login_token token值
     * @param context          上下文对象
     */
    public static void setUserLoginToken(String user_login_token, Context context) {
        SharedPreferences sp;
        SharedPreferences.Editor editor;
        try {
            sp = context.getSharedPreferences(Constants.CONFIG,
                    Context.MODE_PRIVATE);
            editor = sp.edit();
            editor.putString("user_login_token", user_login_token);
            editor.commit();

        } catch (Exception e) {


        }
    }

    /**
     * 获取用户token
     *
     * @param context 上下文对象
     * @return token值
     */
    public static String getUserLoginToken(Context context) {

        SharedPreferences sp;
        try {
            sp = context.getSharedPreferences(Constants.CONFIG, Context.MODE_PRIVATE);
            String token = sp.getString("user_login_token", "");

            return token;

        } catch (Exception e) {

            e.printStackTrace();
            return "";
        }
    }
    /**
     * 添加登录token
     *
     * @param userId token值
     * @param context          上下文对象
     */
    public static void setUserId(String userId, Context context) {
        SharedPreferences sp;
        SharedPreferences.Editor editor;
        try {
            sp = context.getSharedPreferences(Constants.CONFIG,
                    Context.MODE_PRIVATE);
            editor = sp.edit();
            editor.putString("userId", userId);
            editor.commit();

        } catch (Exception e) {


        }
    }

    /**
     * 获取用户token
     *
     * @param context 上下文对象
     * @return token值
     */
    public static String getUserId(Context context) {

        SharedPreferences sp;
        try {
            sp = context.getSharedPreferences(Constants.CONFIG, Context.MODE_PRIVATE);
            String userInfo = sp.getString("userId", "");

            return userInfo;

        } catch (Exception e) {

            e.printStackTrace();
            return "";
        }
    }

    /**
     * @param isLogin 登录标记
     * @param context 上下文对象
     */
    public static void setIsLogin(String isLogin, Context context) {
        SharedPreferences sp;
        SharedPreferences.Editor editor;
        try {
            sp = context.getSharedPreferences(Constants.CONFIG,
                    Context.MODE_PRIVATE);
            editor = sp.edit();
            editor.putString("isLogin", isLogin);
            editor.commit();

        } catch (Exception e) {

            LogUtils.i(TAG, "保存该用户是否登录异常抛出异常:" + e);
        }
    }

    /**
     * @param context 上下文对象
     * @return boolean值
     */
    public static boolean getIsLogin(Context context) {

        SharedPreferences sp;
        try {
            sp = context.getSharedPreferences(Constants.CONFIG, Context.MODE_PRIVATE);
            String isLogin = sp.getString("isLogin", "");

            if (isLogin.equals("isLogin")) {

                return true;
            } else {

                return false;
            }

        } catch (Exception e) {
            LogUtils.i(TAG, "获取该用户是否登录异常抛出异常:" + e);
            return false;
        }
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
