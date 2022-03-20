package com.lcvc.ebuy.web.shop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcvc.ebuy.bean.AdminBean;
import com.lcvc.ebuy.model.Admin;
@WebServlet("/shop/zuce")
public class zuce extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminBean adminBean=new  AdminBean();
		String username = request.getParameter("username");
		String screenName = request.getParameter("screenName");
		String userpass = request.getParameter("password");
		String Fupassword = request.getParameter("Fupassword");
		if(username.trim().equals(userpass)){
			request.setAttribute("message", "账户添加失败：用户名为空");
			request.getRequestDispatcher("/jsp/shop/signin.jsp").forward(request, response);
		}
		if(username.trim().equals("")){
			request.setAttribute("message", "账户添加失败：用户名为空");
			request.getRequestDispatcher("/jsp/shop/signin.jsp").forward(request, response);
		}else if(screenName.trim().equals("")){
			request.setAttribute("message", "账户添加失败：网名名为空");
			request.getRequestDispatcher("/jsp/shop/signin.jsp").forward(request, response);
		}else if(userpass.trim().equals("")){
			request.setAttribute("message", "账户添加失败：初始密码为空");
			request.getRequestDispatcher("/jsp/shop/signin.jsp").forward(request, response);
		}else if(AdminBean.queryUsername(username) == true){
			request.setAttribute("message", "账户添加失败：用户名重名");
			request.getRequestDispatcher("/jsp/shop/signin.jsp").forward(request, response);
		}else {
			Admin admin = new Admin();
			admin.setUsername(username);
			admin.setUserpass(userpass);
			admin.setScreenName(screenName);
			adminBean.saveAdmin(admin);
			request.setAttribute("message", "账户成功!!!");
			request.getRequestDispatcher("/jsp/shop/signin.jsp").forward(request, response);
		}
	}

	}

