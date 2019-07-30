package com.mincat.sample.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Gin
 *
 * @描述 验证是否是手机号码
 */
public class VerifyPhone {

	public static boolean verifyPhone(String phone) {

		boolean flag = false;

		try {
			Pattern regex = Pattern
					.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
			Matcher matcher = regex.matcher(phone);

			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}

		return flag;

	}

}
