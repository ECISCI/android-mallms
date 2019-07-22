package com.mincat.sample.utils;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Gin
 * @描述 字符串操作工具类
 */
public class StrUtils {

    // 禁止创建此类对象
    private StrUtils() {
    }


    // 判断一个字符串是否都为数字
    public static boolean isDigit(String strNum) {
        Pattern pattern = Pattern.compile("[0-9]{1,}");
        Matcher matcher = pattern.matcher((CharSequence) strNum);
        return matcher.matches();
    }

    // 截取数字
    public static String getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    // 截取非数字
    public static String splitNotNumber(String content) {
        Pattern pattern = Pattern.compile("\\D+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    // 判断一个字符串是否含有数字

    public static boolean hasDigit(String str) {

        boolean flag = false;

        Pattern p = Pattern.compile(".*\\d+.*");

        Matcher m = p.matcher(str);

        if (m.matches())

            flag = true;

        return flag;

    }

    /**
     * 判断字符串是否为数字
     *
     * @param str 传入的字符串
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 将字符串变为UrlEncode UTF-8
     */
    public static String getURLEncoder(String sr) throws Exception {

        String encoder = URLEncoder.encode(sr, "UTF-8");

        return encoder;

    }
}
