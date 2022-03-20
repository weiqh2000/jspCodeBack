package com.lcvc.ebuy.test.Admin;

import java.util.List;

import org.junit.*;
import com.lcvc.ebuy.bean.AdminBean;
import com.lcvc.ebuy.model.Admin;

public class AdminBeanTest {
	
	private AdminBean adminBean = new AdminBean();
	
	@Test
	public void testLogin(){
		System.out.println(adminBean.login("user", "123456"));
		System.out.println(adminBean.login("ji", "15"));
	}
	
	@Test
	public void testGetAdmins(){
		List<Admin> admins = adminBean.getAdmins();
		System.out.println(admins.size());
		System.out.println(admins);
		for(Admin admin:admins){
			System.out.println(admin.getUsername());
			System.out.println(admin.getUsername());
			System.out.println(admin.toString());
		}
//		for(int i = 0;i < admins.size();i ++){
//			System.out.println(admins.toString());
//		}
	}
}
