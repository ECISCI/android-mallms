package com.mincat.mobilemallmanager.utils;

public class Constants {

    /**
     * SpringBoot请求基础URL
     */
    public static final String BASE_URL = "http://10.0.2.2:8088/";

    /**
     * 用户登录标记
     */
    public static final String LOGIN = BASE_URL + "login";


    /**
     * 空串
     */
    public static final String NULL_STRING = "";

    /**
     * 登录请求标记
     */
    public static final String SIGN_LOGIN = "SIGN_LOGIN";

    /**
     * 获取商品分类列表
     */
    public static final String GET_GOODS_CATEGORY_LIST = BASE_URL + "getGoodsCategoryList";

    /**
     * 商品分类列表请求标记
     */
    public static final String GOODS_CATEGORY_SIGN = "GOODS_CATEGORY_SIGN";
}
