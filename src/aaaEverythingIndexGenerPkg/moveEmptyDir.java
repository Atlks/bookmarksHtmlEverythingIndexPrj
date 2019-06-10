package aaaEverythingIndexGenerPkg;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class moveEmptyDir {
	
	public static void main(String[] args) throws IOException {
		String dir="D:\\000atibek\\l6 foto vc s59 seris netcc\\seri";
		File[] fa=new File(dir).listFiles();
		for (File file : fa) {
			if(file.isDirectory())
			{
				if(isEmptydir(file))
					FileUtils.moveDirectoryToDirectory(file, new File("D:\\000atibek\\l6 foto vc s59 seris netcc\\emaptydirs"), true);
			}
		}
	}

	private static boolean isEmptydir(File dir) {
		File[] fa=dir.listFiles();
		if(fa.length==0)
			return true;
		else
		return false;
	}

}
