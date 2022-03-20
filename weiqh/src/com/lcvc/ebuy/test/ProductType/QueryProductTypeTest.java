package com.lcvc.ebuy.test.ProductType;

import java.util.List;

import org.junit.Test;

import com.lcvc.ebuy.bean.ProductTypeBean;
import com.lcvc.ebuy.model.ProductType;

public class QueryProductTypeTest {

	private ProductTypeBean productBean = new ProductTypeBean();
	
	@Test
	public void QueryProductType(){
			List<ProductType> ProductTypes = productBean.getProductTypes();
			System.out.println(ProductTypes.size());
			for(ProductType productType:ProductTypes){
				System.out.println(productType.toString());
			}
			
		
	}
}
