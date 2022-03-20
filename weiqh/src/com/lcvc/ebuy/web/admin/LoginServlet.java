package com.lcvc.ebuy.web.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcvc.ebuy.bean.AdminBean;
@WebServlet("/admin/login")
public class LoginServlet extends HttpServlet {
	
	private AdminBean adminbean = new AdminBean();
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String username = request.getParameter("username");
		String userpass = request.getParameter("userpass");
		HttpSession session = request.getSession();
		if(adminbean.login(username, userpass)){
			session.setAttribute("admin",adminbean.getAdmin(username));
			response.sendRedirect(basePath+"jsp/admin/main.jsp");
		}else{
			out.print("登录失败,三秒后跳转回登录界面");
			response.setHeader("refresh","3;url="+basePath+"jsp/admin/login.jsp");
		}
	}
}
