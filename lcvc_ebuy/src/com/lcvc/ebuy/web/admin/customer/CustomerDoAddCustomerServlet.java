package com.lcvc.ebuy.web.admin.customer;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.CustomerBean;
import com.lcvc.ebuy.model.Customer;
import com.lcvc.ebuy.model.exception.MyFormException;

/*
 * 处理产品列表查询请求
 */
@WebServlet(urlPatterns="/admin/customer/doAddCustomer")
public class CustomerDoAddCustomerServlet extends HttpServlet {
	private CustomerBean customerBean=new CustomerBean();

	public CustomerDoAddCustomerServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf8"); 
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		//传递当前页码参数，保证返回的时候能返回产品管理的当前页
		request.setAttribute("page", request.getParameter("page"));
		//错误信息类
		Map<String,String> errors=new HashMap<String,String>();
		Customer customer=validateForm(request, errors);
		if(errors.size()==0){//说明表单验证成功
			try {
				Integer id = customerBean.addCustomer(customer);//获取保存后的标识符
				if(id!=null){
					request.setAttribute("myMessage", "账户添加成功");
					//初始化表单
					request.setAttribute("product", null);
				}else{
					request.setAttribute("myMessage", "账户添加失败");
				}
			} catch (MyFormException e) {
				request.setAttribute("myMessage", e.getMessage());
			} finally{
				request.getRequestDispatcher("/jsp/admin/customer/customeradd.jsp").forward(request,response);
			}
		}else{
			request.setAttribute("customer", customer);//返回错误的表单数据
			request.setAttribute("errors", errors);//错误集合
			request.getRequestDispatcher("/jsp/admin/customer/customeradd.jsp").forward(request,response);
		}
	}
	
	/*
	 * 针对客户添加请求进行验证并对部分属性进行赋值
	 * @param request
	 * @param errors 错误信息集合
	 * @return 
	 */
	private Customer validateForm(HttpServletRequest request,Map<String,String> errors){
		Customer customer=new Customer();
		//用户名验证
		String username=request.getParameter("username");
		if(username==null||username.trim().equals("")){
			errors.put("username","用户名不能为空");
		}else{
			if(username.length()<2||username.length()>20){
				errors.put("username","用户名长度不符合要求");
			}else{
				if(customerBean.getCountByUsername(username)>0){//如果用户名不冲突
					errors.put("username","该用户名已存在");
				}
				customer.setUsername(username);//如果冲突也保留冲突的用户名到前端
			}
		}				
		//客户名验证
		String name=request.getParameter("name");
		if(name==null||name.trim().equals("")){
			errors.put("name","名字不能为空");
		}else{
			if(name.length()<2||name.length()>30){
				errors.put("name","名字长度不符合要求");
			}else{
				customer.setName(name);
			}
		}	
		//头像验证
		String picUrl=request.getParameter("picUrl");
		if(picUrl!=null&&!picUrl.trim().equals("")){
			if(picUrl.length()>255){
				errors.put("picUrl","图片的网址超出规定的255字符长度");
			}else{
				customer.setPicUrl(picUrl);
			}
		}
		//电话验证
		String tel=request.getParameter("tel");
		if(tel!=null&&!tel.trim().equals("")){
			if(tel.length()>20){
				errors.put("tel","电话的长度不能超过20字符");
			}else{
				customer.setTel(tel);
			}
		}	
		//地址验证
		String address=request.getParameter("address");
		if(address!=null&&!address.trim().equals("")){
			if(address.length()>100){
				errors.put("tel","地址的长度不能超过100字符");
			}else{
				customer.setAddress(address);
			}
		}	
		//邮编验证
		String zip=request.getParameter("zip");
		if(zip!=null&&!zip.trim().equals("")){
			if(zip.length()>10){
				errors.put("tel","邮编的长度不能超过10字符");
			}else{
				customer.setZip(zip);
			}
		}	
		//邮箱验证
		String email=request.getParameter("email");
		if(email!=null&&!email.trim().equals("")){
			if(email.length()>30){
				errors.put("tel","邮编的长度不能超过30字符");
			}else{
				customer.setEmail(email);
			}
		}	
		//简介验证
		String intro=request.getParameter("intro");
		customer.setIntro(intro);
		return customer;
	}
}
