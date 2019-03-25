import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import com.attilax.text.PinYin;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

@SuppressWarnings("all")
public class clrFileUtil {
	final static Logger log = Logger.getLogger(clrFileUtil.class);

	public static void main(String[] args) throws IOException {

		String srcdir = "G:\\prjdown zipext java codelib";
		// String srcdir = "C:\\Users\\zhoufeiyue\\Desktop\\prjdown_java";

		String mvedDir = "C:\\Users\\zhoufeiyue\\Desktop\\prjdownZipMved";
		searchMov(srcdir, mvedDir);
		
		// String srcdir="C:\\Users\\zhoufeiyue\\Desktop\\kechengbiao";
	//	Path startingDir = Paths.get(srcdir);

		
		// change2Enname(startingDir);
	//	deduliMovDulifileDir(srcdir, "g:\\prjdownZipDulipedFiledir");
		System.out.println("--f");

	}

	private static void deduliMovDulifileDir(String srcdir, String mvedDir) throws IOException {
		Set fileSizeLib = Sets.newLinkedHashSet();
		Files.walkFileTree(Paths.get(srcdir), new SimpleFileVisitor<Path>() {

			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

				try {

					Path absolutePath = file.toAbsolutePath();

					long filesize = file.toFile().length();
					String curFname = FilenameUtils.getBaseName(file.toString());
					String keyString = filesize + curFname;

					if (fileSizeLib.contains(keyString)) {
						// duli file
						String rltPathString = getRltpath(file.toAbsolutePath().toString(), srcdir);
						File destFile = new File(mvedDir + "\\" + rltPathString);
						FileUtils.moveFile(file.toFile(), destFile);

						log.info("move file:" + file);

					} else {
						fileSizeLib.add(keyString);
					}

					// fileSizeLib.put(filesize, value)

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				return FileVisitResult.CONTINUE;

			}
		});

	}

	private static void searchMov(String srcdir, String mvedDir) throws IOException {
		Files.walkFileTree(Paths.get(srcdir), new SimpleFileVisitor<Path>() {

			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				// TODO Auto-generated method stub
				// return super.preVisitDirectory(dir, attrs);
				// System.out.println("正在访问：" + dir + "目录");
				return FileVisitResult.CONTINUE;
			}

			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

				try {
					Path absolutePath = file.toAbsolutePath();
					String extname = FilenameUtils.getExtension(absolutePath.toString());
					if (!extname.equals("java")) {
						String rltPathString = getRltpath(file.toAbsolutePath().toString(), srcdir);

						File destFile = new File(mvedDir + "\\" + rltPathString);
						FileUtils.moveFile(file.toFile(), destFile);
						log.info("move file:" + file);
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				return FileVisitResult.CONTINUE;

			}
		});
	}

	protected static String getRltpath(String p, String pathHead) {
		// TODO Auto-generated method stub
		return p.substring(pathHead.length() + 1);
	}

	private static void change2Enname(Path startingDir) throws IOException {
		List<String> result = new LinkedList<String>();

		Files.walkFileTree(startingDir, new SimpleFileVisitor<Path>() {

			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				// TODO Auto-generated method stub
				// return super.preVisitDirectory(dir, attrs);
				System.out.println("正在访问：" + dir + "目录");
				return FileVisitResult.CONTINUE;
			}

			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Path absolutePath = file.toAbsolutePath();
				String path_pinyin = PinYin.getPinYin(absolutePath.toString());
				System.out.println(absolutePath);
				System.out.println(file.getFileName());
				FileUtils.copyFile(new File(file.toString()), new File(path_pinyin));
				return FileVisitResult.CONTINUE;
			}
		});

//        System.out.println("result.size()=" + result.size());
//        for (String name : result) {
//            System.out.println(name);
//        }
	}

	protected static String pinyin(String src) {
		// TODO Auto-generated method stub
		return PinYin.getPinYin(src);
	}

}
