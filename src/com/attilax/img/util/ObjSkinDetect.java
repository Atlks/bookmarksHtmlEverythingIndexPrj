package com.attilax.img.util;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.attilax.img.HSV;
import com.attilax.img.HsvRangeV2;
import com.attilax.img.imgx;
import com.attilax.img.skindetect.SkinFilter1;
import com.attilax.img.skindetect.SkinFilter2;
import com.attilax.img.skindetect.SkinFilter3;
import com.attilax.img.skindetect.SkinFilter4;
import com.attilax.img.skindetect.SkinFilter5;
import com.attilax.json.AtiJson;
import com.jhlabs.image.AbstractBufferedImageOp;
/**
 * this is very good skin detection
 * get real skin segmentation correctly....
 * ohh... cool
 * 
 * @author zhigang
 *
 */
public class ObjSkinDetect extends AbstractBufferedImageOp {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// imgfile,lowhsv,hihsv,outputfile..
		
		ObjSkinDetect SkinFilter_ati_hsv1=new ObjSkinDetect();
		BufferedImage src=imgx.toImg("D:\\00netpicrecg\\23-24-26-AdCenter006ef349f13b045bc38bc897c1a736bb629e4ef1c.jpg");
	 
		BufferedImage d=SkinFilter_ati_hsv1.filter(src, null);
		imgx.output_var(d, "var_d", "d:\\00netpic_dbg");
		
		
		BufferedImage d1= new SkinFilter1().filter(src, null);
		imgx.output_var(d1, "var_d1", "d:\\00netpic_dbg");
		
		BufferedImage d2= new SkinFilter2().filter(src, null);
		imgx.output_var(d2, "var_d2", "d:\\00netpic_dbg");
		
		imgx.output_var(new SkinFilter3().filter(src, null), "var_d3", "d:\\00netpic_dbg");
		imgx.output_var(new SkinFilter4().filter(src, null), "var_d4", "d:\\00netpic_dbg");
		imgx.output_var(new SkinFilter5().filter(src, null), "var_d5", "d:\\00netpic_dbg");
	//	ImageIO.write(d, "jpg", new java.io.FileOutputStream(new File("C:\\0000t\\dt[1].jpg")));
		System.out.println("--f");
	}
//    public BufferedImage createCompatibleDestImage(BufferedImage src, ColorModel dstCM) {
//        if ( dstCM == null )
//            dstCM = src.getColorModel();
//        return new BufferedImage(dstCM, dstCM.createCompatibleWritableRaster(src.getWidth(), src.getHeight()), dstCM.isAlphaPremultiplied(), null);
//    }
	@Override
	public BufferedImage filter(BufferedImage src, BufferedImage dest) {
		HSV low=new HSV().setHsv100mode(0, 2, 60);//15 17  85   ,19 11 90
		//31 23 86   , 14 24 84
	//	HSV hi=new HSV().setHsv100mode(31, 60, 100);
		HSV hi=new HSV().setHsv100mode(50, 60, 100);
		HsvRangeV2 hr=new HsvRangeV2(low, hi);
		int width = src.getWidth();
        int height = src.getHeight();

        if ( dest == null )
        	dest = createCompatibleDestImage( src, null );

       
      
        int index = 0;
        for(int row=0; row<height; row++) {
        	 
        	for(int col=0; col<width; col++) {
        	 
        		HSV hsv=imgx.getHsv(src,col, row);
        	//	System.out.println( AtiJson.toJson(hsv));
        		if(hr.contain(hsv))
        			dest.setRGB( col,row, Color.black.getRGB());
        		else
        			dest.setRGB( col,row, Color.white.getRGB());
        			
                
                
                // detect skin method...
                
        	}
        }
       
        return dest;
	}
}

