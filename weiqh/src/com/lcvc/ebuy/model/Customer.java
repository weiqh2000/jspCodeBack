package com.lcvc.ebuy.model;

import java.util.Date;


public class Customer{
	
	private Integer id;
	  
	  private String username;
	  
	  private String password;
	  
	  private String name;
	  
	  private String picUrl;
	  
	  private String tel;
	  
	  private String address;
	  
	  private String zip;
	  
	  private String email;
	  
	  private String intro;
	  
	  private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", username=" + username + ", password="
				+ password + ", name=" + name + ", picUrl=" + picUrl + ", tel="
				+ tel + ", address=" + address + ", zip=" + zip + ", email="
				+ email + ", intro=" + intro + ", createTime=" + createTime
				+ "]";
	}
	
	  
	  
	
}