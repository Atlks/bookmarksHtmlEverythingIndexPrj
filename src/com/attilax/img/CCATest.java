package com.attilax.img;

import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.FeatureDetector;
import org.opencv.highgui.Highgui;

public class CCATest {

	public static void main(String[] args) {
		   System.load( "C:\\progrm\\opencv\\build\\java\\x64\\opencv_java2413.dll" );
		    Mat test_mat = Highgui.imread("C:\\00\\b.jpg");
		    Mat desc = new Mat();
		    FeatureDetector fd = FeatureDetector.create(FeatureDetector.SIFT);
		    MatOfKeyPoint mkp =new MatOfKeyPoint();
		    fd.detect(test_mat, mkp);
		    DescriptorExtractor de = DescriptorExtractor.create(DescriptorExtractor.SIFT);
		    de.compute(test_mat,mkp,desc );//提取sift特征
		    System.out.println(desc.cols());
		    System.out.println(desc.rows());
	}

}
