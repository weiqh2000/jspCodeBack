package com.lcvc.ebuy.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.lcvc.ebuy.model.Admin;
import com.lcvc.ebuy.model.Customer;
import com.mysql.jdbc.Statement;




	/**
	 * 用来处理管理账户的所有业务方法
	 * @author MyJ
	 * */
public class CustomerBean {
	
	private Object nsername;

	/**
	 * 处理管理客户账户登录
	 * @param username 账户名
	 * @param password 密码
	 * @return true表示登陆成功，false表示登陆失败
	 * **/
	
	public List<Customer> getCustomers(){
		List<Customer> Customers=new ArrayList<Customer>();
		try{
			Connection con=DBHelper.getConnection();
			String sql="select * from customer";
			PreparedStatement pstmt=con.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				Customer custom=new Customer();
				custom.setId(rs.getInt("id"));
				custom.setUsername(rs.getString("username"));
				custom.setPassword(rs.getString("password"));
				custom.setName(rs.getString("name"));
				custom.setTel(rs.getString("tel"));
				custom.setAddress(rs.getString("address"));
				custom.setZip(rs.getString("zip"));
				custom.setEmail(rs.getString("email"));
				custom.setPicUrl(rs.getString("picUrl"));
				custom.setIntro(rs.getString("intro"));
				custom.setCreateTime(rs.getTimestamp("createTime"));
				Customers.add(custom);
				
			}
			DBHelper.close(con, pstmt, rs);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return Customers;
	}
	public boolean delete(Integer id){
		boolean status=false;//默认删除失败
		try{
			Connection con=DBHelper.getConnection();
			String sql="delete from customer where id=?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			int number=pstmt.executeUpdate();
			if(number>0){
				status=true;
			}
			DBHelper.close(con, pstmt, null);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	public Boolean addCustomer(Customer customer){	
		Boolean status=null;//获取插入记录后的标识符
		//对数据库进行操作
		Connection con=DBHelper.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs = null; 
		try{
			String sql="insert into customer (id,username,password,name,picUrl,tel,address,email,zip,intro,createTime) values(null,?,?,?,?,?,?,?,?,?,now())";
			pstmt=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, customer.getUsername());
			pstmt.setString(2, "123456");
			pstmt.setString(3, customer.getName());
			pstmt.setString(4, customer.getPicUrl());
			pstmt.setString(5, customer.getTel());
			pstmt.setString(6, customer.getAddress());
			pstmt.setString(7, customer.getEmail());
			pstmt.setString(8, customer.getZip());
			pstmt.setString(9, customer.getIntro());
			rs=pstmt.getGeneratedKeys();
			int number=pstmt.executeUpdate();
			if(number>0){
				status=true;
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBHelper.close(con, pstmt, rs);
		}
		return status;	
	}
	
	public Customer updateCustomer(Integer id){
		Customer customer=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBHelper.getConnection();
			String sql="select * from customer where id=?";							//定义一个字符串
			pstmt=con.prepareStatement(sql);	//这个对象可以用来将sql语句发送到数据库（准备发送）
			pstmt.setInt(1,id);
			rs=pstmt.executeQuery();					//pstmt.executeQuery()表示将sql语句真的发送到数据库，并返回查询结果
			if(rs.next()){
				customer=new Customer();
				customer.setId(rs.getInt("id"));
				customer.setUsername(rs.getString("username"));
				customer.setPassword(rs.getString("password"));
				customer.setName(rs.getString("name"));
				customer.setPicUrl(rs.getString("picUrl"));
				customer.setTel(rs.getString("tel"));
				customer.setZip(rs.getString("zip"));
				customer.setAddress(rs.getString("address"));
				customer.setEmail(rs.getString("email"));
				customer.setIntro(rs.getString("intro"));
				customer.setCreateTime(rs.getDate("createTime"));
			}
												//关闭数据库
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBHelper.close(con, pstmt, rs);
			}
		return customer;
	}
	
	//修改客户信息
	public boolean doUpdateCustomer(Customer customer){
		boolean status=false;//默认修改失败
		
		try{
			Connection con=DBHelper.getConnection();
			String sql="update customer set username=?,password=?,name=?,picUrl=?,tel=?,address=?,email=?,zip=?,intro=? where id=?";
			PreparedStatement pstmt=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, customer.getUsername());
			pstmt.setString(2, customer.getPassword());
			pstmt.setString(3, customer.getName());
			pstmt.setString(4, customer.getPicUrl());
			pstmt.setString(5, customer.getTel());
			pstmt.setString(6, customer.getAddress());
			pstmt.setString(7, customer.getEmail());
			pstmt.setString(8, customer.getZip());
			pstmt.setString(9, customer.getIntro());
			pstmt.setInt(10, customer.getId());
			int number=pstmt.executeUpdate();
			if(number==1){
				status=true;
			}
			DBHelper.close(con, pstmt, null);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	//前台登录
	public boolean login(String username,String password){
		boolean result=false;
		try {
			Connection connection=DBHelper.getConnection();
			
			String sql="select count(1) from customer where username=? and password=?";							//定义一个字符串
			PreparedStatement pstmt=connection.prepareStatement(sql);	//这个对象可以用来将sql语句发送到数据库（准备发送）
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs=pstmt.executeQuery();					//pstmt.executeQuery()表示将sql语句真的发送到数据库，并返回查询结果
			if(rs.next()){
				int number=rs.getInt(1);
				if(number==1){
					result=true;
				}
			}
				DBHelper.close(connection, pstmt, rs);						//关闭数据库
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return result;
	}

	public Customer getCustomers(String username){
		Customer customer=new Customer();;
		try {
			
			Connection con=DBHelper.getConnection();
			String sql="select * from customer where username=?";							//定义一个字符串
			PreparedStatement pstmt=con.prepareStatement(sql);	//这个对象可以用来将sql语句发送到数据库（准备发送）
			pstmt.setString(1, username);
			ResultSet rs=pstmt.executeQuery();					//pstmt.executeQuery()表示将sql语句真的发送到数据库，并返回查询结果
			if(rs.next()){
//				Customer customer //创建一个admin对象，用于保存admin表中的一条记录
				customer.setUsername(rs.getString("username"));
				customer.setPassword(rs.getString("password"));
				customer.setId(rs.getInt("password"));

			}
			DBHelper.close(con, pstmt, rs);									//关闭数据库
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return customer;
	}
	public Boolean registered(Customer customer){	
		Boolean status=null;//获取插入记录后的标识符
		//对数据库进行操作
		Connection con=DBHelper.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs = null; 
		try{
			String sql="insert into customer (id,username,password,name,tel,createTime)value(null,?,?,?,?,now())";
			pstmt=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, customer.getUsername());
			pstmt.setString(2, customer.getPassword());
			pstmt.setString(3, customer.getName());
			pstmt.setString(4, customer.getTel());
			rs=pstmt.getGeneratedKeys();
			int number=pstmt.executeUpdate();
			if(number>0){
				status=true;
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBHelper.close(con, pstmt, rs);
		}
		return status;	
	}
	

	
	
	
}
