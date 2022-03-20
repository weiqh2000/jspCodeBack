package com.lcvc.ebuy.web.admin.productManage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcvc.ebuy.bean.ProductBean;
import com.lcvc.ebuy.model.Admin;
import com.lcvc.ebuy.model.Product;

@WebServlet("/admin/go/doAddProduct")
public class ProductManageAddServlet extends HttpServlet{
	private ProductBean productBean = new ProductBean();
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
//		if(request.getParameter("name") == null || request.getParameter("name") == ""){
//			request.setAttribute("message", "账户添加失败：产品分类为空");
//			request.getRequestDispatcher("/go/toPageProductType").forward(request, response);
//		}
//		
//		if(request.getParameter("imageUrl") == null || request.getParameter("imageUrl") == ""){
//			request.setAttribute("message", "账户添加失败：图片为空");
//			request.getRequestDispatcher("/go/toPageProductType").forward(request, response);
//		}
//		
//		if(request.getParameter("orderNum") == null || request.getParameter("orderNum") == ""){
//			request.setAttribute("message", "账户添加失败：优先级为空");
//			request.getRequestDispatcher("/go/toPageProductType").forward(request, response);
//		}
		
		Product product = new Product();
		product.setProductTypeId(Integer.valueOf(request.getParameter("productTypeId")));
		product.setName(request.getParameter("name"));
		product.setOrderNum(Integer.valueOf(request.getParameter("orderNum")));
		product.setDescription(request.getParameter("description"));
		product.setContent(request.getParameter("content"));
		product.setPrice(Integer.valueOf(request.getParameter("price")));
		product.setOriginalPrice(Integer.valueOf(request.getParameter("originalPrice")));
		product.setPicUrl(request.getParameter("picUrl"));
		product.setNumber(Integer.valueOf(request.getParameter("orderNum")));
		product.setClick(Integer.valueOf(request.getParameter("click")));
		product.setOnSale(Boolean.valueOf(request.getParameter("onSale")));
		
		productBean.saveProduct(product, ((Admin) request.getSession().getAttribute("admin")).getUserId());
		
		
		request.getRequestDispatcher("/jsp/admin/product/productAdd.jsp").forward(request, response);
	}
}
