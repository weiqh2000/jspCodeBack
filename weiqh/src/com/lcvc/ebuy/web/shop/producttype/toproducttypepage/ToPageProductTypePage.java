package com.lcvc.ebuy.web.shop.producttype.toproducttypepage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.ProductTypeBean;
import com.lcvc.ebuy.model.Page;
import com.lcvc.ebuy.model.ProductType;

@WebServlet("/shop/pageProductTypePage")
public class ToPageProductTypePage extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse reponse)
			throws ServletException, IOException {
		Integer index = 1;
		if(request.getParameter("index") == null || request.getParameter("index") == ""){
			
		}else{
			index = Integer.valueOf(request.getParameter("index"));
		}
		ProductTypeBean productTypeBean = new ProductTypeBean();
		Page page1 = new Page();
		page1.setPageNow(index);
		Page page = productTypeBean.page(page1);
		List<ProductType> productTypes = productTypeBean.pageProductTypes(page1);
		
		request.setAttribute("productTypes", productTypes);
		request.setAttribute("page", page);
		request.setAttribute("productTypeId", Integer.valueOf(request.getParameter("productTypeId")));
		

		request.getRequestDispatcher("/jsp/shop/producttype.jsp").forward(request, reponse);
	}
}
