package com.lcvc.ebuy.test.Admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class DBTest {
	/**
	 * 测试数据库的连接
	 */
	@Test
	public void testConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");//1.指明要连接mysql数据库，用到哪个驱动，mysql驱动规定的com.mysql.jdbc.Driver
			//2.指明要访问的mysql数据库是哪个，用什么账户名和密码去连接这个数据库
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/lcvc_test?characterEncoding=utf-8", "user", "123456");
			String sql="select * from admin where username='user' and password='1234561'";//定义一个字符串
			PreparedStatement pstmt=connection.prepareStatement(sql);//这个对象可以用来将sql语句发送到数据库(准备发送)
			ResultSet rs=pstmt.executeQuery();//pstmt.executeQuery()表示将sql语句真的发送到数据库，并且返回查询结果
			if(rs.next()){//如果能找到下一条记录
				System.out.println("登录成功");
			}else{
				System.out.println("登录失败");
			}			
			connection.close();//关闭数据库
		} catch (ClassNotFoundException e) {//catch表示，如果出现了ClassNotFoundException异常，就执行括号内的代码
			e.printStackTrace();
		} catch(SQLException e){//catch表示，如果出现了SQLException异常，就执行括号内的代码
			e.printStackTrace();
		}
	}
}
