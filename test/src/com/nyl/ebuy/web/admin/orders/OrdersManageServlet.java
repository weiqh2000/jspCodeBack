package com.nyl.ebuy.web.admin.orders;


import com.nyl.ebuy.bean.OrdersBean;
import com.nyl.ebuy.model.Orders;
import com.nyl.ebuy.model.other.FormSelect;
import com.nyl.ebuy.model.other.PageObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/*
 * 处理订单列表查询请求
 */
@WebServlet(urlPatterns="/admin/orders/ordersmanage")
public class OrdersManageServlet extends HttpServlet {

	public OrdersManageServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		//传递付款状态集合
		request.setAttribute("tags", FormSelect.getOrdersTagMap());
		request.setAttribute("payments", FormSelect.getOrdersPaymentMap());
		String page=request.getParameter("page");//获取当前页码
		OrdersBean odersBean=new OrdersBean();
		PageObject<Orders> pageObject;
		try {
			pageObject = odersBean.getOrdersList(page, 20);
			request.setAttribute("pageObject", pageObject);
		} catch (SQLException e) {
			request.setAttribute("myMessage",e.getMessage());
		}
		request.getRequestDispatcher("/jsp/admin/orders/ordersmanage.jsp").forward(request,response);
	}
}
