package com.attilax.img;

import java.awt.image.BufferedImage;

import com.jhlabs.image.GaussianFilter;

public class gausblurTest {

	public static void main(String[] args) {
		String i="C:\\00clr\\1.jpg";
		String string = "C:\\00clr\\blur9.jpg";		
		
		BufferedImage src=imgx.toImg(i);
		
		int sum;
		int wid=src.getWidth();
		int h=src.getHeight();
		for(int w=0;w<wid;w++)
			for(int y=0;y<h;y++)
			{
				int clr=src.getRGB(w, y);
				int clr_next=src.getRGB(w, y+1);
				
			}
	
//		BufferedImage des=new GaussianFilter(9).filter(src,null );
//		
//		imgx.save(des,string)	;
			System.out.println("--f");
	}

}
