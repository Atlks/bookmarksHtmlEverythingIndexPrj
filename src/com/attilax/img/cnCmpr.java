package com.attilax.img;

import java.io.FileNotFoundException;

public class cnCmpr {

	public static void main(String[] args) throws FileNotFoundException,
			Exception {
		String my = "C:\\0cnchar\\my.jpg";
		final ImagePHash p = new ImagePHash();
	//	p.size = 32;
	//	p.smallerSize = 32;
		p.dbg_gray_pic_path = "C:\\0cnchar";
		//String my_hash = p.getHash(my);
		String ard = "C:\\0cnchar\\ardy.jpg";
	//	String ard_hash = p.getHash(ard);
		String ms = "C:\\0cnchar\\ms.jpg";
		int dis_myNard = p.distance_img(my, ard);
		int dis_myNms = p.distance_img(my, ms);

		System.out.println("dis_myNard:" + dis_myNard + ",dis_myNms:"
				+ dis_myNms);

	}

}
