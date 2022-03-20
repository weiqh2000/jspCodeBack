package com.lcvc.ebuy.web.admin.adminManage;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.AdminBean;
import com.lcvc.ebuy.model.Admin;

@WebServlet("/admin/adminmanage/EditddAdmin")
public class AdminManageEditServlet extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		Integer userId = Integer.valueOf(request.getParameter("userId"));
		String username = request.getParameter("username");
		String screenName = request.getParameter("screenName");
		String userpass = request.getParameter("userpass");
		
		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setScreenName(screenName);
		admin.setUserpass(userpass);
		admin.setUserId(userId);
		AdminBean adminBean = new AdminBean();
		int status = adminBean.deitAdmin(admin);
		if(status == 1){
			request.setAttribute("message", "操作成功");
		}else if(status == 2){
			request.setAttribute("message", "用户名修改成功！");
		}else{
			request.setAttribute("message", "用户重名！");
		}
		request.getRequestDispatcher("/admin/adminmanage/showAdmins").forward(request, response);
	}
}
