package aOPtool;

import java.awt.LinearGradientPaint;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.attilax.fileTrans.AuthEx;
import com.attilax.fileTrans.ConnEx;
import com.attilax.fileTrans.SShFileUtilV3t33;
import com.attilax.fileTrans.createSCPClientEx;
import com.attilax.fileTrans.uploadFileEx;
import com.attilax.util.PrettyUtil;
import com.attilax.util.shellUtilV2t33;
import com.google.common.base.Joiner;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class preSvr_adminPubScrpt2publishtool2 {

	final static Logger logger = Logger.getLogger(preSvr_adminPubScrpt2publishtool2.class);

	public static void main(String[] args) throws IOException, ConnEx, AuthEx, createSCPClientEx, uploadFileEx {

		// chg act tag
		// http://101.132.148.11:9000/admin
		String sprcfg = "G:\\0ttapi\\tt-api\\com-tt-admin\\src\\main\\resources\\application.yml";
		System.out.println(sprcfg);
		String tString = FileUtils.readFileToString(new File(sprcfg));
		String[] linesArr=tString.split("\r\n");
		String line17=linesArr[16];		
		line17 = line17.replace("preprod", "pre");
		linesArr[16]=line17;	
		tString=Joiner.on("\r\n").join(linesArr);
		FileUtils.write(new File(sprcfg), tString);
		
		
		//maven
//mavenExec();
			 
		
		
		// IOUtils.re
//		List<String> list = IOUtils.readLines(inputStream, "utf8");
//		String retstr = Joiner.on("\r\n").join(list);
//		System.out.println(retstr);
		String host = "101.132.148.11:22:root:pdm#1921";
		System.out.println("****************"+host);
		 try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		SShFileUtilV3t33 c = new SShFileUtilV3t33().setcfg(host);

		Connection connection = c.conn();
		logger.info(" conned ok");
		// Session session = connection.openSession();

//	 	  uploadWar(c, connection);
//
//	 	 try {
//				Thread.sleep(7000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
 		 rebootTomcat(connection);

		System.out.println("--f");

	}

	private static void mavenExec() throws IOException {
		String mvnString="Z:\\soft\\apache-maven-3.5.4-bin\\apache-maven-3.5.4\\bin\\mvn.cmd";
				String cmdString = mvnString+"    -f G:\\0ttapi\\tt-api\\com-tt-admin\\pom_prod.xml install";
				System.out.println(cmdString);
				Process process = Runtime.getRuntime().exec(cmdString);
				InputStream inputStream = process.getInputStream();
				// 消费掉IO流，防止程序被阻塞卡死
				printStream(inputStream,"stdstream:>");
		
				// 消费掉IO流，防止程序被阻塞卡死
				printStream(process.getErrorStream(),"errstream:>");
	}

	private static void printStream(InputStream inputStream, String outtag) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				InputStreamReader isr;
				try {
					isr = new InputStreamReader(inputStream, "utf8");

					BufferedReader br = new BufferedReader(isr);
					String line = null;

					while ((line = br.readLine()) != null)
						System.out.println(outtag+""+line);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();
	}

	private static void uploadWar(SShFileUtilV3t33 c, Connection connection)
			throws IOException, ConnEx, AuthEx, createSCPClientEx, uploadFileEx {
		// bek
		String cmd_bek = "mv  /tt/www/admin-tomcat9/webapps/admin.war    /tt/www/admin-tomcat9/backup/admin.war.41"
				+ new SimpleDateFormat("yyyy-MM-dd.HHmmss").format(new java.util.Date());
		;
		logger.info(cmd_bek);
		List<String> result2 = SShFileUtilV3t33.exec(cmd_bek, connection);
		System.out.println(Joiner.on("\r\n").join(result2));

		// upload
		String localFIle = "G:\\0ttapi\\tt-api\\com-tt-admin\\target\\admin.war";
		String scppath = "/tt/www/admin-tomcat9/webapps";

		logger.info("upload file:" + localFIle + " " + scppath);
		c.scpClient = c.getScpclient(connection);
		c.upload(connection, localFIle, scppath);

		// rename dep
//		String cmd = " mv  /tt/www/admin-tomcat9/webapps/tt-yxt-0.0.1-SNAPSHOT.war  /tt/www/admin-tomcat9/webapps/admin.war ";
//		logger.info(cmd);
//		List<String> result =SShFileUtilV3t33. exec(cmd, connection);
//		System.out.println(Joiner.on("\r\n").join(result));
	}

	private static void rebootTomcat(Connection connection) throws IOException {
		String kewword_forkillpid = "admin-tomcat9";
		try {
			killTomcat(connection, kewword_forkillpid);
		} catch (Exception e) {
			logger.info("", e);
		}

		// restart

		/// usr/local/jenkins-tomcat8 /usr/local/jenkins-tomcat8/bin/startup.sh
		String JAVA_HOME = "export JAVA_HOME=/tt/www/jdk1.8.0_191";
		String cmd_startTomcat = JAVA_HOME + ";" + " /tt/www/admin-tomcat9/bin/startup.sh   ";
		logger.info(cmd_startTomcat);
		System.out.println(SShFileUtilV3t33.exec(cmd_startTomcat, connection.openSession(), 3));

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String cmd3 = "ps -ef|grep  tomcat";
		logger.info(cmd3);
		System.out.println(SShFileUtilV3t33.exec(cmd3, connection));

	}

	private static void killTomcat(Connection connection, String kewword_forkillpid) throws IOException {
		List<Integer> pids = processUtil.showGrepProcessList(connection, kewword_forkillpid);

		// kill pids
		for (int pid : pids) {
			processUtil.killPid(pid, connection);
		}
		;
	}

}
