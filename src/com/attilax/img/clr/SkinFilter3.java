package com.attilax.img.clr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.attilax.clr.SkinFilter3;
import com.attilax.img.imgx;
import com.jhlabs.image.AbstractBufferedImageOp;

public class SkinFilter3 extends AbstractBufferedImageOp {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		SkinFilter3 f=new SkinFilter3();
		BufferedImage src=imgx.toImg("C:\\0000t\\233144_1272824621_tmadpvvn[1].jpg");
		//BufferedImage src=imgx.toImg("C:\\0000t\\233144_1272824621_tmadpvvn[1].jpg");
		BufferedImage d=f.filter(src, null);
		ImageIO.write(d, "jpg", new java.io.FileOutputStream(new File("C:\\0000t\\skin3.jpg")));
		System.out.println("--f");
	}

	@Override
	public BufferedImage filter(BufferedImage src, BufferedImage dest) {
		int width = src.getWidth();
        int height = src.getHeight();

        if ( dest == null )
        	dest = createCompatibleDestImage( src, null );

        int[] inPixels = new int[width*height];
        int[] outPixels = new int[width*height];
        getRGB( src, 0, 0, width, height, inPixels );
        int index = 0;
        for(int row=0; row<height; row++) {
        	int ta = 0, tr = 0, tg = 0, tb = 0;
        	for(int col=0; col<width; col++) {
        		index = row * width + col;
        		ta = (inPixels[index] >> 24) & 0xff;
                tr = (inPixels[index] >> 16) & 0xff;
                tg = (inPixels[index] >> 8) & 0xff;
                tb = inPixels[index] & 0xff;
                
                // detect skin method...
                double sum = tr + tg + tb;
                if (((double)tg / (double)tg - (double)tr / (double)tb <= -0.0905) &&
                	((double)(sum) / (double)(3 * tr) + (double)(tr - tg) / (double)(sum) <= 0.9498))
                {
                	tr = tg = tb = 0;
                } else {
                	tr = tg = tb = 255;
                }
                outPixels[index] = (ta << 24) | (tr << 16) | (tg << 8) | tb;
        	}
        }
        setRGB( dest, 0, 0, width, height, outPixels );
        return dest;
	}
}
