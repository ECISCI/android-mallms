package com.mincat.sample.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Wifi 网络状态
 *
 * @author Gin
 */
public class WifiUtils {

    private WifiUtils() {
    }

    /**
     * 判断wifi 是否可用
     *
     * @param context 上下文对象
     * @return Wifi是否可用的 boolean值
     * @throws Exception 使用需要捕获异常
     */
    @SuppressWarnings("deprecation")
    public static boolean isWifiDataEnable(Context context) throws Exception {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiDataEnable = false;
        isWifiDataEnable = connectivityManager.getNetworkInfo(
                ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        return isWifiDataEnable;
    }

}
