package com.nyl.ebuy.web.filter;
import com.nyl.ebuy.bean.CustomerBean;
import com.nyl.ebuy.model.Customer;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CustomerLoginFilter implements Filter{
	private CustomerBean customerBean=null;

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		HttpSession session=request.getSession(); 
		if(session.getAttribute("customer")!=null){
			Customer customer=(Customer)session.getAttribute("customer");
			customer=customerBean.getCustomer(customer.getId());//数据库判断是否该账户还存在
			if(customer!=null){
				session.setAttribute("customer", customer);
				chain.doFilter(request, response);
			}else{
				//request.setAttribute("myMessage", "该账户已经被删除,请切换账户登录");
				response.sendRedirect(basePath+"jsp/shop/signin.jsp");
			}
		}else{
			//request.setAttribute("myMessage", "请重新登录");
			response.sendRedirect(basePath+"jsp/shop/signin.jsp");
		}
		
	}

	@Override
	public void destroy() {
		customerBean=null;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		customerBean=new CustomerBean();
		
	}
}
