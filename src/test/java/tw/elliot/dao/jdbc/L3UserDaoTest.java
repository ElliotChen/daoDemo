package tw.elliot.dao.jdbc;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "JdbcContext.xml" })
@Transactional
public class L3UserDaoTest {
	@Autowired
	private DataSource dataSource;
	@Test
	public void testInit() {
		L3UserDao dao = new L3UserDao();
		dao.setDataSource(dataSource);
		Assert.assertNotNull(dao.findByOid("user1"));
		dao.listAll();
	}
}
