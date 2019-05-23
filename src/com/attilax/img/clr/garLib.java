/**
 * @author attilax 老哇的爪子
\t@since  Aug 23, 2014 8:26:13 PM$
 */
package com.attilax.img.clr;
import com.attilax.Closure;
import com.attilax.core;
import com.attilax.clr.garLib;
import com.attilax.io.filex;
import com.attilax.secury.MD5;
import com.attilax.util.dirx;

import static  com.attilax.core.*;

import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  Aug 23, 2014 8:26:13 PM$
 */
public class garLib {

	Set<String> set=new HashSet<String>();
	/**
	@author attilax 老哇的爪子
	\t@since  Aug 23, 2014 8:26:51 PM$
	
	 * @param f
	 */
	public garLib(String f) {
		//  attilax 老哇的爪子 8:26:51 PM   Aug 23, 2014   
		dirx.trave(f, new Closure<String, String>() {

			@Override
			public String execute(String f) throws Exception {
				// attilax 老哇的爪子  8:28:10 PM   Aug 23, 2014 
				
				{ byte[] ba=filex.readImageData(f);
				String md5=MD5.getMD5_RE(ba);
				set.add(md5);
				return null;
				 } 
				
				
			}
		});
	}

	/**
	@author attilax 老哇的爪子
	\t@since  Aug 23, 2014 8:26:13 PM$
	
	 * @param args
	 */
	public static void main(String[] args) {
		// attilax 老哇的爪子  8:26:13 PM   Aug 23, 2014 

		{	String f="C:\\gar";
		
		String s="C:\\gar\\2.jpg";
		garLib gl=new garLib(f);
		System.out.println(gl.isGarfile(s));
		System.out.println(garLib.$().isGarfile(s));
		}

	}
	//  attilax 老哇的爪子 8:26:13 PM   Aug 23, 2014   

	/**
	@author attilax 老哇的爪子
	\t@since  Aug 23, 2014 8:34:35 PM$
	
	 * @return
	 */
	public static garLib $() {
		// attilax 老哇的爪子  8:34:35 PM   Aug 23, 2014 
		
		{ 
		return new garLib("C:\\gar");
		 } 
		
		
	}

	/**
	@author attilax 老哇的爪子
	\t@since  Aug 23, 2014 8:30:12 PM$
	
	 * @param s
	 * @return
	 */
	public boolean isGarfile(String f) {
		// attilax 老哇的爪子  8:30:12 PM   Aug 23, 2014 
		
		{ byte[] ba=filex.readImageData(f);
		String md5=MD5.getMD5_RE(ba);
		
		return set.contains(md5);
		 } 
		
		
	}
	
	
 
	public boolean isGarfile(byte[] ba) {
		// attilax 老哇的爪子  8:30:12 PM   Aug 23, 2014 
		
		{  
		String md5=MD5.getMD5_RE(ba);
		
		return set.contains(md5);
		 } 
		
		
	}
}

//  attilax 老哇的爪子