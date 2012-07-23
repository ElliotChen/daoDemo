package tw.elliot.dao.jdbc;

public class Param {
	private String column;
	private int type;
	private Object value;

	public Param(String column, int type, Object value) {
		this.column = column;
		this.type = type;
		this.value = value;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
