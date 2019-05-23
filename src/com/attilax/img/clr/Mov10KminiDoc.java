package com.attilax.img.clr;

import java.io.File;
import java.util.Set;
import java.util.function.Function;

import com.attilax.io.dirx;
import com.attilax.io.filex;
import com.attilax.json.AtiJson;
import com.google.common.collect.Sets;

public class Mov10KminiDoc {
	public static	int n=0;
	public static void main(String[] args) {
		
		String[] docExt=new String[]{"csv","conf","txt","reg","zip","rar","SQL","doc","wps","sql","docx","xls","xlsx","ppt","pptx"};
		Set<String> set=Sets.newConcurrentHashSet();
		for (String string : docExt) {
			set.add(string.trim().toLowerCase());
		}
	
		 
				 


		 


		String d="C:\\ati\\q2016";
		String targetDir="C:\\ati\\q2016_miniDoc";
		//Set<String> set=Sets.newConcurrentHashSet();
		
		Function fun=(f)->{
			String ext=filex.getExtName((String) f).toLowerCase().trim();
			File file= new File(f.toString());
			if(set.contains(ext) && file.length()<11000)
			{
				
				filex.move(f.toString(), targetDir, d);
			}
			n++;
			System.out.println(n);
			return f;
			
		};
		dirx.traveV3(d, fun);
	//	System.out.println(AtiJson.toJson(set));
	
	}

}
