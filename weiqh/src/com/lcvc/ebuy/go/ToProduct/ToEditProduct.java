package com.lcvc.ebuy.go.ToProduct;
import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.lcvc.ebuy.bean.AdminBean;
import com.lcvc.ebuy.bean.ProductBean;
import com.lcvc.ebuy.bean.ProductTypeBean;
import com.lcvc.ebuy.model.Admin;
import com.lcvc.ebuy.model.Page;
import com.lcvc.ebuy.model.Product;

@WebServlet("/admin/Product/toEditProduct")
public class ToEditProduct  extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse reponse)
			throws ServletException, IOException {
		Integer Id = Integer.valueOf(request.getParameter("Id"));
		ProductBean productBean = new ProductBean();
		Product product = productBean.getProduct(Id);
		ProductTypeBean productTypeBean = new ProductTypeBean();
		request.setAttribute("productType", productTypeBean.getProductTypes());
		request.setAttribute("product", product);
		
		request.getRequestDispatcher("/jsp/admin/product/productEdit.jsp").forward(request, reponse);
	}
	
}
