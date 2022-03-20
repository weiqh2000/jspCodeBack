package com.lcvc.ebuy.web.admin.adminManage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcvc.ebuy.bean.AdminBean;
import com.lcvc.ebuy.model.Admin;

@WebServlet("/admin/adminmanage/showAdmins")
public class AdminManageServlet extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
				//session传递管理员列表到界面
				AdminBean adminBean = new AdminBean();
				List<Admin> admins = adminBean.getAdmins();
				request.setAttribute("admins", admins);
				request.getRequestDispatcher("/jsp/admin/adminmanage/admin.jsp").forward(request,response);
				
				
	}
	
	
}
