package com.attilax.img;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


// r19 import org.apache.commons.io.FileUtils;
// r1e readd
import org.apache.commons.io.FileUtils;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 

 


















  
 
import com.attilax.exception.ExUtil;
 
 
 
import com.google.common.collect.Maps;
 

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
  
public class imgxBasic extends imgUtil {

	
private Logger log = LoggerFactory.getLogger(getClass());
    
    private static String DEFAULT_THUMB_PREVFIX = "thumb_";
    private static String DEFAULT_CUT_PREVFIX = "cut_";
    private static Boolean DEFAULT_FORCE = false;
	public static boolean isSimilarColor(int cur_color, int select_color_int) {
		 if(cur_color==select_color_int)
			 return true;
			Color curClr=new Color(cur_color);
			Color select_color=new Color(select_color_int);
			int red_diff=Math.abs( curClr.getRed()-select_color.getRed());
			int green_diff=Math.abs( curClr.getGreen()-select_color.getGreen());
			int blue_diff=Math.abs( curClr.getBlue()-select_color.getBlue());
			if(red_diff<25 && green_diff<25 && blue_diff<25)
				return true;
		return false;
	}
    
    /**
     * <p>Title: cutImage</p>
     * <p>Description:  根据原图与裁切size截取局部图片</p>
     * @param srcImg    源图片
     * @param output    图片输出流
     * @param rect        需要截取部分的坐标和大小
     */
	 

//    public void save(File srcImg, OutputStream output, java.awt.Rectangle rect)
//			throws IOException {
//		BufferedImage bi=   cutImage(srcImg,rect);
//		    save(srcImg, output, bi);
//	}

//	private void save(File srcImg, OutputStream output, BufferedImage bi)
//			throws IOException {
//		String suffix;//=filex.getExtName(srcImg.getAbsolutePath());
//		ImageIO.write(bi, suffix, output);
//	}
	public void save( BufferedImage bi, OutputStream output,String ext)
			 {
	//	String suffix=filex.getExtName(srcImg.getAbsolutePath());
		try {
			ImageIO.write(bi, ext, output);
		} catch (IOException e) {
			 ExUtil.throwExV2(e);
		}
	}
	
