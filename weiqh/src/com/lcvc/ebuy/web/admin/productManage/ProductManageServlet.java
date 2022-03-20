package com.lcvc.ebuy.web.admin.productManage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.ProductBean;
import com.lcvc.ebuy.model.Product;
@WebServlet("/admin/productmanage/showproduct")
public class ProductManageServlet extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
				//session传递管理员列表到界面
				ProductBean productBean = new ProductBean();
				List<Product> Products = productBean.getProducts();
				request.setAttribute("Products", Products);
				request.getRequestDispatcher("/jsp/admin/product/product.jsp").forward(request,response);
				
				
	}
}
