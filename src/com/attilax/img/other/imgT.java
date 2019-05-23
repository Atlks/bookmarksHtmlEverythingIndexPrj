package com.attilax.img.other;

import java.awt.Color;
import java.util.List;

import org.opencv.core.CvType;

import com.attilax.img.HSV;
import com.attilax.img.imgx;
import com.google.common.collect.Lists;

public class imgT {

	public static void main(String[] args) {
		System.out.println(CvType.CV_32FC1);
		Color c = new Color(100, 250, 55);
		Color c2 = new Color(69, 176, 216);
		List<Color> li_c = Lists.newArrayList();
		li_c.add(c);
		li_c.add(c2);
		Color c_avg = ColorUtil.avgClr_byLiClr(li_c);

		HSV h = ColorUtil.rgb2hsv(c.getRGB());
		HSV h2 = ColorUtil.rgb2hsv(c2.getRGB());
		List<HSV> li_h = Lists.newArrayList();
		li_h.add(h);
		li_h.add(h2);
		HSV h_avg = ColorUtil.avgHsv(li_h);

		 Color h2c=ColorUtil.HSVtoRGBColorV2(h_avg);
		System.out.println(c_avg);
		System.out.println(h_avg);
		System.out.println(h2c);

	}

}