	public void save( BufferedImage bi, String out_file,String ext)
	 {
		//filex.createAllPath(out_file);
//	String suffix=filex.getExtName(srcImg.getAbsolutePath());
try {
	OutputStream output=(OutputStream) new FileOutputStream(new File(out_file));
	ImageIO.write(bi, ext, output);
} catch (IOException e) {
	 ExUtil.throwExV2(e);
}
}
	@Deprecated
    public BufferedImage cutImage(File srcImg, Rectangle rect) {
    	  ImageReader ImageReader1 = ImgReader(srcImg);
           return cutImg(rect, ImageReader1);
		
	}
	@Deprecated
    public	ImageReader ImgReader(File srcImg) {
		ImageInputStream iis = null;
    	FileInputStream fis = null;
		try {
			fis = new FileInputStream(srcImg);
		} catch (FileNotFoundException e) {
			ExUtil.throwExV2(e);
		}
           // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
           String types = Arrays.toString(ImageIO.getReaderFormatNames()).replace("]", ",");
           String suffix = null;
           // 获取图片后缀
           if(srcImg.getName().indexOf(".") > -1) {
               suffix = srcImg.getName().substring(srcImg.getName().lastIndexOf(".") + 1);
           }// 类型和图片后缀全部小写，然后判断后缀是否合法
//           if(suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()+",") < 0){
//               log.error("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
//               return ;
//           }
           // 将FileInputStream 转换为ImageInputStream
           try {
			iis = ImageIO.createImageInputStream(fis);
		} catch (IOException e) {
			ExUtil.throwEx(e);
		}
           // 根据图片类型获取该种类型的ImageReader
           ImageReader ImageReader1 = ImageIO.getImageReadersBySuffix(suffix).next();
           ImageReader1.setInput(iis,true);
		return ImageReader1;
	}
    public	BufferedImage cutImg(BufferedImage img, Rectangle rect,String suffix) {
    	 // 根据图片类型获取该种类型的ImageReader
    	
        ImageInputStream iis = null;
		try {
			iis = ImageIO
			        .createImageInputStream(new ByteArrayInputStream(
			                bufferedImageToByteArray(img,suffix)));
		} catch (IOException e1) {
		ExUtil.throwExV2(e1);
		}
        
        ImageReader ImageReader1 = ImageIO.getImageReadersBySuffix(suffix).next();
        ImageReader1.setInput(iis, true);
		ImageReadParam ImageReadParam1 = ImageReader1.getDefaultReadParam();
		ImageReadParam1.setSourceRegion(rect);
        BufferedImage bi = null;
		try {
			bi = ImageReader1.read(0, ImageReadParam1);
		} catch (IOException e) {
			ExUtil.throwEx(e);
		}
        return bi;
          // return cutImg(rect, ImageReader1, ImageReadParam1);
	}
    /**
	attilax    2016年11月16日  下午5:44:14
	 * @param img
	 * @param suffix
	 * @return
	 */
	private byte[] bufferedImageToByteArray(BufferedImage img, String suffix) {
	     ByteArrayOutputStream out = new ByteArrayOutputStream();  
         try {
			boolean flag = ImageIO.write(img, suffix, out);
		} catch (IOException e) {
			ExUtil.throwExV2(e);
		}  
         byte[] b = out.toByteArray(); 
		return  b;
	}
	@Deprecated
	public	BufferedImage cutImg(Rectangle rect, ImageReader ImageReader1) {
		ImageReadParam ImageReadParam1 = ImageReader1.getDefaultReadParam();
           return cutImg(rect, ImageReader1, ImageReadParam1);
	}
	@Deprecated
synchronized	public BufferedImage cutImg(Rectangle rect, ImageReader ImageReader1,
			ImageReadParam ImageReadParam1) {
		ImageReadParam1.setSourceRegion(rect);
           BufferedImage bi = null;
		try {
			bi = ImageReader1.read(0, ImageReadParam1);
		} catch (IOException e) {
			ExUtil.throwEx(e);
		}
           return bi;
	}
 
    
    public BufferedImage cutImage_retImg(String srcImg, Rectangle  rect){
      
    	return    cutImage_retImg(srcImg,  rect.x,rect.y,rect.width,rect.height);
    }@Deprecated
    public BufferedImage cutImage_retImg(String srcImg, int x, int y, int width, int height){
    File srcImg2 = new File(srcImg);
	return    cutImage(srcImg2,  new java.awt.Rectangle(x, y, width, height));
    }
    /**
     * <p>Title: thumbnailImage</p>
     * <p>Description: 根据图片路径生成缩略图 </p>
     * @param imagePath    原图片路径
     * @param w            缩略图宽
     * @param h            缩略图高
     * @param prevfix    生成缩略图的前缀
     * @param force        是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
     */
    public void thumbnailImage(File srcImg, OutputStream output, int w, int h, String prevfix, boolean force){
        if(srcImg.exists()){
            try {
                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
                String types = Arrays.toString(ImageIO.getReaderFormatNames()).replace("]", ",");
                String suffix = null;
                // 获取图片后缀
                if(srcImg.getName().indexOf(".") > -1) {
                    suffix = srcImg.getName().substring(srcImg.getName().lastIndexOf(".") + 1);
                }// 类型和图片后缀全部小写，然后判断后缀是否合法
                if(suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()+",") < 0){
                    log.error("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
                    return ;
                }
                log.debug("target image's size, width:{}, height:{}.",w,h);
                Image img = ImageIO.read(srcImg);
                // 根据原图与要求的缩略图比例，找到最合适的缩略图比例
                if(!force){
                    int width = img.getWidth(null);
                    int height = img.getHeight(null);
                    if((width*1.0)/w < (height*1.0)/h){
                        if(width > w){
                            h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w/(width*1.0)));
                            log.debug("change image's height, width:{}, height:{}.",w,h);
                        }
                    } else {
                        if(height > h){
                            w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h/(height*1.0)));
                            log.debug("change image's width, width:{}, height:{}.",w,h);
                        }
                    }
                }
                BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics g = bi.getGraphics();
                g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
                g.dispose();
                // 将图片保存在原目录并加上前缀
                ImageIO.write(bi, suffix, output);
                output.close();
            } catch (IOException e) {
               log.error("generate thumbnail image failed.",e);
            }
        }else{
            log.warn("the src image is not exist.");
        }
    }
	public static int n=0;
	/**
	 * @param args
	 * @throws IOException 
	 */
 

 

	/**com.attilax.img.imgx.toDataUriBase64
	 * @param args
	 */
 


	public  String save2local(String imageFilePath,String localpath)   {
		
		try {
		String 	imageFileNoPath=imageFilePath.substring(3);
			localpath=localpath+"/"+imageFileNoPath;
		//	//filex.createAllPath(localpath);
			File tag = new File(localpath);
			if(!tag.exists())
				FileUtils.copyFile(new File( imageFilePath), tag);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return localpath;
		
		
		
		
	}
		
	
	/**
	attilax    2016年4月14日  下午6:40:35
	 * @param string
	 * @return
	 */
//	public  String toDataUriBase64(String imageFilePath) {
//		//imageFilePath="F:\\美国队长2\\美国队长2.jpg";
//		File f=new File(imageFilePath);
//		System.out.println( f.exists());
//		byte[] ba=toByteArray(f);
//		
//		String encode = Base64.encode(ba, false);
//		return encode;
//	}
	
		/**
	attilax    2016年9月28日  下午5:35:57
	 * @param f
	 * @return
	 */
 

	public static byte[] toByteArray(File imageFile)  {
		if(!imageFile.exists())
			throw new RuntimeException("file not exist,file:"+imageFile.getAbsolutePath());
		try {
		BufferedImage img = ImageIO.read(imageFile);
		ByteArrayOutputStream buf = new ByteArrayOutputStream((int) imageFile
				.length());
	
			ImageIO.write(img, "jpg", buf);
			return buf.toByteArray();
		} catch (Exception e) {
		//	e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	public static byte[] toByteArray(String strUrl) {
		ByteArrayOutputStream baos = null;
		try {
			URL u = new URL(strUrl);
			BufferedImage image = ImageIO.read(u);

			// convert BufferedImage to byte array
			baos = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", baos);
			baos.flush();

			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
				}
			}
		}
		return null;
	}
	
	public static byte[] toByteArray(BufferedImage image,String enc) {
		ByteArrayOutputStream baos = null;
		try {
			//URL u = new URL(strUrl);
		//	BufferedImage image = ImageIO.read(u);

			// convert BufferedImage to byte array
			baos = new ByteArrayOutputStream();
			ImageIO.write(image,enc, baos);
			baos.flush();

			return baos.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static ImageIcon toImg(byte[] by) {
		return  new ImageIcon(by);
	}
	
	
	  public static void setClipboardImage(final Image image)
	    {
	        Transferable trans = new Transferable(){
	            @Override
	            public Object getTransferData(DataFlavor flavor)
	                    throws UnsupportedFlavorException, IOException {
	                // TODO Auto-generated method stub
	                if (isDataFlavorSupported(flavor))
	                {
	                    return image;
	                }                      
	                throw new UnsupportedFlavorException(flavor);
	            }
	 
	            @Override
	            public DataFlavor[] getTransferDataFlavors() {
	                // TODO Auto-generated method stub
	                return new DataFlavor[] { DataFlavor.imageFlavor };
	            }
	 
	            @Override
	            public boolean isDataFlavorSupported(DataFlavor flavor) {
	                // TODO Auto-generated method stub
	                return DataFlavor.imageFlavor.equals(flavor);
	            }             
	        };
	         
	        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(trans, null);
	    }

	 
	 
 
	 

 
	
 
	
	
	 
	 

	public static BufferedImage toImg(String string) {
		
		
		 if(!new File(string).exists())
			 throw new RuntimeException("FileNotExist:"+string);
		try {
			return ImageIO.read(new File( string));
		} catch (IOException e) {
			System.out.println( "--ioex:"+string );
			ExUtil.throwExV2(e,string);
		}
		return null;
	}
	
	 
	private static float min(float r, float g, float b) {
		float m;
		m=r;
		if(m>g)
			 
			m=g;
		if(m>b)
			m=b;
		
		return m;
	}

	private static float max(float r, float g, float b) {
		float m;
		m=r;
		if(m<g)
			 
			m=g;
		if(m<b)
			m=b;
		
		return m;
	}

	public static float getBlackPercent(BufferedImage src2) {
		int n=src2.getWidth()*src2.getHeight();
		int blackCount=getBlackCount(src2);
		return ((float)blackCount)/(float)n;
	}

	private static int getBlackCount(BufferedImage src2) {
		int width = src2.getWidth();
		int height = src2.getHeight();
		int ct = 0;
		Color pixelColor ;
		for(int i=0;i<width;i++)
		{
		
			for(int j=0;j<height;j++)
			{
				//try {
					int n=	src2.getRGB(i, j);
					if(n==-1) //white
						continue;
					pixelColor= new Color(n);
					int r = pixelColor.getRed();
					int g = pixelColor.getGreen();
					int b = pixelColor.getBlue();
					if(r<50 && g<50 && b<50)
						ct++;
				//	System.out.println(n+",cord:"+i+"-"+j+",rgb:"+r+"-"+g+"-"+b);
				//} catch (Exception e) {
				//	e.printStackTrace();
					//System.out.println(n+",corderr:"+i+"-"+j);
			//	}
			}
		}
		return ct;
	}

	 
	
  
 

	public static void setBackgroudColor(BufferedImage bi, Color color) {
		int rgb = color.getRGB();
		 int wid=bi.getWidth();
		int ht=bi.getHeight();
		for(int w=0;w<wid;w++)
			 for(int h=0;h<ht;h++)
			 {
				 //Color color = new Color(255, 255, 255);			
					
					bi.setRGB(w,h, rgb);
			 }
		
	}
	
	public static void setBackgroudColor(BufferedImage bi,int r,int g,int b) {
		Color color=new Color(r, g, b);
		int rgb = color.getRGB();
		 int wid=bi.getWidth();
		int ht=bi.getHeight();
		for(int w=0;w<wid;w++)
			 for(int h=0;h<ht;h++)
			 {
				 //Color color = new Color(255, 255, 255);			
					
					bi.setRGB(w,h, rgb);
			 }
		
	}

	public static void save_overwrite(BufferedImage bi, String out_file) {
		
		//filex.createAllPath(out_file);
		try {
			ImageIO.write(bi, "jpg", new java.io.FileOutputStream(new File(out_file)));
		} catch (IOException e) {
		ExUtil.throwExV2(e);
		}
		
	}


	/**
	attilax    2016年11月8日  下午7:01:09
	 * @param object
	 */
	public static void trave(BufferedImage src,ImagTraver trver) {
		int width = src.getWidth();
		int height = src.getHeight();
	
			// int ta = 0, tr = 0, tg = 0, tb = 0;
		for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					
					trver.trave( x,y);

			}
		}
		
	}

	

	public static BufferedImage addVertLine(String lineCharsPic, int x, java.awt.Color color) {
		BufferedImage bi=imgxBasic.toImg(lineCharsPic);
		 for(int h=0;h<bi.getHeight();h++)
		 {
			 bi.setRGB(x, h, color.getRGB());
		 }
		 return bi;
		
	}

	public static void addVertLine(BufferedImage bi, int x, java.awt.Color color) {
		 for(int h=0;h<bi.getHeight();h++)
		 {
			 bi.setRGB(x, h, color.getRGB());
		 }
		
	}

	public static BufferedImage addVertLine_repeat(String lineCharsPic, int step,
			java.awt.Color color) {
		BufferedImage bi=imgxBasic.toImg(lineCharsPic);
		 for(int h=0;h<bi.getHeight();h++)
		 {
			 for(int x=0;x<bi.getWidth();x=x+step)
				 bi.setRGB(x, h, color.getRGB());
		 }
		 return bi;
	}

	public static void addVertLine_rpt(BufferedImage bi, int step,
			java.awt.Color color) {
		 for(int h=0;h<bi.getHeight();h++)
		 {
			 for(int x=0;x<bi.getWidth();x=x+step)
			 bi.setRGB(x, h, color.getRGB());
		 }
		
	}

	public static BufferedImage Remove_alpha_channel(BufferedImage copy2) {
		// Get buffered image:
		BufferedImage image = copy2;

		// Remove alpha-channel from buffered image:
		BufferedImage imageRGB = 
		  new BufferedImage(
		    image.getWidth(), 
		    image.getHeight(), 
		    BufferedImage.OPAQUE); 

		Graphics2D graphics = imageRGB.createGraphics();

		graphics.drawImage(
		  image, 
		  0, 
		  0, 
		  null);
		return imageRGB;
	}
	/**
	attilax    2016年11月8日  下午7:33:44
	 * @param color
	 * @return
	 */
	public static int gray(Integer color) {
		Color clr = new Color(color);
		int R = clr.getRed();
		int G = clr.getGreen();
		int B = clr.getBlue();
		int Y = (int) (0.30 * R + 0.59 * G + 0.11 * B);
		return Y;
	}

	/**
	attilax    2016年11月8日  下午8:14:14
	 * @param dest2
	 */
	public static void setBackgroudColor_White(BufferedImage dest2) {
		setBackgroudColor(dest2,255,255,255);
		
	}

	/**
	attilax    2016年11月8日  下午8:16:24
	 * @param grayVal
	 * @return
	 */
	public static boolean isDarkColor(int grayVal) {
	if(	grayVal < 170)
		return true;
	return false;
	}

	
	public static boolean isDarkColor(int rgb,int grayVal) {
		int gray=gray(rgb);
	if(	gray <grayVal )
		return true;
	return false;
	}
	/**
	attilax    2016年11月8日  下午8:41:30
	 * @param src
	 * @param dest 
	 * @return
	 */
	public static BufferedImage clone(BufferedImage src, BufferedImage dest) {
		Graphics2D graphics = dest.createGraphics();

		 

		graphics.drawImage(

		src,

		  0,

		  0,

		  null);

		return dest;
		//return null;
	}
	
	public static BufferedImage clone(BufferedImage src ) {
		ColorModel dstCM = null;
		if (dstCM == null)
			dstCM = src.getColorModel();
		BufferedImage dest = new BufferedImage(dstCM, dstCM.createCompatibleWritableRaster(
				src.getWidth(), src.getHeight()), dstCM.isAlphaPremultiplied(),
				null);
		return clone(src,dest);
	}

	/**
	attilax    2016年11月8日  下午9:32:38
	 * @param j 
	 * @param i 
	 * @return
	 */
	public static BufferedImage new_BackgroudColor_White(int i, int j) {
		
		//imgx.clone(src);
		BufferedImage dest = new BufferedImage(i,j ,1);
		imgxBasic.setBackgroudColor_White(dest);
		//imgx.setBackgroudColor_White(dest);
		return dest;
	}
	
	@Deprecated
	public static BufferedImage new_BackgroudColor_black(int i, int j) {
		
		//imgx.clone(src);
	 
		BufferedImage dest = new BufferedImage(i,j ,BufferedImage.TYPE_INT_RGB);  //1
		imgxBasic.setBackgroudColor_black(dest);
		//imgx.setBackgroudColor_White(dest);
		return dest;
	}
	
	public static BufferedImage new_BackgroudColor_blackv2(int i, int j) {
		
		//imgx.clone(src);
	 
		BufferedImage dest = new BufferedImage(i,j ,BufferedImage.TYPE_3BYTE_BGR);  //5
		imgxBasic.setBackgroudColor_black(dest);
		//imgx.setBackgroudColor_White(dest);
		return dest;
	}
	
