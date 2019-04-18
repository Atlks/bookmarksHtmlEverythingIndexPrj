package com.attilax.text;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.beust.jcommander.internal.Sets;
import com.google.common.collect.Lists;

import groovy.transform.builder.InitializerStrategy.SET;
import resume.resumeGrepper;
@SuppressWarnings("all")
public class HeziUtil {
	public static int count(String text) {
		String Reg = "^[\u4e00-\u9fa5]{1}$";// 正则
		int result = 0;
		for (int i = 0; i < text.length(); i++) {
			String b = Character.toString(text.charAt(i));
			if (b.matches(Reg))
				result++;
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		String text = "汉字汉字仮名かなカナ 0189 azAZ ./ ";
		
	
	//	System.out.println(resumeGrepper);
	Set<String> cnsSet=getCnSet();
	List hanzi = getHanzi(text,100);
	float pct=heziPctInCommon2500(hanzi,cnsSet);
	
		System.out.println(count(text));
		
	
		System.out.println(hanzi );
	}

	public static float heziPctInCommon2500(List<String> hanzi, Set<String> cnsSet) {
		int incount = 0;
		 for (String he : hanzi) {
			if(cnsSet.contains(he))
				incount++;
		}
		return (float)incount/(float)hanzi.size();
	}

	public static Set<String> getCnSet() throws IOException {
		Set<String> cnsSet=Sets.newLinkedHashSet();
		URL resource = HeziUtil.class.getResource("");
		String pathname = resource.getFile()+"2500.txt";
		File file = new File(pathname);
		if(!file.exists())
			file=new File("H:\\gitWorkSpace\\bookmarksHtmlEverythingIndexPrj\\src\\com\\attilax\\text\\2500.txt");
		String text=FileUtils.readFileToString(file);
		for (int i = 0; i < text.length(); i++) {
			String b = Character.toString(text.charAt(i));
			cnsSet.add(b);
		}
		return cnsSet;
	}

	public static List getHanzi(String text, int lenPre) {
		List list=Lists.newLinkedList();
		String Reg = "^[\u4e00-\u9fa5]{1}$";// 正则
		int result = 0;
		for (int i = 0; i < text.length(); i++) {
			String b = Character.toString(text.charAt(i));
			if (b.matches(Reg))
				list.add(b);
			if(list.size()>lenPre)
				return list;
		}
		return list;
	}

	public static String convertToHeziByCoreectencode(String htmlBody) throws Exception {
		Set<String> cnsSet=getCnSet();
		List hanzi = getHanzi(htmlBody,100);
		float pct=heziPctInCommon2500(hanzi,cnsSet);
		if(pct>0.5)
		return htmlBody;
		else {
			htmlBody = new String(htmlBody.getBytes("iso-8859-1"),	"gb2312");
			return htmlBody;
		}
			 
			
	}

	public static String replaceHeziComma(String string) {
		string=string.replaceAll("；", ";");	string=string.replaceAll("：", ":");		string=string.replaceAll("＠", "@");	
		return string;
	}
} // 分给上面的吧，这也是一种方法
