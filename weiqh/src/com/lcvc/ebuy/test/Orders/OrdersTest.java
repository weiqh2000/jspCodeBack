package com.lcvc.ebuy.test.Orders;

import org.junit.Test;

import com.lcvc.ebuy.bean.OrdersBean;
import com.lcvc.ebuy.model.Orders;

public class OrdersTest{
	OrdersBean ordersBean = new OrdersBean();
	
	//@Test
	public void getRecordCount(){
		System.out.println(ordersBean.getRecordCount());
	}
	
	//@Test
	public void getOrderDetails(){
		
		Orders orders = new Orders();
		orders.setOrderNo("f653aec2da68487ea69a0adcce58b107");
		
		System.out.println(ordersBean.getOrderDetails(orders));

	}
	
//	@Test
//	public void getOrderDetailss(){
//		
//	}
	
}