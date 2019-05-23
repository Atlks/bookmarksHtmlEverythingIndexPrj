package com.attilax.img.clr;

import java.util.Set;
import java.util.function.Function;

import com.attilax.io.dirx;
import com.attilax.io.filex;
import com.attilax.json.AtiJson;
import com.google.common.collect.Sets;

public class ExtGroupby {
	public static int n=0;
	public static void main(String[] args) {
		String d="C:\\ati\\q2016";
		d="C:\\ati\\gab_mini_js";
		Set<String> set=Sets.newConcurrentHashSet();
		
		Function fun=(f)->{
			String ext=filex.getExtName((String) f);
			set.add(ext);
			n++;
			System.out.println(n);
			return f;
			
		};
		dirx.traveV3(d, fun);
		System.out.println(AtiJson.toJson(set));

	}

}
