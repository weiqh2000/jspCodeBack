package com.lcvc.ebuy.model;

public class Page {
	
	//规定一页的大小为5行
	private int pageSize = 5;
	//一共有多少行——>即有多少行记录
	private int lineCount;
	//分了多少页
	private int pageCount;
	//当前页
	private int pageNow = 1;

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageSize() {
		return pageSize;
	}
	
	public int getLineCount() {
		return lineCount;
	}
	
	public void setLineCount(int lineCount) {
		this.lineCount = lineCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageNow() {
		return pageNow;
	}

	@Override
	public String toString() {
		return "Page [pageSize=" + pageSize + ", lineCount=" + lineCount
				+ ", pageCount=" + pageCount + ", pageNow=" + pageNow + "]";
	}

	
	
	
	
}
