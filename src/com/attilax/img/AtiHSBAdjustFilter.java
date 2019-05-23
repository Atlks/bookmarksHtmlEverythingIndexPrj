package com.attilax.img;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.attilax.img.other.ColorUtil;
import com.jhlabs.image.HSBAdjustFilter;

public class AtiHSBAdjustFilter  {

	private float hFactor;
	private float sFactor;
	private float bFactor;

	@SuppressWarnings("all")
	public BufferedImage filter(BufferedImage bg, BufferedImage dest) {

		imgx.trave_hori(bg, (x, y) -> {
			
			int c = bg.getRGB(x, y);
//			if(c>0 || c<0 )
//			{
//				bg.setRGB(x, y, new Color(255,255,255).getRGB());
//				return;
//			}

				if (c == -1) // wit
				{
					return;
				}

				Color cc2 = new Color(c);
				if(cc2.getRed()>150)
					return;
				if (ColorUtil.isWit(cc2))
				{
					
					bg.setRGB(x, y, new Color(255,255,255,0).getRGB());
					return;
				}
				
				if (ColorUtil.isSimilarWit(cc2))
					return;
				
				if (ColorUtil.isSimilarGray(cc2))
					return;
				HSV hsv = ColorUtil.rgb2hsv(c);
				if(hsv.s<0.2) //baybe gray
					return;
				hsv.h += this.hFactor;
				if (hsv.h > 360)
					hsv.h = hsv.h - 360;
				hsv.s += this.sFactor;
				if (hsv.s > 1)
					hsv.s = 1;
				hsv.v += this.bFactor;
				if (hsv.v > 1)
					hsv.v = 1;
				int clr = ColorUtil.HSVtoRGB(hsv);
				Color cc = new Color(clr);
				bg.setRGB(x, y, clr);

			});

		return bg;

	}

	public void setHFactor(float h) {
		this.hFactor=h;
		
	}

	public void setSFactor(float s) {
		sFactor=s;
		
	}

	public void setBFactor(float v) {
		bFactor=v;
		
	}

}
