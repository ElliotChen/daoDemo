package tw.elliot.util;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.Test;

public class DBUtilsTest {

	@Test
	public void testGetDriverManagerConnection() {
		Connection conn = DBUtils.getDriverManagerConnection();
		assertNotNull(conn);
		
		DBUtils.close(conn);
	}
	
	@Test
	public void testGetDataSourceConnection() {
		Connection conn = DBUtils.getDriverManagerConnection();
		assertNotNull(conn);
		
		DBUtils.close(conn);
	}

}
