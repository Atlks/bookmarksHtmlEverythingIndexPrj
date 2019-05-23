package com.attilax.img.skindetect;

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
import com.attilax.csv.CSVUtils;
import com.attilax.img.imgx;
import com.attilax.io.dirx;
import com.attilax.io.filex;
import com.attilax.json.AtiJson;
import com.attilax.util.CsvUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class moveBySkinLow {
	public static int n = 0;

	public static void main(String[] args) throws IOException {
		Color pixelColor = new Color(-1);// -1=255,255,255
		int r = pixelColor.getRed();
		int g = pixelColor.getGreen();
		int b = pixelColor.getBlue();
		String d = "C:\\ati\\foto ppl"; // C:\ati\foto ppl\p2015.12  \\p2015.12
		String s="c:\\blkpst_asc_for_q2016.txt";
		skinPst_ordrebyAsc_Txt(d,s);
//		BufferedImage src2 = imgx.toImg("C:\\0000t\\skin3.jpg");
//		float blackPercent = imgx.getBlackPercent(src2);
//		System.out.println(blackPercent);
 
		
		List<Map> li=new CSVUtils().read(s, "pst,f");
		for(int i=0;i<2000;i++)
		{
			String f=li.get(i).get("f").toString();
			System.out.println(f);
			System.out.println(i);
			if(new File(f).exists())
		 	filex.move(f, "c:\\000skinLow_q2016",d);
			
		}
		System.out.println("--f");

	}

	public static void skinPst_ordrebyAsc_Txt(String d,String skinPst_OrderbyAsc_File) throws IOException {
		ExecutorService urlPool = Executors.newFixedThreadPool(3);
		final List<Map> li = Lists.newArrayList();
		Function<String, Object> closure = (String f) -> {

			Runnable command = () -> {
				Map m = Maps.newHashMap();
				m.put("f", f);
				BufferedImage src = imgx.toImg(f);
				SkinFilter1 SkinFilter1a = new SkinFilter1();
				BufferedImage dest = SkinFilter1a.filter(src, null);
				float blackPoints = imgx.getBlackPercent(dest);
				m.put("blkpst", blackPoints);
				li.add(m);
				n++;
				System.out.println(n);
			};

			urlPool.execute(command);
			return f;

		};
		//

		// tOrder(li);
		// li.sort((Map m)->{
		//
		// });
		dirx.traveV3(d, closure);

		urlPool.shutdown();
		try {
			urlPool.awaitTermination(20, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	urlPool.sh
		new Linq(li).orderby("blkpst", "asc");
	//	String skinPst_OrderbyAsc_File = "c:\\blkpst_asc.txt";
		filex fx= new filex(skinPst_OrderbyAsc_File);
		for (Map map : li) {
			String line=map.get("blkpst").toString()+","+map.get("f").toString();
			fx.appendLine_flush_safe(line);
			
			
		}
		fx.closeSF();
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
