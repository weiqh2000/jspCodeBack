package com.lcvc.ebuy.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lcvc.ebuy.model.Admin;
import com.lcvc.ebuy.model.Page;
import com.lcvc.ebuy.model.Product;
import com.lcvc.ebuy.model.ProductType;
import com.lcvc.ebuy.model.PageObject;
import com.lcvc.ebuy.util.PageUtils;

public class ProductBean {

	/**
	 * 实现查询并且分页
	 * */
	public List<Product> pageProducts(Page page){
		List<Product> products = new ArrayList<Product>();
		try {
			Connection connection = DBHelper.getConnection();
			String sql = "select * from product limit ?, ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, page.getPageSize() * (page.getPageNow() - 1));
			pstmt.setInt(2, page.getPageSize());
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				Product product=new Product();
				product.setId(rs.getInt("id"));
				product.setProductTypeId(rs.getInt("productTypeId"));
				product.setName(rs.getString("name"));
				product.setOrderNum(rs.getInt("orderNum"));
				product.setDescription(rs.getString("description"));
				product.setContent(rs.getString("content"));
				product.setPrice(rs.getFloat("price"));
				product.setOriginalPrice(rs.getFloat("originalPrice"));
				product.setPicUrl(rs.getString("picUrl"));
				product.setNumber(rs.getInt("number"));
				product.setClick(rs.getInt("click"));
				product.setOnSale(rs.getBoolean("onSale"));
				product.setCreateTime(rs.getTimestamp("createTime"));
				product.setCreatorId(rs.getInt("creatorId"));
				product.setFinalEditorId(rs.getInt("finalEditorId"));
				product.setUpdateTime(rs.getTimestamp("updateTime"));
				product.setAdmin(getAdmin(rs.getInt("creatorId")));
				product.setProductType(getProductTypeProductBean(rs.getInt("productTypeId")));
				String OnSale_String = product.getOnSale() == true ? "上架中":"下架中";
				product.setOnSale_String(OnSale_String); 
				products.add(product);
			}
			DBHelper.close(connection, pstmt, rs);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return products;
	}
	/**
	 * 查询菜品类别表
	 * 
	 * */
	public  ProductType getProductTypeProductBean(Integer id){
		ProductType productType=null;
 		try {
 			Connection connection = DBHelper.getConnection();
 			String sql="select * from producttype where id= ? ";
 			PreparedStatement pstmt=connection.prepareStatement(sql);
 			pstmt.setInt(1, id);
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
	
	/**
	 * 查询创建用户模块
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
	 * 
	 * 分页模块
	 * */
	public Page page(Page page){
		page.setPageSize(10);
		try {
			Connection connection = DBHelper.getConnection();
			String sql = "select count(*) from product";
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
	 * 查询Product,不分页
	 * */
	public List<Product> getProducts(){
		List<Product> products = new ArrayList<Product>();
		try {
			Connection connection = DBHelper.getConnection();
			String sql = "select * from product";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				Product product=new Product();
				product.setId(rs.getInt("id"));
				product.setProductTypeId(rs.getInt("productTypeId"));
				product.setName(rs.getString("name"));
				product.setOrderNum(rs.getInt("orderNum"));
				product.setDescription(rs.getString("description"));
				product.setContent(rs.getString("content"));
				product.setPrice(rs.getFloat("price"));
				product.setOriginalPrice(rs.getFloat("originalPrice"));
				product.setPicUrl(rs.getString("picUrl"));
				product.setNumber(rs.getInt("number"));
				product.setClick(rs.getInt("click"));
				product.setOnSale(rs.getBoolean("onSale"));
				product.setCreateTime(rs.getTimestamp("createTime"));
				product.setCreatorId(rs.getInt("creatorId"));
				product.setFinalEditorId(rs.getInt("finalEditorId"));
				product.setUpdateTime(rs.getTimestamp("updateTime"));
				product.setAdmin(getAdmin(rs.getInt("creatorId")));
				product.setProductType(getProductTypeProductBean(rs.getInt("productTypeId")));
				String OnSale_String = product.getOnSale() == true ? "上架中":"下架中";
				product.setOnSale_String(OnSale_String); 
				products.add(product);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return products;
	}
	
	/**
	 * 实现添加
	 * 返回1，添加失败 -- 返回2添加重名 -- 返回0添加成功
	 * */
	public int saveProduct(Product product, Integer userId){
	   	 int status = 1;//默认添加失败
	   	 try{
	   		 Connection connection = DBHelper.getConnection();
	   		 //该语句实现重置mysql表的自增计数器
	   		 String sql_AUTO_INCREMENT = "ALTER TABLE product AUTO_INCREMENT = 1";
	   		 
	   		 //该语句实现name字段的查重
	   		 String sql_repetition = "select count(name) from product where FIND_IN_SET(?, name)";
	   		 //该语句实现添加
	   		 String sql_INSERT = "INSERT INTO product VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, null, null)";
	   		 //装入PreparedStatement方法
	   		 PreparedStatement pstmt_AUTO_INCREMENT = connection.prepareStatement(sql_AUTO_INCREMENT);
	   		 PreparedStatement pstmt_repetition = connection.prepareStatement(sql_repetition);
	   		 PreparedStatement pstmt_INSERT = connection.prepareStatement(sql_INSERT);
	   		 
	   		 //默认重名
	   		 int number_repetition = 1;
	   		 
	   		 pstmt_repetition.setString(1, product.getName());
	   		 
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
	   			
	   			//定义product对象的值
	   			pstmt_INSERT.setInt(1, product.getProductTypeId());
	   			pstmt_INSERT.setString(2, product.getName());
	   			pstmt_INSERT.setInt(3, product.getOrderNum());
	   			pstmt_INSERT.setString(4, product.getDescription());
	   			pstmt_INSERT.setString(5, product.getContent());
	   			pstmt_INSERT.setFloat(6, product.getPrice());
	   			pstmt_INSERT.setFloat(7, product.getOriginalPrice());
	   			pstmt_INSERT.setString(8, product.getPicUrl());
	   			pstmt_INSERT.setInt(9, product.getNumber());
	   			pstmt_INSERT.setInt(10, product.getClick());
	   			pstmt_INSERT.setBoolean(11, product.getOnSale());
	   			pstmt_INSERT.setInt(12, userId);
	   			
	   			
	   			int number_AUTO_INCREMENT = pstmt_AUTO_INCREMENT.executeUpdate();
	   			
	   			int number_INSERT = pstmt_INSERT.executeUpdate();
	   			
	   			
	   			if(number_INSERT > 0 || number_AUTO_INCREMENT >0){
	   				status = 0;
	   			}else{
	   				
	   			}
	   		 }
	   	 } catch(SQLException e){
	   		 e.printStackTrace();
	   	 } 
	   	 return status;
	    }
	
	/**
	 * 实现前台的八个展示，并按照创建时间展示
	 * 
	 * */
	public List<Product> indexProducts(){
		List<Product> products = new ArrayList<Product>();
		try {
			Connection connection = DBHelper.getConnection();
			String sql = "select * from product  ORDER BY createTime DESC LIMIT 0, 8";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				Product product=new Product();
				product.setId(rs.getInt("id"));
				product.setProductTypeId(rs.getInt("productTypeId"));
				product.setName(rs.getString("name"));
				product.setOrderNum(rs.getInt("orderNum"));
				product.setDescription(rs.getString("description"));
				product.setContent(rs.getString("content"));
				product.setPrice(rs.getFloat("price"));
				product.setOriginalPrice(rs.getFloat("originalPrice"));
				product.setPicUrl(rs.getString("picUrl"));
				product.setNumber(rs.getInt("number"));
				product.setClick(rs.getInt("click"));
				product.setOnSale(rs.getBoolean("onSale"));
				product.setCreateTime(rs.getTimestamp("createTime"));
				product.setCreatorId(rs.getInt("creatorId"));
				product.setFinalEditorId(rs.getInt("finalEditorId"));
				product.setUpdateTime(rs.getTimestamp("updateTime"));
				product.setAdmin(getAdmin(rs.getInt("creatorId")));
				product.setProductType(getProductTypeProductBean(rs.getInt("productTypeId")));
				String OnSale_String = product.getOnSale() == true ? "上架中":"下架中";
				product.setOnSale_String(OnSale_String); 
				products.add(product);
			}
			DBHelper.close(connection, pstmt, rs);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return products;
	}
	
	/**
	 * 实现前台的八个展示，并按照热度排列
	 * 
	 * */
	public List<Product> indexHotProducts(){
		List<Product> products = new ArrayList<Product>();
		try {
			Connection connection = DBHelper.getConnection();
			String sql = "select * from product  ORDER BY number DESC LIMIT 0, 8";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				Product product=new Product();
				product.setId(rs.getInt("id"));
				product.setProductTypeId(rs.getInt("productTypeId"));
				product.setName(rs.getString("name"));
				product.setOrderNum(rs.getInt("orderNum"));
				product.setDescription(rs.getString("description"));
				product.setContent(rs.getString("content"));
				product.setPrice(rs.getFloat("price"));
				product.setOriginalPrice(rs.getFloat("originalPrice"));
				product.setPicUrl(rs.getString("picUrl"));
				product.setNumber(rs.getInt("number"));
				product.setClick(rs.getInt("click"));
				product.setOnSale(rs.getBoolean("onSale"));
				product.setCreateTime(rs.getTimestamp("createTime"));
				product.setCreatorId(rs.getInt("creatorId"));
				product.setFinalEditorId(rs.getInt("finalEditorId"));
				product.setUpdateTime(rs.getTimestamp("updateTime"));
				product.setAdmin(getAdmin(rs.getInt("creatorId")));
				product.setProductType(getProductTypeProductBean(rs.getInt("productTypeId")));
				product.setOnSale_String((product.getOnSale() == true ? "上架中":"下架中"));
				products.add(product);
			}
			DBHelper.close(connection, pstmt, rs);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return products;
	}
	
	
	public List<Product> pageIndexProducts(Page page, Integer productTypeId){
		List<Product> products = new ArrayList<Product>();
		try {
			Connection connection = DBHelper.getConnection();
			String sql = "select * from product where productTypeId = ? limit ?, ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			page.setPageSize(8);
			pstmt.setInt(1, productTypeId);
			pstmt.setInt(2, page.getPageSize() * (page.getPageNow() - 1));
			pstmt.setInt(3, page.getPageSize());
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				Product product=new Product();
				product.setId(rs.getInt("id"));
				product.setProductTypeId(rs.getInt("productTypeId"));
				product.setName(rs.getString("name"));
				product.setOrderNum(rs.getInt("orderNum"));
				product.setDescription(rs.getString("description"));
				product.setContent(rs.getString("content"));
				product.setPrice(rs.getFloat("price"));
				product.setOriginalPrice(rs.getFloat("originalPrice"));
				product.setPicUrl(rs.getString("picUrl"));
				product.setNumber(rs.getInt("number"));
				product.setClick(rs.getInt("click"));
				product.setOnSale(rs.getBoolean("onSale"));
				product.setCreateTime(rs.getTimestamp("createTime"));
				product.setCreatorId(rs.getInt("creatorId"));
				product.setFinalEditorId(rs.getInt("finalEditorId"));
				product.setUpdateTime(rs.getTimestamp("updateTime"));
				product.setAdmin(getAdmin(rs.getInt("creatorId")));
				product.setProductType(getProductTypeProductBean(rs.getInt("productTypeId")));
				product.setOnSale_String((product.getOnSale() == true ? "上架中":"下架中")); 
				products.add(product);
			}
			DBHelper.close(connection, pstmt, rs);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return products;
	}
	
	/**
	 * 实现删除
	 * 
	 * */
	public boolean deleteProduct(Integer id){
		boolean status=false;
		try{
				Connection connection=DBHelper.getConnection();
				 String sql="delete from product where id = ?";//表示占位符
				 PreparedStatement pstmt = connection.prepareStatement(sql);//这个对象可以用来将sql语句发送到数据库（准备发送
				 pstmt.setInt(1, id);
				//数据库的增加、编辑、删除记录用executeUpdate().executeUpdate()会将影响的记录结果，即删除了多少条记录以整数返回
				 int number = pstmt.executeUpdate();
				 if (number > 0){
		  	    	 status=true;
		  	     }
				 DBHelper.close(connection,pstmt,null);//关闭数据库 
			
		 } catch(SQLException e){//catch表示，如果出现SQLException异常，就执行括号内的代码
				e.printStackTrace();
		}
		return status;
	}
	
	/**
	 * 实现Product的编辑功能
	 * 
	 * */
	
	
	/**
	 * 实现查询单个菜品
	 * 
	 * */
	public Product getProduct(Integer Id){
		Product product=new Product();
		try {
			Connection connection = DBHelper.getConnection();
			String sql="select * from product where id= ? ";
			PreparedStatement pstmt=connection.prepareStatement(sql);
			pstmt.setInt(1, Id);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				product.setId(rs.getInt("id"));
				product.setProductTypeId(rs.getInt("productTypeId"));
				product.setName(rs.getString("name"));
				product.setOrderNum(rs.getInt("orderNum"));
				product.setDescription(rs.getString("description"));
				product.setContent(rs.getString("content"));
				product.setPrice(rs.getFloat("price"));
				product.setOriginalPrice(rs.getFloat("originalPrice"));
				product.setPicUrl(rs.getString("picUrl"));
				product.setNumber(rs.getInt("number"));
				product.setClick(rs.getInt("click"));
				product.setOnSale(rs.getBoolean("onSale"));
				product.setCreateTime(rs.getTimestamp("createTime"));
				product.setCreatorId(rs.getInt("creatorId"));
				product.setFinalEditorId(rs.getInt("finalEditorId"));
				product.setUpdateTime(rs.getTimestamp("updateTime"));
				product.setAdmin(getAdmin(rs.getInt("creatorId")));
				product.setProductType(getProductTypeProductBean(rs.getInt("productTypeId")));
				String OnSale_String = product.getOnSale() == true ? "上架中":"下架中";
				product.setOnSale_String(OnSale_String); 
			}
			DBHelper.close(connection, pstmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return product;
	}
	
	
	/**
     **修改用户
     *实现思路，先将改名字，查询有没有重名，如果有，就改回去，如果没有，就不改了，改其他参数
     *@param 
     */
    public  int deitProduct(Product product){
   	 //默认全都不能更改
   	int status = 2;
 		try {
 			//创建数据库连接
 			Connection connection = DBHelper.getConnection();
 			
 			
 			
 			Product productTmp = getProduct(product.getId());

 			
 			
 			String sql_name_update = "update producttype set name=? where id=?";
 			
 			PreparedStatement pstmt_name = connection.prepareStatement(sql_name_update);
 			
 			
 			pstmt_name.setString(1, product.getName());
 			pstmt_name.setInt(2, product.getId());
 			pstmt_name.executeUpdate();
 			
 			
 			
 			
 			
 			
 			
 			String sql_repetition = "select count(name) from producttype where FIND_IN_SET(?, name)";
 			
 	   		PreparedStatement pstmt_repetition = connection.prepareStatement(sql_repetition);
 	   		 
 	   		 
 	   		
 	   		 
 	   		pstmt_repetition.setString(1, product.getName()); 
 	   		 
 	   		ResultSet rs = pstmt_repetition.executeQuery();
 	   		 
 	   		int number_repetition = 0; 
 	   		 
	 	   	if(rs.next()){
	 	   		
				 number_repetition = rs.getInt(1);
				 
			 }
	 	   
	 	   if(number_repetition == 1 || number_repetition == 0){
	 		  
				 String sql_update = "update product set productTypeId=?, name=?, orderNum=?, description=?, content=?, price=?, originalPrice=?, picUrl=?, number=?, click=?, onSale=?, finalEditorId=?, updateTime = now() where id=?";
	 	   			
					PreparedStatement pstmt_update = connection.prepareStatement(sql_update);
	 	   			
					
					pstmt_update.setInt(1, product.getProductTypeId());
					pstmt_update.setString(2, product.getName());
					pstmt_update.setInt(3, product.getOrderNum());
					pstmt_update.setString(4, product.getDescription());
					pstmt_update.setString(5, product.getContent());
					pstmt_update.setFloat(6, product.getPrice());
					pstmt_update.setFloat(7, product.getOriginalPrice());
					pstmt_update.setString(8, product.getPicUrl());
					pstmt_update.setInt(9, product.getNumber());
					pstmt_update.setInt(10, product.getClick());
					pstmt_update.setBoolean(11, product.getOnSale());
					pstmt_update.setInt(12, product.getFinalEditorId());
					pstmt_update.setInt(13, product.getId());

	 	   			
	 	   			pstmt_update.executeUpdate();
	 	   			
	 	   			status = 0;
		 	   		
	 	   		
			 }else if(number_repetition > 1){
				 String sql_name_recover = "update product set name=? where id=?";
		 			
		 			PreparedStatement pstmt_name_recover = connection.prepareStatement(sql_name_recover);
		 			
		 			
		 			pstmt_name_recover.setString(1, productTmp.getName());
		 			pstmt_name_recover.setInt(2, productTmp.getId());
		 			pstmt_name_recover.executeUpdate();
		 			status = 1;
			 }

 			
		} catch (SQLException e) {
			e.printStackTrace();
		}
 		return status;
 	}

//    /*
//	 * 获取指定产品分类下的产品记录总数
//	 * @param productTypeId 类别标识符
//	 * @param conn
//	 * @return null表示发生异常
//	 */
//	public Integer getRecordCountByProductType(int productTypeId){	
//		Connection conn = DBHelper.getConnection();
//		Integer number=null;
//		try {
//			number = getRecordCountByProductType(productTypeId, conn);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			DBHelper.close(conn,null,null);
//		}
//		return number;
//	}
    
	/*
	 * 获取指定产品分类下的产品记录总数
	 * @param productTypeId 类别标识符
	 * @param conn
	 * @return null表示发生异常
	 */
	public Integer getRecordCountByProductType(int productTypeId){	
		Connection conn = DBHelper.getConnection();
		Integer number=null;
		try {
			number = getRecordCountByProductType(productTypeId, conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBHelper.close(conn,null,null);
		}
		return number;
	}
	
	/*
	 * 获取指定产品分类下的产品记录总数，用于配合其他数据库方法使用
	 * @param productTypeId 类别标识符
	 * @param conn
	 * @return 
	 */
	public int getRecordCountByProductType(int productTypeId,Connection conn){	
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try{
			String sql="select count(id) from product where productTypeId=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, productTypeId);
			rs=pstmt.executeQuery();		
			if(rs.next()){
				count=rs.getInt(1);
			}	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
//			DBHelper.close(pstmt,rs);
		}
		return count;			
	}	
	
    /*
	 * 分页获取指定栏目的产品的记录,针对前台设计
	 * @param page 要读取的页码
	 * @param pageSize 每页的记录数，必须>0
	 * @param productTypeId 指定栏目的标识符
	 * @return PageObject分页专用类
	 */
	public PageObject getProductsForFrontdesk(Object page, final int pageSize,Object productTypeId){	
		PageObject<Product> pageObject=null;
		Integer id = null;
		if (productTypeId != null) {
			try {
				String s = productTypeId.toString();
				id = Integer.parseInt(s);
			} catch (Exception e) {
				id = null;
			}
		}
		if(id!=null){
			AdminBean adminBean=new AdminBean();//初始化管理账户表操作类
			ProductTypeBean productTypeBean=new ProductTypeBean();//初始化产品类别bean
			OrdersBean odersBean=new OrdersBean();
			List<Product> list = new ArrayList<Product>();
			//获取产品的总记录数
			int totalRecords=getRecordCountByProductType(id);
			//执行分页
			pageObject=PageUtils.getPageObject(page, pageSize, totalRecords);
			pageObject.setList(list);
			Connection conn=DBHelper.getConnection();
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			Product product=null;
			try{
				String sql="select * from product where productTypeId=? and onSale=true order by createTime desc limit ?,?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				pstmt.setInt(2, (pageObject.getCurrentPage()-1)*pageSize);
				pstmt.setInt(3, pageSize);
				rs=pstmt.executeQuery();
				while(rs.next()){
					product=new Product();
					product.setId(rs.getInt("id"));
					product.setProductType(productTypeBean.getProductType(rs.getInt("productTypeId"), conn));
					product.setName(rs.getString("name"));
					product.setOrderNum(rs.getInt("orderNum"));
					product.setDescription(rs.getString("description"));
					product.setContent(rs.getString("content"));
					product.setPrice(rs.getFloat("price"));
					product.setOriginalPrice(rs.getFloat("originalPrice"));
					product.setPicUrl(rs.getString("picUrl"));
					product.setNumber(rs.getInt("number"));
					product.setClick(rs.getInt("click"));
					product.setOnSale(rs.getBoolean("onSale"));
					product.setCreateTime(rs.getTimestamp("createTime"));
					product.setCreator(adminBean.getAdmin(rs.getInt("creatorId"), conn));
					product.setFinalEditor(adminBean.getAdmin(rs.getInt("finalEditorId"), conn));
					product.setUpdateTime(rs.getTimestamp("updateTime"));
					
					//为订单总价相加
					product.setTotalNumberOfOrder(odersBean.getOrderDetailCount(product.getId(),conn));
					list.add(product);
				}			
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				DBHelper.close(conn,pstmt,rs);
			}
		}else{
			
		}
		return pageObject;			
	}

	/*
	 * 根据产品名字进行搜索,针对前台设计
	 * @param number 要读取的N条产品
	 * @return 产品
	 */
	public List<Product> getProductsForFrontdesk(final String name){	
		List<Product> list = new ArrayList<Product>();
		ProductTypeBean productTypeBean=new ProductTypeBean();//初始化产品类别bean
		AdminBean adminBean=new AdminBean();//初始化管理账户表操作类
		Connection conn=DBHelper.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Product product=null;
		try{
			String sql="select * from product where name like ? and onSale=true order by createTime desc";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+name.trim()+"%");
			rs=pstmt.executeQuery();
			while(rs.next()){
				product=new Product();
				product.setId(rs.getInt("id"));
				product.setProductType(productTypeBean.getProductType(rs.getInt("productTypeId"), conn));
				product.setName(rs.getString("name"));
				product.setOrderNum(rs.getInt("orderNum"));
				product.setDescription(rs.getString("description"));
				product.setContent(rs.getString("content"));
				product.setPrice(rs.getFloat("price"));
				product.setOriginalPrice(rs.getFloat("originalPrice"));
				product.setPicUrl(rs.getString("picUrl"));
				product.setNumber(rs.getInt("number"));
				product.setClick(rs.getInt("click"));
				product.setOnSale(rs.getBoolean("onSale"));
				product.setCreateTime(rs.getTimestamp("createTime"));
				product.setCreator(adminBean.getAdmin(rs.getInt("creatorId"), conn));
				product.setFinalEditor(adminBean.getAdmin(rs.getInt("finalEditorId"), conn));
				product.setUpdateTime(rs.getTimestamp("updateTime"));
				list.add(product);
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBHelper.close(conn,pstmt,rs);
		}
		return list;
	}
	
	
	/*
	 * 定义数据库的查询方法,根据id获取产品对象
	 * @param id 账户标识符
	 * @return null表示获取失败
	 */
	public Product getProduct(Object id){	
		Connection conn = DBHelper.getConnection();
		Product product=null;
		try{
			product=getProduct(id,conn);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBHelper.close(conn, null, null);
		}
		return product;
	}
	
	/*=======================带Connecttion的方法============================*/
	/*
	 * 定义数据库的查询方法,根据id获取产品对象，供其他方法调用
	 * @param id 账户标识符
	 * @return null表示获取失败
	 */
	public Product getProduct(Object id,Connection conn){	
		AdminBean adminBean=new AdminBean();//初始化管理账户表操作类
		ProductTypeBean productTypeBean=new ProductTypeBean();//初始化产品类别bean
		Product product = null;// 最后返回的对象
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
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "select * from product where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, productId);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					product=new Product();
					product.setId(rs.getInt("id"));
					product.setProductType(productTypeBean.getProductType(rs.getInt("productTypeId"), conn));
					product.setName(rs.getString("name"));
					product.setOrderNum(rs.getInt("orderNum"));
					product.setDescription(rs.getString("description"));
					product.setContent(rs.getString("content"));
					product.setPrice(rs.getFloat("price"));
					product.setOriginalPrice(rs.getFloat("originalPrice"));
					product.setPicUrl(rs.getString("picUrl"));
					product.setNumber(rs.getInt("number"));
					product.setClick(rs.getInt("click"));
					product.setOnSale(rs.getBoolean("onSale"));
					product.setCreateTime(rs.getTimestamp("createTime"));
					product.setCreator(adminBean.getAdmin(rs.getInt("creatorId"), conn));
					product.setFinalEditor(adminBean.getAdmin(rs.getInt("finalEditorId"), conn));
					product.setUpdateTime(rs.getTimestamp("updateTime"));
					//该产品的下单数
					OrdersBean odersBean=new OrdersBean();
					product.setTotalNumberOfOrder(odersBean.getOrderDetailCount(product.getId(),conn));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBHelper.close(null, pstmt, rs);
			}
		}
		return product;
	}
	public Product showProduct(Integer id){
		Product product=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBHelper.getConnection();
			String sql="select * from product where id=?";							//定义一个字符串
			pstmt=con.prepareStatement(sql);	//这个对象可以用来将sql语句发送到数据库（准备发送）
			pstmt.setInt(1,id);
			rs=pstmt.executeQuery();					//pstmt.executeQuery()表示将sql语句真的发送到数据库，并返回查询结果
			if(rs.next()){
				product=new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setProductTypeId(rs.getInt("productTypeId"));
				product.setOrderNum(rs.getInt("orderNum"));
				product.setContent(rs.getString("content"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getFloat("price"));
				product.setOriginalPrice(rs.getFloat("originalPrice"));
				product.setPicUrl(rs.getString("picUrl"));
				product.setNumber(rs.getInt("number"));
				product.setClick(rs.getInt("click"));
				product.setOnSale(rs.getBoolean("onSale"));
				product.setCreateTime(rs.getTimestamp("createTime"));
				product.setCreatorId(rs.getInt("creatorId"));
				product.setFinalEditorId(rs.getInt("finalEditorId"));
				product.setUpdateTime(rs.getTimestamp("updateTime"));
			}
			DBHelper.close(con, pstmt, rs);									//关闭数据库
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return product;
	}
	@Override
	public String toString() {
		return "ProductBean []";
	}
	
	

	
}
