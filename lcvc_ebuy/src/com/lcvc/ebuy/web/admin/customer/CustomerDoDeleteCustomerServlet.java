package com.lcvc.ebuy.web.admin.customer;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.CustomerBean;
import com.lcvc.ebuy.model.Customer;
import com.lcvc.ebuy.model.exception.MyFormException;
import com.lcvc.ebuy.model.other.PageObject;

/*
 * 处理产品列表查询请求
 */
@WebServlet(urlPatterns="/admin/customer/doDeleteCustomer")
public class CustomerDoDeleteCustomerServlet extends HttpServlet {
	private CustomerBean customerBean=new CustomerBean();

	public CustomerDoDeleteCustomerServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String id=request.getParameter("id");
		try {
			boolean flag=customerBean.deleteCustomer(id);
			if(!flag){
				request.setAttribute("myMessage", "产品删除失败");
			}
		} catch (MyFormException e) {
			request.setAttribute("myMessage", e.getMessage());
		}
		String page=request.getParameter("page");//获取当前页码
		PageObject<Customer> pageObject=customerBean.getCustomers(page, 20);
		request.setAttribute("pageObject", pageObject);
		request.getRequestDispatcher("/jsp/admin/customer/customermanage.jsp").forward(request,response);
	}
}
