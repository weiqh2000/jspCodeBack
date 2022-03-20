package com.lcvc.ebuy.test.junit.bean;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lcvc.ebuy.bean.CustomerBean;
import com.lcvc.ebuy.bean.OrdersBean;
import com.lcvc.ebuy.bean.ProductBean;
import com.lcvc.ebuy.bean.ShoppingCartBean;
import com.lcvc.ebuy.model.Customer;
import com.lcvc.ebuy.model.Orders;
import com.lcvc.ebuy.model.Product;
import com.lcvc.ebuy.model.ShoppingCart;
import com.lcvc.ebuy.model.ShoppingCartItem;
import com.lcvc.ebuy.model.exception.MyFormException;

/*
 * 订单测试类
 */
public class OrdersBeanTest {
	private static ShoppingCartBean shoppingCartBean;
	private static ProductBean productBean;
	private static OrdersBean odersBean;
	private static CustomerBean customerBean;
	
	@BeforeClass
	// 注意,这里必须是static...因为方法将在类被装载的时候就被调用(那时候还没创建实例)
	public static void before() {
		shoppingCartBean=new ShoppingCartBean();
		productBean=new ProductBean();
		odersBean=new OrdersBean();
		customerBean=new CustomerBean();
	}

	@AfterClass
	public static void after() {
		shoppingCartBean=null;
		productBean=null;
		odersBean=null;
		customerBean=null;
	}
	
	/*
	 * 获取订单信息
	 */
	/*@Test
	public void getOrdersTest() {
		Orders orders=null;
		try {
			orders = odersBean.getOrders("c4253acd027840b68e21c3ae861db022");
			System.out.println(orders.getTotalPrice());
		} catch (MyFormException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(orders);
	}*/
	
	/*
	 * 分页获取订单信息
	 */
	/*@Test
	public void getOrdersListTest() {
		try {
			System.out.println(odersBean.getOrdersList(2, 3).size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	/*
	 * 对订单进行保存
	 */
	@Test
	public void saveOrdersTest() {
		//初始化客户信息
		Customer customer=customerBean.getCustomer(1);
		//初始化购物车
		try {
			odersBean.saveOrders(initOrders(customer), 2,initShoppingCart(), customer);
		} catch (MyFormException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Orders initOrders(Customer customer){
		Orders orders=new Orders();
		orders.setSendName(customer.getName());
		orders.setSendAddress(customer.getAddress());
		orders.setSendZip(customer.getZip());
		orders.setSendTel(customer.getTel());
		orders.setPayment(2);
		orders.setMeno("请不要发次品");
		return orders;
	}
	
	/*
	 * 初始化购物车信息用于后面的订单保存
	 */
	private ShoppingCart initShoppingCart(){
		ShoppingCart shoppingCart=null;//购物车类
		Product product1=productBean.getProduct(76);
		Product product2=productBean.getProduct(73);
		Product product3=productBean.getProduct(80);
		shoppingCart=shoppingCartBean.addShoppingCart(shoppingCart, product1.getId(), 3);
		shoppingCartBean.addShoppingCart(shoppingCart, product1.getId(), 6);
		shoppingCartBean.addShoppingCart(shoppingCart, product2.getId(), 11);
		shoppingCartBean.addShoppingCart(shoppingCart, product3.getId(), 6);
		shoppingCartBean.addShoppingCart(shoppingCart, product2.getId(), 1);
		shoppingCartBean.addShoppingCart(shoppingCart, product3.getId(), 7);
		printShoppingCart(shoppingCart);
		return shoppingCart;
	}
	/*
	 * 打印购物车信息，用于测试
	 */
	private void printShoppingCart(ShoppingCart shoppingCart){
		if(shoppingCart!=null){
			//获取购物车里的商品集合
			List<ShoppingCartItem> list=shoppingCart.getList();
			for(ShoppingCartItem item:list){
				System.out.print(item.getProduct().getName()+"\t");
				System.out.print(item.getNumber()+"\t");
				System.out.print(item.getOriginalPriceOfTotal()+"\t");
				System.out.print(item.getPriceOfTotal()+"\t");
				System.out.print(item.getPriceOfTotalByRuduce()+"\t");
				System.out.println();
			}
			System.out.println("商品数量："+shoppingCart.getNumberOfProduct());
			System.out.println("价格总计："+shoppingCart.getOriginalPriceOfTotal());
			System.out.println("优惠金额："+shoppingCart.getPriceOfTotalByRuduce());
			System.out.println("最后价格："+shoppingCart.getPriceOfTotal());
		}
	}
}
