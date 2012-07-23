package tw.elliot.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import tw.elliot.dao.UserDao;
import tw.elliot.domain.User;
import tw.elliot.domain.support.Condition;
import tw.elliot.domain.support.LikeMode;
import tw.elliot.domain.support.Page;

public class IBatisUserDao extends SqlMapClientDaoSupport implements UserDao {

	@Override
	public User findByOid(String oid) {
		return (User) this.getSqlMapClientTemplate().queryForObject("User.selectByOid", oid);
	}

	@Override
	public void create(User entity) {
		entity.setOid(String.valueOf(System.currentTimeMillis()));
		entity.setVersion(0);
		this.getSqlMapClientTemplate().insert("User.insertUser", entity);
	}

	@Override
	public void delete(User entity) {
		this.getSqlMapClientTemplate().delete("User.deleteByOid", entity.getOid());
	}

	@Override
	public void update(User entity) {
		this.getSqlMapClientTemplate().update("User.updateUser", entity);
	}

	@Override
	public List<User> listAll() {
		return (List<User>)this.getSqlMapClientTemplate().queryForList("User.selectAllUsers");
	}

	@Override
	public List<User> listByExample(User example) {
		return (List<User>)this.getSqlMapClientTemplate().queryForList("User.selectByExample", example);
	}

	@Override
	public List<User> listByExample(User example, List<Condition> conditions, LikeMode mode, String[] ascOrders,
			String[] descOrders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findByName(String name) {
		return (List<User>)this.getSqlMapClientTemplate().queryForList("User.selectUserByName", name);
	}

	@Override
	public void saveOrUpdate(User entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void merge(User entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<User> listByPage(Page<User> page) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
