package com.lcvc.ebuy.web.admin.productTypeManage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.ProductTypeBean;


@WebServlet("/admin/productTypemanage/deleteProductType")
public class ProductTypeManageDeleteServlet extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductTypeBean productType = new ProductTypeBean();
		//Integer.valueOf表示将字符串转换成为整型
		Integer id=Integer.valueOf(request.getParameter("id"));
		boolean status = productType.deleteProductType(id);
		if(status == true){
			request.setAttribute("message", "操作成功！");
		}else{
			request.setAttribute("message", "操作失败：该产品分类下还有产品信息，无法删除");
		}
		request.getRequestDispatcher("/admin/productManage/ProductPage").forward(request, response);
	}
}
