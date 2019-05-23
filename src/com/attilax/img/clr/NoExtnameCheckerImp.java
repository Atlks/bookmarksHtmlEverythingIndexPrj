package com.attilax.img.clr;

import java.io.File;

import com.attilax.clr.IPreProcessor;
import com.attilax.io.filex;

public class NoExtnameCheckerImp implements IPreProcessor {
	
	public static void main(String[] args) {
		 String ext=filex.getExtName("c:\\aa\\bb");
		 System.out.println(ext);
	}

	@Override
	public Object exe(Object object) {
	 String ext=filex.getExtName(object.toString());
	 //pb28
	 if( new File((object.toString())).isDirectory())
			 return object;
	
	 if(ext.equals("")) {
		 String newName = object.toString()+".jpg";
		 System.out.println(newName);
		 filex.rename(object.toString(),    newName  );
		return newName;
	}
	return object;
	}

	 

}
