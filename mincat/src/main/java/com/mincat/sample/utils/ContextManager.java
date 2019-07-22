package com.mincat.sample.utils;

import android.app.Activity;
import android.content.Intent;

import com.mincat.sample.R;

import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Modifier Gin
 */
public class ContextManager {

    public static final String TAG = ContextManager.class.getSimpleName();

    private static Stack<Activity> activityStacks = null;
    private static ContextManager instance;

    private ContextManager() {

    }

    public void init() {
        activityStacks = new Stack<Activity>();
    }

    public static ContextManager getInstance() {
        if (instance == null) {
            instance = new ContextManager();
        }
        return instance;
    }

    public int getActivitySize() {
        return activityStacks.size();
    }

    public void pushActivity(Activity activity) {
        if (activityStacks == null) {
            activityStacks = new Stack<Activity>();
        }
        activityStacks.add(activity);
    }

    /**
     * TODO [添加Activity到栈内]
     */
    public void pushTopActivity(Activity activity) {
        activityStacks.add(activity);
        if (activityStacks.size() > 1) {
            for (int i = 0; i < activityStacks.size(); i++) {
                Activity act = activityStacks.get(0);
                if (act != null) {
                    act.finish();
                    activityStacks.remove(act);
                    act = null;
                }
            }
        }
    }

    /**
     * TODO [获取栈内当前Activity]
     */
    public Activity currActivity() {
        try {
            return activityStacks.lastElement();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    /**
     * TODO [弹出并结束栈内顶端的Activity]
     */
    public void popActivity() {
        if (activityStacks == null || activityStacks.size() == 0) {
            return;
        }
        Activity act = activityStacks.lastElement();
        if (act != null) {
            act.finish();
            act.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            activityStacks.remove(act);
            act = null;
        }
    }

    /**
     * TODO [弹出并结束栈内指定的Activity]
     */
    public void popActivity(Class target) {
        while (true) {
            Activity act = currActivity();
            if (act == null) {
                break;
            }
            if (act.getClass().equals(target)) {
                break;
            }
        }
        popActivity();
    }

    /**
     * TODO [判断当前栈顶Activity是否为当前的activity]
     */
    public void isActivity(Class target) {
        Activity act = currActivity();
        if (act == null) {
            return;
        }
        if (act.getClass().equals(target)) {

            LogUtils.e(TAG, "popActivity" + target.getName());
            popActivity();
        }
    }

    /**
     * TODO [返回操作]
     */
    public void backApp() {
        if (activityStacks.size() > 1) {
            popActivity();
        } else {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.addCategory(Intent.CATEGORY_HOME);
            Activity activity = currActivity();
            if (activity != null) {
                activity.startActivity(home);
            }

        }
    }

    private static Boolean isExit = false;

    /**
     * TODO [返回操作，首页双击退出]
     */
    public void backApp(Boolean doubleClick) {

        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            tExit = new Timer();
            tExit.schedule(new TimerTask() {

                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {

            finishAllActivity();
            //System.exit(0);
        }

    }

    /**
     * TODO [结束栈内所有Activity]
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStacks.size(); i < size; i++) {
            if (null != activityStacks.get(i)) {
                activityStacks.get(i).finish();
            }
        }
        activityStacks.clear();
    }
}
