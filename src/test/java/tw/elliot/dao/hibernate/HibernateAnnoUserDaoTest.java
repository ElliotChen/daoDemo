package tw.elliot.dao.hibernate;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import tw.elliot.dao.UserDao;
import tw.elliot.domain.Gender;
import tw.elliot.domain.Profile;
import tw.elliot.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "AnnotationHibernateContext.xml" })
@Transactional
public class HibernateAnnoUserDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(HibernateAnnoUserDaoTest.class);
	@Autowired
	private UserDao userDao;
	
	@Test
	public void testInit() {
		logger.debug("Test Init !");
		Assert.assertNotNull(userDao);
	}
	
	@Test
	public void testCreate() {
		User user = new User();
		user.setName("Pippen");
		user.setGender(Gender.Male);
		
		Profile profile = new Profile();
		profile.setAddress("KeeLong");
		profile.setAdmin(Boolean.TRUE);
		
		user.setProfile(profile);
		
		this.userDao.create(user);
	}
	
	@Test
	public void testQBE() {
		String name = "Lily";
		User user = new User();
		user.setName(name);
		
		List<User> list = this.userDao.listByExample(user);
		
		List<User> list2 = this.userDao.findByName(name);
		
		logger.info("Test Find By Name[{}] by Example:{} and Name:{}",  new Object[] {name, list.size(), list2.size()});
		Assert.assertEquals(list.size(), list2.size());
	}
	
	@Test
	public void testFindMethods() {
		Assert.assertEquals(1, this.userDao.findByName("Joe").size());
		User user = this.userDao.findByOid("user1");
		Assert.assertNotNull(user);
		Assert.assertEquals(2, user.getRoles().size());
	}
}
