/**
 * 
 */
package com.attilax.img;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * @author attilax
 *2016年11月8日 下午9:02:26
 */
public class ImgGene4oilpnt {
	
	public static void main(String[] args) {
	//	t2();
		
		BufferedImage dest = new BufferedImage(100,100 ,1);
		imgx.setBackgroudColor_White(dest);
		
		   Graphics g = dest.getGraphics();     
	        
	       
	        
	        
	        //set boder
	        g.setColor(new Color(1,1,1));    
		    g.drawRect(9, 9, 80, 70); 
		//    imgx.save_overwrite(dest, "c:\\00qb4\\a.jpg");
		    
		    
		System.out.println("==f");
			 
	}

	private static void t2() {
		BufferedImage dest = new BufferedImage(5,5 ,1);
		imgx.setBackgroudColor_White(dest);
		dest.setRGB(2, 2, new Color(0,0,0).getRGB());
		imgx.save_overwrite(dest, "C:\\00capch\\p5.jpg");
	}

}
