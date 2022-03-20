package com.lcvc.ebuy.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lcvc.ebuy.model.Admin;
import com.lcvc.ebuy.model.Page;

public class PageBean {
	/*
	 * 实现用户的分页
	 * */
	public List<Admin> pageAdmins(Page page){
		List<Admin> admins = new ArrayList<Admin>();
		try {
			Connection connection = DBHelper.getConnection();
			String sql = "select * from admin limit ?, ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, page.getPageSize() * (page.getPageNow() - 1));
			pstmt.setInt(2, page.getPageSize());
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				Admin admin=new Admin();
				admin.setUsername(rs.getString("username"));
				admin.setUserpass(rs.getString("userpass"));
				admin.setScreenName(rs.getString("screenName"));
				admin.setUserId(rs.getInt("userId"));
				admin.setCreateTime(rs.getTimestamp("createTime"));
				admins.add(admin);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return admins;
	}
	
	public Page page(Page page){
		try {
			Connection connection = DBHelper.getConnection();
			String sql = "select count(*) from admin";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				page.setLineCount(rs.getInt(1));
			}
			page.setPageCount(page.getLineCount() % page.getPageSize() == 0 ? page.getLineCount() / page.getPageSize() : page.getLineCount() / page.getPageSize() + 1);
			if(page.getPageNow() == 0){
				page.setPageNow(1);
			}else if(page.getPageNow() > page.getPageCount()){
				page.setPageNow(page.getPageCount());
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return page;
	}
	@Override
	public String toString() {
		return "PageBeam [pageAdmins()=" + pageAdmins(null) + "]";
	}

	
}