public static BufferedImage new_BackgroudColor_black_TYPE_3BYTE_BGR(int i, int j) {
		
		//imgx.clone(src);
		//  System.out.println(BufferedImage.TYPE_3BYTE_BGR);  //5
		BufferedImage dest = new BufferedImage(i,j ,BufferedImage.TYPE_3BYTE_BGR);  //1
		imgxBasic.setBackgroudColor_black(dest);
		//imgx.setBackgroudColor_White(dest);
		return dest;
	}

	private static void setBackgroudColor_black(BufferedImage dest) {
		setBackgroudColor(dest,0,0,0);  //set dest  r,g,b
		
	}

	public static Optional<Integer> grayV2(Optional<Integer> clr) {
	
		//Integer clr2=0;   Optional.ifPresent
		//Optional<Integer> op2 = null;
		Map m=Maps.newConcurrentMap();
		clr.ifPresent(p->{
			Integer color = clr.get();
		   int	 clr2=  gray(color);
		   m.put("v",clr2);
		});
		return   Optional.of((Integer)m.get("v"));
	}

	public static int hsv2grb(HSV hsv) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void trave_hori(BufferedImage src, ImagTraver trver) {
		int width = src.getWidth();
		int height = src.getHeight();
	
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				
					
					trver.trave( x,y);

			}
		}
		
	}

