package aaaEverythingIndexGenerPkg;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class FileTraveList {
	static org.apache.log4j.Logger logger = Logger.getLogger(FileTraveList.class);

	public static void main(String[] args) throws IOException {
		//String prefix = "D:\\000000\\Axure8.0\\";
		String dir = "D:\\000atibek\\l3 sumdoc s2018 torb31 v2 t1";
		String	prefix = dir;
		String listrztfile = "d:\\l3 sumdoc s2018 torb31 v2 t1_filelist.txt";
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
				System.out.println("\t正在访问" + fpath + "文件");
				// get rlt path
				String fpath2 = fpath.substring(prefix.length() +1, fpath.length());
				System.out.println("\t正在访问 relt :" + fpath2 + "文件");

				FileUtils.writeStringToFile(new File(listrztfile), fpath2 + "\r\n", true);
				return FileVisitResult.CONTINUE; // 没找到继续找
			}

		});
		logger.info(dir);

		// 处理目录
	}

}
