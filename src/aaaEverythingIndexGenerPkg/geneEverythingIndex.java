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

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.NDC;

import com.alibaba.fastjson.JSON;
import com.attilax.io.filex;
import com.google.common.collect.Maps;

public class geneEverythingIndex {
	static org.apache.log4j.Logger logger = Logger.getLogger(FileTraveList.class);
	//static static walkFileTreeLogger
	static int listSum;
	public static void main(String[] args) throws IOException {
	 	trave_txt("D:\\l3 sumdoc s2018 torb31 v2 t1_filelist.txt");
//		trave_dir("C:\\Users\\Administrator\\Documents\\sumdoc 2019 zipver","d:\\00sumdoc-2019-everythingIndexOutput");
//		String f = "s9 doc compc v2 s025\\河北英创科技有限公司测评资料\\短信接口安全评测资料补充\\Atitit 项目工作常见问题与 应急预案(1).docx";
//		String zipname = getZipName(f);
//
//		String filename = getmainFilename(f);
//
//		String newFile = zipname + "__" + filename;
//		System.out.println(newFile);
		System.out.println("--");
	}

	private static void trave_dir(String dir, String dirout) throws IOException {
		// 处理下级多层目录
				Files.walkFileTree(Paths.get(dir), new SimpleFileVisitor<Path>() {

					@Override // 处理目录
					public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
						System.out.println(dir);
						return FileVisitResult.CONTINUE;

					}

					// 处理文件
					public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

						// return super.visitFile(file, attrs);
						String fpath = file.toString();
						String string = "\t正在访问" + fpath + "文件";
						System.out.println(string);
						logger.info(string);
						String ext=FilenameUtils.getExtension(file.toFile().getAbsolutePath());
						//System.out.println(ext);
						if(!ext.equals("txt"))
							return FileVisitResult.CONTINUE; // 没找到继续找
						
						
						
						List<String> lines=FileUtils.readLines(file.toFile(),"gbk");
						listSum=listSum+lines.size();
						logCurfile(file, lines);
						int linenum=0;
						for (String line : lines) {
							linenum++;
							String fileSingle=line.trim();
							
							try {
								Map m=Maps.newLinkedHashMap();
								m.put("dirlistFilename",file.toFile().getAbsolutePath());
								m.put("linenum",linenum);
								m.put("linenumSource",line);
								
								String basename=FilenameUtils.getBaseName(fileSingle); 
								basename=filenameEncode(basename);
								String everythingIndexFilename=dirout+"\\"+basename+".plshdr.txt";
								logger.info(everythingIndexFilename);
								File file2 = new File(everythingIndexFilename); 
								//should be all json struts..
								FileUtils.writeStringToFile(file2, "\r\n"+JSON.toJSONString(m, true), true);
							} catch (Exception e) {
								logger.error(e);
							}
							
						}
						
						
						return FileVisitResult.CONTINUE; // 没找到继续找
					}

					

				});
				
				
				Map traceM=Maps.newLinkedHashMap();
				traceM.put("dir", dir);
				traceM.put("listSum", listSum);
				logger.info(JSON.toJSONString(traceM));

				// 处理目录
		
	}
	
	
	static void logCurfile(Path file, List<String> lines) {
		Map m2=Maps.newLinkedHashMap();
		m2.put("dirlistFilename",file.toFile().getAbsolutePath());
		m2.put("lines_size",lines.size());
		m2.put("listSum",listSum);
		
		NDC.push(JSON.toJSONString(m2));
		MDC.put("dirlistFilename", file.toFile().getAbsolutePath());
		MDC.put("lines_size", lines.size());
		MDC.put("listSum",listSum);
		logger.info(JSON.toJSONString(m2));
		MDC.remove("lines_size"); //NDC.remove --> MDC.remove(key)
		  NDC.remove(); //离开方法删除tag
	}

	protected static String filenameEncode(String basename) {
		basename=basename.replaceAll("\\?", "？");
		return basename;
	}

	private static String getmainFilename(String f) {
		String[] fa = f.split("\\\\");
		return fa[fa.length - 1];
	}

	private static String getZipName(String f) {
		String[] fa = f.split("\\\\");
		return fa[0];
	}

	private static void trave_txt((String file) throws IOException {
		// String file="D:\\00l3 sum doc s2018 until9 doc list.txt";
		// file="C:\\Users\\Administrator\\Desktop\\sumdoclist\\00l3 sum doc all
		// doc list.txt";
		// file="";

		List<String> li = FileUtils.readLines(new File(file), "utf8");
		for (String f : li) {
			// line=line.replaceAll("/", "__");
			String zipname = getZipName(f);

			String filename = getmainFilename(f);

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
