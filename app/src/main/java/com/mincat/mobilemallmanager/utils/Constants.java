package com.mincat.mobilemallmanager.utils;

public class Constants {

    /**
     * SpringBoot请求基础URL
     */
    public static final String BASE_URL = "http://10.0.2.2:8088/api/mobile/";

    /**
     * 用户登录
     */
    public static final String LOGIN = BASE_URL + "login";
    public static final String LOGIN_SIGN = "login_sign";


    /**
     * 用户注册
     */
    public static final String REGISTER_USER = BASE_URL + "register";
    public static final String REGISTER_USER_SIGN = "register_user_sign";
    public static final String R_EXIST = "用户已存在";

    /**
     * 获取首页轮播图
     */
    public static final String MAIN_LUNBO_PICS = BASE_URL + "pics/mainHomeLunBoPics";
    public static final String MAIN_LUNBO_PICS_SIGN = "mainLunBoPics";

    /**
     * 空串
     */
    public static final String NULL_STRING = "";

    /**
     * 登录请求标记
     */
    public static final String SIGN_LOGIN = "SIGN_LOGIN";


    public static final String CONTENT_TYPE_KEY = "Content-Type";
    public static final String CONTENT_TYPE_VALUE = "application/json;charset=UTF-8";

    public static final String TOKEN_KEY = "Authorization";

    public static final String APP_TYPE = "appType";
    public static final String APP_TYPE_VALUE = "0";


    public static final String OPERATION_SUCCESS="操作成功";

    public static final  int REQUEST_SUCCESS_CODE = 0;
    public static final  int AUTH_CODE = 20001;

    public static final String REQUEST_T_FAILED = "请求失败...请稍后再试";


    public static final String TEST_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpbk5hbWUiOiIxMTA1IiwiZXhwIjoxNTc4NTQ5OTczfQ.bCwe39W1a12PF3T3bObmQjZkzSYjIu6P7p32R0k0F1k";


}
