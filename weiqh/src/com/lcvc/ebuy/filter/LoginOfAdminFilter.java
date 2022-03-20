package com.lcvc.ebuy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcvc.ebuy.model.Admin;

/**
 * 管理员登录的拦截器，用于拦截需要管理员登录才能访问的Servlet
 * @author S6203-1-08
 *
 */
public class LoginOfAdminFilter implements Filter{


	/**
	 * 过滤的业务类
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse) res;
		String url = request.getRequestURL().toString();//获取请求的url
		if(url.contains("/admin/login")){//如果该请求可以过滤
			chain.doFilter(request, response);
		}else{
			HttpSession session=request.getSession();
			Admin admin=(Admin)session.getAttribute("admin");//获取当前登录账户的信息
			if(admin!=null){//如果登录账户存在/有效
				chain.doFilter(request, response);//将请求传递到下一个过滤器或者要访问的资源（如Servlet、jsp、html……）
			}else{//如果没有登录
				String path = request.getContextPath();
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
				response.sendRedirect(basePath+"jsp/admin/login.jsp");// 跳转到登录成功页面
			}
		}
	}

	/**
	 * 过滤器初始化的时候调用
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("登录过滤器初始化");
	}
	
	/**
	 * 过滤器摧毁的时候，生命周期结束的时候
	 */
	@Override
	public void destroy() {
		System.out.println("登录过滤器被摧毁");
	}

}
