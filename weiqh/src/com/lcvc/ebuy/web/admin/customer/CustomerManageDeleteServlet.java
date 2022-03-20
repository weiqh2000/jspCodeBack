package com.lcvc.ebuy.web.admin.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcvc.ebuy.bean.AdminBean;
import com.lcvc.ebuy.bean.CustomerBean;
import com.lcvc.ebuy.model.Admin;

/*
 * 实现管理员删除的功能
 * @author S6203-1-08
 */
@WebServlet("/admin/customer/customerManageDelete")
public class CustomerManageDeleteServlet extends HttpServlet {
// service 可以同时处理get和post请求
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CustomerBean customerBean = new CustomerBean();
		Integer Id=Integer.valueOf(request.getParameter("Id"));
		if(customerBean.delete(Id) == true){
			request.setAttribute("message", "操作成功！");
		}else{
			request.setAttribute("message", "操作失败！");
		}
		request.getRequestDispatcher("/admin/customer/customerManage").forward(request, response);
	}


}
