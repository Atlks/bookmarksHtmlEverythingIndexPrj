package com.attilax.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.http.StreamingHttpOutputMessage;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Listutil {

	private List<Map> tab;

	public Listutil(List<Map> tab) {
		this.tab=tab;
	}

	public static List deduli(List<String> list) {


		 //遍历后判断赋给另一个list集合
	    
//	         List<String> list  =   new  ArrayList<String>(); 
//	         list.add("aaa");
//	         list.add("bbb");
//	         list.add("aaa");
//	         list.add("aba");
//	         list.add("aaa");

	         List<String> newList = new  ArrayList<String>(); 
	         for (String cd:list) {
	            if(!newList.contains(cd)){
	                newList.add(cd);
	            }
	        }
	       return newList; 
	     
		
	}

	public static List delEmptyElement(List<String> stringA) {
		List<String> li=Lists.newArrayList();
		for (String string : stringA) {
			string=string.replaceAll("\r", "");
			if(string.trim().length()>0)
				li.add(string);
			 
		}
		return li;
		
	}

	public static Listutil from(List<Map> tab) {
		// TODO Auto-generated method stub
		return new Listutil(tab);
	}

	@SuppressWarnings("all")
	public List<Map> select(String string) {
		String[] cols=string.split(",");
		List<Map> li_rzt=Lists.newArrayList();
		List<Map> li=this.tab;
		for (Map map : li) {
			Map m=Maps.newLinkedHashMap();
			for (String col : cols) {
				col=col.trim();
				if(col.trim().length()==0)
					continue;
				m.put(col, map.get(col));
			}
			li_rzt.add(m);
		}
		
		return li_rzt;
	}

	public static List<String> trimElement(List<String> stringA) {
		List<String> li=Lists.newArrayList();
		for (String string : stringA) {
			string=string.replaceAll("\r", "");
			string=string.trim(); 
		 
				li.add(string);
			 
		}
		return li;
		
	}

}
