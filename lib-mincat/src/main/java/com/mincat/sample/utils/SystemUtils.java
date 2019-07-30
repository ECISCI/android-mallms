/*******************************************************************
 * Copyright (C) 2013 Neusoft Group Ltd. All rights reserved.
 *
 * @fileName:SystemUtils.java
 * @author:zhaoyong.neu
 * @version:v1.0.0
 * Modification History:
 * Date           Author           Version      Description
 * -----------------------------------------------------------------
 * 2013-9-11     zhaoyong.neu     v1.0.0       create
 *
 *******************************************************************/
package com.mincat.sample.utils;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.mincat.sample.app.MinCatApp;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.util.List;
import java.util.UUID;

/**
 * @author Gin
 */
public class SystemUtils {

    /**
     * 内存下限值
     */
    private static final long MIN_AVAIL_MEMORY = 204800;
    private static final String tag = "SystemUtils";

    public static String getSysId(Context context) {
        String tmDevice, tmSerial, tmPhone, androidId, mac;
        try {
            NetworkInterface networkInterface = NetworkInterface.getByName("wlan0");
            byte[] mac1 = networkInterface.getHardwareAddress();
            tmDevice = byte2hex(mac1);
        } catch (Exception e) {
            tmDevice = "";
        }
        androidId = ""
                + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32));
        String uniqueId = deviceUuid.toString();
        LogUtils.d("debug", "uuid=" + uniqueId);
        return uniqueId;
    }

    public static String byte2hex(byte[] b) {
        StringBuffer hs = new StringBuffer(b.length);
        String stmp = "";
        int len = b.length;
        for (int n = 0; n < len; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            if (stmp.length() == 1) {
                hs = hs.append("0").append(stmp);
            } else {
                hs = hs.append(stmp);
            }
            if (n != len - 1) {
                hs.append(":");
            }
        }
        return String.valueOf(hs);
    }

    public static String getPhoneMac(Context context) {
        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        return wm.getConnectionInfo().getMacAddress();
    }

    public static String getMacFromWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        State wifiState = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        if (wifiState == State.CONNECTED) {//判断当前是否使用wifi连接
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            if (!wifiManager.isWifiEnabled()) { //如果当前wifi不可用
                wifiManager.setWifiEnabled(true);
            }
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            return wifiInfo.getMacAddress();
        }
        return "";
    }

    /**
     * 获取当前手机号码
     */
    public static String getPhoneNum(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return tm.getLine1Number() == null ? "" : tm.getLine1Number();
            }
            return Constants.NULL_STRING;
        } catch (Exception e) {

        }
        return Constants.NULL_STRING;
    }

    /**
     * 获取当前手机机型
     */
    public static String getPhoneModel() {
        return Build.MODEL;
    }

    /**
     * 获取当前手机品牌
     */
    public static String getPhoneBrand() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取当前手机分辨率
     */
    public static String getPhoneResolution(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metric);
        int width = Math.min(metric.widthPixels, metric.heightPixels);
        int height = Math.max(metric.widthPixels, metric.heightPixels);
        return width + "×" + height;
    }

    /**
     * 获取当前手机分辨率
     */
    public static int[] getResolution(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metric);
        int[] res = new int[2];
        res[0] = Math.min(metric.widthPixels, metric.heightPixels);
        res[1] = Math.max(metric.widthPixels, metric.heightPixels);

        return res;
    }

    /**
     * 获取当前运营商
     */
    public static String getPhoneMobile(Context context) {
        String ProvidersName = "none";
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            // 返回唯一的用户ID;就是这张卡的编号神马的
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                String IMSI = tm.getSubscriberId();
                // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
                if (IMSI != null) {
                    if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
                        ProvidersName = "中国移动";
                    } else if (IMSI.startsWith("46001")) {
                        ProvidersName = "中国联通";
                    } else if (IMSI.startsWith("46003")) {
                        ProvidersName = "中国电信";
                    }
                }
                return ProvidersName;
            }

        } catch (Exception e) {
        }
        return ProvidersName;
    }

    /**
     * 获取当前系统版本
     *
     * @return
     */
    public static String getOSVers() {
        return Build.VERSION.SDK_INT + "";
    }

    /**
     * TODO [获取SIM卡的IMSI码]
     *
     * @param context 应用上下文
     */

    public static String getIMSI(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return tm.getSubscriberId() == null ? "" : tm.getSubscriberId();
            }

        } catch (Exception e) {
            return Constants.NULL_STRING;

        }
        return Constants.NULL_STRING;

    }

    /**
     * TODO [获取IMEI码]
     */
    public static String getIMEI(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return tm.getDeviceId() == null ? "" : tm.getDeviceId();
            }

        } catch (Exception e) {
            return Constants.NULL_STRING;

        }
        return Constants.NULL_STRING;
    }

    /**
     * TODO [获取剩余内存大小]
     */
    public static long getAvailMemory(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        MemoryInfo mi = new MemoryInfo();
        am.getMemoryInfo(mi);
        return mi.availMem;
    }

    /**
     * TODO [是否有足够内存]
     */
    public static Boolean isEnoughMemory(Context context) {
        return getAvailMemory(context) > MIN_AVAIL_MEMORY;
    }

    /**
     * TODO [内存是否高于制定内存数量]
     *
     * @param context   上下文
     * @param minMemory 最小内存值
     */
    public static Boolean isEnoughMemory(Context context, int minMemory) {
        return getAvailMemory(context) > minMemory;
    }

    /**
     * TODO [是否装载外围存储设备SD卡]
     */
    public static final Boolean isHasExternalStorage() {
        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * TODO [获取内部存储空间大小]
     *
     * @return long 防止计算溢出
     */
    public static final long getAvailableInternalMemorySize() {
        File file = Environment.getDataDirectory(); // 获取内部存储目录
        StatFs sf = new StatFs(file.getPath());
        long blockSize = sf.getBlockSize(); // 文件系统数量
        long availableBlocks = sf.getAvailableBlocks(); // 可使用空间大小
        return blockSize * availableBlocks;
    }

    /**
     * TODO [获取外部存储可用空间]
     */
    public static final long getAvailableExternalMemorySize() {
        if (isHasExternalStorage()) {
            File file = Environment.getExternalStorageDirectory();
            StatFs sf = new StatFs(file.getPath());
            long blockSize = sf.getBlockSize(); // 文件系统数量
            long availableBlocks = sf.getAvailableBlocks(); // 可使用空间大小
            return blockSize * availableBlocks;
        } else {
            return -1;
        }
    }

    /**
     * TODO [分辨率从dp转为px]
     *
     * @param context 上下文
     * @param dpVal   dp值
     */
    public static int dip2px(Context context, float dpVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpVal * scale + 0.5f);
    }

    /**
     * TODO [分辨率从px转为dp]
     *
     * @param context 上下文
     *                px值
     */
    public static int px2dip(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxVal / scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * TODO [通过包获取当前应用的VersionName]
     *
     * @param context 上下文
     */
    public static String getVersionName(Context context) {
        String versionName = "";
        String packageName = context.getPackageName();
        try {
            String versionAllName = context.getPackageManager().getPackageInfo(packageName, 0).versionName;
            String[] versionArr = versionAllName.split("\\.");
            versionName = versionArr[0] + "." + versionArr[1] + "." + versionArr[2];
        } catch (NameNotFoundException e) {
            // 暂未做抛出异常
        }
        return versionName;
    }

    public static Drawable getAppIcon(Context context) {
        Drawable appIcon = null;
        String packageName = context.getPackageName();
        try {
            appIcon = context.getPackageManager().getPackageInfo(packageName, 0).applicationInfo.loadIcon(context.getPackageManager());
        } catch (NameNotFoundException e) {
            // 暂未做抛出异常
        }
        return appIcon;
    }

    /**
     * TODO [通过包获取当前应用的版本VersionCode]
     *
     * @param context 上下文
     */
    public static int getVersionCode(Context context) {
        int versionCode = -1;
        String packageName = context.getPackageName();
        try {
            versionCode = context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
        } catch (NameNotFoundException e) {
            // 暂未做抛出异常
        }
        return versionCode;
    }

    /**
     * TODO [通过资源获取当前应用的VersionCode]
     *
     * @param context 上下文
     * @param resId   资源Id，AndroidManifest.xml引用的资源
     */
    public static int getVersionCode(Context context, int resId) {
        String versionCode = context.getResources().getText(resId).toString();
        return Integer.parseInt(versionCode);
    }

    /**
     * TODO [通过资源获取当前应用的VersionName]
     *
     * @param context 上下文
     * @param resId   资源Id，AndroidManifest.xml引用的资源
     */
    public static String getVersionName(Context context, int resId) {
        String versionName = context.getResources().getText(resId).toString();
        return versionName;
    }

    /**
     * TODO [通过资源获取当前应用的Application名称]
     *
     * @param context 上下文
     * @param resId   资源Id，AndroidManifest.xml引用的资源
     */
    public static String getAPPName(Context context, int resId) {
        String appName = context.getResources().getText(resId).toString();
        return appName;
    }

    /**
     * TODO [通过资源获取当前应用的Apk图标]
     *
     * @描述 资源Id，AndroidManifest.xml引用的资源
     */
    public static Drawable getAPPIcon(Context context, int resId) {
        Drawable appIcon = context.getResources().getDrawable(resId);
        return appIcon;
    }

    /**
     * @描述 检查是否开启GPS，需要android.permission.ACCESS_FINE_LOCATION权限
     */
    public static boolean checkGPS(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * 是否安装对应包名应用
     */
    public static boolean isInstall(Context context, String packageName) {
        List localList = context.getPackageManager().getInstalledPackages(0);
        boolean bool = false;
        for (int i = 0; i < localList.size(); i++) {
            PackageInfo localPackageInfo = (PackageInfo) localList.get(i);
            if (packageName.equals(localPackageInfo.packageName)) {
                bool = true;
                return bool;
            }
        }
        return bool;
    }

    /**
     * 获取对应包名的应用版本号
     */
    public static int getVersionCode(Context context, String packageName) {
        List localList = context.getPackageManager().getInstalledPackages(0);
        int code = 0;
        for (int i = 0; i < localList.size(); i++) {
            PackageInfo localPackageInfo = (PackageInfo) localList.get(i);
            if (packageName.equals(localPackageInfo.packageName)) {
                code = localPackageInfo.versionCode;
                return code;
            }
        }
        return code;
    }

    /**
     * 获取数据网络状态
     */
    public static Boolean getIsMobile(Context context) {
        ConnectivityManager mConn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Object[] arg = null;
        Boolean isMobileDataEnabled;
        try {
            isMobileDataEnabled = invokeMethod(context, "getMobileDataEnabled", arg);
            return isMobileDataEnabled;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            LogUtils.e(tag, e.toString());
            return false;
        }

    }

    /**
     * 判断wifi连接状态
     */
    public static boolean getIsWifi(Context context) {
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        return State.CONNECTED == wifi;
    }

    /**
     * 设置数据网络开关
     */
    public void setMobileDataEnabled(Context context, Boolean isOpen) {
        ConnectivityManager mConn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Object[] arg = null;
        try {
            invokeBooleanArgMethod(context, "setMoblieDataEnabled", isOpen);
        } catch (Exception e) {
            LogUtils.e(tag, e.toString());
        }
    }

    private static boolean invokeMethod(Context context, String methodName, Object[] arg) throws Exception {
        ConnectivityManager mConn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Class ownerClass = mConn.getClass();
        Class[] argsClass = null;
        if (arg != null) {
            argsClass = new Class[1];
            argsClass[0] = arg.getClass();
        }
        Method method = ownerClass.getMethod(methodName, argsClass);
        Boolean isOpen = (Boolean) method.invoke(mConn, arg);
        return isOpen;

    }

    private static Object invokeBooleanArgMethod(Context context, String methodName, boolean value) throws Exception {
        ConnectivityManager mConn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Class ownerClass = mConn.getClass();
        Class[] argsClass = new Class[1];
        argsClass[0] = boolean.class;
        Method method = ownerClass.getMethod(methodName, argsClass);
        return method.invoke(mConn, value);

    }

    public static String getApplicationMeta(Context context, String key) {
        String meta = "";
        try {
            ApplicationInfo appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            meta = appInfo.metaData.getString(key);
        } catch (NameNotFoundException e) {
            LogUtils.e(tag, e.toString());
        }

        return meta;
    }

    public static String getAppChannel(Context context) {
        String meta = "00";
        try {
            ApplicationInfo appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            meta = appInfo.metaData.getString("UMENG_CHANNEL");
        } catch (NameNotFoundException e) {
            LogUtils.e(tag, e.toString());
        }

        return meta;
    }

    /**
     * @描述 判断当前应用程序处于前台还是后台
     */
    public static boolean isApplicationBroughtToBackground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;

    }

    /**
     * 程序是否在前台运行
     */
    public static boolean isAppOnForeground(Context mContext) {
        ActivityManager activityManager = (ActivityManager) mContext.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = mContext.getApplicationContext().getPackageName();
        List<RunningAppProcessInfo> appProcess = activityManager.getRunningAppProcesses();
        if (appProcess == null) {
            return false;
        } else {
            for (RunningAppProcessInfo appProcessInfo : appProcess) {
                if (appProcessInfo.processName.equals(packageName)
                        && appProcessInfo.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获得设备的屏幕高度
     */
    public static int getDeviceHeight(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return manager.getDefaultDisplay().getHeight();
    }

    /**
     * 获得设备的屏幕高度
     *
     * @param context
     * @return
     */
    public static int getDeviceWidth(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return manager.getDefaultDisplay().getWidth();
    }

    /**
     * 获取状态栏高度＋标题栏高度
     */
    public static int getTopBarHeight(Activity activity) {
        return activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
    }

    /**
     * @描述 通过反射获取状态栏高度
     */
    public static int getStatusBarHeight(Context mContext) {

        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = mContext.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }

    /**
     * 判断某个服务是否正在运行的方法
     *
     * @param mContext
     * @param serviceName 是包名+服务的类名（例如：net.loonggg.testbackstage.TestService）
     * @return true代表正在运行，false代表服务没有正在运行
     */
    public static boolean isServiceWork(Context mContext, String serviceName) {
        boolean isWork = false;
        ActivityManager myAM = (ActivityManager) mContext
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningServiceInfo> myList = myAM.getRunningServices(40);
        if (myList.size() <= 0) {
            return false;
        }
        for (int i = 0; i < myList.size(); i++) {
            String mName = myList.get(i).service.getClassName().toString();
            if (mName.equals(serviceName)) {
                isWork = true;
                break;
            }
        }
        return isWork;
    }

    public static void onClickCopy(String content) {

        // 从API11开始android推荐使用android.content.ClipboardManager
        // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ClipboardManager cm = (ClipboardManager) MinCatApp.getInstance().getSystemService(Context.CLIPBOARD_SERVICE);
            // 将文本内容放到系统剪贴板里。
            cm.setText(content);
            Toast.makeText(MinCatApp.getInstance().getApplicationContext(), "复制成功，可以发给朋友们了。", Toast.LENGTH_SHORT).show();
        } else {
        }

    }

    /**
     * @描述 关闭键盘
     */
    public static void closeKeyBroad(Activity mContext) {
        InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (mContext.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(mContext.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
