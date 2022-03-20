package com.lcvc.ebuy.model;

public class OrderDetail{
	
	private Integer id;

	private Orders orders;
	  
	private Product product;
	  
	private Float price;
	  
	private Float originalPrice;
	  
	private Integer number;
	  
	  
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

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", orders=" + orders + ", product="
				+ product + ", price=" + price + ", originalPrice="
				+ originalPrice + ", number=" + number + "]";
	}


	  
	  
}