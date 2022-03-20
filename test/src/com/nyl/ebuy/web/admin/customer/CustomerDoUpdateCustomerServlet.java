package com.nyl.ebuy.web.admin.customer;


import com.nyl.ebuy.bean.CustomerBean;
import com.nyl.ebuy.model.Customer;
import com.nyl.ebuy.model.exception.MyFormException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
 * 处理产品列表查询请求
 */
@WebServlet(urlPatterns="/admin/customer/doUpdateCustomer")
public class CustomerDoUpdateCustomerServlet extends HttpServlet {
	private CustomerBean customerBean=new CustomerBean();

	public CustomerDoUpdateCustomerServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf8"); 
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		//传递当前页码参数，保证返回的时候能返回产品管理的当前页
		request.setAttribute("page", request.getParameter("page"));
		//对表单的参数进行验证和封装
		Map<String,String> errors=new HashMap<String,String>();
		Customer customer=this.validateForm(request,errors);
		if(errors.size()==0){//说明表单验证失败
			try {
				if(customerBean.updateCustomer(customer)){
					request.setAttribute("myMessage", "账户编辑成功");
					//初始化表单
					request.setAttribute("customer", customerBean.getCustomer(customer.getId()));
				}else{
					request.setAttribute("myMessage", "账户编辑失败");
				}
			} catch (MyFormException e) {
				request.setAttribute("myMessage", e.getMessage());
			}finally{
				request.getRequestDispatcher("/jsp/admin/customer/customerupdate.jsp").forward(request,response);
			}
		}else{
			request.setAttribute("customer", customer);//返回错误的表单数据
			request.setAttribute("errors", errors);//错误集合
			request.getRequestDispatcher("/jsp/admin/customer/customerupdate.jsp").forward(request,response);
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
		//标识符验证
		String idString=request.getParameter("id");
		try{
			if(idString==null||idString.trim().equals("")){
				errors.put("id", "产品参数不能为空");
			}else{
				customer.setId(Integer.parseInt(idString));
			}
		}catch(Exception e){
			errors.put("id", "产品标识符参数非法");
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
