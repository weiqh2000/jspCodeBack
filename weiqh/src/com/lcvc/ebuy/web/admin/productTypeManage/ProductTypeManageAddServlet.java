package com.lcvc.ebuy.web.admin.productTypeManage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.ProductTypeBean;
import com.lcvc.ebuy.model.ProductType;
@WebServlet("/admin/productTypeManage/addProductType")
public class ProductTypeManageAddServlet extends HttpServlet {
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		if(request.getParameter("name") == null || request.getParameter("name") == ""){
			request.setAttribute("message", "账户添加失败：产品分类名称为空");
			request.getRequestDispatcher("/go/toPageProductType").forward(request, response);
		}
		
		if(request.getParameter("imageUrl") == null || request.getParameter("imageUrl") == ""){
			request.setAttribute("message", "账户添加失败：图片为空");
			request.getRequestDispatcher("/go/toPageProductType").forward(request, response);
		}
		
		if(request.getParameter("orderNum") == null || request.getParameter("orderNum") == ""){
			request.setAttribute("message", "账户添加失败：优先级为空");
			request.getRequestDispatcher("/go/toPageProductType").forward(request, response);
		}
		
		
		
		ProductType productType = new ProductType();
		ProductTypeBean productTypeBean = new ProductTypeBean();
		productType.setName(request.getParameter("name"));
		productType.setImageUrl(request.getParameter("imageUrl"));
		productType.setIntro(request.getParameter("intro"));
		productType.setLinkUrl(request.getParameter("linkUrl"));
		productType.setOrderNum(Integer.valueOf(request.getParameter("orderNum")));
		
		int status =productTypeBean.saveProductType(productType);		
		if(status == 1){
			request.setAttribute("message", "账户添加成功！");
		}else if(status == 2){
			request.setAttribute("message", "账户重名");
		}else if(status == 0){
			request.setAttribute("message", "账户添加失败！");
		}
		
		request.getRequestDispatcher("/go/toPageProductType").forward(request, response);
	}
}
