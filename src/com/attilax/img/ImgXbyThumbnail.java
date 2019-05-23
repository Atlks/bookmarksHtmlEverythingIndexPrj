package com.attilax.img;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;

import com.attilax.Closure;
import com.attilax.img.other.AFont;
import com.attilax.io.filex;
import com.attilax.util.dirx;
import com.google.common.collect.Maps;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Coordinate;
import net.coobird.thumbnailator.geometry.Positions;

/**
 * com.attilax.img.ImgXbyThumbnail
 * @author Administrator
 *
 */
public class ImgXbyThumbnail {

	public static void main(String[] args) throws IOException {
	//	t1();
		
	//	t();
//		dirx.trave("d:\\gialenimg", new Closure () {
//
//			@Override
//			public Object execute(Object f2) throws Exception {
//				String f=(String) f2;
//				System.out.println(f);
//				new ImgXbyThumbnail().scale4clr(f, f, 320);
//				return null;
//			}
//		});
		Map m=Maps.newConcurrentMap();
	//	m.put("bg", value)
	//	new ImgXbyThumbnail()
		
		long startTime=System.currentTimeMillis();   //获取开始时间   

		//doSomeThing();  //测试的代码段   
		ExecutorService urlPool = Executors.newFixedThreadPool(4);
	

		String ori = "C:\\000oil\\bush_blu.png";
		String im1 = "C:\\000oil\\cywe_img.png";		
		String im2 = "C:\\000oil\\din_img.png";
		double fontScale = 0.6;
		
		AFont  aft1=new AFont();
		aft1.point=new Point(60, y);
		BufferedImage img1 = imgx.toImg(im1);
		img1= Thumbnails.of(img1).scale(fontScale).asBufferedImage();  
		int font_margin_top=7;
		int font1_x = 60;
		int ft1_y = 0;
		int span=7;		
		int ft2_y=2;
		
		
		
		ft1_y=ft1_y+font_margin_top;
		ft2_y=ft2_y+font_margin_top;
		int ft2_x=font1_x+img1.getWidth()+span;
		
		
		BufferedImage img2 = imgx.toImg(im2);
	
		img2= Thumbnails.of(img2).scale(fontScale).asBufferedImage();  
		Coordinate coordinate1 = new Coordinate(font1_x, ft1_y);
		
		Coordinate coordinate2 = new Coordinate(ft2_x, ft2_y);
		Builder<File> bldr = Thumbnails.of(new File(ori)).watermark(coordinate1, img1, 1f).watermark(coordinate2, img2, 1f);

		bldr.size(300, 60).keepAspectRatio(true).toFile(new File(filex.addSuffix(ori, filex.getUUidName())));
		long endTime=System.currentTimeMillis(); //获取结束时间   

		System.out.println("程序运行时间： "+(endTime-startTime)+"ms");  
		System.out.println("--0f");
	}

	private static void t() throws IOException {
		deBorder("d:/img/bar.jpg","d:/img/bar2.jpg",150,77);
		deBorder("d:/img/wechat.jpg","d:/img/wechat2.jpg",150,77);
		deBorder("d:/img/they.jpg","d:/img/they2.jpg",150,77);
	}

	private static void t1() throws IOException {
		String jpg = "c:\\0415_105633_801.jpg";
		String deboxJpg = jpg + "_debox.jpg";
		int border = 1;
		// BufferedImage extends java.awt.Image
		// ImgXbyThumbnail.deBorder(jpg, deboxJpg, border);
		//
		String para = "李哥,上海市闵行区吴浦路15号,13512345678,艾龙,艾伦的地址地址地址地址,13640685012";
		String cfg = "153-102,106-194,181-235,509-103,475-184,512-248";
		watermark("E:\\exp.jpg", para, "E:\\exp2.jpg", cfg);
		System.out.println("----f");
	}

	/**
	 * 缩放图片，并在图片右下角添加文字
	 * 
	 * @param cfg
	 *
	 * @throws IOException
	 */
	// @Ignore
	// @Test
	public static void watermark(String ori, String txt, String output,
			String cfg) throws IOException {
		List<String> txts=new ArrayList<String>();
		String[] a = txt.split(",");
		String sender = a[0];
		String sendAddr = a[1];
		String sendTel = a[2];
		
		String recvr = a[3];
		String recvr_add = a[4];
		String recvr_tel = a[5];
		String[] cfg_a = cfg.split(",");
		String senderinfo = cfg_a[0];
		String[] send_a = senderinfo.split("-");
		String add_post = cfg_a[1];
		String[] add_post_a = add_post.split("-");
		Coordinate senderPostion = new Coordinate(Integer.parseInt(send_a[0]),
				Integer.parseInt(send_a[1]));
		Coordinate senderAddPostion = new Coordinate(
				Integer.parseInt(add_post_a[0]),
				Integer.parseInt(add_post_a[1]));
		Coordinate sender_tel_Postion = getTelPostion(cfg, 2);
		Coordinate p3 = getTelPostion(cfg, 3);
		Coordinate p4 = getTelPostion(cfg, 4);
		Coordinate p5 = getTelPostion(cfg, 5);
		BufferedImage bi;

		BufferedImage sender_bi = getTxtImg(sender, output);
		BufferedImage sender_add_bi = getTxtImg(sendAddr, output);
		BufferedImage sender_tel_bi = getTxtImg(sendTel, output);
		
		BufferedImage rcv_bi = getTxtImg(recvr, output);
		BufferedImage rcv_add_bi = getTxtImg(recvr_add, output);
		BufferedImage rcv_tel_bi = getTxtImg(recvr_tel, output);
		// String ori = "E:\\test\\1a.jpg";
		// String output = "E:\\test\\1a_front_1.jpg";
		Builder<File> bldr = Thumbnails.of(new File(ori)).scale(1)
				.watermark(senderPostion, sender_bi, 0.5f)
				.watermark(senderAddPostion, sender_add_bi, 0.4f)
				.watermark(sender_tel_Postion, sender_tel_bi, 0.4f)
					.watermark(p3, rcv_bi, 0.4f)
						.watermark(p4, rcv_add_bi, 0.4f)
							.watermark(p5, rcv_tel_bi, 0.4f);
		bldr
				.toFile(new File(output));
	}

