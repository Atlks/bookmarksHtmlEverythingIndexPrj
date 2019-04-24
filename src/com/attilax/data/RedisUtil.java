package com.attilax.data;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.attilax.util.ExceptionAti;
import com.beust.jcommander.internal.Sets;

import redis.clients.jedis.Jedis;

public class RedisUtil {

	public static Object getVal(Jedis jedis, String k) {
		Object value = null;
		String typeString = jedis.type(k);
		if (typeString.equals("string"))
			value = jedis.get(k);
		else if (typeString.equals("list")) {
			value = jedis.lrange(k, 0, -1);
		} else if (typeString.equals("hash")) {
			value = jedis.hgetAll(k);

			// H:\0db\redisExportDir12\hskey.txt
		} else if (typeString.equals("set")) {
			value = jedis.smembers(k);

		} else {
			ExceptionAti e = new ExceptionAti();
			e.data.put("k", k);
			e.data.put("vType", typeString);
			throw e;
		}
		return value;
	}

	public static Object set(Jedis jedis, String k, Object v) {
		Object value = null;
	//	String typeString = jedis.type(k);
		if (v instanceof String)
			value = jedis.set(k, v.toString());
		else if (v instanceof List) {
			List<String> list = (List) v;
//			for (Object object : list) {
//				 long resultStatus = jedis.rpush(k,(String)object);
//			}
			String[] array = list.toArray(new String[list.size()]);
			long resultStatus = jedis.rpush(k, array);
		} else if (v instanceof Map) {
			jedis.hmset(k, (Map) v);

		} else if  (v instanceof Set) {
			Set<String> list = (Set) v;
			// (Object[]) list.toArray(new Object[list.size()])
			jedis.sadd(k, list.toArray(new String[list.size()]));

		} else {
			ExceptionAti e = new ExceptionAti();
			e.data.put("k", k);
			e.data.put("v", k);
		 //	e.data.put("vType", typeString);
			throw e;
		}
		return value;
	}
	
	
	public static Object toType(String valType, Object object) {
		 if(valType.equals("list"))
		return (List)object;
		 if(valType.equals("hash"))
				return (Map)object;
		 if(valType.equals("set"))
				return  array2set(object);
		 else {
			return (String)object;
		}
	}
	
	
	public static Set array2set(Object parse) {
		JSONArray v=(JSONArray) parse;
		
		
		Set set2=Sets.newLinkedHashSet();
		for (Object object : v) {
		//	JSONObject jo=(JSONObject) object;
			set2.add(object);
		}
		return set2;
	}
	
	
	public static Jedis getJedisObj(String string) throws URISyntaxException {
		
		URI url = new URI(string);
		Jedis jedis = new Jedis(url.getHost(),

				url.getPort());
		// jedis.
		jedis.auth(url.getUserInfo().split(":")[1]);
		jedis.select(Integer.parseInt(url.getPath().substring(1)) - 1); // select db
		return jedis;
	}

}
