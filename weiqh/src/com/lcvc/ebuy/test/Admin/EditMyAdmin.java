package com.lcvc.ebuy.test.Admin;

import org.junit.Test;

import com.lcvc.ebuy.bean.AdminBean;
import com.lcvc.ebuy.model.Admin;

public class EditMyAdmin {
	
	private AdminBean adminBean=new AdminBean();
	private Admin admin=new Admin();
	@Test
	public void testEditAdmin(){
		admin.setUserId(1);
		admin.setUsername("user");
		admin.setScreenName("555d5");
		System.out.println(adminBean.deitMyadmin(admin));
	}
}
