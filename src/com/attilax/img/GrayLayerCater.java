package com.attilax.img;

import java.awt.Color;
import java.awt.Point;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import com.attilax.img.other.ColorUtil;
import com.attilax.json.AtiJson;
import com.google.common.collect.Lists;

public class GrayLayerCater {

	public GrayLayerCater(int maxValue) {
		 this.maxV=maxValue;
		//	return this;
	}
	public GrayLayerCater() {
		 this.maxV=255;
	}
	public static void main(String[] args) {
		System.out.println(AtiJson.toJson(new GrayLayerCater().iniLayers_groupByGray(5, 255)));
	}

	private List<Layer> Buker_list;
	private Matrix mtrx;

	public List<Layer> iniLayers_groupByGray(int clrNums, int max) {
		List<Layer> li = Lists.newArrayList();
		int span = max / clrNums;
		for (int i = 0; i < clrNums; i++) {

			Layer bk = new Layer();
			bk.min = i * (span);
			bk.max = (i + 1) * span - 1;
			// if(bk.max>max)
			// bk.max=max;
			if (i == clrNums - 1) // last
				bk.max = max;
			li.add(bk);
		}
		this.Buker_list = li;
		return li;
	}

	List<HSV> matrix_li_clr_hsv_4dbg = Lists.newArrayList();// for dbg
	List<Color> matrix_li_clr_4dbg = Lists.newArrayList();// for dbg
	private int maxV;
	public void insertInto_Layers_select_Colors_From_Matrix_Groupby_GrayRang(Matrix mtrx) {
		matrix_li_clr_hsv_4dbg = Lists.newArrayList();
		clrAllBukesColors();// for ini
		this.mtrx = mtrx;
		List<Optional<Integer>> colorli = mtrx.colorLi_clrIntMod;
		for (Optional<Integer> clr : colorli) {
			// Consumer<T>
			clr.ifPresent((p) -> {
				Integer color = clr.get();
				Color c = new Color(color);
				
			//	color
			 	HSV h=ColorUtil.rgb2hsv(c);
			 	matrix_li_clr_hsv_4dbg.add(h);
			 	matrix_li_clr_4dbg.add(c);
			});

		}

		List<Optional<Color>> li = mtrx.li_clr;
		for(int i=0;i<li.size();i++)
		{
			int clrPointIdx=i;
			Optional<Color> clr =li.get(i);
			clr.ifPresent((p) -> {
				//Integer color = clr.get();
				int gray = ColorUtil.gray(p);
				
				assignToBukeByGray(gray, p,clrPointIdx);
			});
		}
		 
		// System.out.println("-- mtrx cloor");
		// System.out.println( AtiJson.toJson(li_cor) );
		// System.out.println("-- assed buke list");
		// System.out.println( AtiJson.toJson(list) );
		// System.out.println("dbg");

	}
@Deprecated  
//only dep ij this clor mode class
	public void assignMatrixColorsToBukesByHsv(Matrix mtrx) {
		matrix_li_clr_hsv_4dbg = Lists.newArrayList(); //for debg
		clrAllBukesColors();// for ini
		this.mtrx = mtrx;
		List<Optional<Integer>> colorli = mtrx.colorLi_clrIntMod;
		for (Optional<Integer> clr : colorli) {
			// Consumer<T>
			clr.ifPresent((p) -> {
				Integer color = clr.get();
				Color c = new Color(color);
				HSV hsv=imgx.RGB2HSV(color);
				matrix_li_clr_hsv_4dbg.add(hsv);
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
		for (Layer buker : Buker_list) {

			buker.clrAllColors();
		}

	}

	private void assignToBukeByGray(int gray, Color p, int clrIdx) {
		for (Layer buker : Buker_list) {
			if (gray >= buker.min && gray <= buker.max)
			{
				addColor(buker,p,clrIdx);;
			//	buker.addColor(p);
				break;
			}
		}

	}
	private void addColor(Layer buker,Color p, int clrIdx) {
		buker.clrs.add(p);
		Point ps= mtrx.li_pts.get(clrIdx);
		HSV h=ColorUtil.rgb2hsv(p);
		h.x=ps.x;h.y=ps.y;
		buker.hsvs.add(h);
		
	}
	private void assignToBuke(HSV hsv) {
		for (Layer buker : Buker_list) {
			if (hsv.h >= buker.min && hsv.h <= buker.max)
			 
				buker.addColor(hsv);
		}

	}

	public Layer get_top1_from_Layers_Orderby_ColorsNum_desc() {
		Layer max = new Layer();
		for (Layer buker : Buker_list) {
			if (buker.clrs.size() > max.clrs.size())
				max = buker;
		}

		return max;
	}
	

	public Layer MaxColrsBukr_hsv() {
		Layer max = new Layer();
		for (Layer buker : Buker_list) {
			if (buker.hsvs.size() > max.rgbints.size())
				max = buker;
		}

		return max;
	}

	public List<Layer> iniBukersByHsvSplit(int clrNums, int max) {
		List<Layer> li = Lists.newArrayList();
		int span = max / clrNums;
		for (int i = 0; i < clrNums; i++) {

			Layer bk = new Layer();
			bk.min = i * (span);
			bk.max = (i + 1) * span - 1;
			// if(bk.max>max)
			// bk.max=max;
			if (i == clrNums - 1) // last
				bk.max = max;
			li.add(bk);
		}
		this.Buker_list = li;
		return li;
	}
	public GrayLayerCater iniLayers(int maxValue) {
		 this.maxV=maxValue;
		return this;
	}
	public GrayLayerCater groupByGrayRang( int grayLevDeep) {
		int clrNums=grayLevDeep;
		int max=this.maxV;
		List<Layer> li = Lists.newArrayList();
		int span = max / clrNums;
		for (int i = 0; i < clrNums; i++) {

			Layer bk = new Layer();
			bk.min = i * (span);
			bk.max = (i + 1) * span - 1;
			// if(bk.max>max)
			// bk.max=max;
			if (i == clrNums - 1) // last
				bk.max = max;
			li.add(bk);
		}
		this.Buker_list = li;
		return this;
	//	return null;
	}
	public GrayLayerCater insertInto_Layers(Matrix matrix1) {
		this.mtrx = matrix1;
		return this;
	}
	public GrayLayerCater insertInto_Layers() {
		// TODO Auto-generated method stub
		return this;
	}
	public GrayLayerCater select_Colors_From(Matrix matrix1) {
		this.mtrx = matrix1;
		return this;
		
	}
	public void Groupby_GrayRang() {
		insertInto_Layers_select_Colors_From_Matrix_Groupby_GrayRang(this.mtrx);
		
	}
	public GrayLayerCater insertTo_Layers_select_Colors_From(Matrix matrix1) {
		this.mtrx = matrix1;
		return this;
	}

 

}
