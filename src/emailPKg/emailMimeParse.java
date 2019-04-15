package emailPKg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

import com.alibaba.fastjson.JSON;
import com.attilax.time.sysncTimeX;
import com.beust.jcommander.internal.Lists;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;

public class emailMimeParse {

//	private static final

	public static void main(String args[]) throws Exception {
		Map m_debugMap = Maps.newLinkedHashMap();
		String emlFile = "C:\\Users\\zhoufeiyue\\Desktop\\新建文件夹 (3)\\e.doc";
		
		String txtFinal=eml2txt(emlFile);
		System.out.println(txtFinal);
	//	FileUtils.write(new File("G:\\eml.html"), htmlBody,"utf8");
//		String txtString = Jsoup.clean(htmlBody, "", Whitelist.none(),
//				new Document.OutputSettings().prettyPrint(false));
//		System.out.println(txtString);
		System.out.println(JSON.toJSON(m_debugMap));

//	message.getContent()
//	System.out.println(JSON.toJSON(message));

//	    String body=emlC.getBody(pathname);
//		String html_file_name = "c:\\gdb.htm";
//		html_file_name="c:\\hsbc.htm";
//		FileService.writeFile(html_file_name, body);
		System.out.println("--f");
		//
	}

	private static String eml2txt(String emlFile) throws  Exception {
		Properties props = System.getProperties();
		props.put("mail.host", "smtp.dummydomain.com");
		props.put("mail.transport.protocol", "smtp");

		Session mailSession = Session.getDefaultInstance(props, null);
		InputStream source = new FileInputStream(emlFile);
		System.out.println(source.available());
		MimeMessage message = new MimeMessage(mailSession, source);
		System.out.println("message.getSubject()  ");
		System.out.println(MimeUtility.decodeText(message.getSubject()));
		;

		System.out.println("getContentType" + message.getContentType());
		// getContentType
		// multipart/related;boundary="--boundary_0_9ed30801b0c180c69b3c5d74fede848e"
		// traveMessage(m_debugMap, message);
		String htmlBody = gethtmlBody(message);
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

	private static String ele2txt(Element e) {
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
			if (isBlogckElement(e))
				return "\r\n" + e.text() + "\r\n";
			else {
				return e.text();
			}
		}
		// return null;
	}

	private static boolean isBlogckElement(Element e) {
		System.out.println(e.tagName());
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
				if (mbp.getContentType().trim().startsWith("text/html;"))
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
}
