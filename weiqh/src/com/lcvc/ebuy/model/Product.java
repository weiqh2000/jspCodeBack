package com.lcvc.ebuy.model;

import java.util.Date;

public class Product {

	private Integer id;
	private Integer productTypeId;
	private String name;
	private Integer orderNum;
	private String description;
	private String content;
	private Float price;
	private Float originalPrice;
	private String picUrl;
	private Integer number;
	private Integer click;
	private Boolean onSale;
	private Date createTime;
	private Integer creatorId;
	private Integer finalEditorId;
	private Date updateTime;
	private String onSale_String;
	private Admin admin;
	private ProductType productType;

	public Admin getCreator() {
		return creator;
	}
	public void setCreator(Admin creator) {
		this.creator = creator;
	}
	public Admin getFinalEditor() {
		return finalEditor;
	}
	public void setFinalEditor(Admin finalEditor) {
		this.finalEditor = finalEditor;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public void setOriginalPrice(Float originalPrice) {
		this.originalPrice = originalPrice;
	}
	private Admin creator;//创建产品管理员
	private Admin finalEditor;//最后编辑管理员

	
	//非数据库字段
	//private List<ProductOrder> productOrders = new ArrayList<ProductOrder>();//该产品对应的订单
	//private Float totalPriceOfTrade;//该产品的总交易额
	private Integer totalNumberOfOrder;//该产品的订单数
	
	public Integer getTotalNumberOfOrder() {
		return totalNumberOfOrder;
	}
	public void setTotalNumberOfOrder(Integer totalNumberOfOrder) {
		this.totalNumberOfOrder = totalNumberOfOrder;
	}
	public String getOnSale_String() {
		return onSale_String;
	}
	public void setOnSale_String(String onSale_String) {
		this.onSale_String = onSale_String;
	}
	
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public Integer getProductTypeId() {
		return productTypeId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(float originalPrice) {
		this.originalPrice = originalPrice;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getClick() {
		return click;
	}
	public void setClick(Integer click) {
		this.click = click;
	}
	public Boolean getOnSale() {
		return onSale;
	}
	public void setOnSale(Boolean onSale) {
		this.onSale = onSale;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}
	public Integer getFinalEditorId() {
		return finalEditorId;
	}
	public void setFinalEditorId(Integer finalEditorId) {
		this.finalEditorId = finalEditorId;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", productTypeId=" + productTypeId
				+ ", name=" + name + ", orderNum=" + orderNum
				+ ", description=" + description + ", content=" + content
				+ ", price=" + price + ", originalPrice=" + originalPrice
				+ ", picUrl=" + picUrl + ", number=" + number + ", click="
				+ click + ", onSale=" + onSale + ", createTime=" + createTime
				+ ", creatorId=" + creatorId + ", finalEditorId="
				+ finalEditorId + ", updateTime=" + updateTime
				+ ", onSale_String=" + onSale_String + ", admin=" + admin
				+ ", productType=" + productType + "]";
	}
	
	
	
	
	
	
}
