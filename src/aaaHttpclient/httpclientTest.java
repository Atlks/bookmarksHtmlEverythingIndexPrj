package aaaHttpclient;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class httpclientTest {

	public static void main(String[] args) throws IOException {
		String command="\"D:\\Program Files\\Git\\mingw64\\bin\\curl.exe\"  http://localhost:8080/jscode2session?code=xxxx";
		 command="D:\\prgrm\\bin\\curl.exe  http://localhost:8080/reg";
		 command="D:\\prgrm\\bin\\curl.exe  http://localhost:8088/list.json";
		 
			
		
		String rzt = IOUtils.toString(Runtime.getRuntime().exec(command).getInputStream(), "gbk");
		System.out.println(rzt);
	}

}
