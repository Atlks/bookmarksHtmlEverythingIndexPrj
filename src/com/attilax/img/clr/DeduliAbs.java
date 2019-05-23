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
import com.attilax.io.filex;
import com.attilax.secury.MD5_BigFile;
import com.attilax.secury.geneEX;
import com.attilax.util.dirx;

/**
 * 
 * @author Administrator
 *
 */
@Deprecated
public class DeduliAbs {

	public static void main(String[] args) {
		Options opts = new Options();
		opts.addOption("dir", true, "help:mis param dir ");
		opts.addOption("gene_batfile", true, "help:mis param gene_batfile  ");

		CommandLineParser parser = new PosixParser();
		CommandLine cmd;
		try {
			cmd = parser.parse(opts, args);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e1);
		}

		try {
			fx = new filex(cmd.getOptionValue("gene_batfile"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		new DeduliAbs().traveDir("");

	}

	Set<String> md5Set = new HashSet<String>();
	Set<Long> md5Size = new HashSet<Long>();
	static filex fx;

	public void traveDir(String dir) {

		List li = new ArrayList();
		li = dirx.traveASListOrderby(dir);

		for (Object object : li) {
			// System.out.println(object);
			
			try {
				exe_single(object);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	if(fx!=null)
			fx.closeSF();
	}

	private void exe_single(Object object) throws geneEX {
		String p = object.toString();
		Long size = new File(p).length();//174241
		if(object.toString().contains("1-130H3055913.jpg"))
			System.out.println("debug");
		if (md5Size.contains(size)) {

			String md5 = MD5_BigFile.getMD5(new File(p));
			if (md5Set.contains(md5)) {
				duli_file_Process(p);
			} else
				md5Set.add(md5);
		} else {
			md5Size.add(size);
			String md5 = MD5_BigFile.getMD5(new File(p));
			md5Set.add(md5);
		}
	}

	public void duli_file_Process(String p) {
		//fx.appendLine_flush_safe(" del \"" + p + "\"");
		//System.out.println(" del \"" + p + "\"");
		throw new RuntimeException(" notimp");
	}

}
