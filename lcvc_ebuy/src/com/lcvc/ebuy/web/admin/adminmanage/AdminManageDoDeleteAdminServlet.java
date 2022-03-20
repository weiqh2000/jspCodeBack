package com.lcvc.ebuy.web.admin.adminmanage;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.AdminBean;
import com.lcvc.ebuy.model.Admin;
import com.lcvc.ebuy.model.exception.MyFormException;

/*
 * 处理管理员账户列表的删除请求
 */
@WebServlet(urlPatterns="/admin/adminmanage/doDeleteAdmin")
public class AdminManageDoDeleteAdminServlet extends HttpServlet {

	public AdminManageDoDeleteAdminServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String userId=request.getParameter("userId");
		AdminBean adminBean=new AdminBean();
		try {
			boolean flag=adminBean.deleteAdmin(userId,((Admin)request.getSession().getAttribute("admin")).getUserId());
			if(!flag){
				request.setAttribute("myMessage", "账户删除失败");
			}
		} catch (MyFormException e) {
			request.setAttribute("myMessage", e.getMessage());
		}
		List<Admin> list=adminBean.getAdmins();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/jsp/admin/adminmanage/adminmanage.jsp").forward(request,response);
	}
}
