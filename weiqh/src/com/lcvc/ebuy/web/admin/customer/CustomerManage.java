package com.lcvc.ebuy.web.admin.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.CustomerBean;
import com.lcvc.ebuy.model.Customer;

@WebServlet("/admin/customer/customerManage")
public class CustomerManage extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CustomerBean customerBean = new CustomerBean();
		Customer customer = new  Customer();
		
		request.setAttribute("list", customerBean.getCustomers());
		request.getRequestDispatcher("/jsp/admin/customer/customermanage.jsp").forward(request, response);
	}
}