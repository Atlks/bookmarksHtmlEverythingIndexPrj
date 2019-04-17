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

import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.attilax.mvc.MvcUtil;
import com.attilax.net.URIparser;
import com.attilax.util.AliyunMessageUtil;
import com.attilax.util.DBPoolC3p0Util;
import com.attilax.util.velocityUtil;
import com.google.common.collect.Maps;

import ognl.Ognl;
import ognl.OgnlException;
import redis.clients.jedis.Jedis;
import resume.resumeGrepper;

public class clruser {
	final static Logger log = Logger.getLogger(clruser.class);
	static	String outFile;
	public	static boolean closeEcho=false;
	
	//http://localhost:888/api416?param=唐唐语文 删除 18821766710 328562
	@Path("/api416")
	public String get(HttpServletRequest req,HttpServletResponse resp) throws IOException, Exception {
		String paramString=req.getParameter("param");
		String[]	args = StringUtils.splitByWholeSeparator(paramString, " ");
	//	clruser.closeEcho=true;
		clruser.main(args);
  
		MvcUtil.outputHtml(resp, FileUtils.readFileToString(new File(clruser.outFile)));
	    return "thing";
	}

	//
	public static void c(String[] args) throws Exception, IOException {
		
		Properties p = new Properties();

		String pathname = getCfgpath();
		p.load(new FileReader(new File(pathname)));

		String rediscfg = p.getProperty("redis");
		
		System.out.println( clrusrTomcatStart.class.getResource("/aOPtool/db.propertis").toString()  );
		 outFile=clruser. getOutfile();	
		main2(args,outFile);
		if(!closeEcho)
		System.out.println( FileUtils.readFileToString(new File(clruser.outFile)));
		
	}

	private static void main2(String[] args, String outFile)
			throws FileNotFoundException, IOException, Exception, SQLException, MalformedURLException, ClientException {
		Map map_outMap=Maps.newLinkedHashMap();		
		
		String urlString = "jdbc:mysql://47.100.12.36:3306/tt_pre?userinfo=root:123456";
		urlString = "mysql://47.100.12.36:3306/tt_pre?userinfo=root:123456";
		// URI uri=new URI(urlString);

		// 获取键值对NameValuePair
		// org.apache.catalina.util.RequestUtil.p
		// java.util.List<NameValuePair> list= new
		// URIparser(urlString).getQueryParams();
		// System.out.println(list);

	
		map_outMap.put("args", args);
		String envikeyString = args[0];
		String op = args[1];
		String tel = args[2];

		String dburl = getDBurl(envikeyString);

		

		if (op.equals("查看")) {
			DataSource dSource = getDatasource(dburl);
			String db = "a_user";
			Map map = Maps.newLinkedHashMap();
			map.put("查看", "select * from a_user where cell_phone='$tel'");
			map.put("删除", "delete  from a_user where cell_phone='$tel'");

			String sqlString = (String) map.get(op);

			VelocityContext context = new VelocityContext();

			context.put("tel", tel);
			sqlString = velocityUtil.getTmpltCalcRzt(sqlString, context);
			log.info(sqlString);
			List list2 = query(sqlString, dSource);
			System.out.println(list2);
			log.info(list2);
			map_outMap.put("查看结果：",list2);
		} else if (op.equals("获取密码")) {
			
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

			// 查看服务是否运行
			System.out.println("服务正在运行: " + jedis.ping());
			
			
			String phoneNumber = tel;
			String randomNum = AliyunMessageUtil. createRandomNum(6);
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
			
			
			jedis.set(tel, randomNum);
			
		} else if (op.equals("删除")) {
			
			
			
			try {
				checkPwd(tel, args[3]);
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new RuntimeException("需要提供密码");
			}
			
			
			DataSource dSource = getDatasource(dburl);
			String db = "a_user";
			Map map = Maps.newLinkedHashMap();
			map.put("查看", "select * from a_user where cell_phone='$tel'");
			map.put("删除", "delete  from a_user where cell_phone='$tel'");

			String sqlString = (String) map.get(op);

			VelocityContext context = new VelocityContext();

			context.put("tel", tel);
			sqlString = velocityUtil.getTmpltCalcRzt(sqlString, context);
			log.info(sqlString);
			QueryRunner qr = new QueryRunner(dSource);
			int r = qr.update(sqlString);
			Map map2 = Maps.newLinkedHashMap();
			map2.put("sql", sqlString);
			map2.put("rzt", r);
			System.out.println(map2);
			log.info(map2);
			map_outMap.put("删除数量：", r);
		}

		FileUtils.write(new File(outFile), JSON.toJSONString(map_outMap,true));
	}

	static String getOutfile() throws IOException {
		 FileUtils.forceMkdir(new File("/0db"));
		 FileUtils.forceMkdir(new File("g:/0db"));
	String fnameSfxString=	 new SimpleDateFormat("yyyy-MM-dd.HHmmss").format(new java.util.Date());
		 if(new File("/etc").exists() )  //linux os
			 return "/0db/clrusrOutDir/clrusrOut"+fnameSfxString+".txt";
		 else
		return "g:/0db/clrusrOutDir/clrusrOut"+fnameSfxString+".txt";
	}

	private static String getCfgpath() {
		 
		URL resource = clrusrTomcatStart.class.getResource("/aOPtool/db.propertis");
		System.out.println(resource);
		return  resource.getPath().toString();
	}

	private static void checkPwd(String tel, String pwd) throws FileNotFoundException, IOException {
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

		// 查看服务是否运行
		System.out.println("服务正在运行: " + jedis.ping());
		if(jedis.get(tel)==null)
		{
			throw new RuntimeException(" 没有找到密码 ");
		}
		
		if (!jedis.get(tel).equals(pwd))
		{
			throw new RuntimeException(" 密码错误 ");
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

	private static DataSource getDatasource(String dburl) throws Exception {

		URIparser URIparser1 = new URIparser(dburl);
		java.util.List<NameValuePair> list = URIparser1.getQueryParams();
		String url = URIparser1.getUrlPrepart();
		NameValuePair nameValuePair = list.stream()
				.filter(NameValuePair1 -> NameValuePair1.getName().equals("userinfo")).collect(Collectors.toList())
				.get(0);
		String u = nameValuePair.getValue().split(":")[0];
		String p = nameValuePair.getValue().split(":")[1];
		DataSource datasouce = DBPoolC3p0Util.getDatasouce("com.mysql.jdbc.Driver", url, u, p);
		return datasouce;
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
