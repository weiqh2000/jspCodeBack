package com.lcvc.ebuy.web.admin.product;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.ProductBean;
import com.lcvc.ebuy.model.Product;
import com.lcvc.ebuy.model.exception.MyFormException;
import com.lcvc.ebuy.model.other.PageObject;

/*
 * 处理产品列表查询请求
 */
@WebServlet(urlPatterns="/admin/product/doDeleteProduct")
public class ProductDoDeleteProductServlet extends HttpServlet {

	public ProductDoDeleteProductServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String id=request.getParameter("id");
		ProductBean productBean=new ProductBean();
		try {
			boolean flag=productBean.deleteProduct(id);
			if(!flag){
				request.setAttribute("myMessage", "产品删除失败");
			}
		} catch (MyFormException e) {
			request.setAttribute("myMessage", e.getMessage());
		} catch (SQLException e) {
			request.setAttribute("myMessage", e.getMessage());
		}
		String page=request.getParameter("page");//获取当前页码
		PageObject<Product> pageObject=productBean.getProducts(page, 20);
		request.setAttribute("pageObject", pageObject);
		request.getRequestDispatcher("/jsp/admin/product/productmanage.jsp").forward(request,response);
	}
}
