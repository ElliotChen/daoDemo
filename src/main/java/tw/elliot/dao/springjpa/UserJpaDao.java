package tw.elliot.dao.springjpa;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import tw.elliot.domain.User;

@Repository
public interface UserJpaDao extends PagingAndSortingRepository<User, String>, JpaSpecificationExecutor<User>{
	
}
