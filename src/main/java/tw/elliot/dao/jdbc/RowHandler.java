package tw.elliot.dao.jdbc;

import java.sql.ResultSet;

public interface RowHandler {
	void handle(ResultSet rs);
}
