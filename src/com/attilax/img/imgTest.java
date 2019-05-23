package com.attilax.img;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.attilax.exception.ExUtil;
import com.attilax.io.filex;
import com.attilax.json.AtiJson;

public class imgTest {
	public static void main(String[] args) throws IOException {
		// imgx.txt2img("大", "c:\\0key\\da.png",30);
		System.out.println("--");
		imgx imgx = new imgx();
		imgx.imgSize = 50;
		imgx.fontSize = 40;
		imgx.fontBaselineY=40;
		String t=filex.read("c:\\0key\\cn.txt", "gbk");
	//	String txt = "鹰";
		char[] char_a=t.toCharArray();
		for (char c : char_a) {
			
			String s=String.valueOf(c);
			if(s.trim().length()==0)
				continue;
			processOne(imgx, s);
		}
		
		String output = "c:\\0key\\da.png";
		//ImageIO.write(im, "png", new File(output));

		System.out.println("f");
	}

	private static void processOne(imgx imgx, String txt) {
		BufferedImage im = imgx.txt2img(txt);
		//System.out.println( AtiJson.toJson(im));
		int ht=im.getHeight();int wid=im.getWidth();
		int blackCount = 0;
		for(int i=0;i<ht;i++)
		{
			for(int y=0;y<wid;y++)
			{
				 if(  imgx.isBlackPix(im, i, y))
				 {
					 blackCount++;
				 }
			}
			
		}
		System.out.println(txt+","+blackCount);
	}
}
