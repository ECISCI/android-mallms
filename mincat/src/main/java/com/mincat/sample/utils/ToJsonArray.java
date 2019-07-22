package com.mincat.sample.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Michael
 *
 * @描述 Json转化工具类
 */
public class ToJsonArray {

    private static String param;

    public static final String TAG = ToJsonArray.class.getName();
    private static JSONObject jsonObject;

    private ToJsonArray() {

    }

    // 键值对一定要一一对应
    public static String toJsonArray(String[] paramKey, Object[] paramValue) {
        if (paramKey.length == 0 ||paramValue.length==0) {
            throw new IllegalArgumentException("array must contain elements");
        }

        if (paramKey.length!=paramValue.length){

            throw new IllegalArgumentException("传入数组长度不同");
        }

        jsonObject = new JSONObject();

        for (int i = 0; i < paramKey.length; i++) {
            jsonObject.put(paramKey[i], paramValue[i]);
        }
        param = jsonObject.toJSONString();
        LogUtils.i(TAG, "json array type" + param);
        return param;
    }

}
