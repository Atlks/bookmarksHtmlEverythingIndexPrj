package com.attilax.img.other;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

import com.attilax.db.EmptyEx;
import com.attilax.ex.notFindTargetElemt;
import com.attilax.img.HSV;
import com.attilax.img.HsvRangeV2;
import com.attilax.img.ImgTraver_lineScaner;
import com.attilax.img.imgx;

public class ColorUtil {

	public static void main(String[] args) {
		Color c = new Color(227, 228, 232);
		HSV hsv = imgx.RGB2HSV(c);
		System.out.println(hsv);
		Color c2 = ColorUtil.HSVtoRGBColorV2(hsv);
		System.out.println(c2);
	}

	@Deprecated
	// private static Color HSVtoRGBColor(HSV hsv) {
	//
	// return HSVtoRGB(hsv.h, hsv.s, hsv.v);
	// }
	public static int avgClr(List<Integer> colors) {
		int r_sum = 0;
		int g = 0;
		int b = 0;
		for (Integer integer : colors) {
			Color cl = new Color(integer);
			r_sum = cl.getRed() + r_sum;
			g = cl.getGreen() + g;
			b = cl.getBlue() + b;
		}
		int avg_r = r_sum / colors.size();
		int avr_g = g / colors.size();
		int avr_b = b / colors.size();
		return new Color(avg_r, avr_g, avr_b).getRGB();
	}

	public static int avgClr(Integer[] cls) {
		int r_sum = 0;
		int g = 0;
		int b = 0;
		Color cl;
		for (Integer integer : cls) {
			cl = new Color(integer);
			r_sum = cl.getRed() + r_sum;
			g = cl.getGreen() + g;
			b = cl.getBlue() + b;
		}
		int len = cls.length;
		int avg_r = r_sum / len;
		int avr_g = g / len;
		int avr_b = b / len;
		int rgb = new Color(avg_r, avr_g, avr_b).getRGB();
		return rgb;
	}

	public static int avgColorHsv(HSV[] cls_hsv_arr) {
		int h_sum = 0;
		float s = 0;
		float v = 0;
		for (HSV c : cls_hsv_arr) {
			// cl=new Color(integer);
			h_sum = (int) (h_sum + c.h);
			s = (s + c.s);
			v = (v + c.v);
		}

		int len = cls_hsv_arr.length;
		int h = h_sum / len;
		float avr_g = s / len;
		float avr_b = v / len;
		HSV hsv = new HSV(h, avr_g, avr_b);
		int rgb = HSVtoRGB(hsv);
		return rgb;
	}

	public static HSV avgHsv(List<HSV> cls_hsv_arr) {
		int h_sum = 0;
		float s = 0;
		float v = 0;
		for (HSV c : cls_hsv_arr) {

			h_sum = (int) (h_sum + c.h);
			s = (s + c.s);
			v = (v + c.v);
		}

		int len = cls_hsv_arr.size();
		int h = h_sum / len;
		float avr_g = s / len;
		float avr_b = v / len;
		HSV hsv = new HSV(h, avr_g, avr_b);
		// int rgb = HSVtoRGB(hsv);
		return hsv;
	}

	public static int HSVtoRGB(HSV hsv) {

		return HSVtoRGBColorV2(hsv).getRGB();
	}

	public static Color HSVtoRGBColorV2(HSV hsv) {
		float h = hsv.h;
		float s = hsv.s;
		float v = hsv.v;
		int Hi = (int) ((h / 60) % 6);
		float f = (h / 60) - Hi;

		float p = v * (1 - s);
		float q = v * (1 - f * s);
		float t = v * (1 - (1 - f) * s);
		switch (Hi) {
		case 0:
			return makeColor(v, t, p);
		case 1:
			return makeColor(q, v, p);
		case 2:
			return makeColor(p, v, t);
		case 3:
			return makeColor(p, q, v);
		case 4:
			return makeColor(t, p, v);
		default: // case 5:
			return makeColor(v, p, q);
		}
		// return HSVtoRGB(hsv.h, hsv.s, hsv.v).getRGB();
	}

