package com.lcvc.ebuy.web.admin.productTypeManage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.AdminBean;
import com.lcvc.ebuy.bean.ProductTypeBean;
import com.lcvc.ebuy.model.Admin;
import com.lcvc.ebuy.model.ProductType;
@WebServlet("/admin/product/EditddProductType")
public class ProductTypeManageEditServlet extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		if(request.getParameter("name").trim().equals("")){
			request.setAttribute("message", "操作失败name为空");
			request.getRequestDispatcher("/go/toPageProductType").forward(request, response);
		}else if(request.getParameter("linkUrl").trim().equals("")){
			request.setAttribute("message", "操作失败linkUrl为空");
			request.getRequestDispatcher("/go/toPageProductType").forward(request, response);
		}else if(request.getParameter("imageUrl").trim().equals("")){
			request.setAttribute("message", "操作失败imageUrl为空");
			request.getRequestDispatcher("/go/toPageProductType").forward(request, response);
		}else if(request.getParameter("orderNum").trim().equals("")){
			request.setAttribute("message", "操作失败orderNum为空");
			request.getRequestDispatcher("/go/toPageProductType").forward(request, response);
		}else{

			ProductType productType = new ProductType();
			productType.setImageUrl(request.getParameter("imageUrl"));
			productType.setLinkUrl(request.getParameter("linkUrl"));
			productType.setName(request.getParameter("name"));
			productType.setId(Integer.valueOf(request.getParameter("Id")));
			productType.setIntro(request.getParameter("intro"));
			productType.setOrderNum(Integer.valueOf(request.getParameter("orderNum")));
			
			ProductTypeBean productTypeBean = new ProductTypeBean();
			int status = productTypeBean.deitProductType(productType);
			if(status == 0){
				request.setAttribute("message", "操作成功");
			}else if(status == 2){
				request.setAttribute("message", "操作失败");
			}else if(status == 1){
				request.setAttribute("message", "用户重名！");
			}
			request.getRequestDispatcher("/go/toPageProductType").forward(request, response);
		}

	}
}
