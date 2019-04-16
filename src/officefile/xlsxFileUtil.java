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
 * 将Excel文件转换成TXT文本文件
 * 所依赖的Jar包
 * jxl.jar
 * @author Alias
 *only xls file cant xlsx file
 */
@Deprecated 
public class xlsxFileUtil {
    private static String EXCEL_LINE_DELIMITER = "|";//分隔符
    private static String EXCEL_LINE = "\r\n";//换行符号
 
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
     * excel转换成txt
     *
     * @param from 源目录
     * @param to 目标目录
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
		        if(count==0){//第一次进入时，创建目标文件
		            newFile = new File( newFileName);
		            System.out.println("创建文件成功："+newFile.getName());
		            out = new FileOutputStream(newFile);
		            headStr(out);//创建文件头
		        }
		        count++;
		        is = new FileInputStream(excelfile);
		        book = Workbook.getWorkbook(is);
		        sheets = book.getSheets();
		        System.out.println("sheets.length:"+sheets.length);
		        for (int j = 0; j < sheets.length; j++) {//一个sheet一个sheet的读取并写入
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
		
		// 释放out资源
		try {
		    if (out != null)
		        out.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	   private static void headStr(FileOutputStream out){//创建文件头
	        StringBuffer shead = new StringBuffer();
	        shead.append("序号|学号|姓名|性别|电话|地址|邮编|");	        
	        shead.append(EXCEL_LINE);
	        try {
	            out.write(shead.toString().getBytes());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 /**
     * 读Sheet写到txt文件
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
                // /读取一行数据
                row = sheet.getRow(iRow);
                
                //一行数据写入
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
                //读1行写到txt文件
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
     * 获取目录下的所有文件
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
