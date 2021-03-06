package com.lcvc.ebuy.model;


/**
 * 产品
 * 
 */

public class Product implements java.io.Serializable {

	private Integer id;
	private ProductType productType;//所属产品类别
	private String name;//产品名称
	private Integer orderNum;//优先级
	private String description;//产品描述
	private String content;//产品详细描述
	private Float price;//产品现价
	private Float originalPrice;//产品原价
	private String picUrl;//产品图片路径
	private Integer number;//库存数量
	private Integer click;//点击数
	private Boolean onSale;//是否上架（true表示上架，但是要考虑上架时间；false表示不上架）
	private java.util.Date createTime;//创建时间
	private Admin creator;//创建产品管理员
	private Admin finalEditor;//最后编辑管理员
	private java.util.Date updateTime;//最后编辑时间
	
	//非数据库字段
	//private List<ProductOrder> productOrders = new ArrayList<ProductOrder>();//该产品对应的订单
	//private Float totalPriceOfTrade;//该产品的总交易额
	private Integer totalNumberOfOrder;//该产品的订单数
	
	//private Integer numberOfCart;//获取购物车的数量，用于在下订单时候显示
	
	//用于前台显示字段
	//private Integer onSaleOfSelect;//上架选择，目前是-1表示永久上架、1表示临时上架和0表示下架

	public Product() {
	}
	
	public Product(int id) {
		this.id=id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
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

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	public Integer getTotalNumberOfOrder() {
		return totalNumberOfOrder;
	}

	public void setTotalNumberOfOrder(Integer totalNumberOfOrder) {
		this.totalNumberOfOrder = totalNumberOfOrder;
	}

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

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}