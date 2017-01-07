package com.oracle.movie.dao;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.oracle.movie.pojo.*;
import com.oracle.movie.util.JDBCTemplate;

public class UserDAO {
	private JDBCTemplate jdbc=new JDBCTemplate();
	private QueryRunner q=new QueryRunner();
	public boolean queryUser(String email) throws SQLException{
		List<User> list=new ArrayList<User>();
		String sql ="SELECT * FROM `user` WHERE e_mail ='"+email+"'";
		
		list=(List<User>)q.query(jdbc.getConnection(), sql, new BeanListHandler(User.class));
		if(list.isEmpty()){
			return false;
		}	
		return true;
	}
	public boolean adduser(String username){
		Date time=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String r_time=format.format(time);
		String sql="insert into User (e_mail,r_time) values(?,?)";
		int rowCount=jdbc.executeUpdate(sql, new String[]{username,r_time});
		
		return rowCount == -1 ? false : true;			
	} 
	public List<Object[]> finduserid(String username) throws SQLException{
		String sql="SELECT u_id FROM `user` WHERE e_mail ='"+username+"'";
		
		List<Object[]> list=q.query(jdbc.getConnection(), sql, new ArrayListHandler());
				
		return list ;
	}
	
	

}
