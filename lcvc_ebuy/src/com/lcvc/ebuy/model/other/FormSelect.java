package com.lcvc.ebuy.model.other;

import java.util.LinkedHashMap;
import java.util.Map;

public class FormSelect {
	/*
	 * 产品上架下载选择列表
	 */
	public static final Map<Boolean,String> getOnSaleMap(){
		Map<Boolean,String> map=new LinkedHashMap<Boolean,String>();
		map.put(true, "上架");
		map.put(false, "下架");
		return map;
	}
	
	/*
	 * 订单状态选择列表
	 * 1、已付款，2、未付款，3、异常,4、申请取消，5、已作废
	 */
	public static final Map<Integer,String> getOrdersTagMap(){
		Map<Integer,String> map=new LinkedHashMap<Integer,String>();
		map.put(1, "已付款");
		map.put(2, "未付款");
		map.put(3, "异常");
		map.put(4, "申请取消");
		map.put(5, "已取消");
		return map;
	}
	
	/*
	 * 订单的付款方式
	 * 付款方式（1、货到付款；2、网上支付）
	 */
	public static final Map<Integer,String> getOrdersPaymentMap(){
		Map<Integer,String> map=new LinkedHashMap<Integer,String>();
		map.put(1, "货到付款");
		map.put(2, "网上支付");
		return map;
	}
}
