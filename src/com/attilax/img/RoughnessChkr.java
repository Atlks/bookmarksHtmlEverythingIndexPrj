/**
 * 
 */
package com.attilax.img;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * RoughnessChkr
 * @author attilax 2016年12月19日 下午4:54:20
 */
public class RoughnessChkr {

	/**
	 * attilax 2016年12月19日 下午4:54:20
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String i = "C:\\00gou\\R011.png";
//		long dfns = DefinTest.getDefinetionsSum(i);
//
//		String blu5 = "C:\\00gou\\R081.png";
//
//		long dfns_blu5 = DefinTest.getDefinetionsSum(blu5);
//
 		String dfns_blu9 = args[0];
		// String blu9 = "C:\\00clr\\blur9_gray.jpg";
		//
		// long dfns_blu9 =DefinTest. getDefinetionsSum(blu9);
	//	System.out.println("bl0:" + dfns + ",bl5:" + dfns_blu5 + ",bl9:" + dfns_blu9);
		long dist=DefinTest. getDefinetionsSum(dfns_blu9);
		System.out.println(dist);

	}
	
	
	  /*
	   * 想构造一系列平滑过渡的颜色，用HSV颜色空间容易，用RGB较难。
	   * 
	   * 将色彩由HSV空间转换到RGB空间
	   * 
	   * h  颜色      用角度表示，范围：0到360度
	   * s  色度      0.0到1.0   0为白色，越高颜色越“纯”
	   * v  亮度      0.0到1.0   0为黑色，越高越亮
	   */
	public  Color HSVtoRGB(float h /* 0~360 degrees */, float s /* 0 ~ 1.0 */, float v /* 0 ~ 1.0 */ )
	  {
	    float f, p, q, t;
	    if( s == 0 ) { // achromatic (grey)
	      return makeColor(v,v,v); 
	    }

	    h /= 60;      // sector 0 to 5
	    int i = (int) Math.floor( h );
	    f = h - i;      // factorial part of h
	    p = v * ( 1 - s );
	    q = v * ( 1 - s * f );
	    t = v * ( 1 - s * ( 1 - f ) );
	    switch( i ) {
	      case 0:
	        return makeColor(v,t,p);
	      case 1:
	        return makeColor(q,v,p);
	      case 2:
	        return makeColor(p,v,t);
	      case 3:
	        return makeColor(p,q,v);
	      case 4:
	        return makeColor(t,p,v);
	      default:    // case 5:
	        return makeColor(v,p,q);
	    }
	  }

	private Color makeColor(float v, float v2, float v3) {
		 
		return new Color((int)v,(int) v2, (int)v3);
	}

	private static int calcGray(int r, int g, int b) {
		// TODO Auto-generated method stub
		return 	 (int) (0.30*r + 0.59*g + 0.11*b) ;
	}

	private static void tt() {
		String i = "C:\\00clr\\1_gray.jpg";
		long dfns = getDefinetionsSum(i);

		String blu5 = "C:\\00clr\\2_gray.jpg";

		long dfns_blu5 = getDefinetionsSum(blu5);

		String blu9 = "C:\\00clr\\blur9_gray.jpg";

		long dfns_blu9 = getDefinetionsSum(blu9);
		System.out.println("bl0:" + dfns + ",bl5:" + dfns_blu5 + ",bl9:"
				+ dfns_blu9);
	}

	public static long getDefinetionsSum(String i) {
		BufferedImage src = imgx.toImg(i);
		int sum = 0;
		int wid = src.getWidth();
		int h = src.getHeight();
		for (int w = 0; w < wid; w++)
			for (int y = 0; y < h; y++) {
				int v = (int) imgx.getHsv(src, w, y).v;
				try {
					if (y + 1 > (h-1))
						continue;
					int clr_next = (int) imgx.getHsv(src, w, y + 1).v;
					int tsa = Math.abs(v - clr_next);
					sum = sum + tsa;
				} catch (Exception e) {
					System.out.println(e.getMessage()+" w-h:"+w+"-"+y);
				}
				

			}
		return sum;
	}

}
