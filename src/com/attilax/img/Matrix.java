/**
 * 
 */
package com.attilax.img;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import com.attilax.ex.CantFindForgeColorEx;
 
import com.attilax.img.other.ColorUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author attilax 2016年11月8日 下午6:15:13
 */
public class Matrix {

	public int w;
	public int h;
	BufferedImage img;
	List<Integer> colorLi_grbInt_noOP = Lists.newArrayList();
	List<Optional<Integer>> colorLi_clrIntMod = Lists.newArrayList();
	public List<Optional<Color>> li_clr = Lists.newArrayList();
	public List<Optional<HSV>> li_hsv = Lists.newArrayList();
	
	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public List<Integer> getColorLi() {
		return colorLi_grbInt_noOP;
	}

	public void setColorLi(List<Integer> colorLi) {
		this.colorLi_grbInt_noOP = colorLi;
	}

	public List<Optional<Integer>> getColorLiV2() {
		return colorLi_clrIntMod;
	}

	public void setColorLiV2(List<Optional<Integer>> colorLiV2) {
		this.colorLi_clrIntMod = colorLiV2;
	}

	public List<Optional<Color>> getColorLiV3() {
		return li_clr;
	}

	public void setColorLiV3(List<Optional<Color>> colorLiV3) {
		this.li_clr = colorLiV3;
	}

	public int getStartPos_left_x() {
		return startPos_left_x;
	}

	public void setStartPos_left_x(int startPos_left_x) {
		this.startPos_left_x = startPos_left_x;
	}

	public int getStart_top_y() {
		return start_top_y;
	}

	public void setStart_top_y(int start_top_y) {
		this.start_top_y = start_top_y;
	}

	public com.attilax.lang.Function<Integer, Object> getCheckForgeColorFun() {
		return checkForgeColorFun;
	}

	public void setCheckForgeColorFun(com.attilax.lang.Function<Integer, Object> checkForgeColorFun) {
		this.checkForgeColorFun = checkForgeColorFun;
	}

	public Point getLeftTop_point() {
		return leftTop_point;
	}

	public void setLeftTop_point(Point leftTop_point) {
		this.leftTop_point = leftTop_point;
	}

	public BufferedImage getImg() {
		return img;
	}

	private int startPos_left_x;
	private int start_top_y;
	private com.attilax.lang.Function<Integer, Object> checkForgeColorFun;
	private Point leftTop_point;
	private int radis;

	/**
	 * @param i
	 * @param j
	 */
	public Matrix(int i, int j) {
		w = i;
		h = j;
	}

	public Matrix() {
		// TODO Auto-generated constructor stub
	}

	public Matrix(BufferedImage img2) {
		this.img = img2;
	}

	/**
	 * attilax 2016年11月8日 下午6:21:01
	 * 
	 * @param cur_row
	 * @param cur_col
	 */
	public void fill_and_setMtrx_leftTop_XY(int cur_row, int cur_col) {
		colorLi_grbInt_noOP = Lists.newArrayList();
		this.startPos_left_x = cur_row;
		this.start_top_y = cur_col;
		for (int i = cur_row; i < w + cur_row; i++)
			for (int j = cur_col; j < h + cur_col; j++) {
				try {
					colorLi_grbInt_noOP.add(img.getRGB(i, j));
				} catch (ArrayIndexOutOfBoundsException e) {
					colorLi_grbInt_noOP.add(new Color(255, 255, 255).getRGB());
				}
				
			}

	}
	
	public void fill_and_setMtrx_leftTop_XY_v2(int x, int y) {
		this.leftTop_point=new Point(x, y);
		colorLi_clrIntMod = Lists.newArrayList();
		this.startPos_left_x = x;
		this.start_top_y = y;
		for (int i = x; i < w + x; i++)
			for (int j = y; j < h + y; j++) {
				try {
					int rgb = img.getRGB(i, j);
					colorLi_clrIntMod.add(Optional.of(rgb));
				} catch (ArrayIndexOutOfBoundsException e) {
					colorLi_clrIntMod.add(Optional.empty());
				}
				
			}

	}
	
	public void fill_and_setMtrx_leftTop_XY_v3(int x, int y) {
		this.leftTop_point=new Point(x, y);
		colorLi_clrIntMod = Lists.newArrayList();
		li_clr = Lists.newArrayList();
		this.startPos_left_x = x;
		this.start_top_y = y;
		for (int i = x; i < w + x; i++)
			for (int j = y; j < h + y; j++) {
				try {
					int rgb = img.getRGB(i, j);
					Color c=new Color(rgb);
					li_clr.add(Optional.of(c));
				} catch (ArrayIndexOutOfBoundsException e) {
					li_clr.add(Optional.empty());
				}
				
			}

	}
	
	public void fill_and_setMtrx_leftTop_XY_hsvMode(int x, int y) {
		this.leftTop_point=new Point(x, y);
		colorLi_clrIntMod = Lists.newArrayList();
		li_clr = Lists.newArrayList();
		li_hsv = Lists.newArrayList();
		this.startPos_left_x = x;
		this.start_top_y = y;
		for (int i = x; i < w + x; i++)
			for (int j = y; j < h + y; j++) {
				try {
					int rgb = img.getRGB(i, j);
				HSV h=	ColorUtil.rgb2hsv(rgb);
				h.x=i;h.y=j;
				li_hsv.add(Optional.of(h));
				} catch (ArrayIndexOutOfBoundsException e) {
					li_hsv.add(Optional.empty());
				}
				
			}

	}

