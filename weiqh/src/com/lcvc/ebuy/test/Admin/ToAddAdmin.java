package com.lcvc.ebuy.test.Admin;

import org.junit.Test;

import com.lcvc.ebuy.bean.AdminBean;
import com.lcvc.ebuy.model.Admin;

public class ToAddAdmin {
	private AdminBean adminBean=new AdminBean();
	@Test
	public void setAddAdmin(){
		Admin admin = new Admin();
		admin.setUsername("root");
		admin.setUserpass("123456f");
		admin.setScreenName("测试用户");
		System.out.println(adminBean.queryUsername(admin.getUsername()));
		System.out.println(adminBean.saveAdmin(admin));
	}
}
