package com.attilax.util;

import java.util.List;

import com.attilax.parser.Token;

public class CantFindEx extends Exception {

	public CantFindEx(String process) {
		  super( process);
	}

}
