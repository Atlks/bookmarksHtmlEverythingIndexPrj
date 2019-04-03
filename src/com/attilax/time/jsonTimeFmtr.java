package com.attilax.time;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;import java.util.Date;

import com.attilax.core;
import com.attilax.util.DateUtil;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

	 public class jsonTimeFmtr implements JsonValueProcessor {
		 
		 public static void main(String[] args) {
			 System.out.println( DateUtil.getDatetime());
			
		}
		// 参数1 ：属性名 参数2：json对象的值 参数3：jsonConfig对象
		public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Timestamp stp = (Timestamp) arg1;
				Date d = (Date) stp;
				return sdf.format(d);
			} catch (Exception e) {
				return DateUtil.getDatetime();
			}
		//	return "..";
		}
		public Object processArrayValue(Object value, JsonConfig arg1) {
			String[] obj = {};
			try {
				 
			        if (value instanceof java.sql.Date[]) {
			            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			            Date[] dates = (Date[]) value;
			            obj = new String[dates.length];
			            for (int i = 0; i < dates.length; i++) {
			                obj[i] = sf.format(dates[i]);
			            }
			        }
			      
			} catch (Exception e) {
				 core.err(e);
			}
			  return obj;
			
		}
		/**
		@author attilax 老哇的爪子
			@since  o8k g_53_x   
		
		 * @return
		 */
		public static JsonConfig jsonConfig() {
			// attilax 老哇的爪子  g_53_x   o8k 
			JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件
			jsonConfig.registerJsonValueProcessor(Timestamp.class, new jsonTimeFmtr());
			return jsonConfig;
			
		}
	}