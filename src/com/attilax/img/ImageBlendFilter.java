package com.attilax.img;

//package com.gloomyfish.filter.study;

import java.awt.image.BufferedImage;

import com.jhlabs.image.AbstractBufferedImageOp;
/***
 * i get these blend method from html5 demo then i decide to 
 * translate these java script methods into java
 * 偶尔我也会写中文注释, 常见的图像混合方法
 * @author fish
 * @date 2012-11-28
 */
public class ImageBlendFilter extends AbstractBufferedImageOp {
	/** Define the blend mode */
	public final static int MULTIPLY_PIXEL = 1;
	public final static int SCREEN_PIXEL = 2;
	public final static int OVERLAY_PIXEL = 3;
	public final static int SOFTLIGHT_PIXEL = 4;
	public final static int HARDLIGHT_PIXEL = 5;
	public static final int COLOR_DODGE = 6;
	
	private int mode;
	private BufferedImage secondImage;
	public ImageBlendFilter() {
		mode = 1;
	}

	public ImageBlendFilter setBlendMode(int mode) {
		this.mode = mode;
		return this;
	}
	/**
	 * sec floe ..avover layer
	 * @param image
	 * @return
	 */
	public ImageBlendFilter setSecondImage(BufferedImage image) {
		this.secondImage = image;return this;
	}
	

	@Override
	public BufferedImage filter(BufferedImage src, BufferedImage dest) {
		checkImages(src);
		int width = src.getWidth();
        int height = src.getHeight();

        if ( dest == null )
        	dest = createCompatibleDestImage( src, null );

        int[] input1 = new int[width*height];
        int[] input2 = new int[secondImage.getWidth() * secondImage.getHeight()];
        int[] outPixels = new int[width*height];
        getRGB( src, 0, 0, width, height, input1);
        getRGB( secondImage, 0, 0, secondImage.getWidth(), secondImage.getHeight(), input2);
        int index = 0;
        int ta1 = 0, tr1 = 0, tg1 = 0, tb1 = 0;
        for(int row=0; row<height; row++) {
        	for(int col=0; col<width; col++) {
        		index = row * width + col;
        		ta1 = (input1[index] >> 24) & 0xff;
                tr1 = (input1[index] >> 16) & 0xff;
                tg1 = (input1[index] >> 8) & 0xff;
                tb1 = input1[index] & 0xff;
                int[] rgb = getBlendData(tr1, tg1, tb1, input2, row, col);
                outPixels[index] = (ta1 << 24) | (rgb[0] << 16) | (rgb[1] << 8) | rgb[2];
                
        	}
        }
        setRGB( dest, 0, 0, width, height, outPixels );
        return dest;
	}

	private int[] getBlendData(int tr1, int tg1, int tb1, int[] secImgInpt,int row, int col) {
		int width = secondImage.getWidth();
        int height = secondImage.getHeight();
        if(col >= width || row >= height) {
        	return new int[]{tr1, tg1, tb1};
        }
        int index = row * width + col;
		// int ta = (input[index] >> 24) & 0xff;
        int tr = (secImgInpt[index] >> 16) & 0xff;
        int tg = (secImgInpt[index] >> 8) & 0xff;
        int tb = secImgInpt[index] & 0xff;
        int[] rgb = new int[3];
        if(mode == 1) {
        	rgb[0] = modeOne(tr1, tr);
        	rgb[1] = modeOne(tg1, tg);
        	rgb[2] = modeOne(tb1, tb);
        }
        else if(mode == 2) {
        	rgb[0] = modeTwo(tr1, tr);
        	rgb[1] = modeTwo(tg1, tg);
        	rgb[2] = modeTwo(tb1, tb);        	
        }
        else if(mode == 3) {
        	rgb[0] = modeThree(tr1, tr);
        	rgb[1] = modeThree(tg1, tg);
        	rgb[2] = modeThree(tb1, tb);        	
        }
        else if(mode == 4) {
        	rgb[0] = modeFour(tr1, tr);
        	rgb[1] = modeFour(tg1, tg);
        	rgb[2] = modeFour(tb1, tb);        	
        }
        else if(mode == 5) {
        	rgb[0] = modeFive(tr1, tr);
        	rgb[1] = modeFive(tg1, tg);
        	rgb[2] = modeFive(tb1, tb);        	
        }
        else if(mode == COLOR_DODGE) {
        	rgb[0] = modeCOLOR_DODGE(tr1, tr);
        	rgb[1] =  modeCOLOR_DODGE(tg1, tg);
        	rgb[2] =  modeCOLOR_DODGE(tb1, tb);        	
        }
        return rgb;
	}
	
