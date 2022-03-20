package com.lcvc.ebuy.test.junit.bean;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lcvc.ebuy.bean.ProductBean;
import com.lcvc.ebuy.model.Product;
import com.lcvc.ebuy.model.other.PageObject;

/*
 * 产品类别测试类
 */
public class ProductBeanTest {
	private static ProductBean productBean;
	
	@BeforeClass
	// 注意,这里必须是static...因为方法将在类被装载的时候就被调用(那时候还没创建实例)
	public static void before() {
		productBean=new ProductBean();
	}

	@AfterClass
	public static void after() {
		productBean=null;
	}
	
	/*
	 *  获取产品的分页记录
	 */
	@Test
	public void getAdminsTest() {
		PageObject<Product> pageObject=productBean.getProducts(2, 5);
		List<Product> list=pageObject.getList();
		System.out.println("当前页"+pageObject.getCurrentPage());
		System.out.println("最大页"+pageObject.getMaxPage());
		System.out.println("总记录数"+pageObject.getTotalRecords());
		for(Product product:list){
			System.out.print(product.getId()+"\t");
			System.out.print(product.getName()+"\t");
			System.out.print(product.getCreator().getScreenName()+"\t");
			System.out.println();
		}
	}
	
	/*
	 *  获取产品的总记录数
	 */
	/*@Test
	public void getRecordCountTest() {
		int count=productBean.getRecordCount();
		System.out.println(count);
	}*/
}
