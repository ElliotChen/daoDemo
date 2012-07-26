package tw.elliot.dao.springjpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.elliot.domain.User;

@Service
@Transactional(readOnly=true)
public class UserService {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private UserJpaDao userDao;
	
	public List<User> findByExample(User example) {
		ExampleSpecification<User> es = new ExampleSpecification<User>(entityManager, example);
		return userDao.findAll(es);
	}
}
