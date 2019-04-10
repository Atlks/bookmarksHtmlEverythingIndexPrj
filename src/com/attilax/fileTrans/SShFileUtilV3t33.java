package com.attilax.fileTrans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.attilax.net.URIparser;
import com.attilax.util.PrettyUtil;
import com.attilax.util.shellUtilV2t33;
import com.csvreader.CsvReader;
import com.google.common.base.Joiner;
import com.hp.hpl.sparta.xpath.ThisNodeTest;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * 功能概述：上传文件 <br>
 * 创建时间：2013-5-7上午10:44:09 <br>
 * 修改记录： <br>
 * 
 * @author xiaoliang.li <br>
 */
/**
 * oa1
 * 
 * @author attilax
 * 
 */
public class SShFileUtilV3t33 {
//	 static org.apache.log4j.Logger logger = Logger.getLogger(SShFileUtilV3t33.class);
	/**
	 * 
	 * 2014/11/02 00:00:53 OSS DEBUG [com.attilax.search.UploadFileUtil] -
	 * ==============开始上传====C:\img0\1101_232822_269.jpg==============
	 * (com.attilax.search.UploadFileUtil.upLoadFile.54) 2014/11/02 00:00:53 OSS
	 * DEBUG [com.attilax.search.UploadFileUtil] - 加入SCP实现远程传输文件
	 * (com.attilax.search.UploadFileUtil.upLoadFile.59) 2014/11/02 00:00:53 OSS
	 * DEBUG [com.attilax.search.UploadFileUtil] - 开始连接
	 * (com.attilax.search.UploadFileUtil.upLoadFile.65) 2014/11/02 00:00:55 OSS
	 * DEBUG [com.attilax.search.UploadFileUtil] - 登陆远程服务器root,xxx
	 * (com.attilax.search.UploadFileUtil.upLoadFile.70) 2014/11/02 00:00:56 OSS
	 * DEBUG [com.attilax.search.UploadFileUtil] - 建立scp客户端
	 * (com.attilax.search.UploadFileUtil.upLoadFile.82) 2014/11/02 00:00:56 OSS
	 * DEBUG [com.attilax.search.UploadFileUtil] - 开始上传文件到服务器
	 * (com.attilax.search.UploadFileUtil.upLoadFile.88) 2014/11/02 00:01:31 OSS
	 * DEBUG [com.attilax.search.UploadFileUtil] - 关闭连接
	 * (com.attilax.search.UploadFileUtil.upLoadFile.94) 2014/11/02 00:01:31 OSS
	 * DEBUG [com.attilax.search.UploadFileUtil] - 上传完成
	 * (com.attilax.search.UploadFileUtil.upLoadFile.98)
	 * 
	 * @param args
	 * @throws IOException
	 * @throws uploadFileEx
	 * @throws createSCPClientEx
	 * @throws AuthEx
	 * @throws ConnEx
	 * @throws Exception
	 */
	public static void main(String[] args) throws IOException, ConnEx, AuthEx, createSCPClientEx, uploadFileEx {

		SShFileUtilV3t33 c = new SShFileUtilV3t33().setScpAddress("47.100.12.36").setScpPort("22").setUsername("root")
				.setPassword("dKttdev.321");

		Connection connection = c.conn();
		logger.info(" conned ok");
		Session session = connection.openSession();

		// bek
		String cmd_bek = " mv  /adir/api.war  /adirbek/api325b.war ";
		logger.info(cmd_bek);
		List<String> result2 = exec(cmd_bek, session);
		System.out.println(Joiner.on("\r\n").join(result2));

		// upload
		String scppath = "/adir";
		String localFIle = "H:\\gitCode\\tt-api\\com-tt-yxt\\target\\tt-yxt-0.0.1-SNAPSHOT.war";
		logger.info("upload file:" + localFIle + " " + scppath);
		c.scpClient = c.getScpclient(connection);
		// c.upload(connection, localFIle, scppath);

		// rename
		String cmd = " mv  /adir/tt-yxt-0.0.1-SNAPSHOT.war  /adir/api.war ";
		logger.info(cmd);
		List<String> result = exec(cmd, connection.openSession());
		System.out.println(Joiner.on("\r\n").join(result));

		
		 String kewword_forkillpid = "admin-tomcat8";
		//get 
		String cmd3 = "ps -ef|grep  tomcat8"  ;
		logger.info(cmd3);
		List<String> result3 = exec(cmd3, connection.openSession());
		String ps_rzt_csv = Joiner.on("\r\n").join(result3);
		System.out.println(ps_rzt_csv);
		  readAsCsv(ps_rzt_csv);
		logger.info("------------------");

		System.out.println("\r\n");
		List<Map> tab = shellUtilV2t33.toTableNoHeadMode_ByMultiSpace(ps_rzt_csv);
		
		int pid = getPid(tab, kewword_forkillpid, 1);
		logger.info("---getpid:"+String.valueOf(pid));
		
		;
		System.out.println(PrettyUtil.showListMap(tab));
		
		
		//kill pid
		String cmd4 = "kill "+String.valueOf(pid)  ;
		logger.info(cmd4);
		logger.info("kill ret:"+exec(cmd4, connection.openSession()));
		
		
		///usr/local/jenkins-tomcat8   /usr/local/jenkins-tomcat8/bin/startup.sh 
		String JAVA_HOME="export JAVA_HOME=/usr/java/jdk1.8.0_77"; 
		String cmd_startTomcat=JAVA_HOME+";"+" /usr/local/jenkins-tomcat8/bin/startup.sh ";
		logger.info(cmd_startTomcat);
		System.out.println(exec(cmd_startTomcat, connection.openSession(),3));

		System.out.println("--f");

	}

