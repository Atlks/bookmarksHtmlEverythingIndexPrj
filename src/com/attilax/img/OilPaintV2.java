package com.attilax.img;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

import com.attilax.img.other.ColorUtil;
import com.attilax.io.FileExistEx;
import com.attilax.io.filex;
import com.attilax.json.AtiJson;
import com.google.common.collect.Lists;

public class OilPaintV2 {

	public static void main(String[] args) throws FileExistEx {

		String s = "C:\\000oilpaint\\a.jpg";
		s = "C:\\000oilpaint\\c.jpg";
		// s = "C:\\000oilpaint\\b.jpg";
		// s = "C:\\000oilpaint\\a.png";
		s = "C:\\000oil\\a.png";
		BufferedImage src = imgx.toImg(s);
		// BufferedImage dest2 = imgx.new_BackgroudColor_White(src.getWidth(),
		// src.getHeight());
		int grayLevDeep = 5;
		int radis = 2;
	//	procss(s, src, grayLevDeep, radis);
		procss(s, src, 3, 1);
		procss(s, src, 5, 1);
		procss(s, src, 9, 1);
//		procss(s, src, 5, 1);
		procss(s, src, 5, 2);
		procss(s, src, 5, 3);
		
		System.out.println("--f");
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
	}

	private static void procss(String s, BufferedImage src, int grayLevDeep, int radis) throws FileExistEx {
		new OilPaintV2().process(src, grayLevDeep, radis);
		src = imgx.Remove_alpha_channel(src);
		String addSuffix = filex.addSuffix(s, "_levDeep"+grayLevDeep+"_rds"+radis+"_" + filex.getUUidName() + "");
		imgx.save(src, addSuffix);
	}

	int nowX;
	// ColorBucketCater bukerUtil_dbg;
	GrayLayerCater GrayLayerCater_rgbmod_dbg;
	Matrix Matrix1;
	
	/**
	 * 	if (cur_point.x == nowX) {
				nowX++;
				System.out.println(AtiJson.toJson(cur_point));
			}

			if (cur_point.x == 228 && cur_point.y == 1273) {
				System.out.println("dbg");
			}
					if (MaxPixsLayer1.hsvs.size() == 0)
				System.out.println("dbg");
	  @author attilax
	  @since qb9
	 * @param img
	 * 
	 *  * 	Point mtrxCenter_Point = Matrix1.getCenterPoint();
	 *  
	 *  
	 * @param grayLevDeep 灰度层级
	 * @param radis  扫描模板半径
	 * 	GrayLayerCater_rgbmod_dbg = GrayLayerCater1;
	 * @throws 使用了公共模块，图片矩阵模板扫描器ImgTraver_byMatrix，以及灰度层级分层器GrayLayerCater
	 * 这样让我们就集中精力解决算法问题了。
	
	 */
	
	private void process(BufferedImage img, int grayLevDeep, int radis)   {

		//初始化扫描矩阵模板
		Matrix1 = new Matrix(img).setRadis(radis);
		//初始化灰度层次分类器  分组灰度层次，为255/grayLevDeep层
		GrayLayerCater GrayLayerCater1 = new GrayLayerCater().groupByGrayRang(grayLevDeep);	
      //初始化图片扫描器   按照选框矩阵来扫描
		ImgTraver_byMatrix ImgTraver_byMatrix1 = new ImgTraver_byMatrix(Matrix1);
		//设置扫描到每个像素的事件处理
		ImgTraver_byMatrix1.cur_Pix_Point_Evt_Handler = cur_point-> {
			//对模板选框内内的像素颜色对灰度分组聚合分类，分别放在不同的灰度层次里面
			GrayLayerCater1.insertTo_Layers_select_Colors_From(Matrix1).Groupby_GrayRang();
			//选择最多颜色点的那个层次 
			Layer ColorsNumsTop1Layer = GrayLayerCater1.get_top1_from_Layers_Orderby_ColorsNum_desc();
			//计算颜色均值，分别对各个rgb分量计算，与合成
			Color avgColor = ColorsNumsTop1Layer.avgColor_retClr();		
			img.setRGB(cur_point.x, cur_point.y, avgColor.getRGB());			 
			
		};
		ImgTraver_byMatrix1.trave(img); //开始扫描
	}
//try {
//} catch (ArrayIndexOutOfBoundsException e) {
//	System.out.println(e.getMessage() + "  point:" + cur_point);
//}
//}
	private void travettt() {
		List<Optional<HSV>> li_tr = Matrix1.li_hsv;

		int x1 = 0;
		int y1 = 0;
		BufferedImage newBi = null;
		for (int n = 0; n < li_tr.size(); n++) {
			if ((n + 1) % 3 == 0) {
				x1++;
				y1++;
			}
			newBi = new BufferedImage(3, 3, 1);
			Optional<HSV> hs = li_tr.get(n);
			int x1x = x1;
			int y1y = y1;
			BufferedImage tmp = newBi;
			hs.ifPresent(p -> {
				Color c = ColorUtil.HSVtoRGBColorV2(p);
				tmp.setRGB(x1x, y1y, c.getRGB());
			});
			if (!hs.isPresent())

				newBi.setRGB(x1, y1, new Color(128, 128, 128).getRGB());
		}
		try {
			imgx.save(newBi, "C:\\000oilpaint\\" + filex.getUUidName() + ".jpg");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private HSV aa(BufferedImage src, Point cur_point) {
		HSV avgColor;
		int ori = src.getRGB(cur_point.x, cur_point.y);
		avgColor = ColorUtil.rgb2hsv(ori);

		// if(avgColor.s<0.5f)
		if (avgColor.v < 0.85f) {
			avgColor.s = avgColor.s + 0.3f;
			if (avgColor.s > 1)
				avgColor.s = 1f;
			// if(avgColor.v<0.5f )
			avgColor.v = avgColor.v + 0.3f;
			if (avgColor.v > 1)
				avgColor.v = 1f;
		}
		return avgColor;
	}

}
