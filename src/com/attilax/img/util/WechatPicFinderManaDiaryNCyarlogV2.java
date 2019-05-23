package com.attilax.img.util;

import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Function;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.attilax.img.HSV;
import com.attilax.img.HsvRangeV2;
import com.attilax.img.imgx;
import com.attilax.io.dirx;
import com.attilax.io.filex;
import com.attilax.io.util.FileUtil_tmp4img;
import com.attilax.json.AtiJson;
import com.attilax.util.FileUtil;

public class WechatPicFinderManaDiaryNCyarlogV2 {

	public static int num = 0;

	public static void main(String[] args) {

		//28   16 88  ,  40   12  85
		HSV hi = new HSV(200, 20, 95);
		HSV low = new HSV(20, 0, 50);
		float wechatPicLmt = 1; // percent

		String strPath = "C:\\0000wechatpic";
		strPath = "D:\\ati notvery impt\\r2017 cp pic bek v5 s229";
		String targetDir = "C:\\0000wechatpic_tar_manuedDiaryNcyarlog";

		String strPath_final = strPath;

		ThreadPoolExecutor ExecutorService1_theardpool = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);

		dirx.traveV3(strPath, new Function<String, Object>() {

			@Override
			public Object apply(String cur_f) {
				num++;
				System.out.println("num:" + num);
				if (!cur_f.contains("IMG_2017"))
					return null; // jmp
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

				System.out.println("tgg");
				// only chat msg
				// HSV hi = new HSV(95, 65, 95);
				// HSV low = new HSV(85, 55, 85);

				// all wechat pic

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

				System.out.println("pct:" + pct + ",f:" + cur_f);
				System.out.println("_theardpool.getQueue().size(:" + ExecutorService1_theardpool.getQueue().size());
				// System.out.println("_theardpool:
				// "+AtiJson.toJson(ExecutorService1_theardpool));
				System.out.println("_theardpool: "
						+ com.alibaba.fastjson.JSON.toJSONString(ExecutorService1_theardpool, new PropertyPreFilter() {

					@Override
					public boolean apply(JSONSerializer arg0, Object arg1, String prop) {
						//// rt false //not show
						if (prop.equals("queue"))
							return false;
						else
							return true;
					}
				}, new SerializerFeature[] { SerializerFeature.PrettyFormat }));
				if (pct > wechatPicLmt) {
					System.out.println("pct:" + pct + ",f:" + cur_f);

					filex.move(cur_f, targetDir, strPath_final);
					// FileUtil_tmp4img.copy(cur_f, targetDir, strPath_final);

				}
				return pct;
			}
		});

	}

}

 
