package com.lcvc.ebuy.go.ToCustomer;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcvc.ebuy.bean.AdminBean;
import com.lcvc.ebuy.bean.CustomerBean;
import com.lcvc.ebuy.bean.ProductTypeBean;
import com.lcvc.ebuy.model.Admin;
import com.lcvc.ebuy.model.ProductType;
@WebServlet("/go/toAddCustomer")
public class ToAddCustomer extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		request.getRequestDispatcher("/jsp/admin/customer/customeradd.jsp").forward(request, response);
	}
}