	/*
	 * 鎯虫瀯閫犱竴绯诲垪骞虫粦杩囨浮鐨勯鑹诧紝鐢℉SV棰滆壊绌洪棿瀹规槗锛岀敤RGB杈冮毦銆�
	 * 
	 * 灏嗚壊褰╃敱HSV绌洪棿杞崲鍒癛GB绌洪棿
	 * 
	 * h 棰滆壊 鐢ㄨ搴﹁〃绀猴紝鑼冨洿锛�0鍒�360搴� s 鑹插害 0.0鍒�1.0 0涓虹櫧鑹诧紝瓒婇珮棰滆壊瓒娾�滅函鈥� v 浜害 0.0鍒�1.0 0涓洪粦鑹诧紝瓒婇珮瓒婁寒
	 */
	@Deprecated
	public static Color HSVtoRGB(float h /* 0~360 degrees */, float s /* 0 ~ 1.0 */, float v /*
																							 * 0
																							 * ~
																							 * 1.0
																							 */) {
		float f, p, q, t;
		if (s == 0) { // achromatic (grey)
			return makeColor(v, v, v);
		}

		h = h / 60; // sector 0 to 5
		int i = (int) Math.floor(h);
		f = h - i; // factorial part of h
		p = v * (1 - s);
		q = v * (1 - s * f);
		t = v * (1 - s * (1 - f));
		switch (i) {
		case 0:
			return makeColor(v, t, p);
		case 1:
			return makeColor(q, v, p);
		case 2:
			return makeColor(p, v, t);
		case 3:
			return makeColor(p, q, v);
		case 4:
			return makeColor(t, p, v);
		default: // case 5:
			return makeColor(v, p, q);
		}
	}

	private static Color makeColor(float v, float v2, float v3) {

		return new Color(v, v2, v3);
	}

	public static int avgColorHsv(HSV[] cls_hsv_arr, double s_all, double v_all) {
		int h_sum = 0;
		float s = 0;
		float v = 0;
		for (HSV c : cls_hsv_arr) {
			// cl=new Color(integer);
			h_sum = (int) (h_sum + c.h);
			s = (s + c.s);
			v = (v + c.v);
		}

		int len = cls_hsv_arr.length;
		int h = h_sum / len;
		float avr_g = s / len;
		float avr_b = v / len;
		HSV hsv = new HSV(h, avr_g, avr_b);
		// HSV hsv = new HSV(h, (float)s_all, (float)v_all);
		int rgb = HSVtoRGB(hsv);
		Color clr = new Color(rgb);
		return rgb;
	}

	public static HSV avgColor_RetHsv(HSV[] cls_hsv_arr) {
		int h_sum = 0;
		float s = 0;
		float v = 0;
		for (HSV c : cls_hsv_arr) {
			// cl=new Color(integer);
			h_sum = (int) (h_sum + c.h);
			s = (s + c.s);
			v = (v + c.v);
		}

		int len = cls_hsv_arr.length;
		int avr_h = h_sum / len;
		float avr_s = s / len;
		float avr_b = v / len;
		HSV hsv = new HSV(avr_h, avr_s, avr_b);
		// int rgb = HSVtoRGB(hsv);
		return hsv;
	}
//reutn hsv255mode
	public static HSV rgb2hsv(int rgb) {

		return imgx.RGB2HSV(rgb);
	}

	public static Color avgClr_byLiClr(List<Color> li_c) {
		int r_sum = 0;
		int g = 0;
		int b = 0;
		// Color cl = null;
		for (Color c1 : li_c) {

			r_sum = c1.getRed() + r_sum;
			g = c1.getGreen() + g;
			b = c1.getBlue() + b;
		}
		int len = li_c.size();
		int avg_r = r_sum / len;
		int avr_g = g / len;
		int avr_b = b / len;
		// int rgb = .getRGB();
		return new Color(avg_r, avr_g, avr_b);
		// return avgClr(ca);
	}
/**
 * white
 * {
	"h":0,
	"s":0,
	"v":1,
	"x":0,
	"y":0
}

gray  {
	"h":0,
	"s":0,
	"v":0.98039216,

 * @param c
 * @return
 */
	public static HSV rgb2hsv(Color c) {

		return imgx.RGB2HSV(c);
	}

	public static int gray(Color p) {

		return imgx.gray(p.getRGB());
	}

	public static HSV newHSV(String[] split) {
		float h = Float.valueOf(split[0]);
		float s = Float.valueOf(split[1]);
		float v = Float.valueOf(split[2]);
		HSV hs = new HSV(h, s, v);
		return hs;
	}

	public static boolean isWit(Color cc2) {

		boolean r_rzt = cc2.getRed() == 255;
		boolean g_rzt = cc2.getGreen() == 255;
		boolean b_rzt = cc2.getBlue() == 255;
		boolean rzt = r_rzt && g_rzt && b_rzt;
		return rzt;
	}

	public static boolean isSimilarWit(Color cc2) {

		return cc2.getRed() > 240 && cc2.getGreen() > 240 && cc2.getBlue() > 240;
	}

