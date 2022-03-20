package com.lcvc.ebuy.test.ProductType;
import org.junit.Test;

import com.lcvc.ebuy.bean.ProductTypeBean;
import com.lcvc.ebuy.model.ProductType;
public class ProductTypeAdd {

	private ProductTypeBean productTypeBean = new ProductTypeBean();
	
	@Test
	public void productTypeAddTest(){
		ProductType productType = new ProductType();
		productType.setName("23ssss");
		productType.setImageUrl("23");
		productType.setOrderNum(23);
		
		System.out.println(productTypeBean.saveProductType(productType));
	}
}
