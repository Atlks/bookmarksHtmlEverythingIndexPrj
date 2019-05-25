package data.html;

import java.io.File;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.TextField;

import com.attilax.Charset.EncodingDetect;
import com.attilax.data.csv.htmlJsoupUtil;
//import com.attilax.core.ForeachFunction;
//import com.attilax.data.csv.htmlJsoupUtil;
//import com.attilax.io.DirTraveService;
import com.attilax.io.FileUtilsAti;
//import com.attilax.web.es.esUtil_docx2;

public class html2txtConverter {
	static final Logger logger = Logger.getLogger(html2txtConverter.class);
	
	
	public static void main(String[] args) {
//		String dir = "C:\\Users\\attilax\\Documents\\nethtmlcoll";
//		String dir_tar = "C:\\Users\\attilax\\Documents\\nethtmlcoll_2txt";
//		new DirTraveService().traveV5_vS522(new File(dir), new ForeachFunction() {
//
//			@Override
//			public void each(int count, final File f) throws Exception {
//				
//				try {		
//					logger.info("cn:" + count + f.getAbsolutePath());
//					final String t = FileUtilsAti.readFileToStringAutoDetectEncode(f.getAbsolutePath());
//					
//					String txt = html2txtV2(t);
//				
//					FileUtils.write(new File(dir_tar+"\\"+f.getName()+".txt"), txt);
//					
//				} catch (Exception e) {
//					logger.error(e);
//				}
//				
//				 
//
//			}
//
//			
//
//		 
//
//		});
//		System.out.println("--f");

	}
	
	
	public static String html2txtV2(final String t) {
		String txt;
		try {
			txt=htmlJsoupUtil.html2txt(t);
		} catch (Exception e) {
			try {
				txt=html2txtConverter.Html2TextByRe(t);
			} catch (Exception e2) {
				txt=html2txtConverter.StripHTM2txtByPlaceall(t);
			}
			
		}
		return txt;
	}
	//��html����ȡ���ı�  
	//
	@Deprecated
	public static String StripHTM2txtByPlaceall(String strHtml) {  
	     String txtcontent = strHtml.replaceAll("</?[^>]+>", ""); //�޳�<html>�ı�ǩ    
	        txtcontent = txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "");//ȥ���ַ����еĿո�,�س�,���з�,�Ʊ��    
	        return txtcontent;  
	   } 
	
	//��html����ȡ���ı�
		public static String Html2TextByRe(String inputString) {
			String htmlStr = inputString; // ��html��ǩ���ַ���
			String textStr = "";
			java.util.regex.Pattern p_script;
			java.util.regex.Matcher m_script;
			java.util.regex.Pattern p_style;
			java.util.regex.Matcher m_style;
			java.util.regex.Pattern p_html;
			java.util.regex.Matcher m_html;
			try {
				String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // ����script��������ʽ{��<script[^>]*?>[\\s\\S]*?<\\/script>
		        String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // ����style��������ʽ{��<style[^>]*?>[\\s\\S]*?<\\/style>
		        String regEx_html = "<[^>]+>"; // ����HTML��ǩ��������ʽ
		        p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		        m_script = p_script.matcher(htmlStr);
		        htmlStr = m_script.replaceAll(""); // ����script��ǩ
		        p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		        m_style = p_style.matcher(htmlStr);
		        htmlStr = m_style.replaceAll(""); // ����style��ǩ
		        p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		        m_html = p_html.matcher(htmlStr);
		        htmlStr = m_html.replaceAll(""); // ����html��ǩ
		        textStr = htmlStr;
		    } catch (Exception e) {System.err.println("Html2Text: " + e.getMessage()); }
			//�޳��ո���
			textStr=textStr.replaceAll("[ ]+", " ");
			textStr=textStr.replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "");
			return textStr;// �����ı��ַ���
		}
}
