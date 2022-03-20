package com.lcvc.ebuy.web.admin.producttype;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcvc.ebuy.bean.ProductTypeBean;
import com.lcvc.ebuy.model.ProductType;
import com.lcvc.ebuy.model.exception.MyFormException;

/*
 * 处理产品分类的添加请求
 */
@WebServlet(urlPatterns="/admin/producttype/doAddProductType")
public class ProductTypeDoAddProductTypeServlet extends HttpServlet {

	public ProductTypeDoAddProductTypeServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf8"); 
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		HttpSession session = request.getSession();
		
		//对表单的参数进行验证和封装
		//错误信息类
		Map<String,String> errors=new HashMap<String,String>();
		ProductType productType=this.validateForm(request,errors);
		if(errors.size()==0){//说明表单验证成功
			ProductTypeBean productTypeBean = new ProductTypeBean();
			try {
				Integer id = productTypeBean.addProductType(productType);
				if(id!=null){
					request.setAttribute("myMessage", "产品类别添加成功");
					//初始化表单
					request.setAttribute("productType", productTypeBean.getProductTypeInit());
				}else{
					request.setAttribute("myMessage", "产品类别添加失败");
				}
			} catch (MyFormException e) {
				request.setAttribute("myMessage", e.getMessage());
			} finally{
				request.getRequestDispatcher("/jsp/admin/producttype/producttypeadd.jsp").forward(request,response);
			}
		}else{
			request.setAttribute("productType", productType);//返回错误的表单数据
			request.setAttribute("errors", errors);//错误集合
			request.getRequestDispatcher("/jsp/admin/producttype/producttypeadd.jsp").forward(request,response);
		}
	}
	
	/*
	 * 针对产品类别添加请求进行验证并对部分属性进行赋值
	 * @param request
	 * @param errors 错误信息集合
	 * @return
	 */
	private ProductType validateForm(HttpServletRequest request,Map<String,String> errors){
		//对表单的参数进行封装
		ProductType productType=new ProductType();
		//名字验证
		String name=request.getParameter("name");
		if(name==null||name.trim().equals("")){
			errors.put("name","名字不能为空");
		}else{
			if(name.length()<1||name.length()>30){
				errors.put("name","名称长度不符合要求");
			}else{
				productType.setName(name);
			}
		}
		//图片验证
		String imageUrl=request.getParameter("imageUrl");
		if(imageUrl==null||imageUrl.trim().equals("")){
			errors.put("imageUrl","必须上传图片");
		}else{
			if(imageUrl.length()>255){
				errors.put("imageUrl","图片地址过长");
			}else{
				productType.setImageUrl(imageUrl);
			}
		}		
		//优先级验证
		String orderNumString=request.getParameter("orderNum");
		if(orderNumString==null||orderNumString.trim().equals("")){
			errors.put("orderNum","优先级不能为空");
		}else{
			try{
				Integer orderNum=Integer.parseInt(orderNumString);
				if(orderNum>=0){
					productType.setOrderNum(orderNum);
				}else{
					errors.put("orderNum","优先级不能为负数");
				}
			}catch(Exception e){
				errors.put("orderNum","优先级必须是整数");
			}
		}
		//简介验证
		String intro=request.getParameter("intro");
		productType.setIntro(intro);
		//外部链接验证
		String linkUrl=request.getParameter("linkUrl");
		productType.setLinkUrl(linkUrl);
		if(linkUrl!=null&&!linkUrl.trim().equals("")){
			if(linkUrl.length()>255){
				errors.put("linkUrl","图片地址过长");
			}
		}	
		return productType;
	}
}
