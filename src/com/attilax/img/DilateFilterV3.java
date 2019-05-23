/**
 * 
 */
package com.attilax.img;

/**
 * @author attilax
 *2016年11月8日 下午5:39:17
 */
//package com.attilax.img;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Function;

import com.attilax.ex.notFindTargetElemt;
import com.attilax.exception.ExUtil;
import com.attilax.img.other.ColorUtil;
import com.attilax.io.FileExistEx;
import com.attilax.io.filex;
import com.attilax.json.AtiJson;
import com.jhlabs.image.BinaryFilter;

public class DilateFilterV3 extends BinaryFilter {

	public static void main(String[] args) throws FileExistEx {
		String s = "C:\\00p\\a.jpg";
		// s="C:\\00capch\\p5.jpg";
		BufferedImage src = imgx.toImg(s);
		BufferedImage dest = new DilateFilterV3().filter(src, null);

		// BufferedImage dest= imgx.clone(src);
		imgx.save_png(dest, "C:\\00p\\a" + filex.getUUidName() + " dilate.jpg", false);
		System.out.println("--f");
	}

	public DilateFilterV3() {
		forgeColor = Color.WHITE;
	}

	private Color forgeColor;

	public Color getForgeColor() {
		return forgeColor;
	}

	public void setForgeColor(Color forgeColor) {
		this.forgeColor = forgeColor;
	}

	Matrix mtrx;
	Map mtrxCenterXy_inImg = null;

	/**
	 * if(x==3 && y==0) System.out.println("dbg"); attilax 2016年11月8日 下午9:31:17
	 * 
	 * @param src
	 * @param dest
	 * @return
	 */
	@Override
	public BufferedImage filter(BufferedImage src, BufferedImage dest) {
		// BufferedImage dest2 = imgx.new_BackgroudColor_White(src.getWidth(),
		// src.getHeight());
		BufferedImage dest2 = imgx.new_BackgroudColor_Tresprt(src.getWidth(), src.getHeight());
		mtrx = new Matrix().setRadis(3).setImg(src);
		ImgTraver_byMatrix im = new ImgTraver_byMatrix(mtrx);

		im.cur_Pix_Point_Evt_Handler = point -> {
			// if(ColorUtil.isSimilarWit(src,point))
			// return;
			// {"x":235,"y":272}
			if (point.x == 1 && point.y == 0)
				System.out.println("");
			// mydiar..for clor
			if (ColorUtil.hasDarkColor(src, mtrx.li_hsv, 0.7)) {
				System.out.println(point);
				Point CenterPoint = mtrx.getCenterPoint();
				HSV forgeColor2 = null;
				try {
					forgeColor2 = ColorUtil.darkestColor(mtrx.li_hsv, 0.7);
				} catch (notFindTargetElemt e) {
					System.out.println(AtiJson.toJson(point));
					ExUtil.throwExV2(e);
				}
				Color cl = ColorUtil.HSVtoRGBColorV2(forgeColor2);
				System.out.println("--center pt:" + CenterPoint + " color:" + cl);
				dest2.setRGB(CenterPoint.x, CenterPoint.y, cl.getRGB());
			}

		};
		im.trave(src);
		// imgx.trave(src, (x, y) -> {
		// System.out.println("" + x + ":" + y);
		//
		// mtrx.fill_and_setMtrx_leftTop_XY(x, y);
		//
		// boolean mtrx_hasAnyForgeColor = mtrx.hasAnyForgeColor(mtrx_item_color
		// -> {
		// // dark,,so is forge color.. bkgrd lit..
		// return (imgx.isDarkColor(imgx.gray(mtrx_item_color)));
		//
		// });
		// if (mtrx_hasAnyForgeColor) {
		// int forgeColor2 = mtrx.getForgeColor();
		// mtrxCenterXy_inImg = mtrx.getCenterXy();
		// try {
		// dest2.setRGB((int) mtrxCenterXy_inImg.get("x"), (int)
		// mtrxCenterXy_inImg.get("y"), forgeColor2);
		// } catch (ArrayIndexOutOfBoundsException e) {
		// System.out.println("ArrayIndexOutOfBoundsException  x:" + x + ",y:" +
		// y);
		// }
		//
		// }
		// });

		return dest2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jhlabs.image.WholeImageFilter#filterPixels(int, int, int[],
	 * java.awt.Rectangle)
	 */
	@Override
	protected int[] filterPixels(int arg0, int arg1, int[] arg2, Rectangle arg3) {
		// TODO Auto-generated method stub
		return null;
	}

}
