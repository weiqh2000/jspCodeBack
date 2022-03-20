package com.lcvc.ebuy.web.admin.producttype;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.ProductTypeBean;

/*
 * 跳转到产品分类的添加页面
 */
@WebServlet(urlPatterns="/admin/producttype/toAddProductType")
public class ProductTypeToAddProductTypeServlet extends HttpServlet {
	private ProductTypeBean productTypeBean = new ProductTypeBean();

	public ProductTypeToAddProductTypeServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		request.setAttribute("productType", productTypeBean.getProductTypeInit());
		request.getRequestDispatcher("/jsp/admin/producttype/producttypeadd.jsp").forward(request,response);
	}
}
