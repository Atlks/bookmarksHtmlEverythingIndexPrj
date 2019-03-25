import java.io.File;
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

import org.apache.commons.io.FileUtils;

import com.attilax.text.PinYin;

public class changeFileName2en {

	public static void main(String[] args) throws IOException {

		Path startingDir = Paths.get("C:\\Users\\zhoufeiyue\\Documents\\WeChat Files\\attilax\\Files\\课程表(1)\\课程表");

		Files.walkFileTree(startingDir, new SimpleFileVisitor<Path>() {

			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				// TODO Auto-generated method stub
				// return super.preVisitDirectory(dir, attrs);
				// System.out.println("正在访问：" + dir + "目录");
				return FileVisitResult.CONTINUE;
			}

			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Path absolutePath = file.toAbsolutePath();
				String path_pinyin = PinYin.getPinYin(absolutePath.toString());

//				path_pinyin = path_pinyin.replaceAll("C:\\\\Users\\\\zhoufeiyue\\\\Desktop\\\\",
//						"http://file.chinesesr.com/");
//				path_pinyin = path_pinyin.replace('\\', '/');
				
				
 			System.out.println(path_pinyin);
				// System.out.println(file.getFileName());
				   FileUtils.copyFile(new File(file.toString()), new File(path_pinyin));
				return FileVisitResult.CONTINUE;
			}
		});
		// change2Enname(startingDir);

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
