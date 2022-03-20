package com.lcvc.ebuy.web.shop;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.ProductBean;
import com.lcvc.ebuy.bean.ProductTypeBean;
import com.lcvc.ebuy.model.other.PageObject;

/*
 * 获取商品分类页面，包含商品分类下的商品列表
 */
@WebServlet(urlPatterns="/shop/producttype")
public class ProductTypeServlet extends HttpServlet {
	private ProductTypeBean productTypeBean = new ProductTypeBean();
	private ProductBean productBean = new ProductBean();

	public ProductTypeServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		//获取产品分类
		String productTypeId=request.getParameter("productTypeId");
		String page=request.getParameter("page");
		//获取该产品类别的产品列表
		PageObject pageObject=productBean.getProductsForFrontdesk(page, 8, productTypeId);
		request.setAttribute("pageObject", pageObject);
		request.setAttribute("productType", productTypeBean.getProductType(productTypeId));
		
		request.getRequestDispatcher("/jsp/shop/producttype.jsp").forward(request,response);
		
	}
}
