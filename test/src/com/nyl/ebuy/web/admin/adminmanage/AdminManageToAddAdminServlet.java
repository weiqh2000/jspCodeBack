package com.nyl.ebuy.web.admin.adminmanage;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 *  跳转到管理账户添加页面
 */
@WebServlet(urlPatterns="/admin/adminmanage/toAddAdmin")
public class AdminManageToAddAdminServlet extends HttpServlet {

	public AdminManageToAddAdminServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/admin/adminmanage/adminadd.jsp").forward(request,response);
	}
}
