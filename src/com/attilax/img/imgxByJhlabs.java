package com.attilax.img;

import java.awt.image.BufferedImage;

import com.jhlabs.image.GrayscaleFilter;

public class imgxByJhlabs {

	public static void gray(String string, String string2) {
		
		
		
		 BufferedImage arg0=imgx.toImg(string);
		 BufferedImage dst=	new GrayscaleFilter().filter(arg0, null);
	    imgx.save(dst, string2);
		
	}

}
