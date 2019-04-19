package aOPtool;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.ws.rs.Path;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URIUtils;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.buf.UriUtil;
import org.apache.tomcat.util.http.RequestUtil;
import org.apache.velocity.VelocityContext;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.attilax.mvc.MvcUtil;
import com.attilax.net.HttpServletRequestImp;
import com.attilax.net.OpaqueUri;
import com.attilax.net.URIparser;
import com.attilax.text.HeziUtil;
import com.attilax.util.AliyunMessageUtil;
import com.attilax.util.DBPoolC3p0Util;
import com.attilax.util.ExceptionAti;
import com.attilax.util.velocityUtil;
import com.beust.jcommander.internal.Lists;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.mysql.cj.conf.ConnectionUrlParser;
import com.mysql.cj.conf.url.SingleConnectionUrl;

import javassist.NotFoundException;
import ognl.Ognl;
import ognl.OgnlException;
import redis.clients.jedis.Jedis;
import resume.resumeGrepper;

@SuppressWarnings("all")
public class clruser {
	final static Logger log = Logger.getLogger(clruser.class);
	static String outFile;
	public static boolean closeEcho = false;
	public   ExceptionAti curEx=new ExceptionAti();

	// http://localhost:888/api416?param=唐唐语文 删除 18821766710 328562
	@Path("/api416")
	public String api416(HttpServletRequest req, HttpServletResponse resp) throws IOException, Exception {
		String paramString = req.getParameter("param");
		paramString = paramString.trim();
		paramString = HeziUtil.replaceHeziComma(paramString);
		List list = new clruser().statmentMultiExec(paramString);

//Joiner.on("\r\n<br>\r\n")
		String jsonString = JSON.toJSONString(list, true);
		jsonString = jsonString.replaceAll("\n", "\r\n<br>\r\n");
		MvcUtil.outputHtml(resp, jsonString);
		return "thing";
	}

	public List statmentMultiExec(String string) throws Exception, IOException {
		string = string.trim();

		List list_outRzzts = Lists.newLinkedList();
		List list_partSuccess = Lists.newLinkedList();
		// string = "唐唐云学堂 删除 1882176671094 056060";
		string = HeziUtil.replaceHeziComma(string);
		String[] aStrings = string.split(";");
		int lineNum = 1;
		ExceptionAti atiEx = new ExceptionAti();
		 this.curEx = atiEx;
	 
		atiEx.data.put("statments", string) ;

		for (String cmd : aStrings) {
			if (cmd.trim().length() == 0)
				continue;
			atiEx.addStackTraceElement(cmd, lineNum);

               
//			clruser.closeEcho = true;
			Map outputTztMap = statmentExec(cmd.trim());
		//	System.out.println(FileUtils.readFileToString(new File(clruser.outFile)));
			
			outputTztMap.put("linenum", lineNum);		
			list_outRzzts.add(outputTztMap);
			
			// for debug
			Map outputTztMap4debugMap=Maps.newLinkedHashMap();
			outputTztMap4debugMap.putAll(outputTztMap);;
			outputTztMap4debugMap.put("StackTraceElement", new StackTraceElement("", cmd, "", lineNum));
			list_partSuccess.add(outputTztMap4debugMap);
			atiEx.data.put("sucessOpRzt", list_partSuccess) ;
			
			lineNum++;
		}
	
		return list_outRzzts;
	}

	static Map cur_outMap;

	// statmentExec
	synchronized public static void main(String[] args) throws Exception, IOException {
		String stat = args[0];

		System.out.println(clrusrTomcatStart.class.getResource("/aOPtool/db.propertis").toString());
		outFile = clruser.getOutfile();
		clruser clruser1 = new clruser();
	//	clruser1.curEx = curEx;

		cur_outMap = clruser1.statmentExec(stat);

		if (!closeEcho)
			System.out.println(JSON.toJSONString(cur_outMap, true));
		FileUtils.write(new File(outFile), JSON.toJSONString(cur_outMap, true));
	}

