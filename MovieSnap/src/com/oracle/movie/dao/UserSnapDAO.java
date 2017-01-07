package com.oracle.movie.dao;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import com.oracle.movie.pojo.Snap;
import com.oracle.movie.util.JDBCTemplate;

public class UserSnapDAO {
	private JDBCTemplate jdbc=new JDBCTemplate();
	public List<Object[]> finduid(String moviename,String username) throws SQLException{
		String sql="select s_num from snap where m_id=(SELECT m_id FROM tickes WHERE m_name='"+moviename+"')AND u_id =(SELECT u_id "
+"FROM `user` where e_mail='"+username+"')";
		QueryRunner q=new QueryRunner();
		System.out.println(sql);
		List<Object[]> list=q.query(jdbc.getConnection(), sql,new ArrayListHandler());
		return list;
	}
	public boolean addsnap(Snap snap) throws SQLException{
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String r_time=format.format(new Date());
		QueryRunner q=new QueryRunner();
		String sql="insert into snap(u_id,m_id,s_num,snap_time) values(?,?,?,?)";
		System.out.println(snap.getM_id()+"-"+snap.getU_id());
		int rowCount=q.update(jdbc.getConnection(),sql,snap.getU_id(),snap.getM_id(),snap.getS_num(),r_time);
		System.out.println(rowCount);		
		return rowCount == -1 ? false : true;
		
	}
	

}