	// 暂且将原图像中的颜色称之为“底色A”画笔的颜色为“绘图色B”，将
	//base ori is a, push pen is b
	//yaosi fangfe ,zo div zeor ex..
	//  ，公式为：
//	C =MIN( A +（A×B）/（255-B）,255)
	/**
	 * b = guassBlur[index];
                a = gray[index];
                。颜色减淡的算法是这样的：C =MIN( A +（A×B）/（255-B）,255)，其中C为混合结果，A为源像素点，B为目标像素点。
	 * @param b
	 * @param a
	 * @return  其中（255-混合色）当于混合色的反相。
	 */
	private int modeCOLOR_DODGE(int a, int b) {
		if (b >= 255)
			return 255;
//		if (b < 128)
//			System.out.println("Dbg");
		if(a<128)
			System.out.println("dg");
		//try {
			float rzt = (float)a + (float)(a * b) /(float) (255 - b);
			int clr = (int) rzt;
			if (clr > 255)
				return 255;
			return clr;
		 

		// MIN(clr,255);
	}
	private int modeCOLOR_DODGE3(int a, int b) {
		if (b >= 255)
			return 255;
//		if (b < 128)
//			System.out.println("Dbg");
		if(a<128)
			System.out.println("dg");
		try {
			float rzt = (float)a + (float)(a * b) /(float) (255 - b);
			int clr = (int) rzt;
			if (clr >= 255)
			{
				if(a<=80)
					return a*2;
				if(a>80 && a<160)
					return (int) (a*1.5);
				return 255;
			}
				
			return clr;
		} catch (Exception e) {
			return 255;
		}

		// MIN(clr,255);
	}

	private int MIN(int i, int j) {
	 int m = 0;
	 if(m>i)
		 m=i;
	 if(m>j)
		 m=j;
	return m;
}

	//MULTIPLY_PIXEL=1   v1 is b ,v2 is a ..
	private int modeOne(int v1, int v2) {
		return (v1 * v2) / 255;  //  基色 > 128：结果色 = 255 - (255 - 混合色)* (255 - 基色) / 128
	}
	// SCREEN_PIXEL
	private int modeTwo(int v1, int v2) {
		return v1 + v2 - v1 * v2 / 255;  // 计算公式：结果色 = 255 - 混合色的补色 * 及色的补色 / 255
	}
	//OVERLAY_PIXEL
	private int modeThree(int v1, int v2) {
		return (v2 < 128) ? (2 * v1 * v2 / 255):(255 - 2 * (255 - v1) * (255 - v2) / 255);
	}
	//SOFTLIGHT_PIXEL
	private int modeFour(double v1, double v2) {
      if ( v1 > 127.5 ){
          return (int)(v2 + (255.0 - v2) * ((v1 - 127.5) / 127.5) * (0.5 - Math.abs(v2-127.5)/255.0));
       }else{
          return (int)(v2 - v2 * ((127.5 -  v1) / 127.5) * (0.5 - Math.abs(v2-127.5)/255.0));
       }
	}
	
	//hard light
	private int modeFive(double v1, double v2) {
      if ( v1 > 127.5 ){
          return (int)(v2 + (255.0 - v2) * ((v1 - 127.5) / 127.5));
       }else{
          return (int)(v2 * v1 / 127.5);
       }
	}

	private void checkImages(BufferedImage src) {
		int width = src.getWidth();
        int height = src.getHeight();
        if(secondImage == null )
        	throw new IllegalArgumentException("must set sec img");
		if(secondImage == null || secondImage.getWidth() > width || secondImage.getHeight() > height) {
			throw new IllegalArgumentException("the width, height of the input image must be great than blend image");
		}
	}

}
