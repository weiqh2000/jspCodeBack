package com.lcvc.ebuy.web.admin.productTypeManage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.AdminBean;
import com.lcvc.ebuy.bean.ProductTypeBean;
import com.lcvc.ebuy.model.Admin;
import com.lcvc.ebuy.model.ProductType;

@WebServlet("/admin/productTypemanage/showproductType")
public class ProductTypeManageShowServlet extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
				//session传递管理员列表到界面
				ProductTypeBean productType = new ProductTypeBean();
				List<ProductType> ProductTypes = productType.getProductTypes();
				request.setAttribute("ProductTypes", ProductTypes);
				request.getRequestDispatcher("/jsp/admin/product/productType.jsp").forward(request,response);
				
				
	}
}
