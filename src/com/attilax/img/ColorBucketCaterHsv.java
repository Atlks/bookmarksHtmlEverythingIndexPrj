package com.attilax.img;

import java.awt.Color;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;


import com.attilax.img.other.ColorUtil;
import com.attilax.json.AtiJson;
import com.google.common.collect.Lists;

public class ColorBucketCaterHsv {

	public static void main(String[] args) {
	//	System.out.println(AtiJson.toJson(new ColorBucketCaterHsv().iniBukersByGraysplit(5, 255)));
	}

	private List<Layer> list;
	private Matrix mtrx;

 

	List<HSV> matrix_li_clr_hsv4dbg = Lists.newArrayList();// for dbg

 
	public void assignMatrixColorsToBukesByHsv(Matrix mtrx) {
		matrix_li_clr_hsv4dbg = Lists.newArrayList(); //for debg
		clrAllBukesColors();// for ini
		this.mtrx = mtrx;
		List<Optional<HSV>> colorli = mtrx.li_hsv;
		for (Optional<HSV> clr : colorli) {
			// Consumer<T>
			clr.ifPresent((p) -> {
			//	Integer color = clr.get();
				
				HSV hsv= clr.get();
				Color c =  ColorUtil.HSVtoRGBColorV2(hsv);
				matrix_li_clr_hsv4dbg.add(hsv);
				assignToBuke(hsv);
			});

		}

//		List<Optional<Integer>> li = mtrx.colorLiV2;
//		for (Optional<Integer> clr : li) {
//			// Consumer<T>
//			clr.ifPresent((p) -> {
//				Integer color = clr.get();
//				int gray = imgx.gray(color);
//				assignToBuke(gray, color);
//			});
//
//		}
		// System.out.println("-- mtrx cloor");
		// System.out.println( AtiJson.toJson(li_cor) );
		// System.out.println("-- assed buke list");
		// System.out.println( AtiJson.toJson(list) );
		// System.out.println("dbg");

	}

	public void clrAllBukesColors() {
		for (Layer buker : list) {

			buker.clrAllColors();
		}

	}

 
	private void assignToBuke(HSV hsv) {
		for (Layer buker : list) {
			if (hsv.h >= buker.min && hsv.h <= buker.max)
			 
				buker.addColor(hsv);
		}

	}
 
	

	public Layer MaxColrsBukr_hsv() {
		Layer max = new Layer();
		for (Layer buker : list) {
			if (buker.hsvs.size() > max.rgbints.size())
				max = buker;
		}

		return max;
	}

	public List<Layer> iniBukersByHsvSplit(int clrNums, int maxColorValue) {
		List<Layer> li = Lists.newArrayList();
		int span = maxColorValue / clrNums;
		for (int i = 0; i < clrNums; i++) {

			Layer bk = new Layer();
			bk.min = i * (span);
			bk.max = (i + 1) * span - 1;
			// if(bk.max>max)
			// bk.max=max;
			if (i == clrNums - 1) // last
				bk.max = maxColorValue;
			li.add(bk);
		}
		this.list = li;
		return li;
	}

 

}
