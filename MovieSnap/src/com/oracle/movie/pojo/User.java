package com.oracle.movie.pojo;

import java.sql.Date;

public class User {
	private static int u_id;
	private static String u_name;
	private static String u_pass;
	private static String e_mail;
	private static Date r_time;
	
	public static int getU_id() {
		return u_id;
	}
	public static void setU_id(int u_id) {
		User.u_id = u_id;
	}
	public static String getU_name() {
		return u_name;
	}
	public static void setU_name(String u_name) {
		User.u_name = u_name;
	}
	public static String getU_pass() {
		return u_pass;
	}
	public static void setU_pass(String u_pass) {
		User.u_pass = u_pass;
	}
	public static String getE_mail() {
		return e_mail;
	}
	public static void setE_mail(String e_mail) {
		User.e_mail = e_mail;
	}
	public static Date getR_time() {
		return r_time;
	}
	public static void setR_time(Date r_time) {
		User.r_time = r_time;
	}
	@Override
	public String toString() {
		return "User [getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	


}
