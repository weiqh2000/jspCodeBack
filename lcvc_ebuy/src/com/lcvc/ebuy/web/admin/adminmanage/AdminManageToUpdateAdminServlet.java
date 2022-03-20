package com.lcvc.ebuy.web.admin.adminmanage;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.AdminBean;
import com.lcvc.ebuy.model.Admin;

/*
 * 跳转到管理账户编辑页面
 */
@WebServlet(urlPatterns="/admin/adminmanage/toUpdateAdmin")
public class AdminManageToUpdateAdminServlet extends HttpServlet {

	public AdminManageToUpdateAdminServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String userId=request.getParameter("userId");
		AdminBean adminBean=new AdminBean();
		Admin admin=adminBean.getAdmin(userId);
		request.setAttribute("admin", admin);
		request.getRequestDispatcher("/jsp/admin/adminmanage/adminupdate.jsp").forward(request,response);
	}
}
