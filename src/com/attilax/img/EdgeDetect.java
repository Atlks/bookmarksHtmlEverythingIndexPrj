package com.attilax.img;

import java.awt.Point;
import java.awt.image.BufferedImage;

import com.attilax.img.other.ColorUtil;
import com.attilax.img.other.LineArrivdBorderEx;

@Deprecated
public class EdgeDetect {

	public static void main(String[] args) throws LineArrivdBorderEx {
		String img="C:\\000sklt\\2_gray.jpg";
		BufferedImage img2=imgx.toImg(img);
		ImgTraver it=new ImgTraver();
		it.process_cur_Pix_Point_Fun_Handler=(pt)->{
			Point p_next=new Point(pt.x+1,pt.y);
			Point darkestPoint=ColorUtil.getDarkestPoint(pt,p_next,img2);
		};
		it.trave_downScan_lineByline(img2, new Point(0,0));
		//return this;
	}

}
