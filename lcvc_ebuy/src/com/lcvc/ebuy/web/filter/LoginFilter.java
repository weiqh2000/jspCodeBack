package com.lcvc.ebuy.web.filter;
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

import com.lcvc.ebuy.bean.AdminBean;
import com.lcvc.ebuy.model.Admin;

public class LoginFilter implements Filter{
	private AdminBean adminBean=null;
	private static final String[] IGNORE_URI = { "admin/login"};//定义不拦截的资源

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		//获取请求的URL
		boolean flag = true;//true表示需要验证
		String url = request.getRequestURL().toString();
		for (String s : IGNORE_URI) {
			if (url.contains(s)) {
				flag = false;
				break;
			}
		}		
		if(flag){
			HttpSession session=request.getSession(); 
			if(session.getAttribute("admin")!=null){
				Admin admin=(Admin)session.getAttribute("admin");
				admin=adminBean.getAdmin(admin.getUserId());//数据库判断是否该账户还存在
				if(admin!=null){
					session.setAttribute("admin", admin);
					chain.doFilter(request, response);
				}else{
					//request.setAttribute("myMessage", "该账户已经被删除,请切换账户登录");
					response.sendRedirect(basePath+"jsp/admin/login.jsp");
				}
			}else{
				//request.setAttribute("myMessage", "请重新登录");
				response.sendRedirect(basePath+"jsp/admin/login.jsp");
			}
		}else{//如果不需要验证
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void destroy() {
		adminBean=null;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		adminBean=new AdminBean();
		
	}
}
