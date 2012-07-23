package tw.elliot.dao.spring;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import tw.elliot.dao.UserDao;
import tw.elliot.domain.User;
import tw.elliot.domain.support.Condition;
import tw.elliot.domain.support.LikeMode;
import tw.elliot.domain.support.Page;

public class SpringUserDao implements UserDao {
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getSimpleJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public User findByOid(String id) {
		return jdbcTemplate.queryForObject(
				"SELECT OID, NAME FROM T_USER WHERE OID = ?",
				new UserRowMapper(), new Object[] { id });
	}

	@Override
	public void create(User entity) {
		this.jdbcTemplate.update(
				"INSERT INTO T_USER(OID, NAME, CREATE_DATE, VERSION) VALUES(?,?,?,?)", entity.getOid(),
				entity.getName(), new Date(entity.getCreatedDate().getTime()), entity.getVersion());
		if ("Apolo".equals(entity.getOid())) {
			throw new RuntimeException("Problem!");
		}
	}

	@Override
	public void delete(User entity) {
		this.jdbcTemplate.update("DELETE FROM T_USER WHERE OID = ?",
				entity.getOid());
	}

	@Override
	public void update(User entity) {
		this.jdbcTemplate.update(
				"UPDATE T_USER SET NAME = ? WHERE OID = ?", entity.getName(),
				entity.getOid());
	}

	@Override
	public List<User> listAll() {
		return this.jdbcTemplate.query("SELECT OID, NAME FROM T_USER",
				new UserRowMapper(), new Object[] {});
	}

	@Override
	public List<User> listByExample(User example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> listByExample(User example, List<Condition> conditions,
			LikeMode mode, String[] ascOrders, String[] descOrders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findByName(String name) {
		return this.jdbcTemplate.query(
				"SELECT OID, NAME FROM T_USER WHERE OID = ?",
				new UserRowMapper(), new Object[] { name });
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

class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int number) throws SQLException {
		User user = new User();
		user.setOid(rs.getString(1));
		user.setName(rs.getString(2));
		return user;
	}

}