	@SuppressWarnings("all")
	public Map statmentExec(String stat)
			throws FileNotFoundException, IOException, Exception, SQLException, MalformedURLException, ClientException {

		OpaqueUri uri = new OpaqueUri(stat);
		Map map_outMap = Maps.newLinkedHashMap();

		String urlString = "jdbc:mysql://47.100.12.36:3306/tt_pre?userinfo=root:123456";
		urlString = "mysql://47.100.12.36:3306/tt_pre?userinfo=root:123456";
		// URI uri=new URI(urlString);

		// 获取键值对NameValuePair
		// org.apache.catalina.util.RequestUtil.p
		// java.util.List<NameValuePair> list= new
		// URIparser(urlString).getQueryParams();
		// System.out.println(list);
curEx.data.put("cur_statment语句", stat);
		map_outMap.put("statment语句", stat);
		String envikeyString = uri.getHost();
		String op = uri.getScheme();
		String user_tel = uri.getUserInfo().split(":")[0];

		String dburl = getDBurl(envikeyString);
	//	clruser clruser1 = new clruser();
		if (op.equals("查询")) {
			DataSource dSource = DBPoolC3p0Util.getDatasource(dburl);
			String db = "a_user";
			Map map = Maps.newLinkedHashMap();
			map.put("查询", "select * from a_user where cell_phone='$tel'");
			map.put("删除", "delete  from a_user where cell_phone='$tel'");

			String sqlString = (String) map.get(op);

			VelocityContext context = new VelocityContext();

			context.put("tel", user_tel);
			sqlString = velocityUtil.getTmpltCalcRzt(sqlString, context);
			log.info(sqlString);
			List list2 = query(sqlString, dSource);
			// System.out.println(list2);
			log.info(list2);
			map_outMap.put("查询结果：", list2);
		} else if (op.equals("查询密码")) {

			sendPwd(user_tel, map_outMap);

		} else if (op.equals("删除")) {

			try {

				curEx.data.put("statment语句", stat);
				checkPwd(user_tel, uri.getUserInfo().split(":")[1]);
			} catch (ArrayIndexOutOfBoundsException e) {
				this.curEx.initCause(e);
		 	
				this.curEx.detailMessage = "需要提供密码";
				throw new RuntimeException(JSON.toJSONString(this.curEx, true));

			}

//			catch (NotFoundException e) { // no find redis tel key pwd
//				clruser1.sendPwd(user_tel,map_outMap);
//				this.curEx.initCause(e);
 
//				this.curEx.detailMessage="";		 
//				throw  new RuntimeException(JSON.toJSONString( this.curEx,true)) ;
//			}

			DataSource dSource = DBPoolC3p0Util.getDatasource(dburl);
			String db = "a_user";
			Map map = Maps.newLinkedHashMap();
			map.put("查询", "select * from a_user where cell_phone='$tel'");
			map.put("删除", "delete  from a_user where cell_phone='$tel'");

			String sqlString = (String) map.get(op);

			VelocityContext context = new VelocityContext();

			context.put("tel", user_tel);
			sqlString = velocityUtil.getTmpltCalcRzt(sqlString, context);
			log.info(sqlString);
			QueryRunner qr = new QueryRunner(dSource);
			int r = qr.update(sqlString);
			Map map2 = Maps.newLinkedHashMap();
			map2.put("sql", sqlString);
			map2.put("rzt", r);
			System.out.println(map2);
			log.info(map2);
			map_outMap.put("删除数量", r);
		} else {
		//	m_debugMap.put("statment语句", stat);
 
		//	curEx.data=m_debugMap;
			this.curEx.detailMessage = "不能识别的操作";
			// this.curEx.
			throw new RuntimeException(JSON.toJSONString(this.curEx, true),this.curEx);
		}
		return map_outMap;
		// FileUtils.write(new File(outFile), JSON.toJSONString(map_outMap, true));
	}

	private void sendPwd(String tel, Map map_outMap)
			throws IOException, FileNotFoundException, MalformedURLException, ClientException {
		Properties p = new Properties();

		String pathname = getCfgpath();
		p.load(new FileReader(new File(pathname)));

		String rediscfg = p.getProperty("redis");
		URL url = new URL(rediscfg);
		Jedis jedis = new Jedis(url.getHost(),

				url.getPort());
		// jedis.
		jedis.auth(url.getUserInfo().split(":")[1]);
		jedis.select(Integer.parseInt(url.getPath().substring(1)) - 1); // select db

		// 查询服务是否运行
		System.out.println("服务正在运行: " + jedis.ping());

		String phoneNumber = tel;
		String randomNum = AliyunMessageUtil.createRandomNum(6);
		String jsonContent = "{\"code\":\"" + randomNum + "\"}";
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("phoneNumber", phoneNumber);
		paramMap.put("msgSign", "唐唐云学堂");
		// paramMap.put("templateCode", "SMS_150495957");

		paramMap.put("templateCode", "SMS_150495959");
		paramMap.put("jsonContent", jsonContent);
		SendSmsResponse sendSmsResponse = AliyunMessageUtil.sendSms(paramMap);
		System.out.println("已发送");
		System.out.println(JSON.toJSON(sendSmsResponse));
		log.info(sendSmsResponse);
		jedis.set(tel, randomNum);
	//	this.m_debugMap.put("sendSmsRzt", sendSmsResponse);
		
		map_outMap.put("执行结果", sendSmsResponse);
	}

