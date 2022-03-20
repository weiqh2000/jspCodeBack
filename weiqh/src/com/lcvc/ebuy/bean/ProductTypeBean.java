package com.lcvc.ebuy.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.lcvc.ebuy.model.Admin;
import com.lcvc.ebuy.model.Page;
import com.lcvc.ebuy.model.ProductType;

public class ProductTypeBean {

	/**
	 * 查询ProductType并实现分页
	 * */
	public List<ProductType> pageProductTypes(Page page){
		
		List<ProductType> productTypes = new ArrayList<ProductType>(); 
		try {
			Connection connection = DBHelper.getConnection();
			String sql = "select * from producttype limit ?, ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, page.getPageSize() * (page.getPageNow() - 1));
			pstmt.setInt(2, page.getPageSize());
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				ProductType productType=new ProductType();
				productType.setId(rs.getInt("id"));
				productType.setName(rs.getString("name"));
				productType.setLinkUrl(rs.getString("linkUrl"));
				productType.setImageUrl(rs.getString("imageUrl"));
				productType.setIntro(rs.getString("intro"));
				productType.setOrderNum(rs.getInt("orderNum"));
	  			String img_YesOrNo = productType.getImageUrl() == "" || productType.getImageUrl() == null ? "无图片":"有图片";
	  			String link_YestOrNo = productType.getLinkUrl() == "" || productType.getLinkUrl() == null ? "无":"有";
				productType.setImg_YesOrNo(img_YesOrNo);
				productType.setLink_YestOrNo(link_YestOrNo);
				productType.setProductQuantity(queryproductQuantity(productType.getId()));
				productTypes.add(productType);
			}
			DBHelper.close(connection, pstmt, rs);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return productTypes;
	}
	
