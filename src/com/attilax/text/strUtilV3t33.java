package com.attilax.text;

public class strUtilV3t33 {

	public static String[] SplitByNone(String s) {
		char[] ca=s.toCharArray();
		String[] sa=new String[ca.length];
		int n=0;
		for (char c : ca) {
			sa[n]=String.valueOf(c);
			n++;
		}
		return sa;
	}

	public static boolean contain(String fileIllcharList, String filenameOriChar) {
		  String[] 	as = strUtilV3t33.SplitByNone(fileIllcharList);

			for (final String s : as) {

				if (s.equals(filenameOriChar))
					return true;

			}
		return false;
	}

}
