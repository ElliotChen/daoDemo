package tw.elliot.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import tw.elliot.dao.UserDao;
import tw.elliot.domain.User;
import tw.elliot.domain.support.Condition;
import tw.elliot.domain.support.LikeMode;
import tw.elliot.domain.support.Page;

public class L1UserDao implements UserDao {
	protected DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public User findByOid(String id) {
		User user = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT OID, NAME, CREATE_DATE FROM T_USER WHERE OID = ?");
			ps.setString(1, id);

			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				int i = 1;
				user.setOid(rs.getString(i++));
				user.setName(rs.getString(i++));
				user.setCreatedDate(rs.getTimestamp(i++));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != rs) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return user;
	}

	@Override
	public List<User> findByName(String name) {
		List<User> users = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT OID, NAME, CREATE_DATE FROM T_USER WHERE NAME = ?");
			ps.setString(1, name);

			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				int i = 1;
				user.setOid(rs.getString(i++));
				user.setName(rs.getString(i++));
				user.setCreatedDate(rs.getTimestamp(i++));

				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != rs) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return users;
	}

	@Override
	public void create(User entity) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("INSERT INTO T_USER(OID, NAME, VERSION) VALUES(?,?,?)");
			ps.setString(1, entity.getOid());
			ps.setString(2, entity.getName());
			ps.setInt(3, entity.getVersion());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(User entity) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("DELETE FROM T_USER WHERE OID = ?");
			ps.setString(1, entity.getOid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}

	}

	@Override
	public void update(User entity) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("UPDATE T_USER SET NAME = ? WHERE OID = ?");
			int i = 1;
			ps.setString(i++, entity.getName());
			ps.setString(i++, entity.getOid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	@Override
	public List<User> listAll() {
		List<User> users = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT OID, NAME, CREATE_DATE FROM T_USER ");

			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				int i = 1;
				user.setOid(rs.getString(i++));
				user.setName(rs.getString(i++));
				user.setCreatedDate(rs.getTimestamp(i++));
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != rs) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return users;
	}

	@Override
	public List<User> listByExample(User example) {

		return null;
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
