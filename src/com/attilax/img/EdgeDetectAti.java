/**
 * 
 */
package com.attilax.img;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

 

import com.attilax.encode.util.tryX;
import com.attilax.img.other.ColorUtil;
import com.attilax.img.other.LineArrivdBorderEx;
import com.attilax.io.filex;

/**
 * @author attilax
 *2016年12月19日 下午6:33:36
 */

@Deprecated
public class EdgeDetectAti {

	/**
	attilax    2016年12月19日  下午6:33:36
	 * @param args
	 * @throws LineArrivdBorderEx 
	 */
	public static void main(String[] args) throws LineArrivdBorderEx {
		 String s="c:\\00edge\\a.jpg";
			BufferedImage src = imgx.toImg(s);
			
			
			ImgTraver it=new ImgTraver();
			it.process_cur_Pix_Point_Fun_Handler=(p)->{
				if(p.x==125&& p.y==75)
					System.out.println("dbg");
				try {
					int x_next=p.x+1;
					int x_pre=p.x-1;
					int pre_gray= ColorUtil.gray(  new Color( src.getRGB(x_pre, p.y) )   );
					int next_gray= ColorUtil.gray(  new Color( src.getRGB(x_next, p.y) )   );
					int now_gray=ColorUtil.gray(  new Color( src.getRGB(p.x, p.y) )   );
					int now_abs=Math.abs(next_gray-pre_gray);
					boolean isGrad = now_abs>110;
					int now_start = 128;
					if(isGrad && now_gray>now_start)
						src.setRGB(p.x, p.y, new Color(255,255,255).getRGB());
					else 	if(isGrad && now_gray<now_start)
						src.setRGB(p.x, p.y, new Color(0,0,0).getRGB());
					else
					{
						src.setRGB(p.x, p.y, new Color(0,0,0).getRGB());
					}
					//src.setRGB(p.x, p.y, new Color(now,now,now).getRGB());
				} catch (Exception e) {
					System.out.println(" point:"+p);
					 e.printStackTrace();
				}
				
				
			};
			it.trave_downScan_lineByline(src, new Point(0,0));
			imgx.save_overwrite(src, "c:\\00edge\\"+filex.getUUidName()+".jpg");
			System.out.println("--f");
			

	}

}
