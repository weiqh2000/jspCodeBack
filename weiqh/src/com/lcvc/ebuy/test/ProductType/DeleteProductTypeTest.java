package com.lcvc.ebuy.test.ProductType;

import org.junit.Test;

import com.lcvc.ebuy.bean.ProductTypeBean;

public class DeleteProductTypeTest {
	private ProductTypeBean productTypeBean = new ProductTypeBean();
	
	@Test
	public void DeleteProductType(){
		System.out.println(productTypeBean.queryproductQuantity(1));
		System.out.println(productTypeBean.deleteProductType(1));
	}
}
