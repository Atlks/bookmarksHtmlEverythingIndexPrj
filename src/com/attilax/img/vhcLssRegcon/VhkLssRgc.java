package com.attilax.img.vhcLssRegcon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.attilax.core;
import com.attilax.ocr.OcrX;

public class VhkLssRgc {

	public static void main(String[] args) {
		String orcMaindir = "C:\\0workspace\\Tesseract";
		String txt =new VhkLssRgc( ).recg( orcMaindir,"c:\\00\\v5.jpg");	
			

		System.out.println("ret:" + txt);
		// return this;
	}

	private String recg(String orcMaindir, String img) {
		// TODO Auto-generated method stub
		 
		String txt = new OcrX().getTxt(orcMaindir, img);
		txt=txt.replaceAll("荒V", "京v");
		return 	txt;
	}
	
	
	
}
