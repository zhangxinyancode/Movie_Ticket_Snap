package com.oracle.movie.redis;

import redis.clients.jedis.Jedis;

public class Get {
	public  static Jedis get(){
			Jedis jedis=null;
			jedis=JedisUtil.getJedis();
		return jedis;
		
		
	}

}
