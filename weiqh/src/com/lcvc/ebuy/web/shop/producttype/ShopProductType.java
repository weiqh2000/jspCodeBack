package com.lcvc.ebuy.web.shop.producttype;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.ProductBean;
import com.lcvc.ebuy.bean.ProductTypeBean;
import com.lcvc.ebuy.model.Page;
import com.lcvc.ebuy.model.Product;

@WebServlet("/shop/producttype")
public class ShopProductType extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			ProductBean productBean = new ProductBean();
			Page page1 = new Page();
			Page page = productBean.page(page1);

			List<Product> products = productBean.pageIndexProducts(page1, Integer.valueOf(request.getParameter("productTypeId")));

			request.setAttribute("products", products);
			request.setAttribute("page", page);
			request.getRequestDispatcher("/jsp/shop/producttype.jsp").forward(request, response);
	}	

}
