package com.nyl.ebuy.bean;

import com.mysql.jdbc.Statement;
import com.nyl.ebuy.model.ProductType;
import com.nyl.ebuy.model.exception.MyFormException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author phw
 *
 * @date 2017年8月26日
 *
 */
public class ProductTypeBean {
	/*
	 * 获取产品类别的所有记录
	 * 
	 * @return null表示获取失败
	 */
	public List<ProductType> getProductTypes() {

		List<ProductType> list = new ArrayList<ProductType>();
		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductType productType = null;
		ProductBean productBean = new ProductBean();
		try {
			String sql = "select * from producttype order by orderNum desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				productType = new ProductType();
				productType.setId(rs.getInt("id"));
				productType.setProductNumber(productBean.getRecordCountByProductType(rs.getInt("id")));
				productType.setImageUrl(rs.getString("imageUrl"));
				productType.setIntro(rs.getString("intro"));
				productType.setLinkUrl(rs.getString("linkUrl"));
				productType.setName(rs.getString("name"));
				productType.setOrderNum(rs.getInt("orderNum"));
				list.add(productType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.closeConn(conn, pstmt, rs);
		}
		return list;
	}
	
	/*
	 * 获取产品的初始值-用于表单创建
	 */
	public ProductType getProductTypeInit(){	
		ProductType productType=new ProductType();
		productType.setOrderNum(100);
		return productType;
	}

	/*
	 * 定义数据库的查询方法,根据id获取产品类别对象
	 * 
	 * @param id 账户标识符
	 * 
	 * @return null表示获取失败
	 */
	public ProductType getProductType(Object id) {
		Connection conn = DBHelper.getConnection();
		ProductType productType=null;
		try {
			productType = getProductType(id, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBHelper.closeConn(conn, null, null);//关闭数据库
		}
		return productType;
	}
	
	

	/*
	 * 根据传入的参数添加到数据库，并返回插入数据库后的标识符（适合数据库的自增字段）
	 * 
	 * @param productType 要添加的对象
	 * 
	 * @return null表示插入失败
	 */
	public Integer addProductType(ProductType productType) throws MyFormException {
		Integer id = null;// 获取插入记录后的标识符
		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "insert into producttype(id,name,linkUrl,imageUrl,intro, orderNum) values(null,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);// 要获取插入记录后的主键做法
			pstmt.setString(1, productType.getName());
			pstmt.setString(2, productType.getLinkUrl());
			pstmt.setString(3, productType.getImageUrl());
			pstmt.setString(4, productType.getIntro());
			pstmt.setInt(5, productType.getOrderNum());
			int i = pstmt.executeUpdate();
			if (i > 0) {// 如果插入成功
				rs = pstmt.getGeneratedKeys();// 获取插入后的标识符
				if (rs.next()) {
					id = rs.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.closeConn(conn, pstmt, rs);
		}
		return id;
	}

	/*
	 * 根据传入的参数更新数据库
	 * 
	 * @param productType 要更新的对象
	 * 
	 * @return true表示更新成功，false表示更新失败
	 */
	public boolean updateProductType(ProductType productType) throws MyFormException {
		boolean flag = false;
		if (getProductType(productType.getId()) != null) {
			Connection conn = DBHelper.getConnection();
			PreparedStatement pstmt = null;
			try {
				String sql = "update producttype set name = ?, linkUrl = ?, imageUrl = ?, intro = ?,orderNum=? where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, productType.getName());
				pstmt.setString(2, productType.getLinkUrl());
				pstmt.setString(3, productType.getImageUrl());
				pstmt.setString(4, productType.getIntro());
				pstmt.setInt(5, productType.getOrderNum());
				pstmt.setInt(6, productType.getId());
				int i = pstmt.executeUpdate();
				if (i > 0) {// 如果插入成功
					flag=true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBHelper.closeConn(conn, pstmt, null);
			}
		}else{
			throw new MyFormException("操作失败：该记录不存在");
		}
		return flag;
	}

	/*
	 * 删除指定的对象
	 * 说明：如果产品分类下还有产品信息，则无法删除该分类
	 * @param id 标识符
	 * 
	 * @return true表示删除成功，false表示删除失败
	 */
	public boolean deleteProductType(Object id) throws MyFormException{
		boolean flag = false;
		Integer productTypeId = null;
		if (id != null) {
			try {
				String s = id.toString();
				productTypeId = Integer.parseInt(s);
			} catch (Exception e) {
				productTypeId = null;
			}
		}
		if (productTypeId != null) {
			// 首先判断该产品分类下是否有产品
			ProductBean productBean = new ProductBean();
			int productCount = productBean.getRecordCountByProductType(productTypeId);
			if (productCount == 0) {
				Connection conn = DBHelper.getConnection();
				PreparedStatement pstmt = null;
				try {
					String sql = "delete from producttype where id = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, productTypeId);
					int i = pstmt.executeUpdate();
					if (i > 0) {
						flag = true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					DBHelper.closeConn(conn, pstmt, null);
				}
			}else{
				throw new MyFormException("操作失败：该产品分类下还有产品信息，无法删除");
			}
		} 
		return flag;
	}
	/*=======================带Connecttion的方法============================*/
	/*
	 * 定义数据库的查询方法,根据id获取产品类别对象，该数据库用于配合其他数据库操作方法(共享Connection)
	 * 
	 * @param id 账户标识符
	 * 
	 * @return null表示获取失败
	 */
	public ProductType getProductType(Object id,Connection conn) {
		ProductType productType = null;// 最后返回的对象
		Integer productTypeId = null;
		if (id != null) {
			try {
				String s = id.toString();
				productTypeId = Integer.parseInt(s);
			} catch (Exception e) {
				productTypeId = null;
			}
		}
		if (productTypeId != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "select * from producttype where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, productTypeId);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					productType = new ProductType();
					productType.setId(rs.getInt("id"));
					productType.setImageUrl(rs.getString("imageUrl"));
					productType.setIntro(rs.getString("intro"));
					productType.setLinkUrl(rs.getString("linkUrl"));
					productType.setName(rs.getString("name"));
					productType.setOrderNum(rs.getInt("orderNum"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBHelper.closeConn(null, pstmt, rs);
			}
		}
		return productType;
	}
}
