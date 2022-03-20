package com.nyl.ebuy.web.admin.adminmanage;


import com.nyl.ebuy.bean.AdminBean;
import com.nyl.ebuy.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
 * 处理管理员账户列表查询请求
 */
@WebServlet(urlPatterns="/admin/adminmanage/adminmanage")
public class AdminManageServlet extends HttpServlet {

	public AdminManageServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		AdminBean adminBean=new AdminBean();
		List<Admin> list=adminBean.getAdmins();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/jsp/admin/adminmanage/adminmanage.jsp").forward(request,response);
	}
}
