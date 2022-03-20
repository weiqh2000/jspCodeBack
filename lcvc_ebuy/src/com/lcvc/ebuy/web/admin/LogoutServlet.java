package com.lcvc.ebuy.web.admin;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * 处理注销请求
 */
@WebServlet(urlPatterns="/admin/logout")
public class LogoutServlet extends HttpServlet {

	public LogoutServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		HttpSession session=request.getSession(); 
		session.removeAttribute("admin");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		response.sendRedirect(basePath+"jsp/admin/login.jsp");
	}
}
