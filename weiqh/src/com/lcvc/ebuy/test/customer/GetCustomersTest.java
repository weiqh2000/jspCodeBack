package com.lcvc.ebuy.test.customer;

import java.util.Date;

import org.junit.Test;

import com.lcvc.ebuy.bean.CustomerBean;
import com.lcvc.ebuy.model.Customer;

public class GetCustomersTest{
	
	private CustomerBean customerBean = new CustomerBean();
	private Customer customer = new  Customer();
	//@Test
	public void GetCustomers(){
		System.out.println(customerBean.getCustomers().size());
	}
	
	//@Test
	public void Delete(){
		System.out.println(customerBean.delete(15));
	}
//	@Test
	public void addCustomer(){
		customer.setAddress("Test");
		customer.setUsername("Test");
		customer.setName("Test");
		customer.setPicUrl("Test");
		customer.setTel("Test");
		customer.setAddress("Test");
		customer.setZip("Test");
		customer.setEmail("Test");
		customer.setIntro("Test");
		
		System.out.println(customerBean.addCustomer(customer));
	}
	
//	@Test
	public void UpdateCustomer(){
		
		System.out.println(customerBean.updateCustomer(1));
	}
	@Test
	public void doUpdateCustomer(){
		customer.setId(9);
		customer.setAddress("Test");
		customer.setUsername("Test");
		customer.setName("Test");
		customer.setPicUrl("Test");
		customer.setTel("Test");
		customer.setAddress("Test");
		customer.setZip("Test");
		customer.setEmail("Test");
		customer.setIntro("Test");
		System.out.println(customerBean.doUpdateCustomer(customer));
	}
	
}