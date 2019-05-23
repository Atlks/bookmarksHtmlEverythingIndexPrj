package com.attilax.img.util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;

import com.attilax.exception.ExUtil;
import com.attilax.img.imgx;
import com.attilax.img.other.CantFindMatch;
import com.attilax.img.other.TemplateMaching;
import com.attilax.io.FileNotExist;
import com.attilax.io.filex;
import com.attilax.io.pathx;
import com.attilax.json.AtiJson;
import com.attilax.laisens.LaisensManger;
import com.attilax.laisens.OverTimeEx;
import com.attilax.net.URLEncoder;
import com.attilax.util.PropX;
import com.google.common.collect.Lists;

/**
 * com.attilax.img.util.ImgSearch
 * @author Administrator
 *
 */
public class ImgSearch {

	public static void main(String[] args) {
		String dir;String tmplPart;
		if(args.length==0)
		{
			dir="C:\\0workspace\\atiplat_img\\fmspaint";
			tmplPart="C:\\0workspace\\atiplat_img\\fmspaint\\n.jpg";
			
			dir="d:\\workspace\\atiplat_img\\fmspaint";
			tmplPart="D:\\000tmpl\\真正模板z.jpg";
			
			dir="F:\\00原图数据库";
			tmplPart="F:\\00原图数据库\\局部图\\1-1.jpg";
			
		}else
		{
		
		  dir = args[0].trim();
				//
		  tmplPart =args[1].trim();
		}
		
		
		String string1 = "2017-2-12";
		try {
		
			new LaisensManger().	checkOvertimeV2(string1);
		} catch (OverTimeEx e1) {
			 
			ExUtil.throwExV3(e1, "exp:"+string1);
		}
		//"C:\\0workspace\\atiplat_img\\fmspaint\\t.jpg";
		List<SearchResult> fls = new ImgSearch().searchByPart(dir, tmplPart);
		System.out.println(AtiJson.toJson(fls));
	}
	boolean inied=false;
	private List<SearchResult> searchByPart(String dbDir, String tmplPart) {
	
		
		ini();
		List<SearchResult> li = Lists.newArrayList();
		File f = new File(dbDir);
		File[] fs = f.listFiles();
		assert(fs!=null);
		if(fs==null)
			throw new RuntimeException(" dbdir lst is empty:"+dbDir);
		for (File file : fs) {
			if(file.isDirectory())
				continue;
			if (file.length() == new File(tmplPart).length())
				continue;
		 

			String bigimg = file.getAbsolutePath();
			if(bigimg.contains("rect4dbg"))
				continue;
			String resultRect4dbgFile = filex.addSuffix(bigimg, "rect4dbg");
			Point pt = null;
			try {
				pt = OpencvUtil.matchTemplate(bigimg, tmplPart, resultRect4dbgFile, Imgproc.TM_SQDIFF);
			} catch (CantFindMatch | IOException e) {
				System.out.println("=====================  OpencvUtil.matchTemplate ex,file:"+bigimg +"     ex:"+e.getMessage());
				continue;
				//e.printStackTrace();
			}catch(Exception e)
			{
				
				System.out.println("==================  OpencvUtil.matchTemplate ex,file:"+bigimg +"    ex:"+e.getMessage());
				continue;
			}

			BufferedImage part_bi = imgx.toImg(tmplPart);
			Rectangle rect = new Rectangle(new Double(pt.x).intValue(), new Double(pt.y).intValue(), part_bi.getWidth(), part_bi.getHeight());
			BufferedImage rect_part = new imgx().cutImage_retImg(bigimg, rect);
			float sml = new SimilarComparer().compare(rect_part, part_bi);
			System.out.println("---file:"+bigimg +" comparePct:"+sml);
		//	if (sml > 80)
			
			SearchResult sr=new SearchResult();
			sr.smlPct=sml;
			sr.f=bigimg;
				li.add(sr);
				
				
			// String outpic ="C:\\0img\\tmpl_out.jpg";

		}
		
		li.sort(   (SearchResult h1, SearchResult h2) -> h2.smlPct.compareTo(h1.smlPct)     );
		if(li.size()>51)
		li=li.subList(0, 50);
		return li;
	}

	public void ini() {
		if(inied)
			return;
		String prjPath = pathx.prjPath_semode();
		String f=prjPath+"/cfg.txt";
		PropX px=new PropX(f);
		String opencvlib=px.getProperty("opencvlib").trim();
		opencvlib=opencvlib.replace("%prjpath%", prjPath);
		String string = "F:\\opencv_build_x64_vc12 bin\\bin\\opencv_java2413.dll";
		// string = "E:\\opencv\\opencv_java2413.dll";
		string = "C:\\progrm\\opencv\\build\\java\\x64\\opencv_java2413.dll";
		if( !new File(opencvlib).exists())
			try {
				throw new FileNotExist(opencvlib) ;
			} catch (FileNotExist e) {
				ExUtil.throwExV2(e);
			}
		String lib2 = prjPath+"\\opencv_build_x64_vc12_bin\\opencv_core2413.dll";
		System.out.println("  --load lib:"+opencvlib +"@@over");
//		System.out.println("  --load lib:"+lib2 +"@@over");
		System.load(opencvlib);
		
	//	System.load(lib2);
		
		inied=true;
	}
}
