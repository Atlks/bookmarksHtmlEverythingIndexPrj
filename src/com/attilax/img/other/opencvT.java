package com.attilax.img.other;

import java.awt.Frame;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import com.attilax.img.imgx;
import com.attilax.img.util.OpencvUtil;
import com.attilax.io.filex;

public class opencvT {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub

		String string = "F:\\opencv_build_x64_vc12 bin\\bin\\opencv_java2413.dll";
		// string = "E:\\opencv\\opencv_java2413.dll";
		string = "C:\\progrm\\opencv\\build\\java\\x64\\opencv_java2413.dll";
		System.load(string);
		String f = "C:\\000oil\\亚当与上帝.jpg";
		// f="亚当";
		byte[] ba = f.getBytes("unicode");
		// byte[] ba=f.getBytes("utf-8");
		String f2 = new String(ba, "iso-8859-1");
		// toLocal8Bit
		f2="C:\\000oil\\a.jpg";
		Mat dst = Highgui.imread(f2);   //defl  Mat [ 337*600*CV_8UC3,
		Mat src=Highgui.imread(f2);
		System.out.println( OpencvUtil.comPareHist(src, dst));
//		dst = OpencvUtil.bufImg2mat(imgx.toImg(f));
//		System.out.println(dst.cols());
		// Highgui.im
		// imshow("imgShow", dst);
		// return this;
	}

	/**
	 * 
	 * 功能说明:读取图像
	 * 
	 * @param filePath
	 *            文件路径,可以包含中文
	 * @return Mat
	 * @time:2016年3月31日下午1:26:51
	 * @author:linghushaoxia
	 * @exception:
	 * 
	 */
	public static Mat imRead(String filePath) {
		Mat mat = null;
		try {
			// 使用java2D读取图像
			Image image = ImageIO.read(new File(filePath));
			/**
			 * 转为mat 1、由Java2D的image转为javacv自定义的Frame 2、由Frame转为Mat
			 */
			Java2DFrameConverter java2dFrameConverter = new Java2DFrameConverter();
			Frame frame = java2dFrameConverter.convert((BufferedImage) image);
			// ToMat converter = new OpenCVFrameConverter.ToMat();
			// mat = converter.convert(frame);

		} catch (Exception e) {
			System.out.println("读取图像出现异常：filePath=" + filePath);
			e.printStackTrace();
		}
		return mat;
	}

}
