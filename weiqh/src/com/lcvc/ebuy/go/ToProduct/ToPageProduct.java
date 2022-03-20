package com.lcvc.ebuy.go.ToProduct;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.PageBean;
import com.lcvc.ebuy.bean.ProductBean;
import com.lcvc.ebuy.model.Admin;
import com.lcvc.ebuy.model.Page;
import com.lcvc.ebuy.model.Product;

@WebServlet("/go/toPageProduct")
public class ToPageProduct  extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse reponse)
			throws ServletException, IOException {
		Integer index = 1;
		if(request.getParameter("index") == null || request.getParameter("index") == ""){
			
		}else{
			index = Integer.valueOf(request.getParameter("index"));
		}
		
		ProductBean productBean = new ProductBean();
		Page page1 = new Page();
		page1.setPageNow(index);
		Page page = productBean.page(page1);
		List<Product> products = productBean.pageProducts(page1);
		
		request.setAttribute("products", products);
		request.setAttribute("page", page);
		
		request.getRequestDispatcher("/jsp/admin/product/productPage.jsp").forward(request, reponse);
	}
}
