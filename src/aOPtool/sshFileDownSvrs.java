package aOPtool;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.attilax.fileTrans.SShFileUtilV3t33;
import com.attilax.time.jsonTimeFmtr;
import com.attilax.time.timeUtilV2t33;
import com.google.common.collect.Maps;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;

@SuppressWarnings("all")
public class sshFileDownSvrs {

	// java -cp /lib/*:/targetBookmark/classes aOPtool.sshFileDownSvrs
	// 101.132.148.11:22:root:pdm#1921 /tt/www/admin-tomcat9/webapps/admin.war.0402
	// /0downfileDir1/
	// 101.132.148.11:22:root:pdm#1921 /0db_tmpTable/prod_token.txt g:/0downfile

	public static void main(String[] args) throws Exception {

		String cmdString = "http://root:pdm#1921@101.132.148.11:22:  /0db_tmpTable/prod_token.txt  g:/0downfileDir2/ ";
	//	cmdString = "101.132.148.11:22:root:pdm#1921  /tt/www/admin-tomcat9/webapps/admin.war.0402  g:/0downfileDir1/ ";
		 args=StringUtils.splitByWholeSeparator(cmdString, " ");
		System.out.println(JSON.toJSON(args));
		System.out.println(timeUtilV2t33.Now_CST());
		System.out.println("will sleep 7sec ...");
		Thread.sleep(3000);
		String host;// = "101.132.148.11:22:root:pdm#1921";
		host = args[0];
		String remoteFile = args[1];
		String localTargetDirectory = args[2];

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {

						String pathname = localTargetDirectory + File.separator + FilenameUtils.getName(remoteFile);
						Map map = Maps.newConcurrentMap();
						map.put("kb", (float) new File(pathname).length() / 1000f);
						map.put("mb", (float) new File(pathname).length() / 1000000f);
						System.out.println("recevie file size:" + new File(pathname).length() / 1000 + "kb");
						System.out.println(JSON.toJSONString(map));
						Thread.sleep(3000);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}
		}).start();

		SShFileUtilV3t33 c = new SShFileUtilV3t33().setcfg(host);

		Connection connection = c.conn();
		SCPClient scpClient = c.getScpclient(connection);
		// c.upload(connection, localFIle, scppath);

		if (!new File(localTargetDirectory).exists())
			FileUtils.forceMkdir(new File(localTargetDirectory));
		scpClient.get(remoteFile, localTargetDirectory);
		// .put(fileName, scpPath, "0777");
		System.out.println(timeUtilV2t33.Now_CST());

		System.out.println("--f");
	}

}