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
	private EntityManager entityManager;
	@Test
	public void test() {
		userJpaDao.count();
		User example = new User();
		ExampleSpecification<User> es = new ExampleSpecification<User>(entityManager, example);
		es.toPredicate(null, null, null);
		es.toPredicate(null, null, null);
		es.toPredicate(null, null, null);
	}

}
