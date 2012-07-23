package tw.elliot.domain.support;

public interface Condition {
	public String toSqlString();
	public ConditionEnum getConditionEnum();
}
