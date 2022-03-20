package com.lcvc.ebuy.web.admin;


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
 * 处理登录请求
 */
@WebServlet(urlPatterns="/admin/login")
public class LoginServlet extends HttpServlet {

	public LoginServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		AdminBean adminBean=new AdminBean();
		try {
			Admin admin=adminBean.login(username, password);
			if(admin!=null){
				HttpSession session=request.getSession(); 
				session.setAttribute("admin", admin);
				response.sendRedirect(basePath+"jsp/admin/main.jsp");
			}else{
				request.setAttribute("message", "登录失败：用户名和密码错误");
				request.getRequestDispatcher("/jsp/admin/login.jsp").forward(request,response);
			}
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("login.jsp").forward(request,response);
		}
	}
}
