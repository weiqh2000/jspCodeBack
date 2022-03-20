package com.nyl.ebuy.web.admin.admin;


import com.nyl.ebuy.bean.AdminBean;
import com.nyl.ebuy.model.Admin;
import com.nyl.ebuy.model.exception.MyFormException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
 * 执行管理账户个人信息编辑请求
 */
@WebServlet(urlPatterns="/admin/admin/doUpdateMyAdmin")
public class AdminDoUpdateAdminServlet extends HttpServlet {

	public AdminDoUpdateAdminServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		HttpSession session=request.getSession(); 
		AdminBean adminBean=new AdminBean();
		//对表单的参数进行封装
		Integer userId=((Admin)session.getAttribute("admin")).getUserId();
		String username=request.getParameter("username");
		String screenName=request.getParameter("screenName");
		Admin admin=new Admin();
		admin.setUserId(userId);
		admin.setUsername(username);
		admin.setScreenName(screenName);
		request.setAttribute("admin",admin);//先预存原来表单的错误数据，供返回时调用
		try {
			boolean flag = adminBean.updateAdmin(admin);
			if(flag){
				request.setAttribute("myMessage", "编辑成功");
				request.setAttribute("admin",adminBean.getAdmin(userId));
			}else{
				request.setAttribute("myMessage", "编辑失败");
			}
		} catch (MyFormException e) {
			request.setAttribute("myMessage", e.getMessage());
		}finally{
			request.getRequestDispatcher("/jsp/admin/admin/adminupdate.jsp").forward(request,response);
		}
	}
}
