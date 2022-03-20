package com.nyl.ebuy.model;

import java.util.Date;

/**
 * 管理账户
 */

public class Admin implements java.io.Serializable{

	private Integer userId;
	private String username;//用户名
	private String password;//用户密码
	private String screenName;//网名
	private Date createTime;//创建时间

	public Admin() {
	}
	
	public Admin(Integer userId){
		super();
		this.userId=userId;
		
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getScreenName() {
		return screenName;
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

	/*重写hashCode方法，当通过集合判断对象是否相等时，必须重写该方法*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	/*重写equals方法，让对象能通过该方法直接判断是否相等
	 * 以对象标识符判断是否相等
	 */
	@Override
	public boolean equals(Object object) {
		if(this==object){//如果两个对象地址相等，则直接相等。
			return true;
		}
		if(object==null){//如果该对象为Null，则直接不相等
			return false;
		}
		if(object.getClass() == Admin.class){//如果该对象是属于这个类
			Admin userEquals = (Admin)object;     
			if(userEquals.getUserId()==null){
				return false;
			}
			if(this.getUserId()==null){
				return false;
			}
            return userEquals.getUserId()==this.getUserId().intValue();//判断该对象是否与本对象的某属性相等
        }
		return false;     
	}
	
	
	
}