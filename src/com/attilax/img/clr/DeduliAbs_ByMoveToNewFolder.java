package com.attilax.img.clr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import com.attilax.Closure;
import com.attilax.core;
import com.attilax.clr.DeduliAbs;
import com.attilax.clr.DeduliAbs_ByMoveToNewFolder;
import com.attilax.io.filex;
import com.attilax.io.pathx;
import com.attilax.secury.MD5_BigFile;
import com.attilax.secury.geneEX;
import com.attilax.util.dirx;

public class DeduliAbs_ByMoveToNewFolder extends DeduliAbs {

	private String targetDir;
	String dir;

	public static void main(String[] args) {
		DeduliAbs_ByMoveToNewFolder c = new DeduliAbs_ByMoveToNewFolder();
//		c.dir = "d:\\ati\\isheo";
//		c.targetDir = "d:\\ati\\isheo_deduli";
		
		c.dir = "d:\\ati\\pic";
		c.targetDir = "d:\\ati\\pic_deduli";
		c.traveDir(c.dir);
		System.out.println("--f");

	}

	public void duli_file_Process(String p) {
		//d:\ati\isheo\2015新款女装春季韩版娃娃领长袖钉珠气质修身显瘦蕾丝衫打底衫女-淘宝网_files\4fc80c276cb4175a26409246cfaf3627
		String ori_name = p;
		p = noDirFix(p);
		p = targetDir + "" + p;
		p=pathx.fixSlash(p);
		filex.createAllPath(p);
		filex.move(ori_name, p);
		System.out.println(" ok \"" + p + "\"");
	}

	private String noDirFix(String p) {
		p=pathx.fixSlash(p);
		dir=pathx.fixSlash(dir);
		p=p.replace(dir, "");
		return p;
	}
}
