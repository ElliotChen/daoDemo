package tw.elliot.dao.jpa;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import tw.elliot.dao.UserDao;
import tw.elliot.domain.User;
import tw.elliot.domain.support.Condition;
import tw.elliot.domain.support.LikeMode;
import tw.elliot.domain.support.Page;

@Repository
public class JpaUserDao extends AbstractBaseDao<User, String> implements UserDao{

	@Override
	public List<User> findByName(String name) {
		//Demo JPQL
		TypedQuery<User> cq = this.getEntityManager().createQuery("SELECT u FROM User u WHERE u.name = ?1", this.getDomainClass());
		cq.setParameter(1, name);
		return cq.getResultList();
	}

	



}
