package tw.elliot.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import tw.elliot.util.DBUtils;

public abstract class L3AbstractDao {
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public <T> List<T> execute(String sql, Param[] params, RowParser<T> parser) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length;) {
				Param param = params[i++];
				if (null != param.getValue()) {
					ps.setObject(i, param.getValue(), param.getType());
				} else {
					ps.setNull(i, param.getType());
				}
			}
			
			if (ps.execute() && (null != parser)) {
				rs = ps.getResultSet();
				while (rs.next()) {
					parser.getResultList().add(parser.parse(rs));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs);
			DBUtils.close(ps);
			DBUtils.close(conn);
		}
		
		return null != parser ? parser.getResultList() : null;
	}
}
