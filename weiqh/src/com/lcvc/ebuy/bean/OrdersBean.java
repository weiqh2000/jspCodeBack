package com.lcvc.ebuy.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lcvc.ebuy.model.OrderDetail;
import com.lcvc.ebuy.model.Orders;
import com.lcvc.ebuy.model.Admin;
import com.lcvc.ebuy.model.Product;
import com.lcvc.ebuy.model.MyFormException;
import com.lcvc.ebuy.model.PageObject;

import com.mysql.jdbc.Statement;

public class OrdersBean{
	
  /**
   * 实现查询订单个数
   * */
  public int getRecordCount() {

	    int count = 0;
	    try {
	    	Connection conn = DBHelper.getConnection();
	      String sql = "select count(orderNo) from orders";
	      PreparedStatement pstmt = conn.prepareStatement(sql);
	      ResultSet rs = pstmt.executeQuery();
	      if (rs.next()){
	    	  count = rs.getInt(1);  
	      }
	      DBHelper.close(conn, pstmt, rs);
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	    return count;
	  }
  
  
  public List<OrderDetail> getOrderDetails(Orders orders){
	    List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
	    ProductBean productBean = new ProductBean();
	      try {
	    	  Connection conn = DBHelper.getConnection();
	        String sql = "select * from order_detail where orderNo = ?";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, orders.getOrderNo());
	        ResultSet rs = pstmt.executeQuery();
	        OrderDetail orderDetail = null;
	        while (rs.next()) {
	          orderDetail = new OrderDetail();
	          orderDetail.setProduct(productBean.getProduct(Integer.valueOf(rs.getInt("productId"))));
	          orderDetail.setPrice(Float.valueOf(rs.getFloat("price")));
	          orderDetail.setOriginalPrice(Float.valueOf(rs.getFloat("originalPrice")));
	          orderDetail.setNumber(Integer.valueOf(rs.getInt("number")));
	          orderDetails.add(orderDetail);
	          orders.setTotalPrice(Float.valueOf(orders.getTotalPrice().floatValue() + orderDetail.getNumber().intValue() * orderDetail.getPrice().floatValue()));
	        } 
	        DBHelper.close(null, pstmt, rs);
	        }catch (Exception e) {

	      } 
	    return orderDetails;
	  }
  
  public List<OrderDetail> getOrderDetailss(){
	    List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
	    ProductBean productBean = new ProductBean();
	      try {
	    	  Connection conn = DBHelper.getConnection();
	        String sql = "select * from order_detail where";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        OrderDetail orderDetail = null;
	        while (rs.next()) {
	          orderDetail = new OrderDetail();
	          orderDetail.setProduct(productBean.getProduct(Integer.valueOf(rs.getInt("productId"))));
	          orderDetail.setPrice(Float.valueOf(rs.getFloat("price")));
	          orderDetail.setOriginalPrice(Float.valueOf(rs.getFloat("originalPrice")));
	          orderDetail.setNumber(Integer.valueOf(rs.getInt("number")));
	          orderDetails.add(orderDetail);
//	          orders.setTotalPrice(Float.valueOf(orders.getTotalPrice().floatValue() + orderDetail.getNumber().intValue() * orderDetail.getPrice().floatValue()));
	        } 
	        DBHelper.close(null, pstmt, rs);
	        }catch (Exception e) {

	      } 
	    return orderDetails;
	  }
  
  public Orders getOrders(String orderNo){
	    Orders orders = null;
	    if (orderNo != null) {
	    	 CustomerBean customerBean = new CustomerBean();
	      
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	    	  Connection conn = DBHelper.getConnection();
	        String sql = "select * from orders where orderNo = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, orderNo);
	        rs = pstmt.executeQuery();
	        if (rs.next()) {
	          orders = new Orders();
	          orders.setOrderNo(rs.getString("orderNo"));
	          orders.setCustomer(customerBean.getCustomer(Integer.valueOf(rs.getInt("customerId"))));
	          orders.setSendName(rs.getString("sendName"));
	          orders.setSendAddress(rs.getString("sendAddress"));
	          orders.setSendZip(rs.getString("sendZip"));
	          orders.setSendTel(rs.getString("sendTel"));
	          orders.setPayment(Integer.valueOf(rs.getInt("payment")));
	          orders.setMeno(rs.getString("meno"));
	          orders.setCreateTime(rs.getTimestamp("createTime"));
	          orders.setDealTime(rs.getTimestamp("dealTime"));
	          orders.setTag(Integer.valueOf(rs.getInt("tag")));
	          orders.setOrderDetails(getOrderDetails(orders));
	        } 
	      } catch (Exception e) {
	        e.printStackTrace();
	      } finally {
//	        DBHelper.close(conn, pstmt, rs);
	      } 
	    } 
	    return orders;
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
				DBHelper.close(null,pstmt,rs);
			}
		}
		return count;
	}
  
  
  
  
  
}