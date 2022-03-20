package com.lcvc.ebuy.test.Admin;

import org.junit.Test;
import com.lcvc.ebuy.bean.DBHelper;
import java.sql.*;

public class DBHelperTest {

	@Test
	public void  testGetConnection(){
		Connection conn = DBHelper.getConnection();
		System.out.println(conn);
		System.out.println("yes");
	}
	
	
}
