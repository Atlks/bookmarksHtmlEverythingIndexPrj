package com.attilax.util;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class PrettyUtil {

	//java 序列化 文本 查看
	public static String showListMap(List<Map> tab) {
		
		List<String> list=Lists.newArrayList();
	   for (Map map : tab) {
		   String line=JSON.toJSONString(map);
		   line=line.replaceAll("\"", "");
		   list.add(line);
	}
		return Joiner.on("\r\n").join(list);
	}

}
