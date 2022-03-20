package com.lcvc.ebuy.test.Product;

import org.junit.Test;

import com.lcvc.ebuy.bean.ProductTypeBean;

public class QueryProductQuantityTest {
	
	private ProductTypeBean productType = new ProductTypeBean();
	
	@Test
	public void QueryProduct(){
		System.out.println(productType.queryproductQuantity(25));
	}
}
