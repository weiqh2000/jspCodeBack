package com.lcvc.ebuy.web.admin.admin;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 跳转到管理账户密码编辑页面
 */
@WebServlet(urlPatterns="/admin/admin/toUpdateMyAdminPassword")
public class AdminToUpdateAdminPasswordServlet extends HttpServlet {

	public AdminToUpdateAdminPasswordServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		request.getRequestDispatcher("/jsp/admin/admin/adminupdatepassword.jsp").forward(request,response);
	}
}
