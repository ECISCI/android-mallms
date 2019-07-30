package com.mincat.sample.manager.inter;

import com.android.volley.VolleyError;

/**
 * @author Gin
 */
public interface MinCatVolleyAllListener {

    /**
     * 请求成功回调 POST
     *
     * @param response 返回结果
     * @param sign     请求标记
     */
    void onHandleResponsePost(String response, String sign);


    /**
     * 请求成功回调函数 Get
     *
     * @param response 返回结果
     * @param sign     请求标记
     */
    void onHandleResponseGet(String response, String sign);

    /**
     * 请求失败回调
     *
     * @param error 错误信息
     * @param sign  请求标记
     */
    void errorListener(VolleyError error, String sign);
}