//	public static void save_png(BufferedImage bi, String out,boolean rewrite) throws FileExistEx {
//		if(!rewrite)
//		{
//			if( new File(out).exists())
//				throw new FileExistEx("");
//		}
//		//filex.createAllPath(out);
//		try {
//			ImageIO.write(bi, "png", new java.io.FileOutputStream(new File(out)));
//		} catch (IOException e) {
//		ExUtil.throwExV2(e);
//		}
//		
//	}

	/**
	attilax    2016年11月15日  下午9:00:47
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage new_BackgroudColor_Tresprt(int width, int height) {
	//	BufferedImage.type
		BufferedImage dest = new BufferedImage(width,height ,BufferedImage.TYPE_INT_ARGB);
		Color color=new Color(255,255, 255,0);
		int rgb = color.getRGB();
		 int wid=dest.getWidth();
		int ht=dest.getHeight();
		for(int w=0;w<wid;w++)
			 for(int h=0;h<ht;h++)
			 {
				 //Color color = new Color(255, 255, 255);			
					
				 dest.setRGB(w,h, rgb);
			 }
	//	imgx.setBackgroudColor_White(dest);
		//imgx.setBackgroudColor_White(dest);
		return dest;
	}
	BufferedImage src;
	/**
	attilax    2016年11月16日  下午5:22:09
	 * @param src
	 * @return 
	 */
	public imgxBasic setImg(BufferedImage src) {
		this.src=src;
		return this;
	}
	Rectangle rect;
	/**
	attilax    2016年11月16日  下午5:22:51
	 * @param rect
	 * @return
	 */
	public imgxBasic setRect(Rectangle rect) {
		this.rect=rect;
		return this;
	}

	public BufferedImage binaryImage(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
	//	BufferedImage.TYPE_INT_RGB
		BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);// 重点，技巧在这个参数BufferedImage.TYPE_BYTE_BINARY
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if(i==356 && j==478)
					System.out.println("dbg");
				int rgb = image.getRGB(i, j);
				Color clr=new Color(rgb);
				int gray_single_chanel = imgxBasic.gray(rgb);
				if (gray_single_chanel < 128)
					grayImage.setRGB(i, j, new Color(0,0,0).getRGB());
				else
					grayImage.setRGB(i, j, new Color(255,255,255).getRGB());
			}
		}
		return grayImage;
	}
	/**
	attilax    2016年11月8日  下午7:02:51
	 * @param src
	 * @param object
	 */

