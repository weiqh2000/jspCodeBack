package com.lcvc.ebuy.web.admin.adminmanage;


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
 * 处理管理员账户的编辑请求
 */
@WebServlet(urlPatterns="/admin/adminmanage/doAddAdmin")
public class AdminManageDoAddAdminServlet extends HttpServlet {

	public AdminManageDoAddAdminServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		AdminBean adminBean=new AdminBean();
		//对表单的参数进行封装
		String username=request.getParameter("username");
		String screenName=request.getParameter("screenName");
		HttpSession session=request.getSession(); 
		Admin admin=new Admin();
		admin.setUsername(username);
		admin.setScreenName(screenName);
		request.setAttribute("admin",admin);//先预存原来表单的错误数据，供返回时调用
		try {
			Integer userId = adminBean.addAdmin(admin);//获取保存后的标识符
			if(userId!=null){
				request.setAttribute("myMessage", "账户添加成功");
				request.removeAttribute("admin");
			}else{
				request.setAttribute("myMessage", "账户添加失败");
			}
		} catch (MyFormException e) {
			request.setAttribute("myMessage", e.getMessage());
		}finally{
			request.getRequestDispatcher("/jsp/admin/adminmanage/adminadd.jsp").forward(request,response);
		}
	}
}
