package com.lcvc.ebuy.model;

import java.util.ArrayList;
import java.util.List;

import com.lcvc.ebuy.bean.AdminBean;
import com.lcvc.ebuy.bean.OrdersBean;
import com.lcvc.ebuy.bean.ProductTypeBean;

public class Snippet {
	/*
		 * 分页获取指定栏目的产品的记录,针对前台设计
		 * @param page 要读取的页码
		 * @param pageSize 每页的记录数，必须>0
		 * @param productTypeId 指定栏目的标识符
		 * @return PageObject分页专用类
		 */
		public PageObject getProductsForFrontdesk(Object page, final int pageSize,Object productTypeId){	
			PageObject<Product> pageObject=null;
			Integer id = null;
			if (productTypeId != null) {
				try {
					String s = productTypeId.toString();
					id = Integer.parseInt(s);
				} catch (Exception e) {
					id = null;
				}
			}
			if(id!=null){
				AdminBean adminBean=new AdminBean();//初始化管理账户表操作类
				ProductTypeBean productTypeBean=new ProductTypeBean();//初始化产品类别bean
				OrdersBean odersBean=new OrdersBean();
				List<Product> list = new ArrayList<Product>();
				//获取产品的总记录数
				int totalRecords=getRecordCountByProductType(id);
				//执行分页
				pageObject=PageUtils.getPageObject(page, pageSize, totalRecords);
				pageObject.setList(list);
				Connection conn=DBHelper.getConnection();
				PreparedStatement pstmt=null;
				ResultSet rs=null;
				Product product=null;
				try{
					String sql="select * from product where productTypeId=? and onSale=true order by createTime desc limit ?,?";
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, id);
					pstmt.setInt(2, (pageObject.getCurrentPage()-1)*pageSize);
					pstmt.setInt(3, pageSize);
					rs=pstmt.executeQuery();
					while(rs.next()){
						product=new Product();
						product.setId(rs.getInt("id"));
						product.setProductType(productTypeBean.getProductType(rs.getInt("productTypeId"), conn));
						product.setName(rs.getString("name"));
						product.setOrderNum(rs.getInt("orderNum"));
						product.setDescription(rs.getString("description"));
						product.setContent(rs.getString("content"));
						product.setPrice(rs.getFloat("price"));
						product.setOriginalPrice(rs.getFloat("originalPrice"));
						product.setPicUrl(rs.getString("picUrl"));
						product.setNumber(rs.getInt("number"));
						product.setClick(rs.getInt("click"));
						product.setOnSale(rs.getBoolean("onSale"));
						product.setCreateTime(rs.getTimestamp("createTime"));
						product.setCreator(adminBean.getAdmin(rs.getInt("creatorId"), conn));
						product.setFinalEditor(adminBean.getAdmin(rs.getInt("finalEditorId"), conn));
						product.setUpdateTime(rs.getTimestamp("updateTime"));
						
						//为订单总价相加
						product.setTotalNumberOfOrder(odersBean.getOrderDetailCount(product.getId(),conn));
						list.add(product);
					}			
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					DBHelper.closeConn(conn,pstmt,rs);
				}
			}else{
				
			}
			return pageObject;			
		}
}

