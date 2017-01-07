package com.oracle.movie.test;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.oracle.movie.dao.TickesDAO;
import com.oracle.movie.dao.UserDAO;
import com.oracle.movie.redis.JedisUtil;
import com.oracle.movie.redis.TaskQueue;
import com.oracle.movie.redis.TaskQueueManager;

import redis.clients.jedis.Jedis;

public final class Test { 
	public static void main(String[] args) {
//		UserDAO dao=new UserDAO();
//	User usr=new User();
//	usr.setU_name("aaa");
//	usr.setU_pass("asd");
//	usr.setE_mail("181556854@qq.com");
//		try { 
//			System.out.println(dao.queryUser("354635873@qq.com"));
//			System.out.println(dao.adduser(usr));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		};
//		UserSnapService snapservice=new UserSnapService();
//		TickesService tickservice=new TickesService();
		
//			List<Object[]> snaplist=snapservice.findsnap("354635873@qq.com");
//			try {
//				List<Object[]> idlist=tickservice.findmovieid("x战警");
//				List<Object[]> list=snapservice.findsnap("x战警", "1781556854@qq.com");
//				System.out.println(list.get(0)[0]);
//				if(!list.isEmpty()){
//					System.out.println("已经抢购过了");
//				}
//				else{
//					JavaMail mail=new JavaMail();
//					mail.sendemail("1781556854@qq.com","x战警");
//				}
				
//				String s=(String) idlist.get(0)[0];
//				System.out.println(idlist.get(0)[0]);
//				List<Object[]> snaplist=snapservice.findsnap(s);
//				if((int)snaplist.get(0)[0]==1){
//					System.out.println("您已经抢购过了");
//					}
//				else{
//					System.out.println("抢购成功");
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			JedisUtil jedis=new JedisUtil();
//			Jedis dis=jedis.getJedis();
//			System.out.println(dis.get("testvalue"));
		String moviename="你的名字";
		String username="364736873@qq.com";
//		TaskRedisImpl impl=new TaskRedisImpl();
//		impl.pushtask("354635873@qq.com","<h1>尊敬的用户你好:</h1></br><hr><h3>您于"+new Date()+"参与"+moviename+"电影票秒杀活动,成功抢购电影票一张,谢谢您的参与</h3>");
//		String task=impl.poptask("354635873@qq.com");
//		System.out.println(task);
//			dis.lpush();
//		Jedis jedis=new Jedis("192.168.118.137",6379);
//		jedis.auth("123");		
//		TaskQueue queue=TaskQueueManager.getqueue(TaskQueueManager.SMS_QUEUE);
		
//		queue.pushtask(username+":</br><hr><h3>您于"+new Date()+"参与《"+moviename+"》电影票秒杀活动,成功抢购电影票一张,谢谢您的参与</h3>");
		
//		System.out.println(queue.poptask());
//		System.out.println(queue.poptask());
//		System.out.println(queue.poptask());
//		String[] str1=str.split(":");
//		System.out.println(str1[0]);
//		System.out.println(str1[0]);
//		System.out.println(queue.len());
		UserDAO dao=new UserDAO();
		TickesDAO dao1=new TickesDAO();
		Jedis jedis=JedisUtil.getJedis();
		try {
			int count=dao1.getcount();
			dao1.update("你的名字");
			for(int i=1;i<=count;i++){
				String json=JSON.toJSONString(jedis.hgetAll(i+""));
				System.out.println(json);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Jedis jedis=null;
//		jedis.hset
//		jedis=JedisUtil.getJedis();
//		if(jedis!=null){
//			JedisUtil.returnResource(jedis);
//		}
		
//		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
//		try {List<Object[]> list=dao1.findall();
//			for(Object[] o:list ){
//				MovieTickes movie=new MovieTickes();
//				movie.setM_id((int)o[0]+"");
//				movie.setM_name((String)o[1]);
//				movie.setM_num((int)o[2]+"");
//				movie.setAddt_ime(format.format(o[3]));				
////					
//					Map<String,String> map= new HashMap<String,String>();
//					map.put("name",(String)o[1]);
//					map.put("num", (int)o[2]+"");
//					map.put("addtime",format.format(o[3]));
//					map.put("starttime",format.format(o[4]));
//					map.put("endtime",format.format(o[5]));	
//					jedis.hmset((int)o[0]+"", map);	
//				
//				System.out.println(map.get("name"));

//			}
//			List<Object[]> list3=dao1.findmovieid("你的名字");
//			int id=(int)list3.get(0)[0];
		
			
//			System.out.println();
//			System.out.println(id);
//			System.out.println(list.get(id-1)[1]);
//			Map<String,String> map=jedis.hgetAll(3+"");
//			System.out.println(map.get("name"));
//			System.out.println(map.get("num"));
//				System.out.println(jedis.hgetAll(id+""));
		
//		System.out.println();
//		UserSnapDAO dao2=new UserSnapDAO();
//		Snap snap=new Snap();
//		snap.setM_id(1);
//		snap.setU_id(2);
//		snap.setS_num(1);
//		System.out.println(snap.getM_id());
//		
//		try {
//			System.out.println(dao2.addsnap(snap));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
	
	}
}
	
	
	
	
	
	

    