package com.lcvc.ebuy.test;

import java.util.List;

import org.junit.Test;

import com.lcvc.ebuy.bean.PageBean;
import com.lcvc.ebuy.model.Admin;
import com.lcvc.ebuy.model.Page;



public class PageBeanTest {

	private PageBean pageBean = new PageBean();
	private Page page1 = new Page();
	@Test
	public void pageBean(){
		pageBean.page(page1);
		List<Admin> admins = pageBean.pageAdmins(page1);
		System.out.println(admins.size());
		System.out.println(admins);
		for(Admin admin:admins){
			System.out.println(admin.getUsername());
			System.out.println(admin.getUsername());
			System.out.println(admin.toString());
		}
	}
}
