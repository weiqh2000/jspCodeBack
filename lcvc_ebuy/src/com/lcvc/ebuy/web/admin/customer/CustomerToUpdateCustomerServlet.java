package com.lcvc.ebuy.web.admin.customer;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.CustomerBean;
import com.lcvc.ebuy.model.Customer;

/*
 * 处理产品列表查询请求
 */
@WebServlet(urlPatterns="/admin/customer/toUpdateCustomer")
public class CustomerToUpdateCustomerServlet extends HttpServlet {
	private CustomerBean customerBean=new CustomerBean();

	public CustomerToUpdateCustomerServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		//传递当前页码参数，保证返回的时候能返回产品管理的当前页
		request.setAttribute("page", request.getParameter("page"));
		String id=request.getParameter("id");
		Customer customer=customerBean.getCustomer(id);
		if(customer!=null){
			request.setAttribute("customer", customer);
		}else{
			request.setAttribute("myMessage", "记录读取失败");
		}
		request.getRequestDispatcher("/jsp/admin/customer/customerupdate.jsp").forward(request,response);
	}
}
