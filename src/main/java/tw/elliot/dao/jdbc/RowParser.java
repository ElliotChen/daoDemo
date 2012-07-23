package tw.elliot.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface RowParser<T> {
	List<T> getResultList();
	T parse(ResultSet rs) throws SQLException;
}
