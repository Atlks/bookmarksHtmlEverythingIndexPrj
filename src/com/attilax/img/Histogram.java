package com.attilax.img;

import java.awt.image.BufferedImage;
import java.util.List;

import com.attilax.json.AtiJson;
import com.google.common.collect.Lists;

import System.Drawing.Bitmap;
import System.Drawing.Color;
import System.Drawing.Graphics;
import System.Drawing.Pens;

public class Histogram {

	private int lineHitMax;
	private int lineHitMin;
	private int linePixMin;
	private int colPixMin;
	private int charWidMin;

	public static void main(String[] args) {
		// new com.jhlabs.image.Histogram().

		// BufferedImage bi = new BufferedImage(600, 1000,
		// BufferedImage.TYPE_INT_RGB);
		String jpg = "C:\\00ocr\\ajpg.jpg";
		// List<Rang> li = testLineHIsto(jpg);
		// List<Rang> li = testLineHIsto(jpg);

		
		String lineCharsPic="C:\\00ocr\\ajpg_rang_1.jpg";
		Histogram histogram = new Histogram();
	 	histogram.geneColsHistoData(lineCharsPic);	
		BufferedImage bi=imgx.toImg(lineCharsPic);
		Bitmap tzt = 	histogram.geneCharPrjktPic(bi.getWidth(),bi.getHeight()*40,40);
	
		
		 
	//	 BufferedImage bi_cur=	imgx.addVertLine_repeat(lineCharsPic,42,new java.awt.Color(255,0,0));
		// imgx.save_overwrite(bi_cur, "C:\\00ocr\\ajpg_rang_1_addReadLine.jpg");
	//	 imgx.addVertLine_rpt(tzt.bi,42,new java.awt.Color(255,0,0));
		 tzt.toFile_overwrite("C:\\00ocr\\ajpg_rang_1_charPrjkt.jpg");
		//System.out.println(AtiJson.toJson(li));
		// Bitmap tzt = histogram.lineHistogram(tmp);
		// tzt.toFile("C:\\00ocr\\ajpg_hist.jpg");

		// = new Bitmap(600, 1000);
		// tmp.type=BufferedImage.TYPE_INT_RGB;

	}



	private void geneColsHistoData(String jpg) {
		Bitmap tmp = imgx.toBitmap(jpg);
		geneColsHistoData(tmp);
	}

	private static List<Rang> testLineHIsto(String jpg) {
		Bitmap tmp = imgx.toBitmap(jpg);
		Histogram histogram = new Histogram();
		histogram.geneLineHisto(tmp);
		histogram.lineHitMax = 20;
		histogram.lineHitMin = 8;
		histogram.linePixMin = 8;
		List<Rang> li = histogram.calcRang();
		int n = 0;
		for (Rang rang : li) {
			n++;
			BufferedImage bi = new imgx().cutImage_retImg(jpg, 0,
					rang.start - 1, tmp.Width, rang.end - rang.start + 1);
			imgx.save_overwrite(bi, "C:\\00ocr\\ajpg_rang_" + n + ".jpg");
		}
		return li;
	}

	public List<Rang> calcRang4ColHisto() {
		List<Rang> li = Lists.newArrayList();
		Rang rg = new Rang();
		String stat = "ini";
		int colWidsCalcTmp = 0;
		for (int curColIdx = 0; curColIdx < histo.length; curColIdx++) {
			int ColPixCount = histo[curColIdx];

			// rang start
			if (ColPixCount > 0
					&& (stat.equals("ini") || stat.equals("rangEnd"))) {
				rg = new Rang();
				rg.start = curColIdx;
				stat = "rangStart";
				colWidsCalcTmp = 0;
				colWidsCalcTmp++;
				continue;
			}
			// normal
			colWidsCalcTmp++;
			// end rang //is black
			if (ColPixCount < 1 && stat.equals("rangStart")) {
				stat = "rangEnd";
				// if (colWidsCalcTmp >= charWidMin) {
				rg.end = curColIdx;
				li.add(rg);
				colWidsCalcTmp = 0;
				continue;
				// } else { //ignor too min line ... mast line hit grath then
				// linehitmin var...
				// rg = new Rang();
				// colWidsCalcTmp = 0;
				// continue;
				// }
			}

		}
		return li;

	}

