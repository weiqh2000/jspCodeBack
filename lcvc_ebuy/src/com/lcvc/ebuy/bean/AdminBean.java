package com.lcvc.ebuy.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.lcvc.ebuy.model.Admin;
import com.lcvc.ebuy.model.exception.MyFormException;
import com.mysql.jdbc.Statement;
/*
 * 管理账户bean
 */
public class AdminBean {
	
	/*
	 * 获取管理账户的所有记录
	 * @return null表示获取失败
	 */
	public List<Admin> getAdmins(){	
		List<Admin> list = new ArrayList<Admin>();
		Connection conn=DBHelper.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Admin admin=null;
		try{
			String sql="select * from admin";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				admin=new Admin();
				admin.setUserId(rs.getInt("userId"));
				admin.setUsername(rs.getString("username"));
				admin.setPassword(rs.getString("password"));
				admin.setScreenName(rs.getString("screenName"));
				admin.setCreateTime(rs.getDate("createTime"));
				list.add(admin);
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBHelper.closeConn(conn,pstmt,rs);
		}
		return list;			
	}	
	
	/*
	 * 定义数据库的查询方法,根据id获取账户对象
	 * @param id 账户标识符
	 * @return null表示获取失败
	 */
	public Admin getAdmin(Object id){	
		Admin admin=null;//最后返回的对象
		Integer userId=null;
		if(id!=null){
			try {
				String s=id.toString();
				userId=Integer.parseInt(s);
			}catch(Exception e){
				userId=null;
			}
		}
		if(userId!=null){
			Connection conn=DBHelper.getConnection();
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try{
				String sql="select * from admin where userId = ?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, userId);
				rs=pstmt.executeQuery();
				if(rs.next()){
					admin=new Admin();
					admin.setUserId(rs.getInt("userId"));
					admin.setUsername(rs.getString("username"));
					admin.setPassword(rs.getString("password"));
					admin.setScreenName(rs.getString("screenName"));
					admin.setCreateTime(rs.getTimestamp("createTime"));
				}			
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				DBHelper.closeConn(conn,pstmt,rs);
			}
		}
		return admin;			
	}	
	
	
	
	
	/*
	 * 根据传入的参数添加到数据库，并返回插入数据库后的标识符（适合数据库的自增字段）
	 * @param admin 要添加的对象,如果密码字段为NULL则默认密码是123456
	 * @return null表示插入失败
	 */
	public Integer addAdmin(Admin admin) throws MyFormException{	
		Integer userId=null;//获取插入记录后的标识符
		//进行表单验证
		if(admin!=null){
			String username=admin.getUsername();
			if(username==null||username.trim().equals("")){
				throw new MyFormException("用户名不能为空");
			}
			if(username.length()<3||username.length()>20){
				throw new MyFormException("用户名长度不符合要求");
			}
			if(this.getCountByUsername(username)>0){
				throw new MyFormException("该用户名已存在，请重命名");
			}
			String password=admin.getPassword();
			if(password!=null){
				if(password.length()<6||password.length()>50){
					throw new MyFormException("密码长度不符合要求");
				}
			}else{//如果表单没有密码文本框
				admin.setPassword("123456");
			}
			String screenName=admin.getScreenName();
			if(screenName==null||screenName.trim().equals("")){
				throw new MyFormException("名字不能为空");
			}
			if(screenName.length()<2||screenName.length()>20){
				throw new MyFormException("姓名长度不符合要求");
			}
		}else{
			throw new MyFormException("表单不能为空");
		}
		Connection conn=DBHelper.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs = null; 
		try{
			String sql="insert into admin(userId,username,password,screenName,createTime) values(null,?,?,?,?)";
			pstmt=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);//要获取插入记录后的主键做法
			pstmt.setString(1, admin.getUsername());
			pstmt.setString(2, admin.getPassword());
			pstmt.setString(3, admin.getScreenName());
			pstmt.setTimestamp(4, new Timestamp(Calendar.getInstance().getTimeInMillis()));//当前时间作为创建时间
			int i = pstmt.executeUpdate(); 
			if(i>0){//如果插入成功
				rs = pstmt.getGeneratedKeys();//获取插入后的标识符
				if (rs.next()) {
					userId = rs.getInt(1); 
				}
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBHelper.closeConn(conn,pstmt,rs);
		}
		return userId;			
	}	
	
