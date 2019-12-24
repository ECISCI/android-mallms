package com.mincat.mobilemallmanager.utils;

import android.os.Handler;

public class HandleUtils {

    public static void sendHandle(Handler handler, int flag, Object object) {

        handler.obtainMessage(flag, object).sendToTarget();

    }
}