	/**
	 * attilax 2016年11月8日 下午6:25:02
	 * 
	 * @param object
	 * @return
	 */
	@Deprecated
	public boolean hasAnyForgeColor(
			com.attilax.lang.Function<Integer, Object> object) {

		this.checkForgeColorFun = object;
		for (Integer integer : colorLi_grbInt_noOP) {
			if ((boolean) object.apply(integer))
				return true;
		}
		return false;
	}

	/**
	 * attilax 2016年11月8日 下午6:40:46
	 * 
	 * @return
	 * @throws CantFindForgeColorEx
	 */
	public int getForgeColor() {
		for (Integer integer : colorLi_grbInt_noOP) {
			if ((boolean) checkForgeColorFun.apply(integer))
				return integer;
		}
		throw new RuntimeException("CantFindForgeColorEx();");
	}

	/**
	 * attilax 2016年11月8日 下午6:41:22
	 * 
	 * @return
	 */
	public Map getCenterXy() {
		Map m = Maps.newConcurrentMap();
		int x = this.startPos_left_x + w - 2;
		int y = this.start_top_y + h - 2;
		m.put("x", x);
		m.put("y", y);
		return m;
	}
	
	public Point getCenterPoint() {
	//	Map m = Maps.newConcurrentMap();
		int x = this.startPos_left_x + this.radis;
		int y = this.start_top_y  + this.radis;
	 
		return new Point(x, y);
	}

	/**
	 * attilax 2016年11月8日 下午7:17:47
	 * 
	 * @param src
	 * @return
	 */
	public Matrix setImg(BufferedImage src) {
		this.img = src;
		return this;
	}

	public int calcAvgColor(int clrNums) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Point getCenterPoint() {
		int x = this.startPos_left_x + w - 2;
		int y = this.start_top_y + h - 2;
		return new Point(x, y);
	}

	public Matrix setRadis(int i) {
		 this.radis=i;
		 this.w=2*i+1;
		 this.h=this.w;
		return this;
	}
	public	Map<Color,HSV> chMap=Maps.newConcurrentMap();

	public Map getChMap() {
		return chMap;
	}

	public void setChMap(Map chMap) {
		this.chMap = chMap;
	}
    List<Point>  li_pts= Lists.newArrayList();
	public void fill_and_setMtrx_leftTop_XY_AllMode(int x, int y) {
		this.leftTop_point=new Point(x, y);
		colorLi_clrIntMod = Lists.newArrayList();
		colorLi_grbInt_noOP = Lists.newArrayList();
		li_clr = Lists.newArrayList();
		li_hsv = Lists.newArrayList();
		 li_pts= Lists.newArrayList();
		 
		this.startPos_left_x = x;
		this.start_top_y = y;
		for (int i = x; i < w + x; i++)
			for (int j = y; j < h + y; j++) {
				Point pt=new Point(i,j);
				try {
					int rgb = img.getRGB(i, j);
					Color c=new  Color(rgb);
					HSV h=ColorUtil.rgb2hsv(c);
					h.x=x;h.y=y;
					li_hsv.add(Optional.of(h));
					li_clr.add(Optional.of(c));
				
					
					//chMap.put(c, h);
				} catch (ArrayIndexOutOfBoundsException e) {
				 
					li_clr.add(Optional.empty());
					li_hsv.add(Optional.empty());
				}
				li_pts.add(pt);
				
			}
		
	}

	public List<Integer> getColorLi_grbInt_noOP() {
		return colorLi_grbInt_noOP;
	}

	public void setColorLi_grbInt_noOP(List<Integer> colorLi_grbInt_noOP) {
		this.colorLi_grbInt_noOP = colorLi_grbInt_noOP;
	}

	public List<Optional<Integer>> getColorLi_clrIntMod() {
		return colorLi_clrIntMod;
	}

	public void setColorLi_clrIntMod(List<Optional<Integer>> colorLi_clrIntMod) {
		this.colorLi_clrIntMod = colorLi_clrIntMod;
	}

	public List<Optional<Color>> getLi_clr() {
		return li_clr;
	}

	public void setLi_clr(List<Optional<Color>> li_clr) {
		this.li_clr = li_clr;
	}

	public List<Optional<HSV>> getLi_hsv() {
		return li_hsv;
	}

	public void setLi_hsv(List<Optional<HSV>> li_hsv) {
		this.li_hsv = li_hsv;
	}

	public int getRadis() {
		return radis;
	}

	/**
	 * attilax 2016年11月8日 下午7:14:16
	 * 
	 * @param object
	 * @return
	 */
	// public boolean hasAnyForgeColor(
	// com.attilax.lang.Function<Object, Object> object) {
	// // TODO Auto-generated method stub
	// return false;
	// }

	/**
	 * attilax 2016年11月8日 下午7:12:09
	 * 
	 * @param object
	 * @return
	 */

	/**
	 * attilax 2016年11月8日 下午7:07:07
	 * 
	 * @param object
	 * @return
	 */

}
