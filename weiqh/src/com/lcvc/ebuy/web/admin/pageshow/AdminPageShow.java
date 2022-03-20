package com.lcvc.ebuy.web.admin.pageshow;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lcvc.ebuy.bean.PageBean;
import com.lcvc.ebuy.model.Admin;
import com.lcvc.ebuy.model.Page;

@WebServlet("/admin/pageshow/adminPageShow")
public class AdminPageShow extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
				//session传递管理员列表到界面
				PageBean pageBean = new PageBean();
				Page page1 = new Page();
				List<Admin> admins = pageBean.pageAdmins(page1);
				Page page = pageBean.page(page1);
				request.setAttribute("admins", admins);
				request.setAttribute("page", page);
				request.getRequestDispatcher("/jsp/admin/adminmanage/adminPage.jsp").forward(request,response);
				
	}
}
