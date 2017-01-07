package com.oracle.movie.redis;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TaskQueueManager {
	private static Map<String,TaskRedisImpl> queuemap=new ConcurrentHashMap<String,TaskRedisImpl>();
	
	public static final String SMS_QUEUE="SM_QUEUE";
	
	public static void initQueueMap(){
		
		queuemap.put(SMS_QUEUE, new TaskRedisImpl(SMS_QUEUE));
		System.out.println("创建消息队列.....");
	}
	static{
		initQueueMap();
	}
	
	public  static TaskQueue getqueue(String name){
		return queuemap.get(name);
		
	}
	

}
