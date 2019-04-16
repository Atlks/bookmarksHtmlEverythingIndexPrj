package emailPKg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

import com.alibaba.fastjson.JSON;
import com.attilax.text.HeziUtil;
import com.attilax.time.sysncTimeX;
import com.attilax.util.FileCacheManager;
import com.attilax.util.HtmlUtilV2t33;
import com.beust.jcommander.internal.Lists;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;

import bsh.This;

public class emailMimeParse {

private FileCacheManager fileCacheManager;

public emailMimeParse(FileCacheManager fileCacheManager) {
		this.fileCacheManager=fileCacheManager;
	}

//	private static final

	public static void main(String args[]) throws Exception {
//		Map m_debugMap = Maps.newLinkedHashMap();
//		String emlFile = "C:\\Users\\zhoufeiyue\\Desktop\\新建文件夹 (3)\\e.doc";
//		
//		String txtFinal=eml2txt(emlFile);
//		System.out.println(txtFinal);
//	//	FileUtils.write(new File("G:\\eml.html"), htmlBody,"utf8");
////		String txtString = Jsoup.clean(htmlBody, "", Whitelist.none(),
////				new Document.OutputSettings().prettyPrint(false));
////		System.out.println(txtString);
//		System.out.println(JSON.toJSON(m_debugMap));
//
////	message.getContent()
////	System.out.println(JSON.toJSON(message));
//
////	    String body=emlC.getBody(pathname);
////		String html_file_name = "c:\\gdb.htm";
////		html_file_name="c:\\hsbc.htm";
////		FileService.writeFile(html_file_name, body);
//		System.out.println("--f");
		//
	}

	public   String eml2txt(String emlFile) throws  Exception {
		
		String htmlBody = getHtmlBody(emlFile);
		htmlBody=	HeziUtil.convertToHeziByCoreectencode(htmlBody);
		
		String basename=FilenameUtils.getName(emlFile);
		this.fileCacheManager.set( basename+".html" , htmlBody);
		
		Document document = Jsoup.parse(htmlBody);
	 
	 
	 
		String txtFinal =HtmlUtilV2t33. ele2txt(document);
		txtFinal=txtFinal.replaceAll("\r\n\r\n", "\r\n");
		txtFinal=txtFinal.replaceAll("\r\n\r\n", "\r\n");
		txtFinal=txtFinal.replaceAll("\r\n\r\n", "\r\n");
	return txtFinal;
	
//	NodeTraversor nTraversor=new NodeTraversor(new NodeVisitor() {
//	
//	@Override
//	public void tail(Node arg0, int arg1) {
//		    System.out.println("tail..."+arg0);
//		
//	}
//	
//	@Override
//	public void head(Node arg0, int arg1) {
//		System.out.println("--head");
//	    System.out.println(arg0);
//		
//	}
//});
//nTraversor.traverse(document);


}

	private static String getHtmlBody(String emlFile)
			throws FileNotFoundException, IOException, MessagingException, Exception {
		if(emlFile.contains("曾超 20年"))
			System.out.println("");
		Properties props = System.getProperties();
		props.put("mail.host", "smtp.dummydomain.com");
		props.put("mail.transport.protocol", "smtp");

		Session mailSession = Session.getDefaultInstance(props, null);
		InputStream source = new FileInputStream(emlFile);
		System.out.println(source.available());
		MimeMessage message = new MimeMessage(mailSession, source);
		System.out.println("message.getSubject()  ");
//		System.out.println(MimeUtility.decodeText(message.getSubject()));
//		;

		System.out.println("getContentType" + message.getContentType());
		// getContentType
		// multipart/related;boundary="--boundary_0_9ed30801b0c180c69b3c5d74fede848e"
		// traveMessage(m_debugMap, message);
		String htmlBody = gethtmlBody(message);
		return htmlBody;
	}

 
 
	private static String gethtmlBody(MimeMessage message) throws Exception {
		Object object = message.getContent();
		if (object instanceof MimeMultipart) {

			MimeMultipart mmpart = (MimeMultipart) message.getContent();
			// m_debugMap.put("MimeMultipart.getCount", mmpart.getCount());
			List list = Lists.newLinkedList();
			for (int i = 0; i < mmpart.getCount(); i++) {
				// MimeBodyPart
				BodyPart bodyPart = mmpart.getBodyPart(i);
				// System.out.println(JSON.toJSON(bodyPart));
				MimeBodyPart mbp = (MimeBodyPart) bodyPart;
				Map tMap = Maps.newLinkedHashMap();
				// tMap.put("mbp", mbp);
				Object content = mbp.getContent();
				tMap.put("mbp.getContent", content);
				tMap.put("mbp.getContentType", mbp.getContentType());
				if (mbp.getContentType().trim().startsWith("text/html"))
					return mbp.getContent().toString();
				list.add(tMap);
				System.out.println("");
			}
			// System.out.println(JSON.toJSONString(list,true));
		}
		return "";
	}

