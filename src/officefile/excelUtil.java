package officefile;

import com.attilax.util.ExUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by xxx on 2016/11/8.
 */
public class excelUtil {

	private static Map<Integer, String> keyMapName = new LinkedHashMap<Integer, String>();
	private static Map<String, String> keyMapContext = new LinkedHashMap<String, String>();

	static ConcurrentLinkedQueue<String> queues = new ConcurrentLinkedQueue<String>();

	static ObjectMapper objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
			.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
			.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

	public static void main(String[] args) {
		String filePath = "C:\\Users\\attilax\\Documents\\s2018 doc\\r2017 rb sumdoc\\r2017 rb sumdoc\\aitit pato log how many ppl log  sumup and detail v4 rb28 .xlsx";

		String txt = "c:\\tmp\\xls.txt";
		excel2txtXlsxVer(filePath, txt);
	}
	public static void trave_2007fmt(String filePath,String sheetname, Consumer<Object> Consumer1) {
		
		  Map<Integer, String> keyMapName = new LinkedHashMap<Integer, String>();
		 
			try {
				XSSFWorkbook XSSFWorkbook1 = null;
				POIFSFileSystem fs = null;

				// fs = new POIFSFileSystem(new FileInputStream(new
				// File(filePath)));
				FileInputStream fi = new FileInputStream(new File(filePath));
				XSSFWorkbook1 = new XSSFWorkbook(fi);

				
				XSSFSheet sheet =getSheet(sheetname);
						XSSFWorkbook1.getSheetAt(0);
				sheet.getSheetName();
				Integer rowNum;
				XSSFRow rowName = sheet.getRow(0);
				for (int cellNum = rowName.getFirstCellNum(); cellNum < rowName.getLastCellNum(); cellNum++) {
					keyMapName.put(cellNum, rowName.getCell(cellNum).getStringCellValue());
				}
				//	
				for (rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
					XSSFRow row_cur = sheet.getRow(rowNum);
					 Map<String, String> row_map = new LinkedHashMap<String, String>();
					if (row_cur != null) {
						for (int cellNum_index = row_cur.getFirstCellNum(); cellNum_index < row_cur.getLastCellNum(); cellNum_index++) {
							XSSFCell cell = row_cur.getCell(cellNum_index);
							if (cell != null) {
								String colval = cell.getStringCellValue();
								if (colval != null
										&& !"".equals(colval)) {
									String colname = keyMapName.get(cellNum_index);
									row_map.put(colname, colval);
								}
							}
						}
						Consumer1.accept(row_map);
					}
				
					 
				}

			 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	public static void excel2txtFmt2007V3(String filePath, Consumer<Object> Consumer1) {
		
	  Map<Integer, String> keyMapName = new LinkedHashMap<Integer, String>();
	 
		try {
			XSSFWorkbook XSSFWorkbook1 = null;
			POIFSFileSystem fs = null;

			// fs = new POIFSFileSystem(new FileInputStream(new
			// File(filePath)));
			FileInputStream fi = new FileInputStream(new File(filePath));
			XSSFWorkbook1 = new XSSFWorkbook(fi);

			XSSFSheet sheet = XSSFWorkbook1.getSheetAt(0);
			Integer rowNum;
			XSSFRow rowName = sheet.getRow(0);
			for (int cellNum = rowName.getFirstCellNum(); cellNum < rowName.getLastCellNum(); cellNum++) {
				keyMapName.put(cellNum, rowName.getCell(cellNum).getStringCellValue());
			}
			//	
			for (rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
				XSSFRow row_cur = sheet.getRow(rowNum);
				 Map<String, String> row_map = new LinkedHashMap<String, String>();
				if (row_cur != null) {
					for (int cellNum_index = row_cur.getFirstCellNum(); cellNum_index < row_cur.getLastCellNum(); cellNum_index++) {
						XSSFCell cell = row_cur.getCell(cellNum_index);
						if (cell != null) {
							String colval = cell.getStringCellValue();
							if (colval != null
									&& !"".equals(colval)) {
								String colname = keyMapName.get(cellNum_index);
								row_map.put(colname, colval);
							}
						}
					}
					Consumer1.accept(row_map);
				}
			
				 
			}

		 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void excel2txtFmt2007V2(String filePath, Consumer<String> Consumer1) {
		try {
			XSSFWorkbook wb = null;
			POIFSFileSystem fs = null;

			// fs = new POIFSFileSystem(new FileInputStream(new
			// File(filePath)));
			FileInputStream fi = new FileInputStream(new File(filePath));
			wb = new XSSFWorkbook(fi);

			XSSFSheet sheet = wb.getSheetAt(0);
			Integer rowNum;
			XSSFRow rowName = sheet.getRow(0);
			for (int cellNum = rowName.getFirstCellNum(); cellNum < rowName.getLastCellNum(); cellNum++) {
				keyMapName.put(cellNum, rowName.getCell(cellNum).getStringCellValue());
			}

			for (rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
				XSSFRow row = sheet.getRow(rowNum);
				if (row != null) {
					for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
						if (row.getCell(cellNum) != null)
							if (row.getCell(cellNum).getStringCellValue() != null
									&& !"".equals(row.getCell(cellNum).getStringCellValue())) {
								keyMapContext.put(keyMapName.get(cellNum), row.getCell(cellNum).getStringCellValue());
							}
					}
				}
				if (keyMapContext != null && keyMapContext.size() > 0) {
					queues.add(objectMapper.writeValueAsString(keyMapContext));
				}
			}

			int i = 0;
			while (true) {
				if (!queues.isEmpty()) {
					String context = queues.poll();
					if (context == null)
						continue;
					Consumer1.accept(context);

					i++;
					System.out.println("total £º" + i);
				}
				if (queues.isEmpty()) {

					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void excel2txtXlsxVer(String filePath, String txtFile) {

		FileOutputStream fileOutputStream = null;
		BufferedWriter bufferedWriter = null;
		OutputStreamWriter outputStream = null;
		try {
			fileOutputStream = new FileOutputStream(txtFile);
			outputStream = new OutputStreamWriter(fileOutputStream);
			bufferedWriter = new BufferedWriter(outputStream);
		} catch (FileNotFoundException e1) {
			ExUtil.throwExV2(e1);
		}
		BufferedWriter bufferedWriterAAA = bufferedWriter;
		excel2txtFmt2007V2(filePath, new Consumer<String>() {

			@Override
			public void accept(String line) {
				try {
					
					bufferedWriterAAA.write(line);
					bufferedWriterAAA.newLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			

			}
		});
		try {
			bufferedWriter.flush();
			bufferedWriter.close();
			outputStream.flush();
			outputStream.close();
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

	private static void excel2txtXlsVer(String filePath, String txt) {
		try {
			HSSFWorkbook wb = null;
			POIFSFileSystem fs = null;

			fs = new POIFSFileSystem(new FileInputStream(new File(filePath)));
			wb = new HSSFWorkbook(fs);

			HSSFSheet sheet = wb.getSheetAt(0);
			Integer rowNum;
			HSSFRow rowName = sheet.getRow(0);
			for (int cellNum = rowName.getFirstCellNum(); cellNum < rowName.getLastCellNum(); cellNum++) {
				keyMapName.put(cellNum, rowName.getCell(cellNum).getStringCellValue());
			}

			for (rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
				HSSFRow row = sheet.getRow(rowNum);
				if (row != null) {
					for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
						if (row.getCell(cellNum).getStringCellValue() != null
								&& !"".equals(row.getCell(cellNum).getStringCellValue())) {
							keyMapContext.put(keyMapName.get(cellNum), row.getCell(cellNum).getStringCellValue());
						}
					}
				}
				if (keyMapContext != null && keyMapContext.size() > 0) {
					queues.add(objectMapper.writeValueAsString(keyMapContext));
				}
			}

			File file = new File(txt);
			// ByteArrayOutputStream baos=new ByteArrayOutputStream();
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			OutputStreamWriter outputStream = new OutputStreamWriter(fileOutputStream);
			BufferedWriter bufferedWriter = new BufferedWriter(outputStream);

			int i = 0;
			while (true) {
				if (!queues.isEmpty()) {
					String context = queues.poll();
					if (context == null)
						continue;
					bufferedWriter.write(context);
					bufferedWriter.newLine();
					i++;
					System.out.println("total £º" + i);
				}
				if (queues.isEmpty()) {
					try {
						bufferedWriter.flush();
						bufferedWriter.close();
						outputStream.flush();
						outputStream.close();
						fileOutputStream.flush();
						fileOutputStream.close();
					} catch (Exception e) {
						e.printStackTrace(System.out);
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String exexl2txtAllfmt(String f) {
		if (f.endsWith(".xlsx"))
		return	excel2txtXlsVer_2007fmt_newfmt_retStr(f);
		if (f.endsWith(".xls"))
		return excel2txtXlsVer_2003fmt_oldVerFmt_retStr(f);;
		return f;
	}

	private static String excel2txtXlsVer_2003fmt_oldVerFmt_retStr(String f) {
		// TODO Auto-generated method stub
		return null;
	}

	private static String excel2txtXlsVer_2007fmt_newfmt_retStr(String f) {
		List<String> li=Lists.newArrayList();
		excel2txtFmt2007V2(f, new Consumer<String>() {

			@Override
			public void accept(String line) {
				 
					
					li.add(line);
				 
			

			}
		});
		return Joiner.on("\r\n").join( li);
	 
		
	}
}