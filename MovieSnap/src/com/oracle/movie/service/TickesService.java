package com.oracle.movie.service;

import java.sql.SQLException;
import java.util.List;

import com.oracle.movie.dao.TickesDAO;

public class TickesService {	
	private TickesDAO dao=new TickesDAO();
	public List<Object[]> findmovieid(String moviename) throws SQLException{
		
		List<Object[]> list=dao.findmovieid(moviename);
		
		return list;
		
	}
	public List<Object[]> findall() throws SQLException{
		return dao.findall();
	}
	public void savetickesByhash() throws SQLException{
		dao.savetickesByhash();
	}
	public synchronized void update(String moviename) throws SQLException{
		dao.update(moviename);
	}
	public int getcount() throws SQLException{
		return dao.getcount();
	}
	
}
