package com.oracle.movie.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.oracle.movie.redis.Get;
import com.oracle.movie.redis.JedisUtil;
import com.oracle.movie.service.TickesService;

import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class ShowInfo
 */
public class ShowInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			this.getClass().getClassLoader().loadClass("org.apache.commons.pool2.impl.GenericObjectPoolConfig");
		} catch (ClassNotFoundException e1) {
//			System.out.println("³ö´íÎ¸");
		}
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		TickesService tickservice=new TickesService();
		
		try {
			int count=tickservice.getcount();
			StringBuffer buf = new StringBuffer("[");
			for(int i=1;i<count;i++){
				String json=JSON.toJSONString(Get.get().hgetAll(i+""));
				buf.append(json+",");
			}
			buf = new StringBuffer(buf.toString().subSequence(0, buf.length()-1)+"]");
			System.out.println(buf.toString());
			out.println(buf.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.flush();
		out.close();
		
		
	}

}
