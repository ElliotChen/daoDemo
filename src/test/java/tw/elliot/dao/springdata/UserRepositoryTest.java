package tw.elliot.dao.springdata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import tw.elliot.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "SpringDataDaoContext.xml" })
@Transactional
public class UserRepositoryTest {
	@Autowired
	private UserRepository userRepository;
	@Test
	@Rollback(false)
	public void test() {
		assertNotNull(userRepository);
		
		List<User> users = userRepository.findByName("Adam");
		assertEquals(1, users.size());
		
		userRepository.countByName("Adam");
	}

}
