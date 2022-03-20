package com.lcvc.ebuy.bean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import com.lcvc.ebuy.model.Admin;
/*
 * AdminBean,用来处理业务方法
 */
import com.lcvc.ebuy.model.Product;

public class AdminBean {
	
	/**
	 * 处理管理账户登录
	 * @param username 账户名
	 * @param password 密码
	 * @return true表示登陆成功，false表示登陆失败
	 * **/
	public boolean login(String username,String password){
		boolean result=false;
		try {
			Connection connection = DBHelper.getConnection();
			//定义一个字符串
			String sql="select count(1) from admin where username= ? and userpass= ? ";							
			//这个对象可以用来将sql语句发送到数据库（准备发送）
			PreparedStatement pstmt=connection.prepareStatement(sql);	
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			//pstmt.executeQuery()表示将sql语句真的发送到数据库，并返回查询结果
			ResultSet rs=pstmt.executeQuery();					
			if(rs.next()){
				int number = rs.getInt(1);
				if(number == 1){
					result=true;
				}else{
					
				}
			}else{
				
			}
			DBHelper.close(connection, pstmt, rs);								//关闭数据库
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			return result;
	}
	
	
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
					admin.setUserpass(rs.getString("userpass"));
					admin.setScreenName(rs.getString("screenName"));
					admin.setCreateTime(rs.getTimestamp("createTime"));
				}			
			}catch(Exception e){
				e.printStackTrace();
			}finally{
			}
		}
		return admin;			
	}	
	
	/**
	 * 根据账户名获取相应的Admin对象(模拟从数据库读取)
	 * @param username
	 * @return null表示没有找到
	 * */
	public  Admin getAdmin(String username){
		Admin admin=null;
		try {
			Connection connection = DBHelper.getConnection();
			//定义一个字符串
			String sql="select * from admin where username= ? ";
			//这个对象可以用来将sql语句发送到数据库（准备发送）
			PreparedStatement pstmt=connection.prepareStatement(sql);
			pstmt.setString(1, username);
			//pstmt.executeQuery()表示将sql语句真的发送到数据库，并返回查询结果
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				admin=new Admin();
				admin.setUsername(rs.getString("username"));
				admin.setUserpass(rs.getString("userpass"));
				admin.setScreenName(rs.getString("screenName"));
				admin.setUserId(rs.getInt("userId"));
			}
			DBHelper.close(connection, pstmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return admin;
	}
	
	/**
	 * 从数据库中读取admin表的所有信息
	 * */
	public List<Admin> getAdmins(){
		//定义一个List集合
		List<Admin> admins = new ArrayList<Admin>(); 
		try {
			Connection connection = DBHelper.getConnection();
			String sql = "select * from admin";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				Admin admin=new Admin();
				admin.setUsername(rs.getString("username"));
				admin.setUserpass(rs.getString("userpass"));
				admin.setScreenName(rs.getString("screenName"));
				admin.setUserId(rs.getInt("userId"));
				admin.setCreateTime(rs.getTimestamp("createTime"));
				admins.add(admin);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return admins;
	}
	
	
	/**
     * 删除指定账户
     * @param userId admin的标志符，主键
     * return true 表示删除成功，false表示删除失败
     */
     public boolean deleteAamin(Integer userId){
    	 boolean status=false;//默认删除失败
    	 try{
    		 Connection connection=DBHelper.getConnection();
//    		 String sqlFlush1 = "SET @count = 0;";
//    		 String sqlFlush2 = "UPDATE `admin` SET `userId` = @count:= @count + 1;";
    		 String sql="delete from admin where userId=?";//表示占位符
    		 PreparedStatement pstmt = connection.prepareStatement(sql);//这个对象可以用来将sql语句发送到数据库（准备发送
//    		 PreparedStatement pstmtFlush1 = connection.prepareStatement(sqlFlush1);//这个对象可以用来将sql语句发送到数据库（准备发送
//    		 PreparedStatement pstmtFlush2 = connection.prepareStatement(sqlFlush2);//这个对象可以用来将sql语句发送到数据库（准备发送
    		 pstmt.setInt(1, userId);
    		 int number = pstmt.executeUpdate();//数据库的增加、编辑、删除记录用executeUpdate().executeUpdate()会将影响的记录结果，即删除了多少条记录以整数返回
//    		 int number1 = pstmtFlush1.executeUpdate();//数据库的增加、编辑、删除记录用executeUpdate().executeUpdate()会将影响的记录结果，即删除了多少条记录以整数返回
//    		 int number2 = pstmtFlush2.executeUpdate();//数据库的增加、编辑、删除记录用executeUpdate().executeUpdate()会将影响的记录结果，即删除了多少条记录以整数返回
//    		 if (number > 0 || number1 > 0 || number2 > 0){
//      	    	 status=true;
//      	     }else{
//      	    	 
//      	     }
    		 if (number > 0){
      	    	 status=true;
      	     }else{
      	    	 
      	     }
    		 DBHelper.close(connection,pstmt,null);//关闭数据库  
//    		 DBHelper.close(connection,pstmtFlush1,null);
//    		 DBHelper.close(connection,pstmtFlush2,null);
    	 } catch(SQLException e){//catch表示，如果出现SQLException异常，就执行括号内的代码
				e.printStackTrace();
		}
		return status;
     }
	
     /**
      * 查询用户是否存在
      * @param userId 
      * @param return true 表示有此账户, false 表示没有此账户
      * */ 
      public static boolean queryUsername(String username){
    	  boolean status=false;
  		try {
  			Connection connection = DBHelper.getConnection();
  			String sql="select count(1) from admin where username= ?";
  			PreparedStatement pstmt=connection.prepareStatement(sql);
  			pstmt.setString(1, username);
  			ResultSet rs=pstmt.executeQuery();
  			if(rs.next()){
				int number = rs.getInt(1);
				if(number > 0){
					status = true;
				}
			}else{
				
			}
  			DBHelper.close(connection, pstmt, rs);
  			} catch (SQLException e) {
  				e.printStackTrace();
  			}
  		return status;
  	}
     
	/**
	 * 保存添加的账户信息
	 * @param userId admin的标志符，主键
	 * @param return true 表示保存成功 ，false表示失败
	 * */
     public boolean saveAdmin(Admin admin){
    	 boolean status=false;//默认添加失败
    	 try{
    		 Connection connection = DBHelper.getConnection();
    		 String sql_AUTO_INCREMENT = "ALTER TABLE admin AUTO_INCREMENT = 1;";
    		 String sql_two = "INSERT INTO admin VALUES(null,?,?,?,now());";
    		 PreparedStatement pstmt_one = connection.prepareStatement(sql_AUTO_INCREMENT);
    		 PreparedStatement pstmt_two = connection.prepareStatement(sql_two);
    		 if(AdminBean.queryUsername(admin.getUsername()) == false){
    			 pstmt_two.setString(1, admin.getUsername());
        		 pstmt_two.setString(2, admin.getUserpass());
        		 pstmt_two.setString(3, admin.getScreenName());
        		 int number_one = pstmt_one.executeUpdate();
        		 int number_two = pstmt_two.executeUpdate();//数据库的增加、编辑、删除记录用executeUpdate().executeUpdate()会将影响的记录结果，即删除了多少条记录以整数返回
        		 if(number_one > 0 || number_two > 0){
        			status=true;
        		 } else{
        			 
        		 }
    		 }else{
    			 
    		 }
    		 DBHelper.close(connection, pstmt_one, null);
    		 DBHelper.close(connection, pstmt_two, null);//关闭数据库
    	 } catch(SQLException e){
    		 e.printStackTrace();
    	 }
    	 return status;
     }
     
     /**通过userId获取对应用户信息
      * @param 返回该用户对象
      * */
     public  Admin getAdmin(Integer userId){
 		Admin admin=null;
 		try {
 			Connection connection = DBHelper.getConnection();
 			String sql="select * from admin where userId= ? ";
 			PreparedStatement pstmt=connection.prepareStatement(sql);
 			pstmt.setInt(1, userId);
 			ResultSet rs=pstmt.executeQuery();
 			if(rs.next()){
 				admin=new Admin();
 				admin.setUsername(rs.getString("username"));
 				admin.setUserpass(rs.getString("userpass"));
 				admin.setScreenName(rs.getString("screenName"));
 				admin.setUserId(rs.getInt("userId"));
 				admin.setCreateTime(rs.getTimestamp("createTime"));
 			}
 			DBHelper.close(connection, pstmt, rs);
 			} catch (SQLException e) {
 				e.printStackTrace();
 			}
 		return admin;
 	}
     
     /**
      **修改用户
      *@param 返回1表示无用户名需要修改且修改成功 返回2表示用户名修改成功 返回0表示有重名
      */
     public  int deitAdmin(Admin admin){
    	 //默认全都不能更改
    	int status = 0;
  		try {
  			//创建数据库连接
  			Connection connection = DBHelper.getConnection();
  			/*
  			 * 该模块实现查询当前需要修改的用户名与
  			 * */
  			String queryDistinct ="select * from admin where userId= ?";
  			PreparedStatement pstmtQueryDistinct = connection.prepareStatement(queryDistinct);
  			pstmtQueryDistinct.setInt(1, admin.getUserId());
  			ResultSet rs=pstmtQueryDistinct.executeQuery();
  			Admin activeUser=new Admin();
  			while(rs.next()){
  				activeUser.setUsername(rs.getString("username"));
  				activeUser.setUserpass(rs.getString("userpass"));
  				activeUser.setScreenName(rs.getString("screenName"));
  				activeUser.setUserId(rs.getInt("userId"));
  				activeUser.setCreateTime(rs.getTimestamp("createTime"));
			}
  			DBHelper.close(connection, pstmtQueryDistinct, rs);
  			String adminUsername = new String(admin.getUsername());
  			String activeUsername = new String(activeUser.getUsername());
  			//判断要用户名是否需要修改
  			if(adminUsername.equals(activeUsername)){
  				Connection connection1 = DBHelper.getConnection();
  				String deitSql="update admin set screenName = ?, userpass = ? where userId = ?";
				PreparedStatement pstmt = connection1.prepareStatement(deitSql);
				pstmt.setString(1, admin.getScreenName());
				pstmt.setString(2, admin.getUserpass());
	  			pstmt.setInt(3, admin.getUserId());
  	  			int number = pstmt.executeUpdate();
  	  			if(number == 1){
  	  				status = 1;
  	  			}
  	  			DBHelper.close(connection1, pstmt, null);
  			//判断需要修改的用户名是否重名
  			}else if(AdminBean.queryUsername(admin.getUsername()) == false){
  				Connection connection1 = DBHelper.getConnection();
  				String deitSql="update admin set username = ?, screenName = ?, userpass = ? where userId = ?";
  				PreparedStatement pstmt = connection1.prepareStatement(deitSql);
  				pstmt.setString(1, admin.getUsername());
  				pstmt.setString(2, admin.getScreenName());
  				pstmt.setString(3, admin.getUserpass());
  	  			pstmt.setInt(4, admin.getUserId());
  	  			int number = pstmt.executeUpdate();
  	  			if(number == 1){
  	  				status = 2;
  	  			}
  	  			DBHelper.close(connection1, pstmt, null);
  			//如果重名就全部数值不给予修改
  			}else{
  				status = 0;
  			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
  		return status;
  	}
     
     /**
      * 获取旧密码    实现与新密码的比对
      * */
     public String getPassword(Integer Id){
    	 String status = "0";
 		try {
 			Connection connection = DBHelper.getConnection();
 			String sql="select userpass from admin where userid= ?";
 			PreparedStatement pstmt=connection.prepareStatement(sql);
 			pstmt.setInt(1, Id);
 			ResultSet rs=pstmt.executeQuery();
 			if(rs.next()){
 				status = rs.getString("userpass");
			}
 			DBHelper.close(connection, pstmt, rs);
 			} catch (SQLException e) {
 				e.printStackTrace();
 			}
 		return status;
 	}
     
     /**
      * 修改密码
      * 将输入的旧密码与数据库的密码进行比对
      * */
     public String changePassword(Integer Id, String oldPassword, String newPassword){
    	 String status = "1";

    		 if(getPassword(Id).equals(oldPassword)){
    			 try {	 
    				 Connection connection = DBHelper.getConnection();
    				 String sql_password="update admin set userpass = ? where userId = ?";
    				 PreparedStatement pstmt_password = connection.prepareStatement(sql_password);
    				 pstmt_password.setString(1, newPassword);
    				 pstmt_password.setInt(2, Id);
    				 int number = pstmt_password.executeUpdate();
    				 if(number > 0){
    					 status = "0"; 
    				 }
				} catch (SQLException e) {
					e.printStackTrace();
				} 
    		 }else{
    			 status = "2"; 
    		 }
    		 
    	 return status;
     }
     
     
     /**
      * 修改个人信息
      * */
     public  int deitMyadmin(Admin admin){
       	 //默认全都不能更改
       	int status = 2;
     		try {
     			//创建数据库连接
     			Connection connection = DBHelper.getConnection();
     			
     			
     			Admin adminTmp = getAdmin(admin.getUserId());
     			
     			
     			String sql_name_update = "update admin set username=? where userId=?";
     			
     			PreparedStatement pstmt_name = connection.prepareStatement(sql_name_update);
     			
     			
     			pstmt_name.setString(1, admin.getUsername());
     			pstmt_name.setInt(2, admin.getUserId());
     			pstmt_name.executeUpdate();
     			
     			
     			
     			
     			
     			
     			
     			String sql_repetition = "select count(username) from admin where FIND_IN_SET(?, username)";
     			
     	   		PreparedStatement pstmt_repetition = connection.prepareStatement(sql_repetition);
     	   		 
     	   		 
     	   		
     	   	
     	   		pstmt_repetition.setString(1, admin.getUsername()); 
     	   		 
     	   		ResultSet rs = pstmt_repetition.executeQuery();
     	   		 
     	   		int number_repetition = 0; 
     	   		 
    	 	   	if(rs.next()){
    	 	   	
    				 number_repetition = rs.getInt(1);
    				 
    			 }
    	 	   
    	 	   if(number_repetition == 1 || number_repetition == 0){
    	 		  
    				 String sql_update = "update admin set username = ?, screenName = ?  where userId=?";
    	 	   			
    					PreparedStatement pstmt_update = connection.prepareStatement(sql_update);
    	 	   			
    					
    					pstmt_update.setString(1, admin.getUsername());
    					pstmt_update.setString(2, admin.getScreenName());
    					pstmt_update.setInt(3, admin.getUserId());

    	 	   			
    	 	   			pstmt_update.executeUpdate();
    	 	   			
    	 	   			status = 0;
    		 	   		
    	 	   		
    			 }else if(number_repetition > 1){
    				 String sql_name_recover = "update admin set username = ? where userId=?";
    		 			
    		 			PreparedStatement pstmt_name_recover = connection.prepareStatement(sql_name_recover);
    		 			
    		 			
    		 			pstmt_name_recover.setString(1, adminTmp.getUsername());
    		 			pstmt_name_recover.setInt(2, adminTmp.getUserId());
    		 			pstmt_name_recover.executeUpdate();
    		 			status = 1;
    			 }

     			
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
     		return status;
     	}

     
     /**
      * toString方法
      * 重写返回的地址值，更好用于测试
      * */
	@Override
	public String toString() {
		return "AdminBean []";
	}

	
	
}
