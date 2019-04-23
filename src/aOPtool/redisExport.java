package aOPtool;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.attilax.data.RedisUtil;
import com.attilax.fileTrans.SShFileUtilV3t33;
import com.attilax.util.ExceptionAti;
import com.beust.jcommander.internal.Lists;
import com.google.common.collect.Maps;

import redis.clients.jedis.Jedis;

//
public class redisExport {

	@Test
	public void testMain() throws Exception {
	//	String pathname = "d:/0db/redis_11url.txt";
	//	pathname = "h:/0db/redis_36url.txt";
	//	String urlString = FileUtils.readFileToString(new File(pathname));
		String	urlString="http://u:p@localhost:1314/2";
		System.out.println(urlString);
		main(new String[] { urlString, "d:/0db/redisExportDir/11svr" });
	}

	static Map curDebugMap = Maps.newLinkedHashMap();
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
		String logr = redisExport.class.getCanonicalName();
		String string = args[0].trim();
		System.out.println(string);
		URI url = new URI(string);
		Jedis jedis = new Jedis(url.getHost(),

				url.getPort());
		// jedis.
		jedis.auth(url.getUserInfo().split(":")[1]);
		jedis.select(Integer.parseInt(url.getPath().substring(1)) - 1); // select db

		// jedis
		// hash类型的设置与读取
		Map m = Maps.newConcurrentMap();
		m.put("mykey1", "myval1");
		List<Object> newArrayList = Lists.newArrayList();
		newArrayList.add("listv1");
		newArrayList.add("listv2");
		m.put("minikey2", "3");
		m.put("minikey3", newArrayList.toString());
		jedis.hmset("hskey", m);
		Map m2 = jedis.hgetAll("hashkey1");

		readallRediskeys(jedis, new Consumer<String>() {

			@Override
			public void accept(String k) {

				now++;
				try {
					curDebugMap.put("curKey", k);
					// jedis.getVal(k);

					Object value = RedisUtil.getVal(jedis, k);
					String typeString=	jedis.type(k);
					Map map = Maps.newLinkedHashMap();
					map.put("key", k);
					map.put("valType", typeString);
					map.put("val", value);

					String pathname = args[1] + "/" + k + ".txt";
					System.out.println(pathname);
					logger.info(pathname);
					String jsonString = JSON.toJSONString(map, true);
					logger.info(jsonString);
					FileUtils.write(new File(pathname), jsonString);

					System.out.println("now:" + now);
					logger.info("now:" + now);
				} catch (Exception e) {
					ExceptionAti ex = new ExceptionAti();
					ex.data = curDebugMap;
					ex.initCause(e);
					ex.lineNumber = now;
					Object message = JSON.toJSONString(ex, true);
					logger.error(message, ex);
				}

			}
		});

		System.out.println(m2);
		System.out.println("--f");
	}

	public static void readallRediskeys(Jedis jedis, Consumer<String> consumer) throws IOException {
		Set<?> s = jedis.keys("*");

		System.out.println("keys size:" + s.size());
		logger.info("keys size:" + s.size());
		Iterator<?> it = s.iterator();

		// FileWriter writer = new FileWriter(FILEPATH + "category3.txt", true);
		while (it.hasNext()) {
			Object next = it.next();
			String key = (String) next;
			consumer.accept(key);
//             String value = jedis.get(key);
//             System.out.println(key + ": " + value);
//             writer.write(key + ": " + value);
//             writer.write(System.getProperty("line.separator"));
		}
		// writer.close();
	}

}
