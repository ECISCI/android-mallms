package com.mincat.sample.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Gin
 * @描述 获取当前系统时间
 */
public class GetSystemTime {

	private static SimpleDateFormat formatter;

	private static Date curDate;

	private static String time;

	// 构造函数私有化 不允许创建对象
	private GetSystemTime() {

	}

	// 获取当前系统时间
	// "yyyy-MM-dd HH:mm:ss"
	public static String getSystemTime( String dateFormat) {

		if (!dateFormat.contains("yyyy") && !dateFormat.contains("MM")
				&& !dateFormat.contains("dd") && !dateFormat.contains("HH")
				&& !dateFormat.contains("mm") && !dateFormat.contains("ss")) {

			throw new IllegalArgumentException("date format error");

		}
		formatter = new SimpleDateFormat(dateFormat);

		curDate = new Date(System.currentTimeMillis());

		time = formatter.format(curDate);

		return time;
	}
}
