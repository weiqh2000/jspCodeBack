package com.nyl.ebuy.web.shop.order;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * 跳转到订单页面
 */
@WebServlet(urlPatterns="/shop/order/showOrder")
public class OrdersServlet extends HttpServlet {

	public OrdersServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		request.getRequestDispatcher("/jsp/shop/order/order.jsp").forward(request,response);
		
	}
}
