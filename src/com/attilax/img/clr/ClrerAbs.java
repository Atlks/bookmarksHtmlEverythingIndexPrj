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
import com.attilax.clr.IClrerPart;
import com.attilax.clr.IPreProcessor;
import com.attilax.clr.imp.NoPicReconer;
import com.attilax.ex.CantBeNull;
import com.attilax.ex.FileNotExistEx;
import com.attilax.ex.FmtEx;
import com.attilax.io.filex;
import com.attilax.secury.MD5_BigFile;
import com.attilax.secury.geneEX;
import com.attilax.util.dirx;

/**
 * 
 * @author Administrator
 *
 */
public class ClrerAbs {
	
	
	private String targetDir;
	String dir;
	
	public List<IClrerPart> IClrerParts=new ArrayList<IClrerPart>();
	public  IPreProcessor PreProcessor;
//	public List<Executer> Executers=new ArrayList<Executer>();

	public static void main(String[] args) {
	   
	

	}

	Set<String> md5Set = new HashSet<String>();
	Set<Long> md5Size = new HashSet<Long>();
	static filex fx;
	public  String noExtFile_addExt;
	public void traveDirNfile(String dir) {
		this.dir=dir;

		List li = new ArrayList();
		li = dirx.traveASListOrderbyPath_wzDir(dir);
		int i=0;

		for (Object object : li) {
			// System.out.println(object);
			i++;
			if(new File(object.toString()).isDirectory())
				continue;
			try {
				String ext=filex.getExtNameV2(object.toString());
				String target = object.toString();
				if(ext==null) {
					  target = object.toString()+"."+this.noExtFile_addExt;
					filex.rename(object.toString(), target);
				}
				traveDirNfile_single(target);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("now:"+i);

		}
	if(fx!=null)
			fx.closeSF();
	}

	private void traveDirNfile_single(Object object) throws geneEX {
		
		
		if(object==null)
			throw new CantBeNull("traveDirNfile_single param,should be a file ");
		if(PreProcessor!=null)
		object=PreProcessor.exe(object);
		for (IClrerPart clrPart : IClrerParts) {
			try {
				if(clrPart.isGabFile(object))
				{
				//	exe_gab_file_process(object);
					clrPart.exe(object);
					return;
				}
			} catch (FileNotExistEx e) {
				//maybe first iclr moved 
				System.out.println(e.getMessage());
				break;
				
			} catch (FmtEx e) {
				break;//maybe not pic fmt
			}
		}
//		String p = object.toString();
//		Long size = new File(p).length();//174241
//		if(object.toString().contains("1-130H3055913.jpg"))
//			System.out.println("debug");
//		if (md5Size.contains(size)) {
//
//			String md5 = MD5_BigFile.getMD5(new File(p));
//			if (md5Set.contains(md5)) {
//				duli_file_Process(p);
//			} else
//				md5Set.add(md5);
//		} else {
//			md5Size.add(size);
//			String md5 = MD5_BigFile.getMD5(new File(p));
//			md5Set.add(md5);
//		}
//		throw new RuntimeException(" no imp");
	}

//	private void exe_gab_file_process(Object object) {
//		for (Executer executer : Executers) {
//			executer.exe(object);
//		}
//	}
 

}
