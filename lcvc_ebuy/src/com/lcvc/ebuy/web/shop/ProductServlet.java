package com.lcvc.ebuy.web.shop;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.ProductBean;

/*
 * 获取商品详细页面
 */
@WebServlet(urlPatterns="/shop/product")
public class ProductServlet extends HttpServlet {
	private ProductBean productBean = new ProductBean();

	public ProductServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String productId=request.getParameter("productId");
		//获取该产品类别的产品列表
		request.setAttribute("product", productBean.getProduct(productId));
		
		request.getRequestDispatcher("/jsp/shop/product.jsp").forward(request,response);
		
	}
}
