package tw.elliot.dao.ibatis;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tw.elliot.dao.UserDao;
import tw.elliot.domain.Gender;
import tw.elliot.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "IBatisContext.xml" })
public class IBatisUserDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(IBatisUserDaoTest.class);
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void testInit() {
		Assert.assertNotNull(userDao);
		
		User user = userDao.findByOid("user1");
		Assert.assertNotNull(user);
		
		logger.info("Gender is {}",user.getGender());
		
		List<User> list = userDao.findByName("Lily");
		Assert.assertEquals(1, list.size());
		
		User example = new User();
		example.setOid("user1");
		list = userDao.listByExample(example);
		Assert.assertEquals(1, list.size());
	}
	
	@Test
	public void testCreate() {
		User entity = new User();
		entity.setName("Adam");
		entity.setGender(Gender.Male);
		this.userDao.create(entity);
	}
}
