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
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Function;

import com.attilax.io.FileExistEx;
import com.attilax.io.filex;
import com.jhlabs.image.BinaryFilter;

public class DilateFilterV2 extends BinaryFilter {

	public static void main(String[] args) throws FileExistEx {
		String s = "C:\\00capch\\1108_195608_830.jpg";
		// s="C:\\00capch\\p5.jpg";
		BufferedImage src = imgx.toImg(s);
		BufferedImage dest = new DilateFilterV2().filter(src, null);

		// BufferedImage dest= imgx.clone(src);
		imgx.save(dest, "C:\\00capch\\" + filex.getUUidName() + " dilate.jpg");
		System.out.println("--f");
	}

	public DilateFilterV2() {
		forgeColor = Color.WHITE;
	}

	private Color forgeColor;

	public Color getForgeColor() {
		return forgeColor;
	}

	public void setForgeColor(Color forgeColor) {
		this.forgeColor = forgeColor;
	}

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
		BufferedImage dest2 = imgx.new_BackgroudColor_White(src.getWidth(), src.getHeight());
		Matrix mtrx = new Matrix(3, 3).setImg(src);
		imgx.trave(src, (x, y) -> {
			System.out.println("" + x + ":" + y);

			mtrx.fill_and_setMtrx_leftTop_XY(x, y);

			boolean mtrx_hasAnyForgeColor = mtrx.hasAnyForgeColor(mtrx_item_color -> {
				// dark,,so is forge color.. bkgrd lit..
					return (imgx.isDarkColor(imgx.gray(mtrx_item_color)));

				});
			if (mtrx_hasAnyForgeColor) {
				int forgeColor2 = mtrx.getForgeColor();
				mtrxCenterXy_inImg = mtrx.getCenterXy();
				try {
					dest2.setRGB((int) mtrxCenterXy_inImg.get("x"), (int) mtrxCenterXy_inImg.get("y"), forgeColor2);
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("ArrayIndexOutOfBoundsException  x:" + x + ",y:" + y);
				}

			}
		});

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
