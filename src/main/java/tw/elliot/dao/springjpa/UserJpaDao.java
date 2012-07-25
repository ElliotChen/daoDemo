package tw.elliot.dao.springjpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import tw.elliot.domain.User;

@Repository
public interface UserJpaDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User>{
	
}
