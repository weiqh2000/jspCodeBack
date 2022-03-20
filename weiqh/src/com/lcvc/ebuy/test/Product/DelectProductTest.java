package com.lcvc.ebuy.test.Product;

import org.junit.Test;

import com.lcvc.ebuy.bean.ProductBean;

public class DelectProductTest {
	private ProductBean productType = new ProductBean();
	
	@Test
	public void DeleteProductType(){
		System.out.println(productType.deleteProduct(100));
	}
}