package com.attilax.img.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class SimilarComparer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	return this;
	}
	
	public static float compare(String f,String f2) throws IOException 
	{
	 return  PhotoDigest.compare(PhotoDigest.getData(f),PhotoDigest.getData(f2));
	}

	public float compare(BufferedImage rect_part, BufferedImage f2) {
		 return  PhotoDigest.compare(PhotoDigest.getData(rect_part),PhotoDigest.getData(f2));
	}

}
