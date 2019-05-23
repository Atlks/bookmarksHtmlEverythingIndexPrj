package com.attilax.img;

import java.awt.Image;
import java.awt.image.BufferedImage;

 //import javafx.scene.effect.GaussianBlur;



















import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import com.attilax.io.FileExistEx;
import com.attilax.io.filex;
import com.jhlabs.composite.MiscComposite;
import com.jhlabs.composite.MiscCompositeContext;
import com.jhlabs.image.AbstractBufferedImageOp;
import com.jhlabs.image.BlurFilter;
import com.jhlabs.image.ChannelMixFilter;
import com.jhlabs.image.ContourFilter;
import com.jhlabs.image.GaussianFilter;
import com.jhlabs.image.GrayscaleFilter;
import com.jhlabs.image.InvertFilter;

public class SketchImg  extends Application{

	private String f;

	/**
	 * @param f
	 */
	public SketchImg(String f) {
		this.f = f;
	}
	public SketchImg() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws FileExistEx {
		// javafx.application.Application.launch(args);
		
		String fx = "C:\\000sklt\\2.jpg";
	//	BufferedImage ori=imgx.toImg(fx);	
		BufferedImage copy =(BufferedImage) new SketchImg(fx).setGausRadis(25).filter(imgx.toImg(fx));
		 
		imgx.save_overwrite(copy,filex.addSuffix(fx, "_gray_gaus25_blend"+filex.getUUidName()));
		System.out.println("---jfx --stsared");
		System.exit(0);
		
		System.out.println("--f");
	}

	private SketchImg setGausRadis(int i) {
		this.gausRadis=i;
		return this;
	}
	/**
	attilax    2016年10月31日  下午8:42:58
	 * @param img1
	 * @param img2
	 * @return
	 */
	private BufferedImage blend(BufferedImage img1, BufferedImage img2) {
		// new MiscComposite(MiscComposite.COLOR_DODGE)  qb2
		return null;
	}

	private BufferedImage img;


//public void toGray(){
//	
//	//if(!gray){
//	//	this.gray = true;
//		for (int y = 0; y < h; y++) {
//            for (int x = 0; x < w; x++) {
//                 int c = this.data[x + y * w];
//                 int R = (c >> 16) & 0xFF;
//                 int G = (c >> 8) & 0xFF;
//                 int B = (c >> 0) & 0xFF;
//                 this.data[x + y * w] = (int)(0.3f*R + 0.59f*G + 0.11f*B); //to gray
//            }
//          }
//	}
//}
//public BufferedImage toGray(BufferedImage srcImg){      
//    return new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null).filter(srcImg, null);
//}
	int gausRadis ;

	public Image filter(BufferedImage img) throws FileExistEx {

		// if(!this.img.gray)
		// this.img.toGray(); // to gray

		BufferedImage gray_copy = new GrayscaleFilter().filter(img, null);
		imgx.save_overwrite(gray_copy, filex.addSuffix(f, "gray"));
		// img.clone();
		// Negative image

		BufferedImage	Invertcopy = new InvertFilter().filter(gray_copy, null);
		imgx.save_overwrite(Invertcopy, filex.addSuffix(f, "invert"));
		
	
		BufferedImage 	Gaussiancopy =  new GaussianFilter(gausRadis).filter(Invertcopy, null);
		imgx.save_overwrite(Gaussiancopy, filex.addSuffix(f, "blur"));
		// copy.filter(Mask.Guass); //Gaussian Blur
		//
		// for (int y = 0; y < this.img.h; y++) {
		// for (int x = 0; x < this.img.w; x++) {
		// int a = this.img.data[x + y * this.img.w];
		// int b = copy.data[x + y * this.img.w];
		//
		// int c = a + (a*b)/(255-b);
		//
		// this.img.data[x + y * this.img.w] = clamp(c);
		// }
		// }
		// return this.img;
							
	
	 //	BufferedImage copy2 =blend_jfx(gray_copy,Gaussiancopy);
		//should gasblue  above ,,ori pic backgrod
	 	BufferedImage copy2=new ImageBlendFilter().setBlendMode(ImageBlendFilter.COLOR_DODGE)
	 			.setSecondImage(Gaussiancopy ).filter( gray_copy     , null);
	 //	copy2 = new InvertFilter().filter(copy2, null);
	 	
		return copy2;
	}

	private BufferedImage blend_gazi(BufferedImage gray_copy,
			BufferedImage Gaussiancopy) {
		BufferedImage copy2=new ImageBlendFilter().setBlendMode(ImageBlendFilter.SCREEN_PIXEL)
 			.setSecondImage(gray_copy).filter(  Gaussiancopy, null);
		return copy2;
	}
	public BufferedImage blend2(BufferedImage img1,
			 
			BufferedImage img2) {
		// javafx.scene.image.Image imgjfx;
		 javafx.scene.image.Image img1_jfxfmt = SwingFXUtils.toFXImage(img1, null);  	 	
		 ImageView iv =new ImageView();
		 iv.setImage(img1_jfxfmt);
		 Group grp1=new Group(iv);
	//	 grp1.sete
		 
 		 javafx.scene.image.Image img2_gs = SwingFXUtils.toFXImage(img2, null);  	
//		 Group g2=new Group(toImageView( img_gs)); 
//		 g2.setEffect(new Blend(BlendMode.COLOR_DODGE));
//		 
	//	 Group grp=new Group(grp1,g2);
		 iv.setImage(img2_gs);
		 iv.setEffect(new Blend(BlendMode.COLOR_DODGE));
		 
		
		 
			BufferedImage copy2 =SwingFXUtils.fromFXImage(iv.getImage(), null);
		return copy2;
	}
	
