package com.lcvc.ebuy.test.junit.bean;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lcvc.ebuy.bean.AdminBean;

/*
 * 账户测试类
 */
public class AdminBeanTest {
	private static AdminBean adminBean;
	
	@BeforeClass
	// 注意,这里必须是static...因为方法将在类被装载的时候就被调用(那时候还没创建实例)
	public static void before() {
		adminBean=new AdminBean();
	}

	@AfterClass
	public static void after() {
		adminBean=null;
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
	/*
	 *  定义数据库的查询方法,根据id获取账户对象
	 */
	/*@Test
	public void getAdminTest() {
		System.out.println(adminBean.getAdmin(0));//数据库不存在的标识符
		System.out.println(adminBean.getAdmin(1).getUsername());//数据库存在的标识符
	}*/
	
	/*
	 *  根据传入的参数添加到数据库，并返回插入数据库后的标识符（适合数据库的自增字段）
	 */
	/*@Test
	public void addAdminTest() {
		Admin admin=new Admin();
		admin.setUsername("test1");
		admin.setPassword("123456");
		admin.setScreenName("测试账号1");
		admin.setCreateTime(Calendar.getInstance().getTime());
		System.out.println(adminBean.addAdmin(admin));
	}*/
	
	/*
	 *  定义数据库的查询方法,根据id获取账户对象
	 */
	/*@Test
	public void updateAdminTest() {
		System.out.println(adminBean.updateAdmin(new Admin(1)));
		System.out.println(adminBean.updateAdmin(new Admin(0)));
	}*/
	
	/*
	 *  定义数据库的查询方法,根据id获取账户对象
	 */
	/*@Test
	public void deleteAdminTest() {
		System.out.println(adminBean.deleteAdmin(74));
		System.out.println(adminBean.deleteAdmin(0));
	}*/
	
	/*
	 *  定义数据库的查询方法,查询用户名和密码是否正确
	 */
	@Test
	public void loginTest() {
		System.out.println(adminBean.login("user","123456"));//错误的账户名和密码
		System.out.println(adminBean.login("user","123456").getUsername());//正确的账户名和密码
	}
	
	/*
	 * 根据用户名获取相同用户名的用户数量
	 */
	/*@Test
	public void getCountByUsernameTest() {
		System.out.println(adminBean.getCountByUsername("abc"));//数据库不存在的账户名，预期为0
		System.out.println(adminBean.getCountByUsername("liangdian"));//数据库存在的账户名，预期为1
	}*/
}
