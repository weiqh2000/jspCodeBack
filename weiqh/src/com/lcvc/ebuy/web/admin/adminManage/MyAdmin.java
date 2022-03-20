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

@WebServlet("/admin/adminmanage/myAdmin")
public class MyAdmin extends HttpServlet {
	// service 可以同时处理get和post请求
	@Override
		public void service(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		int Id = Integer.valueOf(request.getParameter("userId"));
			if(request.getParameter("username").trim().equals("")){
				
				request.setAttribute("message", "用户名为空！");
				request.getRequestDispatcher("/admin/adminmanage/toMyAdmin?Id="+Id).forward(request, response);
				
			}else if(request.getParameter("screenName").trim().equals("")){
				
				request.setAttribute("message", "网名为空！");
				request.getRequestDispatcher("/admin/adminmanage/toMyAdmin?Id="+Id).forward(request, response);
				
			}else{
				AdminBean adminBean=new  AdminBean();
				Admin admin=new Admin();
				admin.setUserId(Integer.valueOf(request.getParameter("userId")));
				admin.setUsername(request.getParameter("username"));
				admin.setScreenName(request.getParameter("screenName"));
				

				int status = adminBean.deitMyadmin(admin);
				if(status == 2){
					request.setAttribute("message", "修改失败！");
					request.getRequestDispatcher("/admin/adminmanage/toMyAdmin?Id="+Id).forward(request, response);
				}else if(status == 0){
					request.setAttribute("message", "修改成功！");
					request.getRequestDispatcher("/admin/adminmanage/toMyAdmin?Id="+Id).forward(request, response);
				}else if(status == 1){
					request.setAttribute("message", "用户名重名！");
					request.getRequestDispatcher("/admin/adminmanage/toMyAdmin?Id="+Id).forward(request, response);
				}
				
			}
			
			
		}


	}
