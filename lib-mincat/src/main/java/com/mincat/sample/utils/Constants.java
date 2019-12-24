package com.mincat.sample.utils;

import com.mincat.sample.utils.router.Router;

/**
 * @author Gin
 * @描述 常量类
 */
public class Constants {

    // 禁止创建此类对象
    private Constants() {

    }

    public static final String CONTENT_TYPE_KEY = "Content-Type";
    public static final String CONTENT_TYPE_VALUE = "application/json;charset=UTF-8";

    public static final String TOKEN_KEY = "Authorization";

    public static final String APP_TYPE = "appType";

    public static final String APP_TYPE_VALUE = "0";




    public static final String TAG = "Constants";

    // 内置文件表
    public static final String CONFIG = "config";
    // 用户信息表
    public static final String USERINFO = "userInfo";
    // 空字符串
    public static final String NULL_STRING = "";

    public static final int ZERO = 0;

    public static final int EXIT_CONTINUED = 1500;

    public static final String EXIT = "再按一次退出";

    /**
     * 配置
     */
    public static class Config {

        public static final boolean DEVELOPER_MODE = false;
    }

    /**
     * 正则表达式：验证用户名
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";

    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])";

    /**
     * 正则表达式：验证URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";


    public static final String FILE_DIR_NAME = "thumb";

    public static int ROUTER_ANIM_ENTER = Router.RES_NONE;

    public static int ROUTER_ANIM_EXIT = Router.RES_NONE;


    public static final String TEST_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpbk5hbWUiOiIxMTA1IiwiZXhwIjoxNTc4NTQ5OTczfQ.bCwe39W1a12PF3T3bObmQjZkzSYjIu6P7p32R0k0F1k";

}
