package com.attilax.util;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.common.base.Joiner;

public class ProcessUtil {

	
	public static String cmdUtil_exec(String cmdString) throws IOException {
		Process process = Runtime.getRuntime().exec(cmdString);
		List<String> list = IOUtils.readLines(process.getInputStream(), "utf8");
		String retstr = Joiner.on("\r\n").join(list);
		return retstr;
	}
	
	

}
