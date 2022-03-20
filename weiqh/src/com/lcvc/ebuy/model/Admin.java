package com.lcvc.ebuy.model;

import java.util.Date;

public class Admin {

	private Integer userId;
	private String username;
	private String userpass;
	private String screenName;
	private Date createTime;
	
	public Admin(){
		
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getScreenName() {
		return screenName;
	}

	@Override
	public String toString() {
		return "Admin [userId=" + userId + ", username=" + username
				+ ", userpass=" + userpass + ", screenName=" + screenName
				+ ", createTime=" + createTime + "]";
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
