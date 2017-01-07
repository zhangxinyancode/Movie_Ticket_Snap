package com.oracle.movie.service;

import java.sql.SQLException;
import java.util.List;

import com.oracle.movie.dao.UserDAO;

public class UserService {
	private static UserDAO dao=new UserDAO(); 
	public boolean queryuser(String username) throws SQLException{
		 
		return  dao.queryUser(username); 
	}
	public boolean adduser(String username){
		
		return dao.adduser(username);
	}
	public List<Object[]> finduserid(String username) throws SQLException{
		
		return dao.finduserid(username);
	}

}
