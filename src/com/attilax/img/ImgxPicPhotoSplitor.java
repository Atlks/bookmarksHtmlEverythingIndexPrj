package com.attilax.img;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.attilax.Closure;
import com.attilax.io.filex;
import com.attilax.util.dirx;

public class ImgxPicPhotoSplitor {

	public static void main(String[] args) throws IOException {

		// t1();

		t2();
		// File file = new File("D:\\img\\wechat.jpg");
		// BufferedImage image = ImageIO.read(file);
		// System.out.println(imgx.containsWhiteLine(image) );

	}

	private static void t2() {
		String strPath = "D:\\gialenimg";
		final String tag = "_picx";
		dirx.trave(strPath, new Closure() {

			@Override
			public Object execute(Object arg0) throws Exception {
				String f = (String) arg0;
				try {
				
					BufferedImage image = ImageIO.read(new File(f));
					if (!f.contains(tag))
						if (imgx.containsWhiteLine(image))

						{

							rename(f);
						}
				} catch (Exception e) {
					System.out.println("err:"+f);
					e.printStackTrace();
				}
				
				return null;
			}

			private void rename(String f) {
				String path = new File(f).getParent();
				String base = filex.getFileName_noExtName(f);
				String ext = filex.getExtName(f);

				String newFile = path + "\\" + base + tag + "." + ext;
				System.out.println(newFile);
				filex.rename(f, newFile);
				// new File(f).

			}

		});
	}

	private static void t1() throws IOException {
		File file = new File("D:\\img\\wechat.jpg");
		BufferedImage image = ImageIO.read(file);
		System.out.println(image.getHeight());
		int w = 200;
		int h = 700;
		int rgb = image.getRGB(w, h);
		PixLine pl = imgx.getPixLine(image, 700);
		if (imgx.isWhiteLine(pl))
			System.out.println("--wit line");

		int[] rgbs = new int[image.getWidth()];
		// rgb2==t sRGB colorspace.
		// rgb== aRgb colorspace..
		int[] rgbs2 = image.getRGB(0, 700, image.getWidth() - 1, 2, rgbs, 0,
				image.getWidth());
		Color pixelColor = new Color(rgb);
		int r = pixelColor.getRed();
		int g = pixelColor.getGreen();
		int b = pixelColor.getBlue();
		System.out.println(rgb);
		System.out.println(r + ":" + g + ":" + b);
	}

	/**
	 * 2 * 根据BufferedImage获取图片RGB数组 3 * @param bfImage 4 * @return 5
	 */
	public static int[][] getImageGRB(BufferedImage bfImage) {
		int width = bfImage.getWidth();
		int height = bfImage.getHeight();
		int[][] result = new int[height][width];
		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				// 使用getRGB(w,
				// h)获取该点的颜色值是ARGB，而在实际应用中使用的是RGB，所以需要将ARGB转化成RGB，即bufImg.getRGB(w,
				// h) & 0xFFFFFF。
				result[h][w] = bfImage.getRGB(w, h) & 0xFFFFFF;
			}
		}
		return result;
	}

}
