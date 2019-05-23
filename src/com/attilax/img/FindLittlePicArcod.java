package com.attilax.img;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.attilax.io.filex;

public class FindLittlePicArcod {

	private static Logger log = LoggerFactory.getLogger("FindLittlePicArcod");

	public static void main(String[] args) throws IOException {
		new FindLittlePicArcod().t();

		//
		// imgx.cutImage(big, "c:\\00cutimg\\" + filex.getUUidName() + ".jpg",
		// 0,
		// 0, 200, 300);
		log.info("--f");
		System.out.println("--f");
		// System.out.println(ImagePHash.newx().distance_img(big, lit));
	}

	int n = 0;

	private void t() throws IOException {
		String big = "c:\\t.jpeg";
		String lit = "c:\\lit.jpg";
		// diff==484

		BufferedImage lit_img = ImageIO.read(new File(lit));

		int w2 = lit_img.getWidth();
		int h2 = lit_img.getHeight();

		imgx imgx = new imgx();
		BufferedImage img = ImageIO.read(new File(big));
		int width = img.getWidth();
		int h = img.getHeight();
		ImagePHash imagePHash = new ImagePHash(8, 8);
		ImageReader ImageReader1 = imgx.ImgReader(new File(big));

		String ext = filex.getExtName(big);
		imgx.save(lit_img, "c:\\0tmpPic\\" + filex.getUUidName() + "." + ext, ext);
		ExecutorService es=Executors.newFixedThreadPool(3);
		lab: for (int i = 0; i < width; i = i + 5) {
			for (int j = 0; j < h; j = j + 5) {
				// BufferedImage tmp =
				// (BufferedImage) imgx.cutImage_retImg(big,
				// i, j, w2, h2);
				n++;
				int x = i;
				int y = j;
				int nowCount=n;
				Runnable ra = () -> {

					process(lit_img, w2, h2, imgx, imagePHash, ImageReader1, nowCount,
							ext, x, y);
				};
				es.execute(ra);
				

			}
		}
		es.shutdown();
	}

	private int process(BufferedImage lit_img, int w2, int h2, imgx imgx,
			ImagePHash imagePHash, ImageReader ImageReader1, int n, String ext,
			int i, int j) {
		Rectangle rect = new java.awt.Rectangle(i, j, w2, h2);
		BufferedImage tmp = imgx.cutImg(rect, ImageReader1);

		int dis = imagePHash.distance(tmp, lit_img);

		if (dis < 5) {
			log.info(" count:" + String.valueOf(n) + " dis:"
					+ String.valueOf(dis) + " rect:" + String.valueOf(i) + "_"
					+ String.valueOf(j));
			// break lab;
		}
		if (dis < 10) {
			String out = "c:\\0tmpPic\\" + filex.getUUidName() + "." + ext;
			imgx.save(tmp, out, ext);
			log.info(" count:" + String.valueOf(n) + " dis:"
					+ String.valueOf(dis) + " rect:" + String.valueOf(i) + "_"
					+ String.valueOf(j) + " file:" + out);
		}

		int cnt = n;
		if (cnt % 100 == 0) {
			String count = String.valueOf(cnt);

			log.info(" count:" + count + " dis:" + String.valueOf(dis)
					+ " rect:" + String.valueOf(i) + "_" + String.valueOf(j));
		}
		return n;
	}

}
