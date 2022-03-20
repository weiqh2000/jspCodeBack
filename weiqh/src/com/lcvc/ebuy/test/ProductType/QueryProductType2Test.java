package com.lcvc.ebuy.test.ProductType;

import java.util.List;

import org.junit.Test;

import com.lcvc.ebuy.bean.ProductTypeBean;
import com.lcvc.ebuy.model.Page;
import com.lcvc.ebuy.model.ProductType;

public class QueryProductType2Test {

	private ProductTypeBean productTypeBean = new ProductTypeBean();
	private Page page1 = new Page();
	@Test
	public void QueryProductType(){
		productTypeBean.page(page1);
		List<ProductType> productTypes = productTypeBean.pageProductTypes(page1);
		System.out.println(productTypes.size());
		
		for(ProductType productType:productTypes){
			System.out.println(productType.toString());
		}
	}
}
