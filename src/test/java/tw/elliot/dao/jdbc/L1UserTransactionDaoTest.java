package tw.elliot.dao.jdbc;

import java.util.Date;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tw.elliot.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "JdbcContext.xml" })
public class L1UserTransactionDaoTest {
	@Autowired
	private DataSource dataSource;
	
	@Test
	@Rollback
	public void testCreate() {
		L1UserTransactionDao dao = new L1UserTransactionDao();
		dao.setDataSource(dataSource);
		
		User apolo = new User();
		apolo.setOid("Apolo");
		apolo.setName("test1");
		apolo.setCreatedDate(new Date());
		apolo.setVersion(0);
		try {
			dao.create(apolo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		User other = new User();
		other.setOid("otherOid");
		other.setName("test2");
		other.setCreatedDate(new Date());
		other.setVersion(0);
		dao.create(other);
		
//		dao.delete(other);
	}
}
