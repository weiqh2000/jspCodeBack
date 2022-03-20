package com.lcvc.ebuy.test.Admin;


import org.junit.*;
import com.lcvc.ebuy.bean.AdminBean;


public class ChangePasswordTest {
	
	private AdminBean adminBean = new AdminBean();
	
	@Test
	public void testLogin(){
		System.out.println(adminBean.changePassword(1, "123456", "asd"));
	}
	
}