package com.attilax.img;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.highgui.Highgui;

import com.attilax.img.util.OpencvUtil;
import com.attilax.io.filexEx;

import org.opencv.features2d.*;

public class SfitTest {

	/**
	 * ret 128 1589 //opencv_java2413 Core.NATIVE_LIBRARY_NAME
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// System.load(
		// "C:\\progrm\\opencv\\build\\java\\x64\\opencv_java2413.dll" );
		OpencvUtil.ini();
		String f = "D:\\0bar\\m.jpg";
		Mat src_mat = Highgui.imread(f);
		Mat out_mat = new Mat();

		
	//	FastFeatureDetector 
		MatOfKeyPoint keypoint_mat = getKp(src_mat);
		
		KeyPoint[] kps=keypoint_mat.toArray();

		Features2d.drawKeypoints(src_mat, keypoint_mat, out_mat);

		Highgui.imwrite(filexEx.addTimestampNSuffix(f, " keypnted"), out_mat);
		System.out.println("--f");

		// BFMatcher

		// desc
		Mat sence_feat = new OpencvUtil().getFeature(src_mat);
		// DescriptorExtractor descEx =
		// DescriptorExtractor.create(DescriptorExtractor.SIFT);
		// descEx.compute(src_mat,keypoint_mat,desc );//提取sift特征
		System.out.println(sence_feat.cols());
		System.out.println(sence_feat.rows());

		String f2 = "D:\\0bar\\prb.jpg";
		MatOfKeyPoint keypoint_mat2 = getKp(f2);
		drawKeypoints(f2, keypoint_mat2);
		Mat objImgMat = Highgui.imread(f2);
		Mat obj_feat2 = new OpencvUtil().getFeature(objImgMat);
		System.out.println(obj_feat2.cols());
		System.out.println(obj_feat2.rows());

		// match
		// Mat m = new Mat(sence_feat.rows(),sence_feat.cols(),CvType.CV_32SC1);
		// m=Mat.zeros(sence_feat.rows(),sence_feat.cols(),CvType.CV_32F);
		MatOfDMatch matches_rzt = new MatOfDMatch();// 8uc1
		DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_SL2);//
		// BRUTEFORCE_HAMMING
		matcher.match(obj_feat2, sence_feat, matches_rzt);
		// sence_feat Mat [ 3732*128*CV_32FC1,

		// jout match rzt img
		Mat outImg = new Mat();
		Features2d.drawMatches(objImgMat, keypoint_mat2, src_mat, keypoint_mat, matches_rzt, outImg);
		Highgui.imwrite(filexEx.addTimestampNSuffix(f, " match rzt"), outImg);
		System.out.println("--f");
		// Highgui.dra
		// Imgproc.drawKeypoints(img1,keypoints1,img_keypoints1,Scalar::all(-1),0);

	}

	private static void drawKeypoints(String f2, MatOfKeyPoint keypoint_mat) {
		Mat src_mat = Highgui.imread(f2);
		Mat out_mat = new Mat();
		Features2d.drawKeypoints(src_mat, keypoint_mat, out_mat);

		Highgui.imwrite(filexEx.addTimestampNSuffix(f2, " keypnted"), out_mat);

	}

	private static MatOfKeyPoint getKp(String f2) {
		Mat src_mat = Highgui.imread(f2);
		return getKp(src_mat);
	}

	private static MatOfKeyPoint getKp(Mat src_mat) {
		MatOfKeyPoint keypoint_mat = new MatOfKeyPoint();
		FeatureDetector fd = FeatureDetector.create(FeatureDetector.SIFT);
		// MatOfKeyPoint keypoint_mat =new MatOfKeyPoint();
		fd.detect(src_mat, keypoint_mat);
		return keypoint_mat;
	}

}
