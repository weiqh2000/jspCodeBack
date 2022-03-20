package com.lcvc.ebuy.web.shop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.ProductBean;
import com.lcvc.ebuy.bean.ProductTypeBean;

@WebServlet("/shop/index")
public class IndexServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			ProductTypeBean productTypeBean = new ProductTypeBean();
			ProductBean productBean = new ProductBean();
			
			
			request.setAttribute("newProducts", productBean.indexProducts());
			request.setAttribute("productTypelist", productTypeBean.getProductTypes());
			request.setAttribute("hotProducts", productBean.indexHotProducts());
    		
			request.getRequestDispatcher("/jsp/shop/index.jsp").forward(request, response);
	}

}
