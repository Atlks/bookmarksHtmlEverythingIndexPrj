package com.attilax.img;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.function.Function;

import com.attilax.img.other.CurPixArrivdBoderEx;
import com.attilax.img.other.LineArrivdBorderEx;
import com.attilax.img.other.NewLineColorCheck;
import com.attilax.img.other.ProcessPointColor;
import com.attilax.io.FileExistEx;
import com.attilax.io.filex;

public class ImgTraver_lineScaner {
	
	public ImgTraver_lineScaner() {
		// TODO Auto-generated constructor stub
	}
	
	public ImgTraver_lineScaner(BufferedImage src2) {
		this.src=src2;
	}

	public ImgTraver_lineScaner(String f) {
		this.src=imgx.toImg(f);
	}

	public static void main(String[] args) {
		String s = "C:\\00p\\a1115_210836_162 dilate.jpg";
		// s="C:\\00capch\\p5.jpg";
		BufferedImage src = imgx.toImg(s);
	
	// 	Rectangle rect=new ImgTraver_lineScaner().setSrc(src)
				//new Rectangle(x, y, width, height)
		// t();
		System.out.println("--f");
	}

	private static void t() {
		BufferedImage dest = null ;//= new DilateFilterV3().filter(src, null);

		// BufferedImage dest= imgx.clone(src);
		try {
			imgx.save_png(dest, "C:\\00p\\a" + filex.getUUidName() + " dilate.jpg",false);
		} catch (FileExistEx e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BufferedImage getSrc() {
		return src;
	}

	public ImgTraver_lineScaner setSrc(BufferedImage src) {
		this.src = src;return this;
	}

	public void trave_downScan_lineByline(BufferedImage src, Point ini_select_Point_just4x) throws LineArrivdBorderEx  {
		int width = src.getWidth();
		int height = src.getHeight();
		

		for (int y = ini_select_Point_just4x.y; y < height; y++) {

			scanLine(src, ini_select_Point_just4x, y);

		}

	}
	

	public void trave_toDownScan_lineByline(BufferedImage src, Point ini_select_Point_just4x) throws LineArrivdBorderEx, CurPixArrivdBoderEx  {
		int width = src.getWidth();
		int height = src.getHeight();
		

		for (int y = ini_select_Point_just4x.y; y < height; y++) {

			 
				scanLineV2(src, ini_select_Point_just4x, y);
			 

		}

	}
	public ProcessPointColor  cur_Pix_Point_process_Fun_Handler;
	public Function<Integer,Boolean>  checkIsMeetBorderPix_Handler;
	public Function  new_line_upNdown_evt_handler;
	public Function  new_line_scanFinish_evt_handler;
	private BufferedImage src;
	//public  NewLineColorCheck  newLineFirstColorCheckFun;
	private void scanLine(BufferedImage src, Point ini_selectedPoint, int y) throws LineArrivdBorderEx      {
		
		new_line_upNdown_evt_handler.apply(new Point(ini_selectedPoint.x,y));
		int cur_line_point_color = src.getRGB(ini_selectedPoint.x, y);// x is ini,y is next line point 
	 
		if (checkIsMeetBorderPix_Handler.apply(cur_line_point_color))
			throw new LineArrivdBorderEx();
		 
	
		// left scan
		try {
			leftScan(src, ini_selectedPoint, y);
		} catch (CurPixArrivdBoderEx e) {
			 
		}
		// right scan
		try {
			rightScan(src, ini_selectedPoint, y);
		} catch (CurPixArrivdBoderEx e) {
			 
		}
	}
	private void scanLineV2(BufferedImage src, Point ini_selectedPoint, int y) throws LineArrivdBorderEx, CurPixArrivdBoderEx      {
		
		if(new_line_upNdown_evt_handler!=null)
		new_line_upNdown_evt_handler.apply(new Point(ini_selectedPoint.x,y));
		int cur_line_point_color = src.getRGB(ini_selectedPoint.x, y);// x is ini,y is next line point 
	 
		if(checkIsMeetBorderPix_Handler!=null)
		if (checkIsMeetBorderPix_Handler.apply(cur_line_point_color))
			throw new LineArrivdBorderEx();
		 
	
		// left scan
	 
			leftScan(src, ini_selectedPoint, y);
		  
		 
		// right scan
		 
			rightScan(src, ini_selectedPoint, y);
		 
	}
	
	public void trave_upScan_lineByLine(BufferedImage src, Point ini_selectedPoint) throws   LineArrivdBorderEx {
		int width = src.getWidth();
		int height = src.getHeight();
		//  ini_select_color_int = src.getRGB(ini_selectedPoint.x, ini_selectedPoint.y);

		for (int y = ini_selectedPoint.y-1; y >0; y--) {

			scanLine(src, ini_selectedPoint, y);

		}

	}
	
	public void trave_toUpScan_lineByLine(BufferedImage src, Point ini_selectedPoint) throws   LineArrivdBorderEx, CurPixArrivdBoderEx {
		int width = src.getWidth();
		int height = src.getHeight();
		//  ini_select_color_int = src.getRGB(ini_selectedPoint.x, ini_selectedPoint.y);

		for (int y = ini_selectedPoint.y-1; y >0; y--) {

			scanLineV2(src, ini_selectedPoint, y);

		}

	}
	
	public void trave_toRitScan_lineByLine(int startX) throws   LineArrivdBorderEx, CurPixArrivdBoderEx {
		int width = src.getWidth();
		int height = src.getHeight();
		//  ini_select_color_int = src.getRGB(ini_selectedPoint.x, ini_selectedPoint.y);

		for (int x = startX; x<src.getWidth(); x++) {

			scanLine_vert(x);
			//scanLine(src, ini_selectedPoint, y);
if(new_line_scanFinish_evt_handler!=null)
			new_line_scanFinish_evt_handler.apply(null);
		}

	}

	
	/**
	attilax    2016年11月16日  下午3:13:00
	 * @param src
	 * @param x
	 * @throws CurPixArrivdBoderEx 
	 */
	@Deprecated
	private void scanLine_vert_toRit(BufferedImage src, int x) throws CurPixArrivdBoderEx {
		for (int y = 0; y<src.getHeight(); y++) {
			if(x==15)
				System.out.println("dbg");
			Point preLeftPoint=new Point(x, y);
			if(x<9)
			{
				System.out.println("dbg");
			}
		 	int clr=src.getRGB(preLeftPoint.x,preLeftPoint. y);
		 //	if(checkIsMeetBorderPix_Handler.apply(clr))
		
		 		 this.cur_Pix_Point_process_Fun_Handler.apply(preLeftPoint);
//

		}
		
	}
	public Point nowPoint;

	public void trave_toLeftScan_lineByLine(int startX) throws   LineArrivdBorderEx {
		int width = src.getWidth();
		int height = src.getHeight();
		//  ini_select_color_int = src.getRGB(ini_selectedPoint.x, ini_selectedPoint.y);

		for (int x=startX;x>0;x--) {

			try {
			
				scanLine_vert( x);
			} catch (CurPixArrivdBoderEx e) {
				throw new LineArrivdBorderEx();
			}

		}

	}


	/**
	attilax    2016年11月16日  下午3:23:11
	 * @param x
	 * @throws CurPixArrivdBoderEx 
	 */
	private void scanLine_vert(int x) throws CurPixArrivdBoderEx {
		for (int y = 0; y<src.getHeight(); y++) {
			nowPoint=new Point(x, y);
			if(x==15)
				System.out.println("dbg");
			Point preLeftPoint=new Point(x, y);
			if(x<9)
			{
				System.out.println("dbg");
			}
		 	int clr=src.getRGB(preLeftPoint.x,preLeftPoint. y);
		 //	if(checkIsMeetBorderPix_Handler.apply(clr))
		
		 		 this.cur_Pix_Point_process_Fun_Handler.apply(preLeftPoint);
//

		}
		
	}

	/**
	 * left and curSelectPoint
	 * @param src
	 * @param ini_selectedPoint
	 * @param y
	 * @throws CurPixArrivdBoderEx 
	 */
	private void leftScan(BufferedImage src,Point ini_selectedPoint, int y) throws CurPixArrivdBoderEx {
		for (int x = ini_selectedPoint.x; x > 0; x--) {
			if(x==15)
				System.out.println("dbg");
			Point preLeftPoint=new Point(x, y);
			if(x<9)
			{
				System.out.println("dbg");
			}
		 	int clr=src.getRGB(preLeftPoint.x,preLeftPoint. y);
		 //	if(checkIsMeetBorderPix_Handler.apply(clr))
		
		 		 this.cur_Pix_Point_process_Fun_Handler.apply(preLeftPoint);
//

		}
	}

 

	private void rightScan(BufferedImage src,  Point selectedPoint, int y) throws CurPixArrivdBoderEx {
		//int select_color_int = src.getRGB(selectedPoint.x, selectedPoint.y);//-65536 red
	//	Color curClr=new Color(ini_select_color_int);
		for (int x = selectedPoint.x+1; x < src.getWidth(); x++) {

			Point nextRightPoint=new Point(x, y);
			nowPoint=nextRightPoint;
			this.cur_Pix_Point_process_Fun_Handler.apply(nextRightPoint);
		}
	}

	/**
	attilax    2016年11月9日  下午6:47:18
	 * @param img
	 * @param startPixPoint
	 */
	public void scan_Byline_from_startPixPoint(BufferedImage img, Point startPixPoint) {
		try {
			trave_downScan_lineByline(img, startPixPoint);
		} catch (LineArrivdBorderEx e) {

		}

		try {
			trave_upScan_lineByLine(img, startPixPoint);
		} catch (LineArrivdBorderEx e) {

		}
		
	}

	/**
	attilax    2016年11月16日  下午3:37:24
	 * @return
	 */
	public  Rectangle trav() {
		int  lastX = 0; int x = 0;	int y = 0;	int lastY = 0;
		try {
			trave_toLeftScan_lineByLine(src.getWidth()-1);
		} catch (LineArrivdBorderEx e ) {
			 
			System.out.println(e.getMessage());
			lastX=nowPoint.x;
			if(lastX+2 <src.getWidth())
				lastX=lastX+2;
		}
		
		try {
			trave_toRitScan_lineByLine(0);
		} catch (LineArrivdBorderEx | CurPixArrivdBoderEx e) {
			System.out.println(e.getMessage());
			x=nowPoint.x;
			if(x-1!=0)
				x=x-1;
		}
		
	
		try {
			trave_toDownScan_lineByline(src,new Point(0,0)  );
		} catch (LineArrivdBorderEx | CurPixArrivdBoderEx e) {
			System.out.println(e.getMessage());
			y=nowPoint.y;
			if(y-1!=0)
				y=y-1;
		}
		
	
		try {
			trave_toUpScan_lineByLine(src, new Point(0,src.getHeight()-1 ));
		} catch (LineArrivdBorderEx | CurPixArrivdBoderEx e) {
			System.out.println(e.getMessage());
			lastY=nowPoint.y;
			if(lastY+2 <src.getHeight())
				lastY=lastY+2;
		}
		return new Rectangle(x, y, lastX-x, lastY-y);
	}


}
