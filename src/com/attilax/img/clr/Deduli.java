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
import com.attilax.io.filex;
import com.attilax.secury.MD5_BigFile;
import com.attilax.secury.geneEX;
import com.attilax.util.dirx;

public class Deduli {

	public static void main(String[] args) throws ParseException, geneEX {
		Set<String> md5Set = new HashSet<String>();
		Set<Long> md5Size = new HashSet<Long>();
		Options opts = new Options();
		opts.addOption("dir", true, "help:mis param dir ");
		opts.addOption("gene_batfile", true, "help:mis param gene_batfile  ");

		CommandLineParser parser = new PosixParser();
		CommandLine cmd = parser.parse(opts, args);
		List li = new ArrayList();
		li = dirx.traveASListOrderby(cmd.getOptionValue("dir"));

		final filex fx;
		try {
			fx = new filex(cmd.getOptionValue("gene_batfile"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		for (Object object : li) {
			// System.out.println(object);
			try {
				String p = object.toString();
				Long size = new File(p).length();
				if (md5Size.contains(size)) {

					String md5 = MD5_BigFile.getMD5(new File(p));
					if (md5Set.contains(md5)) {
						fx.appendLine_flush_safe(" del \"" + p + "\"");
						System.out.println(" del \"" + p + "\"");
					} else
						md5Set.add(md5);
				} else {
					md5Size.add(size);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		fx.closeSF();

	}

}
