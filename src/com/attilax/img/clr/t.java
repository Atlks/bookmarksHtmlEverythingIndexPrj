/**
 * @author attilax 老哇的爪子
\t@since  Aug 23, 2014 8:10:45 PM$
 */
package com.attilax.img.clr;
import com.attilax.core;
import com.attilax.io.filex;
 

import com.attilax.secury.MD5;

import static  com.attilax.core.*;

import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  Aug 23, 2014 8:10:45 PM$
 */
public class t {

	/**
	@author attilax 老哇的爪子
	\t@since  Aug 23, 2014 8:10:45 PM$
	
	 * @param args
	 */
	public static void main(String[] args) {
		// attilax 老哇的爪子  8:10:45 PM   Aug 23, 2014 

		{
			String f="C:\\gar\\2.jpg";
			byte[] ba=filex.readImageData(f);
			String md5=MD5.getMD5_RE(ba);
			System.out.println(md5);
		}

	}
	//  attilax 老哇的爪子 8:10:45 PM   Aug 23, 2014   
}

//  attilax 老哇的爪子