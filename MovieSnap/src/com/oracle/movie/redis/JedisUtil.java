package com.oracle.movie.redis;

import com.sun.javafx.logging.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;  
  
public class JedisUtil {    
     private static String ADDR = "192.168.118.134";  
       
     private static int PORT = 6379;  
     
     private static int MAX_ACTIVE = 200;  
      
     private static int MAX_IDLE = 50;  
     
     private static int MIN_IDLE=8;
     
     private static int MAX_WAIT = 100000;  
             
     private static boolean TEST_ON_BORROW = true;  
     private static JedisPool jedisPool = null;  
    
      static {  
          try {  
        	  
               JedisPoolConfig config = new JedisPoolConfig();  
               config.setMaxTotal(MAX_ACTIVE);  
               config.setMaxIdle(MAX_IDLE);  
               config.setMinIdle(MIN_IDLE);
               config.setMaxWaitMillis(MAX_WAIT);  
               config.setTestOnBorrow(TEST_ON_BORROW);
               config.setTestOnBorrow(true);
               config.setTestWhileIdle(true);
             //表示idle object evitor两次扫描之间要sleep的毫秒数
             config.setTimeBetweenEvictionRunsMillis(30000);
             //表示idle object evitor每次扫描的最多的对象数
             config.setNumTestsPerEvictionRun(10);
             //表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
             config.setMinEvictableIdleTimeMillis(60000);
               
               jedisPool = new JedisPool(config, ADDR, PORT,10000,"123",0);  
//               System.out.println("------------------");
//               System.out.println(jedisPool);
//               System.out.println("------------------");
          } catch (Exception e) {  
               e.printStackTrace();  
          }  
      }  
        
      /** 
       * 获取Jedis实例 
       * @return 
       */  
      public synchronized static Jedis getJedis() {  
          try {  
//        	  System.out.println("hello");
              if (jedisPool != null) {  
            	  System.out.println("Could get a resource from the pool");
                  Jedis resource = jedisPool.getResource();  
                 
                  return resource;  
              } else {  
            	  System.out.println("Could not get a resource from the pool");
                  return null;  
              }  
          } catch (Exception e) {  
              e.printStackTrace();  
              return null;  
          }  
      }  
             
      /** 
       * 释放jedis资源 
       * @param jedis 
       */  
       public static void returnResource(final Jedis jedis) {  
           if (jedis != null) {  
                jedisPool.returnResourceObject(jedis);  
           }  
       }  
}  