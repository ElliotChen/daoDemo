package tw.elliot.dao.jpa;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import tw.elliot.dao.UserDao;
import tw.elliot.domain.Gender;
import tw.elliot.domain.Profile;
import tw.elliot.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "JpaDaoContext.xml" })
@Transactional
public class JpaUserDaoTest {
	@Autowired
	private UserDao userDao;

	@Test
	public void init() {
		Assert.assertNotNull(userDao);
	}

	@Test
	public void testCreate() {
		User user = new User();
		user.setName("Joe");
		user.setGender(Gender.Male);
		
		Profile profile = new Profile();
		profile.setAddress("Taipei");
		profile.setAdmin(Boolean.TRUE);
		
		user.setProfile(profile);
		
		this.userDao.create(user);
	}
	
	@Test
	public void testFindMethods() {
		Assert.assertEquals(1, this.userDao.findByName("Lily").size());
		User user = this.userDao.findByOid("user1");
		Assert.assertNotNull(user);
		Assert.assertEquals(2, user.getRoles().size());
	}
}
