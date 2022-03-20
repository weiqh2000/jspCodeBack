package com.lcvc.ebuy.go.ToAdmin;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lcvc.ebuy.bean.AdminBean;
import com.lcvc.ebuy.model.Admin;

@WebServlet("/admin/adminmanage/toEditddAdmin")
public class ToEditAdmin extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse reponse)
			throws ServletException, IOException {
		Integer userId = Integer.valueOf(request.getParameter("userId"));
		AdminBean adminBean = new AdminBean();
		Admin admin = adminBean.getAdmin(userId);
		request.setAttribute("admin", admin);
		request.getRequestDispatcher("/jsp/admin/adminmanage/adminEdit.jsp").forward(request, reponse);
	}
}
