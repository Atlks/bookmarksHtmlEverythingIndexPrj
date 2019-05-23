package com.attilax.exception;

public class MsgBoxSrv {

	public void show(String string) {
		System.out.println(string);
		throw new RuntimeException(string);
	}

	public void show(String string, Exception e) {
		throw new RuntimeException(string,e);
		
	}

}
