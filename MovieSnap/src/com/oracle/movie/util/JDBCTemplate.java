package com.oracle.movie.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {

	private Connection conn = null;

	private Statement stmt = null;

	private PreparedStatement pstmt = null;

	private ResultSet rs = null;

	static {
		try {
			// 加载驱动
			Class.forName(Constants.driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 获取连接
	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(Constants.url, Constants.user,
					Constants.password);
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 创建Statement
	private void createStatement() {

		try {
			this.getConnection();
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 创建preparedStatement
	private void createPreparedStatement(String sql) {
		this.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 执行查询的方法
	 * 
	 * @param sql
	 *            查询的sql语句
	 * @return rs 返回结果集
	 */
	public ResultSet executeQuery(String sql) {

		try {
			this.createStatement();

			rs = stmt.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	/**
	 * 执行增删改的方法
	 * 
	 * @param sql
	 *            增删改的sql
	 * @return rowCount 影响的行数
	 */
	public int executeUpdate(String sql) {
		int rowCount = -1;

		try {
			this.createStatement();

			rowCount = stmt.executeUpdate(sql);

			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			// 关闭资源
			this.closeResource();
		}

		return rowCount;

	}

	/**
	 * 查询的方法
	 * 
	 * @param sql
	 *            带？号的sql
	 * @param args
	 *            ？号的值
	 * @return 结果集
	 */
	public ResultSet executeQuery(String sql, String[] args) {
		try {
			this.createPreparedStatement(sql);

			this.operateParameter(args);

			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	/**
	 * 执行增删改的方法
	 * 
	 * @param sql
	 *            带？号的sql
	 * @param args
	 *            ？号的值
	 * @return 影响的行数
	 */
	public int executeUpdate(String sql, String[] args) {
		int rowCount = -1;
		try {
			this.createPreparedStatement(sql);

			this.operateParameter(args);

			rowCount = pstmt.executeUpdate();

			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			this.closeResource();
		}

		return rowCount;

	}

	public String executeScalar(String sql) {

		String result = "";

		this.createStatement();

		try {
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				result = rs.getString(1); // 获取第一行第一列的值
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeResource();
		}
		return result;
	}

	// 给？号赋值的方法
	private void operateParameter(String[] args) throws SQLException {
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				pstmt.setString(i + 1, args[i]);
			}
		}
	}

	// 关闭资源的方法
	public void closeResource() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

