package officefile;

 

	import java.io.File;
	import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.POIXMLDocument;
	import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hwpf.extractor.WordExtractor;
	import org.apache.poi.openxml4j.opc.OPCPackage;
	import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.Test;

 
import com.attilax.util.ExUtilV2t33;

	public class wordutil {
		
		public static void main(String[] args) {
//			// TODO Auto-generated method stub
			wordutil tp = new wordutil();
 		String content = tp.readPPt("C:\\d\\atitit lucene concept mindchart v2 s55.docx");
 			System.out.println("content===="+content);
		}
		@Test
		public void readPPT_tst()
		{
			wordutil tp = new wordutil();
	 		String content = tp.readPPT("C:\\atibeks517\\l4 other doc v4 s56 oriVer exted\\ppt\\������Դ��ܽ���.ppt");
	 			System.out.println("content===="+content);
		}
		
		  //ֱ�ӳ�ȡ�õ�Ƭ��ȫ������
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
		
		
		  //ֱ�ӳ�ȡ�õ�Ƭ��ȫ������
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
				//	ex.close();
				} else if (path.endsWith("docx")) {
					OPCPackage opcPackage = POIXMLDocument.openPackage(path);
					POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
					buffer = extractor.getText();
				//	extractor.close();
				} else {
					System.out.println("���ļ�����word�ļ���");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return buffer;
		}
		
		/**
		 * ��ȡword�ļ�����
		 * 
		 * @param path
		 * @return buffer
		 */

		public static String readWord(String path) {
			
 			String buffer = "";
			try {
				if (path.endsWith(".doc")) {
					InputStream is = new FileInputStream(new File(path));
					WordExtractor ex = new WordExtractor(is);
					buffer = ex.getText();
				//	ex.close();
				} else if (path.endsWith("docx")) {
					OPCPackage opcPackage = POIXMLDocument.openPackage(path);
					POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
					buffer = extractor.getText();
				//	extractor.close();
				} else {
					System.out.println("���ļ�����word�ļ���");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return buffer;
		}

	

 
	 

}
