package com.lcvc.ebuy.test.ProductType;

import org.junit.Test;
import com.lcvc.ebuy.bean.ProductTypeBean;

public class EditProductType2 {

	private ProductTypeBean productTypeBean=new ProductTypeBean();
	
	@Test
	public void testEditAdmin(){
		System.out.println(productTypeBean.getProductType(1));
	}
}
