package com.attilax.time;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;

public class TimeJna {
//	
	public static void main(String[] args) {
		 //  CLibrary.INSTANCE.printf("Hello, World\n"); 
		  int i= CLibrary.INSTANCE.time(null);
		  System.out.println(i);
		  System.out.println("---");
	}

}
