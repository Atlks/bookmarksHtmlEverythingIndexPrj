package emailPKg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
 
 
 
 /**
  * jra 1.8.16
  * @author zhoufeiyue
  *
  */
public class ExtractTextFromPDF {
public static  String readPDFV2WithCache(String filename,String cacheDir) throws  Exception{
		
		String basename=FilenameUtils.getName(filename);
		File file2 = new File(cacheDir+"\\"+basename+".txt");
		if(file2.exists())
		{
			return FileUtils.readFileToString(file2);
		}
		
		File file = new File(filename);
		FileInputStream in = null;
		 
			in = new FileInputStream(filename);
			PDFParser parser = new PDFParser(in);
			parser.parse();
			PDDocument pdDocument = parser.getPDDocument();
			PDFTextStripper stripper = new PDFTextStripper();
			String result = stripper.getText(pdDocument);
			
			System.out.println("PDF文件" + file.getAbsolutePath()+"内容如下：");
		
			FileUtils.write(file2, result);
			return (result);
		 
	}
	
	public static  String readPDFV2(String filename) throws  Exception{
		
		String basename=FilenameUtils.getName(filename);
		File file2 = new File("g:\\0db\\"+basename+".txt");
		if(file2.exists())
		{
			return FileUtils.readFileToString(file2);
		}
		
		File file = new File(filename);
		FileInputStream in = null;
		 
			in = new FileInputStream(filename);
			PDFParser parser = new PDFParser(in);
			parser.parse();
			PDDocument pdDocument = parser.getPDDocument();
			PDFTextStripper stripper = new PDFTextStripper();
			String result = stripper.getText(pdDocument);
			
			System.out.println("PDF文件" + file.getAbsolutePath()+"内容如下：");
		
			FileUtils.write(file2, result);
			return (result);
		 
	}
	public void readPDF(String filename){
		File file = new File(filename);
		FileInputStream in = null;
		try {
			in = new FileInputStream(filename);
			PDFParser parser = new PDFParser(in);
			parser.parse();
			PDDocument pdDocument = parser.getPDDocument();
			PDFTextStripper stripper = new PDFTextStripper();
			String result = stripper.getText(pdDocument);
			
			System.out.println("PDF文件" + file.getAbsolutePath()+"内容如下：");
			FileUtils.write(new File("g:\\0db\\pdf.txt"), result);
			System.out.println(result);
		} catch (IOException e) {
			 e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		String pdf="C:\\Users\\zhoufeiyue\\Downloads\\技术总监4.12\\看着不太适合的价格太高行业不符或工作年限\\【研发总监 _ 上海 30k-35k】王星 13年.pdf";
		
		new ExtractTextFromPDF().readPDF(pdf);
	}
}

 