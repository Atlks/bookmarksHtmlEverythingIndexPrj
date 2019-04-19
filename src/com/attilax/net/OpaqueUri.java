package com.attilax.net;

import java.net.URISyntaxException;

public class OpaqueUri extends URIparser {

	public OpaqueUri(String str)   {
	//	super(str);
		this.input=this.input2=str;
	//	host =this.parser. getHost( this.input);
	}
	public String getUserInfo() {
		 //"查看:18821766710@唐唐云学堂"
		int mohaoPos= input.indexOf(":");
		int atPos = input.indexOf("@");
		return input.substring(mohaoPos+1,atPos);
		
	}
	public String getHost() {
		if(new URIparser.Parser(input2). hasUserinfo(input2)) {
			int atPos = input.indexOf("@");
			int portSplitorPos = input.indexOf(":", atPos + 1);
			if(portSplitorPos==-1)  // noport
				return input2.substring(atPos+1);
			// with port
			int hostEnd = input.indexOf(":", atPos + 1);
			host = this.input.substring(atPos + 1, hostEnd);
			return  host;
		}else {
			int syegeoStart = input.indexOf("//");
			int hostEnd = input.indexOf(":", syegeoStart + 1);
			return input.substring(syegeoStart+2,hostEnd);
		}
	}
	
	
	public String getScheme() {
		int syegeoStart = input.indexOf("://");
		if(syegeoStart==-1) //is OpaqueUri 
		{
			int mohaoPos= input.indexOf(":");
			return input2.substring(0,mohaoPos);
		}
		else
		return input2.substring(0,syegeoStart);
	}


}
