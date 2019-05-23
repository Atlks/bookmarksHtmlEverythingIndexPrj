package com.attilax.compress;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.zip.ZipException;

 
import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import com.attilax.io.FileUtils;
import com.attilax.exception.ExUtil;
import com.attilax.io.pathx;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/** */
/**
 * 鍘嬬缉/瑙ｅ帇缂﹝ip鍖呭鐞嗙被
 * 
 * @author yayagepei
 * @date 2008-8-25
 */

public class ZipUtilV2t55 {
	protected static Logger logger = Logger.getLogger(ZipUtilV2t55.class);

	/** */
	/**
	 * 鍘嬬缉
	 * 
	 * @param zipFileName
	 *            鍘嬬缉浜х敓鐨剒ip鍖呮枃浠跺悕--甯﹁矾寰�,濡傛灉涓簄ull鎴栫┖鍒欓粯璁ゆ寜鏂囦欢鍚嶇敓浜у帇缂╂枃浠跺悕
	 * @param relativePath
	 *            鐩稿璺緞锛岄粯璁や负绌�
	 * @param directory
	 *            鏂囦欢鎴栫洰褰曠殑缁濆璺緞
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @author yayagepei
	 * @date 2008-8-26
	 */
	public static void zip(String zipFileName, String relativePath, String directory)
			throws FileNotFoundException, IOException {
		String fileName = zipFileName;
		if (fileName == null || fileName.trim().equals("")) {
			File temp = new File(directory);
			if (temp.isDirectory()) {
				fileName = directory + ".zip";
			} else {
				if (directory.indexOf(".") > 0) {
					fileName = directory.substring(0, directory.lastIndexOf(".")) + "zip";
				} else {
					fileName = directory + ".zip";
				}
			}
		}
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(fileName));
		try {
			zip(zos, relativePath, directory);
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (null != zos) {
				zos.close();
			}
		}
	}

	/** */
	/**
	 * 鍘嬬缉
	 * 
	 * @param zos
	 *            鍘嬬缉杈撳嚭娴�
	 * @param relativePath
	 *            鐩稿璺緞
	 * @param absolutPath
	 *            鏂囦欢鎴栨枃浠跺す缁濆璺緞
	 * @throws IOException
	 * @author yayagepei
	 * @date 2008-8-26
	 */
	private static void zip(ZipOutputStream zos, String relativePath, String absolutPath) throws IOException {
		File file = new File(absolutPath);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				File tempFile = files[i];
				if (tempFile.isDirectory()) {
					String newRelativePath = relativePath + tempFile.getName() + File.separator;
					createZipNode(zos, newRelativePath);
					zip(zos, newRelativePath, tempFile.getPath());
				} else {
					zipFile(zos, tempFile, relativePath);
				}
			}
		} else {
			zipFile(zos, file, relativePath);
		}
	}

	/** */
	/**
	 * 鍘嬬缉鏂囦欢
	 * 
	 * @param zos
	 *            鍘嬬缉杈撳嚭娴�
	 * @param file
	 *            鏂囦欢瀵硅薄
	 * @param relativePath
	 *            鐩稿璺緞
	 * @throws IOException
	 * @author yayagepei
	 * @date 2008-8-26
	 */
	private static void zipFile(ZipOutputStream zos, File file, String relativePath) throws IOException {
		ZipEntry entry = new ZipEntry(relativePath + file.getName());
		zos.putNextEntry(entry);
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			int BUFFERSIZE = 2 << 10;
			int length = 0;
			byte[] buffer = new byte[BUFFERSIZE];
			while ((length = is.read(buffer, 0, BUFFERSIZE)) >= 0) {
				zos.write(buffer, 0, length);
			}
			zos.flush();
			zos.closeEntry();
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (null != is) {
				is.close();
			}
		}
	}

	/** */
	/**
	 * 鍒涘缓鐩綍
	 * 
	 * @param zos
	 *            zip杈撳嚭娴�
	 * @param relativePath
	 *            鐩稿璺緞
	 * @throws IOException
	 * @author yayagepei
	 * @date 2008-8-26
	 */
	private static void createZipNode(ZipOutputStream zos, String relativePath) throws IOException {
		ZipEntry zipEntry = new ZipEntry(relativePath);
		zos.putNextEntry(zipEntry);
		zos.closeEntry();
	}

	public static void main(String[] args) throws IOException {
//		String f = "C:\\00\\FlashFXP_4.3.1.1969_ati.zip";
//		String f2 = "C:\\00";
//		// unzip(f,f2);
//		f = "C:\\0wkspc\\绉诲姩鍖荤枟婧愮爜\\绉诲姩鍖绘姢\\绉诲姩鎶ゅ＋绔橽\honurse\\holib\\activemq\\activemq-all-5.11.1.jar";
//		System.out.println(unzip_filelist(f));
	}

	/** */
	/**
	 * 瑙ｅ帇缂﹝ip鍖�
	 * 
	 * @param zipFilePath
	 *            zip鏂囦欢璺緞
	 * @param targetPath
	 *            瑙ｅ帇缂╁埌鐨勪綅缃�,linba ma
	 *            fesyegeor锛屽鏋滀负null鎴栫┖瀛楃涓插垯榛樿瑙ｅ帇缂╁埌璺焭ip鍖呭悓鐩綍璺焭ip鍖呭悓鍚嶇殑鏂囦欢澶逛笅
	 * @throws IOException
	 * @author yayagepei
	 * @date 2008-9-28
	 */
	public static String unzip_filelist(String zipFilePath) {
		OutputStream os = null;
		InputStream is = null;
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(zipFilePath);
			String directoryPath = "";

			Enumeration entryEnum = zipFile.getEntries();
			if (null == entryEnum)
				return "";

			ZipEntry zipEntry = null;
			while (entryEnum.hasMoreElements()) {
				zipEntry = (ZipEntry) entryEnum.nextElement();
				// FlashFXP_4.3.1.1969_ati/What's new.rtf
				System.out.println(zipEntry);
				System.out.println(zipEntry.getName());
				System.out.println("============");

			}
			/// end while

		} catch (IOException ex) {
			ExUtil.throwExV2(ex);
		}
		return zipFilePath;
	}

	public static List<String> filelistV3s99(String zipFilePath, String encode) {
		List<String> li = Lists.newArrayList();
		OutputStream os = null;
		InputStream is = null;
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(zipFilePath, encode);
			// String encode= zipFile.getEncoding(); null

			String directoryPath = "";

			Enumeration Enumeration_item = zipFile.getEntries();

			if (null == Enumeration_item)
				return li;

			ZipEntry zipEntry = null;
			while (Enumeration_item.hasMoreElements()) {
				zipEntry = (ZipEntry) Enumeration_item.nextElement();

				// FlashFXP_4.3.1.1969_ati/What's new.rtf
				System.out.println(zipEntry);
				// zipEntry.get
				System.out.println(zipEntry.getName());
				System.out.println("============");
				li.add(zipEntry.getName());

			}
			/// end while

		} catch (IOException ex) {
			ExUtil.throwExV2(ex);
		}
		return li;
	}

	public static List<String> filelist(String zipFilePath, String encode,Consumer<Map> Consumer1) {
		List<String> li = Lists.newArrayList();
		OutputStream os = null;
		InputStream is = null;
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(zipFilePath, encode);
			// String encode= zipFile.getEncoding(); null

			String directoryPath = "";

			Enumeration<ZipEntry> Enumeration_item = zipFile.getEntries();

			if (null == Enumeration_item)
				return li;

			ZipEntry zipEntry = null;
			while (Enumeration_item.hasMoreElements()) {
				zipEntry = (ZipEntry) Enumeration_item.nextElement();
				Map m=Maps.newLinkedHashMap();
				m.put("zipEntry", zipEntry);
				m.put("zipFile",zipFile);
				Consumer1.accept(m);
				 

			}
			/// end while

		} catch (IOException ex) {
			ExUtil.throwExV2(ex);
		}
		return li;
	}

	public static List<String> unzip_filelistV2(String zipFilePath) {
		List<String> li = Lists.newArrayList();
		OutputStream os = null;
		InputStream is = null;
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(zipFilePath);

			String directoryPath = "";

			Enumeration entryEnum = zipFile.getEntries();
			if (null == entryEnum)
				return li;

			ZipEntry zipEntry = null;
			while (entryEnum.hasMoreElements()) {
				zipEntry = (ZipEntry) entryEnum.nextElement();

				// FlashFXP_4.3.1.1969_ati/What's new.rtf
				System.out.println(zipEntry);
				System.out.println(zipEntry.getName());
				System.out.println("============");
				li.add(zipEntry.getName());

			}
			/// end while

		} catch (IOException ex) {
			ExUtil.throwExV2(ex);
		}
		return li;
	}

	public static void unzip_throwRE(String zipFilePath, String targetPath) {
		try {
			unzipV2(zipFilePath, targetPath);
		} catch (Exception e) {

			e.printStackTrace();
			ExUtil.throwExV2(e);
		}
	}

	/**
	 * upzip to cur dir
	 * 
	 * @param zipFilePath
	 * @param runnableImp
	 */
	public static void unzipZipoisit(String zipFilePath, Function runnableImp) {
		unzipV3(zipFilePath, null, runnableImp);
	}

	public static void unzipZipoisit(String zipFilePath) {
		unzipV2(zipFilePath, null);
	}

	/** */
	/**
	 * 瑙ｅ帇缂﹝ip鍖�
	 * 
	 * @param zipFilePath
	 *            zip鏂囦欢璺緞
	 * @param targetPath
	 *            瑙ｅ帇缂╁埌鐨勪綅缃�,linba ma
	 *            fesyegeor锛屽鏋滀负null鎴栫┖瀛楃涓插垯榛樿瑙ｅ帇缂╁埌璺焭ip鍖呭悓鐩綍璺焭ip鍖呭悓鍚嶇殑鏂囦欢澶逛笅
	 * @throws IOException
	 * @author yayagepei
	 * @date 2008-9-28
	 */
	public static void unzipV3(String zipFilePath, String targetPath, Function runnableImp) {

		try {
			ZipFile zipFile = null;
			zipFile = new ZipFile(zipFilePath);

			String directoryPath = "";
			if (null == targetPath || "".equals(targetPath)) {
				directoryPath = new File(zipFilePath).getParent();
			} else {
				directoryPath = targetPath;
			}
			Enumeration entryEnum = zipFile.getEntries();
			if (null == entryEnum) {
				return;
			}

			while (entryEnum.hasMoreElements()) {

				ZipEntry zipEntry = (ZipEntry) entryEnum.nextElement();
				runnableImp.apply(zipEntry.getName());
				upzipSingle(zipFile, directoryPath, zipEntry);
			}
			/// end while

		} catch (IOException ex) {

			ExUtil.throwExV2(ex);

		}
	}

	/** */
	/**
	 * 瑙ｅ帇缂﹝ip鍖�
	 * 
	 * @param zipFilePath
	 *            zip鏂囦欢璺緞
	 * @param targetPath
	 *            瑙ｅ帇缂╁埌鐨勪綅缃�,linba ma
	 *            fesyegeor锛屽鏋滀负null鎴栫┖瀛楃涓插垯榛樿瑙ｅ帇缂╁埌璺焭ip鍖呭悓鐩綍璺焭ip鍖呭悓鍚嶇殑鏂囦欢澶逛笅
	 * @throws IOException
	 * @author yayagepei
	 * @date 2008-9-28
	 */
	public static void unzipV2(String zipFilePath, String targetPath) {

		try {
			ZipFile zipFile = null;
			zipFile = new ZipFile(zipFilePath);
			String directoryPath = "";
			if (null == targetPath || "".equals(targetPath)) {
				directoryPath = new File(zipFilePath).getParent();
			} else {
				directoryPath = targetPath;
			}
			Enumeration entryEnum = zipFile.getEntries();
			if (null == entryEnum) {
				return;
			}

			while (entryEnum.hasMoreElements()) {

				ZipEntry zipEntry = (ZipEntry) entryEnum.nextElement();
				upzipSingle(zipFile, directoryPath, zipEntry);
			}
			/// end while

		} catch (IOException ex) {

			ExUtil.throwExV2(ex);

		}
	}

	private static void upzipSingle(ZipFile zipFile, String directoryPath, ZipEntry zipEntry)
			throws IOException, FileNotFoundException, ZipException {

		OutputStream os = null;
		InputStream is = null;

		// FlashFXP_4.3.1.1969_ati/What's new.rtf // dir is WebContent/
		logger.info("============");
		String zipentry1_name = zipEntry.getName();
		if (!pathx.isWindows()) {
			zipentry1_name = pathx.fixSlash(zipentry1_name);
		}
		logger.info("zip filewzpath:" + zipentry1_name);

		String fileName = directoryPath + File.separator + zipentry1_name;
		if (zipEntry.isDirectory()) {
			String upzipdir = fileName;
			org.apache.commons.io.FileUtils.forceMkdir(new File(upzipdir));
			logger.info("upzip dir:" + upzipdir);
			return;
		}
		// zipfile is file not dir
		if (!zipEntry.isDirectory() && zipEntry.getSize() > 0) {
			// FileUtil
			// 鏂囦欢
			boolean isNotDirectory = false;
			logger.info("upzip file:" + fileName);
			File targetFile = FileUtils.buildFile(fileName, isNotDirectory);
			upzipSingleOutput(zipFile, zipEntry, targetFile);
		}
		if (!zipEntry.isDirectory() && zipEntry.getSize() == 0) // empty file
		{
			// 绌篺ile
			logger.info("upzip file empty :" + fileName);
			FileUtils.buildFile(fileName, true);
		}
	}

	public static void upzipSingleOutput(ZipFile zipFile, ZipEntry zipEntry, File targetFile)
			throws FileNotFoundException, IOException, ZipException {
		OutputStream os;
		InputStream is;
		os = new BufferedOutputStream(new FileOutputStream(targetFile));
		is = zipFile.getInputStream(zipEntry);
		byte[] buffer = new byte[4096];
		int readLen = 0;
		while ((readLen = is.read(buffer, 0, 4096)) >= 0) {
			os.write(buffer, 0, readLen);
		}

		os.flush();
		os.close();
	}

	private static void close_isNos(OutputStream os, InputStream is, ZipFile zipFile) {
		if (null != zipFile) {
			zipFile = null;
		}
		if (null != is) {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (null != os) {
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/** */
	/**
	 * 瑙ｅ帇缂﹝ip鍖�
	 * 
	 * @param zipFilePath
	 *            zip鏂囦欢璺緞
	 * @param targetPath
	 *            瑙ｅ帇缂╁埌鐨勪綅缃�,linba ma
	 *            fesyegeor锛屽鏋滀负null鎴栫┖瀛楃涓插垯榛樿瑙ｅ帇缂╁埌璺焭ip鍖呭悓鐩綍璺焭ip鍖呭悓鍚嶇殑鏂囦欢澶逛笅
	 * @throws IOException
	 * @author yayagepei
	 * @date 2008-9-28
	 */
	public static void unzip(String zipFilePath, String targetPath) throws IOException {
		OutputStream os = null;
		InputStream is = null;
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(zipFilePath);
			String directoryPath = "";
			if (null == targetPath || "".equals(targetPath)) {
				directoryPath = zipFilePath.substring(0, zipFilePath.lastIndexOf("."));
			} else {
				directoryPath = targetPath;
			}
			Enumeration entryEnum = zipFile.getEntries();
			if (null != entryEnum) {
				ZipEntry zipEntry = null;
				while (entryEnum.hasMoreElements()) {
					zipEntry = (ZipEntry) entryEnum.nextElement();
					// FlashFXP_4.3.1.1969_ati/What's new.rtf
					System.out.println(zipEntry);
					System.out.println(zipEntry.getName());
					System.out.println("============");
					if (zipEntry.isDirectory()) {
						directoryPath = directoryPath + File.separator + zipEntry.getName();
						System.out.println(directoryPath);
						continue;
					}
					if (zipEntry.getSize() > 0) {
						// FileUtil
						// 鏂囦欢
						File targetFile = FileUtils.buildFile(directoryPath + File.separator + zipEntry.getName(),
								false);
						upzipSingleOutput(zipFile, zipEntry, targetFile);
					} else {
						// 绌虹洰褰�
						FileUtils.buildFile(directoryPath + File.separator + zipEntry.getName(), true);
					}
				}
				/// end while
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (null != zipFile) {
				zipFile = null;
			}
			if (null != is) {
				is.close();
			}
			if (null != os) {
				os.close();
			}
		}
	}

}