	/*
	 * [ { "mbp.getContentType":"text/html;charset=\"gb2312\"" }, {
	 * "mbp.getContent":{}, "mbp.getContentType":"image/png" }, {
	 * "mbp.getContent":{}, "mbp.getContentType":"image/png" }, {
	 * "mbp.getContent":{}, "mbp.getContentType":"image/png" }, {
	 * "mbp.getContent":{}, "mbp.getContentType":"image/png" }, {
	 * "mbp.getContent":{}, "mbp.getContentType":"image/png" }, {
	 * "mbp.getContent":{}, "mbp.getContentType":"image/png" } ]
	 * 
	 * 
	 * 
	 */
	private static void traveMessage(Map m_debugMap, MimeMessage message) throws IOException, MessagingException {
		Object object = message.getContent();
		if (object instanceof MimeMultipart) {

			MimeMultipart mmpart = (MimeMultipart) message.getContent();
			m_debugMap.put("MimeMultipart.getCount", mmpart.getCount());
			List list = Lists.newLinkedList();
			for (int i = 0; i < mmpart.getCount(); i++) {
				// MimeBodyPart
				BodyPart bodyPart = mmpart.getBodyPart(i);
				// System.out.println(JSON.toJSON(bodyPart));
				MimeBodyPart mbp = (MimeBodyPart) bodyPart;
				Map tMap = Maps.newLinkedHashMap();
				// tMap.put("mbp", mbp);
				Object content = mbp.getContent();
				tMap.put("mbp.getContent", content);
				tMap.put("mbp.getContentType", mbp.getContentType());
				list.add(tMap);
				System.out.println("");
			}
			System.out.println(JSON.toJSONString(list, true));
		}
	}

	public static String getBody(String path) throws Exception {

		File emlFile = new File(path);
		Properties props = System.getProperties();
		props.put("mail.host", "smtp.dummydomain.com");
		props.put("mail.transport.protocol", "smtp");

		Session mailSession = Session.getDefaultInstance(props, null);
		InputStream source = new FileInputStream(emlFile);
		System.out.println(source.available());
		MimeMessage message = new MimeMessage(mailSession, source);

		System.out.println(message);

		System.out.println("Subject : " + message.getSubject());
		System.out.println("From : " + message.getFrom()[0]);
		System.out.println("--------------");
		System.out.println("Body : " + message.getContent());
		// System.out.println("Bodys : " + message.getContent().//

		int i = 0;
		System.out.println("message.getSubject()  ");
		return MimeUtility.decodeText(message.getSubject());

	}

	public static String eml2txtWithCache(String absolutePath, FileCacheManager fileCacheManager, Function<String, String> fun) throws Exception {
		
		if(fileCacheManager.existCache(absolutePath))
			return fileCacheManager.getCache(absolutePath);
	
		
		
		String eml2txt = eml2txt(absolutePath,fun);
		if( eml2txt.trim().length()>50)		
			fileCacheManager.setCache(absolutePath,eml2txt );
		return eml2txt;
	}

	private static String eml2txt(String emlFile, Function<String, String> fun) throws Exception {
		String htmlBody = getHtmlBody(emlFile);
		  htmlBody = fun.apply(htmlBody);
		
		
		String cacheDir= "g:\\0db\\doccache";
		String basename=FilenameUtils.getName(emlFile);
		File file2 = new File(cacheDir+"\\"+basename+".html");
		FileUtils.write(file2, htmlBody);
	//	new FileCacheManager().setCache(emlFile+".html", htmlBody);
		Document document = Jsoup.parse(htmlBody);
 	 
	 
	 
		String txtFinal =HtmlUtilV2t33.ele2txt(document);
		txtFinal=txtFinal.replaceAll("\r\n\r\n", "\r\n");
		txtFinal=txtFinal.replaceAll("\r\n\r\n", "\r\n");
		txtFinal=txtFinal.replaceAll("\r\n\r\n", "\r\n");
//	String apply = fun.apply(txtFinal);
	return  txtFinal ;
	}

	public static String eml2txtWithCache(String absolutePath, FileCacheManager fileCacheManager) throws Exception {
		if(fileCacheManager.existCache(absolutePath))
			return fileCacheManager.getCache(absolutePath);
	
		
		
		String eml2txt =new emailMimeParse(fileCacheManager).  eml2txt(absolutePath);
		if( eml2txt.trim().length()>50)		
			fileCacheManager.setCache(absolutePath,eml2txt );
		return eml2txt;
	}
}
