package com.oracle.movie.redis;

public interface TaskQueue { 
	
	String getqueuename();
	
	void pushtask(String taskname);
	
	String poptask();
	Long len();
}
