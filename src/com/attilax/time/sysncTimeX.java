/**
 * @author attilax 老哇的爪子
	@since  o7k ml38$
 */
package com.attilax.time;
import com.attilax.core;
import com.attilax.spri.SpringUtil;
import com.attilax.util.DateUtil;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
 * @author  attilax 老哇的爪子
 *@since  o7k ml38$
 */@Component  
public class sysncTimeX {
	 
		public static Logger logger = Logger.getLogger("AtiTimerSyncer");
	/**
	@author attilax 老哇的爪子
		@since  o7k ml38$
	
	 * @param args
	 */ 
	public static void main(String[] args) {
		// attilax 老哇的爪子  ml38   o7k 
		//   sync();
		System.out.println(SpringUtil.getBean("sysncTimeX")); 

	}
	//  attilax 老哇的爪子 ml38   o7k   
	 @Scheduled(cron="0 0 8 * * ?")  
	private static void sync() {
		Date date =DateUtil. getDateFrmNet();
		 dateTimeX.  setSystime(date);
		 logger.info("--o7k1 sync time:ok");
	}
}

//  attilax 老哇的爪子