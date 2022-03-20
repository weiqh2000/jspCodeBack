package com.nyl.ebuy.web.shop.order;


import com.nyl.ebuy.bean.OrdersBean;
import com.nyl.ebuy.bean.ProductBean;
import com.nyl.ebuy.bean.ProductTypeBean;
import com.nyl.ebuy.model.Customer;
import com.nyl.ebuy.model.Orders;
import com.nyl.ebuy.model.ShoppingCart;
import com.nyl.ebuy.model.exception.MyFormException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/*
 * 提交订单
 */
@WebServlet(urlPatterns="/shop/order/doSubmitOrders")
public class OrdersDoSubmitServlet extends HttpServlet {
	private ProductTypeBean productTypeBean = new ProductTypeBean();
	private ProductBean productBean = new ProductBean();
	private OrdersBean ordersBean=new OrdersBean();

	public OrdersDoSubmitServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		HttpSession session=request.getSession(); 
		Customer customer=(Customer)session.getAttribute("customer");
		Orders orders=new Orders();
		orders.setSendName(customer.getName());
		orders.setSendAddress(customer.getAddress());
		orders.setSendZip(customer.getZip());
		orders.setSendTel(customer.getTel());
		try {
			ordersBean.saveOrders(orders,Integer.parseInt(request.getParameter("tag")) ,(ShoppingCart)session.getAttribute("shoppingCart"), customer);
			//清空购物车
			session.removeAttribute("shoppingCart");
		} catch (NumberFormatException e) {
			request.setAttribute("myMessage", e.getMessage());
		} catch (MyFormException e) {
			request.setAttribute("myMessage", e.getMessage());
		} catch (SQLException e) {
			request.setAttribute("myMessage", e.getMessage());
		}
		request.getRequestDispatcher("/jsp/shop/order/order.jsp").forward(request,response);
	}
}
