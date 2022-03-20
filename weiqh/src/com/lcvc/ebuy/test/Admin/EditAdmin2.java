package com.lcvc.ebuy.test.Admin;

import org.junit.Test;

import com.lcvc.ebuy.bean.AdminBean;
import com.lcvc.ebuy.model.Admin;

public class EditAdmin2 {

	private AdminBean adminBean=new AdminBean();
	
	@Test
	public void testEditAdmin2(){
		Admin admin = new Admin();
		admin.setUserId(2);
		admin.setUsername("users");
		admin.setUserpass("123456");
		admin.setScreenName("测试用户");
		System.out.println(adminBean.deitAdmin(admin));
	}
}
