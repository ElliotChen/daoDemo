package tw.elliot.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tw.elliot.domain.Gender;
import tw.elliot.domain.User;

public class HibernateCfgTest {
	private static final Logger logger = LoggerFactory.getLogger(HibernateCfgTest.class);
	
	@Test
	public void testLoadCfg() {
		SessionFactory sf = new Configuration().configure("org/elliot/dao/hibernate/hibernate.cfg.xml").buildSessionFactory();
		Assert.assertNotNull(sf);
		String name = "EG";
		
		User user = new User();
		user.setName(name);
		user.setGender(Gender.Male);
		user.setCreatedAccount("NA");
		user.setModifiedAccount("NA");
		
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(user);
		logger.info(user.getOid());
		transaction.commit();
		session.flush();
		
		//Criteria Search
		User example = new User();
		example.setName(name);
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Example.create(example));
		List<User> users = (List<User>) criteria.list();
		Assert.assertEquals(1, users.size());
		
		//HQL
		Query query = session.createQuery("from User as user where name = ?");
		query.setString(0, name);
		users = (List<User>) query.list();
		Assert.assertEquals(1, users.size());
		
		
		query = session.createQuery("select distinct name from User as user");
		List<String> names = (List<String>)query.list();
		for (String n : names) {
			logger.info(n);
		}
		
		/*
		session.delete(user);
		session.flush();
		*/
		session.close();
		sf.close();
		
		
	}
}
