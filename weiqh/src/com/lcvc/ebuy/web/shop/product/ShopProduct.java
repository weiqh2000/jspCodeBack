package com.lcvc.ebuy.web.shop.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcvc.ebuy.bean.ProductBean;
import com.lcvc.ebuy.model.Product;


@WebServlet("/shop/showProduct")
public class ShopProduct extends HttpServlet {

	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id=null;
		if(request.getParameter("id")!=""){
			id=Integer.valueOf(request.getParameter("id"));
		}else{
			request.setAttribute("message", "非法参数");
		}
		ProductBean productBean=new ProductBean();
		Product product=productBean.showProduct(id);
		request.setAttribute("product", product);
		request.getRequestDispatcher("/jsp/shop/product.jsp").forward(request, response);	//跳转页面
	}

}
 
