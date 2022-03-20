package com.lcvc.ebuy.model;

import java.util.Date;
import java.util.List;
public class Orders{
	
	private Integer id;
	  
	private Customer customer;
	  
	  private String orderNo;
	  
	  private String sendName;
	  
	  private String sendAddress;
	  
	  private String sendZip;
	  
	  private String sendTel;
	  
	  private Integer payment;
	  
	  private String meno;
	  
	  private Date createTime;
	  
	  private Date dealTime;
	  
	  private Integer tag;
	  
	  private List<OrderDetail> orderDetails;
	  
	  private Float totalPrice;
	
	
	  public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public String getSendAddress() {
		return sendAddress;
	}

	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}

	public String getSendZip() {
		return sendZip;
	}

	public void setSendZip(String sendZip) {
		this.sendZip = sendZip;
	}

	public String getSendTel() {
		return sendTel;
	}

	public void setSendTel(String sendTel) {
		this.sendTel = sendTel;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	public String getMeno() {
		return meno;
	}

	public void setMeno(String meno) {
		this.meno = meno;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", customer=" + customer + ", orderNo="
				+ orderNo + ", sendName=" + sendName + ", sendAddress="
				+ sendAddress + ", sendZip=" + sendZip + ", sendTel=" + sendTel
				+ ", payment=" + payment + ", meno=" + meno + ", createTime="
				+ createTime + ", dealTime=" + dealTime + ", tag=" + tag
				+ ", orderDetails=" + orderDetails + ", totalPrice="
				+ totalPrice + "]";
	}

	
	
	
	
}