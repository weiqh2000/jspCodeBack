package com.lcvc.ebuy.test.Product;

import org.junit.Test;

import com.lcvc.ebuy.bean.ProductBean;
import com.lcvc.ebuy.model.Product;
public class ProductAdd {
	ProductBean productBean = new ProductBean();
	
	@Test
	public void productAddTest(){
	
		Product product = new Product();
		product.setProductTypeId(25);
		product.setName("erwe");
		product.setOrderNum(15);
		product.setDescription("66623666");
		product.setContent("666666");
		product.setPrice(90);
		product.setOriginalPrice(23);
		product.setPicUrl("111111");
		product.setNumber(23);
		product.setClick(22);
		product.setOnSale(true);
		
		
		System.out.println(productBean.saveProduct(product, 1));
	}
}
