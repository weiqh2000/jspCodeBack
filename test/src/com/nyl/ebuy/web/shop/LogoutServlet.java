package com.nyl.ebuy.web.shop;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
 * 处理注销请求
 */
@WebServlet(urlPatterns="/shop/logout")
public class LogoutServlet extends HttpServlet {

	public LogoutServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		HttpSession session=request.getSession(); 
		session.removeAttribute("customer");
		response.sendRedirect(basePath+"jsp/shop/signin.jsp");
	}
}
