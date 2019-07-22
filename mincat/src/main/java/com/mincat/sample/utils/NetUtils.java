package com.mincat.sample.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;


/**
 * @author Gin
 * @描述 判断网络活动状态
 */
public class NetUtils {

    public static final String TAG = NetUtils.class.getSimpleName();

    //禁止创建对象
    private NetUtils() {

    }

    //检查网络是否连接
    public static boolean checkNet(Context context) {

        try {
            if (context == null)
                return false;

            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            if (connectivity != null) {
                NetworkInfo info = connectivity.getActiveNetworkInfo();

                if (info != null && info.isConnected()) {

                    if (info.getState() == NetworkInfo.State.CONNECTED) {

                        return true;
                    }
                }
            }
        } catch (Exception e) {

            Log.e(TAG, "错误信息:" + e);
        }
        return false;
    }

    //检查网络是否可用
    public static boolean isNetWorkConnected(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = connectivity.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();

    }

    //获取当前网络类型
    public static int getNetWorkType(Context context) {
        final int NETTYPE_WIFI = 0x01;
        final int NETTYPE_CMWAP = 0x02;
        final int NETTYPE_CMNET = 0x03;

        int netType = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
            if (!TextUtils.isEmpty(extraInfo)) {
                if (extraInfo.toLowerCase().equals("cmnet")) {
                    netType = NETTYPE_CMNET;
                } else {
                    netType = NETTYPE_CMWAP;
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NETTYPE_WIFI;
        }
        return netType;

    }

    //获取当前网络所处的类型
    public static String getCurrentNetType(Context context) {
        try {
            if (context == null)
                return null;
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            if (cm != null) {
                NetworkInfo info = cm.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    int type = info.getType();

                    // 手机网络 3G/EDGE
                    if (type == ConnectivityManager.TYPE_MOBILE) {
                        return "当前网络：3G/EDGE";
                    }

                    // WIFI网络
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        return "当前网络：WIFI";
                    }
                }
            }
        } catch (Exception e) {

            LogUtils.e(TAG, "错误信息：" + e);
        }
        return null;
    }

    /**
     * 获取活动的网络信息
     * <p>
     * ActiveInfo可以判断网络的有无，如果返回的是null，这时候是断网了，如果返回对象不为空，则是连上了网。
     * <p>
     * 在返回的NetworkInfo对象里，可以有对象的方法获取更多的当前网络信息，比如是wifi还是cmwap等
     *
     * @param context
     * @return
     */
    public static NetworkInfo getActiveNetwork(Context context) {
        try {
            if (context == null)
                return null;
            ConnectivityManager mConnMgr = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (mConnMgr == null)
                return null;
            NetworkInfo aActiveInfo = mConnMgr.getActiveNetworkInfo();
            // 获取活动网络连接信息
            return aActiveInfo;
        } catch (Exception e) {
            LogUtils.e(TAG, "错误信息:" + e);
        }
        return null;
    }

    // 判断网络连接状态
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    // 判断wifi状态
    @SuppressWarnings("deprecation")
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    // 判断移动网络
    @SuppressWarnings("deprecation")
    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    // 获取连接类型
    public static int getConnectedType(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }

    @SuppressWarnings("deprecation")
    public static boolean isWifiConnect(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = mConnectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return mWifi.isConnected();
    }

    @SuppressWarnings("deprecation")
    public static boolean isMobileConnected1(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity) {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings",
                "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }
}
