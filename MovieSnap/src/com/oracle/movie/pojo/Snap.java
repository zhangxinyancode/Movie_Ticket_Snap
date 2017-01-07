package com.oracle.movie.pojo;

import java.sql.Date;

public class Snap {
	private static int s_id;
	private static int u_id;
	private static int m_id;
	private static int s_num;
	private static Date snap_time;
	public static int getS_id() {
		return s_id;
	}
	public static void setS_id(int s_id) {
		Snap.s_id = s_id;
	}
	public static int getU_id() {
		return u_id;
	}
	public static void setU_id(int u_id) {
		Snap.u_id = u_id;
	}
	public static int getM_id() {
		return m_id;
	}
	public static void setM_id(int m_id) {
		Snap.m_id = m_id;
	}
	public static int getS_num() {
		return s_num;
	}
	public static void setS_num(int s_num) {
		Snap.s_num = s_num;
	}
	public static Date getSnap_time() {
		return snap_time;
	}
	public static void setSnap_time(Date snap_time) {
		Snap.snap_time = snap_time;
	}
	@Override
	public String toString() {
		return "Snap [getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	

}
