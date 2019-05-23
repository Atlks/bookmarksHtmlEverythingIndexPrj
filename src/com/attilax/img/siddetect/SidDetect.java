package com.attilax.img.siddetect;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;

import org.opencv.core.Mat;

import com.attilax.img.HSV;
import com.attilax.img.HsvRangeV2;
import com.attilax.img.imgx;
import com.attilax.img.util.OpencvUtil;

public class SidDetect {

	public static void main(String[] args) {
		String f = "D:\\00sidtmp\\01-36-35-20140319_23b44db756b98dc5afe89p099Aaed0v0.jpg";
	  f="01-36-32-001UuJ0pgy6KR3X3Tnnfd&690";
	  f="D:\\00sidtmp\\"+f+".jpg";
	  
		BufferedImage src = imgx.toImg(f);
		System.out.println( "src type:"+src.getType());//def is     public static final int TYPE_3BYTE_BGR = 5;

		HSV low = new HSV().setHsv100mode(170, 0, 25);// 190 6 64.. 197 14 66
		// shadow 217 9 35

		HSV hi = new HSV().setHsv100mode(255, 15, 80);
		HsvRangeV2 hr = new HsvRangeV2(low, hi);
		List<Point> li = imgx.findColorPoints(src, hr);
		BufferedImage dest = imgx.new_BackgroudColor_blackv2(src.getWidth(), src.getHeight());

		BufferedImage dest_findColored = imgx.setMaskWhite(dest, li);
		imgx.output_var_multi(dest_findColored, "dest_findColored", "d:\\00dbgpic_out");

		// f= "D:\\00sidtmp\\"+f+".jpg";

		BufferedImage bined = new com.jhlabs.image.ThresholdFilter(85).filter(src, null);
		System.out.println( "bined type:"+bined.getType());   //  bined type:0
		//   public static final int TYPE_CUSTOM = 0;
		System.out.println( BufferedImage.TYPE_3BYTE_BGR);
		//     public static final int TYPE_3BYTE_BGR = 5;
		System.out.println( BufferedImage.TYPE_BYTE_GRAY);
		System.out.println( BufferedImage.TYPE_INT_BGR);
		System.out.println( BufferedImage.TYPE_USHORT_GRAY);
		System.out.println( " BufferedImage.TYPE_BYTE_BINARY :" +BufferedImage.TYPE_BYTE_BINARY  );
		//  BufferedImage.TYPE_BYTE_BINARY :12
		imgx.output_var_multi(bined, "bined", "d:\\00dbgpic_out");
		OpencvUtil.ini();
		Mat opencv_img = OpencvUtil.morph_close(bined, 20);
		BufferedImage morph_close = OpencvUtil.toImg(opencv_img);
		
		imgx.output_var_multi(morph_close, "morph_close", "d:\\00dbgpic_out");
		BufferedImage morph_close2 = OpencvUtil.morph_close_retImg(morph_close, 15);
		imgx.output_var_multi(morph_close2, "morph_close2__", "d:\\00dbgpic_out");
		
		BufferedImage dest_findColored_morph_close = OpencvUtil.morph_close_retImg(dest_findColored, 15);
		imgx.output_var_multi(dest_findColored_morph_close, "dest_findColored_morph_close", "d:\\00dbgpic_out");
		System.out.println("|--f");

	}

}
