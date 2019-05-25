package com.attilax.Charset;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.attilax.io.pathx;
import com.attilax.str.Strutil;

/**
 * v2 s522
 * @author attilax
 *
 */
public class encodeService {

	private static Set<String> charset;
	
	public static void main(String[] args) {
		
		String f="C:\\encode\\gbk.txt";
		String f2="C:\\encode\\utfnobom.txt";
		String f3="C:\\encode\\utfbom.txt";
	System.out.println(EncodingDetect.getJavaEncode(f));	;
	System.out.println(EncodingDetect.getJavaEncode(f2));
	System.out.println(EncodingDetect.getJavaEncode(f3));
		System.out.println("--");
	}
	/**
	 * String fileEncode=EncodingDetect.getJavaEncode(filePath);   
	 * @param f
	 * @return
	 */
	public static String checkEncode(String f )
	{
		return f;
		
		
	}

	public static String gbk2utf8(String caption) {

		try {
			byte[] bytes = caption.getBytes("gbk");
			return new String(bytes, "utf8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return caption;
	}

	public static String utf2gbk(String caption) {

		try {
			byte[] bytes = caption.getBytes("utf8");
			return new String(bytes, "gbk");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return caption;
	}

	public static String iso2gbk(String caption) {
		// TODO Auto-generated method stub
		try {
			byte[] bytes = caption.getBytes("ISO-8859-1");
			return new String(bytes, "gbk");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return caption;

	}

	public static String iso2utf(String caption) {
		// TODO Auto-generated method stub
		try {
			byte[] bytes = caption.getBytes("ISO-8859-1");
			return new String(bytes, "utf8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return caption;

	}

	public static String getCorrectDecodeTxt(String s) {
		 

			if (isCoreectDecode(s))
				return s;
			else
			{
				String  iso2gbk=	 encodeService.iso2gbk(s);
				if(isCoreectDecode(iso2gbk))
					return iso2gbk;
				
				String  iso2utf=	 encodeService.iso2utf(s);
				if(isCoreectDecode(iso2utf))
					return iso2utf;
				String  gbk2utf8=	 encodeService.gbk2utf8(s);
				
				if(isCoreectDecode(gbk2utf8))
					return gbk2utf8;
				String  utf2gbk=	 encodeService.utf2gbk(s);
				if(isCoreectDecode(utf2gbk))
					return utf2gbk;
			}

		 
		return s;
	}

	private static boolean isCoreectDecode(String s) {
		// Strutil.toSet(compressableMimeType, string)
		double checkFlush = 0.6;
		String f = "";
		try {
			f = FileUtils.readFileToString(new File(pathx.classPath() + File.separator + "com/attilax/web/2500.txt"),
					"utf8");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		f=f.trim();
		if(f.length()<100)
			throw new RuntimeException("2500.txt file maybe not full ");
		f = f + "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ.";
		charset = strService.toSetNoSplitor(f);
		
		List<String> li_sa =strService. toListNoSplitor(s);
		int correctCnt = 0;
		for (String ch : li_sa) {
			if (charset.contains(ch))
				correctCnt++;
		}
		
		if ((float) correctCnt / (float) s.length() > checkFlush)
			return true;
		else

			return false;
	}

	public static void setCorrectDecodeText(Map<String, Object> paramMap, String keys) {
		String[] sa=keys.split(",");
		for (String k : sa) {
			try {
				if(paramMap.get(k)==null)
					continue;
				String correctDecodeTxt = encodeService.getCorrectDecodeTxt(paramMap.get(k).toString());
				 paramMap.put(k, correctDecodeTxt);
			} catch (Exception e) {
				e.printStackTrace();
			}
			 
		}
		
		
	}

	

}
