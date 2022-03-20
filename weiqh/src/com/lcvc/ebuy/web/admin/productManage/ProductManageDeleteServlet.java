package com.lcvc.ebuy.web.admin.productManage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.ProductBean;

@WebServlet("/admin/go/doDelectProduct")
public class ProductManageDeleteServlet extends HttpServlet{
	private ProductBean product = new ProductBean();
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id=Integer.valueOf(request.getParameter("id"));
		boolean status = product.deleteProduct(id);
		if(status == true){
			request.setAttribute("message", "操作成功！");
		}else{
			request.setAttribute("message", "操作失败！");
		}
		request.getRequestDispatcher("/admin/productManage/ProductPage").forward(request, response);
	}
}
