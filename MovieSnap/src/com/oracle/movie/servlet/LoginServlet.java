package com.oracle.movie.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.movie.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String username=request.getParameter("inputEmail3");
		/*
		 * ����Ҫ�ж��û��Ƿ�Ϊ���û���������û���ȥ���ݿ�� 
		 * if username=null;��ô�������ݿ����������û�����Ϣ
		 * if username!=null;��ô�Ͳ��� ֱ�ӵ�¼ ��ת����Ʊҳ��
		 * 
		 */
		UserService usrservice=new UserService();
		
		try {
			boolean flag=usrservice.queryuser(username);
			if(!flag){
				usrservice.adduser(username);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		request.getSession().setAttribute("username", username);
		response.sendRedirect("showinfo.jsp");
		
	}

}
