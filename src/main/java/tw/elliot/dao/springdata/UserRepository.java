package tw.elliot.dao.springdata;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tw.elliot.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
	public List<User> findByName(String name);
	Long countByName(String name);
}
