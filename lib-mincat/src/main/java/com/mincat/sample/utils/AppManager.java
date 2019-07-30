package com.mincat.sample.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Process;

import java.util.Stack;

/**
 * @author Gin
 * @描述 应用程序Activity管理类
 * @描述 用于Activity管理和应用程序退出
 * @描述 这个类比较古老了 android 4.0的时候做的封装
 */
public class AppManager {

    private static final String TAG = "AppManager";

    private static Stack<Activity> activityStack;

    private static AppManager instance;

    private AppManager() {
    }

    // 单利模式
    public static AppManager getAppManager() {

        if (instance == null) {

            instance = new AppManager();
        }

        return instance;
    }

    // 添加Activity到堆栈
    public void addActivity(Activity activity) {

        if (activityStack == null) {

            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    public void removeActivity(Activity activity) {

        if (activityStack != null) {

            activityStack.remove(activity);
        }
    }

    // 获取当前Activity（堆栈中最后一个压入的）
    public Activity currentActivity() {

        Activity activity = activityStack.lastElement();

        return activity;
    }

    // 结束当前Activity（堆栈中最后一个压入的）
    public void finishActivity() {

        Activity activity = activityStack.lastElement();

        finishActivity(activity);
    }

    // 结束指定的Activity
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    // 结束指定类名的Activity
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    // 结束所有Activity
    public void finishAllActivity() {
        if (activityStack != null && !activityStack.isEmpty()) {
            for (int i = 0, size = activityStack.size(); i < size; i++) {
                if (null != activityStack.get(i)) {
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
        } else {
            LogUtils.i(TAG, "已经清空Activity栈了，请不要再次清理");
        }
    }

    // 结束除了当前Activity（堆栈中最后一个压入的）之外的其他所有Activity
    public void finishActivitysExceptLast() {
        for (int i = 0, size = activityStack.size(); i < size - 1; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
                activityStack.remove(i);
            }
        }
    }

    // 退出应用程序
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            System.gc();
            System.gc();
            System.gc();
            System.gc();
            Process.killProcess(Process.myPid());
        } catch (Exception e) {
            LogUtils.e(TAG, "AppExit:" + e);
        }
    }

    // 关闭虚拟机强行退出
    public void onExit(Context context) {

        Intent intent = new Intent(Intent.ACTION_MAIN);

        intent.addCategory(Intent.CATEGORY_HOME);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);

        System.exit(0);
    }
}