package com.attilax.img.util;

import java.awt.image.BufferedImage;

import com.attilax.img.imgx;
import com.jhlabs.image.AbstractBufferedImageOp;
import com.jhlabs.image.EdgeFilter;

public class EdgeDetect  extends AbstractBufferedImageOp  {

	public static void main(String[] args) {
		String s="D:\\00dbgpic_out\\morph_close20608_171148_854.jpg";
		BufferedImage src=imgx.toImg(s);
		BufferedImage bi=	new EdgeFilter().filter(src, null);
		imgx.output_var_multi(bi, "bi", "D:\\00dbgpic_out");
		System.out.println("bitype:"+bi.getType());
		 System.out.println("--f");

	}

	@Override
	public BufferedImage filter(BufferedImage src, BufferedImage arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
