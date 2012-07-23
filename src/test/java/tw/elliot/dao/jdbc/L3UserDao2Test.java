package tw.elliot.dao.jdbc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import tw.elliot.dao.UserDao;
import tw.elliot.domain.Gender;
import tw.elliot.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "JdbcDiContext.xml" })
@Transactional
public class L3UserDao2Test {
	@Autowired
	private UserDao userDao;
	
	@Test
	public void testInit() {
		Assert.assertNotNull(userDao.findByOid("user1"));
		userDao.listAll();
		
		User example = new User();
		example.setName("Lily");
		Assert.assertEquals(1, userDao.listByExample(example).size());
		
		example = new User();
		example.setGender(Gender.Female);
		Assert.assertEquals(1, userDao.listByExample(example).size());
	}
}
