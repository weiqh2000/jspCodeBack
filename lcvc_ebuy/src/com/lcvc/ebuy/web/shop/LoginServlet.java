package com.lcvc.ebuy.web.shop;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcvc.ebuy.bean.CustomerBean;
import com.lcvc.ebuy.model.Customer;

/*
 * 处理登录请求
 */
@WebServlet(urlPatterns="/shop/login")
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
		CustomerBean customerBean=new CustomerBean();
		try {
			Customer customer=customerBean.login(username, password);
			if(customer!=null){
				HttpSession session=request.getSession(); 
				session.setAttribute("customer", customer);
				response.sendRedirect(basePath+"shop/index");
			}else{
				request.setAttribute("myMessage", "登录失败：用户名和密码错误");
				request.getRequestDispatcher("/jsp/shop/signin.jsp").forward(request,response);
			}
		} catch (Exception e) {
			request.setAttribute("myMessage", e.getMessage());
			request.getRequestDispatcher("/jsp/shop/signin.jsp").forward(request,response);
		}
	}
}
