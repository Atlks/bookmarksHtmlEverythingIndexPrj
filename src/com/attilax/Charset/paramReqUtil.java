package com.attilax.Charset;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Joiner;

public class paramReqUtil {

 
public static void main(String[] args) {
	//http://localhost:1314/honurse/vaf2/weizhonRec_save?VCF08=1&VAA07=55975&VCF18=1&VCF09A=6&VCF65=2222&VCF09=2018-5-106:00&VAA05=李东林&VAA01=82767&BCE03A=系统管理员&VCF11=01&BCE01A=244&room=2
	String java_obj_str="{VCF08=1, VAA07=55975, VCF18=1, VCF09A=6, VCF65=2222, VCF09=2018-5-10 6:00, VAA05=李东林, VAA01=82767, BCE03A=系统管理员, VCF11=01, BCE01A=244, room=2}";
 
	String join = getReqParamsKeysJoinDohaor(java_obj_str);
	System.out.println(join);

}



private static String getReqParamsKeysJoinDohaor(String java_obj_str) {
	Map<String, String> httpreqParams2map = paramReqUtil.httpreqParams2map(paramReqUtil.mapobjstr2urlQuerystr(java_obj_str));
	Set keys=httpreqParams2map.keySet();
	
	String join = Joiner.on(",").join(keys);
	return join;
}


 /**
  * if include key=time ...hto you space l ,maybe err
  * @param java_obj_str
  * @return
  */
		private static String mapobjstr2urlQuerystr(String java_obj_str) {
			java_obj_str=java_obj_str.replaceAll(",", "&").replaceAll(" ", "");
			return java_obj_str.substring(1, java_obj_str.length()-1);
//			String url="";
//	         String[] kvs=java_obj_str.split(",");
//	         for (String kv : kvs) {
//				//String[] kva=kv.split("=");
//				//String querykv=
//			}
//	return null;
}

		public static Map<String,String> httpreqParams2map(String paramString) {
			Map<String,String> params = new HashMap<String,String>();
			String[] paramPairs = paramString.split("&");
			for(String param : paramPairs) {
				String[] key_value = param.split("=");
				params.put(key_value[0], key_value[1]);
			}
			return params;
		}

		public static Map<String,String> parse(String paramString) {
			Map<String,String> params = new HashMap<String,String>();
			String[] paramPairs = paramString.split("&");
			for(String param : paramPairs) {
				String[] key_value = param.split("=");
				params.put(key_value[0], key_value[1]);
			}
			return params;
		}
 

}
