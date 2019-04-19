package aOPtool;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import com.beust.jcommander.internal.Lists;
import com.google.common.collect.Maps;

import redis.clients.jedis.Jedis;

public class redisDemoT417 {
	
	public static void main(String[] args) throws URISyntaxException {
		String string="http://root:ttredis2018124@47.100.12.36:63790/2";
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

}
