package com.attilax.img;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import m.global;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Coordinate;

import com.attilax.cn.CncharX;
import com.attilax.exception.ExUtil;
import com.attilax.img.other.AFont;
import com.attilax.img.other.ColorUtil;
import com.attilax.io.FileExistEx;
import com.attilax.io.filex;
import com.attilax.lang.Global;
import com.attilax.ocr.OcrX;
import com.attilax.util.pathx;
import com.google.common.collect.Lists;
import com.jhlabs.image.HSBAdjustFilter;

public class SvsBtnFilter {
	ExecutorService urlPool = Executors.newFixedThreadPool(4);

	public static void main(String[] args) throws FileNotFoundException {

		long startTime = System.currentTimeMillis(); // 获取开始时间
		String out = "C:\\0workspace\\AtiPlatf_cms\\WebRoot\\btn\\" + filex.getUUidName() + ".png";
		double fontScale = 0.6;
		new SvsBtnFilter().setBg("bush_blu", 300, 60, "210,0.1,0.1").setFont("cywe_img", fontScale, 50, 5).setFont("din_img", fontScale, 90, 5).toOutStream(new FileOutputStream(new File(out)));
		System.out.println("--f");
		long endTime = System.currentTimeMillis(); // 获取结束时间

		System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
		System.out.println("--0f");

	}

	public void toRespOutStream() throws IOException {
		// response
		HttpServletResponse resp = Global.resp.get();
		toOutStream(resp.getOutputStream());
	}

	private void toOutStream(OutputStream fileOutputStream) {
		// Runnable poolItem = () -> {
		// String t = new OcrX().getTxt("C:\\0workspace\\Tesseract", jpg);
		// int cnCount= CncharX.cncharCount(t);
		// String targetDir="c:\\000dcim_mov22_TomanyTxt";
		// if(cnCount>200)
		// filex.move(jpg, targetDir, dir);
		// n++;
		// System.out.println(n+",cncount:"+cnCount+",jpg:"+jpg);
		// };
		//
		// urlPool.execute(poolItem);
		// urlPool.su
		BufferedImage bg = imgx.toImg(this.bg);
		AtiHSBAdjustFilter hsbAdjustFilter = new AtiHSBAdjustFilter();
		// bg=HSBAdjustFilter
		hsbAdjustFilter.setHFactor(hsv.h);
		hsbAdjustFilter.setSFactor(hsv.s);
		hsbAdjustFilter.setBFactor(hsv.v);
		bg = hsbAdjustFilter.filter(bg, null);
		// bg.setRGB(0,0, new Color(255,255,255).getRGB());
		// bg= imgx.Remove_alpha_channel(bg);
		String out = "C:\\0workspace\\AtiPlatf_cms\\WebRoot\\btn\\tmp_bg" + filex.getUUidName() + ".png";
		try {
			// imgx.save(bg, out);
			imgx.save_png(bg, out, true);
		} catch (FileExistEx e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Builder<BufferedImage>  bldr = Thumbnails.of(bg);
				//of(new File(this.bg));
		for (AFont aFont : li) {
			Runnable c = () -> {
				BufferedImage img1 = imgx.toImg(aFont.fontPic);
				try {
					img1 = Thumbnails.of(img1).scale(aFont.fontScale).asBufferedImage();
					Coordinate coordinate1 = new Coordinate(aFont.point.x, aFont.point.y);
					bldr.watermark(coordinate1, img1, 1f);
				} catch (IOException e) {
					System.out.println("----wan::" + e.getMessage() + aFont);
					e.printStackTrace();
				}
			};
			c.run();
			// urlPool.execute(c);

		}

		urlPool.shutdown();
		try {
			urlPool.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			ExUtil.throwExV2(e);
		}

		try {
			bldr.size(300, 60).keepAspectRatio(true).outputFormat("png").toOutputStream(fileOutputStream);
		} catch (IOException e1) {
			ExUtil.throwExV2(e1);
		}

		try {
			fileOutputStream.flush();
			// fileOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String bg;
	List<AFont> li = Lists.newArrayList();
	private int bgW;
	private int bgH;
	private HSV hsv;

	private SvsBtnFilter setFont(String fontPic, double fontScale, int i, int j) {

		fontPic = com.attilax.io.pathx.webAppPath() + "/btn/font/" + fontPic + ".png";
		AFont aft1 = new AFont();
		aft1.point = new Point(i, j);
		aft1.fontPic = fontPic;
		aft1.fontScale = fontScale;
		li.add(aft1);
		return this;
	}

	// String ori = "C:\\000oil\\bush_blu.png";
	private SvsBtnFilter setBg(String bg, int i, int j, String hsv_s) {
		this.hsv = ColorUtil.newHSV(hsv_s.split(","));
		bg = com.attilax.io.pathx.webAppPath() + "/btn/" + bg + ".png";
		this.bg = bg;
		this.bgW = i;
		this.bgH = j;
		return this;

	}
	
	private SvsBtnFilter setBg(String bg, int i, int j ) {
	//	this.hsv = ColorUtil.newHSV(hsv_s.split(","));
		bg = com.attilax.io.pathx.webAppPath() + "/btn/" + bg + ".png";
		this.bg = bg;
		this.bgW = i;
		this.bgH = j;
		return this;

	}

}
