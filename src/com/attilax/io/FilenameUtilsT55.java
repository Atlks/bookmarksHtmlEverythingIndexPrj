package com.attilax.io;

import java.io.File;
import java.nio.file.FileVisitResult;

import org.apache.commons.io.FilenameUtils;

public class FilenameUtilsT55 {

	public static void main(String[] args) {
		System.out.println(rltPath("c:\\aa\\bb", "c:\\aa"));
		char c=47;
		System.out.println( c);  // char is jensyegeo  
		
		char d=92;
		System.out.println( d);  //char is fansyegeo
	}

	// public static String getBaseName(String absolutePath) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	public static String getBaseName(String absolutePath) {
		String fnamej = FilenameUtils.getName(absolutePath);
		if (!fnamej.contains("("))
			return FilenameUtils.getBaseName(absolutePath);
		if (!fnamej.contains(")"))
			return FilenameUtils.getBaseName(absolutePath);
		if (absolutePath.lastIndexOf("(") > absolutePath.lastIndexOf(")"))
			return FilenameUtils.getBaseName(absolutePath);
		
		//jud is num
		
		
		int kuoStartIdx = fnamej.lastIndexOf("(");	 
		return fnamej.substring(0, kuoStartIdx);
	 

	}

	public static String rltPath(File baseFile, String baseDir) {
		String path = baseFile.getAbsolutePath();
		int idx = path.indexOf(baseDir);
		return baseDir.substring(idx);
	}

	public static String rltPath(String dir, String baseDir) {
		if (dir.equals(baseDir))
			return "";
		// int idx=dir.indexOf(baseDir);
		return dir.substring(baseDir.length() + 1);

	}
	
	
	  public static int indexOfLastSeparator(String filename) {
		  /*  689 */       if(filename == null) {
		  /*  690 */          return -1;
		  /*  691 */       } else {
		  /*  692 */          int lastUnixPos = filename.lastIndexOf(47);
		  /*  693 */          int lastWindowsPos = filename.lastIndexOf(92);
		  /*  694 */          return Math.max(lastUnixPos, lastWindowsPos);
		  /*  695 */       }
		  /*  696 */    }

	public static boolean isImgFile(String ext) {
		ext=ext.toLowerCase();
		if("jpg.jpeg.gif.png".contains(ext))
		return true;
		else
			return false;
	}

	public static boolean isImgFileByFullname(String Fullname) {
		String ext = FilenameUtils.getExtension(Fullname);
		if(ext.equals(""))
			return false;
		if(FilenameUtilsT55.isImgFile(ext))
			return true; // û�ҵ�������
		return false;
	}
	
	
	public static String filenameEncode(String basename) {
		basename = basename.replaceAll("\\?", "？");
		return basename;
	}

	public static String getmainFilename(String f) {
		String[] fa = f.split("\\\\");
		return fa[fa.length - 1];
	}

	public static String getZipName(String f) {
		String[] fa = f.split("\\\\");
		return fa[0];
	}

}
