package com.lcvc.ebuy.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * 购物车实体类
 * 说明：与数据库无关
 */
public class ShoppingCart {
	private List<ShoppingCartItem> list=new ArrayList<ShoppingCartItem>();//购物车子类集合
	//private Customer customer;//客户实体类
	
	//非关键字段，用于根据上面的字段计算得出
	private Integer numberOfProduct;//商品总数
	private Float originalPriceOfTotal;//购物车里面的原价格总和
	private Float priceOfTotal;//购物车里面的现价总和
	private Float priceOfTotalByRuduce;//总共节省的金额,原价-现价

	private Date createTime;//购物车创建时间
	
	public ShoppingCart(){
		
	}
	public List<ShoppingCartItem> getList() {
		return list;
	}
	public void setList(List<ShoppingCartItem> list) {
		this.list = list;
	}
	public Float getOriginalPriceOfTotal() {
		return originalPriceOfTotal;
	}
	public void setOriginalPriceOfTotal(Float originalPriceOfTotal) {
		this.originalPriceOfTotal = originalPriceOfTotal;
	}
	public Float getPriceOfTotal() {
		return priceOfTotal;
	}
	
	public void setPriceOfTotal(Float priceOfTotal) {
		this.priceOfTotal = priceOfTotal;
	}
	public Float getPriceOfTotalByRuduce() {
		return priceOfTotalByRuduce;
	}
	public void setPriceOfTotalByRuduce(Float priceOfTotalByRuduce) {
		this.priceOfTotalByRuduce = priceOfTotalByRuduce;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getNumberOfProduct() {
		return numberOfProduct;
	}
	public void setNumberOfProduct(Integer numberOfProduct) {
		this.numberOfProduct = numberOfProduct;
	}
	
	
}
