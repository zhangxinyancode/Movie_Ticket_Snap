package com.oracle.movie.dao;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;

import com.oracle.movie.pojo.MovieTickes;
import com.oracle.movie.pojo.Snap;
import com.oracle.movie.redis.JedisUtil;
import com.oracle.movie.util.JDBCTemplate;

import redis.clients.jedis.Jedis;

public class TickesDAO {
	private JDBCTemplate  jdbc=new JDBCTemplate();
	private QueryRunner q=new QueryRunner();
	public List<Object[]> findmovieid(String name) throws SQLException{
		String sql="select m_id from tickes where m_name ='"+name+"'";
		List<Object[]> list=q.query(jdbc.getConnection(), sql, new ArrayListHandler());
				
				return list ;
	}
	public List<Object[]> findall() throws SQLException{
		String sql="select * from  tickes ";
		List <Object[]> list =
				q.query(jdbc.getConnection(), sql, new ArrayListHandler());
		return list;
	}
	public void savetickesByhash() throws SQLException{
		List<Object[]> list=findall();
		Jedis jedis=null;
		jedis=JedisUtil.getJedis();
		if(jedis!=null){
			JedisUtil.returnResource(jedis);
		}
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(Object[] o:list ){			
				Map<String,String> map= new HashMap<String,String>();
				map.put("name",(String)o[1]);
				map.put("num", (int)o[2]+"");
				map.put("addtime",format.format(o[3]));
				map.put("starttime",format.format(o[4]));
				map.put("endtime",format.format(o[5]));	
				jedis.hmset((int)o[0]+"", map);				
			
		}	
	}
	public synchronized void update(String moviename) throws SQLException{
		Jedis jedis=null;
		jedis=JedisUtil.getJedis();
		if(jedis!=null){
			JedisUtil.returnResource(jedis);
		}
		List<Object[]> list3=findmovieid(moviename);
		int id=(int)list3.get(0)[0];
		Map<String,String> map=jedis.hgetAll(id+"");
		int newcount=Integer.parseInt(map.get("num"))-1;
		map.put("num",newcount+"" );
		jedis.hmset(id+"", map);
		System.out.println(map.get("num"));	
	}
	public int getcount() throws SQLException{
		String sql="select count(*) FROM tickes";
		List<Object[]> list=q.query(jdbc.getConnection(), sql, new ArrayListHandler());
//		Object count=map.get("countt");
//		System.out.println(map.get("count"));
		Object count=list.get(0)[0];	
		return Integer.parseInt(count.toString());
	}

}
