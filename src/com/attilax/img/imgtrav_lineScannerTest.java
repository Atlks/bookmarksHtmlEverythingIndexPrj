/**
 * 
 */
package com.attilax.img;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.attilax.img.other.ColorUtil;
import com.attilax.io.FileExistEx;
import com.attilax.io.filex;
import com.attilax.img.other.CurPixArrivdBoderEx;

/**
 * @author attilax 2016年11月16日 下午3:36:27
 */
public class imgtrav_lineScannerTest {

	/**
	 * attilax 2016年11月16日 下午3:36:27
	 * 
	 * @param args
	 * @throws FileExistEx
	 */
	public static void main(String[] args) throws FileExistEx {
		String s = "C:\\00p\\a1115_210836_162 dilate.jpg.png";
		String ext = filex.getExtName(s);
		// s="C:\\00capch\\p5.jpg";
		BufferedImage src = imgx.toImg(s);

		ImgTraver_lineScaner trvr = new ImgTraver_lineScaner().setSrc(src);
		trvr.cur_Pix_Point_process_Fun_Handler = pnt -> {
			int rgb = src.getRGB(pnt.x, pnt.y);
			HSV hsv = ColorUtil.rgb2hsv(rgb);
			// System.out.println(pnt+ "hsv:"+hsv);
			if (hsv.v < 0.6) // dark clolor
				throw new CurPixArrivdBoderEx();
		};
		Rectangle rect = trvr.trav();
		BufferedImage des = new imgx().setImg(src).setRect(rect).cutImg(src, rect, ext);
		imgx.save_png(des, "C:\\00p\\a" + filex.getUUidName() + " dilate.jpg", true);
		System.out.println(rect);
		// new Rectangle(x, y, width, height)
		// BufferedImage dest = null ;//= new DilateFilterV3().filter(src,
		// null);
		//
		// // BufferedImage dest= imgx.clone(src);
		// try {
		// imgx.save_png(dest, "C:\\00p\\a" + filex.getUUidName() +
		// " dilate.jpg",false);
		// } catch (FileExistEx e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		System.out.println("--f");

	}

}
