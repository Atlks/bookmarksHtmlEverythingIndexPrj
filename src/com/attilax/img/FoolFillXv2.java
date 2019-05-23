package com.attilax.img;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.function.Function;

import com.attilax.concur.TaskUtil;
import com.attilax.img.other.ArrivdBorderEx;
import com.attilax.img.other.LineArrivdBorderEx;
import com.attilax.img.other.NewLineColorCheck;
import com.attilax.img.other.ProcessPointColor;
import com.attilax.img.other.CurPixArrivdBoderEx;
import com.attilax.io.FileExistEx;
import com.attilax.io.filex;

public class FoolFillXv2 {

	public static void main(String[] args) throws FileExistEx {
		TaskUtil.asyn(() -> {
			try {
				Thread.sleep(20000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.exit(0);

		}, "");

		/**
		 * cvFloodFill( IplImage* src, CvPoint seedPoint, //漫水法从点seedPoint开始实行算法
		 * CvScalar newVal, //像素点被染色的值
		 */
		BufferedImage bi = imgx.toImg("c:\\00qb4\\a.jpg");
		new FoolFillXv2().foolfile(bi, 50, 50, new Color(255, 0, 0));
		imgx.save(bi, filex.addSuffix("c:\\00qb4\\a.jpg", filex.getUUidName()));

		System.out.println("--f");

	}

	ImagTraver settNewColor;

	public void foolfile(BufferedImage img, int xStart, int yStart, Color color) {

		Point startPixPoint = new Point(xStart, yStart);
		color= new Color(255, 0, 0);
		floodFill(img, startPixPoint,color);

	}

	public void floodFill(BufferedImage img, Point startPixPoint, Color NewColor) {
		ini_select_color_int = img.getRGB(startPixPoint.x, startPixPoint.y);
		ImgTraver_lineScaner ImgTraver_lineScaner1 = new ImgTraver_lineScaner();
		//检测是否遇到边缘
		ImgTraver_lineScaner1.checkIsMeetBorderPix_Handler = (cur_color) -> {
			return (!imgx.isSimilarColor(cur_color, ini_select_color_int));

		};  
		//扫描到每个像素点的处理，遇到边框终止，否则设置为新颜色
		ImgTraver_lineScaner1.cur_Pix_Point_process_Fun_Handler = (cur_point) -> {
			int cur_color = img.getRGB(cur_point.x, cur_point.y);
			Boolean isBorderPix = ImgTraver_lineScaner1.checkIsMeetBorderPix_Handler.apply(cur_color);
			//System.out.println("isBorderPix:" + isBorderPix);
			if (isBorderPix)
				throw new CurPixArrivdBoderEx();
			img.setRGB(cur_point.x, cur_point.y, NewColor.getRGB() );
 

		};

		ImgTraver_lineScaner1.scan_Byline_from_startPixPoint(img, startPixPoint);

	}

	/**
	attilax    2016年11月9日  下午6:46:03
	 * @return
	 */
	private int get_red_color() {
	 
		return   new Color(255, 0, 0).getRGB();
	}

	int ini_select_color_int;
	Point ini_selectedPoint;

	private void setPointToNewColor_when_if_curPointColor_is_similar_color_with_seedColor(Point curPoint, int newColor, BufferedImage src, int ini_select_color_int) throws CurPixArrivdBoderEx {
		int cur_color = src.getRGB(curPoint.x, curPoint.y);// -1 is white
		Color curClr = new Color(cur_color);
		if (imgx.isSimilarColor(cur_color, ini_select_color_int)) {
			src.setRGB(curPoint.x, curPoint.y, new Color(255, 0, 0).getRGB());
		} else {// to border
				// break;
			throw new CurPixArrivdBoderEx();

		}
	}

}
