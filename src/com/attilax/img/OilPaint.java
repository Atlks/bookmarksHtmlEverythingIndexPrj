package com.attilax.img;

import java.awt.image.BufferedImage;


import com.attilax.io.FileExistEx;
import com.attilax.io.filex;
import com.attilax.lang.tryX;
import com.attilax.util.randomx;


public class OilPaint {

	public static void main(String[] args) throws FileExistEx {
	String s="C:\\000oilpaint\\a.jpg";
	BufferedImage bi=imgx.toImg(s);
	oilPaint(bi);
	imgx.save(bi, filex.addSuffix("C:\\000oilpaint\\a.jpg",filex.getUUidName()));
	System.out.println("--f");
	}

	private static void oilPaint(BufferedImage bi) {
		imgx.trave(bi, (x,y)->{
			int random = randomx.randomNum(1, 5);
			int newX=randomx.randomNum(1, 5)+x;
			int newY=randomx.randomNum(1, 5)+y;
			int nowColor=bi.getRGB(x, y);
			try {
				bi.setRGB(newX, newY, nowColor);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println(e.getMessage()+" x:"+x+",y:"+y);
			}
			
			
		});
	}

}
