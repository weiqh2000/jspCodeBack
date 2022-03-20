package com.lcvc.ebuy.web.admin.adminManage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcvc.ebuy.bean.AdminBean;
import com.lcvc.ebuy.model.Admin;

@WebServlet("/admin/adminmanage/myPassword")
public class MyPassword extends HttpServlet {
	// service 可以同时处理get和post请求
	@Override
		public void service(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			if(request.getParameter("oldPassword").trim().equals("")){
				request.setAttribute("message", "旧密码为空！");
				request.getRequestDispatcher("/admin/adminmanage/toMyPassword").forward(request, response);
			}else if(request.getParameter("newPassword").trim().equals("")){
				request.setAttribute("message", "新密码为空！");
				request.getRequestDispatcher("/admin/adminmanage/toMyPassword").forward(request, response);
			}else if(request.getParameter("rePassword").trim().equals("")){
				request.setAttribute("message", "请确认要更改的密码！");
				request.getRequestDispatcher("/admin/adminmanage/toMyPassword").forward(request, response);
			}else if(request.getParameter("rePassword").trim().equals(request.getParameter("newPassword"))){
				AdminBean adminBean=new  AdminBean();
				
				String status = adminBean.changePassword(Integer.valueOf(request.getParameter("userId")), request.getParameter("oldPassword"), request.getParameter("newPassword"));
				if(status == "1"){
					request.setAttribute("message", "修改失败！");
					request.getRequestDispatcher("/admin/adminmanage/toMyPassword").forward(request, response);
				}else if(status == "0"){
					request.setAttribute("message", "修改成功！");
					request.getRequestDispatcher("/admin/adminmanage/toMyPassword").forward(request, response);
				}else if(status == "2"){
					request.setAttribute("message", "输入的旧密码与原密码不相符！");
					request.getRequestDispatcher("/admin/adminmanage/toMyPassword").forward(request, response);
				}
			}
			
			

		}


	}
