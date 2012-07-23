package tw.elliot.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tw.elliot.domain.User;

public class L1UserTransactionDao extends L1UserDao {
	private static final Logger logger = LoggerFactory.getLogger(L1UserTransactionDao.class);
	@Override
	public void create(User entity) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			//Set AutoCommit as FALSE!
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement("INSERT INTO T_USER(OID, NAME, CREATE_DATE, VERSION) VALUES(?,?,?,?)");
			ps.setString(1, entity.getOid());
			ps.setString(2, entity.getName());
			ps.setDate(3, new Date(entity.getCreatedDate().getTime()));
			ps.setInt(4, entity.getVersion());
			
			ps.executeUpdate();
			//Try to throw an Exception
			if ("Apolo".equals(entity.getOid())) {
//				conn.commit();
				throw new RuntimeException("Test");
			}
			
			conn.commit();
		} catch (Exception e) {
			logger.error("Huston, we have a big problem! "+e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
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

}