	private static int getPid(List<Map> tab, String kewword, int pidIndex) {
		Map map = grepBykewword(tab, kewword);
		return Integer.parseInt(map.get("index"+pidIndex).toString().trim());
	}

	private static Map grepBykewword(List<Map> tab, String kewword) {
		for (Map map : tab) {
			String string = JSON.toJSONString(map);
			if (string.contains(kewword))
				return map;
		}
		return null;
	}

	private static void readAsCsv(String ps_rzt_csv) throws IOException {
		// 创建CSV读对象
	//	CsvReader.parse(arg0)
		CsvReader csvReader = new CsvReader(IOUtils.toInputStream(ps_rzt_csv), '\t',Charset.defaultCharset());
				//CsvReader.parse(ps_rzt_csv);
		 

		// 读表头
		// csvReader.readHeaders();
		while (csvReader.readRecord()) {
			System.out.println("--getColumnCount:" + csvReader.getColumnCount());
			System.out.println(csvReader.get(5));

			// 读一整行
			// System.out.println(csvReader.getRawRecord());
			// 读这行的某一列
			// System.out.println(csvReader.get("Link"));
		}
	}

	public void upload(Connection connection, String localFIle, String scppath)
			throws ConnEx, AuthEx, createSCPClientEx, uploadFileEx, IOException {

		this.setScpPath(scppath);

		upload(localFIle, connection);
	}
	
