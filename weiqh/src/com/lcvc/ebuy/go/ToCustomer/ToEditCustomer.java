package com.lcvc.ebuy.go.ToCustomer;
import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.lcvc.ebuy.bean.AdminBean;
import com.lcvc.ebuy.bean.CustomerBean;
import com.lcvc.ebuy.bean.ProductBean;
import com.lcvc.ebuy.bean.ProductTypeBean;
import com.lcvc.ebuy.model.Admin;
import com.lcvc.ebuy.model.Page;
import com.lcvc.ebuy.model.Product;

@WebServlet("/go/toEditCustomer")
public class ToEditCustomer  extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse reponse)
			throws ServletException, IOException {
		Integer Id = Integer.valueOf(request.getParameter("Id"));
		CustomerBean customerBean = new CustomerBean();
		

		request.setAttribute("customer",customerBean.updateCustomer(Id));

		
		request.getRequestDispatcher("/jsp/admin/customer/customerupdate.jsp").forward(request, reponse);
	}
	
}
