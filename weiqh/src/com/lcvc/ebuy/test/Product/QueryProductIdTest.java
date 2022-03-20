package com.lcvc.ebuy.test.Product;
import org.junit.Test;

import com.lcvc.ebuy.bean.ProductBean;
public class QueryProductIdTest {

	private ProductBean productBean = new ProductBean();
	
	@Test
	public void productTypeAddTest(){
		
		System.out.println(productBean.getProduct(72));
	}
}
