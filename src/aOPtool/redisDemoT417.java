package aOPtool;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.beust.jcommander.internal.Lists;
import com.google.common.collect.Maps;

import redis.clients.jedis.Jedis;

public class redisDemoT417 {
	
	public static void main(String[] args) throws URISyntaxException, IOException {
		String string= "http://u:p@localhost:1314/2";
		System.out.println(string);
		URI url=new URI(string);
		Jedis jedis = new Jedis(url.getHost(),

				url.getPort());
		// jedis.
		jedis.auth(url.getUserInfo().split(":")[1]);
		jedis.select(Integer.parseInt(url.getPath().substring(1)) - 1); // select db
		
	//	jedis
		// hash类型的设置与读取
        Map m=Maps.newConcurrentMap();
        m.put("mykey1", "myval1");
        List<Object> newArrayList = Lists.newArrayList();
        newArrayList.add("listv1");  newArrayList.add("listv2");
		m.put("minikey2", "3");
		m.put("minikey3", newArrayList.toString());
        jedis.hmset("hskey", m);
        Map m2=     jedis.hgetAll("hashkey1");
        System.out.println(m2);
	}
	
	
	/**
	 * //		string="http://{0}@r-uf6o2jdhb4bqojtwro.redis.rds.aliyuncs.com:6379/2";
//		string=MessageFormat.format(string, FileUtils.readFileToString(new File("h:/0db/redis_aliyun.txt")));
//	
	 */

}
