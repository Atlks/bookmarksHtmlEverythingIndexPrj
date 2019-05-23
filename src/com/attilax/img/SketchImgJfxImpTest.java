package com.attilax.img;

import java.awt.Image;
import java.awt.image.BufferedImage;

//import javafx.scene.effect.GaussianBlur;

import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import com.attilax.exception.ExUtil;
import com.attilax.io.FileExistEx;
import com.attilax.io.filex;
import com.attilax.javafx.javafxUtil;
import com.jhlabs.composite.MiscComposite;
import com.jhlabs.composite.MiscCompositeContext;
import com.jhlabs.image.AbstractBufferedImageOp;
import com.jhlabs.image.BlurFilter;
import com.jhlabs.image.ChannelMixFilter;
import com.jhlabs.image.ContourFilter;
import com.jhlabs.image.GaussianFilter;
import com.jhlabs.image.GrayscaleFilter;
import com.jhlabs.image.InvertFilter;

public class SketchImgJfxImpTest extends Application {

	private String f;

	/**
	 * @param f
	 */
	public SketchImgJfxImpTest(String f) {
		this.f = f;
	}

	public SketchImgJfxImpTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws FileExistEx {
		javafx.application.Application.launch(args);
		System.out.println("--f");
	}

	/**
	 * attilax 2016年10月31日 下午8:42:58
	 * 
	 * @param img1
	 * @param img2
	 * @return
	 */
	private BufferedImage blend(BufferedImage img1, BufferedImage img2) {
		// new MiscComposite(MiscComposite.COLOR_DODGE) qb2
		return null;
	}

	private BufferedImage img;

	public BufferedImage blend2(BufferedImage img1,

	BufferedImage img2) {
		// javafx.scene.image.Image imgjfx;
		javafx.scene.image.Image img1_jfxfmt = SwingFXUtils.toFXImage(img1,
				null);
		ImageView iv = new ImageView();
		iv.setImage(img1_jfxfmt);
		Group grp1 = new Group(iv);
		// grp1.sete

		javafx.scene.image.Image img2_gs = SwingFXUtils.toFXImage(img2, null);
		// Group g2=new Group(toImageView( img_gs));
		// g2.setEffect(new Blend(BlendMode.COLOR_DODGE));
		//
		// Group grp=new Group(grp1,g2);
		iv.setImage(img2_gs);
		iv.setEffect(new Blend(BlendMode.COLOR_DODGE));

		BufferedImage copy2 = SwingFXUtils.fromFXImage(iv.getImage(), null);
		return copy2;
	}

	@Deprecated
	// jeig color to red..
	public BufferedImage blend_jfx(BufferedImage img1) {
		javafx.scene.image.Image img1_jfxfmt = SwingFXUtils.toFXImage(img1,
				null); // jeig haide bianhong
		ImageView iv1 = new ImageView(img1_jfxfmt);
		BufferedImage copy2 = SwingFXUtils.fromFXImage(img1_jfxfmt, null);
		return copy2;
	}

	public BufferedImage blend_jfx(BufferedImage img1, BufferedImage img2)

	{
		return img2;
	}

	/**
	 * jeig mabie red...
	 * 
	 * @param img1
	 * @return
	 */
	public BufferedImage blend_jfx(String img1) {
		// iv.setImage(new Image(file.toURI().toURL().toString(),true));
		javafx.scene.image.Image img1_jfxfmt = null;
		try {
			img1_jfxfmt = new javafx.scene.image.Image(new File(img1).toURI()
					.toURL().toString());
		} catch (MalformedURLException e) {
			ExUtil.throwExV2(e);
		}
		ImageView iv1 = new ImageView(img1_jfxfmt);
		BufferedImage copy2 = SwingFXUtils.fromFXImage(img1_jfxfmt, null);
		return copy2;
	}

	Scene scene;

