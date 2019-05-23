package com.attilax.img;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
 












import javax.imageio.ImageIO;

import com.attilax.Closure;
import com.attilax.exception.ExUtil;
import com.attilax.io.filex;
import com.attilax.util.dirx;
/*
* pHash-like image hash.
* Author: Elliot Shepherd (elliot@jarofworms.com
* Based On: http://www.hackerfactor.com/blog/index.php?/archives/432-Looks-Like-It.html
*/
public class TextureFeatureUtil {
	
//	public static void main(String[] args) {
//		
//	}
// 
	public int size = 32; //32;   
	public int smallerSize =8;// 32;    jeig  lada score
	public String dbg_gray_pic_path;
    
   public TextureFeatureUtil() {
       initCoefficients();
   }
    
   public TextureFeatureUtil(int size, int smallerSize) {
       this.size = size;
       this.smallerSize = smallerSize;
        
       initCoefficients();
   }
    
   /**
    * str
    * @param s1  figer not img path
    * @param s2 figer
    * @return
    */
   public int distance(String s1, String s2) {
       int counter = 0;
       for (int k = 0; k < s1.length();k++) {
           if(s1.charAt(k) != s2.charAt(k)) {
               counter++;
           }
       }
       return counter;
   }
   /**
    * dis==0  is same. when  more ,more diff
    * @param imgPath1
    * @param imgPath12
    * @return
    */
	public int distance_img(String imgPath1, String imgPath12) {
		Image s1 = null;
		Image s2 = null;
		try {
			s1 = ImageIO.read(new FileInputStream(new File(imgPath1)));
			s2 = ImageIO.read(new FileInputStream(new File(imgPath12)));

		} catch (IOException e) {
			ExUtil.throwExV2(e);
		}
	 
		
	   String sa=getHash(s1);String sb=getHash(s2);
	   return distance(sa, sb);
   }
   
   public int distance(java.awt.Image s1, java.awt.Image s2) {
	   String sa=getHash(s1);String sb=getHash(s2);
	   return distance(sa, sb);
   }
    
   // Returns a 'binary string' (like. 001010111011100010) which is easy to do a hamming distance on.
   public String getHash(InputStream is) throws Exception {
       BufferedImage img = ImageIO.read(is);
        
       return getHash(img);
   }
/**
 * length==961   maybe 32*32=1024
 * @param imgx
 * @return
 */
   public String getHash(Image imgx)   {
	/* 1. Reduce size.
        * Like Average Hash, pHash starts with a small image.
        * However, the image is larger than 8x8; 32x32 is a good size.
        * This is really done to simplify the DCT computation and not
        * because it is needed to reduce the high frequencies.
        */
	   BufferedImage img=(BufferedImage) imgx;
       img = resize(img, size, size);
        
       /* 2. Reduce color.
        * The image is reduced to a grayscale just to further simplify
        * the number of computations.
        */
       img = grayscale(img);
       if(dbg_gray_pic_path!=null)
		try {
			ImageIO.write(img, "jpg", new File(dbg_gray_pic_path+"/"+filex.getUUidName()+".jpg"));
		} catch (IOException e) {
			ExUtil.throwExV2(e);
		}
       
        
       double[][] vals = new double[size][size];
        
       for (int x = 0; x < img.getWidth(); x++) {
           for (int y = 0; y < img.getHeight(); y++) {
               vals[x][y] = getBlue(img, x, y);
           }
       }
        
       /* 3. Compute the DCT.
        * The DCT separates the image into a collection of frequencies
        * and scalars. While JPEG uses an 8x8 DCT, this algorithm uses
        * a 32x32 DCT.
        */
       long start = System.currentTimeMillis();
       double[][] dctVals = applyDCT(vals);
       System.out.println("DCT fee time (ms) : " + (System.currentTimeMillis() - start));
        
       /* 4. Reduce the DCT.
        * This is the magic step. While the DCT is 32x32, just keep the
        * top-left 8x8. Those represent the lowest frequencies in the
        * picture.
        */
       /* 5. Compute the average value.
        * Like the Average Hash, compute the mean DCT value (using only
        * the 8x8 DCT low-frequency values and excluding the first term
        * since the DC coefficient can be significantly different from
        * the other values and will throw off the average).
        */
       double total = 0;
        
       for (int x = 0; x < smallerSize; x++) {
           for (int y = 0; y < smallerSize; y++) {
               total += dctVals[x][y];
           }
       }
       total -= dctVals[0][0];
        
       double avg = total / (double) ((smallerSize * smallerSize) - 1);
    
       /* 6. Further reduce the DCT.
        * This is the magic step. Set the 64 hash bits to 0 or 1
        * depending on whether each of the 64 DCT values is above or
        * below the average value. The result doesn't tell us the
        * actual low frequencies; it just tells us the very-rough
        * relative scale of the frequencies to the mean. The result
        * will not vary as long as the overall structure of the image
        * remains the same; this can survive gamma and color histogram
        * adjustments without a problem.
        */
       String hash = "";
        
       for (int x = 0; x < smallerSize; x++) {
           for (int y = 0; y < smallerSize; y++) {
               if (x != 0 && y != 0) {
                   hash += (dctVals[x][y] > avg?"1":"0");
               }
           }
       }
        
       return hash;
}
    
