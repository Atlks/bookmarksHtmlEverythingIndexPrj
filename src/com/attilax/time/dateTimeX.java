/**
 * @author attilax 老哇的爪子
	@since  o7k ipf$
 */
package com.attilax.time;
import com.attilax.core;
import com.attilax.util.DateUtil;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o7k ipf$
 */
public class dateTimeX {

	/**
	@author attilax 老哇的爪子
		@since  o7k ipf$
	
	 * @param args
	 */
    public static void main(String[] args) throws Exception {  
    
    	String d1="1582-10-09"; //-12219094800000 ,-12219008400000   -12218922000000
    	//10-07>>>10-09  
    	Date d=DateUtil.str2date(d1, false);
    	System.out.println(d.getTime());
//    	String d2="1582-10-15";
//    int span=	DateUtil.getDayInterval(,DateUtil.str2date(d2, false));
//    	System.out.println(span);
        Date date =DateUtil. getDateFrmNet();
   //     TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        System.out.println(DateUtil.toStandFmtYYYYmmDD_hhmmss_byDate_CST(date));
        //分别取得时间中的小时，分钟和秒，并输出  
    //    System.out.print(date.getHours()+"时"+date.getMinutes()+"分"+date.getSeconds()+"秒");  
        
        setSystime(date);
  }  
	//  attilax 老哇的爪子 ipf   o7k   

 

	/**
	@author attilax 老哇的爪子
		@since  o7k j40v$
	
	 * @param date
	 */
	public static void setSystime(Date date) {
		// attilax 老哇的爪子  j40v   o7k 
		 //Operating system name  
		String osName = System.getProperty("os.name");  
//	 TimeZone.setDefault();
		String s=DateUtil.getDatetime(date);
		String[] a=s.split(" ");
		String dt=a[0];String tm=a[1];
		
		
		String cmd = "";  
		try {  
		    if (osName.matches("^(?i)Windows.*$")) {// Window 系统  
		    // 格式 HH:mm:ss  
		    cmd = "  cmd /c time "+tm;  
		    Runtime.getRuntime().exec(cmd);  
		    // 格式：yyyy-MM-dd  
		    cmd = " cmd /c date "+dt;  
		    Runtime.getRuntime().exec(cmd);  
		} else {// Linux 系统  
		   // 格式：yyyyMMdd  
		   cmd = "  date -s 20090326";  
		   Runtime.getRuntime().exec(cmd);  
		   // 格式 HH:mm:ss  
		   cmd = "  date -s 22:35:00";  
		   Runtime.getRuntime().exec(cmd);  
		}  
		} catch (IOException e) {  
		    e.printStackTrace();  
		}  
		
	}
}

//  attilax 老哇的爪子