	public BufferedImage blend_jfx(String topImg,

	String lowImg) {
		Blend blend = new Blend(BlendMode.COLOR_DODGE);
		javafx.scene.image.Image img1_jfxfmt = null;
		try {
			img1_jfxfmt = new javafx.scene.image.Image(new File(topImg).toURI()
					.toURL().toString());
		} catch (MalformedURLException e) {
			ExUtil.throwExV2(e);
		}

		ImageView topImg_imgview = new ImageView(img1_jfxfmt);
  //      topImg_imgview.setEffect(blend);

		javafx.scene.image.Image lowImg_jfxFmt = null;
		try {
			lowImg_jfxFmt = new javafx.scene.image.Image(new File(lowImg)
					.toURI().toURL().toString());
		} catch (MalformedURLException e) {
			ExUtil.throwExV2(e);
		}

		ImageView lowImg_Imgview = new ImageView(lowImg_jfxFmt);
	// 	lowImg_Imgview.setEffect(blend);
		// javafx.scene.image.Image imgjfx;

		// iv.setImage(img1_jfxfmt);

	
		  blend.setTopInput( new ImageInput(img1_jfxfmt));
	  blend.setBottomInput(new ImageInput(lowImg_jfxFmt) );

		// iv2.setEffect(blend);

		Group grp =new Group();
				//new Group( lowImg_Imgview,topImg_imgview);
	 	 grp.setEffect(blend);
		// new JFXPanel();
		// grp.setf

		// scene.snapshot(img);
		// grp.
	//	lowImg_Imgview.setFitWidth(300);
	//	lowImg_Imgview.setFitHeight(300);
		// Display image on screen
		// imageView.setImage(wImage);
//		String orcimg = "http://docs.oracle.com/javafx/"  
//		        + "javafx/images/javafx-documentation.png";
//		javafx.scene.image.Image image = new javafx.scene.image.Image(orcimg); 
//		System.out.println(orcimg);
//	    ImageView imageView = new ImageView();  
//        imageView.setImage(image);  
//		StackPane root = new StackPane();
//		root.getChildren().add(imageView);

		
		WritableImage img = new WritableImage((int) img1_jfxfmt.getWidth(),
				(int) img1_jfxfmt.getHeight());
	//	  scene.snapshot(img);
		  WritableImage img2=	  grp.snapshot(new SnapshotParameters(), null);  
		  
	 	
		 javafx.scene.image.Image imgFnl=img2;
		 StackPane root=javafxUtil.getStackPaneFrmImg(imgFnl);
		  scene = new Scene(root, 500, 700);
		// imgFnl.save // haosyo zinen export swing img then export jfx img mage
		// save meth.
		// SwingFXUtils.fromFXImage(arg0, arg1)

		// Group g2=new Group(toImageView( img1_jfxfmt));
		// g2.
		// g2.setEffect(new Blend(BlendMode.COLOR_DODGE));
		//
		// Group grp=new Group(grp1,g2);
		// iv.setImage(img2_gs);

		// FXImaging imager = new FXImaging();

		BufferedImage copy2 = null ;
		copy2=SwingFXUtils.fromFXImage(imgFnl,   null );
		copy2=imgx.Remove_alpha_channel(copy2);
		return copy2;
	}

	public BufferedImage blend3(BufferedImage img1,

	BufferedImage img2) {

		javafx.scene.image.Image img2_gs = SwingFXUtils.toFXImage(img2, null);
		ImageView iv2 = new ImageView(img2_gs);

		// javafx.scene.image.Image imgjfx;
		javafx.scene.image.Image img1_jfxfmt = SwingFXUtils.toFXImage(img1,
				null);

		ImageView iv = new ImageView();
		iv.setImage(img1_jfxfmt);
		iv.setClip(iv2);
		iv.setEffect(new Blend(BlendMode.COLOR_DODGE));

		// Group g2=new Group(toImageView( img_gs));
		// g2.setEffect(new Blend(BlendMode.COLOR_DODGE));
		//
		// Group grp=new Group(grp1,g2);
		// iv.setImage(img2_gs);

		BufferedImage copy2 = SwingFXUtils.fromFXImage(iv.getImage(), null);
		return copy2;
	}

	/**
	 * attilax 2016年10月31日 下午7:28:46
	 * 
	 * @param img_gs
	 * @return
	 */
	private Node toImageView(javafx.scene.image.Image img_gs) {
		ImageView iv = new ImageView();
		iv.setImage(img_gs);
		return iv;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage paramStage) throws Exception {

		String fx = "C:\\000sklt\\2.jpg";
		BufferedImage ori = imgx.toImg(fx);
		// BufferedImage copy= new ContourFilter().filter(ori, null);

		BufferedImage copy;// =(BufferedImage) new SketchImg(fx).filter(ori);
		//new SketchImgJfxImpTest(fx).
		copy =BlendUtil.blend_COLOR_DODGE("C:\\000sklt\\2_blur.jpg",				"C:\\000sklt\\2_gray.jpg");
				//blend_jfx("C:\\000sklt\\2_blur.jpg",				"C:\\000sklt\\2_gray.jpg");

		imgx.save_overwrite(
				copy,
				filex.addSuffix(fx,
						"_blue gray blend jfx algo" + filex.getUUidName()));
		System.out.println("---jfx --stsared");

		// StackPane root = new StackPane();
		// root.getChildren().add(imageView);
		// Scene scene = new Scene(root, 300, 250);
		paramStage.setTitle("Image Write Test");
		if(scene==null)
			throw new RuntimeException("scene is null");
		paramStage.setScene(scene);
		paramStage.show();
		// System.exit(0);

	}

}
