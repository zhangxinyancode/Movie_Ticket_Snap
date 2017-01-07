package com.oracle.movie.redis;

import redis.clients.jedis.Jedis;

public class TaskRedisImpl implements TaskQueue {

	private final String queuename;

	public TaskRedisImpl(String queuename) {
		
		this.queuename = queuename;
	}
	public String getqueuename(){
		return this.queuename;
	}
	@Override
	public void pushtask(String taskname) {
		// TODO Auto-generated method stub
		Jedis jedis=null;
		jedis=JedisUtil.getJedis();
		jedis.lpush(this.queuename,taskname);
//		System.out.println(jedis.llen(this.queuename));
		/*
		 * ������Ϣ����ÿ����10����Ϣ ����һ���Դ���
		 * ÿ���ڶ�������ӽ���Ϣ֮�� �ͽ����ж� redis��list�ĳ��ȴ�С�Ƿ񳬹�10
		 */
		Long size=jedis.llen(this.queuename);
		if(size>=10){
			MessageManager.handlemessage();
		}
		if(jedis!=null){
			JedisUtil.returnResource(jedis);
		}		
	}
	@Override
	public String poptask() {
		// TODO Auto-generated method stub
		Jedis jedis=null;
		String task=null;
		jedis=JedisUtil.getJedis();
		task=jedis.rpop(this.queuename);
		if(jedis!=null){
			JedisUtil.returnResource(jedis);
		}
		return task;
	}
	@Override
	public Long len(){
		Jedis jedis=null;
		String task=null;
		jedis=JedisUtil.getJedis();
		
		
		return jedis.llen(this.queuename);
	}
	
	
}
