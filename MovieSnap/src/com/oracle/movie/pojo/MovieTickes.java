package com.oracle.movie.pojo;

import java.sql.Date;

public class MovieTickes {
	private static String m_id;
	private static String m_name;
	private static String m_num;
	private static String  addt_ime;
	private static String  start_time;
	private static String end_time;
	public static String getM_id() {
		return m_id;
	}
	public static void setM_id(String m_id) {
		MovieTickes.m_id = m_id;
	}
	public static String getM_name() {
		return m_name;
	}
	public static void setM_name(String m_name) {
		MovieTickes.m_name = m_name;
	}
	public static String getM_num() {
		return m_num;
	}
	public static void setM_num(String m_num) {
		MovieTickes.m_num = m_num;
	}
	public static String getAddt_ime() {
		return addt_ime;
	}
	public static void setAddt_ime(String addt_ime) {
		MovieTickes.addt_ime = addt_ime;
	}
	public static String getStart_time() {
		return start_time;
	}
	public static void setStart_time(String start_time) {
		MovieTickes.start_time = start_time;
	}
	public static String getEnd_time() {
		return end_time;
	}
	public static void setEnd_time(String end_time) {
		MovieTickes.end_time = end_time;
	}
	@Override
	public String toString() {
		return "MovieTickes [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
	}
