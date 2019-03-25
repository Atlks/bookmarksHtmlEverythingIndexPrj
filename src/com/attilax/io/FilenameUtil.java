package com.attilax.io;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import com.attilax.text.strUtilV3t33;

public class FilenameUtil {
	
	public static void main(String[] args) {
		String fString=".haha/(";
		fString="* - 5SING";
	 	System.out.println(  fileNameEncodeUrlencMode(fString ));
	}

	
	public static String fileNameEncodeUrlencMode(String filenameOri) {
		String s_final="";
		final String[] as = strUtilV3t33.SplitByNone(filenameOri);
		for (String s : as) {
			String s_enc=fileNameEncodeChar(s);
			s_final=s_final+s_enc;
		}
//		if(s_final.length()>255)
//			return s_final.substring(0, 254);
		return s_final;
		
	}

	public static String fileNameEncodeChar(String filenameOriChar) {

		// /\:* <>\"|
		
		if (filenameOriChar.equals(".."))

			return "%2E%2E";

		if (filenameOriChar.equals("."))

			return "%2E";

		if (filenameOriChar.equals("*"))

			return "%2A";


		// Map<String, String> mp = (Map<String, String>) new ClosureNoExcpt() {
		String fileIllcharList = "/\\:?<>\"|*";
		 if(strUtilV3t33.contain(fileIllcharList,filenameOriChar))
		 {
			 try {
					return URLEncoder.encode(filenameOriChar, "utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		 }
			
	
		
		return filenameOriChar;

	}
	
	

	// cant case cn char cant be urlcode ..beir neke readable cant..
	@Deprecated
	public static String encodeFilenameByUrlcode(String filename) {
		// TODO Auto-generated method stub
		return URLEncoder.encode(filename);
	}

	public static String encodeFilenameByCnChar(String filename) {
		// TODO Auto-generated method stub
		return null;
	}


	public static String fileNameEncodeUrlencMode(String filename, String qqBrowerBookmarkEverythinIndexDir) {
		String filename_enc=fileNameEncodeUrlencMode(filename);
		int fnameMaxLen = 255-qqBrowerBookmarkEverythinIndexDir.length();
		if(filename_enc.length()>fnameMaxLen)
			return  filename_enc.substring(0,fnameMaxLen);
		return filename_enc;
	}

}
