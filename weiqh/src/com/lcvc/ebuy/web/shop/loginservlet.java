package com.lcvc.ebuy.web.shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcvc.ebuy.bean.CustomerBean;
import com.lcvc.ebuy.model.Customer;


@WebServlet("/shop/login")
public class loginservlet extends HttpServlet {
	
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		CustomerBean customerBean=new CustomerBean();
		response.setCharacterEncoding("uft-8");
		response.setContentType("text/html");
		HttpSession session=request.getSession();
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String username=request.getParameter("username");
	  	  String password=request.getParameter("password");
	  	  
	  	  if(customerBean.login(username,password)){

	  		Customer customer=customerBean.getCustomers(username);

	    		session.setAttribute("customer",customer);
	    		request.setAttribute("message", "登陆成功!");//存入用户信息
	    		//response.sendRedirect(basePath+"jsp/shop/index.jsp");   //跳转页面
	    		//response.setHeader("refresh", "1;url=MyJsp.jsp");		//进入该页面1秒后跳转页面
	    		request.getRequestDispatcher("/shop/index").forward(request, response);	//跳转页面
	   	 } else {
	    		//out.print("登录失败<br>");
	   		request.setAttribute("message", "登陆失败，请重新登录!");
	    	response.sendRedirect(basePath+"jsp/shop/signin.jsp");//跳转页面
	    		//response.setHeader("refresh", "3;url=login.html");    //进入该页面3秒后跳转页面
	    	}
	}

}
