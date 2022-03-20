package com.lcvc.ebuy.model;

public class ProductType {

	private Integer id;
	private String name;
	private String linkUrl;
	private String imageUrl;
	private String intro;
	private Integer orderNum;
	private String img_YesOrNo;
	private String link_YestOrNo;
	private Integer productQuantity;
	
	public Integer getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}
	public String getImg_YesOrNo() {
		return img_YesOrNo;
	}
	public void setImg_YesOrNo(String img_YesOrNo) {
		this.img_YesOrNo = img_YesOrNo;
	}
	public String getLink_YestOrNo() {
		return link_YestOrNo;
	}
	public void setLink_YestOrNo(String link_YestOrNo) {
		this.link_YestOrNo = link_YestOrNo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	@Override
	public String toString() {
		return "ProductType [id=" + id + ", name=" + name + ", linkUrl="
				+ linkUrl + ", imageUrl=" + imageUrl + ", intro=" + intro
				+ ", orderNum=" + orderNum + ", img_YesOrNo=" + img_YesOrNo
				+ ", link_YestOrNo=" + link_YestOrNo + ", productQuantity="
				+ productQuantity + "]";
	}
	
	
	
	
}
