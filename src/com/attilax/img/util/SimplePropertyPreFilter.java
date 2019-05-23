package com.attilax.img.util;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.attilax.img.HSV;

public class SimplePropertyPreFilter implements PropertyPreFilter {      
 

 
	@Override  
	//rt false   //not show
	public boolean apply(JSONSerializer arg0, Object obj, String prop) {
		 if(prop.equals("h"))
			 	return false;
	 	else
			return true; 
	}
	
	public static void main(String[] args) {
		
		HSV h=new HSV();
		 
		System.out.println(	com.alibaba.fastjson.JSON.toJSONString(h,  new PropertyPreFilter() {
			
			@Override
			public boolean apply(JSONSerializer arg0, Object arg1, String prop) {
				////rt false   //not show
				 if(prop.equals("h"))
					 	return false;
			 	else
					return true; 
			}
		} ,new SerializerFeature[]{SerializerFeature.PrettyFormat}));
	//	com.alibaba.fastjson.JSON.toJSONString(li, true);
	}


    //...
 
}