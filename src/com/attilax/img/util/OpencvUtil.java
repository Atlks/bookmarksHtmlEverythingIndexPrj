package com.attilax.img.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.attilax.exception.ExUtil;
import com.attilax.img.imgx;
import com.attilax.img.other.CantFindMatch;
import com.attilax.io.FileNotExist;
import com.attilax.io.filex;
import com.attilax.io.filexEx;
import com.attilax.io.pathx;
import com.attilax.qrcode.MatrixToImageWriter;
import com.attilax.util.PropX;
import com.google.common.collect.Lists;

import android.widget.Toast;

public class OpencvUtil {

	public static void main(String[] args) {
		new ImgSearch().ini();
		Point pt = null;
		try {
			String bigimg = "C:\\0workspace\\atiplat_img\\fmspaint\\亚当与上帝.jpg";
			String tmplPart = "C:\\0workspace\\atiplat_img\\fmspaint\\t.jpg";
			;

			String resultRect4dbgFile = filex.addSuffix(bigimg, "rect4dbg");
			pt = OpencvUtil.matchTemplate(bigimg, tmplPart, resultRect4dbgFile, Imgproc.TM_SQDIFF);
		} catch (CantFindMatch | IOException e) {

			e.printStackTrace();
		}
		System.out.println(pt);

	}
	
	public static void showKeypointsVarVal(String f2, MatOfKeyPoint keypoint_mat) {
		Mat src_mat = Highgui.imread(f2);
		String addTimestampNSuffix = filexEx.addTimestampNSuffix(f2, " keypnted");
		showKeypointsVarVal( keypoint_mat, src_mat,addTimestampNSuffix);

	}

//	private static void showKeypointsVarVal(Mat src_mat, MatOfKeyPoint keypoint_mat,String f2) {
//	
//		
//		showKeypointsVarVal(src_mat, keypoint_mat, addTimestampNSuffix);
//	}

	public static void showKeypointsVarVal( MatOfKeyPoint keypoint_mat,Mat src_mat, String outfile) {
		Mat out_mat = new Mat();
		Features2d.drawKeypoints(src_mat, keypoint_mat, out_mat);

		
		Highgui.imwrite(outfile, out_mat);
	}
	   public MatOfKeyPoint getFeaturePoints_Fast(Mat mat){  
	        FeatureDetector fd = FeatureDetector.create(FeatureDetector.FAST);  
	        MatOfKeyPoint mkp =new MatOfKeyPoint();  
	        fd.detect(mat, mkp);  
	        return mkp;  
	    }  
	      
	/** 
     * 传入一张图片得到sift特征点 
     * @param mat 
     * @return 
     */  
    public MatOfKeyPoint getFeaturePoints(Mat mat){  
        FeatureDetector fd = FeatureDetector.create(FeatureDetector.SIFT);  
        MatOfKeyPoint mkp =new MatOfKeyPoint();  
        fd.detect(mat, mkp);  
        return mkp;  
    }  
      
    /** 
     * 获取sift特征 
     * @param mat 
     * @return 
     */  
    public Mat getFeature(Mat mat){  
        Mat desc = new Mat();  
        MatOfKeyPoint mkp = getFeaturePoints(mat);  
        DescriptorExtractor de = DescriptorExtractor.create(DescriptorExtractor.SIFT);  
        de.compute(mat,mkp,desc );//提取sift特征  
        return desc;  
    }  

	BufferedImage original;
	int itype;
	int mtype;
	static boolean inied = false;

	public static void ini() {
		if (inied)
			return;
		iniForce();
	}

	private static void iniForce() {
		String prjPath = pathx.prjPath_semode();

		String opencvlib = prjPath + "/dll/opencv_java2413.dll";
		opencvlib = opencvlib.replace("%prjpath%", prjPath);

		if (!new File(opencvlib).exists())
			try {
				throw new FileNotExist(opencvlib);
			} catch (FileNotExist e) {
				ExUtil.throwExV2(e);
			}

		System.out.println("  --load lib:" + opencvlib + "@@over");
		// System.out.println(" --load lib:"+lib2 +"@@over");
		System.load(opencvlib);

		// System.load(lib2);

		inied = true;
	}

