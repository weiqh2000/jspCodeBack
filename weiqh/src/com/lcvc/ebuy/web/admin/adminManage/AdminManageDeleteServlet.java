package com.lcvc.ebuy.web.admin.adminManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcvc.ebuy.bean.AdminBean;
import com.lcvc.ebuy.model.Admin;

/*
 * 实现管理员删除的功能
 * @author S6203-1-08
 */
@WebServlet("/admin/adminmanage/deleteAdmin")
public class AdminManageDeleteServlet extends HttpServlet {
// service 可以同时处理get和post请求
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        //session传递管理员列表到页面
		AdminBean adminBean=new  AdminBean();
		//执行删除
		//Integer.valueOf表示将字符串转换成为整型
		Integer userId=Integer.valueOf(request.getParameter("userId"));
	    HttpSession session = request.getSession();
	    Admin admin = (Admin)session.getAttribute("admin");
	    if(admin != null){//如果登陆账户存在/有效
	    	if(admin.getUserId() != userId.intValue()){//如果要删除的账户不是当前的登陆账户
	    		adminBean.deleteAamin(userId);//执行删除
	    	}else{//如果要删除的是当前登陆账户
	    		request.setAttribute("message", "操作失败：不允许删除自己的账户");
	    	}
	    }else{//如果没有登陆
	    	request.setAttribute("message", "操作失败：请先登陆");
	    }
		request.getRequestDispatcher("/admin/adminmanage/showAdmins").forward(request, response);
	}


}
