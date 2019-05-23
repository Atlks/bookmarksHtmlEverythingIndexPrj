package com.attilax.img.util;

public class SimilarTest {

	public static void main(String[] args) {
		String string = "F:\\opencv_build_x64_vc12 bin\\bin\\opencv_java2413.dll";
		// string = "E:\\opencv\\opencv_java2413.dll";
		string = "C:\\progrm\\opencv\\build\\java\\x64\\opencv_java2413.dll";
		System.load(string);
		
		
		
		String srcFile="C:\\000oil\\亚当与上帝.jpg";
		String desFile=srcFile;
	System.out.println(OpencvUtil.comPareHist(srcFile, desFile));	;
	 
	}

}
