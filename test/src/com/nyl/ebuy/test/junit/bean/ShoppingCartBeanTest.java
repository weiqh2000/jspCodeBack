package com.nyl.ebuy.test.junit.bean;

import com.nyl.ebuy.bean.ProductBean;
import com.nyl.ebuy.bean.ShoppingCartBean;
import com.nyl.ebuy.model.Product;
import com.nyl.ebuy.model.ShoppingCart;
import com.nyl.ebuy.model.ShoppingCartItem;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

/*
 * 购物车测试类
 */
public class ShoppingCartBeanTest {
	private static ShoppingCartBean shoppingCartBean;
	private static ProductBean productBean;
	
	@BeforeClass
	// 注意,这里必须是static...因为方法将在类被装载的时候就被调用(那时候还没创建实例)
	public static void before() {
		shoppingCartBean=new ShoppingCartBean();
		productBean=new ProductBean();
	}

	@AfterClass
	public static void after() {
		shoppingCartBean=null;
		productBean=null;
	}
	
	/*
	 * 对购物车购物、编辑、移除等进行测试
	 */
	@Test
	public void shoppingTest() {
		ShoppingCart shoppingCart=null;//购物车类
		Product product1=productBean.getProduct(31);
		Product product2=productBean.getProduct(33);
		Product product3=productBean.getProduct(9);
		shoppingCart=shoppingCartBean.addShoppingCart(shoppingCart, product1.getId(), 3);
		shoppingCartBean.addShoppingCart(shoppingCart, product1.getId(), 6);
		shoppingCartBean.addShoppingCart(shoppingCart, product2.getId(), 3);
		shoppingCartBean.addShoppingCart(shoppingCart, product3.getId(), 6);
		shoppingCartBean.addShoppingCart(shoppingCart, product2.getId(), 1);
		shoppingCartBean.addShoppingCart(shoppingCart, product3.getId(), 7);
		shoppingCartBean.updateShoppingCart(shoppingCart, product1.getId(), 2);
		shoppingCartBean.removeShoppingCart(shoppingCart, product2.getId());
		printShoppingCart(shoppingCart);
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
