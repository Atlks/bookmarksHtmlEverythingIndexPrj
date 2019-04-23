package aOPtool;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.attilax.fileTrans.SShFileUtilV3t33;
import com.attilax.net.URIparser;
import com.attilax.time.jsonTimeFmtr;
import com.attilax.time.timeUtilV2t33;
import com.attilax.util.CfgUtil;
import com.google.common.collect.Maps;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;

@SuppressWarnings("all")
public class sshFileDownSvrsByCfgfile {

	// java -cp /lib/*:/targetBookmark/classes aOPtool.sshFileDownSvrs //
 	public static void main(String[] args) throws Exception {
		// root:pdm#1921
		 
 		String remtFile = "/tt/www/redis5/redis.conf";
 		 remtFile = "/var/opt/gitlab/redis/redis.conf";
 		 remtFile = "/usr/lib/redis.conf";
 		
 		
		String path=FileUtils.readFileToString(new File("H:\\0db\\dev36svr.txt")).trim()+remtFile;
		String savedir = "   g:/0downfileDir36svr/usrLib";
		String cmdString =""+ path+"  "+savedir;
		
		//cmdString=CfgUtil.parse(cmdString);
		System.out.println(cmdString);
		
		
//		String file = args[0];
//		String urlString = FileUtils.readFileToString(new File(file));
//		urlString = urlString + args[1];
//		String[] argsx = StringUtils.splitByWholeSeparator(urlString + " " + args[2], " ", 2);
		String[] argsx= StringUtils.splitByWholeSeparator(cmdString," ");
		sshFileDownSvrs.main(argsx);

		
		String localfileString=savedir.trim()+"/"+FilenameUtils.getName(remtFile);
		System.out.println(localfileString);
		System.out.println(FileUtils.readFileToString(new File(localfileString)));
		// t(args);
	}

	private static void t(String[] args) throws URISyntaxException, Exception, IOException {
		String cmdString = "http://r 2/0db_tmpTable/prod_token.txt  g:/0downfileDir3/";
		 
		// /tt/www/admin-tomcat9/webapps/admin.war.0402 g:/0downfileDir1/ ";
		// args=StringUtils.splitByWholeSeparator(cmdString, " ",2);
		// StringUtils.split
		System.out.println(JSON.toJSON(args));
		System.out.println(timeUtilV2t33.Now_CST());
		System.out.println("will sleep 7sec ...");
		// Thread.sleep(3000);
		String url;
		url = args[0];
		URIparser uri = new URIparser(args[0]);
		String remoteFile = uri.getPath();
		String localTargetDirectory = args[1];
		String filename = FilenameUtils.getName(remoteFile);
		String pathname = localTargetDirectory + File.separator + filename;
		Thread showFilesize = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {

						receveFilesize(pathname);
						Thread.sleep(3000);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}

		});
		showFilesize.start();

		SShFileUtilV3t33 c = new SShFileUtilV3t33().setcfg(url);

		Connection connection = c.conn();
		SCPClient scpClient = c.getScpclient(connection);
		// c.upload(connection, localFIle, scppath);

		if (!new File(localTargetDirectory).exists())
			FileUtils.forceMkdir(new File(localTargetDirectory));
		scpClient.get(remoteFile, localTargetDirectory);
		// .put(fileName, scpPath, "0777");
		System.out.println(timeUtilV2t33.Now_CST());

		System.out.println("--f");
		showFilesize.stop();
		receveFilesize(pathname);
	}

	private static void receveFilesize(String pathname) {
		Map map = Maps.newConcurrentMap();
		map.put("f", pathname);
		map.put("kb", (float) new File(pathname).length() / 1000f);
		map.put("mb", (float) new File(pathname).length() / 1000000f);
//System.out.println("recevie file size:" + new File(pathname).length() / 1000 + "kb");
		System.out.println(JSON.toJSONString(map));
	}
}
