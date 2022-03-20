package com.lcvc.ebuy.web.admin.producttype;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.ProductTypeBean;
import com.lcvc.ebuy.model.ProductType;
import com.lcvc.ebuy.model.exception.MyFormException;

/*
 * 处理产品分类的删除请求，删除后返回产品分类列表
 */
@WebServlet(urlPatterns="/admin/producttype/doDeleteProductType")
public class ProductTypeDoDeleteProductTypeServlet extends HttpServlet {

	public ProductTypeDoDeleteProductTypeServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String id = request.getParameter("id");
		ProductTypeBean productTypeBean = new ProductTypeBean();
		try {
			boolean flag = productTypeBean.deleteProductType(id);
			if (!flag) {
				request.setAttribute("myMessage", "productType删除失败！");
			}
		} catch (MyFormException e) {
			request.setAttribute("myMessage", e.getMessage());
		}
		List<ProductType> list= productTypeBean.getProductTypes();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/jsp/admin/producttype/producttypemanage.jsp").forward(request,response);
	}
}
