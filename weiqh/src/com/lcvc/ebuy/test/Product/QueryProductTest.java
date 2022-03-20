package com.lcvc.ebuy.test.Product;

import java.util.List;

import org.junit.Test;

import com.lcvc.ebuy.bean.ProductBean;
import com.lcvc.ebuy.model.Product;

public class QueryProductTest {

	private ProductBean productBean = new ProductBean();
	
	@Test
	public void QueryProduct(){
		List<Product> Products = productBean.getProducts();
		System.out.println(Products.size());
		for(Product productType:Products){
			System.out.println(productType.toString());
		}
	}
}
