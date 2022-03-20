package com.lcvc.ebuy.web.admin.productTypeManage;

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

@WebServlet("/admin/productTypeManage/ProductTypePage")
public class ProductTypeManagePageServlet extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
				//session传递管理员列表到界面
				ProductTypeBean productTypeBean = new ProductTypeBean();
				Page page1 = new Page();
				Page page = productTypeBean.page(page1);
				List<ProductType> productTypes = productTypeBean.pageProductTypes(page1);
				request.setAttribute("productTypes", productTypes);
				request.setAttribute("page", page);
				request.getRequestDispatcher("/jsp/admin/product/productTypePage.jsp").forward(request,response);
				
	}
}
