package com.attilax.img.skindetect;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.attilax.img.imgx;
import com.attilax.io.filex;

public class skinfltAll {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		String img = "C:\\0000t\\e2b7b48304149fb5aee9197e81d3dc4b.jpg";
		BufferedImage src=imgx.toImg(img);
		//BufferedImage src=imgx.toImg("C:\\0000t\\233144_1272824621_tmadpvvn[1].jpg");
		SkinFilter1 f=new SkinFilter1();
		BufferedImage d=f.filter(src, null);
		ImageIO.write(d, "jpg", new java.io.FileOutputStream(new File(filex.addSuffix(img,"flt1"))));
		
	//	SkinFilter2 f2=;
	//	BufferedImage d=;
		ImageIO.write(new SkinFilter2().filter(src, null), "jpg", new java.io.FileOutputStream(new File(filex.addSuffix(img,"flt2"))));
		ImageIO.write(new SkinFilter3().filter(src, null), "jpg", new java.io.FileOutputStream(new File(filex.addSuffix(img,"flt3"))));
		ImageIO.write(new SkinFilter4().filter(src, null), "jpg", new java.io.FileOutputStream(new File(filex.addSuffix(img,"flt4"))));
		ImageIO.write(new SkinFilter5().filter(src, null), "jpg", new java.io.FileOutputStream(new File(filex.addSuffix(img,"flt5"))));
		
		
		System.out.println("--f");

	}

}
