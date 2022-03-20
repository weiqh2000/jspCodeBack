package com.lcvc.ebuy.test.Admin;

import org.junit.Test;

import com.lcvc.ebuy.bean.AdminBean;
public class DeleteAdmin {
	private AdminBean adminBean=new AdminBean();
	@Test
	public void testDeladmin(){
		System.out.println(adminBean.deleteAamin(4));
	}
}