package tw.elliot.dao.hibernate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import tw.elliot.domain.Profile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "AnnotationHibernateContext.xml" })
@Transactional
public class HibernateProfileDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(HibernateProfileDaoTest.class);
	@Autowired
	private HibernateProfileDao profileDao;

	public HibernateProfileDao getProfileDao() {
		return profileDao;
	}

	public void setProfileDao(HibernateProfileDao profileDao) {
		this.profileDao = profileDao;
	}
	
	@Test
	@Rollback(false)
	public void testMethod1() {
		logger.debug("Test Init");
		Profile pf = new Profile();
		pf.setAdmin(Boolean.TRUE);
		
	}
}
