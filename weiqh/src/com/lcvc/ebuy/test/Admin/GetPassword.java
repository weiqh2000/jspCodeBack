package com.lcvc.ebuy.test.Admin;


import org.junit.*;
import com.lcvc.ebuy.bean.AdminBean;


public class GetPassword {
	
	private AdminBean adminBean = new AdminBean();
	
	@Test
	public void testLogin(){
		System.out.println(adminBean.getPassword(1));
	}
	
}