package com.oracle.movie.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.movie.pojo.Snap;
import com.oracle.movie.redis.MessageManager;
import com.oracle.movie.redis.TaskQueue;
import com.oracle.movie.redis.TaskQueueManager;
import com.oracle.movie.service.TickesService;
import com.oracle.movie.service.UserService;
import com.oracle.movie.service.UserSnapService;

/**
 * Servlet implementation class SendMailServlet
 */
public class SendMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMailServlet() {
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
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String moviename=request.getParameter("moviename");
		String username=request.getParameter("username");
//		System.out.println(username);
//		System.out.println(new String(moviename.getBytes("ISO-8859-1"),"UTF-8"));
		//判断User是否已经抢购过票
		UserSnapService snapservice=new UserSnapService();
		UserService userservice=new UserService();
		TickesService tickservice=new TickesService();
		try {
			List<Object[]> list=snapservice.findsnap(new String(moviename.getBytes("ISO-8859-1"),"UTF-8"), username);
			List<Object[]> list1=userservice.finduserid(username);
			
			if(!list.isEmpty()){
				System.out.println("已经抢购过了");
			}
			else{
				System.out.println("成功抢购");
//				int m_id=(int)list.get(0)[0];
//				int u_id=(int)list1.get(0)[0];
				Date time=new Date();
				DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String r_time=format.format(time);
				//成功抢购后将抢购后的信息写入到用户数据库中
//				Snap snap=new Snap();
//				snap.setM_id(m_id);
//				snap.setU_id(u_id);
//				snap.setS_num(1);
//				snap.setSnap_time(r_time);
//				snapservice.addsnap(snap);
//				JavaMail mail=new JavaMail();
//				mail.sendemail(username, moviename);
				//生成邮件信息
				String message=username+":</br><hr><h3>您于"+r_time+"参与《"+new String(moviename.getBytes("ISO-8859-1"),"UTF-8")+"》电影票秒杀活动,成功抢购电影票一张,谢谢您的参与</h3>";
				//将消息放入到redis队列中 并等待处理
				TaskQueue queue=TaskQueueManager.getqueue(TaskQueueManager.SMS_QUEUE);
				queue.pushtask(message);
				MessageManager.pushmessage(message);
				System.out.println(message);
				//抢购成功后将redis中相应影片的剩余票数进行-1
				tickservice.update(new String(moviename.getBytes("ISO-8859-1"),"UTF-8"));
				response.sendRedirect("showinfo.jsp");
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	
		
		
	}

}
