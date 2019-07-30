package com.mincat.sample.manager.inter;

import com.android.volley.VolleyError;

/**
 * @author Gin
 * @描述 Volley Get 请求监听
 */

public interface MinCatVolleyGetListener {

    /**
     * 请求成功回调
     *
     * @param response 返回结果
     * @param sign     请求标记
     */
    void onHandleResponsePost(String response, String sign);

    /**
     * 请求失败回调
     *
     * @param error 错误信息
     * @param sign  请求标记
     */
    void errorListener(VolleyError error, String sign);

}
