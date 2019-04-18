package aOPtool;

import java.util.Map;

import com.attilax.util.ExceptionAti;
import com.google.common.collect.Maps;

public class exTest {

	public static void main(String[] args) {
		Map map=Maps.newLinkedHashMap();map.put("kkk", "vv");
		
		ExceptionAti exceptionAti=new ExceptionAti();
		exceptionAti.backtrace=map;
		throw exceptionAti;

//		try {
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	
	}

}
