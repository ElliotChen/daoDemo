package tw.elliot.dao;

import java.util.List;

import tw.elliot.domain.User;

public interface UserDao extends BaseDao<User, String> {
	public List<User> findByName(String name);
}
