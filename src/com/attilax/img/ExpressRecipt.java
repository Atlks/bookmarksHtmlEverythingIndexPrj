package com.attilax.img;

import java.io.IOException;


/**
 * 
 * @author Administrator
 *com.attilax.img.ExpressRecipt
 */
public class ExpressRecipt {
	
	public static void main(String[] args) throws IOException {
		try {
			String para = "李哥,上海市闵行区吴浦路15号,13512345678,艾龙,艾伦的地址地址地址地址,13640685012";
			String cfg = "153-102,106-194,181-235,509-103,475-184,512-248";
			String ori = "c:\\exp.jpg";
			String output = "c:\\exp2.jpg";
			ori=args[0];
			para=args[1];
			cfg=args[2];
			output=args[3];
			ImgXbyThumbnail.	watermark(ori, para, output, cfg);
			System.out.println("--ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
