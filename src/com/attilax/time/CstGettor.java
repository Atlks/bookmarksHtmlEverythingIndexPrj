package com.attilax.time;

import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

public class CstGettor {

	public static void main(String[] args) throws ParseException {
//		Date dt = getDateFrmNet();
//		System.out.println(dt);
//		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
//
//		System.out.println(DateUtil.toStandFmtYYYYmmDD_hhmmss_byDate(dt));
	}

	public static Date getDateFrmNet() {
		String urlStr = "http://www.bjtime.cn";
		Date d;
		try {
			d = _getDateFrmNet(urlStr);
		} catch (Exception e) {
			e.printStackTrace();
			d = getDateFrmNetByByedu();
		}
		return d;

	}

	public static Date _getDateFrmNet(String urlStr) {
		URL url;
		URLConnection uc = null;
		try {
			// String urlStr = "http://www.bjtime.cn";
			url = new URL(urlStr);
			uc = url.openConnection();

			uc.connect();
		} catch (Exception e1) {
			// attilax 老哇的爪子 l4737 o7k
			throw new RuntimeException(e1);
		}// 取得资源对象

		// 发出连接
		long ld = uc.getDate(); // 取得网站日期时间（时间戳）
		Date date = new Date(ld); // 转换为标准时间对象
		return date;
	}

	public static Date getDateFrmNetByByedu() {
		URL url;
		URLConnection uc = null;
		try {
			url = new URL("http://www.baidu.com");
			uc = url.openConnection();

			uc.connect();
		} catch (Exception e1) {
			// attilax 老哇的爪子 l4737 o7k
			throw new RuntimeException(e1);
		}// 取得资源对象

		// 发出连接
		long ld = uc.getDate(); // 取得网站日期时间（时间戳）
		Date date = new Date(ld); // 转换为标准时间对象
		return date;
	}

}
