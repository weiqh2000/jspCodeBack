package com.lcvc.ebuy.web.admin.product;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.ProductBean;
import com.lcvc.ebuy.bean.ProductTypeBean;

/*
 * 跳转到产品添加页面
 */
@WebServlet(urlPatterns="/admin/product/toUpdateProduct")
public class ProductToUpdateProductServlet extends HttpServlet {

	public ProductToUpdateProductServlet() {
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
		//产品值
		ProductBean productBean=new ProductBean();
		String id=request.getParameter("id");
		request.setAttribute("product", productBean.getProduct(id));
		request.getRequestDispatcher("/jsp/admin/product/productupdate.jsp").forward(request,response);
	}
}
