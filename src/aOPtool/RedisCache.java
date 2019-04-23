package aOPtool;

import org.apache.log4j.Logger;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisCache {  
    protected static Logger logger = Logger.getLogger(RedisCache.class);  
    public final static String VIRTUAL_COURSE_PREX = "_lc_vc_";  
      
      
    private RedisCacheConfig redisCacheConfig;  
    private JedisPool jedisPool = null;  
      
    public RedisCacheConfig getRedisCacheConfig() {  
        return redisCacheConfig;  
    }  
  
    public void setRedisCacheConfig(RedisCacheConfig redisCacheConfig) {  
        this.redisCacheConfig = redisCacheConfig;  
    }  
  
    public RedisCache(){  
          
    }  
      
    /** 
     * 初始化Redis连接池 
     */  
    private void initialPool(){  
        JedisPoolConfig poolConfig = redisCacheConfig.getPoolConfig();  
        String[] serverArray = redisCacheConfig.getServers().split(",");  
        for(int i = 0; i < serverArray.length; i++){  
            try {  
                JedisPoolConfig config = new JedisPoolConfig();  
                config.setMaxTotal(poolConfig.getMaxTotal());  
                config.setMaxIdle(poolConfig.getMaxIdle());  
                config.setMaxWaitMillis(poolConfig.getMaxWaitMillis());  
                config.setTestOnBorrow(poolConfig.getTestOnBorrow());  
                config.setTestOnReturn(poolConfig.getTestOnReturn());  
                  
                jedisPool = new JedisPool(config, serverArray[i], redisCacheConfig.getPort(), redisCacheConfig.getTimeout());  
                break;  
            } catch (Exception e) {  
                logger.error("initialPool create JedisPool(" + serverArray[i] + ") error : "+e);  
            }  
        }  
          
    }  
      
    /** 
     * 在多线程环境同步初始化 
     */  
    private synchronized void poolInit() {  
        if (jedisPool == null) {    
            initialPool();  
        }  
    }  
   
       
    /** 
     * 同步获取Jedis实例 
     * @return Jedis 
     */  
    public synchronized Jedis getJedis() {  
        if (jedisPool == null) {  
            poolInit();  
        }  
        Jedis jedis = null;  
        try {  
            if (jedisPool != null) {  
                jedis = jedisPool.getResource();  
                jedis.auth(redisCacheConfig.getAuth());  
            }  
        } catch (Exception e) {  
            logger.error("Get jedis error : "+e);  
            e.printStackTrace();  
        }finally{  
            returnResource(jedis);  
        }  
        return jedis;  
    }  
      
    /** 
     * 释放jedis资源 
     * @param jedis 
     */  
    public void returnResource(final Jedis jedis) {  
        if (jedis != null && jedisPool !=null) {  
            jedisPool.returnResource(jedis);  
        }  
    }  
  
    /** 
     * 得到Key 
     * @param key 
     * @return 
     */  
    public String buildKey(String key){  
        return VIRTUAL_COURSE_PREX + key;  
    }  
    /** 
     * 设置 String 
     * @param key 
     * @param value 
     */  
    public void setString(String key ,String value){  
        try {  
            value = StringUtil.isNullOrEmpty(value) ? "" : value;  
            getJedis().set(buildKey(key),value);  
        } catch (Exception e) {  
            logger.error("Set key error : "+e);  
        }  
    }  
       
    /** 
     * 设置 过期时间 
     * @param key 
     * @param seconds 以秒为单位 
     * @param value 
     */  
    public void setString(String key ,int seconds,String value){  
        try {  
            value = StringUtil.isNullOrEmpty(value) ? "" : value;  
            getJedis().setex(buildKey(key), seconds, value);  
        } catch (Exception e) {  
            logger.error("Set keyex error : "+e);  
        }  
    }  
       
    /** 
     * 获取String值 
     * @param key 
     * @return value 
     */  
    public String getString(String key){  
        String bKey = buildKey(key);  
        if(getJedis() == null || !getJedis().exists(bKey)){  
            return null;  
        }  
        return getJedis().get(bKey);  
    }  
    /** 
     * 设置 list 
     * @param <T> 
     * @param key 
     * @param value 
     */  
    public <T> void setList(String key ,List<T> list){  
        try {  
            getJedis().set(key.getBytes(),ObjectTranscoder.serialize(list));  
        } catch (Exception e) {  
            logger.error("Set key error : "+e);  
        }  
    }  
    /** 
     * 获取list 
     * @param <T> 
     * @param key 
     * @return list 
     */  
    public <T> List<T> getList(String key){  
        String bKey = buildKey(key);  
        if(getJedis() == null || !getJedis().exists(key.getBytes())){  
            return null;  
        }  
        byte[] in = getJedis().get(key.getBytes());    
        List<T> list = (List<T>) ObjectTranscoder.deserialize(in);    
        return list;  
    }  
    /** 
     * 设置 map 
     * @param <T> 
     * @param key 
     * @param value 
     */  
    public <T> void setMap(String key ,Map<String,T> map){  
        try {  
            getJedis().set(key.getBytes(),ObjectTranscoder.serialize(map));  
        } catch (Exception e) {  
            logger.error("Set key error : "+e);  
        }  
    }  
    /** 
     * 获取list 
     * @param <T> 
     * @param key 
     * @return list 
     */  
    public <T> Map<String,T> getMap(String key){  
        String bKey = buildKey(key);  
        if(getJedis() == null || !getJedis().exists(key.getBytes())){  
            return null;  
        }  
        byte[] in = getJedis().get(key.getBytes());    
        Map<String,T> map = (Map<String, T>) ObjectTranscoder.deserialize(in);    
        return map;  
    }  
}  
