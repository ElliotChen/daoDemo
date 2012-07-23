package tw.elliot.dao.jdbc;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "JdbcContext.xml" })
public class L1UserDaoTest {
	@Autowired
	private DataSource dataSource;
	@Test
	public void testInit() {
		L1UserDao dao = new L1UserDao();
		dao.setDataSource(dataSource);
		Assert.assertNotNull(dao.findByOid("user1"));
		dao.listAll();
	}
}
