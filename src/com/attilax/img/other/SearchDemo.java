/**
 * 
 */
package com.attilax.img.other;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;

import com.attilax.img.ImagePHash;
import com.attilax.img.TextureFeatureUtil;
import com.attilax.img.imgx;

/**
 * @author attilax
 *2017年1月16日 下午3:50:56
 */
public class SearchDemo {

	/**
	attilax    2017年1月16日  下午3:50:57
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		String img_mini="c:\\0img\\mini.jpg";
		String img_1="c:\\0img\\1.jpg";
		String img_9="c:\\0img\\9.jpg";
		final TextureFeatureUtil p = new TextureFeatureUtil();
		String img1_toone = "c:\\0img\\1_toOne.jpg";
		String img2_toone = "c:\\0img\\9_toOne.jpg";
		BufferedImage img1=imgx.toImg(img_1);
		int width = img1.getWidth();
		int wid_final = 328;
		int hei_img1_one = getRltHeightByFinalWit(  width, wid_final,img1.getHeight());
		Thumbnails.of(img_1).size(wid_final, hei_img1_one).toFile(img1_toone);
	//	String img9_toone = "c:\\0img\\9_toOne.jpg";
		
		
		BufferedImage img2=imgx.toImg(img_9);
		int hei_img2_one = getRltHeightByFinalWit(img2.getWidth(), wid_final,img2.getHeight() );
		
		
		Thumbnails.of(img2).size(wid_final,hei_img2_one ).toFile(img2_toone);
		BufferedImage cut_img_1=new imgx().cutImage(new File(img1_toone),new Rectangle(0,0, wid_final,100));
		imgx.save_overwrite(cut_img_1, "c:\\0img\\1_cut_tmp.jpg");
		
		BufferedImage cut_img_2=new imgx().cutImage(new File(img2_toone),new Rectangle(0,0, wid_final,100));
		imgx.save_overwrite(cut_img_2, "c:\\0img\\9_cut_tmp.jpg");
		
		int dis_1 = p.distance_img(img_mini, "c:\\0img\\1_cut_tmp.jpg");
		int dis_2 = p.distance_img(img_mini, "c:\\0img\\9_cut_tmp.jpg");
		System.out.println("dis_1:"+dis_1+"    dis_9:"+dis_2);
	}

	private static int getRltHeightByFinalWit( int width, int wid_final, int ini_ht) {
		float num1=   width/wid_final;
		int hei_img1_one=(int) (ini_ht/num1);
		return hei_img1_one;
	}

}
