package com.lcvc.ebuy.test.Product;

import org.junit.Test;

import com.lcvc.ebuy.bean.ProductBean;
import com.lcvc.ebuy.bean.ProductTypeBean;
import com.lcvc.ebuy.model.Product;
import com.lcvc.ebuy.model.ProductType;

public class EditProduct {


	
	private Product product = new Product();
	private ProductBean productBean = new ProductBean();
	@Test
	public void editProductTypeTest(){
		product.setId(86);
		product.setProductTypeId(8908);
		product.setName("awef");
		product.setOrderNum(45);
		product.setDescription("jiojioj");
		product.setContent("11981");
		product.setPrice(19);
		product.setOriginalPrice(189);
		product.setPicUrl("asdf");
		product.setNumber(189);
		product.setClick(189);
		product.setOnSale(true);
		
		product.setFinalEditorId(2);
		
		System.out.println(productBean.deitProduct(product));
	}
}