	/**
	 * 查询产品数量
	 * */
	public int queryproductQuantity(Integer productTypeId){
		int sum = 1;
		try {
			Connection connection = DBHelper.getConnection();
			String sql="select count(*) from product where productTypeId = ?";
			PreparedStatement pstmt=connection.prepareStatement(sql);
			pstmt.setInt(1, productTypeId);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				sum = rs.getInt("count(*)");
			}
			DBHelper.close(connection, pstmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return sum;
	}
	/**
	 * 实现分页
	 * */
	public Page page(Page page){
		page.setPageSize(5);
		try {
			Connection connection = DBHelper.getConnection();
			String sql = "select count(*) from producttype";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				page.setLineCount(rs.getInt(1));
			}
			page.setPageCount(page.getLineCount() % page.getPageSize() == 0 ? page.getLineCount() / page.getPageSize() : page.getLineCount() / page.getPageSize() + 1);
			if(page.getPageNow() == 0){
				page.setPageNow(1);
			}else if(page.getPageNow() > page.getPageCount()){
				page.setPageNow(page.getPageCount());
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return page;
	}
	
	
	/**
	 * 查询ProductType不实现分页
	 * */
	public List<ProductType> getProductTypes(){
		List<ProductType> productTypes = new ArrayList<ProductType>(); 
		try {
			Connection connection = DBHelper.getConnection();
			String sql = "select * from producttype";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				ProductType productType=new ProductType();
				productType.setId(rs.getInt("id"));
				productType.setName(rs.getString("name"));
				productType.setLinkUrl(rs.getString("linkUrl"));
				productType.setImageUrl(rs.getString("imageUrl"));
				productType.setIntro(rs.getString("intro"));
				productType.setOrderNum(rs.getInt("orderNum"));
	  			String img_YesOrNo = productType.getImageUrl() == "" || productType.getImageUrl() == null ? "无图片":"有图片";
	  			String link_YestOrNo = productType.getLinkUrl() == "" || productType.getLinkUrl() == null ? "无":"有";
				productType.setImg_YesOrNo(img_YesOrNo);
				productType.setLink_YestOrNo(link_YestOrNo);
				productType.setProductQuantity(queryproductQuantity(productType.getId()));
				productTypes.add(productType);
			}
			DBHelper.close(connection, pstmt, rs);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return productTypes;
	}
	
	/**
	 * 
	 * 删除产品分类管理条目
	 * */
	public boolean deleteProductType(Integer id){
		boolean status=false;
		try{
			if(queryproductQuantity(id) == 0){
				Connection connection=DBHelper.getConnection();
				 String sql="delete from producttype where id = ?";//表示占位符
				 PreparedStatement pstmt = connection.prepareStatement(sql);//这个对象可以用来将sql语句发送到数据库（准备发送
				 pstmt.setInt(1, id);
				//数据库的增加、编辑、删除记录用executeUpdate().executeUpdate()会将影响的记录结果，即删除了多少条记录以整数返回
				 int number = pstmt.executeUpdate();
				 if (number > 0){
		  	    	 status=true;
		  	     }
				 DBHelper.close(connection,pstmt,null);//关闭数据库 
			}
		 } catch(SQLException e){//catch表示，如果出现SQLException异常，就执行括号内的代码
				e.printStackTrace();
		}
		return status;
	}

	/**
	 * 实现添加产品类型的添加
	 * */
	public int saveProductType(ProductType productType){
   	 int status = 0;//默认添加重名
   	 try{
   		 Connection connection = DBHelper.getConnection();
   		 //该语句实现重置mysql表的自增计数器
   		 String sql_AUTO_INCREMENT = "ALTER TABLE producttype AUTO_INCREMENT = 1";
   		 //该语句实现name字段的查重
   		 String sql_repetition = "select count(name) from producttype where FIND_IN_SET(?, name)";
   		 //该语句实现添加
   		 String sql_INSERT = "INSERT INTO producttype VALUES(null, ?, ?, ?, ?, ?)";
   		 //装入PreparedStatement方法
   		 PreparedStatement pstmt_AUTO_INCREMENT = connection.prepareStatement(sql_AUTO_INCREMENT);
   		 PreparedStatement pstmt_repetition = connection.prepareStatement(sql_repetition);
   		 PreparedStatement pstmt_INSERT = connection.prepareStatement(sql_INSERT);
   		 
   		 //默认重名
   		 int number_repetition = 0;
   		 pstmt_repetition.setString(1, productType.getName());
		 ResultSet rs = pstmt_repetition.executeQuery();
		 if(rs.next()){
			 //将返回的对象强转为int类型
			 number_repetition = rs.getInt(1);
			 if(number_repetition == 0){
				 
			 }else{
				 //重名
				 status = 2;
			 }
		 }else{
			 
		 }
		 
		 //如果不重名
   		 if(number_repetition == 0){
   			
   			//定义productType对象的值
   			pstmt_INSERT.setString(1, productType.getName());
   			pstmt_INSERT.setString(2, productType.getLinkUrl());
   			pstmt_INSERT.setString(3, productType.getImageUrl());
   			pstmt_INSERT.setString(4, productType.getIntro());
   			pstmt_INSERT.setInt(5, productType.getOrderNum());
   			
   			
   			int number_AUTO_INCREMENT = pstmt_AUTO_INCREMENT.executeUpdate();
   			
   			int number_INSERT = pstmt_INSERT.executeUpdate();
   			
   			
   			if(number_INSERT > 0 || number_AUTO_INCREMENT >0){
   				status = 1;
   			}else{
   				
   			}
   		 }
   		
   	 } catch(SQLException e){
   		 e.printStackTrace();
   	 } 
   	 return status;
    }
	
	
	
	/**
     **修改用户
     *实现思路，先将改名字，查询有没有重名，如果有，就改回去，如果没有，就不改了，改其他参数
     *@param 返回1表示无用户名需要修改且修改成功 返回2表示用户名修改成功 返回0表示有重名
     */
    public  int deitProductType(ProductType productType){
   	 //默认全都不能更改
   	int status = 2;
 		try {
 			//创建数据库连接
 			Connection connection = DBHelper.getConnection();
 			
 			
 			
 			ProductType productTypeTmp = getProductType(productType.getId());

 			
 			
 			String sql_name_update = "update producttype set name=? where id=?";
 			
 			PreparedStatement pstmt_name = connection.prepareStatement(sql_name_update);
 			
 			
 			pstmt_name.setString(1, productType.getName());
 			pstmt_name.setInt(2, productType.getId());
 			pstmt_name.executeUpdate();
 			
 			
 			
 			
 			
 			
 			
 			String sql_repetition = "select count(name) from producttype where FIND_IN_SET(?, name)";
 			
 	   		PreparedStatement pstmt_repetition = connection.prepareStatement(sql_repetition);
 	   		 
 	   		 
 	   		
 	   		 
 	   		pstmt_repetition.setString(1, productType.getName()); 
 	   		 
 	   		ResultSet rs = pstmt_repetition.executeQuery();
 	   		 
 	   		int number_repetition = 0; 
 	   		 
	 	   	if(rs.next()){
	 	   		
				 number_repetition = rs.getInt(1);

			 }
 	   		 
	 	   if(number_repetition == 1 || number_repetition == 0){
				 
				 String sql_update = "update producttype set name=?, linkUrl=?, imageUrl= ?, intro=?, orderNum=? where id=?";
	 	   			
					PreparedStatement pstmt_update = connection.prepareStatement(sql_update);
	 	   			
	 	   			pstmt_update.setString(1, productType.getName());
	 	   			pstmt_update.setString(2, productType.getLinkUrl());
	 	   			pstmt_update.setString(3, productType.getImageUrl());
	 	   			pstmt_update.setString(4, productType.getIntro());
	 	   			pstmt_update.setInt(5, productType.getOrderNum());
	 	   			pstmt_update.setInt(6, productType.getId());
	 	   			
	 	   			
	 	   			pstmt_update.executeUpdate();
	 	   			
	 	   			status = 0;
		 	   		
	 	   		
			 }else if(number_repetition > 1){
				 String sql_name_recover = "update producttype set name=? where id=?";
		 			
		 			PreparedStatement pstmt_name_recover = connection.prepareStatement(sql_name_recover);
		 			
		 			
		 			pstmt_name_recover.setString(1, productTypeTmp.getName());
		 			pstmt_name_recover.setInt(2, productTypeTmp.getId());
		 			pstmt_name_recover.executeUpdate();
		 			status = 1;
			 }

 			
		} catch (SQLException e) {
			e.printStackTrace();
		}
 		return status;
 	}
    
    /**通过userId获取对应用户信息
     * @param 返回该用户对象
     * */
    public ProductType getProductType(Integer Id){
    	ProductType productType=null;
		try {
			Connection connection = DBHelper.getConnection();
			String sql="select * from producttype where id= ? ";
			PreparedStatement pstmt=connection.prepareStatement(sql);
			pstmt.setInt(1, Id);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				productType=new ProductType();
				productType.setId(rs.getInt("id"));
				productType.setName(rs.getString("name"));
				productType.setLinkUrl(rs.getString("linkUrl"));
				productType.setImageUrl(rs.getString("imageUrl"));
				productType.setIntro(rs.getString("intro"));
				productType.setOrderNum(rs.getInt("orderNum"));
			}
			DBHelper.close(connection, pstmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return productType;
	}

    /*
	 * 定义数据库的查询方法,根据id获取产品类别对象，该数据库用于配合其他数据库操作方法(共享Connection)
	 * 
	 * @param id 账户标识符
	 * 
	 * @return null表示获取失败
	 */
	public ProductType getProductType(Object id,Connection conn) {
		ProductType productType = null;// 最后返回的对象
		Integer productTypeId = null;
		if (id != null) {
			try {
				String s = id.toString();
				productTypeId = Integer.parseInt(s);
			} catch (Exception e) {
				productTypeId = null;
			}
		}
		if (productTypeId != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "select * from producttype where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, productTypeId);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					productType = new ProductType();
					productType.setId(rs.getInt("id"));
					productType.setImageUrl(rs.getString("imageUrl"));
					productType.setIntro(rs.getString("intro"));
					productType.setLinkUrl(rs.getString("linkUrl"));
					productType.setName(rs.getString("name"));
					productType.setOrderNum(rs.getInt("orderNum"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBHelper.close(null, pstmt, rs);
			}
		}
		return productType;
	}


    
	@Override
	public String toString() {
		return "ProductTypeBean []";
	}
    
}
