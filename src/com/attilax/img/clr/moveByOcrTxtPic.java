package com.attilax.img.clr;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import com.attilax.Stream.Linq;
import com.attilax.cn.CncharX;
import com.attilax.csv.CSVUtils;
import com.attilax.img.imgx;
import com.attilax.io.dirx;
import com.attilax.io.filex;
import com.attilax.json.AtiJson;
import com.attilax.ocr.OcrX;
import com.attilax.util.CsvUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class moveByOcrTxtPic {
	public static int n = 0;

	public static void main(String[] args) throws IOException {

	 
		String dir="D:\\ati\\dcim qb0";
		String movDestDir = "d:\\ati\\garb 0dcim qb0_TomanyTxt 100txt";
		String orcMaindir = "C:\\0workspace\\Tesseract";
		main0(dir,100,movDestDir,orcMaindir);
		System.out.println("--f");

	}

	private static void main0(String dir,int cnCountsZaza,	String targetDir,String orcMaindir) throws IOException {
		ExecutorService urlPool = Executors.newFixedThreadPool(3);
		final List<Map> li = Lists.newArrayList();

		Function<String, Object> closure = (String jpg) -> {
			String ext=filex.getExtName(jpg);
			if(ext.trim().toLowerCase().equals("txt"))
				return jpg;

			Runnable poolItem = () -> {
				if(filex.existSameFileButExtIs("txt",jpg))
					return;
				
				String t = new OcrX().getTxt(orcMaindir, jpg);
				int cnCount=	CncharX.cncharCount(t);
			
			//	int cnCountsZaza = 200;
				if(cnCount>cnCountsZaza)
					filex.move(jpg, targetDir, dir);
				n++;
				System.out.println(n+",cncount:"+cnCount+",jpg:"+jpg);
			};

			urlPool.execute(poolItem);
			return jpg;

		};
		//

		// tOrder(li);
		// li.sort((Map m)->{
		//
		// });
		dirx.traveV3(dir, closure);

		urlPool.shutdown();
		try {
			urlPool.awaitTermination(20, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		// urlPool.sh
//		new Linq(li).orderby("blkpst", "asc");
//		filex fx = new filex("c:\\blkpst_asc.txt");
//		for (Map map : li) {
//			String line = map.get("blkpst").toString() + ","
//					+ map.get("f").toString();
//			fx.appendLine_flush_safe(line);
//
//		}
//		fx.closeSF();
	}

	private static void tOrder(final List<Map> li) {
		Map m1 = Maps.newHashMap();
		m1.put("blkpst", 0.1f);
		Map m2 = Maps.newHashMap();
		m2.put("blkpst", 0.8f);
		Map m3 = Maps.newHashMap();
		m3.put("blkpst", 0.3f);
		li.add(m1);
		li.add(m2);
		li.add(m3);
		new Linq(li).orderby("blkpst", "");
		System.out.println(AtiJson.toJson(li));
	}

}
