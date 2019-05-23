package com.attilax.img.util;

import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

import com.attilax.img.HSV;
import com.attilax.img.HsvRangeV2;
import com.attilax.img.imgx;
import com.attilax.io.dirx;
import com.attilax.io.filex;
import com.attilax.io.util.FileUtil_tmp4img;
import com.attilax.json.AtiJson;
import com.attilax.util.FileUtil;

public class WechatPicFinder {

	public static int num = 0;

	public static void main(String[] args) {
		float wechatPicLmt = 3; // percent
		String targetDir = "C:\\0000wechatpic_tar";
		;
		String strPath = "C:\\0000wechatpic";
		strPath = "D:\\ati notvery impt\\r2017 cp pic bek v4 s229";
		String strPath_final = strPath;

		ExecutorService ExecutorService1_theardpool = Executors.newFixedThreadPool(4);
		dirx.traveV3(strPath, new Function<String, Object>() {

			@Override
			public Object apply(String cur_f) {
				ExecutorService1_theardpool.submit(new Runnable() {

					@Override
					public void run() {
						createTask(wechatPicLmt, targetDir, strPath_final, cur_f);

					}
				}); // end sumbit
				return null;
				// end apply

			}

			private Object createTask(float wechatPicLmt, String targetDir, String strPath_final, String cur_f) {
				num++;
				System.out.println("tgg");
				// only chat msg
				// HSV hi = new HSV(95, 65, 95);
				// HSV low = new HSV(85, 55, 85);

				// all wechat pic
				HSV hi = new HSV(125, 92, 95);
				HSV low = new HSV(85, 55, 63);
				HsvRangeV2 ran = new HsvRangeV2(low, hi);

				// String f = "C:\\0000wechatpic\\a.png";

				BufferedImage image = imgx.toImg(cur_f);
				int heit = image.getHeight();
				int gray = 0;
				int hitCount = 0;
				for (int curheit = 0; curheit < heit; curheit++) {
					for (int curwid = 0; curwid < image.getWidth(); curwid++) {
						if (curwid == 609 && curheit == 699)
							System.out.println("dbg");
						int clr_int = image.getRGB(curwid, curheit);
						HSV hsv_cur = HSV.getHsvByColorint(clr_int);
						if (curwid == 609 && curheit == 699)
							System.out.println(AtiJson.toJson(hsv_cur));
						if (ran.contain(hsv_cur))
							hitCount++;
					}
				}
				float pct = (float) hitCount / (heit * image.getWidth()) * 100;
				System.out.println("num:" + num);
				System.out.println("pct:" + pct + ",f:" + cur_f);
				if (pct > wechatPicLmt) {
					System.out.println("pct:" + pct + ",f:" + cur_f);
					filex.move(cur_f, targetDir, strPath_final);
					// FileUtil_tmp4img.copy(cur_f, targetDir, strPath);
				}
				return pct;
			}
		});

	}

}
