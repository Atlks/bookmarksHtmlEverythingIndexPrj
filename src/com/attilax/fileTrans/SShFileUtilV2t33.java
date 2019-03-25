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
 * /ganymed-ssh2-build210.jar
 * @author attilax
 * 
 */
public class SShFileUtilV2t33 {

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
	 * @throws Exception
	 */
	public static void main(String[] args) {
		SShFileUtilV2t33 c = new SShFileUtilV2t33().setScpAddress("172.246.241.101")
				.setScpPort("22").setScpPath("/home_src/test")
				.setUsername("root").setPassword("myhopecometrue");

		try {
			c.upLoadFile("C:\\img0\\1101_232822_269.jpg");
		} catch (ConnEx e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AuthEx e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (createSCPClientEx e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (uploadFileEx e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getScpPath() {
		return scpPath;
	}

	public SShFileUtilV2t33 setScpPath(String scpPath) {
		this.scpPath = scpPath;
		;
		return this;
	}

	public String getScpAddress() {
		return scpAddress;
	}

	public SShFileUtilV2t33 setScpAddress(String scpAddress) {
		this.scpAddress = scpAddress;
		;
		return this;
	}

	public String getScpPort() {
		return scpPort;
	}

	public SShFileUtilV2t33 setScpPort(String scpPort) {
		this.scpPort = scpPort;
		;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public SShFileUtilV2t33 setUsername(String username) {
		this.username = username;
		;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public SShFileUtilV2t33 setPassword(String password) {
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
			.getLogger(SShFileUtilV2t33.class);

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
			createSCPClientEx, uploadFileEx {
		if (logger.isDebugEnabled()) {
			logger.debug("==============开始上传====" + fileName + "==============");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("加入SCP实现远程传输文件");
		}
		// 加入SCP实现远程传输文件
		Connection con = new Connection(scpAddress, Integer.parseInt(scpPort));
		if (logger.isDebugEnabled()) {
			logger.debug("开始连接");
		}
		// 连接
		try {
			con.connect();
		} catch (IOException e) {

			e.printStackTrace();
			throw new ConnEx("conn ex");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("登陆远程服务器" + username + "," + password);
		}
		// 登陆远程服务器的用户名密码
		boolean isAuthed;
		try {
			isAuthed = con.authenticateWithPassword(username, password);
		} catch (IOException e) {

			e.printStackTrace();
			throw new AuthEx("authenticateWithPassword ex");
		}
		// 登陆失败
		if (!isAuthed) {
			if (logger.isDebugEnabled()) {
				logger.debug("登陆远程服务器失败");
			}
			return "506";
		}
		if (logger.isDebugEnabled()) {
			logger.debug("建立scp客户端");
		}

		// 建立SCP客户端
		SCPClient scpClient;
		try {
			scpClient = con.createSCPClient();
		} catch (IOException e) {

			e.printStackTrace();
			throw new createSCPClientEx("createSCPClientEx");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("开始上传文件到服务器");
		}

		// 0755是指权限编号
		try {
			scpClient.put(fileName, scpPath, "0755");
		} catch (IOException e) {

			e.printStackTrace();
			throw new uploadFileEx("up uploadFileEx");
		}
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