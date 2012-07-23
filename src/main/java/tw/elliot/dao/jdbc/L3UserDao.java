package tw.elliot.dao.jdbc;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import tw.elliot.dao.UserDao;
import tw.elliot.domain.User;
import tw.elliot.domain.support.Condition;
import tw.elliot.domain.support.LikeMode;
import tw.elliot.domain.support.Page;
import tw.elliot.util.StringUtils;

public class L3UserDao extends L3AbstractDao implements UserDao {
	private static final String SELECT_SQL = "SELECT OID, NAME, CREATE_DATE FROM T_USER ";
	@Override
	public User findByOid(String oid) {
		List<User> users = this.execute(SELECT_SQL + "WHERE OID = ?",
				new Param[] { new Param("OID", Types.VARCHAR, oid) }, new UserRowParser());

		return users.isEmpty() ? null : users.get(0);
	}

	@Override
	public List<User> findByName(String name) {
		return this.execute(SELECT_SQL + "WHERE NAME = ?",
				new Param[] { new Param("NAME", Types.VARCHAR, name) },
				new UserRowParser());
	}

	@Override
	public void create(User entity) {
		this.execute("INSERT INTO T_USER(OID, NAME, VERSION) VALUES(?,?,?)", new Param[] {
				new Param("OID", Types.VARCHAR, entity.getOid()),
				new Param("NAME", Types.VARCHAR, entity.getName()),
				new Param("VERSION", Types.INTEGER, entity.getVersion())}, null);
	}

	@Override
	public void delete(User entity) {
		this.execute("DELETE FROM T_USER WHERE OID = ?", new Param[] { new Param(
				"OID", Types.VARCHAR, entity.getOid()) }, null);
	}

	@Override
	public void update(User entity) {
		this.execute("UPDATE T_USER SET NAME = ? WHERE OID = ?", new Param[] {
				new Param("NAME", Types.VARCHAR, entity.getName()),
				new Param("OID", Types.VARCHAR, entity.getOid()) }, null);
	}

	@Override
	public List<User> listAll() {
		return this.execute(SELECT_SQL, new Param[] {}, new UserRowParser());
	}

	@Override
	public List<User> listByExample(User example) {
		List<Param> params = new ArrayList<Param>();
		
		if (StringUtils.isNotEmpty(example.getOid())) {
			params.add(new Param("OID", Types.VARCHAR, example.getOid()));
		}
		
		if (StringUtils.isNotEmpty(example.getName())) {
			params.add(new Param("NAME", Types.VARCHAR, example.getName()));
		}
		
		if (null != example.getGender()) {
			params.add(new Param("GENDER", Types.VARCHAR, example.getGender().toString()));
		}
		if (null != example.getCreatedDate()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			params.add(new Param("CREATE_DATE", Types.DATE, Date.valueOf(sdf.format(example.getCreatedDate()))));
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT_SQL + "WHERE 1 = 1 ");
		for (Param param : params) {
			sql.append(" AND " + param.getColumn() + " = ? ");
		}
		
		return this.execute(sql.toString(), params.toArray(new Param[params.size()]), new UserRowParser());
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
	public List<User> listByExample(User example, List<Condition> conditions,
			LikeMode mode, String[] ascOrders, String[] descOrders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> listByPage(Page<User> page) {
		// TODO Auto-generated method stub
		return null;
	}


}

class UserRowParser implements RowParser<User> {
	List<User> users = new ArrayList<User>();

	@Override
	public User parse(ResultSet rs) throws SQLException {
		User user = new User();
		int i = 1;
		user.setOid(rs.getString(i++));
		user.setName(rs.getString(i++));
		user.setCreatedDate(rs.getTimestamp(i++));
		return user;
	}

	@Override
	public List<User> getResultList() {
		return users;
	}

}
