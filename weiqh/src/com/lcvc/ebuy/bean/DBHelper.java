package com.lcvc.ebuy.bean;

import java.sql.*;

public class DBHelper {
	//获取数据库Connection对象
	public static Connection getConnection(){
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/admin?characterEncoding=utf-8";
		String username = "root";
		String password = "123456";
		
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e){
			
		}
		return conn;
	}
	
	//定义关闭数据库方法
	public static void close(Connection conn, PreparedStatement stmt, ResultSet rs){
		try {
			if (rs != null){
				rs.close();
			}
			if (stmt != null){
				stmt.close();
			}
			if (conn != null){
				conn.close();
			}
		} catch (Exception e){
			
		}
	}
	
}
