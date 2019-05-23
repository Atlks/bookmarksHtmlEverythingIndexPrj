package com.attilax.img;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Point;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;

 
import com.attilax.img.other.ColorUtil;
 
import com.google.common.collect.Lists;

public class imgUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		byte[] b = imgUtil.toByteArr("d:\\t.jpg");
		System.out.println(b.length);

	}
	  public static BufferedImage toBufferedImage(String f) {  
		return 	toBufferedImage(new ImageIcon(f).getImage());
	  }
		     
	  public static BufferedImage setMaskWhite(BufferedImage dest, List<Point> li) {
		  
		  for (Point point : li) {
			dest.setRGB(point.x, point.y, Color.white.getRGB());
		}
			return dest;
		}
	  public static List<Point> findColorPoints(BufferedImage src, HsvRangeV2 hr) {
		  List<Point> li=Lists.newArrayList();
		  ImgTraver ir=new ImgTraver();
			ir.process_cur_Pix_Point_Fun_Handler=p->{
				HSV cur=imgx.getHsv(src, p);
				 if( hr.contain(cur)  )
				 {
					 li.add(p);
					  
						// src.setRGB(p.x, p.y,Color.white.getRGB() );
				 }
			};
			ir.trave_fromTop2down(src); 
			return li;
		}
	
	  // This method returns a buffered image with the contents of an image  
    public static BufferedImage toBufferedImage(Image image) {  
        if (image instanceof BufferedImage) {  
            return (BufferedImage) image;  
        }  
  
        // Determine if the image has transparent pixels; for this method's  
        // implementation, see e661 Determining If an Image Has Transparent  
        // Pixels  
        boolean hasAlpha = hasAlpha(image);  
  
        // Create a buffered image with a format that's compatible with the  
        // screen  
        BufferedImage bimage = null;  
        GraphicsEnvironment ge = GraphicsEnvironment  
                .getLocalGraphicsEnvironment();  
        int width = image.getWidth(null);
		try {  
            // Determine the type of transparency of the new buffered image  
            int transparency = Transparency.OPAQUE;  
            if (hasAlpha) {  
                transparency = Transparency.BITMASK;  
            }  
  
            // Create the buffered image  
            GraphicsDevice gs = ge.getDefaultScreenDevice();  
            GraphicsConfiguration gc = gs.getDefaultConfiguration();  
            bimage = gc.createCompatibleImage(width, image  
                    .getHeight(null), transparency);  
        } catch (HeadlessException e) {  
            // The system does not have a screen  
        }  
  
        if (bimage == null) {  
            // Create a buffered image using the default color model  
            int type = BufferedImage.TYPE_INT_RGB;  
            if (hasAlpha) {  
                type = BufferedImage.TYPE_INT_ARGB;  
            }  
            bimage = new BufferedImage(width, image  
                    .getHeight(null), type);  
        }  
  
        // Copy image to buffered image  
        Graphics g = bimage.createGraphics();  
        // Paint the image onto the buffered image  
        g.drawImage(image, 0, 0, null);  
        g.dispose();  
  
        return bimage;  
    }  
  
    // This method returns true if the specified image has transparent pixels  
    public static boolean hasAlpha(Image image) {  
        // If buffered image, the color model is readily available  
        if (image instanceof BufferedImage) {  
            BufferedImage bimage = (BufferedImage) image;  
            return bimage.getColorModel().hasAlpha();  
        }  
  
        // Use a pixel grabber to retrieve the image's color model;  
        // grabbing a single pixel is usually sufficient  
        PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);  
        try {  
            pg.grabPixels();  
        } catch (InterruptedException e) {  
        }  
  
        // Get the image's color model  
        ColorModel cm = pg.getColorModel();  
        if(cm!=null)
        return cm.hasAlpha();  
        else
        	return false;//r65
    }  
  
  

	public static byte[] toByteArr(String string) {
		byte[] b = null;
		try {
			b = org.apache.commons.io.FileUtils.readFileToByteArray(new File(string));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * attilax 2017��1��11�� ����7:26:56
	 * 
	 * @param img
	 * @param point
	 * @return
	 */
	public static Color color(BufferedImage img, Point point) {
		int c_int = img.getRGB(point.x, point.y);
		return new Color(c_int);
	}

	public static boolean isSimilarColor(int cur_color, int select_color_int) {
		if (cur_color == select_color_int)
			return true;
		Color curClr = new Color(cur_color);
		Color select_color = new Color(select_color_int);
		int threotNum = 25;
		
		
		return isSimilar(curClr, select_color, threotNum);
	}

	public static boolean isSimilar(Color curClr, Color select_color, int threotNum) {
		int red_diff = Math.abs(curClr.getRed() - select_color.getRed());
		int green_diff = Math.abs(curClr.getGreen() - select_color.getGreen());
		int blue_diff = Math.abs(curClr.getBlue() - select_color.getBlue());
		
		if (red_diff < threotNum && green_diff < threotNum && blue_diff < threotNum)
			return true;
		return false;
	}

	 

	 

	//for dbg
	public static void output_var(BufferedImage img,String varName,String saveDir)
	{
		imgx.save_overwrite(img,saveDir+"\\"+varName+".jpg");
	}
	/**
	 * for dbg
	 * @param img
	 * @param varName
	 * @param saveDir
	 */
	public static void output_var_multi(BufferedImage img,String varName,String saveDir)
	{
	//	imgx.save_overwrite(img,saveDir+"\\"+varName+filex.getUUidName()+".jpg");
	}
}
