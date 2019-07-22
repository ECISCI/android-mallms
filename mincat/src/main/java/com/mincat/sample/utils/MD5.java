package com.mincat.sample.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Gin
 *
 * @描述 MD5工具类
 */
public class MD5 {

    private static final String TAG = MD5.class.getName();

    public static String getMd5(String str) throws NoSuchAlgorithmException {

        //得到一个信息摘要器
        MessageDigest messageDigest = null;

        messageDigest = MessageDigest.getInstance("md5");

        StringBuffer sb = new StringBuffer();

        byte[] result = messageDigest.digest(str.getBytes());

        //把byte数组做一个与运算 0xff
        for (byte b : result) {

            int number = b & 0xfff;//不是标准的Md5加密 在密码学上叫做加盐

            String md5String = Integer.toHexString(number);

            if (md5String.length() == 1) {
                sb.append("0");
            }
            sb.append(md5String);
        }

        //标准MD5加密后的结果
        LogUtils.i(TAG, sb.toString());

        return sb.toString();
    }
}