	static String getOutfile() throws IOException {
		FileUtils.forceMkdir(new File("/0db"));
		FileUtils.forceMkdir(new File("g:/0db"));
		String fnameSfxString = new SimpleDateFormat("yyyy-MM-dd.HHmmss").format(new java.util.Date());
		if (new File("/etc").exists()) // linux os
			return "/0db/clrusrOutDir/clrusrOut" + fnameSfxString + ".txt";
		else
			return "g:/0db/clrusrOutDir/clrusrOut" + fnameSfxString + ".txt";
	}

	private static String getCfgpath() {

//		URL resource = clrusrTomcatStart.class.getResource("/aOPtool/db.propertis");
//		System.out.println(resource);

		String pathname = "H:\\gitWorkSpace\\bookmarksHtmlEverythingIndexPrj\\src\\aOPtool\\db.propertis";
		if (new File(pathname).exists())
			return pathname;
		else if (new File("/0db/db.propertis").exists()) {
			return "/0db/db.propertis";
		}
		return "/0db/db.propertis";
	}

	//public Map m_debugMap = Maps.newLinkedHashMap();

	private void checkPwd(String tel, String pwd) throws FileNotFoundException, IOException, NotFoundException {
		Properties p = new Properties();

		String pathname = getCfgpath();
		p.load(new FileReader(new File(pathname)));

		String rediscfg = p.getProperty("redis");
		URL url = new URL(rediscfg);
		Jedis jedis = new Jedis(url.getHost(),

				url.getPort());
		// jedis.
		jedis.auth(url.getUserInfo().split(":")[1]);
		jedis.select(Integer.parseInt(url.getPath().substring(1)) - 1); // select db

		// 查询服务是否运行
		System.out.println("服务正在运行: " + jedis.ping());
		if (jedis.get(tel) == null) {
		//	m_debugMap.put("msg", "  没有找到密码,"); // 已经重新发送密码验证码，请等待查收

		//	throw new RuntimeException(JSON.toJSONString(m_debugMap));
		//	curEx.data=m_debugMap;
			this.curEx.detailMessage = "没有找到密码";
			throw new RuntimeException(JSON.toJSONString(this.curEx, true), this.curEx);

		}

		if (!jedis.get(tel).equals(pwd)) {

			// m_debugMap.put("msg", " 密码错误 ");
			// this.curEx.initCause(e);
		 
		 
			this.curEx.detailMessage = "密码错误";
			throw new RuntimeException(JSON.toJSONString(this.curEx, true), this.curEx);

		}

	}

	private static List<Map<String, Object>> query(String sql, DataSource datasouce) throws SQLException {
		QueryRunner qr = new QueryRunner(datasouce);
//		List<Map<String, Object>> list = qr.query(getUCodeSqlString, new MapListHandler());
//		System.out.println(JSON.toJSONString(list, true));
		List<Map<String, Object>> li = qr.query(sql, new MapListHandler());
		System.out.println(li);
		return li;
	}

	private static String getDBurl(String envikeyString) throws FileNotFoundException, IOException {
		Properties p = new Properties();

		String pathname = getCfgpath();
		p.load(new FileReader(new File(pathname)));
		return p.getProperty(envikeyString);
	}

	private static DataSource getDatasource(Map m) throws OgnlException, PropertyVetoException {
		// 非根节点取值需要#开头
		Object expression = Ognl.parseExpression("spring.datasource");

		Object value = Ognl.getValue(expression, m); // Ognl.getValue(expression);
		System.out.println(value);
		String url = (String) Ognl.getValue(Ognl.parseExpression("spring.datasource.url"), m);
		String u = (String) Ognl.getValue(Ognl.parseExpression("spring.datasource.username"), m);
		String p = Ognl.getValue(Ognl.parseExpression("spring.datasource.password"), m).toString();
		DataSource datasouce = DBPoolC3p0Util.getDatasouce("com.mysql.jdbc.Driver", url, u, p);
		return datasouce;
	}

}
