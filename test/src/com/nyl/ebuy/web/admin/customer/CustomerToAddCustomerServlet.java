package com.nyl.ebuy.web.admin.customer;


import com.nyl.ebuy.bean.CustomerBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * 处理产品列表查询请求
 */
@WebServlet(urlPatterns="/admin/customer/toAddCustomer")
public class CustomerToAddCustomerServlet extends HttpServlet {
	private CustomerBean customerBean=new CustomerBean();

	public CustomerToAddCustomerServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		//传递当前页码参数，保证返回的时候能返回产品管理的当前页
		request.setAttribute("page", request.getParameter("page"));
		request.getRequestDispatcher("/jsp/admin/customer/customeradd.jsp").forward(request,response);
	}
}