	/*
	 * 根据传入的参数更新数据库
	 * @param admin 要更新的对象
	 * @return true表示更新成功，false表示更新失败
	 */
	public boolean updateAdmin(Admin admin) throws MyFormException{	
		boolean flag=false;//获取最后的更新状态，false表示失败
		Admin adminOfOriginal=null;//最终要更新的对象
		if(admin!=null){
			adminOfOriginal=getAdmin(admin.getUserId());//从数据库判断该账户是否还存在
			if(adminOfOriginal!=null){
				String username=admin.getUsername();
				if(username!=null){
					if(username.length()>=3&&username.length()<=20){
						if(this.getCountByUsername(username,adminOfOriginal.getUserId())==0){
							adminOfOriginal.setUsername(username);
						}else{
							throw new MyFormException("该用户名已存在，请重命名");
						}
					}else{
						throw new MyFormException("用户名长度不符合要求");
					}
				}
				String password=admin.getPassword();
				if(password!=null){
					if(password.length()>=6&&password.length()<=50){
						adminOfOriginal.setPassword(password);
					}else{
						throw new MyFormException("密码长度不符合要求");
					}
					adminOfOriginal.setPassword(password);
				}
				String screenName=admin.getScreenName();
				if(screenName!=null){
					if(screenName.length()>=2&&screenName.length()<=20){
						adminOfOriginal.setScreenName(screenName);
					}else{
						throw new MyFormException("名字长度不符合要求");
					}
				}
			}
		}
		if(adminOfOriginal!=null){
			Connection conn=DBHelper.getConnection();
			PreparedStatement pstmt=null;
			try{
				String sql="update admin set username=?,password=?,screenName=? where userId=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, adminOfOriginal.getUsername());
				pstmt.setString(2, adminOfOriginal.getPassword());
				pstmt.setString(3, adminOfOriginal.getScreenName());
				pstmt.setInt(4, adminOfOriginal.getUserId());
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
	 * 删除指定的对象，不能自己删除自己的账户
	 * @param id 被删除账户的标识符
	 * @param adminId 执行删除账户的标识符
	 * @return true表示删除成功，false表示删除失败
	 */
	public boolean deleteAdmin(Object id,Integer adminId) throws MyFormException{	
		boolean flag=false;//false表示失败
		Admin admin=null;//最后返回的对象
		Integer userId=null;
		if(id!=null){
			try {
				String s=id.toString();
				userId=Integer.parseInt(s);
			}catch(Exception e){
				userId=null;
			}
		}
		if(userId!=null){
			if(adminId!=userId.intValue()){
				Connection conn=DBHelper.getConnection();
				PreparedStatement pstmt=null;
				try{
					String sql="delete from admin where userId=?";
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, userId);
					int i = pstmt.executeUpdate(); 
					if(i>0){
						flag=true;
					}			
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					DBHelper.closeConn(conn,pstmt,null);
				}
			}else{
				throw new MyFormException("不能删除自己的账户");
			}
		}
		return flag;			
	}	
	
	/*
	 * 定义数据库的查询方法,查询用户名和密码是否正确
	 * @param username 用户名
	 * @param passowrd 密码
	 * @return null表示登录失败
	 */
	public Admin login(String username,String password){	
		Admin admin=null;//最后返回的对象
		Connection conn=DBHelper.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			String sql="select * from admin where username=? and password=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if(rs.next()){
				admin=new Admin();
				admin.setUserId(rs.getInt("userId"));
				admin.setUsername(rs.getString("username"));
				admin.setPassword(rs.getString("password"));
				admin.setScreenName(rs.getString("screenName"));
				admin.setCreateTime(rs.getDate("createTime"));
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBHelper.closeConn(conn,pstmt,rs);
		}
		return admin;			
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
			String sql="select count(userId) from admin where username=?";
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
	
	/*
	 * 根据用户名并且是非当前账户，获取相同用户名的用户数量
	 * 用于账户编辑时判断用户名是否已经存在
	 * @param username用户名
	 * @return 返回相同用户名的数量。
	 */
	public int getCountByUsername(String username,Integer userId){	
		Connection conn=DBHelper.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try{
			String sql="select count(userId) from admin where username=? and userId!=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setInt(2, userId);
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
	 * 定义数据库的查询方法,根据id获取账户对象，该数据库用于配合其他数据库操作方法(共享Connection)
	 * @param id 账户标识符
	 * @return null表示获取失败
	 */
	public Admin getAdmin(Integer userId,Connection conn){	
		Admin admin=null;//最后返回的对象
		if(userId!=null){
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try{
				String sql="select * from admin where userId=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, userId);
				rs=pstmt.executeQuery();
				if(rs.next()){
					admin=new Admin();
					admin.setUserId(rs.getInt("userId"));
					admin.setUsername(rs.getString("username"));
					admin.setPassword(rs.getString("password"));
					admin.setScreenName(rs.getString("screenName"));
					admin.setCreateTime(rs.getTimestamp("createTime"));
				}			
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				DBHelper.closeResultSet(rs);
				DBHelper.closePreparedStatement(pstmt);
			}
		}
		return admin;			
	}	
}
