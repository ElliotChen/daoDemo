package tw.elliot.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tw.elliot.util.DBUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "../dao/spring/SpringDaoContext.xml" })
public class InitTest {
	private static final Logger logger = LoggerFactory.getLogger(InitTest.class);
	@Autowired
	@Qualifier("dataSource")
	private DataSource boneDataSource = null;

	@Autowired
	@Qualifier("dbcpDataSource")
	private DataSource dbcpDataSource = null;

	@Ignore
	@Test
	public void testInit() throws Exception {
		DBUtils.close(boneDataSource.getConnection());
		DBUtils.close(dbcpDataSource.getConnection());
		for (int i = 0; i < 10; i++) {
			this.runTest(dbcpDataSource, "DBCP");
			this.runTest(boneDataSource, "BoneCP");
		}
		
	}

	private void runTest(DataSource ds, String name) {
		Connection conn = null;
		long begin = 0;
		long time = 0;
		
		Statement preStat = null;
		begin = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			try {
				
				conn = ds.getConnection();
				
				preStat = conn.createStatement();
				preStat.execute("SELECT 1");

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtils.close(preStat);
				DBUtils.close(conn);
			}
		}
		time += (System.currentTimeMillis() - begin);

		logger.info("[{}] used time [{}] ms", name, time);
	}
}
