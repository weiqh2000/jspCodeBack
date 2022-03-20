package com.lcvc.ebuy.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.lcvc.ebuy.model.Customer;
import com.lcvc.ebuy.model.exception.MyFormException;
import com.lcvc.ebuy.model.other.PageObject;
import com.lcvc.ebuy.util.PageUtils;
import com.mysql.jdbc.Statement;

/*
 * 客户bean
 * 特别注明，该类所有分页均通过mysql的limit实现，如果使用其他数据库请使用相应的数据库分页
 */
public class CustomerBean {
	/*
	 * 定义数据库的查询方法,查询用户名和密码是否正确
	 * @param username 用户名
	 * @param passowrd 密码
	 * @return null表示登录失败
	 */
	public Customer login(String username,String password){	
		Customer customer=null;//最后返回的对象
		Connection conn=DBHelper.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			String sql="select * from customer where username=? and password=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if(rs.next()){
				customer=new Customer();
				customer=new Customer();
				customer.setId(rs.getInt("id"));
				customer.setUsername(rs.getString("username"));
				customer.setPassword(rs.getString("password"));
				customer.setName(rs.getString("name"));
				customer.setTel(rs.getString("tel"));
				customer.setAddress(rs.getString("address"));
				customer.setZip(rs.getString("zip"));
				customer.setEmail(rs.getString("email"));
				customer.setPicUrl(rs.getString("picUrl"));
				customer.setIntro(rs.getString("intro"));
				customer.setCreateTime(rs.getTimestamp("createTime"));
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBHelper.closeConn(conn,pstmt,rs);
		}
		return customer;			
	}
	/*
	 * 获取客户的所有记录
	 * @param page 要读取的页码
	 * @param pageSize 每页的记录数，必须>0
	 * @return PageObject分页专用类
	 */
	public PageObject getCustomers(Object page, final int pageSize){	
		AdminBean adminBean=new AdminBean();//初始化管理账户表操作类
		ProductTypeBean productTypeBean=new ProductTypeBean();//初始化产品类别bean
		List<Customer> list = new ArrayList<Customer>();
		//获取产品的总记录数
		int totalRecords=getRecordCount();
		//执行分页
		PageObject<Customer> pageObject=PageUtils.getPageObject(page, pageSize, totalRecords);
		pageObject.setList(list);
		Connection conn=DBHelper.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Customer customer=null;
		try{
			String sql="select * from customer order by createTime desc limit ?,?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (pageObject.getCurrentPage()-1)*pageSize);
			pstmt.setInt(2, pageSize);
			rs=pstmt.executeQuery();
			while(rs.next()){
				customer=new Customer();
				customer.setId(rs.getInt("id"));
				customer.setUsername(rs.getString("username"));
				customer.setPassword(rs.getString("password"));
				customer.setName(rs.getString("name"));
				customer.setTel(rs.getString("tel"));
				customer.setAddress(rs.getString("address"));
				customer.setZip(rs.getString("zip"));
				customer.setEmail(rs.getString("email"));
				customer.setPicUrl(rs.getString("picUrl"));
				customer.setIntro(rs.getString("intro"));
				customer.setCreateTime(rs.getTimestamp("createTime"));
				list.add(customer);
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBHelper.closeConn(conn,pstmt,rs);
		}
		return pageObject;			
	}
	
	/*
	 * 根据用户名获取相同用户名的用户数量
	 * @param username用户名
	 * @return 返回相同用户名的数量。
	 */
	public int getRecordCount(){	
		Connection conn=DBHelper.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try{
			String sql="select count(id) from customer";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();		
			if(rs.next()){
				count=rs.getInt(1);
			}	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBHelper.closeConn(conn,pstmt,rs);
		}
		return count;			
	}	
	
	
	/*
	 * 定义数据库的查询方法,根据id获取账户对象
	 * @param id 账户标识符
	 * @return null表示获取失败
	 */
	public Customer getCustomer(Object id){	
		Customer customer=null;//最后返回的对象
		Integer customerId=null;
		if(id!=null){
			try {
				String s=id.toString();
				customerId=Integer.parseInt(s);
			}catch(Exception e){
				customerId=null;
			}
		}
		if(customerId!=null){
			Connection conn=DBHelper.getConnection();
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try{
				String sql="select * from customer where id = ?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, customerId);
				rs=pstmt.executeQuery();
				if(rs.next()){
					customer=new Customer();
					customer.setId(rs.getInt("id"));
					customer.setUsername(rs.getString("username"));
					customer.setPassword(rs.getString("password"));
					customer.setName(rs.getString("name"));
					customer.setTel(rs.getString("tel"));
					customer.setAddress(rs.getString("address"));
					customer.setZip(rs.getString("zip"));
					customer.setEmail(rs.getString("email"));
					customer.setPicUrl(rs.getString("picUrl"));
					customer.setIntro(rs.getString("intro"));
					customer.setCreateTime(rs.getTimestamp("createTime"));
				}			
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				DBHelper.closeConn(conn,pstmt,rs);
			}
		}
		return customer;			
	}	
	
	/*
	 * 根据传入的参数添加到数据库，并返回插入数据库后的标识符（适合数据库的自增字段）
	 * @param customer 要添加的对象,如果密码字段为NULL则默认密码是123456
	 * @return null表示插入失败
	 */
	public Integer addCustomer(Customer customer) throws MyFormException{	
		Integer id=null;//获取插入记录后的标识符
		//进行表单验证
		if(customer!=null){
			if(customer.getPassword()==null){
				customer.setPassword("123456");
			}
			Connection conn=DBHelper.getConnection();
			PreparedStatement pstmt=null;
			ResultSet rs = null; 
			try{
				String sql="insert into customer(id,username,password,name,picUrl,tel,address,zip,email,intro,createTime) values(null,?,?,?,?,?,?,?,?,?,?)";
				pstmt=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);//要获取插入记录后的主键做法
				pstmt.setString(1, customer.getUsername());
				pstmt.setString(2, customer.getPassword());
				pstmt.setString(3, customer.getName());
				pstmt.setString(4, customer.getPicUrl());
				pstmt.setString(5, customer.getTel());
				pstmt.setString(6, customer.getAddress());
				pstmt.setString(7, customer.getZip());
				pstmt.setString(8, customer.getEmail());
				pstmt.setString(9, customer.getIntro());
				pstmt.setTimestamp(10, new Timestamp(Calendar.getInstance().getTimeInMillis()));//当前时间作为创建时间
				int i = pstmt.executeUpdate(); 
				if(i>0){//如果插入成功
					rs = pstmt.getGeneratedKeys();//获取插入后的标识符
					if (rs.next()) {
						id = rs.getInt(1); 
					}
				}			
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				DBHelper.closeConn(conn,pstmt,rs);
			}
		}else{
			throw new MyFormException("操作失败：参数非法");
		}
		return id;			
	}	
	
	/*
	 * 根据传入的参数更新数据库
	 * @param customer 要更新的对象
	 * @return true表示更新成功，false表示更新失败
	 */
	public boolean updateCustomer(Customer customer) throws MyFormException{	
		boolean flag=false;//获取最后的更新状态，false表示失败
		Customer customerOfOriginal=null;//最终要更新的对象
		if(customer!=null){//字段验证在web完成，这里仅仅对参数进行重新封装，避免表单中没有的字段变为Null而不是保留原始值
			customerOfOriginal=getCustomer(customer.getId());//从数据库判断该账户是否还存在
			if(customerOfOriginal!=null){
				String username=customer.getUsername();
				if(username!=null){
					customerOfOriginal.setUsername(username);
				}
				String password=customer.getPassword();
				if(password!=null){
					customerOfOriginal.setPassword(password);
				}
				String name=customer.getName();
				if(name!=null){
					customerOfOriginal.setName(name);
				}
				String picUrl=customer.getPicUrl();
				if(picUrl!=null){
					customerOfOriginal.setPicUrl(picUrl);
				}
				String tel=customer.getTel();
				if(tel!=null){
					customerOfOriginal.setTel(tel);
				}
				String address=customer.getAddress();
				if(address!=null){
					customerOfOriginal.setAddress(address);
				}
				String zip=customer.getZip();
				if(zip!=null){
					customerOfOriginal.setZip(zip);
				}
				String email=customer.getEmail();
				if(email!=null){
					customerOfOriginal.setEmail(email);
				}
				String intro=customer.getIntro();
				if(intro!=null){
					customerOfOriginal.setIntro(intro);
				}
			}
		}
		if(customerOfOriginal!=null){
			Connection conn=DBHelper.getConnection();
			PreparedStatement pstmt=null;
			try{
				String sql="update customer set username=?,password=?,name=?,picUrl=?,tel=?,address=?,zip=?,email=?,intro=? where id=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, customerOfOriginal.getUsername());
				pstmt.setString(2, customerOfOriginal.getPassword());
				pstmt.setString(3, customerOfOriginal.getName());
				pstmt.setString(4, customerOfOriginal.getPicUrl());
				pstmt.setString(5, customerOfOriginal.getTel());
				pstmt.setString(6, customerOfOriginal.getAddress());
				pstmt.setString(7, customerOfOriginal.getZip());
				pstmt.setString(8, customerOfOriginal.getEmail());
				pstmt.setString(9, customerOfOriginal.getIntro());
				pstmt.setInt(10, customerOfOriginal.getId());
				int i = pstmt.executeUpdate(); 
				if(i>0){
					flag=true;
				}			
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				DBHelper.closeConn(conn,pstmt,null);
			}
		}
		return flag;			
	}	
	
	/*
	 * 删除指定的对象
	 * @param id 标识符
	 * @return true表示删除成功，false表示删除失败
	 */
	public boolean deleteCustomer(Object id){	
		boolean flag = false;
		Integer productId = null;
		if (id != null) {
			try {
				String s = id.toString();
				productId = Integer.parseInt(s);
			} catch (Exception e) {
				productId = null;
			}
		}
		if (productId != null) {
			Connection conn = DBHelper.getConnection();
			PreparedStatement pstmt = null;
			try {
				String sql = "delete from customer where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, productId);
				int i = pstmt.executeUpdate();
				if (i > 0) {
					flag = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBHelper.closeConn(conn, pstmt, null);
			}
		}
		return flag;
	}
	
	
	/*
	 * 根据用户名获取相同用户名的用户数量
	 * @param username用户名
	 * @return 返回相同用户名的数量。
	 */
	public int getCountByUsername(String username){	
		Connection conn=DBHelper.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try{
			String sql="select count(id) from customer where username=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs=pstmt.executeQuery();
			if(rs.next()){
				count=rs.getInt(1);
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBHelper.closeConn(conn,pstmt,rs);
		}
		return count;			
	}	
	
	
	/*=======================带Connecttion的方法============================*/
	/*
	 * 定义数据库的查询方法,根据id获取账户对象，用于配合其他方法使用，不能单独调用
	 * @param id 账户标识符
	 * @return null表示获取失败
	 */
	public Customer getCustomer(Object id,Connection conn){	
		Customer customer=null;//最后返回的对象
		Integer customerId=null;
		if(id!=null){
			try {
				String s=id.toString();
				customerId=Integer.parseInt(s);
			}catch(Exception e){
				customerId=null;
			}
		}
		if(customerId!=null){
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try{
				String sql="select * from customer where id = ?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, customerId);
				rs=pstmt.executeQuery();
				if(rs.next()){
					customer=new Customer();
					customer.setId(rs.getInt("id"));
					customer.setUsername(rs.getString("username"));
					customer.setPassword(rs.getString("password"));
					customer.setName(rs.getString("name"));
					customer.setTel(rs.getString("tel"));
					customer.setAddress(rs.getString("address"));
					customer.setZip(rs.getString("zip"));
					customer.setEmail(rs.getString("email"));
					customer.setPicUrl(rs.getString("picUrl"));
					customer.setIntro(rs.getString("intro"));
					customer.setCreateTime(rs.getTimestamp("createTime"));
				}			
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				DBHelper.closeConn(null,pstmt,rs);
			}
		}
		return customer;			
	}	
}