//	public BufferedImage cutImage(BufferedImage bi, Rectangle rect2) {
//		 
//		return null;
//	}
//	 
	public static BufferedImage mask(String f, String maskFile) {
		BufferedImage mskBufImg=imgxBasic.toImg(maskFile);
		 BufferedImage src=imgxBasic.toImg(f);
		 ImgTraver_lineScaner  trl=new ImgTraver_lineScaner(f);
		 trl.cur_Pix_Point_process_Fun_Handler=p->{
			 int clr=mskBufImg.getRGB(p.x,p.y);
			 if(imgx.isDarkColor(clr,128))
			 {
				 src.setRGB(p.x, p.y, Color.black.getRGB());
			 }
		 };
		 trl.trav();
		return src;
	}
 

	public static BufferedImage rectangle(String bigimg, java.awt.Point point, Point downPnt, Color green) {
		BufferedImage img=imgxBasic.toImg(bigimg);
		return rectangle(img,point, downPnt, green);
		
	}
	public static final Logger logger = LoggerFactory.getLogger("imgx");
	public static BufferedImage rectangle(  BufferedImage img,java.awt.Point point, Point downPnt, Color green) {
		for(int i=point.x;i<downPnt.x;i++)
		{
			img.setRGB(i, point.y, green.getRGB());
			try{
			img.setRGB(i, downPnt.y, green.getRGB());
			} catch (ArrayIndexOutOfBoundsException e) {
				//logger.error("ArrayIndexOutOfBoundsException , uppoint:"+AtiJson.toJson(point)+ ",downpnt:"+AtiJson.toJson(downPnt) ,e);
				throw e;
			}
		}
		
	//down line
		 
		for(int i=point.y;i<downPnt.y;i++)
		{
			img.setRGB(point.x, i, green.getRGB());
			try {
				img.setRGB(downPnt.x, i, green.getRGB());
			} catch (ArrayIndexOutOfBoundsException e) {
				//logger.error("ArrayIndexOutOfBoundsException , uppoint:"+AtiJson.toJson(point)+ ",downpnt:"+AtiJson.toJson(downPnt) ,e);
				throw e;
			}
			
		}
		return img;
	}

	public static void rectangle(String bigimg, List<Point> li,Rectangle rect, Color clr) {
		BufferedImage src=imgxBasic.toImg(bigimg); 
	//	BufferedImage tmp_pic_img=imgx.toImg(tmp_pic);
		for (Point point : li) {
			Point downPnt = new Point(point.x + new Double(rect.getWidth()).intValue(), point.y + new Double( rect.getHeight()).intValue());
			src=imgxBasic.rectangle(src, point, downPnt,				clr);
		}
		
	//	imgxBasic.save_overwrite(src, "D:\\0bar\\"+filex.getUUidName()+" drawRctss.jpg");
		
	}

	 
	

 
	

	 

}
