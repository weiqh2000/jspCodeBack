package com.nyl.ebuy.web.admin.product;


import com.nyl.ebuy.bean.ProductBean;
import com.nyl.ebuy.bean.ProductTypeBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * 跳转到产品添加页面
 */
@WebServlet(urlPatterns="/admin/product/toAddproduct")
public class ProductToAddProductServlet extends HttpServlet {

	public ProductToAddProductServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		//传递当前页码参数，保证返回的时候能返回产品管理的当前页
		request.setAttribute("page", request.getParameter("page"));
		//传递产品栏目集合
		ProductTypeBean productTypeBean=new ProductTypeBean();
		request.setAttribute("productTypes", productTypeBean.getProductTypes());
		//产品初始值
		ProductBean productBean=new ProductBean();
		request.setAttribute("product", productBean.getProductInit());
		request.getRequestDispatcher("/jsp/admin/product/productadd.jsp").forward(request,response);
	}
}
