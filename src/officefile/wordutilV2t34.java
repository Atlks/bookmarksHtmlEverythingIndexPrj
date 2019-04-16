package officefile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlException;
import org.junit.Test;

import com.attilax.util.ExUtilV2t33;

public class wordutilV2t34 {

	public static void main(String[] args) {
//			// TODO Auto-generated method stub
		wordutilV2t34 tp = new wordutilV2t34();
		String content = tp.readPPt("C:\\d\\atitit lucene concept mindchart v2 s55.docx");
		System.out.println("content====" + content);
	}

	@Test
	public void readPPT_tst() {
		wordutilV2t34 tp = new wordutilV2t34();
		String content = tp.readPPT("C:\\atibeks517\\l4 other doc v4 s56 oriVer exted\\ppt\\������Դ��ܽ���.ppt");
		System.out.println("content====" + content);
	}

	// ֱ�ӳ�ȡ�õ�Ƭ��ȫ������
	public static String readPPT(String f) {
		PowerPointExtractor extractor;
		try {
			extractor = new PowerPointExtractor(new FileInputStream(new File(f)));
			return extractor.getText();
		} catch (IOException e) {
			ExUtilV2t33.throwExV2(e);
		}
		return null;

	}

	// ֱ�ӳ�ȡ�õ�Ƭ��ȫ������
	public static String readPPT(InputStream is) {
		PowerPointExtractor extractor;
		try {
			extractor = new PowerPointExtractor(is);
			return extractor.getText();
		} catch (IOException e) {
			ExUtilV2t33.throwExV2(e);
		}
		return null;

	}

//		 public static String readPPT(String filePath) {  
//	            String str = "";  
//	            InputStream is = null;  
////	            PowerPointExtractor extractor = null;  
//	            try {  
//	                is = new FileInputStream(filePath);  
//	                SlideShow SlideShow1=new SlideShow(new HSLFSlideShow(is));  
//	                Slide[] slides=SlideShow1.getSlides();  
//	                for (Slide slide : slides) {
//	                	slide.
//					}
//	                
//	                for(int i=0; paras = doc.getParagraphs();  
//	            for (XWPFParagraph para : paras) {  
//	                //��ǰ���������  
//	               str = str+para.getText();  
//	            }  
//	            //��ȡ�ĵ������еı��  
//	            List tables = doc.getTables();  
//	            List rows;  
//	            List cells;  
//	            for (XWPFTable table : tables) {  
//	                //��ȡ����Ӧ����  
//	                rows = table.getRows();  
//	                for (XWPFTableRow row : rows) {  
//	                    //��ȡ�ж�Ӧ�ĵ�Ԫ��  
//	                    cells = row.getTableCells();  
//	                    for (XWPFTableCell cell : cells) {  
//	                        str = str+cell.getText();  
//	                    }  
//	                }  
//	            }  
//	            fis.close();  
//	        } catch (Exception e) {  
//	            e.printStackTrace();  
//	        }  
//	        return str;  
//	    }  
	@Deprecated
	public String readPPt(String path) {

		String buffer = "";
		try {
			if (path.endsWith(".doc")) {
				InputStream is = new FileInputStream(new File(path));
				WordExtractor ex = new WordExtractor(is);
				buffer = ex.getText();
				// ex.close();
			} else if (path.endsWith("docx")) {
				buffer = readWordDocx(path);
			} else {
				System.out.println("���ļ�����word�ļ���");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return buffer;
	}

	public static String readWordV2(String path) throws  Exception {

		String buffer = "";
		if (path.endsWith("docx")) {
			return readWordDocx(path);
			// extractor.close();
		}

		
		
		if (path.endsWith(".doc")) {
			InputStream is = new FileInputStream(new File(path));
		 
				WordExtractor ex = new WordExtractor(is);
				return ex.getText();
			 
		}
		
		
	
		return buffer;
	}
	/**
	 * ��ȡword�ļ�����
	 * 
	 * @param path
	 * @return buffer
	 * @throws OpenXML4JException
	 * @throws XmlException
	 * @throws IOException
	 */

	public static String readWord(String path) throws IOException, XmlException, OpenXML4JException {

		String buffer = "";
		if (path.endsWith("docx")) {
			return readWordDocx(path);
			// extractor.close();
		}

		
		
		if (path.endsWith(".doc")) {
			InputStream is = new FileInputStream(new File(path));
			try {
				WordExtractor ex = new WordExtractor(is);
				return ex.getText();
			} catch (java.io.IOException e) {
				return readWordDocx(path);
//				if (e.getMessage()
//						.contains("read 0x6854203A65746144, expected 0xE11AB1A1E011CFD0")) {
//					
//				}
//				throw e;
			}

			// ex.close();
		}
		
		
	
		return buffer;
	}

	private static String readWordDocx(String path) throws IOException, XmlException, OpenXML4JException {
		String buffer;
		OPCPackage opcPackage = POIXMLDocument.openPackage(path);
		POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
		buffer = extractor.getText();
		return buffer;
	}
	
	public static String readWordDocxWithCache(String path,String cacheDir) throws Exception {
		
		String basename=FilenameUtils.getName(path);
		File file2 = new File(cacheDir+"\\"+basename+".txt");
		if(file2.exists())
		{
			return FileUtils.readFileToString(file2);
		}
		
		String buffer;
		OPCPackage opcPackage = POIXMLDocument.openPackage(path);
		POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
		buffer = extractor.getText();
		return buffer;
	}

	public static String readWordWithCache(String path, String cacheDir) throws Exception {
		String basename=FilenameUtils.getName(path);
		File file2 = new File(cacheDir+"\\"+basename+".txt");
		if(file2.exists())
		{
			return FileUtils.readFileToString(file2);
		}
		String result = readWord(path);
		if( result.trim().length()>50)
		FileUtils.write(file2, result);
	
		return result;
	}

}
