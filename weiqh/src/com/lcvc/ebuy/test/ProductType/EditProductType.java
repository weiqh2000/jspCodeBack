package com.lcvc.ebuy.test.ProductType;

import org.junit.Test;

import com.lcvc.ebuy.bean.ProductTypeBean;
import com.lcvc.ebuy.model.ProductType;

public class EditProductType {


	
	private ProductType productType = new ProductType();
	private ProductTypeBean productTypeBean = new ProductTypeBean();
	@Test
	public void editProductTypeTest(){
		productType.setId(1);
		productType.setName("小吃");
		productType.setLinkUrl("qwe");
		productType.setImageUrl("qwe");
		productType.setIntro("qwerf");
		productType.setOrderNum(334445334);
		System.out.println(productTypeBean.deitProductType(productType));
	}
}