   private BufferedImage resize(BufferedImage image, int width,    int height) {
       BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
       Graphics2D g = resizedImage.createGraphics();
       g.drawImage(image, 0, 0, width, height, null);
       g.dispose();
       return resizedImage;
   }
    
   private ColorConvertOp colorConvert = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
 
   private BufferedImage grayscale(BufferedImage img) {
       colorConvert.filter(img, img);
       return img;
   }
    
   private static int getBlue(BufferedImage img, int x, int y) {
       return (img.getRGB(x, y)) & 0xff;
   }
    
   // DCT function stolen from http://stackoverflow.com/questions/4240490/problems-with-dct-and-idct-algorithm-in-java
 
   private double[] c;
   private void initCoefficients() {
       c = new double[size];
        
       for (int i=1;i<size;i++) {
           c[i]=1;
       }
       c[0]=1/Math.sqrt(2.0);
   }
    
   private double[][] applyDCT(double[][] f) {
       int N = size;
        
       double[][] F = new double[N][N];
       for (int u=0;u<N;u++) {
         for (int v=0;v<N;v++) {
           double sum = 0.0;
           for (int i=0;i<N;i++) {
             for (int j=0;j<N;j++) {
               sum+=Math.cos(((2*i+1)/(2.0*N))*u*Math.PI)*Math.cos(((2*j+1)/(2.0*N))*v*Math.PI)*(f[i][j]);
             }
           }
           sum*=((c[u]*c[v])/4.0);
           F[u][v] = sum;
         }
       }
       return F;
   }
   static String image2;
   public static void main(String[] args) {
        //<473 d zosh yyeo..
       final TextureFeatureUtil p = new TextureFeatureUtil();
    
      
       try {
    	   final String image1_figer      = p.getHash(new FileInputStream(new File("d:/img/a2.jpg")));
           dirx.trave("D:\\gialenimg", new Closure () {

			@Override
			public Object execute(Object arg0) throws Exception {
				String f=(String) arg0;
				  image2 = p.getHash(new FileInputStream(new File(f)));
				  int dis=p.distance(image1_figer, image2);
				  if(dis<474)
				  {
					  if(!f.contains("_barx"))
					  rename(f);
				  }
				return null;
			}

			private void rename(String f) {
				String path=new File(f).getParent();
				String base=filex.getFileName_noExtName(f);
				String ext=filex.getExtName(f);
				String newFile=path+"\\"+base+"_barx."+ext;
				System.out.println(newFile);
			 	filex.rename(f, newFile);
			//	new File(f).
				
			}
		});
           
//           image2 = p.getHash(new FileInputStream(new File("d:/img/bar2.jpg")));
//           System.out.println("1:1 Score is " + p.distance(image1, image2));
//      
//           image2 = p.getHash(new FileInputStream(new File("d:/img/wechat2.jpg")));
//           System.out.println("1:2 Score is " + p.distance(image1, image2));
//          
//           image2 = p.getHash(new FileInputStream(new File("d:/img/they2.jpg")));
//           System.out.println("1:3 Score is " + p.distance(image1, image2));
//           
            
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (Exception e) {
           e.printStackTrace();
       }
 
   }

public static TextureFeatureUtil newx() {
	return new TextureFeatureUtil();
	
}

public String getHash(String f) throws FileNotFoundException, Exception {
	 
	return getHash(new FileInputStream(new File(f)));
}
}