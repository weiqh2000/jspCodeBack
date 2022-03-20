package com.lcvc.ebuy.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import com.lcvc.ebuy.model.Customer;
import com.lcvc.ebuy.model.OrderDetail;
import com.lcvc.ebuy.model.Orders;
import com.lcvc.ebuy.model.Product;
import com.lcvc.ebuy.model.ShoppingCart;
import com.lcvc.ebuy.model.ShoppingCartItem;
import com.lcvc.ebuy.model.exception.MyFormException;
import com.lcvc.ebuy.model.other.PageObject;
import com.lcvc.ebuy.util.PageUtils;

/*
 * 订单操作类
 */
public class OrdersBean {
	
	
	/*
	 * 生成订单编号（唯一），按规则生成
	 * 计算规则：直接使用java API生成
	 */
	public String getOrderNo(){
		UUID uuid = UUID.randomUUID();
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/*
	 * 获取订单的记录总数
	 * @return 
	 */
	public int getRecordCount(){	
		Connection conn=DBHelper.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try{
			String sql="select count(orderNo) from orders";
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
	 * 获取订单主表和子表的所有记录，使用事务处理
	 * @param orderNo 订单号
	 * @return
	 */
	public PageObject<Orders> getOrdersList(Object page, final int pageSize) throws SQLException{
		List<Orders> list=new ArrayList<Orders>();
		//获取订单的总记录数
		int totalRecords=getRecordCount();
		//执行分页
		PageObject<Orders> pageObject=PageUtils.getPageObject(page, pageSize, totalRecords);
		pageObject.setList(list);
		CustomerBean customerBean=new CustomerBean();//客户操作类
		Connection conn=DBHelper.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			String sql="select * from orders order by createTime desc limit ?,?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (pageObject.getCurrentPage()-1)*pageSize);
			pstmt.setInt(2, pageSize);
			rs=pstmt.executeQuery();
			Orders orders=null;
			while(rs.next()){
				orders=new Orders();
				orders.setOrderNo(rs.getString("orderNo"));
				orders.setCustomer(customerBean.getCustomer(rs.getInt("customerId")));
				orders.setSendName(rs.getString("sendName"));
				orders.setSendAddress(rs.getString("sendAddress"));
				orders.setSendZip(rs.getString("sendZip"));
				orders.setSendTel(rs.getString("sendTel"));
				orders.setPayment(rs.getInt("payment"));
				orders.setMeno(rs.getString("meno"));
				orders.setCreateTime(rs.getTimestamp("createTime"));
				orders.setDealTime(rs.getTimestamp("dealTime"));
				orders.setTag(rs.getInt("tag"));
				//从数据库中附加所有订单从表
				orders.setOrderDetails(getOrderDetails(orders,conn));
				list.add(orders);
			}			
		}catch(Exception e){
			throw new SQLException(e.getMessage());
		}finally{
			DBHelper.closeConn(conn,pstmt,rs);
		}
		return pageObject;
	}
	
	/*
	 * 获取订单主表和子表的所有记录，使用事务处理
	 * @param orderNo 订单号
	 */
	public Orders getOrders(String orderNo) throws MyFormException,SQLException{
		Orders orders=null;
		if(orderNo!=null){	
			CustomerBean customerBean=new CustomerBean();//客户操作类
			Connection conn=DBHelper.getConnection();
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try{
				String sql="select * from orders where orderNo = ?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, orderNo);
				rs=pstmt.executeQuery();
				if(rs.next()){
					orders=new Orders();
					orders.setOrderNo(rs.getString("orderNo"));
					orders.setCustomer(customerBean.getCustomer(rs.getInt("customerId")));
					orders.setSendName(rs.getString("sendName"));
					orders.setSendAddress(rs.getString("sendAddress"));
					orders.setSendZip(rs.getString("sendZip"));
					orders.setSendTel(rs.getString("sendTel"));
					orders.setPayment(rs.getInt("payment"));
					orders.setMeno(rs.getString("meno"));
					orders.setCreateTime(rs.getTimestamp("createTime"));
					orders.setDealTime(rs.getTimestamp("dealTime"));
					orders.setTag(rs.getInt("tag"));
					//从数据库中附加所有订单从表
					orders.setOrderDetails(getOrderDetails(orders,conn));
				}			
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				DBHelper.closeConn(conn,pstmt,rs);
			}
		}
		return orders;
	}
	
	/*
	 * 将购物车的信息保存到订单中，使用事务处理
	 * @param orders 不能为NULL，并且前台应该已经对规定信息进行验证和封装
	 * @param shoppingCart 不能为Null
	 * @param customer 不能为Null
	 */
	public void saveOrders(Orders orders,Integer tag,ShoppingCart shoppingCart,Customer customer) throws MyFormException,SQLException {
		//在web层已经通过验证截取非法信息，这里仅进行核心验证
		if(orders==null){
			throw new MyFormException("操作失败：参数非法");
		}
		if(shoppingCart==null){
			throw new MyFormException("操作失败：请先购买商品再下订单");
		}
		orders.setCustomer(customer);
		orders.setOrderNo(getOrderNo());
		orders.setPayment(2);
		if(tag==null){
			orders.setTag(2);//设置为未付款
		}else{
			orders.setTag(tag);
		}
		Connection conn=DBHelper.getConnection();//打开数据库连接
		try{
			conn.setAutoCommit(false); //设置事务不自动提交
			//插入订单记录主表
			insertOrders(orders, conn);
			//插入订单记录子表
			insertOrderDetail(orders.getOrderNo(), shoppingCart, conn);
			conn.commit();//提交事务
			//清空购物车
			shoppingCart.getList().clear();
			shoppingCart=null;
		}catch (SQLException e) {
			try {
				conn.rollback();//事务回滚
			} catch (SQLException e1) {
				throw new SQLException("操作失败警告：订单("+orders.getOrderNo()+")下达时出现数据库回滚异常，请检查数据库删除因此产生的异常数据。"+e1.getMessage());
			}
			throw new SQLException(e.getMessage());
        }finally{
        	DBHelper.closeConn(conn, null, null);
        }
	}
	
	/*
	 * 取消订单
	 * @param orderNo 订单编号
	 */
	public boolean cancelOrders(String orderNo) throws MyFormException,SQLException{
		//获取订单的最新状态
		Integer tag=getOrdersTag(orderNo);
		if(tag==1){
			throw new MyFormException("操作失败：已经付款的订单无法作废");
		}else{
			//更改订单状态
			return updateOrdersTag(orderNo,5);
		}
	}
	
	
	
	/*
	 * 获取订单状态
	 * @param orderNo 订单编号
	 * @param 订单状态
	 */
	private Integer getOrdersTag(String orderNo) throws MyFormException,SQLException{
		Integer tag=null;
		if(orderNo!=null){
			Connection conn=DBHelper.getConnection();//打开数据库连接
			PreparedStatement pstmt=null;
			ResultSet rs = null; 
			try{
				String sql="select tag from orders where orderNo=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, orderNo);
				rs=pstmt.executeQuery();
				if(rs.next()){		
					tag=rs.getInt("tag");
				}
			}catch(Exception e){
				throw new SQLException("操作失败：获取订单状态失败"+e.getMessage());
			}finally{
				DBHelper.closeConn(conn,pstmt,rs);
			}		
		}
		return tag;
	}
	
	/*
	 * 更改订单状态
	 * @param orderNo 订单编号
	 * @param tag 订单状态
	 */
	private boolean updateOrdersTag(String orderNo,Integer tag) throws MyFormException,SQLException{
		boolean flag=false;
		if(tag>5||tag<1){
			throw new MyFormException("操作失败：订单状态非法");
		}
		if(orderNo!=null){
			Connection conn=DBHelper.getConnection();//打开数据库连接
			PreparedStatement pstmt=null;
			ResultSet rs = null; 
			try{
				String sql="update orders set tag=? where orderNo=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, tag);
				pstmt.setString(2, orderNo);
				int i = pstmt.executeUpdate(); 
				if(i>0){//如果插入成功
					flag=true;
				}			
			}catch(Exception e){
				throw new SQLException("操作失败：商品库存更新失败"+e.getMessage());
			}finally{
				DBHelper.closeConn(conn,pstmt,rs);
			}		
		}
		return flag;
	}
	
	
	/*=======================带Connecttion的方法============================*/
	
	/*
	 * 插入订单主表记录，供其他方法调用，不能单独使用
	 * @param orders 订单类，必须是验证赋值后的最终值
	 * @param conn
	 */
	private boolean insertOrders(Orders orders,Connection conn) throws SQLException{
		boolean flag=false;//最后的插入结果，true为成功
		PreparedStatement pstmt=null;
		try{
			String sql="insert into orders(orderNo,customerId,sendName,sendAddress,sendZip,sendTel,payment,meno,createTime,dealTime,tag) values(?,?,?,?,?,?,?,?,?,null,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orders.getOrderNo());
			pstmt.setInt(2, orders.getCustomer().getId());
			pstmt.setString(3, orders.getSendName());
			pstmt.setString(4, orders.getSendAddress());
			pstmt.setString(5, orders.getSendZip());
			pstmt.setString(6, orders.getSendTel());
			pstmt.setInt(7, orders.getPayment());
			pstmt.setString(8, orders.getMeno());
			pstmt.setTimestamp(9, new Timestamp(Calendar.getInstance().getTimeInMillis()));//当前时间作为创建时间
			pstmt.setInt(10, orders.getTag());
			int i = pstmt.executeUpdate(); 
			if(i>0){
				flag=true;
			}				
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBHelper.closeConn(null,pstmt,null);
		}
		return flag;	
	}
	
	/*
	 * 插入订单从表记录，供其他方法调用，不能单独使用.
	 * 说明：插入记录的同时，要将商品的库存减掉相应数量
	 * @param orderNo 订单主表主键，必须为实际存在
	 * @param shoppingCart 购物车，必须是验证赋值后的最终值
	 * @param conn
	 */
	private boolean insertOrderDetail(String orderNo,ShoppingCart shoppingCart,Connection conn) throws SQLException{
		boolean flag=true;//最后的插入结果，true为成功
		PreparedStatement pstmt=null;
		ProductBean productBean=new ProductBean();//初始化产品业务类
		//获取购物车里条目列表
		List<ShoppingCartItem> list=shoppingCart.getList();
		for(ShoppingCartItem item:list){
			//获取最新的产品信息
			Product product=productBean.getProduct(item.getProduct().getId());
			if(!product.getOnSale()){
				throw new MyFormException("操作错误：商品"+product.getName()+"已经下架，请移除该商品");
			}else if(item.getNumber()>product.getNumber().intValue()){
				throw new MyFormException("操作失败："+product.getName()+"库存不足");
			}else if(item.getProduct().getPrice()!=product.getPrice().floatValue()){
				throw new MyFormException("操作失败："+product.getName()+"的价格由购买时的"+item.getProduct().getPrice()+"变动为"+product.getPrice()+"，请确认是否继续购买");
			}else{//如果都没有异常，进行库存计算
				//获取剩余库存
				int number=product.getNumber()-item.getNumber();
				//对产品的剩余库存进行更新
				productBean.updateProductNumber(product.getId(),number,conn);
			}
			item.setProduct(product);//将的产品信息付给购物条
			try{
				String sql="insert into order_detail(id,orderNo,productId,price,originalPrice,number) values(null,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, orderNo);
				pstmt.setInt(2, item.getProduct().getId());
				pstmt.setFloat(3, item.getProduct().getPrice());
				pstmt.setFloat(4, item.getProduct().getOriginalPrice());
				pstmt.setInt(5, item.getNumber());
				int i = pstmt.executeUpdate(); 
				if(i==0){
					flag=false;
				}				
			}catch(Exception e){
				throw new MyFormException("操作失败：订单从表保存异常"+e.getMessage());
			}finally{
				DBHelper.closeConn(null,pstmt,null);
			}
		}
		return flag;	
	}
	
	/*
	 * 读取订单主表对应的所有从表
	 * @param orders，必须是正确的订单类数据，含数据库中的所有字段
	 * @return 
	 */
	private List<OrderDetail> getOrderDetails(Orders orders,Connection conn) throws SQLException {
		List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
		if(orders!=null){
			ProductBean productBean=new ProductBean();
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try{
				String sql="select * from order_detail where orderNo = ?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, orders.getOrderNo());
				rs=pstmt.executeQuery();
				OrderDetail orderDetail=null;
				while(rs.next()){
					orderDetail=new OrderDetail();
					orderDetail.setProduct(productBean.getProduct(rs.getInt("productId"),conn));
					orderDetail.setPrice(rs.getFloat("price"));
					orderDetail.setOriginalPrice(rs.getFloat("originalPrice"));
					orderDetail.setNumber(rs.getInt("number"));
					orderDetails.add(orderDetail);
					//为订单总价相加
					orders.setTotalPrice(orders.getTotalPrice()+orderDetail.getNumber()*orderDetail.getPrice());
				}			
			}catch(Exception e){
				throw new SQLException(e.getMessage());
			}finally{
				DBHelper.closeConn(null,pstmt,rs);
			}
		}
		return orderDetails;
	}
	
	/*
	 * 读取产品对应的所有订单数量，该方法供其他方法调用
	 * @param productId，产品标识符
	 * @return 订单数量
	 */
	public int getOrderDetailCount(Integer productId) throws SQLException {
		int count=0;
		if(productId!=null){
			Connection conn = DBHelper.getConnection();
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try{
				String sql="select count(id) from order_detail where productId = ?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, productId);
				rs=pstmt.executeQuery();
				OrderDetail orderDetail=null;
				if(rs.next()){
					count=rs.getInt(1);
				}			
			}catch(Exception e){
				throw new SQLException(e.getMessage());
			}finally{
				DBHelper.closeConn(conn,pstmt,rs);
			}
		}
		return count;
	}
	
	/*
	 * 读取产品对应的所有订单数量，该方法供其他方法调用
	 * @param productId，产品标识符
	 * @return 订单数量
	 */
	public int getOrderDetailCount(Integer productId,Connection conn) throws SQLException {
		int count=0;
		if(productId!=null){
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try{
				String sql="select count(id) from order_detail where productId = ?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, productId);
				rs=pstmt.executeQuery();
				OrderDetail orderDetail=null;
				if(rs.next()){
					count=rs.getInt(1);
				}			
			}catch(Exception e){
				throw new SQLException(e.getMessage());
			}finally{
				DBHelper.closeConn(null,pstmt,rs);
			}
		}
		return count;
	}
	
	
}
