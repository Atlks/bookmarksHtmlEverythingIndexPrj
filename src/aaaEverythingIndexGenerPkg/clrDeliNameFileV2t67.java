package aaaEverythingIndexGenerPkg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.NDC;

import com.alibaba.fastjson.JSON;
import com.attilax.io.FilenameUtilsT55;
//import com.attilax.io.filex;
import com.attilax.util.CantFindEx;
import com.google.common.collect.Maps;

public class clrDeliNameFileV2t67 {
	static org.apache.log4j.Logger logger = Logger.getLogger(FileTraveList.class);
	// static static walkFileTreeLogger
	static int listSum;

	public static void main(String[] args) throws Exception {
		// t1("D:\\l3 sumdoc s2018 torb31 v2 t1_filelist.txt");
		// trave_dir("C:\\Users\\Administrator\\Documents\\law res
		// 法学资源库","d:\\law res 法学资源库clrdeduli");

		trave_dir("D:\\0gif sexy\\2019billpic\\bill t1 final\\bill  t1", "d:\\billt1Duli");
		// trave_dir("C:\\Users\\Administrator\\Documents\\law res
		// 法学资源库","d:\\law res 法学资源库clrdeduli3");

		System.out.println("--");
	}

	private static void trave_dir(String dir_source, String dirout) throws Exception {
		// 处理下级多层目录
		Files.walkFileTree(Paths.get(dir_source), new SimpleFileVisitor<Path>() {
 

			// 处理文件
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

				// walkFile log
				String fpath = file.toString();
				if (fpath.contains("是印度最古老的一部法律文献"))
					System.out.println("dbg");
				String string = "\t正在访问" + fpath + "文件";
				System.out.println(string);
				logger.info(string);
				String ext = FilenameUtils.getExtension(file.toFile().getAbsolutePath());
				String basenameBase = FilenameUtilsT55.getBaseName(file.toFile().getAbsolutePath());
				String dirParent = file.toFile().getParent();
				String baseFile = dirParent + "\\" + basenameBase + "." + ext;
				logger.info("baseFile will" + baseFile);
				String baseFile1 = dirParent + "\\" + basenameBase + "(1)." + ext;
				Consumer<Map> fileConsumer1 = (paraM) -> {
					String rltPath = FilenameUtilsT55.rltPath(dirParent, dir_source);
					// String
					// destFpath=dirout+"\\"+rltPath+"\\"+basenameBase+"("+i+")."+ext;
					Map traceM = Maps.newLinkedHashMap();
					traceM.put("act", "moveFile");
					traceM.put("baseFile", paraM);
					logger.info(JSON.toJSONString(traceM, true));
					try {
						// copyFile
						FileUtils.moveFile((File) paraM.get("f"), (File) paraM.get("destFpath"));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.exit(0);
					}
					// duliFiles log

				};
				if (new File(baseFile).exists()) {
					process_duliFiles(new File(baseFile), new File(dirout), 1, 50, dir_source, fileConsumer1);
				} else {
					try {
						process_duliFiles_byTop1(new File(baseFile), new File(dirout), 50, dir_source, fileConsumer1);
					} catch (CantFindEx e) {
						logger.error(e);
					}
				}

				return FileVisitResult.CONTINUE; // 没找到继续找
			}

		});

		// total log
		// Map traceM=Maps.newLinkedHashMap();
		// traceM.put("file", file);
		// traceM.put("listSum", listSum);
		// logger.info(JSON.toJSONString(traceM));

		// 处理目录

	}

	protected static void process_duliFiles_byTop1(File baseFile, File destDir, int end, String baseDir,
			Consumer<Map> fileConsumer1) throws CantFindEx, IOException {
		String dir = baseFile.getParent();
		String basenameBase = FilenameUtilsT55.getBaseName(baseFile.getAbsolutePath());
		String ext = FilenameUtils.getExtension(baseFile.getAbsolutePath());
		String rltPath = FilenameUtilsT55.rltPath(dir, baseDir);
		int start;

		start = getStartIdexExistfileMinidx(baseFile, end);
		process_duliFiles(baseFile, destDir, start + 1, end, baseDir, fileConsumer1);

	}

	private static int getStartIdexExistfileMinidx(File baseFile, int end) throws CantFindEx {

		String dir = baseFile.getParent();
		String basenameBase = FilenameUtils.getBaseName(baseFile.getAbsolutePath());
		String ext = FilenameUtils.getExtension(baseFile.getAbsolutePath());
		for (int i = 1; i < end; i++) {
			String f = dir + "\\" + basenameBase + "(" + i + ")." + ext;
			if (new File(f).exists())
				return i;

		}
		throw new CantFindEx(baseFile.toString());
	}

	protected static void process_duliFiles(File baseFile, File destDir, int start, int end, String baseDir,
			Consumer<Map> fileConsumer1) throws IOException {
		if (baseFile.toString().contains("是印度最古老的一部法律文献"))
			System.out.println("dbg");

		String dir_baseFile_getParent = baseFile.getParent();
		String basenameBase = FilenameUtils.getBaseName(baseFile.getAbsolutePath());
		String ext = FilenameUtils.getExtension(baseFile.getAbsolutePath());
		String rltPath = FilenameUtilsT55.rltPath(dir_baseFile_getParent, baseDir);
		for (int i = start; i < end; i++) {
			String f = dir_baseFile_getParent + "\\" + basenameBase + "(" + i + ")." + ext;

			String destFpath = destDir + "\\" + rltPath + "\\" + basenameBase + "(" + i + ")." + ext;
			if (new File(f).exists()) {
				Map paramM = Maps.newLinkedHashMap();
				paramM.put("f", new File(f));
				paramM.put("destFpath", new File(destFpath));
				paramM.put("start", start);
				paramM.put("end", end);
				paramM.put("curIdx", i);
				fileConsumer1.accept(paramM);
			}

			/// FileUtils.moveFile(new File(f), new File(destFpath));
			// FileUtils.moveFileToDirectory(new File(f), destDir, true);

		}
		// File[] subfiles=new File(dir).listFiles();
		// for (File subf : subfiles) {
		//
		// }

	}

	static void logCurfile(Path file, List<String> lines) {
		Map m2 = Maps.newLinkedHashMap();
		m2.put("dirlistFilename", file.toFile().getAbsolutePath());
		m2.put("lines_size", lines.size());
		m2.put("listSum", listSum);

		NDC.push(JSON.toJSONString(m2));
		MDC.put("dirlistFilename", file.toFile().getAbsolutePath());
		MDC.put("lines_size", lines.size());
		MDC.put("listSum", listSum);
		logger.info(JSON.toJSONString(m2));
		MDC.remove("lines_size"); // NDC.remove --> MDC.remove(key)
		NDC.remove(); // 离开方法删除tag
	}

	

	private static void t1(String file) throws IOException {
		// String file="D:\\00l3 sum doc s2018 until9 doc list.txt";
		// file="C:\\Users\\Administrator\\Desktop\\sumdoclist\\00l3 sum doc all
		// doc list.txt";
		// file="";

		List<String> li = FileUtils.readLines(new File(file), "utf8");
		for (String f : li) {
			// line=line.replaceAll("/", "__");
			String zipname = FilenameUtilsT55.getZipName(f);

			String filename =FilenameUtilsT55. getmainFilename(f);

			String newFile = zipname + "__" + filename;
			try {
				FileUtils.writeStringToFile(new File("d:\\l4 sumdoc s20180901-1231 everthing index\\" + newFile),
						"aaa");
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println(newFile);
		}
	}

}
