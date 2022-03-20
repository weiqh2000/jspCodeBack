package com.lcvc.ebuy.web.admin.product;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcvc.ebuy.bean.AdminBean;
import com.lcvc.ebuy.bean.ProductBean;
import com.lcvc.ebuy.bean.ProductTypeBean;
import com.lcvc.ebuy.model.Admin;
import com.lcvc.ebuy.model.Product;
import com.lcvc.ebuy.model.ProductType;
import com.lcvc.ebuy.model.exception.MyFormException;

/*
 * 处理产品的添加请求
 */
@WebServlet(urlPatterns="/admin/product/doAddProduct")
public class ProductDoAddProductServlet extends HttpServlet {
	private ProductTypeBean productTypeBean=new ProductTypeBean();
	private ProductBean productBean=new ProductBean();
	private AdminBean adminBean=new AdminBean();

	public ProductDoAddProductServlet() {
		super();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf8"); 
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		HttpSession session=request.getSession(); 
		//传递当前页码参数，保证返回的时候能返回产品管理的当前页
		request.setAttribute("page", request.getParameter("page"));
		//传递产品栏目集合
		request.setAttribute("productTypes", productTypeBean.getProductTypes());
		//对表单的参数进行验证和封装
		//错误信息类
		Map<String,String> errors=new HashMap<String,String>();
		Product product=this.validateForm(request,errors);
		if(errors.size()==0){//说明表单验证成功
			try {
				Integer id = productBean.addProduct(product,(Admin)session.getAttribute("admin"));//获取保存后的标识符
				if(id!=null){
					request.setAttribute("myMessage", "产品添加成功");
					//初始化表单
					request.setAttribute("product", productBean.getProductInit());
				}else{
					request.setAttribute("myMessage", "产品添加失败");
				}
			} catch (MyFormException e) {
				request.setAttribute("myMessage", e.getMessage());
			} finally{
				request.getRequestDispatcher("/jsp/admin/product/productadd.jsp").forward(request,response);
			}
		}else{
			request.setAttribute("product", product);//返回错误的表单数据
			request.setAttribute("errors", errors);//错误集合
			request.getRequestDispatcher("/jsp/admin/product/productadd.jsp").forward(request,response);
		}
	}
	
	/*
	 * 针对产品添加请求进行验证并对部分属性进行赋值
	 * @param request
	 * @param errors 错误信息集合
	 * @return
	 */
	private Product validateForm(HttpServletRequest request,Map<String,String> errors){
		//对表单的参数进行封装
		Product product=new Product();
		//产品分类验证
		String productTypeIdString=request.getParameter("productTypeId");
		if(productTypeIdString==null||productTypeIdString.trim().equals("")){
			errors.put("productTypeId","必须选择产品分类");
		}else{
			try{
				Integer productTypeId=Integer.parseInt(productTypeIdString);
				ProductType productType=productTypeBean.getProductType(productTypeId);
				if(productType!=null){
					product.setProductType(productTypeBean.getProductType(productTypeId));
				}else{
					errors.put("productTypeId","该产品栏目已经被删除，请重新选择");
				}
			}catch(Exception e){
				errors.put("productTypeId","产品栏目标识符参数非法");
			}
		}
		//产品名验证
		String name=request.getParameter("name");
		if(name==null||name.trim().equals("")){
			errors.put("name","产品名称不能为空");
		}else{
			if(name.length()<2||name.length()>50){
				errors.put("name","产品名称长度不符合要求");
			}else{
				product.setName(name);
			}
		}
		//产品图片验证
		String picUrl=request.getParameter("picUrl");
		if(picUrl==null||picUrl.trim().equals("")){
			errors.put("picUrl","必须上传产品图片");
		}else{
			if(picUrl.length()>255){
				errors.put("picUrl","产品图片的网址超出规定的255字符长度");
			}else{
				product.setPicUrl(picUrl);
			}
		}
		//产品原价验证
		String originalPriceString=request.getParameter("originalPrice");
		if(originalPriceString==null||originalPriceString.trim().equals("")){
			errors.put("originalPrice","原价不能为空");
		}else{
			try {
				Float originalPrice=Float.valueOf(originalPriceString);
				if(originalPrice>0){
					product.setOriginalPrice(originalPrice);
				}else{
					errors.put("originalPrice","原价必须大于0");
				}
			} catch (NumberFormatException e) {
				errors.put("originalPrice","原价必须是浮点数");
			}
		}
		//产品现价验证
		String priceString=request.getParameter("price");
		if(priceString==null||priceString.trim().equals("")){
			errors.put("price","现价不能为空");
		}else{
			try {
				Float price=Float.valueOf(priceString);
				if(price>0){
					product.setPrice(price);
				}else{
					errors.put("price","现价必须大于0");
				}
			} catch (NumberFormatException e) {
				errors.put("price","现价必须是浮点数");
			}
		}
		//产品库存验证
		String numberString=request.getParameter("number");
		if(numberString==null||numberString.trim().equals("")){
			errors.put("number","产品库存不能为空");
		}else{
			try{
				Integer number=Integer.parseInt(numberString);
				if(number>0){
					product.setNumber(number);
				}else{
					errors.put("number","库存必须大于0");
				}
			}catch(Exception e){
				errors.put("number","库存必须是整数");
			}
		}
		//产品优先级验证
		String orderNumString=request.getParameter("orderNum");
		if(orderNumString==null||orderNumString.trim().equals("")){
			errors.put("orderNum","优先级不能为空");
		}else{
			try{
				Integer orderNum=Integer.parseInt(orderNumString);
				if(orderNum>=0){
					product.setOrderNum(orderNum);
				}else{
					errors.put("orderNum","优先级不能为负数");
				}
			}catch(Exception e){
				errors.put("orderNum","优先级必须是整数");
			}
		}
		//产品点击数验证
		String clickString=request.getParameter("click");
		if(clickString==null||clickString.trim().equals("")){
			errors.put("click","产品点击数不能为空");
		}else{
			try{
				Integer click=Integer.parseInt(clickString);
				if(click>=0){
					product.setClick(click);
				}else{
					errors.put("click","产品点击数不能为负数");
				}
			}catch(Exception e){
				errors.put("click","产品点击数必须是整数");
			}
		}
		//产品点击数验证
		String onSaleString=request.getParameter("onSale");
		if(onSaleString==null||onSaleString.trim().equals("")){
			errors.put("click","是否上架不能为空");
		}else{
			try{
				Boolean onSale=Boolean.valueOf(onSaleString);
				product.setOnSale(onSale);
			}catch(Exception e){
				errors.put("click","是否上架的参数非法");
			}
		}
		//产品描述验证
		String description=request.getParameter("description");
		if(description==null||description.trim().equals("")){
			errors.put("description","产品描述不能为空");
		}else{
			product.setDescription(description);
		}
		//产品内容验证
		String content=request.getParameter("content");
		if(content==null||content.trim().equals("")){
			errors.put("content","产品内容不能为空");
		}else{
			product.setContent(content);
		}
		return product;
	}
}
