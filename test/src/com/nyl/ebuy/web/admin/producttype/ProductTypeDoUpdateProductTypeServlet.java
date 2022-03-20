package com.nyl.ebuy.web.admin.producttype;


import com.nyl.ebuy.bean.ProductTypeBean;
import com.nyl.ebuy.model.ProductType;
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
 * 处理产品分类的编辑请求
 */
@WebServlet(urlPatterns="/admin/producttype/doUpdateProductType")
public class ProductTypeDoUpdateProductTypeServlet extends HttpServlet {

	public ProductTypeDoUpdateProductTypeServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf8"); 
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		ProductTypeBean productTypeBean = new ProductTypeBean();
		
		Map<String,String> errors=new HashMap<String,String>();
		ProductType productType=this.validateForm(request,errors);
		if(errors.size()==0){//说明表单验证通过
			try {
				if(productTypeBean.updateProductType(productType)){
					request.setAttribute("myMessage", "产品类别编辑成功");
					//初始化表单
					request.setAttribute("productType", productTypeBean.getProductType(productType.getId()));
				}else{
					request.setAttribute("myMessage", "产品类别编辑失败");
				}
			} catch (MyFormException e) {
				request.setAttribute("myMessage", e.getMessage());
			}finally{
				request.getRequestDispatcher("/jsp/admin/producttype/producttypeupdate.jsp").forward(request,response);
			}
		}else{
			request.setAttribute("productType", productType);//返回错误的表单数据
			request.setAttribute("errors", errors);//错误集合
			request.getRequestDispatcher("/jsp/admin/producttype/producttypeupdate.jsp").forward(request,response);
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
		String idString=request.getParameter("id");
		try{
			if(idString==null||idString.trim().equals("")){
				errors.put("id", "标识符参数不能为空");
			}else{
				//产品标识符验证
				Integer id=Integer.parseInt(idString);
				productType.setId(id);
			}
		}catch(Exception e){
			errors.put("id", "产品标识符参数非法");
		}
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
