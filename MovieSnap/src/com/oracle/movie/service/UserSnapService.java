package com.oracle.movie.service;

import java.sql.SQLException;
import java.util.List;

import com.oracle.movie.dao.UserSnapDAO;
import com.oracle.movie.pojo.Snap;

public class UserSnapService {
	 private UserSnapDAO dao=new UserSnapDAO(); 
	 public List<Object[]> findsnap(String moviename,String username) throws SQLException{
		 List<Object[]> list=dao.finduid(moviename,username);
		 return list;		 
	 }
	 
	 public boolean addsnap(Snap snap) throws SQLException{
		 
		 
		 return dao.addsnap(snap);
	 }

}
