package com.lcvc.ebuy.model;

/**
 * 详细订单
 * 
 */

public class OrderDetail implements java.io.Serializable {
	private Integer id;
	private Orders orders;// 订单编号（外键）
	private Product product;// 产品编号（外键）
	private Float price;// 交易价格（下订单时的产品价格）
	private Float originalPrice;// 原价
	private Integer number;// 购买产品数量
	
	//非数据库字段
	//private Float originalPriceTotal;//该订单的原价格总和
	//private Float priceTotal;//该订单的现价总和
	//private Float priceTotalByRuduce;//总共节省的金额

	public OrderDetail() {
	}
	
	public OrderDetail(int id) {
		this.id=id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	
	public Float getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Float originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
}