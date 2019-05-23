package com.attilax.img;

import java.awt.Color;
import java.util.List;

import com.attilax.img.other.ColorUtil;
import com.attilax.json.AtiJson;
import com.google.common.collect.Lists;

public class Layer {

	public int min;

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

 
	public void setColors(List<Integer> colors) {
		this.rgbints = colors;
	}

 
	public void setColors_objs(List<Color> colors_objs) {
		this.clrs = colors_objs;
	}

	 

	public void setCls(Integer[] cls) {
		this.rgbint_arr = cls;
	}

	public List<Integer> getRgbints() {
		return rgbints;
	}

	public void setRgbints(List<Integer> rgbints) {
		this.rgbints = rgbints;
	}

	public List<Color> getClrs() {
		return clrs;
	}

	public void setClrs(List<Color> clrs) {
		this.clrs = clrs;
	}

	public List<HSV> getHsvs() {
		return hsvs;
	}

	public void setHsvs(List<HSV> hsvs) {
		this.hsvs = hsvs;
	}

 

 
 

	public void setCls_hsv_arr(HSV[] cls_hsv_arr) {
		this.cls_hsv_arr = cls_hsv_arr;
	}

	public int max;
	public List<Integer> rgbints = Lists.newArrayList();
	public List<Color> clrs = Lists.newArrayList();
	public List<HSV> hsvs = Lists.newArrayList();
	public void addColor(int clr) {
		rgbints.add(clr);
		clrs.add(new Color(clr));
	}
	public void addColor(HSV hsv) {
		hsvs.add(hsv);
		
	}

	Integer[] rgbint_arr;
	HSV[] cls_hsv_arr;
	public int avgColor() {
		// String[] arrStr1 = listStr.toArray(new String[] {});
		if (rgbint_arr == null)
			rgbint_arr = rgbints.toArray(new Integer[] {});
		return ColorUtil.avgClr(rgbint_arr);
	}
	
	public Color avgColor_retClr() {
		// String[] arrStr1 = listStr.toArray(new String[] {});
	//	if (rgbint_arr == null)
	//		rgbint_arr = colors_rgbint.toArray(new Integer[] {});
		return ColorUtil.avgClr_byLiClr(clrs);
	}
	
	public HSV avgColor_Hsv() {
		// String[] arrStr1 = listStr.toArray(new String[] {});
		if (cls_hsv_arr == null)
			cls_hsv_arr = hsvs.toArray(new HSV[] {});
		
		return ColorUtil.avgColor_RetHsv(cls_hsv_arr);
	}


	public String toString() {
		return AtiJson.toJson(this);
	}

	public void clrAllColors() {
		rgbints = Lists.newArrayList();
		clrs = Lists.newArrayList();
		hsvs=Lists.newArrayList();
	}

	public void addColor(Color p) {
		 
		clrs.add(p);
		HSV h=ColorUtil.rgb2hsv(p);
		hsvs.add(h);
	}



}
