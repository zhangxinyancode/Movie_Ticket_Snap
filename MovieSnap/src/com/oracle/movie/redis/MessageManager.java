package com.oracle.movie.redis;

import com.oracle.email.JavaMail;

public class MessageManager{
	private static TaskQueue taskqueue=TaskQueueManager.getqueue(TaskQueueManager.SMS_QUEUE);
	public static void pushmessage(String message){
		taskqueue.pushtask(message);
		
		
	}
	public static void handlemessage(){
		String task=null;
//		int i=0;
		while(true){
			task=taskqueue.poptask();
			if(task!=null){
				String[] str=task.split(":");
				String username=str[0];
				JavaMail mail=new JavaMail();
				
				try {
//					System.out.println(i++);
					mail.sendemail(username, task);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else{
				break;
			}
			
		}
		
		
	}
	
	
	
	

}
