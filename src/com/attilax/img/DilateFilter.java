/**
 * 
 */
package com.attilax.img;

/**
 * @author attilax
 *2016年11月8日 下午5:39:17
 */
//package com.attilax.img;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

 


import com.attilax.io.FileExistEx;
import com.attilax.io.filex;
import com.jhlabs.image.BinaryFilter;

public class DilateFilter extends BinaryFilter {
	
	public static void main(String[] args) {
		String s="C:\\00capch\\1108_170607_914.jpg";
		BufferedImage src=imgx.toImg(s);
		BufferedImage dest=	new DilateFilter().filter(src, null);
		try {
			imgx.save(dest, "C:\\00capch\\"+filex.getUUidName()+" dilate.jpg");
		} catch (FileExistEx e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("--f");
	}
	
	public DilateFilter() {
		forgeColor = Color.WHITE;
	}
	
	private Color forgeColor;

	public Color getForgeColor() {
		return forgeColor;
	}

	public void setForgeColor(Color forgeColor) {
		this.forgeColor = forgeColor;
	}

	@Override
	public BufferedImage filter(BufferedImage src, BufferedImage dest) {
		int width = src.getWidth();
        int height = src.getHeight();

        if ( dest == null )
        	dest = createCompatibleDestImage( src, null );

        int[] inPixels = new int[width*height];
        int[] outPixels = new int[width*height];
      //  src = super.filter(src, null); // we need to create new one
        getRGB( src, 0, 0, width, height, inPixels );
        int index = 0, index1 = 0, newRow = 0, newCol = 0;
        int ta1 = 0, tr1 = 0, tg1 = 0, tb1 = 0;
        for(int row=0; row<height; row++) {
        	int ta = 0, tr = 0, tg = 0, tb = 0;
        	for(int col=0; col<width; col++) {
        		index = row * width + col;
        		ta = (inPixels[index] >> 24) & 0xff;
                tr = (inPixels[index] >> 16) & 0xff;
                tg = (inPixels[index] >> 8) & 0xff;
                tb = inPixels[index] & 0xff;
                boolean dilation = false;
                for(int offsetY=-1; offsetY<=1; offsetY++) {
                	for(int offsetX=-1; offsetX<=1; offsetX++) {
                		if(offsetY==0 && offsetX==0) {
                			continue;
                		}
                		newRow = row + offsetY;
                		newCol = col + offsetX;
                		if(newRow <0 || newRow >=height) {
                			newRow = 0;
                		}
                		if(newCol < 0 || newCol >=width) {
                			newCol = 0;
                		}
                		index1 = newRow * width + newCol;
                		ta1 = (inPixels[index1] >> 24) & 0xff;
                        tr1 = (inPixels[index1] >> 16) & 0xff;
                        tg1= (inPixels[index1] >> 8) & 0xff;
                        tb1 = inPixels[index1] & 0xff;
                        if(tr1 == forgeColor.getRed() && tg1 == tb1) {
	                        dilation = true;
	                        break;
                        }
                	}
                	if(dilation){
                		break;
                	}
                }
                
                if(dilation) {
                	tr = tg = tb = forgeColor.getRed();
                } else {
                	tr = tg = tb = 255 - forgeColor.getRed();
                }
                outPixels[index] = (ta << 24) | (tr << 16) | (tg << 8) | tb;
        	}
        }
        setRGB( dest, 0, 0, width, height, outPixels );
        return dest;
	}

	/* (non-Javadoc)
	 * @see com.jhlabs.image.WholeImageFilter#filterPixels(int, int, int[], java.awt.Rectangle)
	 */
	@Override
	protected int[] filterPixels(int arg0, int arg1, int[] arg2, Rectangle arg3) {
		// TODO Auto-generated method stub
		return null;
	}

}

