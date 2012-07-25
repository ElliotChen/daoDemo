package tw.elliot.dao.springjpa;

import java.lang.reflect.Method;

import javax.persistence.metamodel.SingularAttribute;

public class ExpressionParam<T> {
	private String name;
	private Method readMethod;
	private SingularAttribute<T, ?> attribute;
	
	public ExpressionParam(String name, Method readMethod, SingularAttribute<T, ?> attribute) {
		super();
		this.name = name;
		this.readMethod = readMethod;
		this.attribute = attribute;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Method getReadMethod() {
		return readMethod;
	}
	public void setReadMethod(Method readMethod) {
		this.readMethod = readMethod;
	}
	public SingularAttribute<T, ?> getAttribute() {
		return attribute;
	}
	public void setAttribute(SingularAttribute<T, ?> attribute) {
		this.attribute = attribute;
	}
	
	
}
