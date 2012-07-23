package tw.elliot.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import tw.elliot.dao.RoleDao;
import tw.elliot.domain.Role;
import tw.elliot.domain.support.Condition;
import tw.elliot.domain.support.LikeMode;
import tw.elliot.domain.support.Page;
import tw.elliot.util.DBUtils;

public class L2RoleDao implements RoleDao {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Role findByOid(String id) {
		List<Role> roles = this.executeQuery("SELECT oid, name FROM Role WHERE oid = ?", new Object[] {id});
		
		return roles.isEmpty() ? null : roles.get(0);
	}

	@Override
	public void create(Role entity) {
		this.executeUpdate("INSERT INTO Role(oid, name) VALUES(?,?)", new Object[] {entity.getOid()});
	}

	@Override
	public void delete(Role entity) {
		this.executeUpdate("DELETE FROM Role WHERE oid = ?", new Object[] {entity.getOid()});
	}

	@Override
	public void update(Role entity) {
		this.executeUpdate("UPDATE Role SET name = ? WHERE oid = ?", new Object[] {entity.getOid()});
	}

	@Override
	public List<Role> listAll() {
		return this.executeQuery("SELECT oid, name FROM Role", new Object[] {});
	}

	@Override
	public List<Role> listByExample(Role example) {
		
		return null;
	}
	
	
	private List<Role> executeQuery(String sql, Object[] params) {
		List<Role> roles = new ArrayList<Role>();
		
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
				Role role = new Role();
				role.setOid(rs.getString(1));
				
				roles.add(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs);
			DBUtils.close(ps);
			DBUtils.close(conn);
		}
		
		return roles;
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
			DBUtils.close(ps);
			DBUtils.close(conn);
		}
	}

	@Override
	public void saveOrUpdate(Role entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void merge(Role entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Role> listByExample(Role example, List<Condition> conditions,
			LikeMode mode, String[] ascOrders, String[] descOrders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Role> listByPage(Page<Role> page) {
		// TODO Auto-generated method stub
		return null;
	}

}
