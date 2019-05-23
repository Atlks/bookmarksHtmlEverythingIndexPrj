/**
 * 
 */
package com.attilax.img.other;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.opencv.core.Core;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import com.attilax.io.filex;
import com.attilax.json.AtiJson;
import com.attilax.net.URLEncoder;
import com.attilax.net.urlEncode;

public class TemplateMaching {
	public String sourcePath, dstPath;
	
	public String sourcePathUrlencoded, dstPathUrlencoded;
	
	public Mat source, dst;

	 

	// 原图片
	public void setSource(String picPath) {
		this.sourcePath = picPath;
	}

	// 需要匹配的部分
	public void setDst(String picPath) {
		this.dstPath = picPath;
	}

	// 处理，生成结果图
	public void process(String outpic, int matchMode) {
		// 将文件读入为OpenCV的Mat格式
		source = Highgui.imread(sourcePath);
		dst = Highgui.imread(dstPath);
		// 创建于原图相同的大小，储存匹配度
	//	CvType.cv
		Mat result = Mat.zeros(source.rows(), source.cols(), CvType.CV_32FC1);
		// 调用模板匹配方法

		Imgproc.matchTemplate(source, dst, result, matchMode); // TM_CCOEFF_NORMED
		// 规格化 rst= Mat [ 1005*1236*CV_32FC1, isCont=true, isSubmat=false,
		// nativeObj=0x57b3fca0, dataAddr=0x59e80050 ]
		Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1);
		// 获得最可能点，MinMaxLocResult是其数据格式，包括了最大、最小点的位置x、y
		System.out.println(AtiJson.toJson(result));
		MinMaxLocResult mlr = Core.minMaxLoc(result);
		Point matchLoc;
		if (matchMode == Imgproc.TM_SQDIFF || matchMode == Imgproc.TM_SQDIFF_NORMED) {
			matchLoc = mlr.minLoc;
		} else {
			matchLoc = mlr.maxLoc;

		}

		// 在原图上的对应模板可能位置画一个绿色矩形
		Core.rectangle(source, matchLoc, new Point(matchLoc.x + dst.width(), matchLoc.y + dst.height()), new Scalar(0, 255, 0));
		// 将结果输出到对应位置
		Highgui.imwrite(outpic, source);
	}

	public Point findMatchPoint(int matchMode  ) throws CantFindMatch, IOException {
		// 将文件读入为OpenCV的Mat格式
		if( !new File(sourcePath).exists())
			throw new FileNotFoundException(sourcePath);
		if( !new File(dstPath).exists())
			throw new FileNotFoundException(dstPath);
		
		source = Highgui.imread(sourcePath);
	//	System.out.println( AtiJson.toJson(source));
		if(source.cols()==0)
			throw new IOException(sourcePath);
		
		
		dst = Highgui.imread(dstPath);
		if(dst.cols()==0)
			throw new IOException(dstPath);
		// 创建于原图相同的大小，储存匹配度
		Mat result = Mat.zeros(source.rows(), source.cols(), CvType.CV_32FC1);
		// 调用模板匹配方法

		try {
			Imgproc.matchTemplate(source, dst, result, matchMode); // TM_CCOEFF_NORMED

		} catch (Exception e) {
			e.printStackTrace();
			throw new CantFindMatch(" cant find match",e);
		}

		// 规格化 rst= Mat [ 1005*1236*CV_32FC1, isCont=true, isSubmat=false,
		// nativeObj=0x57b3fca0, dataAddr=0x59e80050 ]
		Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1);
		// 获得最可能点，MinMaxLocResult是其数据格式，包括了最大、最小点的位置x、y
	//	System.out.println(AtiJson.toJson(result));
		MinMaxLocResult mlr = Core.minMaxLoc(result);
		Point matchLoc;
		if (matchMode == Imgproc.TM_SQDIFF || matchMode == Imgproc.TM_SQDIFF_NORMED) {
			matchLoc = mlr.minLoc;
		} else {
			matchLoc = mlr.maxLoc;

		}
		
		// 在原图上的对应模板可能位置画一个绿色矩形
		Point downPoint = new Point(matchLoc.x + dst.width(), matchLoc.y + dst.height());
		Core.rectangle(source, matchLoc, downPoint, new Scalar(0, 255, 0));
		String outpic=filex.addSuffix(sourcePath,"matched");
		// 将结果输出到对应位置
		Highgui.imwrite(outpic, source);
		
		
		if (matchLoc != null)
			return matchLoc;
		throw new CantFindMatch(" cant find match");

	}
	
	public Point matchTemplate(int matchMode  ) throws CantFindMatch, IOException {
		// 将文件读入为OpenCV的Mat格式
		if( !new File(sourcePath).exists())
			throw new FileNotFoundException(sourcePath);
		if( !new File(dstPath).exists())
			throw new FileNotFoundException(dstPath);
		
		source = Highgui.imread(sourcePath);
	//	System.out.println( AtiJson.toJson(source));
		if(source.cols()==0)
			throw new IOException(sourcePath);
		
		
		dst = Highgui.imread(dstPath);
		if(dst.cols()==0)
			throw new IOException(dstPath);
		// 创建于原图相同的大小，储存匹配度
		Mat result = Mat.zeros(source.rows(), source.cols(), CvType.CV_32FC1);
		// 调用模板匹配方法

		try {
			Imgproc.matchTemplate(source, dst, result, matchMode); // TM_CCOEFF_NORMED

		} catch (Exception e) {
			e.printStackTrace();
			throw new CantFindMatch(" cant find match",e);
		}

		// 规格化 rst= Mat [ 1005*1236*CV_32FC1, isCont=true, isSubmat=false,
		// nativeObj=0x57b3fca0, dataAddr=0x59e80050 ]
	 	Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1);
		// 获得最可能点，MinMaxLocResult是其数据格式，包括了最大、最小点的位置x、y
	//	System.out.println(AtiJson.toJson(result));
