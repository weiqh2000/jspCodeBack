package com.lcvc.ebuy.web.admin.customer;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.AdminBean;
import com.lcvc.ebuy.bean.CustomerBean;
import com.lcvc.ebuy.model.Admin;
import com.lcvc.ebuy.model.Customer;

@WebServlet("/admin/customer/customerManageEdit")
public class CustomerManageEditServlet extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		Integer Id = Integer.valueOf(request.getParameter("Id"));
		String name = request.getParameter("name");

		
		if(name.trim().equals("")){
			request.setAttribute("message", "账户添加失败：name为空");
			request.getRequestDispatcher("/admin/customer/customerManage").forward(request, response);
		}else {
			Customer customer = new  Customer();
			CustomerBean customerBean = new CustomerBean();
			customer.setId(Id);
			customer.setAddress(request.getParameter("address"));
			customer.setUsername(request.getParameter("username"));
			customer.setName(request.getParameter("name"));
			customer.setPicUrl(request.getParameter("picUrl"));
			customer.setTel(request.getParameter("tel"));
			customer.setZip(request.getParameter("zip"));
			customer.setEmail(request.getParameter("email"));
			customer.setIntro(request.getParameter("intro"));
			if(customerBean.doUpdateCustomer(customer) == true){
				request.setAttribute("message", "账户编辑成功!!!");
				request.getRequestDispatcher("/admin/customer/customerManage").forward(request, response);
			}else{
				request.setAttribute("message", "账户编辑失败!!!");
				request.getRequestDispatcher("/admin/customer/customerManage").forward(request, response);
			}
			
			
		}
	}
}
