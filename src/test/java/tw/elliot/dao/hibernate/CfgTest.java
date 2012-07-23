package tw.elliot.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tw.elliot.domain.Gender;
import tw.elliot.domain.User;

public class CfgTest {

	private static final Logger logger = LoggerFactory.getLogger(CfgTest.class);

	@Test
	public void test() {
		SessionFactory sf = new Configuration().configure("org/elliot/dao/hibernate/hibernate.cfg.xml").buildSessionFactory();
		Assert.assertNotNull(sf);
		User user = new User();
		user.setName("X");
		user.setGender(Gender.Female);
		Session session = sf.openSession();
		session.save(user);
		session.flush();

		User example = new User();
		example.setName("Joe");

		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Example.create(example));
		criteria.add(Restrictions.gt("version", 10));
		List<User> users = (List<User>) criteria.list();
		for (User u : users) {
			logger.info(u.getOid());
		}

		Query query = session.createQuery("from User as user where name = ?");
		query.setString(0, "Joe");
		users = (List<User>) query.list();
		for (User u : users) {
			logger.info(u.getOid());
		}

		session.close();
		sf.close();

	}
}
