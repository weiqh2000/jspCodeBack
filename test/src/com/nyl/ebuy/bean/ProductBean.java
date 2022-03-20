package com.nyl.ebuy.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.nyl.ebuy.model.Admin;
import com.nyl.ebuy.model.Product;
import com.nyl.ebuy.model.exception.MyFormException;
import com.nyl.ebuy.model.other.PageObject;
import com.nyl.ebuy.util.PageUtils;
import com.mysql.jdbc.Statement;

/*
 * 产品bean
 * 特别注明，该类所有分页均通过mysql的limit实现，如果使用其他数据库请使用相应的数据库分页
 */
public class ProductBean {
	/*
	 * 分页获取产品的记录
	 * @param page 要读取的页码
	 * @param pageSize 每页的记录数，必须>0
	 * @return PageObject分页专用类
	 */
	public PageObject getProducts(Object page, final int pageSize){	
		AdminBean adminBean=new AdminBean();//初始化管理账户表操作类
		ProductTypeBean productTypeBean=new ProductTypeBean();//初始化产品类别bean
		OrdersBean odersBean=new OrdersBean();
		List<Product> list = new ArrayList<Product>();
		//获取产品的总记录数
		int totalRecords=getRecordCount();
		//执行分页
		PageObject<Product> pageObject=PageUtils.getPageObject(page, pageSize, totalRecords);
		pageObject.setList(list);
		Connection conn=DBHelper.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Product product=null;
		try{
			String sql="select * from product order by createTime desc limit ?,?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (pageObject.getCurrentPage()-1)*pageSize);
			pstmt.setInt(2, pageSize);
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
				
				product.setTotalNumberOfOrder(odersBean.getOrderDetailCount(product.getId()));
				list.add(product);
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBHelper.closeConn(conn,pstmt,rs);
		}
		return pageObject;			
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
				DBHelper.closeConn(conn,pstmt,rs);
			}
		}else{
			
		}
		return pageObject;			
	}
	
	/*
	 * 获取最新发布的N条产品的记录,针对前台设计
	 * @param number 要读取的N条产品
	 * @return 产品
	 */
	public List<Product> getNewProductsForFrontdesk(final int number){	
		List<Product> list = new ArrayList<Product>();
		ProductTypeBean productTypeBean=new ProductTypeBean();//初始化产品类别bean
		AdminBean adminBean=new AdminBean();//初始化管理账户表操作类
		Connection conn=DBHelper.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Product product=null;
		try{
			String sql="select * from product where onSale=true order by createTime desc limit ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, number);
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
			DBHelper.closeConn(conn,pstmt,rs);
		}
		return list;
	}
	
	/*
	 * 获取最热门的N条产品的记录,针对前台设计
	 * 说明：目前以点击数为判断
	 * @param number 要读取的N条产品
	 * @return 产品
	 */
	public List<Product> getHotProductsForFrontdesk(final int number){	
		List<Product> list = new ArrayList<Product>();
		ProductTypeBean productTypeBean=new ProductTypeBean();//初始化产品类别bean
		AdminBean adminBean=new AdminBean();//初始化管理账户表操作类
		OrdersBean odersBean=new OrdersBean();
		Connection conn=DBHelper.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Product product=null;
		try{
			String sql="select * from product where onSale=true order by click desc limit ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, number);
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
			DBHelper.closeConn(conn,pstmt,rs);
		}
		return list;
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
			DBHelper.closeConn(conn,pstmt,rs);
		}
		return list;
	}
	
	
	/*
	 * 获取产品的初始值-用于表单创建
	 */
	public Product getProductInit(){	
		Product product=new Product();
		product.setClick(0);
		product.setOrderNum(100);
		product.setOnSale(true);
		return product;
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
			DBHelper.closeConn(conn, null, null);
		}
		return product;
	}
	
	
	

	/*
	 * 根据传入的参数添加到数据库，并返回插入数据库后的标识符（适合数据库的自增字段）
	 * @param product 要添加的对象
	 * @param creator 创建产品的账户
	 * @return null表示插入失败
	 */
	public Integer addProduct(Product product,Admin creator) throws MyFormException{	
		Integer id=null;//获取插入记录后的标识符
		//对数据库进行操作
		Connection conn=DBHelper.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs = null; 
		try{
			String sql="insert into product(id,productTypeId,name,orderNum,description,content,price,originalPrice,picUrl,number,click,onSale,createTime,creatorId,finalEditorId,updateTime) values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,null,null)";
			pstmt=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, product.getProductType().getId());
			pstmt.setString(2, product.getName());
			pstmt.setInt(3, product.getOrderNum());
			pstmt.setString(4, product.getDescription());
			pstmt.setString(5, product.getContent());
			pstmt.setFloat(6, product.getPrice());
			pstmt.setFloat(7, product.getOriginalPrice());
			pstmt.setString(8, product.getPicUrl());
			pstmt.setInt(9, product.getNumber());
			pstmt.setInt(10, product.getClick());
			pstmt.setBoolean(11, product.getOnSale());
			pstmt.setTimestamp(12, new Timestamp(Calendar.getInstance().getTimeInMillis()));//当前时间作为创建时间
			pstmt.setInt(13, creator.getUserId());
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
		return id;		
	}
	
	
	/*
	 * 根据传入的参数更新数据库
	 * @param product 要更新的对象
	 * @param creator 最后编辑产品的账户
	 * @return true表示更新成功，false表示更新失败
	 */
	public boolean updateProduct(Product product,Admin finalEditor) throws MyFormException{	
		boolean flag=false;
		if(getProduct(product.getId())==null){
			throw new MyFormException("操作失败：该记录不存在");
		}
		//对数据库进行操作
		Connection conn=DBHelper.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs = null; 
		try{
			String sql="update product set productTypeId=?,name=?,orderNum=?,description=?,content=?,price=?,originalPrice=?,picUrl=?,number=?,click=?,onSale=?,finalEditorId=?,updateTime=? where id=?";
			pstmt=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, product.getProductType().getId());
			pstmt.setString(2, product.getName());
			pstmt.setInt(3, product.getOrderNum());
			pstmt.setString(4, product.getDescription());
			pstmt.setString(5, product.getContent());
			pstmt.setFloat(6, product.getPrice());
			pstmt.setFloat(7, product.getOriginalPrice());
			pstmt.setString(8, product.getPicUrl());
			pstmt.setInt(9, product.getNumber());
			pstmt.setInt(10, product.getClick());
			pstmt.setBoolean(11, product.getOnSale());
			pstmt.setInt(12, finalEditor.getUserId());
			pstmt.setTimestamp(13, new Timestamp(Calendar.getInstance().getTimeInMillis()));//当前时间作为创建时间
			pstmt.setInt(14, product.getId());
			int i = pstmt.executeUpdate(); 
			if(i>0){//如果插入成功
				flag=true;
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBHelper.closeConn(conn,pstmt,rs);
		}		
		return flag;
	}
	
	/*
	 * 删除指定的对象
	 * 说明：如果该产品已经有订单，则不允许删除
	 * @param id 标识符
	 * @return true表示删除成功，false表示删除失败
	 */
	public boolean deleteProduct(Object id)throws MyFormException,SQLException{	
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
			OrdersBean odersBean=new OrdersBean();
			if(odersBean.getOrderDetailCount(productId)==0){//如果该产品没有订单
				Connection conn = DBHelper.getConnection();
				PreparedStatement pstmt = null;
				try {
					String sql = "delete from product where id = ?";
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
			}else{
				throw new MyFormException("操作失败：该产品已经有订单购买，无法删除");
			}
		}
		return flag;
	}
	
	/*
	 * 获取产品的记录总数
	 * @return 
	 */
	public int getRecordCount(){	
		Connection conn=DBHelper.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try{
			String sql="select count(id) from product";
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
			DBHelper.closeConn(conn,null,null);
		}
		return number;
	}	
	
	
	
	/*
	 * 更改库存信息，供方法只能供其他方法调用，目前设计是给下订单时使用（剩余数量=库存数量-购买数量）
	 * @param productId 产品标识符，必须为实际存在
	 * @param number 产品库存，必须存在值
	 * @param conn
	 */
	public boolean updateProductNumber(Integer productId,Integer number,Connection conn) throws SQLException{
		boolean flag=false;
		PreparedStatement pstmt=null;
		ResultSet rs = null; 
		try{
			String sql="update product set number=? where id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setInt(2, productId.intValue());
			int i = pstmt.executeUpdate(); 
			if(i>0){//如果插入成功
				flag=true;
			}			
		}catch(Exception e){
			throw new SQLException("操作失败：商品库存更新失败"+e.getMessage());
		}finally{
			DBHelper.closeConn(null,pstmt,rs);
		}		
		return flag;
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
				DBHelper.closeConn(null, pstmt, rs);
			}
		}
		return product;
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
			DBHelper.closeConn(pstmt,rs);
		}
		return count;			
	}	
	
	
}
