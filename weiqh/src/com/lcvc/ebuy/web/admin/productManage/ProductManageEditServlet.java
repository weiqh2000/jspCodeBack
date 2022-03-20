package com.lcvc.ebuy.web.admin.productManage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.ProductBean;
import com.lcvc.ebuy.bean.ProductTypeBean;
import com.lcvc.ebuy.model.Admin;
import com.lcvc.ebuy.model.Product;
import com.lcvc.ebuy.model.ProductType;

@WebServlet("/admin/product/EditddProduct")
public class ProductManageEditServlet extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		if(request.getParameter("productTypeId").trim().equals("")){
			request.setAttribute("message", "productTypeId为空！");
			request.getRequestDispatcher("/go/toPageProduct").forward(request, response);
		}else if(request.getParameter("name").trim().equals("")){
			System.out.println(request.getParameter("name"));
			request.setAttribute("message", "name为空！");
			request.getRequestDispatcher("/go/toPageProduct").forward(request, response);		
		}else if(request.getParameter("picUrl").trim().equals("")){
			request.setAttribute("message", "picUrl为空！");
			request.getRequestDispatcher("/go/toPageProduct").forward(request, response);
		}else if(request.getParameter("originalPrice").trim().equals("")){
			request.setAttribute("message", "originalPrice为空！");
			request.getRequestDispatcher("/go/toPageProduct").forward(request, response);
		}else if(request.getParameter("price").trim().equals("")){
			request.setAttribute("message", "price为空！");
			request.getRequestDispatcher("/go/toPageProduct").forward(request, response);
		}else if(request.getParameter("number").trim().equals("")){
			request.setAttribute("message", "number为空！");
			request.getRequestDispatcher("/go/toPageProduct").forward(request, response);
		}else if(request.getParameter("orderNum").trim().equals("")){
			request.setAttribute("message", "orderNum为空！");
			request.getRequestDispatcher("/go/toPageProduct").forward(request, response);
		}else if(request.getParameter("click").trim().equals("")){
			request.setAttribute("message", "click为空！");
			request.getRequestDispatcher("/go/toPageProduct").forward(request, response);
		}else if(request.getParameter("onSale").trim().equals("")){
			request.setAttribute("message", "onSale为空！");
			request.getRequestDispatcher("/go/toPageProduct").forward(request, response);
		}else{
			Product product = new Product();
			product.setId(Integer.valueOf(request.getParameter("id")));
			product.setProductTypeId(Integer.valueOf(request.getParameter("productTypeId")));
			product.setName(request.getParameter("name"));
			product.setPicUrl(request.getParameter("picUrl"));
			product.setOriginalPrice(Float.valueOf(request.getParameter("originalPrice")));
			product.setPrice(Float.valueOf(request.getParameter("price")));
			product.setNumber(Integer.valueOf(request.getParameter("number")));
			product.setOrderNum(Integer.valueOf(request.getParameter("orderNum")));
			product.setClick(Integer.valueOf(request.getParameter("click")));
			product.setOnSale(Boolean.valueOf(request.getParameter("onSale")));
			product.setDescription(request.getParameter("description"));
			product.setContent(request.getParameter("content"));
			product.setFinalEditorId(((Admin) request.getSession().getAttribute("admin")).getUserId());
			

			
			ProductBean productBean = new ProductBean();
			int status = productBean.deitProduct(product);
			if(status == 0){
				request.setAttribute("message", "操作成功");
				request.getRequestDispatcher("/go/toPageProduct").forward(request, response);
			}else if(status == 2){
				request.setAttribute("message", "操作失败");
				request.getRequestDispatcher("/go/toPageProduct").forward(request, response);
			}else if(status == 1){
				request.setAttribute("message", "用户重名！");
				request.getRequestDispatcher("/go/toPageProduct").forward(request, response);
			}
		}
		

		
		
	}
}

