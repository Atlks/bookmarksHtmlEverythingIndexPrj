package com.attilax.Charset;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class reqService {

	public static Map putMapByReqParams(String ps, HttpServletRequest request1) {
		  Map<String,Object> paramMap = new HashMap<String, Object>();
		String[] a=ps.split(",");
		for (String p : a) {
			String parameter = request1.getParameter(p.trim());
			if(parameter!=null)
			paramMap.put(p.trim(), parameter);
		}
		return paramMap;
		
	}

}
