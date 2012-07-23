package tw.elliot.dao.hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import tw.elliot.domain.Profile;

@Repository
public class HibernateProfileDao extends AbstractBaseDao<Profile, String> {
	private static final Logger logger = LoggerFactory.getLogger(HibernateProfileDao.class);
	@Override
	public Logger getLogger() {
		return logger;
	}
	
}
