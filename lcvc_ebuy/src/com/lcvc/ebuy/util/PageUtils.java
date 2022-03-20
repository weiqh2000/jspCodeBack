package com.lcvc.ebuy.util;

import com.lcvc.ebuy.model.other.PageObject;

/*
 * 分页工具
 * 该类仅仅实现对部分分页的  要求
 * @author ljy
 */
public class PageUtils {
	/*
	 * 根据分页参数，获取分页实体
	 * @param page 请求的当前页码
	 * @param pageSize 每页的记录数，不能为空
	 * @param totalRecords 总记录数
	 */
	public static PageObject getPageObject(Object page,final int pageSize,final int totalRecords){
		PageObject pageObject=new PageObject();
		pageObject.setPageSize(pageSize);//每页显示的记录数
		pageObject.setTotalRecords(totalRecords);//总记录数
		int maxPage = ((totalRecords % pageSize) == 0)?(totalRecords / pageSize):(totalRecords / pageSize + 1);
		pageObject.setMaxPage(maxPage);//显示的最大页数
		int currentPage;//显示当前页数
		//设置当前页码
		String page2=null;
		if(page==null) {
			page2="0";//此处统一为1，是为了归类，表示首页（第一页）
		}else{
			page2=page.toString();
		}
		try{
			currentPage=Integer.parseInt(page2);//获取当前页数
		}catch(NumberFormatException e){
			currentPage=0;
		}		
		//一下判断语句主要限定页数的范围，防止非法页数
		if (currentPage < 1) {//如果有些人故意输入的页数小于1，或者小于0，则指向第一页
			currentPage = 1;
		} else if(currentPage > maxPage){//如果输入的页数超过了最大页数，则指向最大页。
		      currentPage = maxPage;
		}
		pageObject.setCurrentPage(currentPage);
		//设置上一页
		int previousPage=currentPage-1;
		if(previousPage<1){
			previousPage=1;
		}
		pageObject.setPreviousPage(previousPage);
		//设置下一页
		int nextPage=currentPage+1;
		if(nextPage>maxPage){
			nextPage=maxPage;
		}
		pageObject.setNextPage(nextPage);		
		return pageObject;
	}
}
