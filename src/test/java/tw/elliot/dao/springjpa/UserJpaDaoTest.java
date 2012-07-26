package tw.elliot.dao.springjpa;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import tw.elliot.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "SpringJpaDaoContext.xml" })
@Transactional
public class UserJpaDaoTest {
	@Autowired
	private UserJpaDao userJpaDao;

	@Autowired
	private UserService userService;
	@Test
	public void test() {
		userJpaDao.count();
		User example = new User();
		example.setName("Bob");
		
		this.userService.findByExample(example);
	}

}