	/**
	 * 
	 * @param image
	 * @param imgType
	 *            bufferedImage的类型 如 BufferedImage.TYPE_3BYTE_BGR
	 * @param matType
	 *            转换成mat的type 如 CvType.CV_8UC3
	 */
	// public BufImgToMat(BufferedImage image, int imgType, int matType) {
	// original = image;
	// itype = imgType;
	// mtype = matType;
	// }

	public Mat getMat() {
		if (original == null) {
			throw new IllegalArgumentException("original == null");
		}

		// Don't convert if it already has correct type
		if (original.getType() != itype) {

			// Create a buffered image
			BufferedImage image = new BufferedImage(original.getWidth(), original.getHeight(), itype);

			// Draw the image onto the new buffer
			Graphics2D g = image.createGraphics();
			try {
				g.setComposite(AlphaComposite.Src);
				g.drawImage(original, 0, 0, null);
			} finally {
				g.dispose();
			}
		}

		byte[] pixels = ((DataBufferByte) original.getRaster().getDataBuffer()).getData();
		Mat mat = Mat.eye(original.getHeight(), original.getWidth(), mtype);
		mat.put(0, 0, pixels);
		return mat;
	}

	Mat matrix;
	MatOfByte mob;
	String fileExten;

	// The file extension string should be ".jpg", ".png", etc
	// public MatToBufImg(Mat amatrix, String fileExtension) {
	// matrix = amatrix;
	// fileExten = fileExtension;
	// mob = new MatOfByte();
	// }

