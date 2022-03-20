package com.lcvc.ebuy.go.ToAdmin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.AdminBean;
import com.lcvc.ebuy.bean.PageBean;
import com.lcvc.ebuy.model.Admin;
import com.lcvc.ebuy.model.Page;

@WebServlet("/go/toPageAdmin")
public class ToPageAdmin  extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse reponse)
			throws ServletException, IOException {
		
		Integer index = 1;
		if(request.getParameter("index") == null || request.getParameter("index") == ""){
			
		}else{
			index = Integer.valueOf(request.getParameter("index"));
		}
		
		PageBean pageBean = new PageBean();
		Page page1 = new Page();
		page1.setPageNow(index);
		Page page = pageBean.page(page1);
		List<Admin> admins = pageBean.pageAdmins(page1);
		request.setAttribute("admins", admins);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/jsp/admin/adminmanage/adminPage.jsp").forward(request, reponse);
	}
}
