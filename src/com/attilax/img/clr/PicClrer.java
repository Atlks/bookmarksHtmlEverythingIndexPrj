/**
 * 
 */
package com.attilax.img.clr;

import java.util.function.Function;

import com.attilax.img.imgx;
import com.attilax.io.dirx;
import com.attilax.io.filex;

/**
 * @author attilax 2016年10月20日 下午7:45:13
 */
public class PicClrer {

	public static int iidx = 0;

	/**
	 * attilax 2016年10月20日 下午7:45:13
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		String s="D:\\dcim\\103APPLE\\IMG_4103.PNG";
		s="D:\\dcim\\102APPLE\\IMG_2744.PNG";
		imgx ix=new imgx();
	 	System.out.println(ix.containsWhiteLine(s,600)); 
	//	System.out.println(filex.moveToDir(s, "d:\\dcim_mov"));
	  	splitPicNPhoto();
 
	}

	private static void splitPicNPhoto() {
		imgx ix=new imgx();
		Function<String, Object> fun = (String f) -> {
			try {
				if (!f.toLowerCase().trim().endsWith("jpg"))
					if (!f.toLowerCase().trim().endsWith("png"))				
						return null;
				System.out.println(f);
				
				if (ix.containsWhiteLine(f,600))
					filex.moveToDir(f, "d:\\dcim_mov");
			} catch (Exception e) {
			e.printStackTrace();
			}
			

			iidx++;
			System.out.println(iidx);
			return f;

		};
		dirx.traveFiles("d:\\dcim", fun);
	}
}