	public BufferedImage getImage() {
		// convert the matrix into a matrix of bytes appropriate for
		// this file extension
		Highgui.imencode(fileExten, matrix, mob);
		// convert the "matrix of bytes" into a byte array
		byte[] byteArray = mob.toArray();
		BufferedImage bufImage = null;
		try {
			InputStream in = new ByteArrayInputStream(byteArray);
			bufImage = ImageIO.read(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bufImage;
	}

	public static Point matchTemplate(String srcImgFile, String desImgFile, String findedRectCutImgPart4dbgFile, int matchMode)
			throws CantFindMatch, IOException {
		BufferedImage src = imgx.toImg(srcImgFile);
		BufferedImage dest = imgx.toImg(desImgFile);
		BufferedImage resultRect4dbg = imgx.toImg(srcImgFile);
		Point matchTemplatePt = matchTemplate(src, dest, resultRect4dbg, matchMode);

		Rectangle rect = new Rectangle(new Double(matchTemplatePt.x).intValue(),
				new Double(matchTemplatePt.y).intValue(), dest.getWidth(), dest.getHeight());
		BufferedImage rect_part = new imgx().cutImg(src, rect, "jpg");
		imgx.save_overwrite(rect_part, findedRectCutImgPart4dbgFile);
		return matchTemplatePt;
	}
	public static final Logger logger = LoggerFactory.getLogger("opencvutil");
	public static List<java.awt.Point> matchTemplate(String srcImgFile, String tmpltImgFile, String dbgDir,
			int matchMode, int count) {
		List<java.awt.Point> li = Lists.newArrayList();
		BufferedImage src_BufferedImage, tmplt, resultRect4dbg_img;
		src_BufferedImage = imgx.toImg(srcImgFile);
		tmplt = imgx.toImg(tmpltImgFile);
		resultRect4dbg_img = imgx.toImg(srcImgFile);
		Point tmpPt = null;
		int n=0;
		for (int i = count; i > 0; i--) {
			try {
				if(n==0)
				{
					
				}else
				{
					Rectangle drawRect = getDrawRect(tmpPt, tmplt.getWidth(), tmplt.getHeight());
					src_BufferedImage = drawContours(src_BufferedImage, drawRect, Color.black);
				}
				
				imgx.save_overwrite(src_BufferedImage, dbgDir + "\\" + filex.getUUidName() + " drawConted.jpg");
				Point matchTemplatePt = matchTemplate(src_BufferedImage, tmplt, resultRect4dbg_img, matchMode);
				java.awt.Point javaPoint = toJavaPoint(matchTemplatePt);
				
				//----------------for dbg out
				java.awt.Point downPnt = new java.awt.Point(javaPoint.x + tmplt.getWidth(),
						javaPoint.y + tmplt.getHeight());		
				if(downPnt.x>= resultRect4dbg_img.getWidth() )
					downPnt.x= resultRect4dbg_img.getWidth() -1;
				if(downPnt.y>= resultRect4dbg_img.getHeight() )
					downPnt.y= resultRect4dbg_img.getHeight() -1;
				logger.info( "will rectangle  resultRect4dbg_img:" +srcImgFile );
				imgx.rectangle(resultRect4dbg_img, toJavaPoint(matchTemplatePt), downPnt, Color.red);
				imgx.save_overwrite(resultRect4dbg_img, dbgDir + "\\" + filex.getUUidName() + " rected.jpg");
				//-----------------------
				
				
				
				li.add(javaPoint);
				tmpPt = matchTemplatePt;n++;
			} catch (CantFindMatch | IOException e) {
				System.out.println(e.getMessage() + "");
			}

		}

		return li;
	}

	private static java.awt.Point toJavaPoint(Point matchTemplatePt) {
		// TODO Auto-generated method stub
		return new java.awt.Point(new Double( matchTemplatePt.x).intValue(), new Double( matchTemplatePt.y).intValue());
	}

	private static BufferedImage drawContours(BufferedImage src, Rectangle drawRect, Color black) {
		for (int i = drawRect.x; i < drawRect.x + drawRect.width; i++)
			for (int j = drawRect.y; j < drawRect.y + drawRect.height; j++) {
				src.setRGB(i, j, black.getRGB());
			}
		return src;
	}
	
	

	private static Rectangle getDrawRect(Point matchTemplatePt, int width, int height) {
	 
		return new Rectangle(new Double(matchTemplatePt.x).intValue(), new Double(matchTemplatePt.y).intValue(), width,
				height);
	}

	/**
	 * // if( !new File(sourcePath).exists()) // throw new
	 * FileNotFoundException(sourcePath); // if( !new File(dstPath).exists()) //
	 * throw new FileNotFoundException(dstPath);
	 * 
	 * @param srcImg
	 * @param desImg
	 * @param matchMode
	 * @return
	 * @throws CantFindMatch
	 * @throws IOException
	 */

	public static Point matchTemplate(BufferedImage srcImg, BufferedImage desImg, BufferedImage resultRect4dbg,
			int matchMode) throws CantFindMatch, IOException {
		// 将文件读入为OpenCV的Mat格式

		imgx.save_overwrite(srcImg, "d:\\00img\\srcimg.jpg");
		Mat source = bufImg2mat(srcImg);
		// Highgui.imread(sourcePath);
		// System.out.println( AtiJson.toJson(source));
		// if(source.cols()==0)
		// throw new IOException(sourcePath);

		Mat dst = bufImg2mat(desImg);
		// if(dst.cols()==0)
		// throw new IOException(dstPath);
		// 创建于原图相同的大小，储存匹配度
		Mat result = Mat.zeros(source.rows(), source.cols(), CvType.CV_32FC1);
		// 调用模板匹配方法

		try {
			Imgproc.matchTemplate(source, dst, result, matchMode); // TM_CCOEFF_NORMED

		} catch (Exception e) {

			throw new RuntimeException("Imgproc.matchTemplate ex:" + e.getMessage(), e);

		}

		// 规格化 rst= Mat [ 1005*1236*CV_32FC1, isCont=true, isSubmat=false,
		// nativeObj=0x57b3fca0, dataAddr=0x59e80050 ]
		Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1);
		// 获得最可能点，MinMaxLocResult是其数据格式，包括了最大、最小点的位置x、y
		// System.out.println(AtiJson.toJson(result));
		MinMaxLocResult mlr = Core.minMaxLoc(result);
		Point matchLoc;
		if (matchMode == Imgproc.TM_SQDIFF || matchMode == Imgproc.TM_SQDIFF_NORMED) {
			matchLoc = mlr.minLoc;
		} else {
			matchLoc = mlr.maxLoc;

		}

		// 在原图上的对应模板可能位置画一个绿色矩形
		Core.rectangle(source, matchLoc, new Point(matchLoc.x + dst.width(), matchLoc.y + dst.height()),
				new Scalar(0, 255, 0));
		// String outpic=filex.addSuffix(sourcePath,"matched");
		// 将结果输出到对应位置
		// Highgui.imwrite(outpic, source);
		resultRect4dbg = mat2bufImg(source);

		if (matchLoc != null)
			return matchLoc;
		throw new CantFindMatch(" cant find match");

	}

	public static BufferedImage mat2bufImg(Mat img) {
		// CV_8UC3 zosh uchar 0-255 ,clor=3
		MatToBufImg matToBufImg = new MatToBufImg(img, ".jpg");
		return matToBufImg.getImage();
		// (img, BufferedImage.TYPE_3BYTE_BGR, CvType.CV_8UC3).getMat();

	}
	
	public static BufferedImage toImg(Mat img) {
		// CV_8UC3 zosh uchar 0-255 ,clor=3
		MatToBufImg matToBufImg = new MatToBufImg(img, ".jpg");
		return matToBufImg.getImage();
		// (img, BufferedImage.TYPE_3BYTE_BGR, CvType.CV_8UC3).getMat();

	}

	public static Mat bufImg2mat(BufferedImage img) {
		// CV_8UC3 zosh uchar 0-255 ,clor=3
		return new BufImgToMat(img, BufferedImage.TYPE_3BYTE_BGR, CvType.CV_8UC3).getMat();

	}
	
	// CV_8UC3 is ok de ...  CV_8UC1 cant..
	@Deprecated
	public static Mat bufImg2mat(BufferedImage img,int chanleType) {
		// CV_8UC3 zosh uchar 0-255 ,clor=3
		return new BufImgToMat(img, BufferedImage.TYPE_3BYTE_BGR, chanleType).getMat();
		
	 

	}
	
	public static Mat bufImg2mat_TYPE_INT_RGB(BufferedImage img) {
		// CV_8UC3 zosh uchar 0-255 ,clor=3
		// img.getType()::1 ,, BufferedImage.TYPE_INT_RGB::1
		System.out.println(  "img.getType()::"+ img.getType()+  " ,, BufferedImage.TYPE_INT_RGB::"+BufferedImage.TYPE_INT_RGB);
		return new BufImgToMat().getMat_typeIntRgb( img, BufferedImage.TYPE_INT_RGB, CvType.CV_32S );

	}
	
	public static Mat bufImg2mat_TYPE_binary(BufferedImage bined_img) {
		// CV_8UC3 zosh uchar 0-255 ,clor=3
		// img.getType()::1 ,, BufferedImage.TYPE_INT_RGB::1
		System.out.println(  "img.getType()::"+ bined_img.getType()+  " ,, BufferedImage.TYPE_INT_RGB::"+BufferedImage.TYPE_INT_RGB);
		return new BufImgToMat().getMat_typeIntRgb( bined_img, BufferedImage.TYPE_BYTE_BINARY, CvType.CV_32S );

	}

	public static double comPareHist(String srcFile, String desFile) {
		BufferedImage src_img = imgx.toImg(srcFile);
		BufferedImage dst_img = imgx.toImg(desFile);
		Mat src = bufImg2mat(src_img);
		// Mat src1 = null;
		src.convertTo(src, CvType.CV_32FC1);
		// src.convert(mat,CvType.CV_32S),
		Mat des = bufImg2mat(dst_img);
		// Mat des1 = null;
		des.convertTo(des, CvType.CV_32FC1);
		return comPareHist(src, des);
	}

	public static double comPareHist(BufferedImage srcMat, BufferedImage desMat) {
		Mat src = bufImg2mat(srcMat);
		Mat des = bufImg2mat(desMat);
		return comPareHist(src, des);
	}

	// double psnr(Mat I1, Mat I2){
	// Mat s1;
	// absdiff(I1, I2, s1);
	// s1.convertTo(s1, CV_32F);//转换为32位的float类型，8位不能计算平方
	// s1 = s1.mul(s1);
	// Scalar s = sum(s1); //计算每个通道的和
	// double sse = s.val[0] + s.val[1] + s.val[2];
	// if( sse <= 1e-10) // for small values return zero
	// return 0;
	// else
	// {
	// double mse = sse / (double)(I1.channels() * I1.total()); // sse/(w*h*3)
	// double psnr = 10.0 * log10((255*255)/mse);
	// return psnr;
	// }
	//
	// }
	/**
	 * 比较来个矩阵的相似度
	 * 
	 * @param srcMat
	 * @param desMat
	 * @return
	 */
	public static double comPareHist(Mat srcMat, Mat desMat) {

		srcMat.convertTo(srcMat, CvType.CV_32F);
		desMat.convertTo(desMat, CvType.CV_32F);
		double target = Imgproc.compareHist(srcMat, desMat, Imgproc.CV_COMP_CORREL);
		// Log.e(TAG, "相似度 ： ==" + target);
		return target;
		// Toast.makeText(this, "相似度 ： ==" + target, 1000).show();
	}

	@Deprecated
	public static Mat bufImg2mat_gazi(BufferedImage img) {
		Mat result = Mat.zeros(img.getHeight(), img.getWidth(), CvType.CV_32FC1);
		for (int i = 0; i < img.getWidth(); i++)
			for (int j = 0; j < img.getHeight(); j++) {
				int clr_int = img.getRGB(i, j);
				Color clr = new Color(clr_int);
				int[] data = new int[3];
				data[0] = clr.getRed();
				data[1] = clr.getGreen();
				data[2] = clr.getBlue();
				int row = j;
				result.put(row, i, data);
			}
		return result;
	}

	/**
	 * supt cn name
	 * 
	 * @param f
	 * @return
	 */
	public static Mat imread(String f) {
		BufferedImage bi = imgx.toImg(f);
		return bufImg2mat(bi);
	}
	
	public static void savepic(String f,Mat img_mat) {
		filex.mkdir(f);
		//filex.save_safe(jsonStr, file);
		//	createAllPath(target2);
		 Highgui.imwrite(f,img_mat);
	//	return bufImg2mat(bi);
	}
	

	public static Mat toBinColormode(Mat srcMat) {
		// src.convertTo(src2, CvType.CV_8UC1); is err cant cvt
		Mat srcMat_Gray = Mat.zeros(srcMat.rows(), srcMat.cols(), CvType.CV_8UC1);
		Imgproc.cvtColor(srcMat, srcMat_Gray, Imgproc.COLOR_BGR2GRAY);

		return srcMat_Gray;
	}


	public static Mat to8uc1Colormode(Mat srcMat) {
		// src.convertTo(src2, CvType.CV_8UC1); is err cant cvt
		Mat srcMat_Gray = Mat.zeros(srcMat.rows(), srcMat.cols(), CvType.CV_8UC1);
		Imgproc.cvtColor(srcMat, srcMat_Gray, Imgproc.COLOR_BGR2GRAY);

		return srcMat_Gray;
	}

	public static Mat toBinImg(Mat srcMat) {
		Mat dst = Mat.zeros(srcMat.rows(), srcMat.cols(), srcMat.type());
		Imgproc.threshold(srcMat, dst, 128, 255, Imgproc.THRESH_BINARY);
		// Core.thre
		// Imgproc.thres
		// threshold
		// Imgproc.cvtColor(srcMat,srcMat_Gray,Imgproc.COLOR_);
		return dst;
	}

	public static List<MatOfPoint> findContours(Mat morph_closeED) {
		Mat bin = OpencvUtil.toBinImg(morph_closeED);
		System.out.println("bin.type():"+bin.type());
		Mat bin2 ;
		if( bin.type()==0 )
			bin2=bin;
			else
			
				bin2=OpencvUtil.to8uc1Colormode(bin);
		new Mat(morph_closeED.rows(), morph_closeED.cols(), CvType.CV_8UC1);
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Imgproc.findContours(bin2, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);
		return contours;
	}

	public static Mat morph_close(String f, int kenelSize) {
		BufferedImage bi = imgx.toImg(f);
		return morph_close(bi, kenelSize);
		// int kenelSize =20;

	}

	/**
	 * must bin img beir OpencvUtil.bufImg2mat throw ex..
	 * 
	 * @param ranged_bufimg
	 * @param kenelSize
	 * @return
	 */
	public static Mat morph_close(BufferedImage ranged_bufimg, int kenelSize) {
		iniForce();
		Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(kenelSize, kenelSize));
		Mat src = OpencvUtil.bufImg2mat(ranged_bufimg);
		Mat dst = new Mat(src.rows(), src.cols(), CvType.CV_8UC3);
		// Imgproc.dilate(src, dst, element);
		Imgproc.morphologyEx(src, dst, Imgproc.MORPH_CLOSE, element);
		return dst;
		// return null;
	}
	
