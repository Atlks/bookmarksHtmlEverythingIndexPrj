package com.attilax.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class HtmlUtilV2t33 {

	public static void main(String[] args) throws IOException {
		String fString="G:\\0db\\doccache\\【研发总监 _ 上海 30k-35k】曾超 20年.doc.html";
		 System.out.println(readHtmlFile2txt(fString));
		
	//	String htmlfrag=FileUtils.readFileToString(new File( "G:\\0ati\\新建文本文档.txt"));
	//	System.out.println(  html2txt(htmlfrag) );

	}
	
	public static String html2txt(String htmlBody) {
		Document document = Jsoup.parse(htmlBody);
//		NodeTraversor nTraversor=new NodeTraversor(new NodeVisitor() {
//			
//			@Override
//			public void tail(Node arg0, int arg1) {
//				    System.out.println("tail..."+arg0);
//				
//			}
//			
//			@Override
//			public void head(Node arg0, int arg1) {
//				System.out.println("--head");
//			    System.out.println(arg0);
//				
//			}
//		});
//		nTraversor.traverse(document);

		 
	 
	 
		String txtFinal =ele2txt(document);
		txtFinal=txtFinal.replaceAll("\r\n\r\n", "\r\n");
		txtFinal=txtFinal.replaceAll("\r\n\r\n", "\r\n");
		txtFinal=txtFinal.replaceAll("\r\n\r\n", "\r\n");
	return txtFinal;
	}

	public static String ele2txt(Element e) {
		
		//maybe p hto yo yva tag can trans...but is txt is not warp by any tag ,then txt is miss..
		//so if p tag ,will be last tag to directly ret txt
		if (e.tagName().toString().equals("p"))
			return "\r\n" + e.text() + "\r\n";
		if (e.children().size() > 0) {
			List txtList = Lists.newLinkedList();
			for (Element e_sub : e.children()) {
				String childTxt = ele2txt(e_sub);
				txtList.add(childTxt);
			}
			if (isBlogckElement(e))
				return "\r\n" + Joiner.on("").join(txtList) + "\r\n";
			else {
				return Joiner.on("").join(txtList);
			}
			 

		} else { // no children
			//System.out.println(e);
			if (isBlogckElement(e))
				return "\r\n" + e.text() + "\r\n";
			else {
				return e.text();
			}
		}
		// return null;
	}

	public static boolean isBlogckElement(Element e) {
	//	System.out.println(e.tagName());
		if (e.tagName().toString().equals("br"))

			return true;
		if (e.tagName().toString().equals("table"))

			return true;
		if (e.tagName().toString().equals("tr"))

			return true;
		if (e.tagName().toString().equals("div"))

			return true;
		else {

			return false;
		}
	}

	public static String html2txtWithCache(String htmlFilepath, String cacheDir) throws IOException {
		String basename=FilenameUtils.getName(htmlFilepath);
		File file2 = new File(cacheDir+"\\"+basename+".txt");
		if(file2.exists())
		{
			return FileUtils.readFileToString(file2);
		}
		return readHtmlFile2txt(htmlFilepath );
		//
	}
	public static String readHtmlFile2txtWithCache(String htmlFilepath, String cacheDir) throws IOException {
		String basename=FilenameUtils.getName(htmlFilepath);
		File file2 = new File(cacheDir+"\\"+basename+".txt");
		if(file2.exists())
		{
			return FileUtils.readFileToString(file2);
		}
		String html2txt = html2txt( FileUtils.readFileToString(new File( htmlFilepath)));
		FileUtils.write(file2, html2txt);
		return html2txt;
	}
	private static String readHtmlFile2txt(String htmlFilepath) throws IOException {
		// TODO Auto-generated method stub
		return html2txt( FileUtils.readFileToString(new File( htmlFilepath)));
	}
}
