package com.attilax.img.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import com.attilax.img.imgx;

public class BufImgToMat {
	
	
	public static void main(String[] args) {
		System.out.println(BufferedImage.TYPE_INT_BGR);  //4
		System.out.println(BufferedImage.TYPE_INT_RGB);  //1
		System.out.println(BufferedImage.TYPE_3BYTE_BGR);  //5
//		String string =   "C:\\progrm\\opencv\\build\\java\\x64\\opencv_java2413.dll";
//		System.load(string);
//		
//		
//		String f = "C:\\000oil\\亚当与上帝.jpg";
		String f = "D:\\00sidtmp\\01-36-35-20140319_23b44db756b98dc5afe89p099Aaed0v0.jpg";
		// f="01-36-32-001UuJ0pgy6KR3X3Tnnfd&690";
		BufferedImage src = imgx.toImg(f);
		OpencvUtil.ini();
		BufferedImage dest = imgx.new_BackgroudColor_black(src.getWidth(), src.getHeight());
	//	BufferedImage img = imgx.toImg(f);
		System.out.println("CvType. CV_8UC3:"+CvType. CV_8UC3);   // CvType. CV_8UC3:16
		System.out.println(" CvType.CV_32S:"+CvType. CV_32S);   //   CvType.CV_32S:4
		 
		Mat dst   =new BufImgToMat().getMat_typeIntRgb(dest,BufferedImage.TYPE_INT_RGB,  CvType. CV_32S);
	//	Imgproc.convertMaps(map1, map2, dstmap1, dstmap2, dstmap1type);
	    
		System.out.println(dst.cols());
	}
        
        BufferedImage originalBufferedImage;
        int itype;
        int mtype;

        /**
         * 
         * @param image
         * @param imgType bufferedImage的类型 如 BufferedImage.TYPE_3BYTE_BGR
         * @param matType 转换成mat的type 如 CvType.CV_8UC3
         */
        public BufImgToMat(BufferedImage image, int imgType, int matType) {
                originalBufferedImage = image;
                itype = imgType;
                mtype = matType;
        }

       public BufImgToMat() {
			// TODO Auto-generated constructor stub
		}
 

		public Mat getMat() {
        //	new ImgSearch().ini();
        	
        	try{
                if (originalBufferedImage == null) {
                        throw new IllegalArgumentException("original == null");
                }

                // Don't convert if it already has correct type
                if (originalBufferedImage.getType() != itype) {

                        // Create a buffered image
                        BufferedImage image = new BufferedImage(originalBufferedImage.getWidth(),
                                        originalBufferedImage.getHeight(), itype);

                        // Draw the image onto the new buffer
                        Graphics2D g = image.createGraphics();
                        try {
                                g.setComposite(AlphaComposite.Src);
                                g.drawImage(originalBufferedImage, 0, 0, null);
                        } finally {
                                g.dispose();
                        }
                }
                DataBuffer dbf=   originalBufferedImage.getRaster().getDataBuffer();
              
                DataBufferByte dbf_byte = (DataBufferByte)dbf;
				byte[] pixels = dbf_byte.getData();
				
                Mat mat = Mat.eye(originalBufferedImage.getHeight(), originalBufferedImage.getWidth(), mtype);
                mat.put(0, 0, pixels);
                return mat;
        	}catch( UnsatisfiedLinkError e)
        	{
        		throw new RuntimeException("maybe not ini opencv dll,should ini() first");
        	}
        }
		
		public Mat getMat_byteRGB(BufferedImage originalBufferedImage , int cv8uc3) {
	        //	new ImgSearch().ini();
			itype= BufferedImage.TYPE_3BYTE_BGR; // ==1
	        	try{
	                if (originalBufferedImage == null) {
	                        throw new IllegalArgumentException("original == null");
	                }

	                // Don't convert if it already has correct type
	                if (originalBufferedImage.getType() != itype) {

	                        // Create a buffered image
	                        BufferedImage image = new BufferedImage(originalBufferedImage.getWidth(),
	                                        originalBufferedImage.getHeight(), itype);

	                        // Draw the image onto the new buffer
	                        Graphics2D g = image.createGraphics();
	                        try {
	                                g.setComposite(AlphaComposite.Src);
	                                g.drawImage(originalBufferedImage, 0, 0, null);
	                        } finally {
	                                g.dispose();
	                        }
	                }
	                DataBuffer dbf=   originalBufferedImage.getRaster().getDataBuffer();
	              
	                DataBufferByte dbf_byte = (DataBufferByte)dbf;
					byte[] pixels = dbf_byte.getData();
					
	                Mat mat = Mat.eye(originalBufferedImage.getHeight(), originalBufferedImage.getWidth(), cv8uc3);
	                mat.put(0, 0, pixels);
	                return mat;
	        	}catch( UnsatisfiedLinkError e)
	        	{
	        		throw new RuntimeException("maybe not ini opencv dll,should ini() first");
	        	}
	        }
@Deprecated
		public Mat getMat_typeIntRgb(BufferedImage originalBufferedImage, int typeIntRgb, int cv8uc3) {
			try{
                if (originalBufferedImage == null) {
                        throw new IllegalArgumentException("original == null");
                }
                //normal   BufferedImage.getType() == typeIntRgb
                // Don't convert if it already has correct type
                if (originalBufferedImage.getType() != typeIntRgb) {

                        // Create a buffered image
                        BufferedImage image = new BufferedImage(originalBufferedImage.getWidth(),
                                        originalBufferedImage.getHeight(), typeIntRgb);

                        // Draw the image onto the new buffer
                        Graphics2D g = image.createGraphics();
                        try {
                                g.setComposite(AlphaComposite.Src);
                                g.drawImage(originalBufferedImage, 0, 0, null);
                        } finally {
                                g.dispose();
                        }
                }
                DataBuffer dbf=   originalBufferedImage.getRaster().getDataBuffer();
              
                DataBufferInt dbf_byte = (DataBufferInt)dbf;
				int[] pixels = dbf_byte.getData();
				
                Mat mat = Mat.eye(originalBufferedImage.getHeight(), originalBufferedImage.getWidth(), cv8uc3);
                mat.put(0, 0, pixels );
                return mat;
        	}catch( UnsatisfiedLinkError e)
        	{
        		throw new RuntimeException("maybe not ini opencv dll,should ini() first");
        	}
		}
}
