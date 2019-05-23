/**
 * 
 */
package com.attilax.img.util;

import java.awt.image.BufferedImage;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import com.attilax.img.imgx;

/**
 * @author attilax
 *2017年1月23日 下午5:36:56
 */
public class DctTest {

	/**
	attilax    2017年1月23日  下午5:36:56
	 * @param args
	 */
	public static void main(String[] args) {
		   new ImgSearch().ini();
			String tmplPart="D:\\0fmspaint\\0亚当与上帝.jpg";
			BufferedImage part_bi = imgx.toImg(tmplPart);
			Mat srcMat=OpencvUtil.bufImg2mat(part_bi);
			
			Mat srcMat_Gray=Mat.zeros(srcMat.rows(), srcMat.cols(), CvType.CV_8UC1);
			Imgproc.cvtColor(srcMat,srcMat_Gray,Imgproc.COLOR_BGR2GRAY);
		 
		//	srcMat.convertTo(srcMat, CvType.CV_8UC1);
			srcMat_Gray.convertTo(srcMat_Gray, CvType.CV_32FC1);
			
			Mat result = Mat.zeros(srcMat.rows(), srcMat.cols(), CvType.CV_32FC1);
			Core.dct(srcMat_Gray, result);
		 System.out.println(result.cols());
		  
	}

}
