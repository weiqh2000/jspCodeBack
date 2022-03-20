package com.lcvc.ebuy.web.admin.admin;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcvc.ebuy.bean.AdminBean;
import com.lcvc.ebuy.model.Admin;
import com.lcvc.ebuy.model.exception.MyFormException;

/*
 * 执行管理账户密码编辑请求
 */
@WebServlet(urlPatterns="/admin/admin/doUpdateMyAdminPassword")
public class AdminDoUpdateAdminPasswordServlet extends HttpServlet {

	public AdminDoUpdateAdminPasswordServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		HttpSession session=request.getSession(); 
		AdminBean adminBean=new AdminBean();
		//接收表单信息
		Integer userId=((Admin)session.getAttribute("admin")).getUserId();
		String password=request.getParameter("password");
		String newPass=request.getParameter("newPass");
		String rePass=request.getParameter("rePass");
		//对表单信息进行验证
		Admin admin=adminBean.login(((Admin)session.getAttribute("admin")).getUsername(), password);
		if(admin!=null){//如果原密码正确
			if(newPass.equals(rePass)){//如果新密码和确认密码一致
				admin.setPassword(newPass);
				try{
					boolean flag = adminBean.updateAdmin(admin);
					if(flag){
						request.setAttribute("myMessage", "密码修改成功");
					}else{
						request.setAttribute("myMessage", "密码修改失败");
					}
				}catch (MyFormException e) {
					request.setAttribute("myMessage", e.getMessage());
				}
			}else{
				request.setAttribute("myMessage", "新密码和确认密码不一致");
			}
		}else{
			request.setAttribute("myMessage", "旧密码错误");
		}
		request.getRequestDispatcher("/jsp/admin/admin/adminupdatepassword.jsp").forward(request,response);
	}
}
