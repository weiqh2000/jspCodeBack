package com.lcvc.ebuy.web.shop.SearchProduct;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.ProductBean;
import com.lcvc.ebuy.model.Product;

/*
 * 搜索商品
 */
@WebServlet(urlPatterns="/shop/doSearchProduct")
public class ProductBySearchServlet extends HttpServlet {
	private ProductBean productBean = new ProductBean();

	public ProductBySearchServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		//获取要搜索的产品名字
		String name=request.getParameter("name");
		//获取搜索后的产品列表
		List<Product> list=productBean.getProductsForFrontdesk(name);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/jsp/shop/productsearch.jsp").forward(request,response);
		
	}
}
