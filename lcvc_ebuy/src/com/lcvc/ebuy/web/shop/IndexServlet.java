package com.lcvc.ebuy.web.shop;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.ProductBean;
import com.lcvc.ebuy.bean.ProductTypeBean;

/*
 * 处理订餐系统首页数据
 */
@WebServlet(urlPatterns="/shop/index")
public class IndexServlet extends HttpServlet {
	private ProductTypeBean productTypeBean = new ProductTypeBean();
	private ProductBean productBean = new ProductBean();

	public IndexServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		//获取产品分类
		request.setAttribute("productTypelist", productTypeBean.getProductTypes());
		//获取最新产品集合
		request.setAttribute("newProducts", productBean.getNewProductsForFrontdesk(8));
		//获取最热门产品集合
		request.setAttribute("hotProducts", productBean.getHotProductsForFrontdesk(8));
		
		request.getRequestDispatcher("/jsp/shop/index.jsp").forward(request,response);
		
	}
}