	public BufferedImage blend_jfx(BufferedImage img1)
	{
		javafx.scene.image.Image img1_jfxfmt = SwingFXUtils.toFXImage(img1,
				null);
		ImageView iv1 = new ImageView(img1_jfxfmt);
		BufferedImage copy2 = SwingFXUtils.fromFXImage(img1_jfxfmt, null);
		return copy2;
	}
	
	public BufferedImage blend_jfx(String img1)
	{
		javafx.scene.image.Image img1_jfxfmt =new javafx.scene.image.Image(img1);
		ImageView iv1 = new ImageView(img1_jfxfmt);
		BufferedImage copy2 = SwingFXUtils.fromFXImage(img1_jfxfmt, null);
		return copy2;
	}

	public BufferedImage blend_jfx(BufferedImage img1,

	BufferedImage img2) {

		javafx.scene.image.Image img1_jfxfmt = SwingFXUtils.toFXImage(img1,
				null);

		ImageView iv1 = new ImageView(img1_jfxfmt);

		javafx.scene.image.Image img2_gs = SwingFXUtils.toFXImage(img2, null);
	 
		ImageView iv2 = new ImageView(img2_gs);

		// javafx.scene.image.Image imgjfx;

		// iv.setImage(img1_jfxfmt);

		Blend blend = new Blend(BlendMode.COLOR_DODGE);
		// blend.setTopInput( new ImageInput(img1_jfxfmt));
		// blend.setBottomInput(new ImageInput(img2_gs) );
 
	    iv2.setEffect(blend);
	 	
	 	
		Group grp = new Group(iv1, iv2);
	    //  grp.setEffect(blend);
	//	new JFXPanel();
		Scene scene = new Scene(grp);
		
		// scene.snapshot(img);
		WritableImage img = new WritableImage(img1.getWidth(), img1.getHeight());
		scene.snapshot(img);
		javafx.scene.image.Image imgFnl=img;
	//	imgFnl.save // haosyo zinen export swing img then export jfx img mage save meth.
		// SwingFXUtils.fromFXImage(arg0, arg1)

		// Group g2=new Group(toImageView( img1_jfxfmt));
		// g2.
		// g2.setEffect(new Blend(BlendMode.COLOR_DODGE));
		//
		// Group grp=new Group(grp1,g2);
		// iv.setImage(img2_gs);

		// FXImaging imager = new FXImaging();

		BufferedImage copy2 = SwingFXUtils.fromFXImage(img, null);
		return copy2;
	}
	public BufferedImage blend3(BufferedImage img1,
			 
			BufferedImage img2) {
		
		 javafx.scene.image.Image img2_gs = SwingFXUtils.toFXImage(img2, null);  
 		 ImageView iv2 =new ImageView(img2_gs);
 		 
 		 
 		 
		// javafx.scene.image.Image imgjfx;
		 javafx.scene.image.Image img1_jfxfmt = SwingFXUtils.toFXImage(img1, null);  		 
		 
		 ImageView iv =new ImageView();
		 iv.setImage(img1_jfxfmt);
		 iv.setClip(iv2);
		 iv.setEffect(new Blend(BlendMode.COLOR_DODGE));
		 
 		
//		 Group g2=new Group(toImageView( img_gs)); 
//		 g2.setEffect(new Blend(BlendMode.COLOR_DODGE));
//		 
	//	 Group grp=new Group(grp1,g2);
	//	 iv.setImage(img2_gs);
		
		 
		
		 
			BufferedImage copy2 =SwingFXUtils.fromFXImage(iv.getImage(), null);
		return copy2;
	}


	/**
	attilax    2016年10月31日  下午7:28:46
	 * @param img_gs
	 * @return
	 */
	private Node toImageView(javafx.scene.image.Image img_gs) {
		ImageView iv =new ImageView();
		 iv.setImage(img_gs);
		return iv;
	}

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage paramStage) throws Exception {
	 
		String fx = "C:\\000sklt\\2.jpg";
		BufferedImage ori=imgx.toImg(fx);
	//	BufferedImage copy=	new ContourFilter().filter(ori, null);
		
		BufferedImage copy;//=(BufferedImage) new SketchImg(fx).filter(ori);
		copy=new SketchImg(fx).blend_jfx(fx);
		imgx.save_overwrite(copy,filex.addSuffix(fx, "_test copy_ori is str"));
		System.out.println("---jfx --stsared");
		System.exit(0);
		
	}

}
