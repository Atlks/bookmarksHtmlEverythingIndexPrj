package com.attilax.img;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.attilax.dataspider.TsaolyoNetDataSpider;
import com.attilax.io.dirx;
import com.attilax.io.filex;
import com.attilax.lang.Function;

public class FileBatchDel {
	public static final Logger logger = LoggerFactory.getLogger(FileBatchDel.class);
	public static void main(String[] args) {
		

		final String oriDir="C:\\0ee";
		long longstart=System.currentTimeMillis();
	//	SerializePerson(li);
	
		Function<String,Object> closure = new Function<String,Object>() {

			@Override
			public Object apply(String f)   {
				 
				try {
				//	new File(f)
					new File(f).delete();
				} catch (Exception e) {
					new RuntimeException("--file:"+f, e);
				}
				n++;
				System.out.println("--now process count:"+String.valueOf(n));
				long end=System.currentTimeMillis();
				System.out.println("--time(ms):"+(end-longstart));
				return null;
			}

			 
		};
		new dirx().traveV2(oriDir, closure);
		System.out.println("--f");
	
	}
	public static int n=0;
}
