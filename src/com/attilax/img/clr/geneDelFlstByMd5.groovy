package com.attilax.clr
import com.attilax.Closure;
import com.attilax.core
import com.attilax.collection.listUtil;
import com.attilax.io.filex;
import com.attilax.secury.MD5;
import com.attilax.util.dirx;

import static  com.attilax.core.*;

import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  Sep 1, 2014 4:28:54 PM$
 */
class geneDelFlstByMd5 {
	static Set<String> set=new HashSet<String>();
	/**
	@author attilax 老哇的爪子
	\t@since  Sep 1, 2014 4:28:54 PM$
	
	 * @param args
	 */
	public static void main(String[] args) {
	// attilax 老哇的爪子  4:28:54 PM   Sep 1, 2014 
		set.add("tt");
		List li2=new ArrayList();
		li2.addAll(set);
		filex.saveList2file(li2,"c:\\delFbymd5 O91.txt");
		println core.toJsonStrO88(li2);
//		
//		List li3=[   { } }
//			] as ArrayList

	// 	if(true)return;
	def dir="C:\\gar";
	def savename = "c:\\p6like adpic.txt";

	def n=0;
		dirx.trave(dir, new Closure<String, String>() {

			@Override
			public String execute(String f) throws Exception {
				// attilax 老哇的爪子  8:28:10 PM   Aug 23, 2014 
				n++;
				println n;
				 
				String md5=MD5.getMD5_bigfile(f);
				set.add(md5);
				return null;
				 
				
				
			}
		});
	List li= new ArrayList();
	li.addAll(   set);
	  filex.saveList2file(li,savename);
	println "---ok";
	}
//  attilax 老哇的爪子 4:28:54 PM   Sep 1, 2014   
}

//  attilax 老哇的爪子