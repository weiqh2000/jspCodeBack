package com.lcvc.ebuy.test.shop;
import java.util.List;

import org.junit.Test;

import com.lcvc.ebuy.bean.ProductBean;
import com.lcvc.ebuy.model.Page;
import com.lcvc.ebuy.model.Product;
public class QueryProductTypeShowTesy {

	private ProductBean productBean = new ProductBean();
	
	@Test
	public void productTypeAddTest(){
		Page page = new Page();
		List<Product> products = productBean.pageIndexProducts(page, 25);
		System.out.println(productBean.pageIndexProducts(page, 25).size());
		
		for(Product product:products){
			System.out.println(product.toString());
		}
	}
}
