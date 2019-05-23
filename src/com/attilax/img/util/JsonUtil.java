package com.attilax.img.util;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.attilax.img.HSV;

public class JsonUtil {
	
	public static void main(String[] args) {
		
		HSV h=new HSV();
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(  "s");
		System.out.println(	com.alibaba.fastjson.JSON.toJSONString(h, filter,new SerializerFeature[]{SerializerFeature.PrettyFormat}));
	//	com.alibaba.fastjson.JSON.toJSONString(li, true);
	}

}
