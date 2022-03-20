package com.lcvc.ebuy.web.admin.admin;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcvc.ebuy.bean.AdminBean;
import com.lcvc.ebuy.model.Admin;

/*
 * 跳转到管理账户编辑页面
 */
@WebServlet(urlPatterns="/admin/admin/toUpdateMyAdmin")
public class AdminToUpdateAdminServlet extends HttpServlet {

	public AdminToUpdateAdminServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		HttpSession session=request.getSession(); 
		AdminBean adminBean=new AdminBean();
		Admin admin=adminBean.getAdmin(((Admin)session.getAttribute("admin")).getUserId());
		request.setAttribute("admin", admin);
		request.getRequestDispatcher("/jsp/admin/admin/adminupdate.jsp").forward(request,response);
	}
}
