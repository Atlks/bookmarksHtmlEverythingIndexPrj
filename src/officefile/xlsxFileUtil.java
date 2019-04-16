package officefile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
/**
 * ��Excel�ļ�ת����TXT�ı��ļ�
 * ��������Jar��
 * jxl.jar
 * @author Alias
 *only xls file cant xlsx file
 */
@Deprecated 
public class xlsxFileUtil {
    private static String EXCEL_LINE_DELIMITER = "|";//�ָ���
    private static String EXCEL_LINE = "\r\n";//���з���
 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//window
        String from = "e:\\fq\\test\\from\\";
        String to = "e:\\fq\\test\\to\\";
        String newfileName = "test.txt";
        //linux
        //String from = "/home/ap/ods/tmp/excel/from/";
        //String to = "/home/ap/ods/tmp/excel/to/";
        
     //   excelToTxt(from,to,newfileName);
        
        xlsx2txt("C:\\Users\\attilax\\Documents\\s2018 doc\\r2017 rb sumdoc\\r2017 rb sumdoc\\aitit pato log how many ppl log  sumup and detail v4 rb28 .xlsx","c:\\tmp\\xls.txt");
	}
	  /**
     * excelת����txt
     *
     * @param from ԴĿ¼
     * @param to Ŀ��Ŀ¼
     */
    public static void excelToTxt(String from, String to, String newFileName) {
        File[] files = getFiles(from);
        File newFile = null;
     
        for (int i = 0; i < files.length; i++) {
        	String filePath = files[i].getPath();
        	   xlsx2txt( filePath,newFileName );
            
            
        }  //end for
       
    }
    public static void xlsx2txt(   	String excelfile, String newFileName ) {
		File newFile;
		int count = 0;
		   InputStream is = null;
		   FileOutputStream out = null;
	
		System.out.println("filePath:"+excelfile);
//            String fileName = files[i].getName();
//            System.out.println("fileName:"+fileName);
//            String fileType = fileName.substring(fileName.indexOf('.') + 1);
		String fileType = excelfile.substring(excelfile.indexOf('.') + 1);
		System.out.println("fileType:"+fileType);
		Workbook book = null;
		Sheet[] sheets = null;
		try {
		    if (fileType.equals("xls")) {
		    	System.out.println("count1="+count);
		        if(count==0){//��һ�ν���ʱ������Ŀ���ļ�
		            newFile = new File( newFileName);
		            System.out.println("�����ļ��ɹ���"+newFile.getName());
		            out = new FileOutputStream(newFile);
		            headStr(out);//�����ļ�ͷ
		        }
		        count++;
		        is = new FileInputStream(excelfile);
		        book = Workbook.getWorkbook(is);
		        sheets = book.getSheets();
		        System.out.println("sheets.length:"+sheets.length);
		        for (int j = 0; j < sheets.length; j++) {//һ��sheetһ��sheet�Ķ�ȡ��д��
		            readSheet(book.getSheet(j), out);
		        }
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    try {
		        if (book != null)
		            book = null;
		        if (is != null)
		            is.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		
		// �ͷ�out��Դ
		try {
		    if (out != null)
		        out.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	   private static void headStr(FileOutputStream out){//�����ļ�ͷ
	        StringBuffer shead = new StringBuffer();
	        shead.append("���|ѧ��|����|�Ա�|�绰|��ַ|�ʱ�|");	        
	        shead.append(EXCEL_LINE);
	        try {
	            out.write(shead.toString().getBytes());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 /**
     * ��Sheetд��txt�ļ�
     *
     * @param sheet
     * @param out
     */
    private static void readSheet(Sheet sheet, FileOutputStream out) {
        int rows = sheet.getRows();
        int cols = sheet.getColumns();
        Cell[] row = new Cell[cols];
        StringBuffer sBuffer = null;
        if (rows > 0) {
            for (int iRow = 1; iRow < rows; iRow++) {
                // /��ȡһ������
                row = sheet.getRow(iRow);
                
                //һ������д��
                sBuffer = new StringBuffer();
                sBuffer.append(row[0].getContents() != null ? row[0].getContents().trim() : "");//CUST_ACCT_NO
                sBuffer.append(EXCEL_LINE_DELIMITER);
                sBuffer.append(row[1].getContents() != null ? row[1].getContents().trim() : "");//INST_NO
                sBuffer.append(EXCEL_LINE_DELIMITER);
                sBuffer.append(row[2].getContents() != null ? row[2].getContents().trim() : "");//INST_NAME
                sBuffer.append(EXCEL_LINE_DELIMITER);
                sBuffer.append("" + EXCEL_LINE_DELIMITER);//CUST_NO
                sBuffer.append(row[3].getContents() != null ? row[3].getContents().trim() : "");//CUST_NAME
                sBuffer.append(EXCEL_LINE_DELIMITER);
                sBuffer.append(row[4].getContents() != null ? row[4].getContents().trim() : "");//AREA_NO
                sBuffer.append(EXCEL_LINE_DELIMITER);
                sBuffer.append(row[5].getContents() != null ? row[5].getContents().trim() : "");//ACCT_NAME
                sBuffer.append(EXCEL_LINE_DELIMITER);
                sBuffer.append(row[6].getContents() != null ? row[6].getContents().trim() : "");//ACQU
                sBuffer.append(EXCEL_LINE_DELIMITER);
                sBuffer.append(EXCEL_LINE);
                //��1��д��txt�ļ�
                try {
                	System.out.println("sBuffer=="+sBuffer.toString());
                    out.write(sBuffer.toString().getBytes());
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
	 /**
     * ��ȡĿ¼�µ������ļ�
     *
     * @param path
     * @return
     */
    private static File[] getFiles(String path) {
        File file = new File(path);
        File[] array = file.listFiles();
        return array;
    }
    
    
}
