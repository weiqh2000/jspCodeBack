package com.nyl.ebuy.web.admin.customer;


import com.nyl.ebuy.bean.CustomerBean;
import com.nyl.ebuy.model.Customer;
import com.nyl.ebuy.model.other.PageObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * 处理产品列表查询请求
 */
@WebServlet(urlPatterns="/admin/customer/customermanage")
public class CustomerManageServlet extends HttpServlet {
	private CustomerBean customerBean=new CustomerBean();

	public CustomerManageServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String page=request.getParameter("page");//获取当前页码
		PageObject<Customer> pageObject=customerBean.getCustomers(page, 20);
		request.setAttribute("pageObject", pageObject);
		request.getRequestDispatcher("/jsp/admin/customer/customermanage.jsp").forward(request,response);
	}
}
