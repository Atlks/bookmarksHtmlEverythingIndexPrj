/**
 * @author attilax 老哇的爪子
	@since  2014-7-2 下午6:00:39$
 */
package com.attilax.time;

import java.util.*;
import java.net.*;
import java.io.*;
import org.apache.log4j.Logger;
/** @author attilax 老哇的爪子
 * @since 2014-7-2 下午6:00:39$ */
public class atitimeX {
	String atiYear = "";
	// 下午7:23:56 2014-7-2 老哇的爪子 Attilax
	/** @author attilax 老哇的爪子
	 * @since 2014-7-2 下午6:00:39$
	 * 
	 * @param args */
	public static void main(String[] args) {
		// attilax 老哇的爪子 下午6:00:39 2014-7-2
		Calendar cal = Calendar.getInstance();
		cal.set(1, 2014);

		// System.out.println(cal.get( cal.HOUR));
		System.out.println(hour(cal));
		// 2024 y ,2025 z ,2026 1
		// int day = cal.get(Calendar.DATE);
		// int month = cal.get(Calendar.MONTH) + 1;
		// int year = cal.get(Calendar.YEAR);
		// String dateStr = date_ati(cal);
		// String timeStr = time_atiFmt(cal);
		
		 String dateStr = date_ati( );
			 String timeStr = time_atiFmt( );
		System.out.println(dateStr + "_" + timeStr);
		Date date = new Date();

		{}
		{}
		{}

	}
/**
 */
	/**
	 */
	public static String time_atiFmt() {
		
		
		String errmsg = "";
		try {
			Calendar cal = Calendar.getInstance();
			return hour(cal) + "_" + minute(cal) + "_" + sec(cal);
		} catch (Exception e) {
			logger.info(e);
			errmsg = e.getMessage();
		}
		return "time_atiFmt() err..." + errmsg;
	}
	public static String date_ati() {
		
		String errmsg = "";
		try {
			Calendar cal = Calendar.getInstance();
			return year(cal) + month(cal) + day(cal);
		} catch (Exception e) {
			logger.info(e);
			errmsg = e.getMessage();
		}
		return "data_ati() err..." + errmsg;
	
	}

	public static String time_atiFmt(Calendar cal) {
		return hour(cal) + "_" + minute(cal) + "_" + sec(cal);
	}
	public static Logger logger = Logger.getLogger("atitimeLogger");
	public static String date_ati(Calendar cal) {
		String errmsg = "";
		try {
			return year(cal) + month(cal) + day(cal);
		} catch (Exception e) {
			logger.info(e);
			errmsg = e.getMessage();
		}
		return "data_ati() err..." + errmsg;

	}
	/** @author attilax 老哇的爪子
	 * @since 2014-7-2 下午7:11:07$
	 * 
	 * @param cal
	 * @return */
	public static String minute(Calendar cal) {
		// attilax 老哇的爪子 下午7:11:07 2014-7-2
		return getChar_MonuteOrSec(cal.get(Calendar.MINUTE));

	}
	/** @author attilax 老哇的爪子
	 * @since 2014-7-2 下午7:11:01$
	 * 
	 * @param cal
	 * @return */
	public static String sec(Calendar cal) {
		// attilax 老哇的爪子 下午7:11:01 2014-7-2
		return getChar_MonuteOrSec(cal.get(Calendar.SECOND));

	}
	/** @author attilax 老哇的爪子
	 * @since 2014-7-2 下午7:10:58$
	 * 
	 * @param cal
	 * @return */
	public static String hour(Calendar cal) {
		// attilax 老哇的爪子 下午7:10:58 2014-7-2
		int hr = cal.get(Calendar.HOUR);
		if (cal.get(Calendar.AM_PM) == 1) { // 0 am ,1pm
			hr = hr + 12;
			// System.out.println(hr);

		}
		return getChar_litThan36(hr);

	}
	/** @author attilax 老哇的爪子
	 * @since 2014-7-2 下午6:57:15$
	 * 
	 * @param cal
	 * @return */
	public static String day(Calendar cal) {
		// attilax 老哇的爪子 下午6:57:15 2014-7-2
		return getChar_monthOrDay(cal.get(Calendar.DATE));

	}
	/** @author attilax 老哇的爪子
	 * @since 2014-7-2 下午6:57:12$
	 * 
	 * @param cal
	 * @return */
	public static String month(Calendar cal) {
		// attilax 老哇的爪子 下午6:57:12 2014-7-2
		return getChar_monthOrDay(cal.get(Calendar.MONTH) + 1);

	}
	/** @author attilax 老哇的爪子
	 * @since 2014-7-2 下午6:16:36$
	 * 
	 * @param cal
	 * @return */
	public static String year(Calendar cal) {
		// attilax 老哇的爪子 下午6:16:36 2014-7-2
		// Calendar cal=Calendar.getInstance();
		// cal.setTime(date);
		int yr = cal.get(Calendar.YEAR);
		return getChar_year(yr);

	}
	public static String getChar_year(int yr) {
		int span = yr - 1989;

		// if(span>36){

		// if(span==0)span=1;
		// }
		if (span == 10) return "0";

		if (span >= 36) {
			span = span % 36;
			if (span == 0) return "z";
		}

		if (span > 10) return getChar_litThan36(span);
		else return String.valueOf(span);
	}

	public static String getChar_monthOrDay(int monthOrDay) {
		int span = monthOrDay;// - 1989;

		// if(span>36){

		// if(span==0)span=1;
		// }
		if (span == 10) return "0";

		if (span >= 36) {
			span = span % 36;
			if (span == 0) return "z";
		}

		if (span > 10) return getChar_litThan36(span);
		else return String.valueOf(span);
	}

	public static String getChar_bigThan36(int monthOrDay) {
		int span = monthOrDay;// - 1989;

		// if(span>36){

		// if(span==0)span=1;
		// }
		if (span == 10) return "0";

		if (span >= 36) {
			span = span % 36;
			if (span == 0) return "z";
		}

		if (span > 10) return getChar_litThan36(span);
		else return String.valueOf(span);
	}

	public static String getChar_MonuteOrSec(int monthOrDay) {
		int span = monthOrDay;// - 1989;

		// if(span>36){

		// if(span==0)span=1;
		// }
		if (span == 10) return "0";

		if (span > 10 && span < 36) return getChar_litThan36(span);
		if (span == 36) {

			return "z";
		}

		else return String.valueOf(span);
	}
	/** @author attilax 老哇的爪子
	 * @since 2014-7-2 下午6:05:05$
	 * 
	 * @param date
	 * @return */
	@Deprecated public static String year(Date date) {
		// attilax 老哇的爪子 下午6:05:05 2014-7-2
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int span = cal.YEAR - 1989;
		span = span % 36;
		if (span > 10) return getChar_litThan36(span);
		else return String.valueOf(span);

	}
	// attilax 老哇的爪子 下午6:00:39 2014-7-2
	/** @author attilax 老哇的爪子
	 * @since 20{14-7-2 下午6:07:27$
	 * 
	 * @param num
	 * @return */
	public static String getChar_litThan36(int num) {
		// attilax 老哇的爪子 下午6:07:27 2014-7-2
		if (num < 11) return String.valueOf(num);
		int n = 0;
		if (num >= 11) n = num - 11;

		int a_intfmt = 'a';
		int ch_intfmt = a_intfmt + n;
		char ch1 = (char) ch_intfmt;
		return String.valueOf(ch1);

	}
}

// attilax 老哇的爪子