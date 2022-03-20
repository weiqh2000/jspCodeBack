package com.nyl.ebuy.web.admin.producttype;


import com.nyl.ebuy.bean.ProductTypeBean;
import com.nyl.ebuy.model.ProductType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * 跳转到产品分类的编辑页面
 */
@WebServlet(urlPatterns="/admin/producttype/toUpdateProductType")
public class ProductTypeToUpdateProductTypeServlet extends HttpServlet {

	public ProductTypeToUpdateProductTypeServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String id = request.getParameter("id");
		ProductTypeBean productTypeBean = new ProductTypeBean();
		ProductType productType = productTypeBean.getProductType(id);
		request.setAttribute("productType", productType);
		request.getRequestDispatcher("/jsp/admin/producttype/producttypeupdate.jsp").forward(request,response);
	}
}