	private List<Rang> calcRang() {
		List<Rang> li = Lists.newArrayList();
		Rang rg = new Rang();
		String stat = "ini";
		int lineHeightCalcTmp = 0;
		for (int curLine = 0; curLine < histo.length; curLine++) {
			int curLinePixs = histo[curLine];

			// rang start
			if (curLinePixs > linePixMin
					&& (stat.equals("ini") || stat.equals("rangEnd"))) {
				rg = new Rang();
				rg.start = curLine;
				stat = "rangStart";
				lineHeightCalcTmp = 0;
				lineHeightCalcTmp++;
				continue;
			}
			// normal
			lineHeightCalcTmp++;
			// end rang
			if (curLinePixs < linePixMin && stat.equals("rangStart")) {
				stat = "rangEnd";
				if (lineHeightCalcTmp >= lineHitMin) {
					rg.end = curLine;
					li.add(rg);
					lineHeightCalcTmp = 0;
					continue;
				} else { // ignor too min line ... mast line hit grath then
							// linehitmin var...
					rg = new Rang();
					lineHeightCalcTmp = 0;
					continue;
				}
			}

		}
		return li;

	}

	public int[] histo;

	public Bitmap lineHistogram(Bitmap bmp) {

		geneLineHisto(bmp);

		int width = bmp.Width;
		int height = bmp.Height;

		// draw
		// int max = getMax(histo);

		Bitmap tmp = geneLineHistoPic(width, height);

		return tmp;
	}

	public Bitmap geneLineHistoPic(int width, int height) {
		Bitmap tmp = new Bitmap(width, height);
		// using ()
		Graphics g = Graphics.FromImage(tmp);
		int lastWidth = width;
		// int curLine=height;
		for (int curLine = 0; curLine < height; curLine++) {
			int curline_pixs = histo[curLine];
			if (curline_pixs > 0)

				System.out.println("curLine:" + curLine + ",curline_pixs:"
						+ curline_pixs);
			int startX = width - curline_pixs;
			g.DrawLine(Pens.Black, startX, curLine, lastWidth, curLine);
		}
		return tmp;
	}
	private Bitmap geneCharPrjktPic(int width, int height, int pct) {
		Bitmap tmp = new Bitmap(width, height);
	 
		Graphics g = Graphics.FromImage(tmp);
	 
		int lastWidth = width;
	 
		for (int curColIdx = 0; curColIdx < width; curColIdx++) {
			int curCol_pixs = histo[curColIdx];
			curCol_pixs=curCol_pixs*pct;
			if (curCol_pixs > 0)

				System.out.println("curLine:" + curColIdx + ",curline_pixs:"
						+ curCol_pixs);
		//	int startX = width - curCol_pixs; 
			g.DrawLine(Pens.Black,curColIdx , height-curCol_pixs , curColIdx, height);
		}
		return tmp;
	}
	public Bitmap geneCharPrjktPic(int width, int height) {
		Bitmap tmp = new Bitmap(width, height);
		// using ()
		Graphics g = Graphics.FromImage(tmp);
		//int width=tmp.Width;
		int lastWidth = width;
	//	int height=tmp.Height;
		// int curLine=height;
		for (int curColIdx = 0; curColIdx < width; curColIdx++) {
			int curCol_pixs = histo[curColIdx];
			if (curCol_pixs > 0)

				System.out.println("curLine:" + curColIdx + ",curline_pixs:"
						+ curCol_pixs);
		//	int startX = width - curCol_pixs; 
			g.DrawLine(Pens.Black,curColIdx , height-curCol_pixs , curColIdx, height);
		}
		return tmp;
	}

	public void geneColsHistoData(Bitmap bmp) {
		// stats
		if (histo != null)
			histo = null;

		int width = bmp.Width;
		int height = bmp.Height;

		histo = new int[width];

		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				Color color = bmp.GetPixel(x, y);

				if (color.R < 128 && color.G <128 && color.B < 128)
					// if (color.A > 200)
					histo[x]++;
			}
	}

	private void geneLineHisto(Bitmap bmp) {
		// stats
		if (histo != null)
			histo = null;

		int width = bmp.Width;
		int height = bmp.Height;

		histo = new int[height];

		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				Color color = bmp.GetPixel(x, y);

				if (color.R < 50 && color.G < 50 && color.B < 50)
					// if (color.A > 200)
					histo[y]++;
			}
	}

}
