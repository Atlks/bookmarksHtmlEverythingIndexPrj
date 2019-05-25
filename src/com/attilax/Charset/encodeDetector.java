package com.attilax.Charset;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

public class encodeDetector {

	public static void main(String[] args) throws UnsupportedEncodingException {
		 String s3="汉字测试";
		 String s3_ucode=URLEncoder.encode(s3,"utf8"); //utf8
		 String s3_decode_iso=URLDecoder.decode(s3_ucode, "ISO-8859-1");
		 String s3_decode_gbk=URLDecoder.decode(s3_ucode, "gbk");
		 String s3_decode_utf=URLDecoder.decode(s3_ucode, "utf8");
		 
	  String s= s3_decode_iso;
	  String correctDecodeTxt = encodeService.getCorrectDecodeTxt(s);
	System.out.println( correctDecodeTxt );
	String  iso2gbk=	 encodeService.iso2gbk(s);
	String  iso2utf=	 encodeService.iso2utf(s);
	String  gbk2utf8=	 encodeService.gbk2utf8(s);
	String  utf2gbk=	 encodeService.utf2gbk(s);
	Map m=Maps.newConcurrentMap();
	m.put("iso2gbk", iso2gbk);	m.put("iso2utf", iso2utf);	m.put("gbk2utf8", gbk2utf8);	m.put("utf2gbk", utf2gbk);
	System.out.println(JSON.toJSONString(m));

	}

}
