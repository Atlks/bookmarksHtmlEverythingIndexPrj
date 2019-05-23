package com.attilax.img;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

import com.attilax.concur.TaskUtil;
import com.attilax.img.other.ArrivdBorderEx;
import com.attilax.io.FileExistEx;
import com.attilax.io.filex;

public class FoolFillX {

	public static void main(String[] args) throws FileExistEx {
		TaskUtil.asyn(() -> {
			try {
				Thread.sleep(20000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	System.exit(0);

		}, "");

		/**
		 * cvFloodFill( IplImage* src, CvPoint seedPoint, //漫水法从点seedPoint开始实行算法
		 * CvScalar newVal, //像素点被染色的值
		 */
		BufferedImage bi = imgx.toImg("c:\\00qb4\\a.jpg");
		new FoolFillX().foolfile(bi, 0, 0, new Color(255, 0, 0));
		imgx.save(bi, filex.addSuffix("c:\\00qb4\\a.jpg", filex.getUUidName()));

		System.out.println("--f");

	}

	ImagTraver settNewColor;

	private void foolfile(BufferedImage img, int xStart, int yStart, Color color) {

		settNewColor = (x, y) -> {
			// return ;
			scanEvt();
		};
		
		Point pt = new Point(xStart, yStart);
		ini_selectedPoint=pt;
		  ini_select_color_int = img.getRGB(ini_selectedPoint.x, ini_selectedPoint.y);
		// down scan
		
		scanNextDownLine_BySelected_Point_Color(img, pt);

		scanPreUpLine_BySelected_Point_Color(img, pt);
		// startY_is(yStart), (x, y) -> {
		// // return ;
		// scanEvt();
		// });

	}
	int ini_select_color_int;
	Point ini_selectedPoint;
	public void scanNextDownLine_BySelected_Point_Color(BufferedImage src, Point ini_select_color_int2) {
		int width = src.getWidth();
		int height = src.getHeight();
		

		for (int y = ini_select_color_int2.y; y < height; y++) {

			try {
				scanLine(src, ini_select_color_int2, y);
			} catch (ArrivdBorderEx e) {
				break;
			}

		}

	}

	private void scanLine(BufferedImage src, Point ini_selectedPoint, int y) throws ArrivdBorderEx {
		int cur_line_point_color = src.getRGB(ini_selectedPoint.x, y);// x is ini,y is next line point 
		if (!isSimilarColor(cur_line_point_color, ini_select_color_int)) {
			 throw new ArrivdBorderEx();
		}
		// left scan
		leftScan(src, ini_selectedPoint, y);
		// right scan
		rightScan(src, ini_selectedPoint, y);
	}
	
	public void scanPreUpLine_BySelected_Point_Color(BufferedImage src, Point ini_selectedPoint) {
		int width = src.getWidth();
		int height = src.getHeight();
		//  ini_select_color_int = src.getRGB(ini_selectedPoint.x, ini_selectedPoint.y);

		for (int y = ini_selectedPoint.y-1; y >0; y--) {

			try {
				scanLine(src, ini_selectedPoint, y);
			} catch (ArrivdBorderEx e) {
				break;
			}

		}

	}

	/**
	 * left and curSelectPoint
	 * @param src
	 * @param ini_selectedPoint
	 * @param y
	 */
	private void leftScan(BufferedImage src,Point ini_selectedPoint, int y) {
		for (int x = ini_selectedPoint.x; x > 0; x--) {
			if(x==15)
				System.out.println("dbg");
			// trver.trave(x, y);
			try {
				Point preLeftPoint=new Point(x, y);
				setPointToNewColor(preLeftPoint,new Color(255, 0, 0).getRGB(),src, ini_select_color_int );
			} catch (ArrivdBorderEx e) {
				break;
			}

		}
	}

	private void rightScan(BufferedImage src,  Point selectedPoint, int y) {
		//int select_color_int = src.getRGB(selectedPoint.x, selectedPoint.y);//-65536 red
		Color curClr=new Color(ini_select_color_int);
		for (int x = selectedPoint.x+1; x < src.getWidth(); x++) {

			try {
				Point nextRightPoint=new Point(x, y);
				setPointToNewColor( nextRightPoint,new Color(255, 0, 0).getRGB(),src, ini_select_color_int);
			} catch (ArrivdBorderEx e) {
				break;
			}
		}
	}

	private void setPointToNewColor(Point curPoint, int newColor,BufferedImage src, int select_color_int) throws ArrivdBorderEx {
		int cur_color = src.getRGB(curPoint.x, curPoint.y);//-1 is white
		Color curClr=new Color(cur_color);
		if (isSimilarColor(cur_color , select_color_int)) {
			src.setRGB(curPoint.x, curPoint.y, new Color(255, 0, 0).getRGB());
		} else {// to border
				// break;
			throw new ArrivdBorderEx();

		}
	}

	private boolean isSimilarColor(int cur_color, int select_color_int) {
		 if(cur_color==select_color_int)
			 return true;
			Color curClr=new Color(cur_color);
			Color select_color=new Color(select_color_int);
			int red_diff=Math.abs( curClr.getRed()-select_color.getRed());
			int green_diff=Math.abs( curClr.getGreen()-select_color.getGreen());
			int blue_diff=Math.abs( curClr.getBlue()-select_color.getBlue());
			if(red_diff<25 && green_diff<25 && blue_diff<25)
				return true;
		return false;
	}

	private int startY_is(int yStart) {
		// TODO Auto-generated method stub
		return yStart;
	}

	private int startX_is(int xStart) {

		return xStart;
	}

	private void scanEvt() {
		// TODO Auto-generated method stub

	}

}
