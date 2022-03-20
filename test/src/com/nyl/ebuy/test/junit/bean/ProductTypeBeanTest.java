package com.nyl.ebuy.test.junit.bean;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.nyl.ebuy.bean.ProductTypeBean;

/*
 * 产品类别测试类
 */
public class ProductTypeBeanTest {
	private static ProductTypeBean productTypeBean;
	
	@BeforeClass
	// 注意,这里必须是static...因为方法将在类被装载的时候就被调用(那时候还没创建实例)
	public static void before() {
		productTypeBean=new ProductTypeBean();
	}

	@AfterClass
	public static void after() {
		productTypeBean=null;
	}
	
	/*
	 *  获取管理账户的所有记录
	 */
	/*@Test
	public void getAdminsTest() {
		List<Admin> list=adminBean.getAdmins();
		for(Admin admin:list){
			System.out.print(admin.getUserId()+"\t");
			System.out.print(admin.getUsername()+"\t");
			System.out.print(admin.getPassword()+"\t");
			System.out.println();
		}
	}*/
}
