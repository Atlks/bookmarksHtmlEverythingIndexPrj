package com.attilax.img.util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;

import com.attilax.img.imgx;
import com.attilax.img.other.CantFindMatch;
import com.attilax.io.filex;

public class SimilarCalctor {
	public static void main(String[] args) throws CantFindMatch, IOException {
		String screen = "D:\\000game\\00893.jpg";
		String anmdir = "D:\\000game\\anm";
		OpencvUtil.ini();

		File[] files = new File(anmdir).listFiles();
		for (File file : files) {
			String resultRect4dbgFile = "D:\\000game";
			String bigimg = screen;
			String tmplPart = file.getAbsolutePath();
			String  mainname=filex.getFileName_mainname_noExtName_nopath(tmplPart);
			resultRect4dbgFile=resultRect4dbgFile+"\\"+mainname+"match cut part.jpg";
			BufferedImage part_bi = imgx.toImg(tmplPart);
			Point pt = OpencvUtil.matchTemplate(bigimg, tmplPart, resultRect4dbgFile, Imgproc.TM_SQDIFF);
			Rectangle rect = new Rectangle(new Double(pt.x).intValue(), new Double(pt.y).intValue(), part_bi.getWidth(),
					part_bi.getHeight());

			BufferedImage rect_part = new imgx().cutImage_retImg(screen, rect);
			float sml = new SimilarComparer().compare(rect_part, part_bi);
			String string = "---file:" + bigimg + ", anm:@anm@,rect_part and partImg comparePct:" + sml;
			string=string.replace("@anm@", mainname);
			System.out.println(string);
		}

	}

}
