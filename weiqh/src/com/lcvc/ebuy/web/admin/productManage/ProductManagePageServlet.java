package com.lcvc.ebuy.web.admin.productManage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lcvc.ebuy.bean.ProductBean;
import com.lcvc.ebuy.model.Page;
import com.lcvc.ebuy.model.Product;

@WebServlet("/admin/productManage/ProductPage")
public class ProductManagePageServlet  extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
				//session传递管理员列表到界面
				ProductBean productBean = new ProductBean();
				Page page1 = new Page();
				Page page = productBean.page(page1);
				List<Product> products = productBean.pageProducts(page1);
				request.setAttribute("products", products);
				request.setAttribute("page", page);
				request.getRequestDispatcher("/jsp/admin/product/productPage.jsp").forward(request,response);
				
	}
}
