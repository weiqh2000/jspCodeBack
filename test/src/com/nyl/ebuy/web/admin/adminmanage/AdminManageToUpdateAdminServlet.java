package com.nyl.ebuy.web.admin.adminmanage;


import com.nyl.ebuy.bean.AdminBean;
import com.nyl.ebuy.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
