package aOPtool;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSON;
import com.attilax.fileTrans.SShFileUtilV3t33;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;

public class sshFileDownSvrs {
	
	
	
	//   101.132.148.11:22:root:pdm#1921  /0db_tmpTable/prod_token.txt  g:/0downfile

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println(JSON.toJSON(args) );
		Thread.sleep(7000);
		String host ;//= "101.132.148.11:22:root:pdm#1921";
		host=args[0];String remoteFile=args[1];
		SShFileUtilV3t33 c = new SShFileUtilV3t33().setcfg(host);
		

		Connection connection = c.conn();
		SCPClient scpClient = c.getScpclient(connection);
	//	c.upload(connection, localFIle, scppath);
		
		String localTargetDirectory=args[2];
		if( !new File(localTargetDirectory).exists())
			FileUtils.forceMkdir(arg0);
		scpClient.get(remoteFile, localTargetDirectory);
	//	.put(fileName, scpPath, "0777");
		System.out.println("--f");
	}

}
