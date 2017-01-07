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
			// ��������
			Class.forName(Constants.driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// ��ȡ����
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

	// ����Statement
	private void createStatement() {

		try {
			this.getConnection();
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ����preparedStatement
	private void createPreparedStatement(String sql) {
		this.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ִ�в�ѯ�ķ���
	 * 
	 * @param sql
	 *            ��ѯ��sql���
	 * @return rs ���ؽ����
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
	 * ִ����ɾ�ĵķ���
	 * 
	 * @param sql
	 *            ��ɾ�ĵ�sql
	 * @return rowCount Ӱ�������
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
			// �ر���Դ
			this.closeResource();
		}

		return rowCount;

	}

	/**
	 * ��ѯ�ķ���
	 * 
	 * @param sql
	 *            �����ŵ�sql
	 * @param args
	 *            ���ŵ�ֵ
	 * @return �����
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
	 * ִ����ɾ�ĵķ���
	 * 
	 * @param sql
	 *            �����ŵ�sql
	 * @param args
	 *            ���ŵ�ֵ
	 * @return Ӱ�������
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
				result = rs.getString(1); // ��ȡ��һ�е�һ�е�ֵ
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeResource();
		}
		return result;
	}

	// �����Ÿ�ֵ�ķ���
	private void operateParameter(String[] args) throws SQLException {
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				pstmt.setString(i + 1, args[i]);
			}
		}
	}

	// �ر���Դ�ķ���
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

