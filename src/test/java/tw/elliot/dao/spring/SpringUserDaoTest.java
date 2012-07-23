package tw.elliot.dao.spring;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import tw.elliot.dao.UserDao;
import tw.elliot.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "SpringDaoContext.xml" })
@Transactional
public class SpringUserDaoTest {
	@Autowired
	private UserDao userDao;
	
	@Test
	public void testInit() {
		Assert.assertNotNull(userDao.findByOid("user1"));
		userDao.listAll();
		
		User apolo = new User();
		apolo.setOid("Apolo");
		apolo.setName("test1");
		apolo.setCreatedDate(new Date());
		apolo.setVersion(0);
		try {
			userDao.create(apolo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		User other = new User();
		other.setOid("otherOid");
		other.setName("test2");
		other.setCreatedDate(new Date());
		other.setVersion(0);
		try {
			userDao.create(other);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