	public static boolean isSimilarGray(Color cc2) {
		int dif = Math.abs(cc2.getRed() - cc2.getGreen());
		int dif2 = Math.abs(cc2.getRed() - cc2.getBlue());
		int dif3 = Math.abs(cc2.getGreen() - cc2.getBlue());
		return dif < 20 && dif2 < 20 && dif3 < 20;
	}

	/**
	 * attilax 2016骞�11鏈�15鏃� 涓嬪崍5:43:39
	 * 
	 * @param src
	 * @param point
	 * @return
	 */
	public static boolean isSimilarWit(BufferedImage src, Point point) {
		int rgb = src.getRGB(point.x, point.y);
		Color c = new Color(rgb);
		return isSimilarWit(c);
	}

	/**
	 * attilax 2016骞�11鏈�15鏃� 涓嬪崍7:31:27
	 * 
	 * @param src
	 * @param li_hsv
	 * @return
	 */
	public static boolean hasDarkColor(BufferedImage src, List<Optional<HSV>> li_hsv,double lmt) {
		for (Optional<HSV> h : li_hsv) {
			if (h.isPresent()) {
			//	double lmt = 0.6;
				if (h.get().v < lmt)
					return true;
			}
		}

		return false;
	}

	/**
	 * attilax 2016骞�11鏈�15鏃� 涓嬪崍7:36:29
	 * 
	 * @param src
	 * @param li_hsv
	 * @return
	 */
	public static boolean hasAnyForgeColor(BufferedImage src, List<Optional<HSV>> li_hsv) {
		for (Optional<HSV> h : li_hsv) {
			if (h.isPresent()) {
				if (h.get().v < 0.3)
					return true;
			}
			// h.ifPresent( (x) -> {
			//
			// //return true;
			// });
		}
		return false;
	}

	/**
	attilax    2016骞�11鏈�15鏃�  涓嬪崍7:56:31
	 * @param li_hsv
	 * @return
	 * @throws notFindTargetElemt 
	 */
	public static  HSV darkestColor(List<Optional<HSV>> li_hsv,double lmt) throws notFindTargetElemt {
		float most = 1;
		HSV hsv2 = null;
		for (Optional<HSV> h : li_hsv) {
			if(h.isPresent())
			{
				HSV h1=h.get();
				if(h1.v<most)
				{
					most=h1.v;
					hsv2=h1;
				}
				
			}
//			h.ifPresent(h1->{
//				
//			});
		}
		
		if(hsv2==null)
			throw  new notFindTargetElemt();
		else if(hsv2.v>lmt )
			throw  new notFindTargetElemt();
		else
			return hsv2;
	}

//	public static Point getDarkestPoint(Point pt, Point p_next, BufferedImage img2) {
//		Point darkst;
//		int gray1=getGray(pt,img2);
//		int gray2=getGray(p_next,img2);
//		if( Math.abs(gray1-gray2)>0)
//		return this;
//	}
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
	private static int getGray(Point pt, BufferedImage img2) {
		 int clr_int=img2.getRGB(pt.x, pt.y);
		return imgx.gray(clr_int);
	//	return this;
	}

	public static HSV  to100ModeStandmode(HSV hsv) {
		HSV hs=new HSV(hsv.h, hsv.s*255, hsv.v*255);
		return hs;
	}
	
	
	/*nont starnd ,,stand is 100mode*/
	
	public static HSV  to255Mode(HSV hsv) {
		HSV hs=new HSV(hsv.h, hsv.s*255, hsv.v*255);
		return hs;
	}

	public static int colorCounts(BufferedImage src2, HSV hi, HSV low) {
		
		HsvRangeV2 hr=new HsvRangeV2(low, hi);
		
		int width = src2.getWidth();
		int height = src2.getHeight();
		int ct = 0;
		Color pixelColor ;
		for(int i=0;i<width;i++)
		{
		
			for(int j=0;j<height;j++)
			{
				 
					int n=	src2.getRGB(i, j);
				 
					pixelColor= new Color(n);
					HSV hsv=ColorUtil.rgb2hsv(pixelColor);
					 if( hr.contain(hsv))
						ct++;
				//	System.out.println(n+",cord:"+i+"-"+j+",rgb:"+r+"-"+g+"-"+b);
				//} catch (Exception e) {
				//	e.printStackTrace();
					//System.out.println(n+",corderr:"+i+"-"+j);
			//	}
			}
		}
		return ct;
	 
	}

	public static HSV to100ModeHsv(int clr_int) {
		HSV hsv_255mode=rgb2hsv(clr_int);
//		HSV setHsv255mode = new HSV().setHsv255mode((int)hsv_255mode.h, (int)hsv_255mode.s, (int)hsv_255mode.v);
		return hsv_255mode;
	}

}
