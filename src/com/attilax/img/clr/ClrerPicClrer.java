package com.attilax.img.clr;

import java.util.function.Function;

import org.apache.taglibs.standard.lang.jstl.FunctionInvocation;

import com.attilax.clr.ClrerAbs;
import com.attilax.clr.ClrerPicClrer;
import com.attilax.clr.tooLongPicClrerPartImp;
import com.attilax.clr.imp.EmptyDirClrerPartImp;
import com.attilax.clr.imp.MoveExcuter;
import com.attilax.clr.imp.NoPicReconer;
import com.attilax.clr.imp.PicFmtCheckImp;
import com.attilax.clr.imp.tooMinSizePicClrerPartImp;
import com.attilax.clr.imp.tooMiniPixPicClrerPartImp;
import com.attilax.io.filex;

public class ClrerPicClrer extends ClrerAbs {

	private Object fmtCheckor;

	public static void main(String[] args) {
		ClrerPicClrer c = new ClrerPicClrer();
		// c.dir="d:\\ati\\isheo";
		c.dir = "c:\\ati\\sum_isheo_hot";// C:\ati\q2016
		//c.dir="C:\\ati\\q2016\\qa\\dct变换\\二维图像的高频和低频部分如何定义__百度知道_files";

		//
		// NoExtnameCheckerImp neImp=new NoExtnameCheckerImp();
		// c.PreProcessor=neImp;

		//
		// EmptyDirClrerPartImp ClrerPartImp = new EmptyDirClrerPartImp();
		// ClrerPartImp.dir=c.dir;
		// ClrerPartImp.targetDir="d:\\ati\\tooMiniPixPic_Pics";
		
		PicFmtCheckImp pFmtCk = new PicFmtCheckImp();
		pFmtCk.dir = c.dir;	
		c.IClrerParts.add(pFmtCk);
		
		
		tooMinSizePicClrerPartImp tmsc = new tooMinSizePicClrerPartImp();
		tmsc.dir = c.dir;
		tmsc.MovTargetDir = "c:\\ati\\00ishoHot_tooMinSize_Pics";
		tmsc.miniSize=30000;//20kb
		c.IClrerParts.add(tmsc);


		// --------and if not jpg ,yet process
		tooMiniPixPicClrerPartImp ClrerPartImp = new tooMiniPixPicClrerPartImp();
		ClrerPartImp.minH=400;
		ClrerPartImp.minW=400;
		ClrerPartImp.dir = c.dir;
		ClrerPartImp.MovTargetDir = "c:\\ati\\00ishoHot_tooMiniPixPic_Pics";
		c.IClrerParts.add(ClrerPartImp);
		//

	
		//
		tooLongPicClrerPartImp tlImpg = new tooLongPicClrerPartImp();
		tlImpg.dir = c.dir;
		tlImpg.MovTargetDir = "c:/ati/00ishoHot_tooLongMaybeAdPicClrerPartImp";

		c.IClrerParts.add(tlImpg);
		
		
	
		
		
		c.noExtFile_addExt = "jpg";
 
		c.traveDirNfile(c.dir);
		System.out.println("--f");
	}

}
