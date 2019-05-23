package com.attilax.img;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.attilax.img.other.ColorUtil;


public class DCT {
	public static int y_last=0;
	public static void main(String[] args) {
		String f="d:\\8864.png";
		BufferedImage img=imgx.toImg(f);
		System.out.println(Math.sqrt(4));
		int n=8;
		double sqrt1 = Math.sqrt( 1d/4d);
		double sqrt2 = Math.sqrt(1d/2d);
		System.out.println(sqrt1*sqrt2);
		
		imgx.trave_hori(img, (x,y)->{
			
			int clr=img.getRGB(x, y);
			int gray=ColorUtil.gray(new Color(clr));
			gray=gray-128;
			System.out.print( gray + " ");
			y_last++;
			if(y_last %8 ==0)
			// if(y_last!=y)
			 {
				 System.out.print(  "\r\n");
				// y_last=y;
			 }
			//System.out.println(x+","+y+" ,gray:"+gray);
		});
		//return this;
	}

}
