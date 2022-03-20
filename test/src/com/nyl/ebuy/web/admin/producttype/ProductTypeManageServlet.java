package com.nyl.ebuy.web.admin.producttype;

import com.nyl.ebuy.bean.ProductTypeBean;
import com.nyl.ebuy.model.ProductType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
 * 处理产品分类列表查询请求，不分页
 */
@WebServlet(urlPatterns = "/admin/producttype/producttypemanage")
public class ProductTypeManageServlet extends HttpServlet {

	public ProductTypeManageServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		ProductTypeBean productTypeBean = new ProductTypeBean();
		List<ProductType> list= productTypeBean.getProductTypes();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/jsp/admin/producttype/producttypemanage.jsp").forward(request, response);
	}
}
