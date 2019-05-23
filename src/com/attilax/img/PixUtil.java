package com.attilax.img;

import java.awt.Point;
import java.util.List;

import com.attilax.cca.Pix;

public class PixUtil {

	public static boolean list_contain(List<Pix> savePx, Point p) {
		 for (Pix pix : savePx) {
			if(pix.point.x==p.x && pix.point.y==p.y)
				return true;
		}
		return false;
	}

}