	public static List<String> exec(String cmd, Session session,int waitSec) throws IOException {
		session.execCommand(cmd);
try {
	Thread.sleep(waitSec*1000);
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		List<String> result = new ArrayList<>();
		out2li(result, session);

		logger.info(" coll err out ok");
		InputStream is = new StreamGobbler(session.getStderr());// 获得标准输出流
		BufferedReader brs = new BufferedReader(new InputStreamReader(is));
		for (String line = brs.readLine(); line != null; line = brs.readLine()) {
			result.add(line);
		}

		return result;
	}
	
	public static List<String> exec(String cmd, Connection conn) throws IOException {
		Session session=conn.openSession();
		session.execCommand(cmd);

		List<String> result = new ArrayList<>();
		out2li(result, session);

	//	logger.info(" coll err out ok");
		InputStream is = new StreamGobbler(session.getStderr());// 获得标准输出流
		BufferedReader brs = new BufferedReader(new InputStreamReader(is));
		for (String line = brs.readLine(); line != null; line = brs.readLine()) {
			result.add(line);
		}

		return result;
	}

	private static List<String> exec(String cmd, Session session) throws IOException {
		session.execCommand(cmd);

		List<String> result = new ArrayList<>();
		out2li(result, session);

	//	logger.info(" coll err out ok");
		InputStream is = new StreamGobbler(session.getStderr());// 获得标准输出流
		BufferedReader brs = new BufferedReader(new InputStreamReader(is));
		for (String line = brs.readLine(); line != null; line = brs.readLine()) {
			result.add(line);
		}

		return result;
	}

//	private static List<String> exec(  Session session) throws IOException {
//	
//	}

	private static void out2li(List<String> result, Session session) throws IOException {
		InputStream is = new StreamGobbler(session.getStdout());// 获得标准输出流
		BufferedReader brs = new BufferedReader(new InputStreamReader(is));
		for (String line = brs.readLine(); line != null; line = brs.readLine()) {
			result.add(line);
		}
	}

	public String getScpPath() {
		return scpPath;
	}

	public SShFileUtilV3t33 setScpPath(String scpPath) {
		this.scpPath = scpPath;
		;
		return this;
	}

	public String getScpAddress() {
		return scpAddress;
	}

	public SShFileUtilV3t33 setScpAddress(String scpAddress) {
		this.scpAddress = scpAddress;
		;
		return this;
	}

	public String getScpPort() {
		return scpPort;
	}

	public SShFileUtilV3t33 setScpPort(String scpPort) {
		this.scpPort = scpPort;
		;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public SShFileUtilV3t33 setUsername(String username) {
		this.username = username;
		;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public SShFileUtilV3t33 setPassword(String password) {
		this.password = password;
		return this;
	}

	// scp文件目录
	public String scpPath;// =
							// WebSrvUtil.srv("scpPath").trim();//"/data01/javadev/";//
	// scp ip地址
	public String scpAddress;// =WebSrvUtil.srv("scpAddress").trim();//"192.168.100.220";
	// //
	// scp 端口号
	public String scpPort;// =
	// WebSrvUtil.srv("scpPort").trim();//"222";//WebSrvUtil.srv("scpPort").trim();
	// scp 用户名
	public String username;// = WebSrvUtil.srv("username").trim();//"javadev";
	// scp 密码
	public String password;// =
	// WebSrvUtil.srv("password").trim();//"javadev123!@#";//WebSrvUtil.srv("password").trim();
	private final static Logger logger = Logger.getLogger(SShFileUtilV3t33.class);

	/**
	 * 方法功能说明：通过scp上传任何文件
	 * 
	 * @param fileName
	 * @throws Exception
	 * @return 返回"200"则上传成功，"500" 则上传失败出现异常 ，"506"则登陆远程服务器失败
	 * @throws ConnEx
	 * @throws AuthEx
	 * @throws createSCPClientEx
	 * @throws uploadFileEx
	 * @throws IOException
	 */
	public String upLoadFile(String fileName) throws ConnEx, AuthEx, createSCPClientEx, uploadFileEx, IOException {

		logger.debug("==============开始上传====" + fileName + "==============");

		logger.debug("加入SCP实现远程传输文件");

		Connection con = conn();

		upload(fileName, con);
		return "200";

	}

	public SCPClient scpClient;

	public void upload(String fileName, Connection con) throws IOException {
		if (scpClient == null)
			// 建立SCP客户端
			scpClient = getScpclient(con);

		upload(fileName, scpClient);
	}

	private void upload(String fileName, SCPClient scpClient) throws IOException {
		// 0755是指权限编号

		scpClient.put(fileName, scpPath, "0777");

//		if (logger.isDebugEnabled()) {
//			logger.debug("关闭连接");
//		}
		// con.close();
		if (logger.isDebugEnabled()) {
			logger.debug("上传完成");
		}
	}

	public SCPClient getScpclient(Connection con) throws IOException {
		SCPClient scpClient;

		if (logger.isDebugEnabled()) {
			logger.debug("建立scp客户端");
		}

		scpClient = con.createSCPClient();

		if (logger.isDebugEnabled()) {
			logger.debug("开始传输");
		}
		return scpClient;
	}

	public Connection conn() throws IOException {
		// 加入SCP实现远程传输文件
		Connection con = new Connection(scpAddress, Integer.parseInt(scpPort));
		if (logger.isDebugEnabled()) {
			logger.debug("开始连接");
		}
		// 连接

		con.connect();

		if (logger.isDebugEnabled()) {
			logger.debug("登陆远程服务器 "+scpAddress  + "," + username + "," + password);
		}
		// 登陆远程服务器的用户名密码
		boolean isAuthed;

		isAuthed = con.authenticateWithPassword(username, password);

		// 登陆失败
		if (!isAuthed) {
			throw new RuntimeException("auth fail....");

		}

		return con;
	}

	public SShFileUtilV3t33 setcfg(String string) throws  Exception {
		URIparser url = new URIparser(string.trim());
		String[] a=string.split(":");  //url.getHost()  "101.132.148.11"
		this.setScpAddress(url.getHost()).setScpPort("22").setUsername("root")
		.setPassword(url.getUserInfo().split(":")[1]);  //  "pdm#1921" 
		return this;
	}
}