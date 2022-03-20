package com.lcvc.ebuy.web.shop.cart;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcvc.ebuy.bean.ShoppingCartBean;
import com.lcvc.ebuy.model.ShoppingCart;
import com.lcvc.ebuy.model.exception.MyFormException;

/*
 * 将产品放入购物车
 */
@WebServlet(urlPatterns="/shop/cart/doPutCart")
public class CartDoputCartServlet extends HttpServlet {
	private ShoppingCartBean shoppingCartBean=new ShoppingCartBean();

	public CartDoputCartServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String productId=request.getParameter("productId");
		String number=request.getParameter("number");
		HttpSession session=request.getSession(); 
		ShoppingCart shoppingCart=null;
		if(session.getAttribute("shoppingCart")!=null){
			shoppingCart=(ShoppingCart)session.getAttribute("shoppingCart");
		}
		try {
			shoppingCart=shoppingCartBean.addShoppingCart(shoppingCart, productId, number);
			session.setAttribute("shoppingCart", shoppingCart);
		} catch (MyFormException e) {
			request.setAttribute("myMessage", e.getMessage());
		}
		request.getRequestDispatcher("/jsp/shop/cart/shopcart.jsp").forward(request,response);
		
	}
}
