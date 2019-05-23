package com.attilax.img;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.attilax.img.other.ColorUtil;

public class CoreImg {
	
	public static void main(String[] args) {
		
		
	}

	public static BufferedImage inRange(BufferedImage src, HSV low, HSV hi) {
		
		BufferedImage dst=imgx.new_BackgroudColor_black(src.getWidth(), src.getHeight());
		HsvRangeV2 hr=new HsvRangeV2(low, hi);
		 ImgTraver_lineScaner itl=new ImgTraver_lineScaner(src);
		 itl.cur_Pix_Point_process_Fun_Handler=p->{
 		 int clr_int=src.getRGB(p.x,p. y);
			 Color clr=new Color(clr_int);
			 HSV hsv=ColorUtil.rgb2hsv(clr_int);
			 if(p.x==409 && p.y==257)
			 {
				 System.out.println("dbg");
			 }
			 if(hr.contain(hsv))
			 {
				 dst.setRGB(p.x,p. y, Color.white.getRGB());
			 }
			 
		 };
		 itl.trav();
		return dst;
	}

}
