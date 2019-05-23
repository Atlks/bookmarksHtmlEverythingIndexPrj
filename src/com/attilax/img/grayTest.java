package com.attilax.img;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.attilax.io.filex;
import com.attilax.json.AtiJson;
import com.jhlabs.image.GrayscaleFilter;

public class grayTest {

	/**gray hsv 0,0,218
	 * HSL 表示 hue（色相）、saturation（饱和度）、lightness（亮度），
	 * HSV表示 hue、saturation、value(色调) 而 HSB 表示 hue、saturation、brightness（明度）。
	 * @param args
	 */
	public static void main(String[] args) {
		
		String i="C:\\00clr\\1_gray.jpg";
	 BufferedImage arg0=imgx.toImg(i);
	 BufferedImage dst=	new GrayscaleFilter().filter(arg0, null);
  //  imgx.save(dst, "C:\\00clr\\1_gray.jpg");
    imgxByJhlabs.gray("C:\\00clr\\2.jpg","C:\\00clr\\2_gray.jpg");
    imgxByJhlabs.gray("C:\\00clr\\blur9.jpg","C:\\00clr\\blur9_gray.jpg");
//	 int clr=arg0.getRGB(10, 10);
//	 HSV hs=imgx.RGB2HSV(clr);
//	 System.out.println(AtiJson.toJson(hs));
	 
	}

}
