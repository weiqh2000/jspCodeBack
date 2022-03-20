package com.lcvc.ebuy.test.shop;

import java.util.List;

import org.junit.Test;

import com.lcvc.ebuy.bean.ProductBean;
import com.lcvc.ebuy.model.Product;

public class QueryNewProduct {

	private ProductBean productBean = new ProductBean();
	
	@Test
	public void QueryIndexProduct(){
		List<Product> Products = productBean.indexProducts();
		System.out.println(Products.size());
		for(Product product:Products){
     		System.out.println(product.toString());
		}
	}
	
	@Test
	public void QueryIndexHotProducts(){
		List<Product> Products = productBean.indexHotProducts();
		System.out.println(Products.size());
		for(Product product:Products){
     		System.out.println(product.toString());
		}
	}
	
}
