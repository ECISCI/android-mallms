package com.mincat.sample.imagecache.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Gin
 * @描述 对图片url地址进行MD5加密, 保证其命名是唯一的
 */

public class ImageUrlMd5 {

    // 禁止创建本类对象
    private ImageUrlMd5() {
    }

    // 此处对url进行MD5加密
    private static String cacheKey;

    public static String hashKeyForDisk(String key) {

        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

}
