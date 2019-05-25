package com.attilax.io;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import com.attilax.util.ExUtil;

public class ByteArrayInputStreamUtil {
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		//����Դ  
		ByteArrayInputStream bais = new ByteArrayInputStream("aa".getBytes("utf8"));  
		
	  System.out.println(  toReader(bais, "utf8") );	
	}

	public static  Reader toReader(ByteArrayInputStream bais,String encode) {
		// TODO Auto-generated method stub
		try {
			return new InputStreamReader(bais,encode);
		} catch (UnsupportedEncodingException e) {
			ExUtil.throwExV2(e);
		}
		return null;
	}

}