//		filex fx=new filex("d:\\mat.txt");
//		for(int i=0;i<result.rows();i++)
//			for(int j=0;j<result.cols();j++)
//			{
//				float[] data = new float[4];
//				float  pipeidu=result.get(i, j, data);
//				String line = "---pipeidu:"+pipeidu +" x_y:"+i+"_"+j +" ";
//				System.out.println(line);
//				fx.appendLine_flush_safe(line);
//			}
//		fx.close();
	 	Core.minm
		MinMaxLocResult mlr = Core.minMaxLoc(result);
		Point matchLoc;
		if (matchMode == Imgproc.TM_SQDIFF || matchMode == Imgproc.TM_SQDIFF_NORMED) {
			matchLoc = mlr.minLoc;
		} else {
			matchLoc = mlr.maxLoc;

		}
		System.out.println( "matchLoc:"+matchLoc);
		
		// 在原图上的对应模板可能位置画一个绿色矩形
		Point downPnt = new Point(matchLoc.x + dst.width(), matchLoc.y + dst.height());
		Scalar colorRGB = new Scalar(0, 255, 0);
		Core.rectangle(source, matchLoc, downPnt, colorRGB);
		String outpic=filex.addSuffix(sourcePath,"matched");
		// 将结果输出到对应位置
		Highgui.imwrite(outpic, source);
		
		
		if (matchLoc != null)
			return matchLoc;
		throw new CantFindMatch(" cant find match");

	}

	public static void main(String[] args) {
		// System.loadLibrary("opencv_java249"); F:\opencv_build_x64_vc12
		// bin\bin
		// System.load(
		// "C:\\progrm\\opencv\\build\\java\\x64\\opencv_java2413.dll" );

		String string = "F:\\opencv_build_x64_vc12 bin\\bin\\opencv_java2413.dll";
	//	string = "E:\\opencv\\opencv_java2413.dll";
		System.load(string);

		//Tsingle();
		 Tdir();

		// macher.process(filex.addSuffix(outpic,
		// "_TM_SQDIFF"),Imgproc.TM_SQDIFF);

		// macher.process(filex.addSuffix(outpic,
		// "_TM_SQDIFF_NORMED"),Imgproc.TM_SQDIFF_NORMED);
		//
		// macher.process(filex.addSuffix(outpic,
		// "_TM_CCORR"),Imgproc.TM_CCORR);
		// macher.process(filex.addSuffix(outpic,
		// "_TM_CCORR_NORMED"),Imgproc.TM_CCORR_NORMED);
		//
		// macher.process(filex.addSuffix(outpic,
		// "_TM_CCOEFF"),Imgproc.TM_CCOEFF);
		// macher.process(filex.addSuffix(outpic,
		// "_TM_CCOEFF_NORMED"),Imgproc.TM_CCOEFF_NORMED);
		System.out.println("--f");
	}

	/**
	 * attilax 2017年1月21日 下午11:07:14
	 */
	private static void Tsingle() {
		String tmp_pic = "f:\\0img\\t1.jpg"; // ver mini

 

		String bigimg = "C:\\0img\\a.jpg";
		TemplateMaching TM = new TemplateMaching();
		// 设置原图

		TM.setSource(bigimg);
		// 设置要匹配的图
		// String tmp_pic ="C:\\0img\\t3_saovei_mini.jpg";

		TM.setDst(tmp_pic);
		try {
			Point pnt = TM.findMatchPoint(Imgproc.TM_SQDIFF);
			System.out.println("   find in file:" + bigimg);
		} catch (CantFindMatch e) {

			System.out.println(" cant find in file:" + bigimg);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// String outpic ="C:\\0img\\tmpl_out.jpg";

	}

	private static void Tdir() {
		String tmp_pic = "f:\\fmspaint\\t.jpg"; // ver mini

		String dbDir = "f:\\fmspaint";
		File f = new File(dbDir);
		File[] fs = f.listFiles();
		for (File file : fs) {
			if (file.length() == new File(tmp_pic).length())
				continue;

			String bigimg = file.getAbsolutePath();
			String bigimg_urlencoded=URLEncoder.encode(bigimg);
			TemplateMaching TM = new TemplateMaching();
			// 设置原图

			TM.setSource(bigimg);
			TM.sourcePathUrlencoded=(bigimg_urlencoded);
			// 设置要匹配的图
			// String tmp_pic ="C:\\0img\\t3_saovei_mini.jpg";

			TM.setDst(tmp_pic);
			TM.dstPathUrlencoded=URLEncoder.encode(tmp_pic);
			try {
				Point pnt = TM.findMatchPoint(Imgproc.TM_SQDIFF);
				System.out.println("   find in file:" + file.getAbsolutePath());
			} catch (CantFindMatch e) {

				System.out.println(" cant find in file:" + file.getAbsolutePath());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// String outpic ="C:\\0img\\tmpl_out.jpg";

		}
	}
}