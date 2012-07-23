package tw.elliot.dao.hibernate;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "HibernateContext.xml" })
@Transactional
public class HibernateUserDaoTest extends HibernateAnnoUserDaoTest{
	
}
