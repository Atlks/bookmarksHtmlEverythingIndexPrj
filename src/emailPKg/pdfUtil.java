package emailPKg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;

public class pdfUtil {

	public static void main(String[] args) throws InvalidPasswordException, IOException {
		String pdf="C:\\Users\\zhoufeiyue\\Downloads\\技术总监4.12\\看着不太适合的价格太高行业不符或工作年限\\【研发总监 _ 上海 30k-35k】王星 13年.pdf";
		PDDocument doc = PDDocument.load(new File(pdf));
		
		
//		File file = new File(pdf);
//		FileInputStream in = null;
//		 
//			in = new FileInputStream(pdf);
//			PDFParser parser = new PDFParser(new RandomAccessFile(file, mode))
//			parser.parse();
//			PDDocument pdDocument = parser.getPDDocument();
// 


		 PDFTextStripper stripper = new PDFTextStripper();
		System.out.println(stripper.getText(doc)); ;
	
	}
}
