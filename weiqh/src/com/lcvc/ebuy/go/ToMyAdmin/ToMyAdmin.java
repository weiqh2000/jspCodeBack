package com.lcvc.ebuy.go.ToMyAdmin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.AdminBean;

@WebServlet("/admin/adminmanage/toMyAdmin")
public class ToMyAdmin extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminBean adminBean = new AdminBean();
		
		request.setAttribute("adminBean",  adminBean.getAdmin(Integer.valueOf(request.getParameter("Id"))));
		
		request.getRequestDispatcher("/jsp/admin/adminmanage/myAdmin.jsp").forward(request, response);
	}
}