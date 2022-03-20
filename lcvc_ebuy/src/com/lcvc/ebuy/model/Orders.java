package com.lcvc.ebuy.model;

import java.util.ArrayList;
import java.util.List;


/**
 * 订单类
 */

public class Orders implements java.io.Serializable {
	private Integer id;
	private Customer customer;//客户类
	private String orderNo;//订单编号（由业务层生成）
	private String sendName;//收货人姓名
	private String sendAddress;//收货人地址
	private String sendZip;//收货人邮编
	private String sendTel;//收货人电话
	private Integer payment;//付款方式（1、货到付款；2、网上支付）
	private String meno;//订单备注
	private java.util.Date createTime;
	private java.util.Date dealTime;//付款时间
	private Integer tag;//订单处理标记（1、已付款，2、未付款，3、异常,4、申请取消，5、已作废）,1的情况下不可以作废
	
	//非数据库字段
	private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
	private Float totalPrice=0.0f;//该订单的总交易额，从扩展性说，总价可以放入数据库，本项目暂时不放
	//private Integer totalNumberOfOrder;//该订单的订单数量

	public Orders() {
	}

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

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(java.util.Date dealTime) {
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
	
	
}