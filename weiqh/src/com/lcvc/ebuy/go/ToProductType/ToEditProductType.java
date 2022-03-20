package com.lcvc.ebuy.go.ToProductType;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.AdminBean;
import com.lcvc.ebuy.bean.ProductBean;
import com.lcvc.ebuy.bean.ProductTypeBean;
import com.lcvc.ebuy.model.Admin;
import com.lcvc.ebuy.model.Product;
import com.lcvc.ebuy.model.ProductType;
@WebServlet("/admin/Product/toEditProductType")
public class ToEditProductType extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse reponse)
			throws ServletException, IOException {
		Integer Id = Integer.valueOf(request.getParameter("Id"));
		ProductTypeBean productTypeBean = new ProductTypeBean();
		ProductType productType = productTypeBean.getProductType(Id);
		
		request.setAttribute("productType", productType);
		
		request.getRequestDispatcher("/jsp/admin/product/productTypeEdit.jsp").forward(request, reponse);
	}
}