	private static Coordinate getTelPostion(String cfg, int i) {
		String[] cfg_a = cfg.split(",");
		String senderinfo = cfg_a[i];
		String[] send_a = senderinfo.split("-");
		Coordinate senderPostion = new Coordinate(Integer.parseInt(send_a[0]),
				Integer.parseInt(send_a[1]));
		return senderPostion;
	}

	private static BufferedImage getTxtImg(String txt, String output)
			throws IOException {
		BufferedImage bi;
		bi = new BufferedImage(500, 30, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bi.createGraphics();
		// g.setBackground(Color.BLACK);
		// g.setBackground(Color.TRANSLUCENT);
		Font font = new Font("仿宋", Font.BOLD, 20);
		g.setFont(font);
		g.setColor(Color.BLUE);
		// g.drawRect(0, 0, 264, 64);
		// String txt = "野马红尘";

		g.drawString(txt, 1, 20);
		// Graphics2D.drawChars
		ImageIO.write(bi, "png", new File(output + "water.png"));
		return bi;
	}

	public static void deBorder(String jpg, String deboxJpg, int border)
			throws IOException {
		Image img = ImageIO.read(new File(jpg));

		int wid = img.getWidth(null) - (border * 2);
		int hit = img.getHeight(null) - (border * 2);
		Thumbnails.of(jpg)
		// 从原图哪里开始裁剪 裁减多少
				.sourceRegion(Positions.CENTER, wid, hit)
				// 新图的大小
				.size(wid, hit).toFile(deboxJpg);
	}

	public static void deBorder(String jpg, String deboxJpg, int Widborder,int HitBoder)
			throws IOException {
		Image img = ImageIO.read(new File(jpg));

		int wid = img.getWidth(null) - (Widborder * 2);
		int hit = img.getHeight(null) - (HitBoder * 2);
		Thumbnails.of(jpg)
		// 从原图哪里开始裁剪 裁减多少
				.sourceRegion(Positions.CENTER, wid, hit)
				// 新图的大小
				.size(wid, hit).toFile(deboxJpg);
	}
	
	
	public static void deBorderLeftNrit(String jpg, String deboxJpg, int border)
			throws IOException {
		Image img = ImageIO.read(new File(jpg));

		
		int wid1= img.getWidth(null) - (border );
		int wid = img.getWidth(null) - (border * 2);
	
		int hit=img.getHeight(null);
	//	int hit = img.getHeight(null) - (border * 2);
		
//		Coordinate senderPostion = new Coordinate(Integer.parseInt(send_a[0]),
	//			Integer.parseInt(send_a[1]));
		Thumbnails.of(jpg)
		// 从原图哪里开始裁剪 裁减多少
				.sourceRegion(Positions.CENTER, wid, hit)
			//	.sourceRegion(Positions.CENTER_RIGHT, wid, hit)
				// 新图的大小
				.size(wid, hit).toFile(deboxJpg);
	}
	
	public static void deBorderTop_n_Bottom(String jpg, String deboxJpg, int border)
			throws IOException {
		Image img = ImageIO.read(new File(jpg));

		int wid = img.getWidth(null) - (border * 2);
		int hit = img.getHeight(null) - (border * 2);
		Thumbnails.of(jpg)
		// 从原图哪里开始裁剪 裁减多少
				.sourceRegion(Positions.CENTER, wid, hit)
				// 新图的大小
				.size(wid, hit).toFile(deboxJpg);
	}
	
	
	public   void scale4clr(String jpg, String deboxJpg, int width)
			throws IOException {
		Image img = ImageIO.read(new File(jpg));

		int width2 = img.getWidth(null);
		if(width2<width+2)
		{System.out.println("--skip:"+jpg);
			return;
		}
		//int wid = width2 - (border * 2);
		double scale_perst=(double)width/(double)width2;
		double nowhit=img.getHeight(null);
		double nowwit=scale_perst*width2;
		int nowhit_haf=(int) img.getHeight(null)/2;
	//	int hit = img.getHeight(null) - (border * 2);
		Thumbnails.of(jpg).scale(scale_perst).sourceRegion(Positions.TOP_LEFT, (int) width2, nowhit_haf).outputFormat("jpg").outputQuality(0.5).toFile(deboxJpg);
	}




}
