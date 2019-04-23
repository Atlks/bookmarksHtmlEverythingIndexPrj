package aOPtool;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
//import org.junit.jupiter.api.Test;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.attilax.data.RedisUtil;
import com.attilax.fileTrans.SShFileUtilV3t33;
import com.attilax.util.ExceptionAti;
import com.beust.jcommander.internal.Lists;
import com.google.common.collect.Maps;

import redis.clients.jedis.Jedis;

//
public class redisImport {

	@Test
	public void testMain() throws Exception {
		String  pathname;String 	importDAtaDir;
         
	
	
		importDAtaDir = "H:\\0db\\redisExportDir12";
		pathname = "h:/0db/redis_36url.txt";
		
		
		//pathname = "h:/0db/redis_11url.txt";
	//	  importDAtaDir = "h:/0db/redisExportDir/11svr";
		
		
		//----------------aliyun redis
		String urlString ;//= FileUtils.readFileToString(new File(pathname));
		importDAtaDir = "d:\\0db\\redisExportDir\\11svr";		
			urlString= "http://u:p@localhost:1314/1";
			
			
			
			
			System.out.println(urlString);
		main(new String[] { urlString, importDAtaDir });
	}
	
	@Test
	public void tesSingle() throws Exception {
		String  pathname;String 	importDAtaDir;
         
	
	
		importDAtaDir = "H:\\0db\\redisExportDir12";
		pathname = "h:/0db/redis_36url.txt";
		
		
		//pathname = "h:/0db/redis_11url.txt";
	//	  importDAtaDir = "h:/0db/redisExportDir/11svr";
		
		
		//----------------aliyun redis
		String urlString = FileUtils.readFileToString(new File(pathname));
		importDAtaDir = "H:\\0db\\redisExportDir\\11svr";		
			urlString= "http://u:p@localhost:1314/2";
			
			
			
			
			System.out.println(urlString);
			
			Jedis jedis = getJedisObj(urlString);
			importSingle(jedis, new File("H:\\0db\\redisExportDir\\11svr\\308936209672048640_2019_04_23.txt"));
	}
	
	

	static Map curDebugMap_traceMap = Maps.newLinkedHashMap();
	public static int now;

	private final static Logger logger = Logger.getLogger("aOPtool_redisExport");
	private final static Logger logger_register = Logger.getLogger("register");

	// redisExport.class.getCanonicalName());

	@SuppressWarnings("all")
	public static void main(String[] args) throws Exception {
		logger_register.info("tttt");
//		  String		string="http://{0}@101.132.148.11:63790/1";
//			String pathname = "h:/0db/redis_11.txt";
//			string=MessageFormat.format(string, FileUtils.readFileToString(new File(pathname)));
		String logr = redisImport.class.getCanonicalName();
		String string = args[0].trim();
		System.out.println(string);
		
		Jedis jedis = getJedisObj(string);

		// jedis
		// hash类型的设置与读取
		String importdir = args[1];
		File[] subFiles=new File(importdir).listFiles();
		for (File recFile : subFiles) {
			now++;
			importSingle(jedis, recFile);
		  
		}

//		readallRediskeys(jedis, new Consumer<String>() {
//
//			@Override
//			public void accept(String k) {
//
//				now++;
//				try {
//					curDebugMap.put("curKey", k);
//					// jedis.getVal(k);
//
//					Object value = RedisUtil.getVal(jedis, k);
//					String typeString=	jedis.type(k);
//					Map map = Maps.newLinkedHashMap();
//					map.put("key", k);
//					map.put("valType", typeString);
//					map.put("val", value);
//
//				
//					String pathname = importdir + "/" + k + ".txt";
//					System.out.println(pathname);
//					logger.info(pathname);
//					String jsonString = JSON.toJSONString(map, true);
//					logger.info(jsonString);
//					FileUtils.write(new File(pathname), jsonString);
//
//					System.out.println("now:" + now);
//					logger.info("now:" + now);
//				} catch (Exception e) {
//					ExceptionAti ex = new ExceptionAti();
//					ex.data = curDebugMap;
//					ex.initCause(e);
//					ex.lineNumber = now;
//					Object message = JSON.toJSONString(ex, true);
//					logger.error(message, ex);
//				}
//
//			}
//		});
//
//		System.out.println(m2);
		System.out.println("--f");
	}

	private static Jedis getJedisObj(String string) throws URISyntaxException {
		
		URI url = new URI(string);
		Jedis jedis = new Jedis(url.getHost(),

				url.getPort());
		// jedis.
		jedis.auth(url.getUserInfo().split(":")[1]);
		jedis.select(Integer.parseInt(url.getPath().substring(1)) - 1); // select db
		return jedis;
	}

	private static void importSingle(Jedis jedis, File recFile) {
		try {
			  String readFileToString = FileUtils.readFileToString(recFile);
				curDebugMap_traceMap.put("f", recFile);
				curDebugMap_traceMap.put("linenum", now);
				curDebugMap_traceMap.put("f_txt", readFileToString);
			Map object=  (Map) JSON.parse(readFileToString);
		
			    String k=(String) object.get("key");
			    String valType=(String) object.get("valType");
			    Object v=RedisUtil. toType(valType,object.get("val"));
			    
			  //  jedis.get("kxxx")==null
			    	  if(jedis.get(k)==null)    //has data
			    			RedisUtil.set(jedis, k, v);
			    		  
				 
			  
				
			
			//	curDebugMap_traceMap.put("f_txt", readFileToString);
				logger.info(JSON.toJSONString(curDebugMap_traceMap, true));
				curDebugMap_traceMap=Maps.newLinkedHashMap();
				
		} catch (Exception e) {
			ExceptionAti ex = new ExceptionAti();
			ex.data = curDebugMap_traceMap;
			ex.initCause(e);
			ex.lineNumber = now;
			Object message = JSON.toJSONString(ex, true);
			logger.error(message, ex);
		}
	}

	 

	 
}
