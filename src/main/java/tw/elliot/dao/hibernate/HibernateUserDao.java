package tw.elliot.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import tw.elliot.dao.UserDao;
import tw.elliot.domain.User;
@Repository
public class HibernateUserDao extends AbstractBaseDao<User, String> implements UserDao {
	private static final Logger logger = LoggerFactory.getLogger(HibernateUserDao.class);
	@Override
	public List<User> findByName(String name) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(User.class)
				.add(Restrictions.eq("name", name));
		return criteria.list();
	}
	@Override
	public Logger getLogger() {
		return logger;
	}

}
