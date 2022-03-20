package com.lcvc.ebuy.bean;

import java.util.List;

import com.lcvc.ebuy.model.Product;
import com.lcvc.ebuy.model.ShoppingCart;
import com.lcvc.ebuy.model.ShoppingCartItem;
import com.lcvc.ebuy.model.exception.MyFormException;

/*
 * 购物车操作类
 */
public class ShoppingCartBean {
	
	/*
	 * 对购物车里的数据进行计算
	 * @param shoppingCart 需要核算的购物车对象，不能为Null
	 */
	public void countShoppingCart(ShoppingCart shoppingCart){
		if(shoppingCart!=null){
			Integer numberOfProduct=0;//商品总数
			Float originalPriceOfTotal=0.0f;//购物车里面的原价格总和
			Float priceOfTotal=0.0f;//购物车里面的现价总和
			Float priceOfTotalByRuduce=0.0f;//总共节省的金额
			//获取购物车里的商品集合
			List<ShoppingCartItem> list=shoppingCart.getList();
			for(ShoppingCartItem item:list){
				Product product=item.getProduct();
				numberOfProduct+=item.getNumber();
				float originalPrice=product.getOriginalPrice()*item.getNumber();
				float price=product.getPrice()*item.getNumber();
				float reducePrice=originalPrice-price;
				item.setOriginalPriceOfTotal(originalPrice);
				item.setPriceOfTotal(price);
				item.setPriceOfTotalByRuduce(reducePrice);
				originalPriceOfTotal+=originalPrice;
				priceOfTotal+=price;
				priceOfTotalByRuduce+=reducePrice;
			}
			shoppingCart.setNumberOfProduct(numberOfProduct);
			shoppingCart.setOriginalPriceOfTotal(originalPriceOfTotal);
			shoppingCart.setPriceOfTotal(priceOfTotal);
			shoppingCart.setPriceOfTotalByRuduce(priceOfTotalByRuduce);
		}
	}
	
	/*
	 * 将商品放入购物车内
	 * @param shoppingCart 购物车类
	 * @param productId 产品标识符
	 * @param numberOfSale 要购买的产品数量
	 * @Throws MyFormPropertyException 检查从web层传递过来的值是否合法
	 * @return 购物车类。特别说明：如果shoppingCart不为Null，则不需要获取返回值来保存购物车类
	 */
	public ShoppingCart addShoppingCart(ShoppingCart shoppingCart,Object productId,Object numberOfSale) throws MyFormException{
		int number;//要购买的产品数量
		try {
			String s=numberOfSale.toString();
			number=Integer.parseInt(s);
			if(number<=0){
				throw new MyFormException("操作错误：产品数量非法");
			}
		}catch(Exception e){
			throw new MyFormException("操作错误：产品数量传递参数非法");
		}
		ProductBean productBean=new ProductBean();
		Product product=productBean.getProduct(productId);
		if(product!=null){
			if(!product.getOnSale()){
				throw new MyFormException("操作错误：商品"+product.getName()+"已经下架，请移除该商品");
			}
			//检查库存是否足够
			if(number>product.getNumber()){
				throw new MyFormException("操作错误：商品"+product.getName()+"库存不足");
			}
		}else{
			throw new MyFormException("操作错误：商品读取失败");
		}
		if(shoppingCart==null){//如果购物车不存在则初始化
			shoppingCart=new ShoppingCart();
		}
		//将本次添加的商品转换为购物车条目
		ShoppingCartItem shoppingCartItem=new ShoppingCartItem(product,number);
		//获取购物车里的商品集合
		List<ShoppingCartItem> list=shoppingCart.getList();
		if(list.contains(shoppingCartItem)){//如果包含该商品条目，已经重写该类的equals方法
			//获取购物车对应的子类
			ShoppingCartItem shoppingCartItemOfOriginal=list.get(list.indexOf(shoppingCartItem));
			//获取原来购物车内该商品的数量
			Integer numberOfCart=shoppingCartItemOfOriginal.getNumber();
			//将商品数量相加
			number+=numberOfCart;
			//检查库存是否足够
			if(number<=product.getNumber().intValue()){
				shoppingCartItemOfOriginal.setNumber(number);
			}else{
				throw new MyFormException("操作错误：商品"+product.getName()+"库存不足");
			}
		}else{//如果不包含则该商品则直接添加
			list.add(shoppingCartItem);
		}
		//最后对购物车中数据进行计算
		countShoppingCart(shoppingCart);
		return shoppingCart;
	}
	
	/*
	 * 修改购物车,根据传入的产品和数量信息，将购物车的指定产品改为相关数量
	 * @param shoppingCart 购物车类
	 * @param productId 产品标识符
	 * @param numberOfSale 要购买的产品数量
	 * @Throws MyFormPropertyException 检查从web层传递过来的值是否合法
	 */
	public void updateShoppingCart(ShoppingCart shoppingCart,Object productId,Object numberOfSale) throws MyFormException{
		int number;//要购买的产品数量
		try {
			String s=numberOfSale.toString();
			number=Integer.parseInt(s);
			if(number<=0){
				throw new MyFormException("操作错误：产品数量非法");
			}
		}catch(Exception e){
			throw new MyFormException("操作错误：产品数量传递参数非法");
		}
		ProductBean productBean=new ProductBean();
		Product product=productBean.getProduct(productId);
		if(product!=null){
			if(!product.getOnSale()){
				throw new MyFormException("操作错误：商品"+product.getName()+"已经下架，请移除该商品");
			}
			//检查库存是否足够
			if(number>product.getNumber().intValue()){
				throw new MyFormException("操作错误：商品"+product.getName()+"库存不足");
			}
		}else{
			throw new MyFormException("操作错误：商品读取失败");
		}
		if(shoppingCart==null){//如果购物车不存在则初始化
			shoppingCart=new ShoppingCart();
		}
		//将本次添加的商品转换为购物车条目
		ShoppingCartItem shoppingCartItem=new ShoppingCartItem(product,number);
		//获取购物车里的商品集合
		List<ShoppingCartItem> list=shoppingCart.getList();
		if(list.contains(shoppingCartItem)){//如果包含该商品条目，已经重写该类的equals方法
			//获取购物车对应的子类
			ShoppingCartItem shoppingCartItemOfOriginal=list.get(list.indexOf(shoppingCartItem));
			//将新的商品数量放入
			shoppingCartItemOfOriginal.setNumber(number);
		}else{//如果不包含则该商品则直接添加
			list.add(shoppingCartItem);
		}
		//最后对购物车中数据进行计算
		countShoppingCart(shoppingCart);
	}
	
	/*
	 * 将商品从购物车内删除
	 * @param shoppingCart 购物车类
	 * @param productId 产品标识符
	 * @Throws MyFormPropertyException 检查从web层传递过来的值是否合法
	 */
	public void removeShoppingCart(ShoppingCart shoppingCart,Object productId) throws MyFormException{
		//删除时不需要对该商品是否存在作验证
		int id;//要购买的产品数量
		try {
			String s=productId.toString();
			id=Integer.parseInt(s);
		}catch(Exception e){
			throw new MyFormException("操作错误：产品数量传递参数非法");
		}
		Product product=new Product(id);
		if(shoppingCart!=null){
			List<ShoppingCartItem> list=shoppingCart.getList();
			if(list.size()>0){
				//将本次添加的商品转换为购物车条目
				ShoppingCartItem shoppingCartItem=new ShoppingCartItem(product);
				list.remove(shoppingCartItem);		
			}
		}
		//最后对购物车中数据进行计算
		countShoppingCart(shoppingCart);
	}
}
