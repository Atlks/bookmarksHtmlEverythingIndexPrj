package com.attilax.img.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import com.attilax.img.imgx;
import com.attilax.json.AtiJson;
import com.google.common.collect.Maps;
import com.sun.media.jai.codec.ImageCodec;

public class sidDetect2 {
	
	public static void main(String[] args) {
		OpencvUtil.ini();
		Mat mCannyMat2=new Mat();            
        Mat rgbMat2 = new Mat();    
            Mat grayMat2 = new Mat();             
            Mat lineMat=new Mat();  
		String s="D:\\00dbgpic_out\\bi0609_092643_117.jpg";
		s="D:\\00dbgpic_out\\morph_close20608_171148_854.jpg";
		BufferedImage bi_edged=imgx.toImg(s);
		mCannyMat2 =OpencvUtil.bufImg2mat(bi_edged);
		System.out.println("mCannyMat2.type():"+mCannyMat2.type());
		   OpencvUtil.savepic("d:\\000out_linedetect\\mCannyMat2_edged.jpg", mCannyMat2);
		   Mat   mCannyMat2_c1=OpencvUtil.to8uc1Colormode(mCannyMat2);
		   System.out.println("mCannyMat2_c1.type():"+mCannyMat2_c1.type());
		   List<MatOfPoint> li=  OpencvUtil.findContours(mCannyMat2_c1);
		   
		   System.out.println("  List<MatOfPoint>  size:"+li.size());
		   for (MatOfPoint matOfPoint : li) {
			   Map m=Maps.newConcurrentMap();
			   m.put("cols", matOfPoint.cols());
			   m.put("rows", matOfPoint.rows());
			   m.put("dims", matOfPoint.dims());
			   m.put("elemSize", matOfPoint.elemSize());   m.put("size", matOfPoint.size()); m.put("total", matOfPoint.total());
			   System.out.println( AtiJson.toJson(matOfPoint));
			   System.out.println( AtiJson.toJson(m));
		   if( matOfPoint.total()>200)
  {
				   OpencvUtil.setRangeEdgeColor(bi_edged, matOfPoint, Color.red.getRGB());
				   imgx.output_var_multi(bi_edged, "bi_edged&total="+matOfPoint.total()+"&stmp=", "d:\\000out_sidetect");
		    }
				   
				  
			   
			   
			
		}
	//	Imgproc.HoughLinesP(matIn, line, 1, Math.PI / 180, 20, 150, 10);
		/**
		 * Hough transform:</p>
 *
 * @param image 8-bit, single-channel binary source image. The image may be
 * modified by the function.
 * @param lines Output vector of lines. Each line is represented by a 4-element
 * vector <em>(x_1, y_1, x_2, y_2)</em>, where <em>(x_1,y_1)</em> and <em>(x_2,
 * y_2)</em> are the ending points of each detected line segment.
 * @param rho Distance resolution of the accumulator in pixels.
 * @param theta Angle resolution of the accumulator in radians.
 * @param threshold Accumulator threshold parameter. Only those lines are
 * returned that get enough votes (<em>&gtthreshold</em>).
 * @param minLineLength Minimum line length. Line segments shorter than that are
 * rejected.
 * @param maxLineGap Maximum allowed gap between points on the same line to link
 * them.
		 */
		   
		
		   Imgproc.HoughLinesP(mCannyMat2_c1, lineMat, 1, Math.PI /180, 128,150,30);
		   System.out.println("lines1.rows():"+lineMat.rows());
		   /*
		   for (int y=0;y<lines1.rows();y++)  
           {  
               double[] vec = lines1.get(y, 0);  
                     
               double  x1 = vec[0],   
                       y1 = vec[1],  
                       x2 = vec[2],  
                       y2 = vec[3];      
                     
               Point start = new Point(x1, y1);  
               Point end = new Point(x2, y2);  
               //Scalar is color
               Core.line(mCannyMat2, start, end, new Scalar(255,0,0), 1);  
           }*/
		   
		   long linesCount = lineMat.total();
		  
		   System.out.println( "  linesCount:"+linesCount);
		   System.out.println( "   lineMat.cols():"+ lineMat.cols());
		 //  lineMat.cols()
		   /*   lines1.rows():1
  linesCount:55
   lineMat.cols():55   
   */
		int[] a = new int[(int)linesCount*lineMat.channels()]; //数组a存储检测出的直线端点坐标

	        lineMat.get(0,0,a);
	       
	        System.out.println(" a.length.():"+ a.length);   // a.length.():252
	        for (int i = 0; i < a.length; i += 4)

	        {

	           Core.line(mCannyMat2, new Point(a[i], a[i+1]), new Point(a[i+2], a[i+3]), new Scalar(255, 0, 0),2);

	        }
		   
		   
		   OpencvUtil.savepic("d:\\000out_linedetect\\lined.jpg", mCannyMat2);
		   BufferedImage lined_bi=OpencvUtil.mat2bufImg(mCannyMat2);
		   imgx.output_var_multi(lined_bi, "lined_bi", "d:\\000out_linedetect");
		   System.out.println("--f");
               
               /*
		if (line.rows() > 0 && line.cols() > 0) {
		    for (int i = 0; i < line.cols(); i++) {
		        double[] l = line.get(0, i);
		        if (l.length == 4) {
		            if (Math.abs(l[1] - l[3]) < 2) {
		                Point p1 = new Point(l[0], l[1]);
		                Point p2 = new Point(l[2], l[3]);
		                Core.line(matIn, p1, p2, new Scalar(255, 255, 255), 2);
		            }
		        }
		    }
		}
		注意Mat变量的结构，通过get(x,y)获取坐标点值，坐标点的各通道在java中通过数组呈现。
		*/
		 
	}

}
