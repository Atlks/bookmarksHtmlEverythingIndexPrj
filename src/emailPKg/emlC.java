package m.eml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import m.FileService;
import m.PraseMimeMessage;

public class emlC {
	
	public static void main(String args[]) throws Exception {
	 
		String pathname = "C:\\gdb.eml";
		pathname = "C:\\hsbc.eml";
	    String body=emlC.getBody(pathname);
		String html_file_name = "c:\\gdb.htm";
		html_file_name="c:\\hsbc.htm";
		FileService.writeFile(html_file_name, body);
		System.out.println("f");
		//
	}

	public static String getBody(String path)
	{
		try{
		File emlFile =new File(path);
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
			//System.out.println("Bodys : " +  message.getContent().//

			int i = 0;
			PraseMimeMessage pmm = new PraseMimeMessage(message);
			System.out.println("Message " + i + " subject: " + pmm.getSubject());
			System.out.println("Message " + i + " sentdate: " + pmm.getSentDate());
			System.out
					.println("Message " + i + " replysign: " + pmm.getReplySign());
			System.out.println("Message " + i + " hasRead: " + pmm.isNew());
			System.out.println("Message " + i + " containAttachment: "
					+ pmm.isContainAttach((Part) message));
			System.out.println("Message " + i + " form: " + pmm.getFrom());
			System.out.println("Message " + i + " to: " + pmm.getMailAddress("to"));
			System.out.println("Message " + i + " cc: " + pmm.getMailAddress("cc"));
			System.out.println("Message " + i + " bcc: "
					+ pmm.getMailAddress("bcc"));
			pmm.setDateFormat("yyƒÍMM‘¬dd»’ HH:mm");
			System.out.println("Message " + i + " sentdate: " + pmm.getSentDate());
			System.out.println("Message " + i + " Message-ID: "
					+ pmm.getMessageId());
			pmm.getMailContent((Part) message);
			String bodyText = pmm.getBodyText();return bodyText;
		}catch (Exception e) {
			 throw new RuntimeException(e);
		}
	}}
 
