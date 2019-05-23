package com.attilax.img.clr;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import com.attilax.Closure;
import com.attilax.clr.ClrByMd5;
import com.attilax.clr.ClrX;
import com.attilax.io.filex;
import com.attilax.secury.MD5_BigFile;
import com.attilax.util.dirx;

public class ClrByMd5 extends ClrX {

	
	/**
	 *  
	 * 
	 * com.attilax.clr.ClrByMd5 -gene  -garb_dir "D:\ati\gabPic" -output_file "d:\ati\gabPic_only4pic_not4manu_q11.txt"
	 *  
	 *  ----execClr
	 * 
	 * -dir "c:\ati\foto ppl" -movToDir "c:\0000movPicDiv"   -fingerprints_lib "C:\ati\pic_gabFingers"  -output_file "e:\ati\clrpic5.bat" 
	 * 
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		Options opts = new Options();
		opts.addOption("gene", false, "help:pls input dir  ");
		opts.addOption("output_file", true, "help:pls input output ");
		opts.addOption("garb_dir", true, "help:pls input garb_dir  ");
	
		opts.addOption("dir", true, "help:pls input dir  ");
		opts.addOption("fingerprints_lib", true, "help:pls input fingerprints_lib  ");
		opts.addOption("movToDir", true, "help:pls input movToDir  ");
		CommandLineParser parser = new PosixParser();
		CommandLine cmd = parser.parse(opts, args);

		// if(cmd.hasOption("h")) {
		// // 这里显示简短的帮助信息
		// }

		long beginTime = System.currentTimeMillis();
		// String json = args[0];
		// JSONObject jo=JSONObject.fromObject(json);
		String dir = cmd.getOptionValue("dir");
		// jo.getString("dir");
		String output = cmd.getOptionValue("output_file");
		if (cmd.hasOption("gene"))
			new ClrByMd5().gene_fingerprints_file(cmd.getOptionValue("garb_dir"),
					cmd.getOptionValue("output_file"));
		else
			new ClrByMd5().execClrV2(cmd.getOptionValue("dir"),
					cmd.getOptionValue("output_file"),cmd.getOptionValue("fingerprints_lib"),cmd.getOptionValue("movToDir"));
		System.out.println("--f");
	}
	int n=0;
	/**
	 * qaa v2
	 * @param frmDir
	 * @param gene_batfile
	 * @param garb_files_md5_lib
	 */
	@SuppressWarnings("all")
	public void execClrV2(String frmDir, String gene_batfile,String garb_files_md5_lib
			,String movDir) {

		// garLib gl=new garLib();
		geneGarbLib_by_filesMD5(garb_files_md5_lib);
		  final filex fx;
		try {
			  fx=new filex(gene_batfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		dirx.trave_files_noIncludeDir(frmDir, new Closure() {

			@Override
			public Object execute(Object arg0) throws Exception {
				n++;
				System.out.println("--now processed cont:"+n);
				String p = (String) arg0;
				
				File f = new File(p);
			//	if(f.getName().contains("ppt"))
				if ( fileSize_set.contains(f.length())) {
					String md5 = MD5_BigFile.getMD5(f);
					if ( MD5set.contains(md5))
					{
						 fx.appendLine_flush_safe(" del \"" + p+"\"");
						System.out.println(" del \"" + p+"\"");
						exeMov(p,frmDir,movDir);
					}
				}else
				{ //for compati ori ,no file lenth ,only md5d figer..
					String md5 = MD5_BigFile.getMD5(f);
					if ( MD5set.contains(md5))
					{ fx.appendLine_flush_safe(" del \"" + p+"\"");
						System.out.println(" del \"" + p+"\"");
						exeMov(p,frmDir,movDir);
					}
				}

				// System.out.println("move \""+dir +"\" e:\\0");

				// System.out.println(i++);
				return null;
			}
		});
		
		try {
			fx.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	protected void exeMov(String f, String frmDir, String movDir) {
		//int n=f.indexOf(frmDir);
	//	 String rltPath=f.substring(n);
	//	 String newPath=movDir+"/"+rltPath;
		 filex.move(f, movDir, frmDir);
		
	}
	@SuppressWarnings("all")
	public void execClr(String dir, String gene_batfile,String garb_files_md5_lib
			) {

		// garLib gl=new garLib();
		geneGarbLib_by_filesMD5(garb_files_md5_lib);
		  final filex fx;
		try {
			  fx=new filex(gene_batfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		dirx.trave_files_noIncludeDir(dir, new Closure() {

			@Override
			public Object execute(Object arg0) throws Exception {
				String p = (String) arg0;
				
				File f = new File(p);
			//	if(f.getName().contains("ppt"))
				if ( fileSize_set.contains(f.length())) {
					String md5 = MD5_BigFile.getMD5(f);
					if ( MD5set.contains(md5))
					{
						 fx.appendLine_flush_safe(" del \"" + p+"\"");
						System.out.println(" del \"" + p+"\"");
					}
				}else
				{
					String md5 = MD5_BigFile.getMD5(f);
					if ( MD5set.contains(md5))
					{ fx.appendLine_flush_safe(" del \"" + p+"\"");
						System.out.println(" del \"" + p+"\"");
					}
				}

				// System.out.println("move \""+dir +"\" e:\\0");

				// System.out.println(i++);
				return null;
			}
		});
		
		try {
			fx.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	int i=0;
	
	
	public void gene_fingerprints_file(String garbFileDir, String fingerprints_file) {
		
		final filex fx = new filex(fingerprints_file, "gbk",true);
	
		dirx.trave(garbFileDir, new Closure() {
	
			@Override
			public Object execute(Object arg0) throws Exception {
				
				if (i == 0)
					fx.appendLine_flush_safe("\r\n");
				i++;
				System.out.println(" now process ing:" + String.valueOf(i));
				String f = (String) arg0;
				File fileZIP = new File(f);
				String md5 =MD5_BigFile. getMD5(fileZIP);
				System.out.println(md5);
				long endTime = System.currentTimeMillis();
				// System.out.println("MD5:"+md5+"\n time:"+((endTime-beginTime)/1000)+"s");
				fx.appendLine_flush_safe(md5 + "," + fileZIP.length()+","+f);
			 
				return null;
			}
		});
		try {
			fx.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("--f");
	}

	
	@SuppressWarnings("all")
	/**
	 * for mem only 
	 * @param garb_files_md5_lib
	 */
	private void geneGarbLib_by_filesMD5(String garb_files_md5_lib) {
		
		dirx.trave_files_noIncludeDir(garb_files_md5_lib, new Closure () {

			@Override
			public Object execute(Object arg0) throws Exception {
				 String p=(String) arg0;
				 
			
				  File f = new File(p);
				List<String> set=filex.read2list(p);
				for (String line : set) {
					String[] a=line.split(",");
					try {
					 MD5set.add(a[0]);
					} catch (Exception e) {
						// TODO: handle exception
					}
				
					try {
						 fileSize_set.add(Long.parseLong(a[1]));
					} catch (Exception e) {
						 
					}
				
				}
			
				return null;
			}
		});
		
	}
}