	/**
	 * ranged_bufimg is init rgb mode
	 * @param ranged_bufimg
	 * @param kenelSize
	 * @return
	 */
	public static BufferedImage morph_close_retImg(BufferedImage bined_img, int kenelSize) {
		iniForce();
		Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(kenelSize, kenelSize));
		Mat src = OpencvUtil.bufImg2mat(bined_img);
		Mat dst = new Mat(src.rows(), src.cols(), CvType.CV_8UC3);
		// Imgproc.dilate(src, dst, element);
		Imgproc.morphologyEx(src, dst, Imgproc.MORPH_CLOSE, element);
		return mat2bufImg( dst);
		// return null;
	}

	public static void setRangeEdgeColor(BufferedImage bi, MatOfPoint matOfPoint, int rgb) {
		List<Point> lst = matOfPoint.toList();

		for (Point point : lst) {
			bi.setRGB((int) point.x, (int) point.y, rgb);
		}

	}

	public static Mat mask(BufferedImage srcbufimg, BufferedImage bin) {
		Mat src = bufImg2mat(srcbufimg);
		Mat mask = bufImg2mat(bin);
		Mat result = Mat.zeros(src.rows(), src.cols(), CvType.CV_8UC3);
		src.copyTo(result, mask);
		return result;
	}

	public static Mat mask(BufferedImage src, Mat morph_closeED) {
		BufferedImage closed = mat2bufImg(morph_closeED);
		return mask(src, closed);
	}

	public static Rectangle toRectangle(Rect rct) {

		return new Rectangle(rct.x, rct.y, rct.width, rct.height);
	}

	public static void showvar_matchRzt(Mat objImgMat, MatOfKeyPoint keypoints_object, Mat scene,
			MatOfKeyPoint keypoints_scene, MatOfDMatch matches, String f) {
		Mat outImg = new Mat();
		Features2d.drawMatches(objImgMat, keypoints_object, scene, keypoints_scene, matches, outImg);
		Highgui.imwrite(f, outImg);
		
	}

}
