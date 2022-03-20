package com.nyl.ebuy.web.admin.adminmanage;


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
 * 处理管理员账户的编辑请求
 */
@WebServlet(urlPatterns="/admin/adminmanage/doUpdateAdmin")
public class AdminManageDoUpdateAdminServlet extends HttpServlet {

	public AdminManageDoUpdateAdminServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		AdminBean adminBean=new AdminBean();
		//对表单的参数进行封装
		String userId=request.getParameter("userId");
		String username=request.getParameter("username");
		String screenName=request.getParameter("screenName");
		Integer id=Integer.parseInt(userId);
		HttpSession session=request.getSession(); 
		Admin admin=new Admin();
		admin.setUserId(id);
		admin.setUsername(username);
		admin.setScreenName(screenName);
		request.setAttribute("admin",admin);//先预存原来表单的错误数据，供返回时调用
		if(((Admin)session.getAttribute("admin")).getUserId()!=id.intValue()){//管理账户不能自己编辑自己
			boolean flag;
			try {
				flag = adminBean.updateAdmin(admin);
				if(flag){
					request.setAttribute("myMessage", "账户编辑成功");
					request.setAttribute("admin",adminBean.getAdmin(userId));
				}else{
					request.setAttribute("myMessage", "账户编辑失败");
				}
			} catch (MyFormException e) {
				request.setAttribute("myMessage", e.getMessage());
			}finally{
				request.getRequestDispatcher("/jsp/admin/adminmanage/adminupdate.jsp").forward(request,response);
			}
		}else{
			request.setAttribute("myMessage", "操作失败：不能编辑自己的账户");
			request.getRequestDispatcher("/jsp/admin/adminmanage/adminupdate.jsp").forward(request,response);
		}
	}
}
