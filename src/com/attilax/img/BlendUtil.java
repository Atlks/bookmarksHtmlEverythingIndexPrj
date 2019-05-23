package com.attilax.img;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;

import com.attilax.exception.ExUtil;
import com.attilax.javafx.javafxUtil;

public class BlendUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static BufferedImage blend_COLOR_DODGE(String topImg,

	String lowImg) {
	
		javafx.scene.image.Image topimg_jfxfmt = toImg(topImg);

		javafx.scene.image.Image lowImg_jfxFmt = toImg(lowImg);

		
		Blend blend = new Blend(BlendMode.COLOR_DODGE);
		blend.setTopInput(new ImageInput(topimg_jfxfmt));
		blend.setBottomInput(new ImageInput(lowImg_jfxFmt));

		Group grp = new Group();

		grp.setEffect(blend);

//		WritableImage img = new WritableImage((int) topimg_jfxfmt.getWidth(),
//				(int) topimg_jfxfmt.getHeight());
		// scene.snapshot(img);
		WritableImage img2 = grp.snapshot(new SnapshotParameters(), null);

	//	StackPane root = javafxUtil.getStackPaneFrmImg(img2);

		BufferedImage copy2 =  SwingFXUtils.fromFXImage(img2, null);
		copy2 = imgx.Remove_alpha_channel(copy2);
		return copy2;
	}

	private static javafx.scene.image.Image toImg(String topImg) {
		try {
			javafx.scene.image.Image img1_jfxfmt = new javafx.scene.image.Image(
					new File(topImg).toURI().toURL().toString());
			return img1_jfxfmt;
		} catch (MalformedURLException e) {
			ExUtil.throwExV2(e);
		}
		return null;

	}

}
