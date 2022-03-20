package com.lcvc.ebuy.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import org.junit.Test;

public class JunitTest {
	Scanner input = new Scanner(System.in);
	
	/*
	 * 测试数据库的连接
	 * */
	@Test
	public void testConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");		//1、指明要连接MySQL数据库，用到哪个驱动，MySQL驱动规定的com.mysql.jdbc.Dryver
														//2、指明要访问的MySQL数据库是哪个。用什么账户名和密码去连接这个数据库
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/admin?characterEncoding=utf-8","root","123456");//连接数据库的路径
			String sql="select * from admin where username='user' and userpass='123456'";							//定义一个字符串
			PreparedStatement pstmt=connection.prepareStatement(sql);	//这个对象可以用来将sql语句发送到数据库（准备发送）
			ResultSet rs=pstmt.executeQuery();					//pstmt.executeQuery()表示将sql语句真的发送到数据库，并返回查询结果
			while(rs.next()){								//如果下一条记录存在，那么游标就移动到下一条记录(循环输出)
				System.out.print(rs.getInt("userId")+"\t");			//输出ID
				System.out.print(rs.getString("username")+"\t");	//输出账户名
				System.out.print(rs.getString("screenName")+"\t");	//输出昵称
				System.out.println();								//换行
			}
			connection.close();								//关闭数据库
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
		}
	}
	
	
	
}
