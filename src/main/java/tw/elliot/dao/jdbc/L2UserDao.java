package tw.elliot.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import tw.elliot.dao.UserDao;
import tw.elliot.domain.User;
import tw.elliot.domain.support.Condition;
import tw.elliot.domain.support.LikeMode;
import tw.elliot.domain.support.Page;
import tw.elliot.util.DBUtils;

public class L2UserDao implements UserDao {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public User findByOid(String id) {
		List<User> users = this.executeQuery("SELECT OID, NAME, CREATE_DATE FROM T_USER WHERE OID = ?", new Object[] {id});
		
		return users.isEmpty() ? null : users.get(0);
	}
	
	@Override
	public List<User> findByName(String name) {
		return this.executeQuery("SELECT OID, NAME, CREATE_DATE FROM T_USER WHERE NAME = ?", new Object[] {name});
	}

	@Override
	public void create(User entity) {
		this.executeUpdate("INSERT INTO T_USER(OID, NAME, VERSION) VALUES(?,?,?)", new Object[] {entity.getOid(), entity.getName(), entity.getVersion()});
	}

	@Override
	public void delete(User entity) {
		this.executeUpdate("DELETE FROM T_USER WHERE OID = ?", new Object[] {entity.getOid()});
	}

	@Override
	public void update(User entity) {
		this.executeUpdate("UPDATE T_USER SET NAME = ? WHERE OID = ?", new Object[] {entity.getName(), entity.getOid()});
	}

	@Override
	public List<User> listAll() {
		return this.executeQuery("SELECT OID, NAME, CREATE_DATE FROM T_USER", new Object[] {});
	}

	@Override
	public List<User> listByExample(User example) {
		
		return null;
	}

	
	
	private List<User> executeQuery(String sql, Object[] params) {
		List<User> users = new ArrayList<User>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; ) {
				Object param = params[i++];
				
				if (null != param) {
					ps.setObject(i, param);
				} else {
					ps.setNull(i, Types.NULL);
				}
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				int i = 1;
				User user = new User();
				user.setOid(rs.getString(i++));
				user.setName(rs.getString(i++));
				user.setCreatedDate(rs.getTimestamp(i++));
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/*
			DBUtils.close(rs);
			DBUtils.close(ps);
			DBUtils.close(conn);
			*/
			DBUtils.close(rs, ps, conn);
		}
		
		return users;
	}
	
	private void executeUpdate(String sql, Object[] params) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; ) {
				Object param = params[i++];
				
				if (null != param) {
					ps.setObject(i, param);
				} else {
					ps.setNull(i, Types.NULL);
				}
			}
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/*
			DBUtils.close(ps);
			DBUtils.close(conn);
			*/
			DBUtils.close(null, ps, conn);
		}
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
