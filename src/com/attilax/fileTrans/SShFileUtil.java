package com.attilax.fileTrans;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;

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
public class SShFileUtil {

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
		SShFileUtil c = new SShFileUtil().setScpAddress("47.100.12.36")
				.setScpPort("22").setScpPath("/adir")
				.setUsername("root").setPassword("dKttdev.321");

		 
			c.upLoadFile("C:\\Users\\zhoufeiyue\\Desktop\\api.war");
		 System.out.println("--f");

	}

	public String getScpPath() {
		return scpPath;
	}

	public SShFileUtil setScpPath(String scpPath) {
		this.scpPath = scpPath;
		;
		return this;
	}

	public String getScpAddress() {
		return scpAddress;
	}

	public SShFileUtil setScpAddress(String scpAddress) {
		this.scpAddress = scpAddress;
		;
		return this;
	}

	public String getScpPort() {
		return scpPort;
	}

	public SShFileUtil setScpPort(String scpPort) {
		this.scpPort = scpPort;
		;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public SShFileUtil setUsername(String username) {
		this.username = username;
		;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public SShFileUtil setPassword(String password) {
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
	private final static Logger logger = LoggerFactory
			.getLogger(SShFileUtil.class);

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
	public String upLoadFile(String fileName) throws ConnEx, AuthEx,
			createSCPClientEx, uploadFileEx, IOException {
		 
			logger.debug("==============开始上传====" + fileName + "==============");
		 
			logger.debug("加入SCP实现远程传输文件");
	 
		// 加入SCP实现远程传输文件
		Connection con = new Connection(scpAddress, Integer.parseInt(scpPort));
		if (logger.isDebugEnabled()) {
			logger.debug("开始连接");
		}
		// 连接
		 
			con.connect();
		 
		if (logger.isDebugEnabled()) {
			logger.debug("登陆远程服务器" + username + "," + password);
		}
		// 登陆远程服务器的用户名密码
		boolean isAuthed;
	 
			isAuthed = con.authenticateWithPassword(username, password);
		 
		// 登陆失败
		if (!isAuthed) {
			throw new RuntimeException("auth fail....");
			 
		}
		if (logger.isDebugEnabled()) {
			logger.debug("建立scp客户端");
		}
		
	 

		// 建立SCP客户端
		SCPClient scpClient;
		 
			scpClient = con.createSCPClient();
		 
		if (logger.isDebugEnabled()) {
			logger.debug("开始上传文件到服务器");
		}

		// 0755是指权限编号
		 
			scpClient.put(fileName, scpPath, "0755");
		 
		if (logger.isDebugEnabled()) {
			logger.debug("关闭连接");
		}
		con.close();
		if (logger.isDebugEnabled()) {
			logger.debug("上传完成");
		}
		return "200";

	}
}