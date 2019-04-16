package com.attilax.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class CfgUtil {

	public static String parse(String cmdString) throws IOException {
		int pos_start=cmdString.indexOf("${");
		int pos_end=cmdString.indexOf("}");
		String cfgfileString=cmdString.substring(pos_start+2,pos_end);
		String tString=FileUtils.readFileToString(new File(cfgfileString));
		return null;
	}

}
