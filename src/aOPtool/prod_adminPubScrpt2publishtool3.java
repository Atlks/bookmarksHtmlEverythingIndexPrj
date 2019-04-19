package aOPtool;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
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

public class prod_adminPubScrpt2publishtool3 {

	final static Logger logger = Logger.getLogger(prod_adminPubScrpt2publishtool3.class);

	public static void main(String[] args) throws Exception {

		
	 
				
	//	http://101.132.148.11:9000/admin 
  
		SShFileUtilV3t33 c = new SShFileUtilV3t33().setcfg(FileUtils.readFileToString(new File("H:\\0db\\prod93.txt")));
		Connection connection = c.conn();
		logger.info(" conned ok");
		//Session session = connection.openSession();

	 //	  uploadWar(c, connection);

	//	killtomcat(connection);
		rebootTomcat(connection);

		System.out.println("--f");

	}

	private static void uploadWar(SShFileUtilV3t33 c, Connection connection) throws IOException, ConnEx, AuthEx, createSCPClientEx, uploadFileEx {
		// bek
		String cmd_bek = "mv  /tt/www/admin-tomcat9/webapps/admin.war    /tt/www/admin-tomcat9/backup/admin.war41."+ new SimpleDateFormat("yyyy-MM-dd.HHmmss").format(new java.util.Date());;
		logger.info(cmd_bek);
		List<String> result2 =SShFileUtilV3t33. exec(cmd_bek, connection);
		System.out.println(Joiner.on("\r\n").join(result2));

		// upload
		String localFIle = "G:\\0ttapi\\tt-api\\com-tt-admin\\target\\admin.war";
		String scppath = "/tt/www/admin-tomcat9/webapps";
		
		logger.info("upload file:" + localFIle + " " + scppath);
		c.scpClient = c.getScpclient(connection);
		  c.upload(connection, localFIle, scppath);

		// rename  dep
//		String cmd = " mv  /tt/www/admin-tomcat9/webapps/tt-yxt-0.0.1-SNAPSHOT.war  /tt/www/admin-tomcat9/webapps/admin.war ";
//		logger.info(cmd);
//		List<String> result =SShFileUtilV3t33. exec(cmd, connection);
//		System.out.println(Joiner.on("\r\n").join(result));
	}

	private static void rebootTomcat(Connection connection) throws IOException {
		killtomcat(connection);
	
		
		//restart
		
		///usr/local/jenkins-tomcat8   /usr/local/jenkins-tomcat8/bin/startup.sh 
		String JAVA_HOME="export JAVA_HOME=/tt/www/jdk1.8.0_191"; 
		String cmd_startTomcat=JAVA_HOME+";"+" /tt/www/admin-tomcat9/bin/startup.sh   ";
		logger.info(cmd_startTomcat);
		System.out.println(SShFileUtilV3t33.exec(cmd_startTomcat, connection.openSession(),3));
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String cmd3 = "ps -ef|grep  tomcat"  ;
		logger.info(cmd3);
		System.out.println(SShFileUtilV3t33.exec(cmd3, connection));
		 
	}

	private static void killtomcat(Connection connection) {
		String kewword_forkillpid = "admin-tomcat9";
		try {  //ingone mode  
		processUtil.	killTomcat(connection, kewword_forkillpid);
		} catch (Exception e) {
			logger.info("", e);
		}
	}

	 
}
