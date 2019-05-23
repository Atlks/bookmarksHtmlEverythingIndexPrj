package com.attilax.img.clr;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import com.attilax.Closure;
import com.attilax.io.filex;
import com.attilax.util.dirx;

public class ClrByName4Doc {

	static CommandLine cmd;

	/**
	 * -gene -dir E:\ati\doc_ext -gene_txtfile
	 * E:\ati\doc_garbFileByName\doc__garbFileByName.txt
	 *  -dir E:\ati\doc  -gene_txtfile E:\ati\doc_garbFileByName\doc__garbFileByName.txt 	  -gene_batfile G:\doc_clr_bat\clrByName.bat
	 * 
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		Options opts = new Options();
		opts.addOption("gene", false, "help:mis param dir ");
		opts.addOption("dir", true, "help:mis param dir ");
		opts.addOption("gene_txtfile", true, "help:mis param gene_batfile  ");
		opts.addOption("gene_batfile", true, "help:mis param gene_batfile  ");
		CommandLineParser parser = new PosixParser();
		cmd = parser.parse(opts, args);
		if (cmd.hasOption("gene"))
			geneBat(cmd.getOptionValue("dir"),
					cmd.getOptionValue("gene_txtfile"));
		else
			execClr(cmd.getOptionValue("dir"),
					cmd.getOptionValue("gene_txtfile"));
		System.out.println("--f");
	}

	static Set<String> set = new HashSet();

	private static void execClr(String dir, String files) {
		List<String> li = filex.read2list(files, "gbk");
		set.addAll(li);
		final filex fx = new filex(cmd.getOptionValue("gene_batfile"), "gbk",
				true);
		dirx.trave_files_noIncludeDir(dir, new Closure() {

			@Override
			public Object execute(Object arg0) throws Exception {
				String p = (String) arg0;
// del "%~dp0..\doc\0other\kp\cplus\复件 atiqtc9\float.jpg"
				String name = new File(p).getName();
				if (set.contains(name)) {
					String s = " del \"" + p+"\"";
					fx.appendLine_flush_safe(s);
					System.out.println(s);
				}

				// System.out.println("move \""+dir +"\" e:\\0");

				// System.out.println(i++);
 				return null;
			}
		});
		fx.closeSF();

	}

	private static void geneBat(String dir, String optionValue2) {
		final filex fx = new filex(optionValue2, "gbk", true);
		dirx.trave_files_noIncludeDir(dir, new Closure() {

			@Override
			public Object execute(Object arg0) throws Exception {
				String p = (String) arg0;

				String name = new File(p).getName();
				fx.appendLine_flush_safe(name);
				System.out.println(name);

				// System.out.println("move \""+dir +"\" e:\\0");

				// System.out.println(i++);
				return null;
			}
		});
		fx.closeSF();

	}

}
