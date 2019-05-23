package com.attilax.img;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

import com.attilax.exception.ExUtil;
import com.attilax.img.other.CurPixArrivdBoderEx;
import com.attilax.img.other.LineArrivdBorderEx;
import com.attilax.img.other.NewLineColorCheck;
import com.attilax.img.other.ProcessPointColor;

/**
 * by pix
 * @author Administrator
 *
 */
public class ImgTraver {
	
	public void trave_fromTop2down(BufferedImage img )
	{
		try {
			trave_downScan_lineByline(img, new Point(0, 0));
		} catch (LineArrivdBorderEx e) {
			ExUtil.throwExV2(e);
		}
	}
	
	/**
	 * fromTop2down scan
	 * @param src
	 * @param ini_select_Point_just4x
	 * @throws LineArrivdBorderEx
	 */
	public void trave_downScan_lineByline(BufferedImage src, Point ini_select_Point_just4x) throws LineArrivdBorderEx  {
		int width = src.getWidth();
		int height = src.getHeight();
		

		for (int y = ini_select_Point_just4x.y; y < height; y++) {

			scanLine(src, ini_select_Point_just4x, y);

		}

	}
	public ProcessPointColor  process_cur_Pix_Point_Fun_Handler;
	
	public  NewLineColorCheck  newLineFirstColorCheckFun;
	private void scanLine(BufferedImage src, Point ini_selectedPoint, int y) throws LineArrivdBorderEx      {
		int cur_line_point_color = src.getRGB(ini_selectedPoint.x, y);// x is ini,y is next line point 
		try {
			if(newLineFirstColorCheckFun!=null)
			newLineFirstColorCheckFun.apply(cur_line_point_color);
		} catch (CurPixArrivdBoderEx e1) {
			throw new LineArrivdBorderEx();
		}
	
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
	/**
	 * from down 2 up scan..
	 * @param src
	 * @param ini_selectedPoint
	 * @throws LineArrivdBorderEx
	 */
	public void trave_upScan_lineByLine(BufferedImage src, Point ini_selectedPoint) throws   LineArrivdBorderEx {
		int width = src.getWidth();
		int height = src.getHeight();
		//  ini_select_color_int = src.getRGB(ini_selectedPoint.x, ini_selectedPoint.y);

		for (int y = ini_selectedPoint.y-1; y >0; y--) {

			scanLine(src, ini_selectedPoint, y);

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
			this.process_cur_Pix_Point_Fun_Handler.apply(preLeftPoint);
//

		}
	}

 

	private void rightScan(BufferedImage src,  Point selectedPoint, int y) throws CurPixArrivdBoderEx {
		//int select_color_int = src.getRGB(selectedPoint.x, selectedPoint.y);//-65536 red
	//	Color curClr=new Color(ini_select_color_int);
		for (int x = selectedPoint.x+1; x < src.getWidth(); x++) {

			Point nextRightPoint=new Point(x, y);
			this.process_cur_Pix_Point_Fun_Handler.apply(nextRightPoint);
		}
	}


}
