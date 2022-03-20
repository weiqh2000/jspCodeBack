package com.lcvc.ebuy.test.Product;

import java.util.List;

import org.junit.Test;

import com.lcvc.ebuy.bean.ProductBean;

import com.lcvc.ebuy.model.Page;
import com.lcvc.ebuy.model.Product;

public class QueryProductTest2 {
	private ProductBean productBean = new ProductBean();
	private Page page1 = new Page();
	@Test
	public void queryproduct(){
		productBean.page(page1);
		List<Product> products = productBean.pageProducts(page1);
		System.out.println(products.size());
		for(Product product:products){
			System.out.println(product.toString());
		}
	}
}
